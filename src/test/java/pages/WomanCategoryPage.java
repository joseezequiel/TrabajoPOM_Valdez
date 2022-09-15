package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilidades.BaseClass;

public class WomanCategoryPage extends BaseClass {
    public WomanCategoryPage(WebDriver driver) {
        super(driver);
    }
    By locatorBtnMore = By.xpath("//body/div[@id='page']/div[2]/div[1]/div[3]/div[2]/ul[1]/li[2]/div[1]/div[2]/div[2]/a[2]");
    By locatorBtnAddToWishList = By.xpath("//a[@id='wishlist_button']");
    By locatorTxtProductAddedToWishList = By.xpath("//p[contains(text(),'Added to your wishlist.')]");


    public void verProducto(int idProducto){
        click(esperaExplicita(locatorBtnMore));
    }

    public By buscarProductoPorSuId(int idProducto){
        By locatorIdProduct = By.cssSelector("a[href='http://automationpractice.com/index.php?id_product="+idProducto+"&controller=product']");
        return locatorIdProduct;
    }

    public void verMasProducto(By product){
        click(esperaExplicita(product));
    }

    public void addToWishList(){
        click(esperaExplicita(locatorBtnAddToWishList));
    }

    public String obtenerMensajeProductoAgregadoAlCarrito(){
        return obtenerTexto(esperaExplicita(locatorTxtProductAddedToWishList));
    }
}
