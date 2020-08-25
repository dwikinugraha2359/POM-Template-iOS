package com.media2359.pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.media2359.helper.HelperFunc;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeMethod;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BasePage {
    // *********Variable*********
    public IOSDriver<IOSElement> driver;
    public WebDriverWait wait;
    HelperFunc helperFunc = new HelperFunc();
    IOSElement resultListElement;

    // *********Web Elements*********
    // Navigation Bar
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeStaticText")
    protected IOSElement titleNavBarEl;

    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar//XCUIElementTypeStaticText[1]")
    protected IOSElement screenTitle;
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar//XCUIElementTypeButton[1]")
    public IOSElement backBtn;

    // ok
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Done']")
    public IOSElement okBtnDatePicker;
    // cancel
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeDatePicker")
    public IOSElement datePickerModal;

    // date
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeDatePicker//XCUIElementTypePicker//XCUIElementTypePickerWheel[2]")
    public IOSElement dateTxtDatePicker;
    // prev
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeDatePicker//XCUIElementTypePicker//XCUIElementTypePickerWheel[1]")
    public IOSElement monthTxtDatePicker;
    // year
    @iOSFindBy(xpath = "//XCUIElementTypeDatePicker//XCUIElementTypePicker//XCUIElementTypePickerWheel[3]")
    public IOSElement yearTxtDatePicker;
    // time
    // hour
    @iOSFindBy(xpath = "//XCUIElementTypeDatePicker//XCUIElementTypePicker//XCUIElementTypePickerWheel[1]")
    public IOSElement hourTxtTimePicker;
    // minute
    @iOSFindBy(xpath = "//XCUIElementTypeDatePicker//XCUIElementTypePicker//XCUIElementTypePickerWheel[2]")
    public IOSElement minuteTxtTimePicker;

    // Log Detail Screen
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar//XCUIElementTypeButton[2]")
    protected IOSElement editActionB;
    @iOSFindBy(xpath = "//XCUIElementTypeOther//XCUIElementTypeStaticText[1]")
    protected IOSElement labelAssigmentLD;
    @iOSFindBy(xpath = "//XCUIElementTypeOther//XCUIElementTypeStaticText[2]")
    protected IOSElement txtAssignmentLD;
    @iOSFindBy(xpath = "//XCUIElementTypeOther//XCUIElementTypeStaticText[3]")
    protected IOSElement fromDateLD;
    @iOSFindBy(xpath = "//XCUIElementTypeOther//XCUIElementTypeStaticText[4]")
    protected IOSElement fromTimeLD;
    @iOSFindBy(xpath = "//XCUIElementTypeOther//XCUIElementTypeStaticText[5]")
    protected IOSElement toDateLD;
    @iOSFindBy(xpath = "//XCUIElementTypeOther//XCUIElementTypeStaticText[6]")
    protected IOSElement toTimeLD;
    @iOSFindBy(xpath = "//XCUIElementTypeOther//XCUIElementTypeStaticText[7]")
    protected IOSElement labelDurationLD;
    @iOSFindBy(xpath = "//XCUIElementTypeOther//XCUIElementTypeStaticText[8]")
    protected IOSElement txtDurationLD;
    @iOSFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeButton[1]")
    protected IOSElement layoutCommentLD;
    @iOSFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeStaticText[10]")
    protected IOSElement txtCommentLD;
    @iOSFindBy(xpath = "(//XCUIElementTypeOther/XCUIElementTypeButton[2]")
    protected IOSElement layoutViolLD;
    @iOSFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeStaticText[12]")
    protected IOSElement txtVIOLD;

    // Edit Log Screen
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar//XCUIElementTypeButton[1]")
    protected IOSElement cancelLogActionBarEL;
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar//XCUIElementTypeButton[2]")
    protected IOSElement saveLogActionBarEL;
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name='Delete log']")
    protected IOSElement deleteLogBtnEL;

    // Comments Screen
    @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar/XCUIElementTypeButton[1]")
    protected IOSElement backBtnComment;
    @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeTable[1]")
    protected IOSElement commentsRV;
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeCell/XCUIElementTypeStaticText[1]")
    protected List<IOSElement> textCommentsRV;
    @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeTextField[1]")
    protected IOSElement inputCommentRV;
    @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeButton[1]")
    protected IOSElement sendBtnCommentRV;

    // Violation Screen
    @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTable")
    protected IOSElement listVio;
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeTable//XCUIElementTypeCell/XCUIElementTypeStaticText[1]")
    protected List<IOSElement> listTitleVio;
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeTable//XCUIElementTypeCell/XCUIElementTypeStaticText[2]")
    protected List<IOSElement> listDescVio;
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeTable//XCUIElementTypeCell/XCUIElementTypeButton[1]")
    protected List<IOSElement> listSelectCauseVio;
    @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name='Select cause']")
    protected IOSElement titleSelectCause;
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeTable//XCUIElementTypeCell/XCUIElementTypeStaticText[1]")
    protected List<IOSElement> listNameCause;
    @iOSFindBy(xpath = "//XCUIElementTypeCell//XCUIElementTypeStaticText[3]")
    protected List<IOSElement> listDefCause;
    // @CacheLookup
    @iOSFindBy(xpath = "//XCUIElementTypeTable//XCUIElementTypeCell/XCUIElementTypeButton[1]")
    protected List<IOSElement> listSelectBtnCause;

    // *********Constructor*********
    @BeforeMethod
    public void waitMethod() throws InterruptedException {
        Thread.sleep(500);
    }

    public BasePage(IOSDriver<IOSElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, 10);
    }

    // *********Page Methods*********

    // Allow Permission
    public void allowPermission(String btnLabel) throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<>();
        params.put("action", "accept");
        params.put("buttonLabel", btnLabel);
        js.executeScript("mobile: alert", params);
        // wait for 1s
        Thread.sleep(1000);
    }

    // Write Text
    public void writeText(IOSElement elementBy, String text) {
        // waitVisibility(elementBy);
        elementBy.sendKeys(text);
    }

    // Read Text
    public String readText(IOSElement elementBy) {
        // waitVisibility(elementBy);
        return elementBy.getText().replace("\n", " ").replace("\r", " ");
    }

    public String readText(MobileElement elementBy) {
        // waitVisibility(elementBy);
        return elementBy.getText().replace("\n", " ").replace("\r", " ");
    }

    public String readValue(MobileElement elementBy) {
        // waitVisibility(elementBy);
        return elementBy.getAttribute("value").replace("\n", " ").replace("\r", " ");
    }

    // Click Method
    public void click(IOSElement elementBy) {
        // waitVisibility(elementBy);
        elementBy.click();
    }

    // clear Text
    public void clearText(IOSElement elementBy) throws InterruptedException {
        Reporter.log(String.valueOf(getAttributeElement(elementBy, "value")) + "|"
                + String.valueOf(getAttributeElement(elementBy, "value").length()));
        // for (int i = 0; i < getAttributeElement(elementBy, "value").length(); i++) {
        // elementBy.sendKeys(Keys.DELETE);
        // Thread.sleep(500);
        // }
        elementBy.clear();
    }

    // get attribute of element
    public String getAttributeElement(IOSElement elementBy, String att) {
        return elementBy.getAttribute(att);
    }

    // get attribute of element
    public String getAttributeElement(MobileElement elementBy, String att) {
        return elementBy.getAttribute(att);
    }

    // Wait Wrapper Method
    public void waitVisibility(IOSElement elementBy) {
        wait.until(ExpectedConditions.visibilityOf(elementBy));
    }

    public void waitVisibility(MobileElement elementBy) {
        wait.until(ExpectedConditions.visibilityOf(elementBy));
    }

    // Assert Visibility
    public void assertVisibility(IOSElement elementBy) {
        waitVisibility(elementBy);
        Assert.assertTrue(isDisplay(elementBy));
    }

    // assert visibility for list element
    public void assertVisibility(List<IOSElement> listElementBy) {
        // waitVisibility(listElementBy);
        Assert.assertTrue(listElementBy.size() > 0);
    }

    // Assert empty
    public void assertEmpty(IOSElement elementBy) {
        waitVisibility(elementBy);
        Reporter.log(readText(elementBy));
        assertTrue(!readText(elementBy).equals(""));
    }

    public void assertEmpty(IOSElement elementBy, boolean isEmpty) {
        waitVisibility(elementBy);
        Reporter.log(readText(elementBy));
        assertTrue(readText(elementBy).equals("") == isEmpty);
    }

    public void assertEmptyValue(IOSElement elementBy, boolean isEmpty) {
        waitVisibility(elementBy);
        Reporter.log(readValue(elementBy));
        assertTrue(readValue(elementBy).equals("") == isEmpty);
    }

    // Assert Fail
    public void assertFail(String message) {
        Reporter.log(message);
        Assert.assertTrue(false);
    }

    // Assert true
    public void assertTrue(boolean condition) {
        Assert.assertTrue(condition);
    }

    // Assert text
    public void assertEquals(IOSElement elementBy, String expectedText) {
        waitVisibility(elementBy);
        Reporter.log(readText(elementBy) + " | " + expectedText);
        Assert.assertEquals(readText(elementBy), expectedText);
    }

    public void assertEqualsValue(IOSElement elementBy, String expectedText) {
        waitVisibility(elementBy);
        Reporter.log(readValue(elementBy) + " | " + expectedText);
        Assert.assertEquals(readValue(elementBy), expectedText);
    }

    public void assertEquals(IOSElement elementBy, String actText, String expectedText) {
        waitVisibility(elementBy);
        Reporter.log(actText + " | " + expectedText);
        Assert.assertEquals(actText, expectedText);
    }

    // Assert Equals
    public void assertContainText(String resultText, String expectedText, String expectedText2) {
        Assert.assertTrue(resultText.contains(expectedText) || resultText.contains(expectedText2));
        Reporter.log(resultText + " | " + expectedText);
    }

    // Assert Equals
    public void assertContainText(String resultText, String expectedText) {
        Reporter.log(resultText + " | " + expectedText);
        Assert.assertTrue(resultText.contains(expectedText));
    }

    // Assert Equals
    public void assertContainText(List<String> resultText, String expectedText) {
        Reporter.log(resultText + " | " + expectedText);
        Assert.assertTrue(resultText.contains(expectedText));
    }

    // check if element contain exp text or not
    public boolean isTextTrueContain(IOSElement elementBy, String expText) {
        return readText(elementBy).contains(expText);
    }

    // assert title screen
    public void checkTitleScreen(String expTitle) {
        // wait visibility
        waitVisibility(screenTitle);
        // verify title screen
        assertEquals(screenTitle, expTitle);
    }

    // tap on back button
    public void tapBackBtnAB(String expTitle) throws InterruptedException {
        // wait visibility
        waitVisibility(backBtn);
        // Tap on back button
        click(backBtn);
        // sleep 1000 ms
        Thread.sleep(1000);
        checkTitleScreen(expTitle);
    }

    // swipe the screen
    public void swipe(String navigate) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<>();
        params.put("direction", navigate);
        js.executeScript("mobile: swipe", params);
    }

    // press back button
    public void pressBackButton() {
        driver.navigate().back();
        // driver.getPageSource();
    }

    // press back button
    public void tapScreen(int x, int y) {
        TouchAction action = new TouchAction(driver);
        action.tap(PointOption.point(x, y)).perform();
    }

    public void minimizeMaximize() {
        try {
            ((IOSDriver) driver).runAppInBackground(Duration.ofSeconds(5));
            ((AppiumDriver) driver).activateApp("com.nhg.sandbox");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // IsDisplayed
    public Boolean isDisplay(IOSElement elementBy) {
        waitVisibility(elementBy);
        Reporter.log(String.valueOf(elementBy.isDisplayed()));
        return elementBy.isDisplayed();
    }

    // check text act equal exp
    public boolean isTextTrueEquals(IOSElement elementBy, String expText) {
        return readText(elementBy).equals(expText);
    }

    // click different element but same iteration
    public void clickOnSameIteration(List<IOSElement> listElementBy1, List<IOSElement> listElementBy2, String expText) {
        for (int i = 0; i < listElementBy1.size(); i++) {
            if (readText(listElementBy1.get(i)).contains(expText)) {
                listElementBy2.get(i).click();
                break;
            }
        }
    }

    // Get Ios Element on List
    public IOSElement getElementOnList(List<IOSElement> listElementBy, String expText) {

        for (IOSElement var : listElementBy) {
            Reporter.log(readText(var) + " | " + expText);
            if (readText(var).contains(expText)) {
                resultListElement = var;
                break;
                // Reporter.log("YEESS");
            }
        }
        return resultListElement;
    }

    // get child IOS Element on list
    public IOSElement getChildElementOnList(List<IOSElement> listElementBy, String expText, By elementBy) {

        for (IOSElement var : listElementBy) {
            if (readText(var.findElement(elementBy)).contains(expText)) {
                resultListElement = var;
                break;
            }
        }
        return resultListElement;
    }

    // back if title is not expected
    public void backWithIteration(String expTitle, int iterationBack) {
        Reporter.log(readText(screenTitle));
        if (!readText(screenTitle).contains(expTitle)) {
            for (int i = 0; i < iterationBack; i++) {
                driver.navigate().back();
            }
        }
    }

    // Vertical Swipe by Point
    public void verticalSwipeByPoint(Point startPoint, Point endPoint) {
        new TouchAction(driver).longPress(PointOption.point(startPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000))).moveTo(PointOption.point(endPoint))
                .release().perform();
    }

    // Vertical Swipe by percentages
    public void verticalSwipeByXPercentages(double startPercentage, double endPercentage, int anchorPercentage)
            throws InterruptedException {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage / 100);
        int startPoint = (int) (size.height * startPercentage / 100);
        int endPoint = (int) (size.height * endPercentage / 100);
        Reporter.log(String.valueOf(anchor + " : " + startPoint + " : " + endPoint));

        // PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        // Sequence dragNDrop = new Sequence(finger, 0);
        // dragNDrop.addAction(
        // finger.createPointerMove(Duration.ofSeconds(0),
        // PointerInput.Origin.viewport(), anchor, startPoint));
        // dragNDrop.addAction(new Pause(finger, Duration.ofMillis(2000)));
        // dragNDrop.addAction(finger.createPointerDown(PointerInput.MouseButton.MIDDLE.asArg()));
        // dragNDrop.addAction(
        // finger.createPointerMove(Duration.ofSeconds(1),
        // PointerInput.Origin.viewport(), anchor, endPoint));
        // dragNDrop.addAction(finger.createPointerUp(PointerInput.MouseButton.MIDDLE.asArg()));
        // driver.perform(Arrays.asList(dragNDrop));

        // new TouchAction(driver).longPress(PointOption.point(anchor, startPoint))
        // .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
        // .moveTo(PointOption.point(anchor, endPoint))
        // .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000))).release().perform();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<>();
        params.put("duration", 2.0);
        params.put("fromX", anchor);
        params.put("fromY", startPoint);
        params.put("toX", anchor);
        params.put("toY", endPoint);
        // params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: dragFromToForDuration", params);

    }

    // Vertical Swipe by percentages but anchor is point
    public void verticalSwipeByXAnchor(double startPercentage, double endPercentage, int anchor)
            throws InterruptedException {
        Dimension size = driver.manage().window().getSize();
        int startPoint = (int) (size.height * startPercentage / 100);
        int endPoint = (int) (size.height * endPercentage / 100);
        Reporter.log(String.valueOf(anchor + " : " + startPoint + " : " + endPoint));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<>();
        params.put("duration", 2.0);
        params.put("fromX", anchor);
        params.put("fromY", startPoint);
        params.put("toX", anchor);
        params.put("toY", endPoint);
        // params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: dragFromToForDuration", params);
    }

    // scroll datepicker
    public void scrollDP(IOSElement dayPicker, String value) throws InterruptedException {
        dayPicker.sendKeys(value);
        dayPicker.sendKeys(Keys.TAB);
        // JavascriptExecutor js = (JavascriptExecutor) driver;
        // Map<String, Object> params = new HashMap();
        // params.put("element", dayPicker);
        // params.put("direction", "down");
        // params.put("text", value);
        // params.put("toVisible", "true");
        // js.executeScript("mobile: scroll", params);
    }

    // get element that same iteration
    public IOSElement getElementSameIteration(String expectedText, List<IOSElement> listElement1,
            List<IOSElement> listElement2) {
        resultListElement = null;
        for (int i = 0; i < listElement1.size(); i++) {
            if (readText(listElement1.get(i)).contains(expectedText)) {
                resultListElement = listElement2.get(i);
                break;
            }
        }
        return resultListElement;
    }

    public List<IOSElement> getElementOnColomnDate(String expDate, List<IOSElement> listWeek, List<IOSElement> listItem)
            throws ParseException {

        String date = helperFunc.dateToString(helperFunc.stringToDate(expDate, "yyyy-MM-dd"), "d");
        IOSElement dateElement = getElementOnList(listWeek, date);

        int startXElement = dateElement.getLocation().x;
        int endXElement = startXElement + dateElement.getSize().width;
        Reporter.log(String.valueOf(startXElement));
        Reporter.log(String.valueOf(endXElement));

        List<IOSElement> resultListElement = new ArrayList<IOSElement>();
        for (IOSElement var : listItem) {
            int centerXVioItem = var.getCenter().getX();
            Reporter.log("{" + String.valueOf(centerXVioItem) + " : " +
            String.valueOf(startXElement) + " : "
            + String.valueOf(endXElement) + "}");
            if (startXElement < centerXVioItem && endXElement > centerXVioItem) {
                resultListElement.add(var);
            }
        }
        return resultListElement;
    }

    // Get iOS Element on List equal on expected text
    public IOSElement getElementOnListEquals(List<IOSElement> listElementBy, String expText) {
        resultListElement = null;
        for (int i = 0; i < listElementBy.size(); i++) {
            if (isTextTrueEquals(listElementBy.get(i), expText)) {
                resultListElement = listElementBy.get(i);
            } else {
                // Reporter.log(listElementBy.get(i).getTagName() + "| " + String.valueOf(i) +
                // "| false");
            }
        }
        return resultListElement;
    }

    // Choose Date Picker
    public void chooseDateOnDP(Date expDate, IOSElement monthTxtDatePicker2, IOSElement yearTxtDatePicker2,
            IOSElement dateTxtDatePicker2) throws ParseException, InterruptedException {

        // Date dateDP = new Date();
        // change expDate into yearMonth
        String date = helperFunc.dateToString(expDate, "d");
        String month = helperFunc.dateToString(expDate, "MMMMM");
        String year = helperFunc.dateToString(expDate, "yyyy");
        // YearMonth yearMonthExp = YearMonth.of(expDate.getYear(), expDate.getMonth());

        // set value dp
        Reporter.log(readText(dateTxtDatePicker2));
        scrollDP(dateTxtDatePicker2, date);
        Reporter.log(readText(dateTxtDatePicker2));
        Thread.sleep(500);
        Reporter.log(readText(monthTxtDatePicker2));
        scrollDP(monthTxtDatePicker2, month);
        Reporter.log(readText(monthTxtDatePicker2));
        Thread.sleep(500);
        Reporter.log(readText(yearTxtDatePicker2));
        scrollDP(yearTxtDatePicker2, year);
        Reporter.log(readText(yearTxtDatePicker2));
        Thread.sleep(500);

        // Save the choosen date
        click(okBtnDatePicker);

    }

    // select time picker
    public void selectHourOrMinutePicker(IOSElement elementHour, IOSElement elementMinute, String expTime)
            throws InterruptedException {

        List<String> listTime = splitText(expTime, ":");
        String hour = listTime.get(0);
        String time = listTime.get(1);

        // set value dp
        Reporter.log(readText(elementHour));
        scrollDP(elementHour, hour);
        Reporter.log(readText(elementHour));
        Thread.sleep(500);
        Reporter.log(readText(elementMinute));
        scrollDP(elementMinute, time);
        Reporter.log(readText(elementMinute));
        Thread.sleep(500);
        // Save the choosen date
        click(okBtnDatePicker);
    }

    public List<String> splitText(String textToSplit, String splitChar) {
        return Arrays.asList(textToSplit.split(splitChar));
    }

    // generate File
    public void generateFile(String originalPath, String copiedPath) throws IOException {

        File original = new File(originalPath);
        File copied = new File(copiedPath);
        FileUtils.copyFile(original, copied);
    }

    // generate Random number
    public String generateRandomNumber(int range, String digit) {
        Random rand = new Random();

        // Obtain a number between [0 - 49].
        int n = rand.nextInt(range);

        // Add 1 to the result to get a number from the required range
        // (i.e., [1 - 50]).
        n += 1;
        String formatted = String.format(digit, n);
        return formatted;
    }

    // to generate date
    public String generateDate() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    // to generate date with hour
    public String generateDateNHour() {
        DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy-HH");
        Date date = new Date();
        return dateFormat.format(date);
    }

    // to generate file with 2 row value
    public void generateFile2Row(String fileName, String rowOne, String rowTwo)
            throws UnsupportedEncodingException, FileNotFoundException {
        PrintWriter writer = new PrintWriter(fileName, "UTF-8");
        writer.println(rowOne);
        writer.println(rowTwo);
        writer.close();
    }

    // verify file downloaded on directory
    public boolean isFileDownloaded(String downloadPath) {
        File dir = new File(downloadPath);
        File[] dirContents = dir.listFiles();

        // verify the count of file on directory
        if (dirContents.length > 0) {
            return true;
        }

        return false;
    }

}