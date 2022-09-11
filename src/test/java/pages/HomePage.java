package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.ClaseBase;

public class HomePage extends ClaseBase {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //localizadores
    By localizadorBtnHazteCliente = By.partialLinkText("Hazte clien");

    //acciones del page
    public void irAHazteCliente(){
        click(esperarPorElementoAClickear(localizadorBtnHazteCliente));
    }

}
