package utilidades;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BaseClass {
    protected WebDriver driver;
    private WebDriverWait wait;


    // driver
    public BaseClass(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }


    // Buscar elemento/s web
    public WebElement buscarElementoWeb(By localizador){
        return driver.findElement(localizador);
    }

    public List<WebElement> buscarElementosWeb(By localizador){
        return driver.findElements(localizador);
    }


    // click
    public void click(By localizador){
        this.driver.findElement(localizador).click();
    }

    public void click(WebElement elemento){
        elemento.click();
    }


    //texto
    public void agregarTexto(By localizador, String texto){
        this.driver.findElement(localizador).sendKeys(texto);
    }

    public void agregarTexto(WebElement elementoWeb, String texto){
        elementoWeb.sendKeys(texto);
    }

    public String obtenerTexto(By localizador, String texto){
        return this.driver.findElement(localizador).getText();
    }

    public String obtenerTexto(WebElement elemento){
        return elemento.getText();
    }


    // combinacion de teclas, por ejemplo ctrl shift
    public void agregarCombinacionTeclas(By localizador, Keys key){
        this.driver.findElement(localizador).sendKeys(key);
    }


    //pagina
    public void cargarSitio(String url){
        this.driver.get(url);
    }


    //esperar
    public void esperarXSegundos(int milisegundos){
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    //espera explicita
    public WebElement esperaExplicita(By localizador){
        wait = new WebDriverWait(this.driver,30);
        return wait.until(ExpectedConditions.presenceOfElementLocated(localizador));
    }

    public WebElement esperarPorElementoAClickear(By localizador){
        wait = new WebDriverWait(this.driver,20);
        return wait.until(ExpectedConditions.elementToBeClickable(localizador));
    }


    public WebDriver conexionDriver(String browser,String rutaDriver, String propertyDriver){
        if(browser.equalsIgnoreCase("chrome")){
            System.setProperty(propertyDriver,rutaDriver);
            this.driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            System.setProperty(propertyDriver,rutaDriver);
            this.driver = new FirefoxDriver();
        }else if(browser.equalsIgnoreCase("IExplorer")){
            System.setProperty(propertyDriver,rutaDriver);
            this.driver = new InternetExplorerDriver();
        }
        return this.driver;
    }

    public void maximizarBrowser(){
        this.driver.manage().window().maximize();
    }

    public void cerrarBrowser(){
        this.driver.close();
    }

    public int contarIFrames(By localizador){  //tagName "iframe"
        List<WebElement> frames = this.driver.findElements(localizador);
        return frames.size();
    }

    public void irAFrameByIndex(int index){
        this.driver.switchTo().frame(index);
    }

    public void irAFrameByIdOrName(String nameOrId){
        this.driver.switchTo().frame(nameOrId);
    }


}
