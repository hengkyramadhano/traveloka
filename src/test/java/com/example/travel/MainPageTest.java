package com.example.travel;

import org.junit.jupiter.api.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainPageTest {
    private WebDriver driver;
    private MainPage mainPage;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.traveloka.com/en-id");

        mainPage = new MainPage(driver);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    /*@Test
    public void search() {
        mainPage.searchButton.click();

        WebElement searchField = driver.findElement(By.cssSelector("[data-test='search-input']"));
        searchField.sendKeys("Selenium");

        WebElement submitButton = driver.findElement(By.cssSelector("button[data-test='full-search-button']"));
        submitButton.click();

        WebElement searchPageField = driver.findElement(By.cssSelector("input[data-test='search-input']"));
        assertEquals("Selenium", searchPageField.getAttribute("value"));
    }

    @Test
    public void toolsMenu() {
        mainPage.toolsMenu.click();

        WebElement menuPopup = driver.findElement(By.cssSelector("div[data-test='main-submenu']"));
        assertTrue(menuPopup.isDisplayed());
    }

    @Test
    public void navigationToAllTools() {
        mainPage.seeDeveloperToolsButton.click();
        mainPage.findYourToolsButton.click();

        WebElement productsList = driver.findElement(By.id("products-page"));
        assertTrue(productsList.isDisplayed());
        assertEquals("All Developer Tools and Products by JetBrains", driver.getTitle());
    }*/


    @Test
    public void clickCarRental() throws InterruptedException {
        WebElement carRentalMenu = driver.findElement(By.xpath("//div[text()='Car Rental']"));
        carRentalMenu.click();

        WebElement region = driver.findElement(By.cssSelector("input[placeholder='Enter city or region']"));
        region.sendKeys("Jakarta");

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement chooseRegion = driver.findElement(By.cssSelector("div[aria-label='Jakarta']"));
        chooseRegion.click();


        WebElement inputDate = driver.findElement(By.cssSelector("input[data-testid='rental-search-form-date-input-start']"));
        inputDate.click();

        DateFormat dayFormat = new SimpleDateFormat("d-M-yyyy");
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 2);
        String bookDate = dayFormat.format(cal.getTime());
        System.out.println("Tanggal: " + bookDate);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));


        WebElement monthName = driver.findElement(By.xpath("//div[@class='css-1dbjc4n r-cpa5s6']/div/div/div/div/h3"));
        String monthPick = monthName.getAttribute("textContent");
        System.out.println("Bulan dari Web "+monthPick);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        WebElement clickDate = driver.findElement(By.xpath("//div[@data-testid='date-cell-"+bookDate+"']"));
        clickDate.click();

        //Select Start time
        WebElement startTime = driver.findElement(By.cssSelector("input[data-testid='rental-search-form-time-input-start']"));
        startTime.click();

        //Search Car Button
        WebElement searchCar = driver.findElement(By.xpath("//*[@data-testid='rental-search-form-cta']"));
        searchCar.click();

        //Select Car
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[1]/div/div[5]/div[2]/div[2]/div/div/div[1]/div/div/div[3]/div[3]/div"))).click();

        //Select Provider
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='__next']/div/div[5]/div/div[4]/div/div[2]/div[2]/div[1]/div[2]/div"))).click();



        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement rentalPickupLocation = driver.findElement(By.xpath("//*[@id='RENTAL_PICKUP_LOCATION']/div/div/div[1]/h3"));

        mainPage.scrollToElementByWebElementLocator(rentalPickupLocation);

        WebElement rentalOffice = driver.findElement(By.xpath("//div[text()='Rental Office']"));
        rentalOffice.click();

        //*[@id="RENTAL_PICKUP_LOCATION"]/div/div/div[3]/div[2]/div/div/div[1]/div[1]/div/div[1]



    }
}
