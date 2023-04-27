package br.ada.schedule;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumTests {
    private static WebDriver driver;
//    private static String url="http://localhost:8080/app/users";
    @BeforeAll
    public static void setup(){
        WebDriverManager.chromedriver().setup();
        driver =  new ChromeDriver( new ChromeOptions().addArguments("--remote-allow-origins=*"));
    }
    @AfterAll
    public static void destroy() {
        driver.quit();
    }
    @BeforeEach
    public void setUrl(){
        driver.get("http://localhost:8080/app/users");
    }

    @Test
    @Order(1)
    public void cadastroBemSucedido_NomeUserSenha(){
//        driver.get(url);
        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/a")).click();
        driver.findElement(By.id("name")).sendKeys("Isabela Santos");
        driver.findElement(By.id("username")).sendKeys("IsabelaSnts01");
        driver.findElement(By.id("password")).sendKeys("369258147");
        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/button")).click();
    }

    @Test
    @Order(2)
    public void cadastroNaoSucedido_NomeUserSEMSenha(){

        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/a")).click();
        driver.findElement(By.id("name")).sendKeys("Isabela Santos");
        driver.findElement(By.id("username")).sendKeys("IsabelaSnts02");
//        driver.findElement(By.id("password")).sendKeys("369258147");
        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/button")).click();

        WebElement element = driver.findElement(
                By.className("user-form-error")
        );
        assertNotNull(element);
        assertEquals("não deve estar em branco", element.getText());
    }

    @Test
    @Order(3)
    public void cadastroNaoSucedido_NomeSenhaSEMUser(){

        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/a")).click();
        driver.findElement(By.id("name")).sendKeys("Isabela Santos");
//        driver.findElement(By.id("username")).sendKeys("IsabelaSnts");
        driver.findElement(By.id("password")).sendKeys("369258147");
        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/button")).click();
        WebElement element = driver.findElement(
                By.className("user-form-error")
        );
        assertNotNull(element);
        assertEquals("não deve estar em branco", element.getText());
    }

    @Test
    @Order(4)
    public void cadastroNaoSucedido_UserSenhaSEMNome(){

        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/a")).click();
//        driver.findElement(By.id("name")).sendKeys("Isabela Santos");
        driver.findElement(By.id("username")).sendKeys("IsabelaSnts03");
        driver.findElement(By.id("password")).sendKeys("369258147");
        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/button")).click();
        WebElement element = driver.findElement(
                By.className("user-form-error")
        );
        assertNotNull(element);
        assertEquals("não deve estar em branco", element.getText());
    }

    @Test
    @Order(5)
    public void cadastroNaoSucedido_NomeJaExistenteUserJaExistente(){

        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/a")).click();
        driver.findElement(By.id("name")).sendKeys("Isabela Santos");
        driver.findElement(By.id("username")).sendKeys("IsabelaSnts01");
        driver.findElement(By.id("password")).sendKeys("369258147");
        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/button")).click();
        WebElement element = driver.findElement(
                By.className("user-form-error")
        );
        assertNotNull(element);
        assertEquals("Username already in use", element.getText());
    }

    @Test
    @Order(6)
    public void cadastroBemSucedido_NomeJaExistenteUserDIFERENTE(){

        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/a")).click();
        driver.findElement(By.id("name")).sendKeys("Isabela Santos");
        driver.findElement(By.id("username")).sendKeys("IsabelaSnts04");
        driver.findElement(By.id("password")).sendKeys("369258147");
        driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/button")).click();
    }

    @Test
    @Order(7)
    public void listaContemNomeUserEEdit(){
        boolean nome = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/table/tbody/tr[1]/td[1]")).isEnabled();
        Assertions.assertTrue(nome);
        boolean username = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/table/tbody/tr[1]/td[2]")).isEnabled();
        Assertions.assertTrue(username);
        boolean botaoEdit = driver.findElement(By.className("edit")).isEnabled();
        Assertions.assertTrue(botaoEdit);
    }

    @Test
    @Order(8)
    public void userNaoAlteravel(){
        driver.findElement(By.className("edit")).click();
        driver.findElement(By.id("username")).getAttribute("readonly");
    }

    @Test
    @Order(9)
    public void senhaAntigaEstaEscondidaPaginaEdit(){
        driver.findElement(By.className("edit")).click();
        String textoSenha = driver.findElement(By.id("password")).getText();
        textoSenha.equals(null);
    }
    @Test
    @Order(10)
    public void editBemSucedido_nomeESenha(){
        driver.findElement(By.className("edit")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("Alebasi Sotnas");
        driver.findElement(By.id("password")).sendKeys("741852963");
    }

    @Test
    @Order(11)
    public void listaNaoContemDelete(){
        Assertions.assertFalse(driver.getPageSource().contains("Delete"));
    }

    @Test
    @Order(12)
    public void paginaEditNaoContemDelete(){
        driver.findElement(By.className("edit")).click();
        Assertions.assertFalse(driver.getPageSource().contains("Delete"));
    }

}
