package demo.qa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import demo.qa.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Date;
import java.util.Locale;
import static java.lang.String.format;
import com.github.javafaker.Faker;
import static demo.qa.utils.RandomUtils.getRandomPhone;
import static demo.qa.utils.RandomUtils.getRandomMonth;



public class RegistrationFormTest {

    RegistrationFormPage registrationFormPage = new RegistrationFormPage();

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "https://demoqa.com";
    }

    @DisplayName("Проверка заполнения регистрационной формы обучающегося")
    @Test
    void testPracticeFormForCorrectInput() {

        SelenideLogger.addListener("allure", new AllureSelenide());

        Faker faker = new Faker(new Locale("EN", "IND"));
        Date birthDate = faker.date().birthday(18, 100);

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

        java.io.File imgStudent = new java.io.File("src/test/resources/Images/images.jpg");

        // Вводим данные
        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender[1])
                .setPhoneNumber(mobileNumber)
                .setBirthday(birthYear, birthMonth, birthDay)
                .setSubject(subjects[0])
                .clearSubject()
                .setSubject(subjects[2].substring(2, 4))
                .setHobbie(hobbie[0])
                .uploadFile(imgStudent)
                .setAddress(address)
                .seleclState(states[0])
                .seleclCity(cities[1])
                .submit();

        // Проверяем результаты.
        registrationFormPage.checkWindowWithResults()
                .checkResults("Student Name", expectedFullName, true)
                .checkResults("Student Email", email, true)
                .checkResults("Mobile", mobileNumber, true)
                .checkResults("Gender", gender[1], true)
                .checkResults("Date of Birth", expectedDateOfBirth, true)
                .checkResults("Subjects", subjects[0], false)
                .checkResults("Subjects", subjects[2], true)
                .checkResults("Hobbies", hobbie[0], true)
                .checkResults("Picture", imgStudent.getName(), true)
                .checkResults("Address", address, true)
                .checkResults("State and City", expectedStateAndCity, true);

    }
}
