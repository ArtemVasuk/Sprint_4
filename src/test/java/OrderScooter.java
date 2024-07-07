import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.yandex.praktikum.pageorder.MainPage;
import ru.yandex.praktikum.pageorder.OrderPage;
import ru.yandex.praktikum.pageorder.RentPage;

import java.time.LocalDate;

@RunWith(Parameterized.class)
public class OrderScooter {
    private final String url = "https://qa-scooter.praktikum-services.ru/";

    private String firstName;
    private String secondName;
    private String adressName;
    private int indexMetroStation;
    private String telephoneNumber;

    static LocalDate currentDate = LocalDate.now();

    private int indexRentPeriod;

    private String commentForCourier;

    public OrderScooter(String firstName, String secondName, String adressName, int indexMetroStation, String telephoneNumber, LocalDate currentDate, int indexRentPeriod, String commentForCourier) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.adressName = adressName;
        this.indexMetroStation = indexMetroStation;
        this.telephoneNumber = telephoneNumber;
        this.currentDate = currentDate;
        this.indexRentPeriod = indexRentPeriod;
        this.commentForCourier = commentForCourier;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{{"Тест", "Тест", "Гражданский проспект 24", 9, "79117975846", currentDate, 4, "Полный заряд"},
                {"Мука", "Муки", "Суздальский 40", 7, "89564876234", currentDate, 2, "Нужно вино"}
        };
    }

    @Test

// Заказ самоката через кнопку заказа в середине сайта

    public void orderBlackScooterThroughInMiddleButton() {

        // создаем драйвер для браузера
        WebDriver driver = new FirefoxDriver();
        //WebDriver driver = new ChromeDriver();
        driver.get(url);

        // создаем объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);

        // клик по кнопке куки
        objMainPage.clickCoockieButton();

        //клик по кнопке "Заказать" в середине сайта сайта
        objMainPage.clickMiddleButtonOrder();

        //создаем объект класса страницы заказа
        OrderPage objOrderPage = new OrderPage(driver);

        // ввод имени
        objOrderPage.sendFirstName(firstName);

        // ввод фамилии
        objOrderPage.sendSecondName(secondName);

        // ввод адреса доставки
        objOrderPage.adressDeliverly(adressName);

        // выбор станции метро
        objOrderPage.choiseMetro(indexMetroStation);

        // ввод номера телефона
        objOrderPage.sendNumberTelephone(telephoneNumber);

        // жмём по кнопке далее
        objOrderPage.clickButtonNext();

        // создаем объект класса страницы с выбором аренды

        RentPage objRentPage = new RentPage(driver);

        // выбор даты аренды
        objRentPage.dateDeliverly(String.valueOf(currentDate));

        // выбор срока на который делается заказ аренды
        objRentPage.choiseRentPeriod(indexRentPeriod);

        // выбор чек-бокса с цветом Самоката
        objRentPage.choiseColourBlackScooter();

        //ввод комментария для курьера
        objRentPage.sendCommentCourier(commentForCourier);

        // клик по кнопке заказать
        objRentPage.clickButtonOrder();

        // клик по кнопке да в модальном окне подтверждения
        objRentPage.clickButtonYes();

        //проверка отображения модального окна с номером заказа
        objRentPage.isDisplayModalOrder();

        //закрытие браузера
        driver.quit();
    }
    @Test
    public void orderScooterColourGreyTroughtButtonInHeader(){
        // создаем драйвер для браузера
        WebDriver driver = new FirefoxDriver();
        // WebDriver driver = new ChromeDriver();
        driver.get(url);

        // создаем объект класса главной страницы
        MainPage objMainPage = new MainPage(driver);

        // клик по кнопке куки
        objMainPage.clickCoockieButton();

        // жмём по кнопке "Заказать" в хедере сайта
        objMainPage.clickButtonHeaderButtonOrder();

        // создаем объект класса страницы заказа
        OrderPage objOrderPage= new OrderPage(driver);

        // ввод имени
        objOrderPage.sendFirstName(firstName);

        // ввод фамилии
        objOrderPage.sendSecondName(secondName);

        // ввод адреса доставки
        objOrderPage.adressDeliverly(adressName);

        // выбор станции метро
        objOrderPage.choiseMetro(indexMetroStation);

        // ввод номера телефона
        objOrderPage.sendNumberTelephone(telephoneNumber);

        // клик по кнопке далее
        objOrderPage.clickButtonNext();

        // создаем объект класса страницы с выбором аренды
        RentPage objRentPage= new RentPage(driver);

        // ввод даты аренды
        objRentPage.dateDeliverly(String.valueOf(currentDate));

        // ввод срока на который заказывается аренда
        objRentPage.choiseRentPeriod(indexRentPeriod);

        // выбор чек-бокса с цветом самоката
        objRentPage.choiseColourGreyScooter();

        // ввод комментария для курьера
        objRentPage.sendCommentCourier(commentForCourier);

        // жмём по кнопке заказа
        objRentPage.clickButtonOrder();

        // жмём по кнопке да в модальном окне
        objRentPage.clickButtonYes();

        // проверка отображения модального окна с номером заказа
        objRentPage.isDisplayModalOrder();

        // закрытие браузера
        driver.quit();
    }
}













