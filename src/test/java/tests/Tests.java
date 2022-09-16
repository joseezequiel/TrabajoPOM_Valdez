package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import utilidades.DataDriven;
import utilidades.PropertiesDriven;

import java.io.IOException;
import java.util.ArrayList;

public class Tests {
    //atributos
    private WebDriver driver;
    private DataDriven data;
    private ArrayList<String> datosCasosDePrueba;
    private PropertiesDriven properties;

    //atributos (page)
    private HomePage homePage;
    private AuthenticationPage authenticationPage;
    private MyAccountPage myAccountPage;
    private WomanCategoryPage womanCategoryPage;
    private ShoppingCartPage shoppingCartPage;




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

        myAccountPage = new MyAccountPage(authenticationPage.getDriver());

        womanCategoryPage = new WomanCategoryPage(authenticationPage.getDriver());

        homePage = new HomePage(womanCategoryPage.getDriver());
        shoppingCartPage = new ShoppingCartPage(homePage.getDriver());

        homePage.cargarSitio(properties.getProperty("url"));
        // No quise maximizar la pantalla porque aveces los localizadores cambian de nombre si se ejecuta el suit en una compu con pantalla grande o chica
        // homePage.maximizarBrowser();
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

    @Test
    public void CP002_loginEmail() throws IOException{
        datosCasosDePrueba = data.getData("CP002_loginEmail");
        homePage.irIniciarSesion();
        authenticationPage.iniciarSesion(datosCasosDePrueba.get(1), datosCasosDePrueba.get(2));
        Assert.assertEquals(authenticationPage.obtenerMensajeAuthenticationEmailFailed(), datosCasosDePrueba.get(3));
    }

    @Test
    public void CP003_logInSuccessfully() throws IOException{
        datosCasosDePrueba = data.getData("CP003_logInSuccessfully");
        homePage.irIniciarSesion();
        authenticationPage.iniciarSesion(datosCasosDePrueba.get(1), datosCasosDePrueba.get(2));
        Assert.assertEquals(myAccountPage.obtenerMensajeLogInSuccessfully(), datosCasosDePrueba.get(3));
    }

    @Test
    public void CP004_addProductToWishlist() throws IOException{
        datosCasosDePrueba = data.getData("CP004_addProductToWishlist");
        homePage.irIniciarSesion();
        authenticationPage.iniciarSesion(datosCasosDePrueba.get(1), datosCasosDePrueba.get(2));

        myAccountPage.irWomanCategoryPage();

        womanCategoryPage.verMasProducto(womanCategoryPage.buscarProductoPorSuId(2));
        womanCategoryPage.addToWishList();

        Assert.assertEquals(womanCategoryPage.obtenerMensajeProductoAgregadoAlCarrito(), datosCasosDePrueba.get(3));
    }

    @Test
    public void CP005_checkThatProductAppearsonWantedList() throws IOException{
        //Mismos pasos que el caso 4. Es decir, la parte de agregar 1 producto a la lista de deseados
        datosCasosDePrueba = data.getData("CP005_checkThatProductAppearsonWantedList");
        homePage.irIniciarSesion();
        authenticationPage.iniciarSesion(datosCasosDePrueba.get(1), datosCasosDePrueba.get(2));
        myAccountPage.irWomanCategoryPage();
        womanCategoryPage.verMasProducto(womanCategoryPage.buscarProductoPorSuId(3));
        womanCategoryPage.addToWishList();

        //ahora si, comienza lo propio de éste caso de prueba
        womanCategoryPage.irAuthenticationPage();
        myAccountPage.irWishLists();

        //el producto que agregamos a la lista de deseados anteriormente, tiene como id el nro 3. Por lo que buscamos ese producto en la lista de deseados
        boolean productoAgregadoALaListaDeDeseados = myAccountPage.checkThatProductAppearsonWantedList(3);
        Assert.assertTrue(productoAgregadoALaListaDeDeseados);
    }

    @Test
    public void CP006_searchProductNotAddedToWishList() throws IOException{
        //Mismos pasos que el caso 4. Es decir, la parte de agregar 1 producto a la lista de deseados
        datosCasosDePrueba = data.getData("CP005_checkThatProductAppearsonWantedList");
        homePage.irIniciarSesion();
        authenticationPage.iniciarSesion(datosCasosDePrueba.get(1), datosCasosDePrueba.get(2));
        myAccountPage.irWomanCategoryPage();
        womanCategoryPage.verMasProducto(womanCategoryPage.buscarProductoPorSuId(3));
        womanCategoryPage.addToWishList();

        //ahora si, comienza lo propio de éste caso de prueba
        womanCategoryPage.irAuthenticationPage();
        myAccountPage.irWishLists();

        //Buscamos otro producto, por ejemplo el que tiene id = 4. No debería encontrarlo ese producto en la lista de deseados ya que en ningún momento se lo agregó.

        //El propósito de éste caso de prueba es que sólo te lo identifique el producto con id=4 en este ejemplo en la wish list, ya que a la izquierda en la columna "Top seller" puede
        //aparecer. Sin embargo el test debe dar falso ya que se busca ese producto en la wish list. Con este caso probamos que no aparezcan productos que no se hayan agregado intencionalmente
        //a la wish list.
        boolean productoNoAgregadoALaListaDeDeseados = myAccountPage.checkThatProductAppearsonWantedList(4);
        Assert.assertFalse(productoNoAgregadoALaListaDeDeseados);
    }

    @Test
    public void CP007_agregarProductoSinLogin() throws IOException {
        datosCasosDePrueba = data.getData("CP007_agregarProductoSinLogin");

        homePage.irWomanPage();
        womanCategoryPage.addToCart();      //agrega un producto pre definido que está en la pagina de categoria "mujer"

        //si el caso falla o tira error, subirle los milisegundos de espera.
        womanCategoryPage.esperarXSegundos(5000);

        Assert.assertEquals(womanCategoryPage.obtenerMensajeProductAddedToCart(), datosCasosDePrueba.get(3));
    }

    @Test
    public void CP008_emptyCartMessage() throws IOException {
        datosCasosDePrueba = data.getData("CP008_emptyCartMessage");

        homePage.irWomanPage();
        womanCategoryPage.addToCart();      //agrega un producto pre definido que está en la pagina de categoria "mujer"

        womanCategoryPage.esperarXSegundos(5000);       //si el caso falla o tira error, subirle los milisegundos de espera.

        womanCategoryPage.clickBtnProceedToCheckout();      //nos re dirige a la página del carrito de compras
        shoppingCartPage.eliminarProducto();
        womanCategoryPage.esperarXSegundos(5000);       //si el caso falla o tira error, subirle los milisegundos de espera.
        Assert.assertEquals(shoppingCartPage.obtenerCartIsEmpty(), datosCasosDePrueba.get(3));

    }
}
