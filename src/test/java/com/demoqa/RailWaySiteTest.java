package com.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RailWaySiteTest {

//    @BeforeAll
//    static void setUp() {
//        Configuration.holdBrowserOpen = true;
//        Configuration.browserSize = "1920x1080";

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

    @CsvSource ( value = {
            "Минск | Гродно",
            "Минск | Витебск"
               },
    delimiter = '|'
    )
    @ParameterizedTest (name = "Маршрут из {0} в {1}")
    void checkHaveTrail (String fromCity, String toCity) {
        Selenide.open("https://www.rw.by");
        $("#acFrom").setValue(fromCity);
        $(".ac_even.ac_over").click();
        $("#acTo").setValue(toCity);
        //$(".ac_even.ac_over").click();
        $x("/html/body/div[3]/div[3]/div[3]/div/div[3]/div[2]/div/form/div[2]/div[3]/a[3]").click();
        $("button[type='submit']").click();


        //$(".menu-items").shouldHave(text(footerData));
    }





}

