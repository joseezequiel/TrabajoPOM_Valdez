package utilidades;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ClaseBase {
    private WebDriver driver;
    private WebDriverWait wait;


    // driver
    public ClaseBase(WebDriver driver) {
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

    public void agregarTexto(WebElement elemento, String texto){
        elemento.sendKeys(texto);
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
    public void cargarPagina(String url){
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
    public WebElement esperarPorPresenciaElemento(By localizador){
        wait = new WebDriverWait(this.driver,20);
        return wait.until(ExpectedConditions.presenceOfElementLocated(localizador));
    }

    public WebElement esperarPorElementoAClickear(By localizador){
        wait = new WebDriverWait(this.driver,20);
        return wait.until(ExpectedConditions.elementToBeClickable(localizador));
    }

    public WebDriver conexionDriver(String ruta, String property, String browser){
        switch (browser){
            case "chrome":
                System.setProperty(property, ruta);
                this.driver = new ChromeDriver();
                return driver;
            case "firefox":
                System.setProperty(property, ruta);
                this.driver = new FirefoxDriver();
                return driver;
            default:
                return driver;
        }
    }

    public void maximizarVentana(){
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
