package com.demoqa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.By;

import java.util.stream.Stream;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;


public class RailWaySiteTest {

    @ValueSource (strings = {
            "КОНТАКТЫ",
            "УСЛУГИ ПАССАЖИРАМ",
            "БИЛЕТЫ",
            "ГРУЗОВЫЕ ПЕРЕВОЗКИ",
            "ТУРИЗМ И ОТДЫХ",
            "КОРПОРАТИВНЫЙ",
            "ПРЕСС-ЦЕНТР"
    })
    @ParameterizedTest (name = "Наличие вкладки {0} в хедере")
    void checkHeader (String footerData) {
        Selenide.open("https://www.rw.by");
        $(".menu-items").shouldHave(text(footerData));
    }

    @CsvSource (value = {
            "Минск | Гродно",
            "Минск | Витебск",
            "Гродно | Брест"
    },
            delimiter = '|'
    )
    @ParameterizedTest (name = "Маршрут из {0} в {1}")
    void checkHaveTrail (String fromCity, String toCity) {
        Selenide.open("https://www.rw.by");
        $("#acFrom").setValue(fromCity);
        $(".ac_even.ac_over").click();
        $("#acTo").setValue(toCity);
        $(".ac_even.ac_over").click();
        $(linkText("на все дни")).click();
        $("input[type='submit']").click();
        $(".sch-title__title.h2").shouldHave(text(fromCity + " — " + toCity + ","));
    }
    static Stream<Arguments> checkHaveTrailWithStream () {
        return Stream.of (
                Arguments.of("Барановичи","Москва"),
                Arguments.of("Полоцк","Брест"),
                Arguments.of("Гродно","Гомель")
        );
    }
    @MethodSource ("checkHaveTrailWithStream")
    @ParameterizedTest (name = "Маршрут из {0} в {1} ")
    void checkHaveTrailWithStream (String fromCity, String toCity) {
        Selenide.open("https://www.rw.by");
        $("#acFrom").setValue(fromCity);
        $(".ac_even.ac_over").click();
        $("#acTo").setValue(toCity);
        $(".ac_even.ac_over").click();
        $(linkText("на все дни")).click();
        $("input[type='submit']").click();
        $(".sch-title__title.h2").shouldHave(text(fromCity + " — " + toCity + ","));
    }
}