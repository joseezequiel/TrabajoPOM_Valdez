package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.BaseClass;

public class RetomarContratacionPage extends BaseClass {

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
