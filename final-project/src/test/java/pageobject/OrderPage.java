package pageobject;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Поля первой страницы формы
    private final By firstName = By.xpath("//input[@placeholder='* Имя']");
    //Поле Имя
    private final By lastName = By.xpath("//input[@placeholder='* Фамилия']");
    //Поле Фамилия
    private final By address = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    //Поле Адрес
    private final By metro = By.xpath("//input[@placeholder='* Станция метро']");
    //Поле СТанция метро
    private final By phone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    //Поле Телефон
    private final By nextButton = By.xpath("//button[contains(text(), 'Далее')]");
    //Кнопка Далее

    // Поля второй страницы формы
    private final By deliveryDate = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    //Поле Дата
    private final By rentDuration = By.cssSelector(".Dropdown-control");
    //Контейнер Выбор цвет
    private final By blackColor = By.id("black");
    //Кнопка цвета Чёрный
    private final By greyColor = By.id("grey");
    //Кнопка цвета Серый
    private final By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");
    //Поле Комментарий

    // Кнопки оформления заказа
    private final By orderButton = By.xpath("//div[contains(@class,'Order_Buttons__')]//button[contains(@class, 'Button_Button__') and contains(text(), 'Заказать')]");
    //Кнопка Заказать
    private final By confirmButton = By.xpath("//button[contains(text(), 'Да')]");
    //Кнопка Да
    private final By successMessage = By.xpath("//*[contains(text(),'Заказ оформлен')]");
    // Сообщение об успешном создании заказа

    public void enterFirstName(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstName)).sendKeys(value);
    }
    public void enterLastName(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(lastName)).sendKeys(value);
    }
    public void enterAddress(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(address)).sendKeys(value);
    }
    public void selectMetro(String value) {
        WebElement metroInput = wait.until(ExpectedConditions.elementToBeClickable(metro));
        metroInput.click();
        metroInput.sendKeys(value);
        By metroOption = By.xpath("//*[contains(@class, 'select-search__option')]//*[contains(text(), '" + value + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(metroOption)).click();
    }
    public void enterPhone(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(phone)).sendKeys(value);
    }
    public void clickNext() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(deliveryDate));
    }
    public void clickNextForValidation() {
        wait.until(ExpectedConditions.elementToBeClickable(nextButton)).click();
    }
    public void selectMetroForValidation(String value) {
        WebElement metroInput = wait.until(ExpectedConditions.elementToBeClickable(metro));
        metroInput.click();
        metroInput.sendKeys(value);
        By metroOption = By.xpath("//*[contains(@class, 'select-search__option')]//*[contains(text(), '" + value + "')]");
        wait.until(ExpectedConditions.elementToBeClickable(metroOption)).click();
    }
    public void enterDeliveryDate(String value) {
        WebElement dateInput = wait.until(ExpectedConditions.elementToBeClickable(deliveryDate));
        dateInput.click();
        dateInput.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        dateInput.sendKeys(value);
        dateInput.sendKeys(Keys.ENTER);
    }
    public void selectRentDuration(String value) {
        wait.until(ExpectedConditions.elementToBeClickable(rentDuration)).click();
        By option = By.xpath("//div[contains(@class, 'Dropdown-option')" + " and normalize-space()='" + value + "']");
        wait.until(ExpectedConditions.elementToBeClickable(option)).click();
    }
    public void selectBlackColor() {
        wait.until(ExpectedConditions.elementToBeClickable(blackColor)).click();
    }
    public void selectGreyColor() {
        wait.until(ExpectedConditions.elementToBeClickable(greyColor)).click();
    }
    public void enterComment(String value) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(comment)).sendKeys(value);
    }
    public void clickOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(orderButton)).click();
    }
    public void confirmOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(confirmButton)).click();
    }
    public boolean isOrderCreated() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).isDisplayed();
    }
    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }
}
