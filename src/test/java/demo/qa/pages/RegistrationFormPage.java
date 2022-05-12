package demo.qa.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import demo.qa.pages.components.CalendarComponent;

import java.io.File;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage  {

    CalendarComponent calendar = new CalendarComponent();

    SelenideElement firstNameLocator = $("#firstName"),
                    lastNameLocator = $("#lastName"),
                    emailLocator = $("#userEmail"),
                    genderLocator = $("#genterWrapper"),
                    phoneNumberLocator = $("#userNumber"),
                    calendarMainLocator = $("#dateOfBirthInput"),
                    subjectLocator = $("#subjectsInput"),
                    clearSubjectsButton = $(".subjects-auto-complete__clear-indicator").$(".css-19bqh2r"),
                    checkboxHobbiesLocator = $("#hobbiesWrapper"),
                    addressLocator = $("#currentAddress"),
                    stateAndCityLocator = $("#stateCity-wrapper"),
                    imgUploadLocator = $("#uploadPicture"),
                    windowWithResults = $(".modal-content");

    @Step("Открываем страницу")
    public RegistrationFormPage openPage() {
        open("automation-practice-form");
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        return this;
    }

    @Step("Вводим имя")
    public RegistrationFormPage setFirstName(String value) {
        firstNameLocator.setValue(value);
        return this;
    }

    @Step("Вводим фамилию")
    public RegistrationFormPage setLastName(String value) {
        lastNameLocator.setValue(value);
        return this;
    }

    @Step("Вводим электронную почту")
    public RegistrationFormPage setEmail(String value) {
        emailLocator.setValue(value);
        return this;
    }

    @Step("Выбираем пол")
    public RegistrationFormPage setGender(String value) {
        genderLocator.$(byText(value)).click();
        return this;
    }

    @Step("Вводим номер телефона")
    public RegistrationFormPage setPhoneNumber(String value) {
        phoneNumberLocator.setValue(value);
        return this;
    }

    @Step("Вводим дату рождения")
    public RegistrationFormPage setBirthday(String year, String month, String day) {
        calendarMainLocator.click();
        calendar.setDate(year, month, day);
        return this;
    }

    @Step("Выбираем предмет, который будем изучать")
    public RegistrationFormPage setSubject(String value) {
        subjectLocator.setValue(value).pressEnter();
        return this;
    }

    @Step("Очищаем выбранные предметы")
    public RegistrationFormPage clearSubject() {
        clearSubjectsButton.click();
        return this;
    }

    @Step("Выбираем хобби")
    public RegistrationFormPage setHobbie(String value) {
        checkboxHobbiesLocator.$(byText(value)).click();
        return this;
    }

    @Step("Вводим адрес")
    public RegistrationFormPage setAddress(String value) {
        addressLocator.setValue(value);
        return this;
    }

    @Step("Выбираем штат")
    public RegistrationFormPage seleclState(String value) {
        stateAndCityLocator.$(byText("Select State")).click();
        stateAndCityLocator.$(byText(value)).click();
        return this;
    }

    @Step("Выбираем город")
    public RegistrationFormPage seleclCity(String value) {
        stateAndCityLocator.$(byText("Select City")).click();
        stateAndCityLocator.$(byText(value)).click();
        return this;
    }

    @Step("Загружаем фото")
    public RegistrationFormPage uploadFile(File value) {
        imgUploadLocator.uploadFile(value);
        return this;
    }

    @Step("Подтверждаем ввод всех регистрационных данных")
    public void submit() {
        $("#submit").click();
    }

    @Step("Проверяем, что открылась таблица с введёнными данными")
    public RegistrationFormPage checkWindowWithResults() {
        windowWithResults.shouldHave(text("Thanks for submitting the form"));
        return this;
    }

    @Step("Проверяем правильность ввода: {key}")
    public RegistrationFormPage checkResults(String key, String value, boolean isPositiveCheck) {
        if (isPositiveCheck) {
            windowWithResults.$(byText(key)).parent()
                    .shouldHave(text(value));
        }
        else {
            windowWithResults.$(byText(key)).parent()
                    .shouldNotHave(text(value));
        }
        return this;
    }

    @Step("Закрываем таблицу с результатами и проверяем, что она закрылась")
    public void closeResultTable() {
        $("#closeLargeModal").click();
        windowWithResults.shouldNotBe(visible);
    }
}
