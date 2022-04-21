package one.nlmk.pages;

import com.codeborne.selenide.SelenideElement;
import one.nlmk.pages.components.CalendarComponent;

import java.io.File;
import java.util.Calendar;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {

    CalendarComponent calendar = new CalendarComponent();
    // locators
    SelenideElement firstNameLocator = $("#firstName");

    // actions
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
        $("#lastName").setValue(value);
        return this;
    }

    public RegistrationFormPage setEmail(String value) {
        $("#userEmail").setValue(value);
        return this;
    }

    public RegistrationFormPage setGender(String value) {
        $("#genterWrapper").$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setPhoneNumber(String value) {
        $("#userNumber").setValue(value);
        return this;
    }

    public RegistrationFormPage setBirthday(String year, String month, String day) {
        $("#dateOfBirthInput").click();
        calendar.setDate(year, month, day);
        return this;
    }

    public RegistrationFormPage setSubject(String value) {
        $("#subjectsInput").setValue(value).pressEnter();
        return this;
    }

    public RegistrationFormPage clearSubject() {
        $(".subjects-auto-complete__clear-indicator").$(".css-19bqh2r").click(); // Передумали, очистили форму
        return this;
    }

    public RegistrationFormPage setHobbie(String value) {
        $(".custom-checkbox").$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage setAddress(String value) {
        $("#currentAddress").setValue(value);
        return this;
    }

    public RegistrationFormPage seleclState(String value) {
        $("#stateCity-wrapper").$(byText("Select State")).click();
        $("#stateCity-wrapper").$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage seleclCity(String value) {
        $("#stateCity-wrapper").$(byText("Select City")).click();
        $("#stateCity-wrapper").$(byText(value)).click();
        return this;
    }

    public RegistrationFormPage uploadFile(String value) {
        $("#uploadPicture").uploadFile(new File("src/test/resources/" + value));
        return this;
    }

    public void submit() {
        $("#submit").click();
    }

    public RegistrationFormPage checkResults(String key, String value, boolean isPositiveCheck) {
        if (isPositiveCheck) {
            $(".table-responsive").$(byText(key)).parent()
                    .shouldHave(text(value));
        }
        else {
            $(".table-responsive").$(byText(key)).parent()
                    .shouldNotHave(text(value));
        }
        return this;
    }

}
