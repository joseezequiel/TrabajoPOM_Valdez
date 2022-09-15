package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.BaseClass;

public class AuthenticationPage extends BaseClass {
    public AuthenticationPage(WebDriver driver) {
        super(driver);
    }

    //localizadores
    By locatorBtnCreateAnAccount = By.xpath("//body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[1]/div[1]/form[1]/div[1]/div[3]/button[1]/span[1]");
    By locatorBtnSignIn = By.xpath("//button[@id='SubmitLogin']");
    By locatorTxtEmail = By.xpath("//input[@id='email']");
    By locatorTxtPassword = By.xpath("//input[@id='passwd']");
    By locatorTxtAuthenticationEmailFailed= By.xpath("//li[contains(text(),'Authentication failed.')]");

    By locatorLblEmailCreateAccount = By.xpath("//input[@id='email_create']");
    By locatorLblInvalidEmail = By.xpath("//li[contains(text(),'Invalid email address.')]");





    public void iniciarSesion(String email, String pass){
        agregarTexto(esperaExplicita(locatorTxtEmail), email);
        agregarTexto(esperaExplicita(locatorTxtPassword), pass);

        click(esperaExplicita(locatorBtnSignIn));
    }

    public void crearCuenta(String email){
        agregarTexto(esperaExplicita(locatorLblEmailCreateAccount), email);

        click(esperaExplicita(locatorBtnCreateAnAccount));
    }

    public String obtenerMensajeInvalidEmail(){
        return obtenerTexto(esperaExplicita(locatorLblInvalidEmail));
    }

    public String obtenerMensajeAuthenticationEmailFailed(){
        return obtenerTexto(esperaExplicita(locatorTxtAuthenticationEmailFailed));
    }


    //public void irAWomanCategoryPage(String email){
    //  agregarTexto(esperaExplicita(locatorLblEmailCreateAccount), email);

    //  click(esperaExplicita(locatorBtnCreateAnAccount));
    //}


}
