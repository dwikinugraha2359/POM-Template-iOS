// package com.media2359.helper;

// import java.io.BufferedWriter;
// import java.io.File;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.net.InetAddress;
// import java.net.URL;
// import java.nio.charset.Charset;
// import java.nio.file.Files;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Map;
// import java.util.Optional;
// import java.util.concurrent.TimeUnit;
// import java.util.logging.Logger;

// import org.openqa.selenium.net.UrlChecker;

// /**
//  * WDAServer
//  */
// public class WDAServer {
//     private static final Logger log = ZLogger.getLog(WDAServer.class.getSimpleName());

//     private static WDAServer instance = null;
//     private final boolean isRealDevice;
//     private final String deviceId;
//     private final String platformVersion;

//     private WDAServer() {
//         try {
//             this.isRealDevice = !getIsSimulatorFromConfig(getClass());
//             String udid;
//             if (isRealDevice) {
//                 udid = IOSRealDeviceHelpers.getUDID();
//             } else {
//                 udid = IOSSimulatorHelpers.getId();
//             }
//             this.deviceId = udid;
//             this.platformVersion = getPlatformVersionFromConfig(getClass());
//         } catch (Exception e) {
//             throw new RuntimeException(e);
//         }
//         ensureToolsExistence();
//         ensureParentDirExistence();

//     }

//     public synchronized static WDAServer getInstance() {
//         if (instance == null) {
//             instance = new WDAServer();
//         }
//         return instance;
//     }

//     private static final int PORT = 8100;
//     private static final Timedelta RESTART_TIMEOUT = Timedelta.ofSeconds(90);
//     public static final String SERVER_URL = String.format("http://127.0.0.1:%d", PORT);

//     private boolean waitUntilIsRunning(Timedelta timeout) throws Exception {
//         final URL status = new URL(SERVER_URL + "/status");
//         try {
//             new UrlChecker().waitUntilAvailable(timeout.asMillis(), TimeUnit.MILLISECONDS, status);
//             return true;
//         } catch (UrlChecker.TimeoutException e) {
//             return false;
//         }
//     }

//     // These settings are needed to properly sign WDA for real device tests
//     // See https://github.com/appium/appium-xcuitest-driver for more details
//     private static final File KEYCHAIN = new File(String.format("%s/%s",
//             System.getProperty("user.home"), "/Library/Keychains/MyKeychain.keychain"));
//     private static final String KEYCHAIN_PASSWORD = "******";

//     private static final File IPROXY_EXECUTABLE = new File("/usr/local/bin/iproxy");
//     private static final File XCODEBUILD_EXECUTABLE = new File("/usr/bin/xcodebuild");
//     private static final File WDA_PROJECT =
//             new File("/usr/local/lib/node_modules/appium/node_modules/appium-xcuitest-driver/" +
//                     "WebDriverAgent/WebDriverAgent.xcodeproj");
//     private static final String WDA_SCHEME = "WebDriverAgentRunner";
//     private static final String CONFIGURATION = "Debug";
//     public static final File XCODEBUILD_LOG = new File("/usr/local/var/log/appium/build.log");
//     public static final File IPROXY_LOG = new File("/usr/local/var/log/appium/iproxy.log");

//     private static final String[] IPROXY_CMDLINE = new String[]{
//             IPROXY_EXECUTABLE.getAbsolutePath(),
//             Integer.toString(PORT),
//             Integer.toString(PORT),
//             String.format("> %s 2>&1 &", IPROXY_LOG.getAbsolutePath())
//     };

//     private static void ensureParentDirExistence() {
//         if (!XCODEBUILD_LOG.getParentFile().exists()) {
//             if (!XCODEBUILD_LOG.getParentFile().mkdirs()) {
//                 throw new IllegalStateException(String.format(
//                         "The script has failed to create '%s' folder for Appium logs. " +
//                                 "Please make sure your account has correct access permissions on the parent folder(s)",
//                         XCODEBUILD_LOG.getParentFile().getAbsolutePath()));
//             }
//         }
//     }

//     private void ensureToolsExistence() {
//         if (isRealDevice && !IPROXY_EXECUTABLE.exists()) {
//             throw new IllegalStateException(String.format("%s tool is expected to be installed (`npm install -g iproxy`)",
//                     IPROXY_EXECUTABLE.getAbsolutePath()));
//         }
//         if (!XCODEBUILD_EXECUTABLE.exists()) {
//             throw new IllegalStateException(String.format("Xcode is not detected on the current system (%s)",
//                     XCODEBUILD_EXECUTABLE.getAbsolutePath()));
//         }
//         if (!WDA_PROJECT.exists()) {
//             throw new IllegalStateException(String.format("WDA project is expected to exist at %s",
//                     WDA_PROJECT.getAbsolutePath()));
//         }
//     }

