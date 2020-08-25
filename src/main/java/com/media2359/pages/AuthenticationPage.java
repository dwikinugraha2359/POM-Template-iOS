package com.media2359.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
/**
 * AuthenticationPage
 */
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class AuthenticationPage extends BasePage {
    // *********Variable*********

    // *********Constructor*********
    public AuthenticationPage(IOSDriver<IOSElement> driver) {
        super(driver);
    }

    // *********Web Elements*********
    // Login
    @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeImage[@name='login_logo']")
    private IOSElement logoLoginEl;
    @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextField")
    private IOSElement emailFieldLoginEl;
    @CacheLookup
    @iOSFindBy(xpath =  "//XCUIElementTypeButton[@name='Next']")
    private IOSElement nextBtnLoginEl;
    @iOSFindBy(xpath = "//XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeSecureTextField")
    private IOSElement passFieldLoginEl;
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Login']")
    private IOSElement loginBtnLoginEl;
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Back to login']")
    private IOSElement backToLoginBtnLoginEl;

    @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Forgot password?']")
    private IOSElement forgotPassBtnLoginEl;
    @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Disclaimer']")
    private IOSElement disclaimerBtnLoginEl;

    // Forgot Password
    @CacheLookup
    @iOSFindBy(id = "Please contact ITD Helpdesk (1800-483-4357) or email ITDHELP@NHG.COM.SG to change your NHG ADID Password. Thank you.")
    private IOSElement messageFPEl;
    @CacheLookup
    @iOSFindBy(id = "Email")
    private IOSElement emailBtnFPEl;
    @CacheLookup
    @iOSFindBy(id = "Call")
    private IOSElement callBtnFPEl;
    @CacheLookup
    @iOSFindBy(id = "Dismiss")
    private IOSElement dismissBtnFPEl;
    @CacheLookup
    @iOSFindBy(id = "com.samsung.android.dialer:id/digits")
    private IOSElement callDigitsFPEl;
    @CacheLookup
    @iOSFindBy(id = "com.google.android.gm:id/to")
    private IOSElement emailToFPEl;

    // Disclaimer
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Disclaimer']/XCUIElementTypeStaticText")
    private IOSElement titleDisclaimerScreenEl;
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Disclaimer']//XCUIElementTypeButton[@name='Close']")
    private IOSElement closeDisclaimerScreenEl;
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeWebView/XCUIElementTypeWebView/XCUIElementTypeWebView/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther")
    private IOSElement statementDisclaimer;

    // Rotation
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Rotation']//XCUIElementTypeStaticText[@name='Rotation']")
    private IOSElement titleRotationEl;
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar[@name='Rotation']//XCUIElementTypeButton[@name='ico menu']")
    private IOSElement menuBtnRotationEl;
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable")
    private IOSElement listRotationEl;

    // alert dialogue
    @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeAlert")
    private IOSElement alertDialogue;
    @CacheLookup
    @iOSFindBy(id = "Don’t Allow")
    private IOSElement dontAllowBtn;
    @CacheLookup
    @iOSFindBy(id = "Allow")
    private IOSElement allowBtn;

    // *********Page Methods*********
    public AuthenticationPage validateLoginPage() {
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Check logo is display
        assertVisibility(logoLoginEl);
        // Check logo is displat
        assertVisibility(emailFieldLoginEl);
        // Check logo is displat
        assertVisibility(nextBtnLoginEl);
        // Check logo is displat
        assertVisibility(forgotPassBtnLoginEl);
        // Check logo is displat
        assertVisibility(disclaimerBtnLoginEl);
        return this;
    }

    public AuthenticationPage login(String email, String password) throws InterruptedException {
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // type email
        clearText(emailFieldLoginEl);
        writeText(emailFieldLoginEl, email);
        Thread.sleep(500);
        // click on next button
        click(nextBtnLoginEl);
        Thread.sleep(500);
        // type password
        clearText(passFieldLoginEl);
        writeText(passFieldLoginEl, password);
        Thread.sleep(500);
        // hide keyboard
        // (MobileDriver)driver.sendKeys(Keys.RETURN);
        driver.hideKeyboard();
        Thread.sleep(500);
        // click on login button
        click(loginBtnLoginEl);

        // wait for 1s
        Thread.sleep(10000);
        return this;
    }

    public AuthenticationPage chooseNotificationAlert(String choose) throws InterruptedException {
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // wait visibility
        // waitVisibility(alertDialogue);
        Thread.sleep(3000);
        try {
            driver.switchTo().alert().accept();
        } catch (NoAlertPresentException e) {
            Reporter.log("Alert is not present" + e.getMessage());
        }
        Thread.sleep(1000);
        // if (choose.equals("allow")) {
        // // click on button
        // click(allowBtn);
        // } else {
        // // click on button
        // click(dontAllowBtn);
        // }
        return this;
    }

    public AuthenticationPage validateRotationScreen(String title) throws InterruptedException {
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // check visibility
        assertEquals(titleRotationEl, title);
        // check visibility
        assertVisibility(menuBtnRotationEl);
        return this;
    }

    public AuthenticationPage openFPDialog() throws InterruptedException {

        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // click on forgot password button
        click(forgotPassBtnLoginEl);
        return this;
    }

    public AuthenticationPage closeFPDialog() throws InterruptedException {
        // wait on 1000 ms
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Thread.sleep(1000);
        // click on forgot password button
        click(dismissBtnFPEl);
        return this;
    }

    public AuthenticationPage verifyFPDialog(String message) throws InterruptedException {
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Thread.sleep(1000);
        // check visibility
        assertEquals(messageFPEl, message);
        // check visibility
        assertVisibility(emailBtnFPEl);
        // check visibility
        assertVisibility(callBtnFPEl);
        // check visibility
        assertVisibility(dismissBtnFPEl);
        return this;
    }

    public AuthenticationPage clickOnCallBtn() throws InterruptedException {
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // wait on 1000 ms
        // Thread.sleep(1000);
        // click on forgot password button
        click(callBtnFPEl);
        return this;
    }

    public AuthenticationPage verifyCallPage(String callText) throws InterruptedException {
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Thread.sleep(1000);
        // check visibility
        assertEquals(callDigitsFPEl, callText);
        // press back button
        pressBackButton();
        // press back button
        pressBackButton();
        return this;
    }

    public AuthenticationPage clickOnEmailBtn() throws InterruptedException {
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // wait on 1000 ms
        // Thread.sleep(1000);
        // click on forgot password button
        click(emailBtnFPEl);
        return this;
    }

    public AuthenticationPage verifyEmailPage(String emailText) throws InterruptedException {
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Thread.sleep(1000);
        // check visibility
        assertEquals(emailToFPEl, emailText);
        // press back button
        pressBackButton();
        // press back button
        pressBackButton();
        return this;
    }

    public AuthenticationPage openDiscalimerPage() throws InterruptedException {
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // wait 1000 ms
        // Thread.sleep(1000);
        // Click on disclaimer button
        click(disclaimerBtnLoginEl);
        return this;
    }

    public AuthenticationPage closeDiscalimerPage() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // wait 1000 ms
        // Thread.sleep(1000);
        // Click on disclaimer button
        click(closeDisclaimerScreenEl);
        return this;
    }

    public AuthenticationPage verifyDisclaimerPage(String title) throws InterruptedException {
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // Thread.sleep(1000);
        // check visibility
        assertEquals(titleDisclaimerScreenEl, title);
        // check visibility
        assertVisibility(statementDisclaimer);
        return this;
    }

}