package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import utilidades.BaseClass;

import java.util.NoSuchElementException;

public class MyAccountPage extends BaseClass {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    By locatorLblWelcomeToYourAccount = By.xpath("//p[contains(text(),'Welcome to your account. Here you can manage all o')]");
    By locatorBtnGoToWomanCategoryPage = By.xpath("//header/div[3]/div[1]/div[1]/div[6]/ul[1]/li[1]/a[1]");

    By locatorBtnMyWishlists = By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[1]/div[1]/div[2]/ul[1]/li[1]/a[1]");
    By locatorBtnViewProductsInMyWishList = By.xpath("//a[contains(text(),'View')]");


    public String obtenerMensajeLogInSuccessfully(){
        return obtenerTexto(esperaExplicita(locatorLblWelcomeToYourAccount));
    }

    public void irWomanCategoryPage(){
        click(esperaExplicita(locatorBtnGoToWomanCategoryPage));
    }

    public void irWishLists(){
        click(esperaExplicita(locatorBtnMyWishlists));
    }
    public boolean checkThatProductAppearsonWantedList(int idProduct){
        boolean productoEncontrado = false;
        click(esperaExplicita(locatorBtnViewProductsInMyWishList));

        esperarXSegundos(5000);

        By locatorProductInTheWishList = By.xpath("//div[@class='wlp_bought']/ul/li/div/div/div/a[@href='http://automationpractice.com/index.php?id_product="+ idProduct +"&controller=product']");

        if(driver.findElements(locatorProductInTheWishList).size() != 0){
            return productoEncontrado = true;
        } else return productoEncontrado;


    }

}
