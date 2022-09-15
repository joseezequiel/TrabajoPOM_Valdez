package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.BaseClass;

public class MyAccountPage extends BaseClass {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    By locatorLblWelcomeToYourAccount = By.xpath("//p[contains(text(),'Welcome to your account. Here you can manage all o')]");
    By locatorBtnGoToWomanCategoryPage = By.xpath("//header/div[3]/div[1]/div[1]/div[6]/ul[1]/li[1]/a[1]");



    public String obtenerMensajeLogInSuccessfully(){
        return obtenerTexto(esperaExplicita(locatorLblWelcomeToYourAccount));
    }

    public void irWomanCategoryPage(){
        click(esperaExplicita(locatorBtnGoToWomanCategoryPage));
    }

}
