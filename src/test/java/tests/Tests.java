package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AuthenticationPage;
import pages.FormularioContratacionPage;
import pages.HomePage;
import pages.RetomarContratacionPage;
import utilidades.DataDriven;
import utilidades.PropertiesDriven;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tests {
    //atributos
    private WebDriver driver;
    private DataDriven data;
    private ArrayList<String> datosCasosDePrueba;
    private PropertiesDriven properties;

    //atributos (page)
    private HomePage homePage;
    private AuthenticationPage authenticationPage;


    @BeforeSuite
    public void inicioSuitDePruebas(){
        properties = new PropertiesDriven();
        System.out.println("Inicio de suit de pruebas automatizadas.");
    }

    @AfterSuite
    public void FinSuitDePruebas(){
        System.out.println("Fin de suit de pruebas automatizadas.");
    }


    @BeforeMethod
    public void preparacionTests(){
        //Instanciar los objetos
        datosCasosDePrueba = new ArrayList<String>();
        homePage = new HomePage(driver);
        String rutaDriver = System.getProperty("user.dir") + properties.getProperty("rutaDriver");

        homePage.conexionDriver(properties.getProperty("browser"), rutaDriver, properties.getProperty("propertyDriver"));

        authenticationPage = new AuthenticationPage(homePage.getDriver());

        homePage.cargarSitio(properties.getProperty("url"));
        homePage.maximizarBrowser();
    }

    @AfterMethod
    public void preparacionPrueba(){
        authenticationPage.cerrarBrowser();
    }


    @BeforeClass
    public void preparacionClase(){
    }


    @Test
    public void CP001_loginInvalidEmail() throws IOException {
        datosCasosDePrueba = data.getData("CP001_loginInvalidEmail");
        homePage.irIniciarSesion();
        authenticationPage.iniciarSesion(datosCasosDePrueba.get(1), datosCasosDePrueba.get(2));
        Assert.assertEquals(authenticationPage.obtenerMensajeInvalidEmail(), datosCasosDePrueba.get(3));
    }


}
