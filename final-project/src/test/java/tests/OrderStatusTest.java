package tests;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.MainPage;
import pageobject.StatusPage;
import org.junit.After;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderStatusTest {
    private MainPage mainPage;
    private StatusPage statusPage;
    private WebDriver driver;
    private final String browser;

    @Parameterized.Parameters
    public static Object[][] getIcon()
    {
        return new Object[][]{
                {"chrome"},
                {"firefox"},
        };
    }
    @Before
    public void setUp()
    {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        }
        else {
            driver = new FirefoxDriver();
        }
        statusPage = new StatusPage(driver);
        mainPage = new MainPage(driver);
        mainPage.open();
    }
    @Test
    public void shouldShowMessageForNonExistOrderId()
    {
        mainPage.clickOrderStatusButton();
        statusPage.enterOrderNumber("99999999999999999999");
        statusPage.clickGo();
        assertTrue("Должно появиться сообщение о том, что заказ не найден", statusPage.isMissingOrderMessageDisplayed());
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }
    public OrderStatusTest(String browser)
    {
        this.browser = browser;
    }

}
