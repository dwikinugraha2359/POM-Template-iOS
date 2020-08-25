package com.media2359.tests;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidKeyCode;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

import com.media2359.pages.AuthenticationPage;

/**
 * a_Authentication
 */
public class a_Authentication extends BaseTest {

    /*
     * Feature : Navigated to contact us page and assert that display contact
     * person, payment instruction, payment info Given : User already logged in to
     * Goldbell Portal as Admin When : User click on "Contact Us" menu Then : User
     * will be able to see "Payment" contact person information, "Customer Service"
     * contact person information, payment information, and payment instruction.
     */
    @Test(priority = 0)
    public void A_001_LaunchApp() {

        // *************PAGE INSTANTIATIONS*************
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);

        // *************PAGE METHODS********************
        try {
            authenticationPage.chooseNotificationAlert("allow");
            authenticationPage.validateLoginPage();
            System.out.println("Test completed successfully");
        } catch (Exception e) {
            // TODO: handle exception
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 6)
    @Parameters({ "email", "password", "titleRotation" })
    public void A_002_ValidLogin(String email, String password, String title) {

        // *************PAGE INSTANTIATIONS*************
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);

        // *************PAGE METHODS********************
        try {
            authenticationPage.login(email, password).chooseNotificationAlert("allow").validateRotationScreen(title);
        } catch (Exception e) {
            // TODO: handle exception
            Assert.fail(e.getMessage());
        }

    }

    @Test(priority = 2)
    @Parameters({ "forgotPassMessage" })
    public void A_003_ForgotPassword(String forgotPassMessage) {

        // *************PAGE INSTANTIATIONS*************
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);

        // *************PAGE METHODS********************
        try {
            authenticationPage.closeDiscalimerPage();
            authenticationPage.openFPDialog().verifyFPDialog(forgotPassMessage).closeFPDialog();
        } catch (Exception e) {
            // TODO: handle exception
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 3)
    @Parameters({ "emailNHG" })
    public void A_004_ForgotPasswordEmail(String emailNHG) {

        // *************PAGE INSTANTIATIONS*************
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);

        // *************PAGE METHODS********************
        try {
            authenticationPage.openFPDialog().clickOnEmailBtn().verifyEmailPage(emailNHG);
        } catch (Exception e) {
            // TODO: handle exception
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 4)
    @Parameters({ "callDigit" })
    public void A_005_ForgotPasswordCall(String callDigit) {

        // *************PAGE INSTANTIATIONS*************
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);

        // *************PAGE METHODS********************
        try {
            authenticationPage.openFPDialog().clickOnCallBtn().verifyCallPage(callDigit);
        } catch (Exception e) {
            // TODO: handle exception
            Assert.fail(e.getMessage());
        }
    }

    @Test(priority = 1)
    @Parameters({ "titleDisclaimer" })
    public void A_005_ViewDisclaimer(String titleDisclaimer) {

        // *************PAGE INSTANTIATIONS*************
        AuthenticationPage authenticationPage = new AuthenticationPage(driver);

        // *************PAGE METHODS********************
        try {
            authenticationPage.openDiscalimerPage().verifyDisclaimerPage(titleDisclaimer);
        } catch (Exception e) {
            // TODO: handle exception
            Assert.fail(e.getMessage());
        }
    }
}