package ru.netology.selenide;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryOrder {

    DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
    LocalDate localDate = LocalDate.now();
    Date dataPst = Date.valueOf(localDate.plusDays(8));
    String dataVst = dateFormat.format(dataPst);

    @Test
    void shouldSubmitRequest() {
        open("http://localhost:9999");
        SelenideElement form = $("form");
        $("[data-test-id='city'] input").setValue("Пенза");
        $(".menu").waitUntil(exist,5000).click();
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dataVst);
        $("[data-test-id='name'] input").setValue("Дмитрий Ячменцев");
        $("[data-test-id='phone'] input").setValue("+79278913719");
        $("[data-test-id='agreement']").click();
        $$("button").find(exactText("Забронировать")).click();
        $(byText("Успешно!")).waitUntil(visible,15000);
    }
}