package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private WebDriver driver;
    private final WebDriverWait wait;
    private final By pageHeader = By.xpath("//div[contains(@class, 'Home_Header')]");
    // Точный путь к верхней кнопке в хедере
    private final By topOrderButton = By.xpath("//div[contains(@class, 'Header_Nav__')]//button[text()='Заказать']");
    //Кнопка Заказать в самом верху страницы
    private final By bottomOrderButton = By.xpath("//div[contains(@class, 'Home_ThirdPart__')]//button[text()='Заказать']");
    //Кнопка Заказать в середине страницы
    private By orderStatusButton = By.xpath("//button[contains(@class, 'Header_Link__')]");
    //Кнопка Статус Заказа
    private By yandexIcon = By.xpath("//img[@alt='Yandex']");
    //Иконка Яндекса в верхнем левом углу экрана
    private By scooterIcon = By.xpath("//img[@alt='Scooter']");
    //Иконка Самоката в верхнем левом углу экрана
    private final By faqHeader = By.xpath("//div[contains(text(),'Вопросы о важном')]");
    //Заголовок Вопросы о важном

    public void clickOrderButton(int index) {
        if (index == 0) {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(topOrderButton));
            element.click();
        } else {
            WebElement element = driver.findElement(bottomOrderButton);
            ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        }
    }
    public void clickOrderStatusButton()
    {
        wait.until(ExpectedConditions.elementToBeClickable(orderStatusButton)).click();
    }
    public void clickYandexIcon()
    {
        wait.until(ExpectedConditions.elementToBeClickable(yandexIcon)).click();
    }
    public boolean isYandexOpenedAfterYandexIconClick() {
        clickYandexIcon();
        String originalWindow = driver.getWindowHandle();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        for (String windowHandle : driver.getWindowHandles()) {
            if (!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        return wait.until(ExpectedConditions.urlContains("dzen.ru"));
    }
    public void clickScooterIcon()
    {
        wait.until(ExpectedConditions.elementToBeClickable(scooterIcon)).click();
    }
    public boolean isScooterOpenedAfterScooterIconClick()
    {
        clickScooterIcon();
        return driver.findElement(faqHeader).isDisplayed();
    }
    public void open()
    {
        driver.get("https://qa-scooter.praktikum-services.ru/");
        wait.until(ExpectedConditions.visibilityOfElementLocated(faqHeader));
    }
    public void clickFaqQuestion(int index) {
        By question = By.id("accordion__heading-" + index);
        wait.until(ExpectedConditions.elementToBeClickable(question)).click();
    }
    public String getFaqAnswer(int index) {
        By answer = By.id("accordion__panel-" + index);
        return wait.until(ExpectedConditions.visibilityOfElementLocated(answer)).getText();
    }
    public boolean isFaqAnswerVisible(int index) {
        try {
            driver.findElement(By.id("accordion__panel-" + index));
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }
    public MainPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
