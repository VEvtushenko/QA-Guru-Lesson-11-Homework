package demo.qa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("RegistrationFormTest")
public class RegistrationFormTest extends TestBase {

    @Test
    @Owner("Vladimir Evtushenko")
    @Severity(SeverityLevel.NORMAL)
    @Feature("DemoQA")
    @Link(value = "QA Guru, Lesson 10, Homework", url = "https://github.com/VEvtushenko/QA-Guru-Lesson-10-Homework")
    @DisplayName("Проверка заполнения регистрационной формы обучающегося")
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

        TestBase.getScreenAndPage(" после ввода данных");

        registrationFormPage.submit();

        TestBase.getScreenAndPage(" после подтверждения ввода данных");

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

        TestBase.getScreenAndPage(" итоговой страницы");
    }
}
