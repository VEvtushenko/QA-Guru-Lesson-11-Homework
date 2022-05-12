package demo.qa.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import demo.qa.data.TestData;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static demo.qa.data.TestData.cities;
import static demo.qa.data.TestData.states;

@Tag("RegistrationFormTest")
public class RegistrationFormTest extends TestBase {

    @Test
    @Owner("Vladimir Evtushenko")
    @Severity(SeverityLevel.NORMAL)
    @Feature("DemoQA")
    @Link(value = "QA Guru, Lesson 11, Homework", url = "https://github.com/VEvtushenko/QA-Guru-Lesson-11-Homework")
    @DisplayName("Проверка заполнения регистрационной формы обучающегося")
    void testPracticeFormForCorrectInput() {

        SelenideLogger.addListener("allure", new AllureSelenide());

//        System.out.println(urlTestedSite);

        registrationFormPage.openPage()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .setGender(gender)
                .setPhoneNumber(mobileNumber)
                .setBirthday(birthYear, birthMonth, birthDay)
                .setSubject(subjectDrop)
                .clearSubject()
                .setSubject(subjectTrue.substring(2, 4))
                .setHobbie(hobbie)
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
                .checkResults("Gender", gender, true)
                .checkResults("Date of Birth", expectedDateOfBirth, true)
                .checkResults("Subjects", subjectDrop, false)
                .checkResults("Subjects", subjectTrue, true)
                .checkResults("Hobbies", hobbie, true)
                .checkResults("Picture", imgStudent.getName(), true)
                .checkResults("Address", address, true)
                .checkResults("State and City", expectedStateAndCity, true)
                .closeResultTable();

        TestBase.getScreenAndPage(" итоговой страницы");
    }
}