//     private List<String> generateXcodebuildCmdline() {
//         final List<String> result = new ArrayList<>();
//         result.add(XCODEBUILD_EXECUTABLE.getAbsolutePath());
//         result.add("clean build test");
//         result.add(String.format("-project %s", WDA_PROJECT.getAbsolutePath()));
//         result.add(String.format("-scheme %s", WDA_SCHEME));
//         result.add(String.format("-destination id=%s", deviceId));
//         result.add(String.format("-configuration %s", CONFIGURATION));
//         result.add(String.format("IPHONEOS_DEPLOYMENT_TARGET=%s", platformVersion));
//         result.add(String.format("> %s 2>&1 &", XCODEBUILD_LOG.getAbsolutePath()));
//         return result;
//     }

//     private static List<String> generateKeychainUnlockCmdlines() throws Exception {
//         final List<String> result = new ArrayList<>();
//         result.add(String.format("/usr/bin/security -v list-keychains -s %s", KEYCHAIN.getAbsolutePath()));
//         result.add(String.format("/usr/bin/security -v unlock-keychain -p %s %s",
//                 KEYCHAIN_PASSWORD, KEYCHAIN.getAbsolutePath()));
//         result.add(String.format("/usr/bin/security set-keychain-settings -t 3600 %s", KEYCHAIN.getAbsolutePath()));
//         return result;
//     }

//     public synchronized void restart() throws Exception {
//         final String hostname = InetAddress.getLocalHost().getHostName();
//         log.info(String.format("Trying to (re)start WDA server on %s:%s...", hostname, PORT));
//         UnixProcessHelpers.killProcessesGracefully(IPROXY_EXECUTABLE.getName(),
//                 XCODEBUILD_EXECUTABLE.getName());

//         final File scriptFile = File.createTempFile("script", ".sh");
//         try {
//             final List<String> scriptContent = new ArrayList<>();
//             scriptContent.add("#!/bin/bash");
//             if (isRealDevice && isRunningInJenkinsNetwork()) {
//                 scriptContent.add(String.join("\n", generateKeychainUnlockCmdlines()));
//             }
//             if (isRealDevice) {
//                 scriptContent.add(String.join(" ", IPROXY_CMDLINE));
//             }
//             final String wdaBuildCmdline = String.join(" ", generateXcodebuildCmdline());
//             log.debug(String.format("Building WDA with command line:\n%s\n", wdaBuildCmdline));
//             scriptContent.add(wdaBuildCmdline);
//             try (Writer output = new BufferedWriter(new FileWriter(scriptFile))) {
//                 output.write(String.join("\n", scriptContent));
//             }
//             log.info(String.format("Waiting for WDA to be (re)started on %s:%s...", hostname, PORT));
//             final Timedelta started = Timedelta.now();
//             new ProcessBuilder("/bin/chmod", "u+x", scriptFile.getCanonicalPath())
//                     .redirectErrorStream(true).start().waitFor(5, TimeUnit.SECONDS);
//             final ProcessBuilder pb = new ProcessBuilder("/bin/bash", scriptFile.getCanonicalPath());
//             final Map<String, String> env = pb.environment();
//             // This is needed for Jenkins
//             env.put("BUILD_ID", "dontKillMe");
//             pb.redirectErrorStream(true).start().waitFor(RESTART_TIMEOUT.asMillis(), TimeUnit.MILLISECONDS);
//             if (!waitUntilIsRunning(RESTART_TIMEOUT)) {
//                 throw new IllegalStateException(
//                         String.format("WDA server has failed to start after %s timeout on server '%s'.\n"
//                                         + "Please make sure that iDevice is properly connected and you can build "
//                                         + "WDA manually from XCode.\n"
//                                         + "Xcodebuild logs:\n\n%s\n\n\niproxy logs:\n\n%s\n\n\n",
//                                 RESTART_TIMEOUT, hostname,
//                                 getLog(XCODEBUILD_LOG).orElse("EMPTY"), getLog(IPROXY_LOG).orElse("EMPTY"))
//                 );
//             }

//             log.info(String.format("WDA server has been successfully (re)started after %s " +
//                     "and now is listening on %s:%s", Timedelta.now().diff(started).toString(), hostname, PORT));
//         } finally {
//             scriptFile.delete();
//         }
//     }

//     public boolean isRunning() throws Exception {
//         final boolean result = waitUntilIsRunning(Timedelta.ofSeconds(3));
//         if (isRealDevice) {
//             return result && UnixProcessHelpers.isProcessRunning(IPROXY_EXECUTABLE.getName());
//         }
//         return result;
//     }

//     public Optional<String> getLog(File logFile) {
//         if (logFile.exists()) {
//             try {
//                 return Optional.of(new String(Files.readAllBytes(logFile.toPath()), Charset.forName("UTF-8")));
//             } catch (IOException e) {
//                 e.printStackTrace();
//             }
//         }
//         return Optional.empty();
//     }

//     public void resetLogs() {
//         for (File logFile : new File[]{XCODEBUILD_LOG, IPROXY_LOG}) {
//             if (logFile.exists()) {
//                 try {
//                     final PrintWriter writer = new PrintWriter(logFile);
//                     writer.print("");
//                     writer.close();
//                 } catch (FileNotFoundException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//     }
// }