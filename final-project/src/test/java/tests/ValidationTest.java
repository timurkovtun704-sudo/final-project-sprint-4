package tests;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.MainPage;
import pageobject.OrderPage;
import java.util.Arrays;
import java.util.Collection;
@RunWith(Parameterized.class)
public class ValidationTest {
    private WebDriver driver;
    private OrderPage orderPage;
    private final String browser;
    private final String invalidField;
    private final By cookieButton = By.id("rcc-confirm-button");
    public ValidationTest(String invalidField, String browser) {
        this.invalidField = invalidField;
        this.browser = browser;
    }
    @Parameterized.Parameters(name = "Проверка обязательного поля: {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"name", "chrome"},
                {"lastName", "chrome"},
                {"address", "chrome"},
                {"metro", "chrome"},
                {"phone", "chrome"},
                {"name", "firefox"},
                {"lastName", "firefox"},
                {"address", "firefox"},
                {"metro", "firefox"},
                {"phone", "firefox"}
        });
    }
    @Before
    public void setUp() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        }
        else {
            driver = new FirefoxDriver();
        }
        MainPage mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        mainPage.open();
        if (!driver.findElements(cookieButton).isEmpty()) {
            driver.findElement(cookieButton).click();
        }
        mainPage.clickOrderButton(1);
    }
    @Test
    public void requiredFieldShouldPreventMovingToNextStep() {
        if (!"name".equals(invalidField)) {
            orderPage.enterFirstName("Иван");
        }
        if (!"lastName".equals(invalidField)) {
            orderPage.enterLastName("Иванов");
        }
        if (!"address".equals(invalidField)) {
            orderPage.enterAddress("Москва, улица Ленина, 1");
        }
        if (!"metro".equals(invalidField)) {
            orderPage.selectMetroForValidation("Сокольники");
        }
        if (!"phone".equals(invalidField)) {
            orderPage.enterPhone("+79990000001");
        }
        orderPage.clickNextForValidation();
        boolean secondPageOpened = !driver.findElements(By.xpath("//input[@placeholder='* Когда привезти самокат']")).isEmpty();
        Assert.assertFalse("Открылся второй экран с незаполненным полем: " + invalidField, secondPageOpened);
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}

