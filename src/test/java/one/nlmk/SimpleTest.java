package one.nlmk;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class SimpleTest {

    @BeforeAll
    static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
    }

    @Test
    void testPracticeFormForCorrectInput() {
        String  firstName = "Molly",
                lastName = "Millions",
                email = "molly@tibacity.com",
                mobileNumber = "9052128506",
                address = "Main street",
                state = "NCR",
                city = "Delhi",
                birthYear = "1970",
                birthMonth = "January",
                birthDay = "15";


        open("/automation-practice-form");
        zoom(0.75);     // Уменьшаем масштаб, так как плашка внизу страницы закрывает кнопку отправки формы

        // Заполняем форму
        $("[id=firstName]").setValue(firstName);
        $("[id=lastName]").setValue(lastName);
        $("[id=userEmail]").setValue(email);
        $("[id=genterWrapper]").$(byText("Female")).click();
        $("[id=userNumber]").setValue(mobileNumber);
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__year-select").selectOption(birthYear);
        $(".react-datepicker__month-select").selectOption(birthMonth);
        $(".react-datepicker__month").$(byText(birthDay)).click();
        $("[id=subjectsInput]").setValue("Economics").pressEnter();
        $(".custom-checkbox").$(byText("Sports")).click();
        $("[id=uploadPicture]").uploadFile(new File("src/test/resources/images.jpg"));
        $("[id=currentAddress]").setValue(address);
        $("[id=react-select-3-input]").setValue(state).pressEnter();
        $("[id=react-select-4-input]").setValue(city).pressEnter();

        $("#submit").click(); // Подтвердаем ввод данных

        // Проверяем результаты. Необходимо узнать как проверить соответствие заголовков и содержания строк в таблице
        $("[class=table-responsive]").shouldHave(
                text(firstName + " " + lastName),
                text(email),
                text(mobileNumber),
                text("Female"),
                text(birthDay + " " + birthMonth + "," + birthYear),
                text("Economics"),
                text("Sports"),
                text("images.jpg"),
                text(address),
                text(state + " " + city));
    }
}
