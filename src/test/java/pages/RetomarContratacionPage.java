package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.ClaseBase;

public class RetomarContratacionPage extends ClaseBase {

    public RetomarContratacionPage(WebDriver driver) {
        super(driver);
    }

    //identificar localizadores
    By locatorBtnRetomarContratacion = By.xpath("//a[contains(text(),'retomar tu solicitud aquí.')]");

    //acciones del page
    public void irARetomarContratacion(){
        click(esperarPorElementoAClickear(locatorBtnRetomarContratacion));
    }
}
