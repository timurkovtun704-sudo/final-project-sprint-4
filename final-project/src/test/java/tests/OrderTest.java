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
import pageobject.OrderPage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private MainPage mainPage;
    private OrderPage orderPage;
    private final int buttonIndex;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String metro;
    private final String phone;
    private final String color;
    private final String comment;
    private final String browser;
    private final By cookieButton = By.id("rcc-confirm-button");
    public OrderTest(int buttonIndex, String firstName, String lastName, String address, String metro, String phone, String color, String comment, String browser) {
        this.buttonIndex = buttonIndex;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.color = color;
        this.comment = comment;
        this.browser = browser;
    }
    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {0, "Иван", "Иванов", "Москва, улица Ленина, 1", "Сокольники", "+79990000001", "black", "Позвонить заранее", "chrome"},
                {1, "Анна", "Петрова", "Москва, улица Мира, 10", "Комсомольская", "+79990000002", "grey", "Домофон не работает", "chrome"},
                {0, "Иван", "Иванов", "Москва, улица Ленина, 1", "Сокольники", "+79990000001", "black", "Позвонить заранее", "firefox"},
                {1, "Анна", "Петрова", "Москва, улица Мира, 10", "Комсомольская", "+79990000002", "grey", "Домофон не работает", "firefox"}
        };
    }
    @Before
    public void setUp() {
        if (browser.equals("chrome")) {
            driver = new ChromeDriver();
        }
        else {
            driver = new FirefoxDriver();
        }
        mainPage = new MainPage(driver);
        orderPage = new OrderPage(driver);
        mainPage.open();
        if (!driver.findElements(cookieButton).isEmpty()) {
            driver.findElement(cookieButton).click();
            }
        }

    @Test
    public void shouldCreateOrderSuccessfully() {
        mainPage.clickOrderButton(buttonIndex);
        orderPage.enterFirstName(firstName);
        orderPage.enterLastName(lastName);
        orderPage.enterAddress(address);
        orderPage.selectMetro(metro);
        orderPage.enterPhone(phone);
        orderPage.clickNext();
        String date = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        orderPage.enterDeliveryDate(date);
        orderPage.selectRentDuration("двое суток");
        if ("black".equals(color)) {
            orderPage.selectBlackColor();
        }
        else {
            orderPage.selectGreyColor();
        }
        orderPage.enterComment(comment);
        orderPage.clickOrder();
        orderPage.confirmOrder();
        assertTrue("Должно появиться сообщение об успешном создании заказа", orderPage.isOrderCreated());
    }
    @After
    public void tearDown() {
            driver.quit();
    }
}

