package demo.qa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.$;

@Tag("simple_tests")
public class SimpleTests {

    @ValueSource(strings = {"Yandex", "Google"})
    @ParameterizedTest
    void simpleYandexTest(String dataForSearch) {
        Selenide.open("https://ya.ru");
        $(".input").$("#text").setValue(dataForSearch).pressEnter();
        $(".main__center").shouldHave(Condition.text("Решения для бизнеса Всё о Google"));
    }
}
