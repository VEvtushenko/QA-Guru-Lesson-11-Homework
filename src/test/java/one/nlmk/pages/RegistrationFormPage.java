package one.nlmk.pages;

import com.codeborne.selenide.SelenideElement;
import one.nlmk.pages.components.CalendarComponent;
import java.io.File;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    CalendarComponent calendar = new CalendarComponent();

    SelenideElement firstNameLocator = $("#firstName"),
                    lastNameLocator = $("#lastName"),
                    emeilLocator = $("#userEmail"),
                    genderLocator = $("#genterWrapper"),
                    phoneNumberLocator = $("#userNumber"),
                    calendarMainLocator = $("#dateOfBirthInput"),
                    subjectLocator = $("#subjectsInput"),
                    checkboxHobbiesLocator = $(".custom-checkbox"),
                    adressLocator = $("#currentAddress"),
                    stateAndCityLocator = $("#stateCity-wrapper"),
                    imgUploadLocator = $("#uploadPicture"),
                    resultsTable = $(".table-responsive");

    public RegistrationFormPage openPage() {
        open("/automation-practice-form");
        zoom(0.75);     // Уменьшаем масштаб, так как плашка внизу страницы закрывает кнопку отправки формы
        return this;
    }

    public RegistrationFormPage setFirstName(String value) {
        firstNameLocator.setValue(value);
        return this;
    }

    public RegistrationFormPage setLastName(String value) {
        lastNameLocator.setValue(value);
        return this;
    }

    public RegistrationFormPage setEmail(String value) {
        emeilLocator.setValue(value);
        return this;
    }

    public RegistrationFormPage setGender(String value) {
        genderLocator.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setPhoneNumber(String value) {
        phoneNumberLocator.setValue(value);
        return this;
    }

    public RegistrationFormPage setBirthday(String year, String month, String day) {
        calendarMainLocator.click();
        calendar.setDate(year, month, day);
        return this;
    }

    public RegistrationFormPage setSubject(String value) {
        subjectLocator.setValue(value).pressEnter();
        return this;
    }

    public RegistrationFormPage clearSubject() {
        $(".subjects-auto-complete__clear-indicator").$(".css-19bqh2r").click(); // Передумали, очистили форму
        return this;
    }

    public RegistrationFormPage setHobbie(String value) {
        checkboxHobbiesLocator.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setAddress(String value) {
        adressLocator.setValue(value);
        return this;
    }

    public RegistrationFormPage seleclState(String value) {
        stateAndCityLocator.$(byText("Select State")).click();
        stateAndCityLocator.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage seleclCity(String value) {
        stateAndCityLocator.$(byText("Select City")).click();
        stateAndCityLocator.$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage uploadFile(String value) {
        imgUploadLocator.uploadFile(new File("src/test/resources/" + value));
        return this;
    }

    public void submit() {
        $("#submit").click();
    }

    public RegistrationFormPage checkResults(String key, String value, boolean isPositiveCheck) {
        if (isPositiveCheck) {
            resultsTable.$(byText(key)).parent()
                    .shouldHave(text(value));
        }
        else {
            resultsTable.$(byText(key)).parent()
                    .shouldNotHave(text(value));
        }
        return this;
    }
}
