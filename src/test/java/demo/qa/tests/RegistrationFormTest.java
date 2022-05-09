package demo.qa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import demo.qa.helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("RegistrationFormTest")
public class RegistrationFormTest extends TestBase {

    @DisplayName("Проверка заполнения регистрационной формы обучающегося")
    @Test
    void testPracticeFormForCorrectInput() {

        SelenideLogger.addListener("allure", new AllureSelenide());

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
                .seleclCity(cities[1]);

        TestBase.getAttachments(" после ввода данных");

        registrationFormPage.submit();

        TestBase.getAttachments(" после подтверждения ввода данных");

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
                .checkResults("State and City", expectedStateAndCity, true)
                .closeResultTable();

        TestBase.getAttachments(" итоговой страницы");
    }
}
