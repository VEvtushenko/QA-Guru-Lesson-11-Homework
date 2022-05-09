package demo.qa.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import demo.qa.helpers.Attach;
import demo.qa.pages.RegistrationFormPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static demo.qa.utils.RandomUtils.getRandomPhone;
import static java.lang.String.format;

public class TestBase {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    Faker faker = new Faker(new Locale("EN", "IND"));

    java.io.File imgStudent = new java.io.File("src/test/resources/Images/images.jpg");

    SimpleDateFormat dateFormater = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
    String stringBirthDate = dateFormater.format(faker.date().birthday(18, 70));
    String[] birthDate = stringBirthDate.split(" ");
    String[] hobbie = new String[] {"Sports", "Reading", "Music"};
    String[] gender = new String[] {"Male", "Female", "Other"};
    String[] subjects = new String[] {"Economics", "Arts", "Computer Science", "Math"};
    String[] states = new String[] {"NCR", "Uttar Pradesh", "Haryana", "Rajastan"};
    String[] cities = new String[] {"Delhi", "Gurgaon", "Nodia",
                                    "Agra", "Lucknow", "Merrut",
                                    "Karnal", "Panipat",
                                    "Jaipur", "Jaiselmer"};

    String  firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            mobileNumber =  faker.number().digits(10),
            address = faker.address().fullAddress(),
            birthYear = birthDate[2],
            birthMonth = birthDate[1],
            birthDay = birthDate[0],
            expectedFullName = format("%s %s", firstName, lastName),
            expectedDateOfBirth = format("%s %s,%s", birthDay,birthMonth, birthYear),
            expectedStateAndCity = format("%s %s", states[0], cities[1]);

    public static void getScreenAndPage(String screenshotName) {
        Attach.attachScreenshot(screenshotName);
        Attach.pageSource();
    }

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }
    @AfterEach
    public void getVideoAndLog() {
        Attach.addVideo();
        Attach.browserConsoleLogs();
        closeWebDriver();
    }
}
