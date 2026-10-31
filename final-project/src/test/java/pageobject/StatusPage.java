package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class StatusPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By orderNumber = By.xpath("//input[@placeholder='Введите номер заказа']");
    // Поле ввода номера заказа
    private final By goButton = By.xpath("//button[normalize-space()='Go!']");
    // Кнопка перехода к статусу заказа
    private final By missingOrder = By.xpath("//div[contains(@class, 'Track_NotFound__')]");
    // Сообщение об отсутствии заказа

    public void enterOrderNumber(String number) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderNumber)).sendKeys(number);
    }
    public void clickGo() {
        wait.until(ExpectedConditions.elementToBeClickable(goButton)).click();
    }
    public boolean isMissingOrderMessageDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(missingOrder)).isDisplayed();
    }
    public StatusPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}

