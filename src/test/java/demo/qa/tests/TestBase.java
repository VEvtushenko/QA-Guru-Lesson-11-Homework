package demo.qa.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import demo.qa.helpers.Attach;
import demo.qa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Date;
import java.util.Locale;

import static demo.qa.utils.RandomUtils.getRandomMonth;
import static demo.qa.utils.RandomUtils.getRandomPhone;
import static java.lang.String.format;

public class TestBase {

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVide", true);
        Configuration.browserCapabilities = capabilities;
    }

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    Faker faker = new Faker(new Locale("EN", "IND"));
    Date birthDate = faker.date().birthday(18, 100);
    java.io.File imgStudent = new java.io.File("src/test/resources/Images/images.jpg");
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
            mobileNumber =  getRandomPhone(),
            address = faker.address().fullAddress(),
            stringBirthDate = String.valueOf(birthDate),
            birthYear = stringBirthDate.substring(stringBirthDate.length() - 4),
            birthMonth = getRandomMonth(birthDate.getMonth()),
            birthDay = String.valueOf(birthDate.getDay()),
            expectedFullName = format("%s %s", firstName, lastName),
            expectedDateOfBirth = format("%s %s,%s", birthDay,birthMonth, birthYear),
            expectedStateAndCity = format("%s %s", states[0], cities[1]);

    public static void getAttachments(String screenshotName) {
        Attach.attachScreenshot(screenshotName);
        Attach.browserConsoleLogs();
        Attach.pageSource();
        Attach.addVideo();
    }
}
