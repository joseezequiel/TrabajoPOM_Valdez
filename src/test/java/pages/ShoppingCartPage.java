package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.BaseClass;

public class ShoppingCartPage extends BaseClass {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    //localizadores

    //el locator locatorBtnDeleteProduct funciona con 1 sólo producto agregado al carrito
    By locatorBtnDeleteProduct = By.xpath("//body[1]/div[1]/div[2]/div[1]/div[3]/div[1]/div[2]/table[1]/tbody[1]/tr[1]/td[7]/div[1]/a[1]");
    By locatorTextoCartIsEmpty = By.xpath("//p[contains(text(),'Your shopping cart is empty.')]");

    public void eliminarProducto(){
        click(esperaExplicita(locatorBtnDeleteProduct));
    }

    public String obtenerCartIsEmpty(){
        return obtenerTexto(esperaExplicita(locatorTextoCartIsEmpty));
    }

}
