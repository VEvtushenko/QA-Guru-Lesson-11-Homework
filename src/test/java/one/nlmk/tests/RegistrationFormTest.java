package one.nlmk.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import com.github.javafaker.Faker;
import static one.nlmk.utils.RandomString.getRandomPhone;
import static one.nlmk.utils.RandomString.getRandomMonth;


public class RegistrationFormTest {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void testPracticeFormForCorrectInput() {

        Faker faker = new Faker(new Locale("EN", "IND"));
        String  firstName = faker.name().firstName(),
                lastName = faker.name().lastName(),
                email = faker.internet().emailAddress(),
                mobileNumber =  getRandomPhone(),
                address = faker.address().fullAddress(),
                state = "NCR",
                city = "Delhi",
                birthDate = String.valueOf(faker.date().birthday(18, 100)),
                birthYear = birthDate.substring(birthDate.length() - 4),
                birthMonth = getRandomMonth(faker.date().birthday().getMonth()),
                birthDay = String.valueOf((faker.date().birthday().getDay())),
                imgFileName = "images.jpg";

        String  expectedFullName = format("%s %s", firstName, lastName),
                expectedDateOfBirth = format("%s %s,%s", birthDay,birthMonth, birthYear),
                expectedStateAndCity = format("%s %s", state, city);

        String[] hobbie = new String[] {"Sports", "Reading", "Music"};
        String[] gender = new String[] {"Male", "Female", "Other"};

        open("/automation-practice-form");
        zoom(0.75);     // Уменьшаем масштаб, так как плашка внизу страницы закрывает кнопку отправки формы

        // Заполняем форму

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("#genterWrapper").$(byText(gender[1])).click();
        $("#userNumber").setValue(mobileNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(birthYear);
        $(".react-datepicker__month-select").selectOption(birthMonth);
        $(".react-datepicker__month").$(byText(birthDay)).click();
        $("#subjectsInput").setValue("Economics").pressEnter();                  // Вводим предмет
        $(".subjects-auto-complete__clear-indicator").$(".css-19bqh2r").click(); // Передумали, очистили форму
        $("#subjectsInput").setValue("Comp").pressEnter();                       // Вводим новый предмет, проверяем как работает автодополнение
        $(".custom-checkbox").$(byText(hobbie[0])).click();
        $("#uploadPicture").uploadFile(new File("src/test/resources/" + imgFileName));
        $("#currentAddress").setValue(address);
        $("#stateCity-wrapper").$(byText("Select State")).click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#stateCity-wrapper").$(byText("Select City")).click();
        $("#stateCity-wrapper").$(byText(city)).click();
//        $("#react-select-3-input").setValue(state).pressEnter();
//        $("#react-select-4-input").setValue(city).pressEnter();

        $("#submit").click(); // Подтвердаем ввод данных

        // Проверяем результаты.
        $(".table-responsive").$(byText("Student Name")).parent()
                .shouldHave(text(expectedFullName));
        $(".table-responsive").$(byText("Student Email")).parent()
                .shouldHave(text(email));
        $(".table-responsive").$(byText("Mobile")).parent()
                .shouldHave(text(mobileNumber));
        $(".table-responsive").$(byText("Gender")).parent()
                .shouldHave(text(gender[1]));
        $(".table-responsive").$(byText("Date of Birth")).parent()
                .shouldHave(text(expectedDateOfBirth));
        $(".table-responsive").$(byText("Subjects")).parent()
                .shouldNotHave(text("Economics"));                                        // Проверяем, что строка 55 сработала и выбранный первым предмет удалилс
        $(".table-responsive").$(byText("Subjects")).parent()
                .shouldHave(text("Computer Science"));
        $(".table-responsive").$(byText("Hobbies")).parent()
                .shouldHave(text(hobbie[0]));
        $(".table-responsive").$(byText("Picture")).parent()
                .shouldHave(text(imgFileName));
        $(".table-responsive").$(byText("Address")).parent()
                .shouldHave(text(address));
        $(".table-responsive").$(byText("State and City")).parent()
                .shouldHave(text(expectedStateAndCity));
    }
}
