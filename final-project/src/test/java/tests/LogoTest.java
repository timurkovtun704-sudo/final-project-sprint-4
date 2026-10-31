package tests;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.MainPage;
import org.junit.After;

@RunWith(Parameterized.class)
public class LogoTest {
    private final String logo;
    private final String browser;
    private WebDriver driver;
    private MainPage mainPage;
    @Parameterized.Parameters
    public static Object[][] getIcon()
    {
        return new Object[][]{
                {"yandex", "chrome"},
                {"scooter", "chrome"},
                {"yandex", "firefox"},
                {"scooter", "firefox"},
        };
    }
    @Before
    public void setUp()
    {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        mainPage = new MainPage(driver);
        mainPage.open();
    }
    @Test
    public void shouldOpenIconPage()
    {
        if(logo.equals("yandex")){
            mainPage.clickOrderButton(0);
            mainPage.isYandexOpenedAfterYandexIconClick();
        }
        else
        {
            mainPage.isScooterOpenedAfterScooterIconClick();
        }
    }
    @After
    public void tearDown()
    {
        driver.quit();
    }

    public LogoTest(String logo, String browser) {
        this.logo = logo;
        this.browser = browser;
    }

}
