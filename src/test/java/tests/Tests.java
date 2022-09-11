package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.FormularioContratacionPage;
import pages.HomePage;
import pages.RetomarContratacionPage;
import utilidades.DataDriven;

import java.io.IOException;
import java.util.List;

public class Tests {
    //atributos
    private WebDriver driver;
    private DataDriven data;

    private List<String> datosCP;

    //atributos (page)
    private HomePage homePage;
    private RetomarContratacionPage retomaContratacionPage;
    private FormularioContratacionPage formularioContratacionPage;

    @BeforeSuite
    public void inicioSuitDePruebas(){
        System.out.println("Inicio de suit de pruebas automatizadas.");
    }

    @BeforeClass
    public void preparacionClase(){
        String rutaProyecto = System.getProperty("user.dir");
        String rutaDriver = rutaProyecto + "\\src\\test\\resources\\drives\\chromedriver.exe";
        String property = "webdriver.chrome.driver";
        String browser = "chrome";

        data = new DataDriven();

        homePage = new HomePage(driver);
        homePage.conexionDriver(rutaDriver, property, browser);

        // obtengo el driver de la página anterior, en este caso de homepage.
        retomaContratacionPage = new RetomarContratacionPage(homePage.getDriver());

        formularioContratacionPage = new FormularioContratacionPage(retomaContratacionPage.getDriver());
    }

    @BeforeMethod
    public void preparacionTests(){
        String url = "https://publico.transbank.cl/";
        homePage.cargarPagina(url);
        homePage.maximizarVentana();
    }

    @Test
    public void CP001_retomaFormularioContratacion() throws IOException {
        datosCP = data.obtenerDatosPrueba("CP001_retomaFormularioContratacion");
        homePage.irAHazteCliente();
        retomaContratacionPage.irARetomarContratacion();
        formularioContratacionPage.llenarFormularioRetomaContratacion(datosCP.get(1), datosCP.get(2));

        Assert.assertEquals(datosCP.get(3), formularioContratacionPage.obtenerMensajeError());
    }

    @AfterMethod
    public void after(){
        homePage.cerrarBrowser();
    }

    @Test
    public void CP002(){}

    @Test
    public void CP003(){}

    @Test
    public void CP004(){}

    @Test
    public void CP005(){}
}
