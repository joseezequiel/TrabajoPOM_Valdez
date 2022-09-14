package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.BaseClass;

public class HomePage extends BaseClass {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //localizadores
    By locatorBtnGoToAuthenticationPage = By.xpath("//a[contains(text(),'Sign in')]");
    By locatorBtnContactUs = By.xpath("//header/div[2]/div[1]/div[1]/nav[1]/div[2]/a[1]");
    By locatorBtnSearch = By.xpath("//header/div[3]/div[1]/div[1]/div[2]/form[1]/button[1]");


    //acciones del page
    public void irIniciarSesion(){
        click(esperaExplicita(locatorBtnGoToAuthenticationPage));
    }

}
