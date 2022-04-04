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
        String FirstName = "Molly",
               LastName = "Millions",
               Email = "molly@tibacity.com",
               MobileNumber = "9052128506",
               Adress = "Main street",
               State = "NCR",
               City = "Delhi";


        open("/automation-practice-form");
        zoom(0.75);     // Уменьшаем масштаб, так как плашка внизу страницы закрывает кнопку отправки формы

        // Заполняем форму
        $("[id=firstName]").setValue(FirstName);
        $("[id=lastName]").setValue(LastName);
        $("[id=userEmail]").setValue(Email);
        $("[id=genterWrapper]").$(byText("Female")).click(); // неоптимальное решение, узнать как работать с selectRadio
        $("[id=userNumber]").setValue(MobileNumber);
        $("[id=dateOfBirthInput]").click();
        $(".react-datepicker__month-select").selectOption("January");
        $(".react-datepicker__year-select").selectOption("1970");
        $("[aria-label='Choose Thursday, January 15th, 1970']").click();  // Не оптимальное решение, нельзя сделать переменной
        $("[id=subjectsInput]").setValue("Economics").pressEnter();
        $(".custom-checkbox").$(byText("Sports")).click();
        $("[id=uploadPicture]").uploadFile(new File("src/test/resources/images.jpg"));
        $("[id=currentAddress]").setValue(Adress);
        $("[id=react-select-3-input]").setValue(State).pressEnter();
        $("[id=react-select-4-input]").setValue(City).pressEnter();

        $("#submit").click(); // Подтвердаем ввод данных

        // Проверяем результаты. Необходимо узнать как проверить соответствие заголовков и содержания строк в таблице
        $("[class=table-responsive]").shouldHave(
                text( FirstName + " " + LastName),
                text(Email),
                text(MobileNumber),
                text("Female"),
                text("15 January,1970"),
                text("Economics"),
                text("Sports"),
                text("images.jpg"),
                text(Adress),
                text( State + " " + City ));
    }
}
