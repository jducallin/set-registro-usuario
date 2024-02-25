package stepdefinition;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistroUsuarioStepDefinition {
    private WebDriver driver;
    private Scenario scenario;
    @Before
    public void setUp(Scenario scenario) {
        this.scenario = scenario;
        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
    }
    @After
    public void tearDown(){
        takeScreenShot(scenario);
        driver.quit();
    }

    public void takeScreenShot(Scenario scenario) {
        try {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Captura de pantalla");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Given("que abro el navegador y visito la pagina web {string}")
    public void queAbroElNavegadorYVisitoLaPaginaWeb(String url) {
        driver.get(url);
    }

    @When("observo el botón de inicio de sesión")
    public void observoElBotónDeInicioDeSesión() {
    }

    @Then("debería ver el boton con el texto {string} en la página")
    public void deberíaVerElBotonConElTextoEnLaPágina(String textoBoton) {
        WebElement boton = driver.findElement(By.xpath("//a[normalize-space()='" + textoBoton + "']"));
        assertTrue(boton.isDisplayed());
    }

    @When("hago clic en el boton con el texto {string}")
    public void hagoClicEnElBotonConElTexto(String textoBoton) {
        WebElement boton = driver.findElement(By.xpath("//a[normalize-space()='" + textoBoton + "']"));
        boton.click();
    }

    @Then("el botón Create Account {string} debería estar presente en la página")
    public void elBotónDeberíaEstarPresenteEnLaPágina(String textoBoton) throws InterruptedException {
        WebElement boton = driver.findElement(By.xpath("//button[@id='" + textoBoton + "']"));
        assertTrue(boton.isDisplayed());
        Thread.sleep(3000);
    }

    @And("hago clic en el botón Create an account {string}")
    public void hagoClicEnElBotón(String textoBoton) throws InterruptedException {
        WebElement boton = driver.findElement(By.xpath("//button[@id='" + textoBoton + "']"));
        boton.click();
        Thread.sleep(3000);
    }

    @Then("deberia de arrojar mensaje de error {string}")
    public void deberiaDeArrojarMensajeDeError(String expectedErrorMessage) throws InterruptedException {
        WebElement errorMessage = driver.findElement(By.xpath("//li[normalize-space()='" + expectedErrorMessage + "']"));
        Assert.assertTrue("No se mostró el mensaje de error esperado", errorMessage.isDisplayed());
        String actualErrorMessage = errorMessage.getText().trim();
        Assert.assertEquals("El mensaje de error no coincide con el esperado", expectedErrorMessage, actualErrorMessage);
        Thread.sleep(3000);
    }

    @And("ingreso correo electrónico {string} en el campo Email address")
    public void ingresoCorreoElectrónicoEnElCampo(String email) {
        WebElement campo = driver.findElement(By.xpath("//input[@id='email_create']"));
        campo.sendKeys(email);
    }

    @And("ingreso correo electrónico valido {string} en el campo Email address")
    public void ingresoCorreoElectrónicoValido(String email) {
        WebElement campo = driver.findElement(By.xpath("//input[@id='email_create']"));
        campo.sendKeys(email);
    }

    @Then("debería ver el siguiente el mensaje de Información personal {string}")
    public void deberíaVerElSiguienteElMensajeDeInformaciónPersonal(String infoPersonal) throws InterruptedException {
        WebElement personalInfoForm = driver.findElement(By.xpath("//h3[normalize-space()='Your personal information']"));
        assertTrue("El formulario de información personal no se cargó correctamente",personalInfoForm.getText().contains(infoPersonal));
        Thread.sleep(3000);
    }

    @And("selecciono el género masculino")
    public void seleccionoElGéneroMasculino() {
        WebElement generoMasculino = driver.findElement(By.xpath("//input[@id='id_gender1']"));
        generoMasculino.click();
    }

    @And("ingreso {string} en el campo first name")
    public void ingresoNombre(String nombre) {
        WebElement campo = driver.findElement(By.xpath("//input[@id='customer_firstname']"));
        campo.sendKeys(nombre);
    }

    @And("ingreso {string} en el campo last name")
    public void ingresoApellido(String apellido) {
        WebElement campo = driver.findElement(By.xpath("//input[@id='customer_lastname']"));
        campo.sendKeys(apellido);
    }

    @And("hago clic en el botón Register {string}")
    public void hagoClicEnElBotónRegister(String textoBoton) throws InterruptedException {
        WebElement boton = driver.findElement(By.xpath("//button[@id='" + textoBoton + "']"));
        boton.click();
        Thread.sleep(3000);
    }
    @Then("debería ver un mensaje de error {string}")
    public void deberíaVerUnMensajeDeError(String error) {
        WebElement mensajeError = driver.findElement(By.xpath("//div[@class='alert alert-danger']"));
        assertTrue("El formulario de información personal no se cargó correctamente",mensajeError.getText().contains(error));
    }

    @And("ingreso {string} en el campo password")
    public void ingresoContraseña(String contraseña) {
        WebElement campo = driver.findElement(By.xpath("//input[@id='passwd']"));
        campo.sendKeys(contraseña);
    }
    @Then("debería ver el mensaje {string}")
    public void RegistroExitoso(String mensajeRegistro) throws InterruptedException {
        WebElement mensaje = driver.findElement(By.xpath("//h1[normalize-space()='" + mensajeRegistro + "']"));
        assertTrue(mensaje.isDisplayed());
        Thread.sleep(5000);
    }
}
