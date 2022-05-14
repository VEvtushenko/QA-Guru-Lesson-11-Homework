package demo.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import demo.qa.config.RemoteHubConfig;
import demo.qa.config.SystemProperties;
import demo.qa.data.TestData;
import demo.qa.helpers.Attach;
import demo.qa.pages.RegistrationFormPage;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static java.lang.String.format;

public class TestBase {

    static RemoteHubConfig remoteHubConfig = ConfigFactory.create(RemoteHubConfig.class);
    static String urlTestedSite = SystemProperties.testedSite;
    static String urlRemoteHub = SystemProperties.remoteHub;
    static String password = remoteHubConfig.password();
    static String login = remoteHubConfig.login();

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    java.io.File imgStudent = new java.io.File("src/test/resources/Images/images.jpg");

    String firstName = TestData.firstName,
           lastName = TestData.lastName,
           email = TestData.email,
           mobileNumber = TestData.mobileNumber,
           address = TestData.address,
           birthYear = TestData.birthDate[2],
           birthMonth = TestData.birthDate[1],
           birthDay = TestData.birthDate[0],
           hobbie = TestData.randomHobbie(),
           gender = TestData.randomGender(),
           subjectDrop = TestData.randomSubject(),
           subjectTrue = TestData.randomSubject(),
           expectedFullName = format("%s %s", TestData.firstName, TestData.lastName),
           expectedDateOfBirth = format("%s %s,%s", birthDay, birthMonth, birthYear),
           expectedStateAndCity = format("%s %s", TestData.states[0], TestData.cities[1]);

    public static void getScreenAndPage(String screenshotName) {
        Attach.attachScreenshot(screenshotName);
        Attach.pageSource();
    }

    @BeforeAll
     public static void setUp() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        Configuration.baseUrl = urlTestedSite;
        Configuration.remote = "https://" + login + ":" + password + "@" + urlRemoteHub;

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @Step("Получим видео теста и лог браузера")
    @AfterAll
    public static void getVideoAndLog() {
        Attach.addVideo();
        Attach.browserConsoleLogs();
        closeWebDriver();
    }
}
