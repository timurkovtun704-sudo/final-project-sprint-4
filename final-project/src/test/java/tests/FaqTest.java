package tests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobject.MainPage;
import java.awt.*;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class FaqTest {
    private WebDriver driver;
    private MainPage mainPage;
    private final int questionIndex;
    private final String expectedAnswer;
    private final String browser;
    private final By cookieButton = By.id("rcc-confirm-button");
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "chrome"},
                {1, "Пока что у нас так: один заказ — один самокат.", "chrome"},
                {2, "Допустим, вы оформляете заказ на 8 мая.", "chrome"},
                {3, "Только начиная с завтрашнего дня.", "chrome"},
                {4, "Пока что нет! Но если что-то срочное", "chrome"},
                {5, "Самокат приезжает к вам с полной зарядкой.", "chrome"},
                {6, "Да, пока самокат не привезли.", "chrome"},
                {7, "Да, обязательно.", "chrome"},
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "firefox"},
                {1, "Пока что у нас так: один заказ — один самокат.", "firefox"},
                {2, "Допустим, вы оформляете заказ на 8 мая.", "firefox"},
                {3, "Только начиная с завтрашнего дня.", "firefox"},
                {4, "Пока что нет! Но если что-то срочное", "firefox"},
                {5, "Самокат приезжает к вам с полной зарядкой.", "firefox"},
                {6, "Да, пока самокат не привезли.", "firefox"},
                {7, "Да, обязательно.", "firefox"}
        };
    }
    @Before
    public void setUp() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
        mainPage = new MainPage(driver);
        mainPage.open();
        if (!driver.findElements(cookieButton).isEmpty()) {
            driver.findElement(cookieButton).click();
        }
    }
    @Test
    public void faqQuestionShouldOpenCorrectAnswer() {
        mainPage.clickFaqQuestion(questionIndex);
        assertTrue("Ответ должен отображаться", mainPage.isFaqAnswerVisible(questionIndex));
        String actualAnswer = mainPage.getFaqAnswer(questionIndex);
        assertTrue("Ответ должен содержать ожидаемый текст", actualAnswer.contains(expectedAnswer));
    }
    @After
    public void tearDown() {
            driver.quit();
    }
    public FaqTest( int questionIndex,String expectedAnswer, String browser)
    {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
        this.browser = browser;
    }
}
