package com.tenpines.starter.ui;

import com.tenpines.starter.web.Endpoints;
import io.ddavison.conductor.Browser;
import io.ddavison.conductor.Config;
import io.ddavison.conductor.Locomotive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@Config(browser = Browser.CHROME,
        url = "http://localhost:8697/")
public class UITest extends Locomotive {

    @Test
    public void estaElBotonParaAgregarUnMensaje() {
        String unNuevoMensaje = "Holis!";

        navigateTo(Endpoints.HOME);
        validateText(By.id("titulo"), "Mensajes");
        setText(By.name("mensaje"), unNuevoMensaje);
        driver.findElement(By.tagName("form")).submit();
        validateText(By.className("msj"), unNuevoMensaje);
    }
}
