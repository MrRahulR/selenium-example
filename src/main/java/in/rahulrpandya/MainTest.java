package in.rahulrpandya;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class MainTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void testcase1() {
        driver.get("https://www.saucedemo.com/v1/");
        WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("user-name")));
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        WebElement btnLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        System.out.println("Enter username - standard_user");
        System.out.println("Enter password - secret_sauce");
        txtUsername.sendKeys("standard_user");
        txtPassword.sendKeys("secret_sauce");
        System.out.println("Click on Login button");
        btnLogin.click();

        System.out.println("Verify user logged in successfully");
        WebElement btnCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_container")));
        wait.until(ExpectedConditions.titleContains("Swag Labs"));
        System.out.println("Page Title - " + driver.getTitle());
        Assert.assertTrue(btnCart.isDisplayed());
    }

    @Test
    public void testcase2() {
        driver.get("https://www.saucedemo.com/v1/");
        WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("user-name")));
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        WebElement btnLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        System.out.println("Enter username - locked_out_user");
        System.out.println("Enter password - secret_sauce");
        txtUsername.sendKeys("locked_out_user");
        txtPassword.sendKeys("secret_sauce");
        System.out.println("Click on Login button");
        btnLogin.click();

        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3")));
        System.out.println("Error Message - " + error.getText());
        Assert.assertTrue(error.getText().contains("Sorry, this user has been locked out"));
    }

    @Test
    public void testcase3() {
        driver.get("https://www.saucedemo.com/v1/");
        WebElement txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("user-name")));
        WebElement txtPassword = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        WebElement btnLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        txtUsername.sendKeys("standard_user");
        txtPassword.sendKeys("secret_sauce");
        System.out.println("Enter username - standard_user");
        System.out.println("Enter password - secret_sauce");
        System.out.println("Click on Login button");
        btnLogin.click();

        System.out.println("Verify user logged in successfully");
        WebElement btnCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("shopping_cart_container")));
        wait.until(ExpectedConditions.titleContains("Swag Labs"));
        Assert.assertTrue(btnCart.isDisplayed());

        System.out.println("Click on Menu button");
        WebElement btnMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("bm-burger-button")));
        btnMenu.click();

        System.out.println("Click on Logout button");
        WebElement btnLogout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        btnLogout.click();

        System.out.println("Verify user logged out successfully");
        txtUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("user-name")));
        Assert.assertTrue(txtUsername.isDisplayed());
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
