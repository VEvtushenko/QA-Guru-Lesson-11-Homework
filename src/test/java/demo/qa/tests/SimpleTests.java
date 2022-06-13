package demo.qa.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Tag("simple_tests")
public class SimpleTests {

    @ValueSource(strings = {"Yandex", "Google"})
    @ParameterizedTest
    void simpleYandexTest(String dataForSearch) {
        Selenide.open("https://ya.ru");
        $(".input").$("#text").setValue(dataForSearch).pressEnter();
        $(".main__center").shouldHave(Condition.text("Решения для бизнеса Всё о Google"));
    }

    @Test
    public void vacancyTest() {
        Configuration.holdBrowserOpen = true;
        open("https://ru-ccs.com/#vacancies");
        $("[data-hover-roll='Разработчик чат-ботов']").click();
        $("[data-hover-roll='Разработчик чат-ботов']").parent().sibling(1)
                .$(byText("Обязанности:")).sibling(0)
                .shouldHave(Condition.text("Взаимодействие с партнёрами, маркетологами, технической поддержкой, другими командами, на предмет выявления проблем, новых требований; Обобщение полученной информации в виде ТЗ, задач в системе контроля задач; Коммуникация в профильном чате. Построение Roadmap. Декомпозиция работ; Управление проектами, постановка и контроль выполнения задач."));
    }
}
