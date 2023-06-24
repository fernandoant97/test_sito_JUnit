import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;


public class test {
    public static void main(String[] args) throws URISyntaxException {

        // Imposta il percorso del driver del browser
        System.setProperty("webdriver.chrome.driver", "C:/Users/parad/Desktop/test-sito/chromedriver.exe");

        // Crea una nuova istanza del driver
        WebDriver driver = new ChromeDriver();
        
        
        // Verifica se c'è una connessione col server locale
        try {
            var urlString = "http://localhost:8080/";
            var url = new URI(urlString).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == 200) {
                System.out.println("Connessione al server locale presente");
            } else {
                System.out.println("Nessuna connessione al server locale");
                driver.quit();
                return;
            }
        } catch (IOException e) {
            System.out.println("Nessuna connessione al server locale");
            driver.quit();
            return;
        }

        // Visita una pagina web
        driver.get("http://localhost:8080/");

        WebElement usernameInput = driver.findElement(By.cssSelector("input[name='username']"));
        usernameInput.sendKeys("root");

        WebElement passwordInput = driver.findElement(By.cssSelector("input[name='password']"));
        passwordInput.sendKeys("root");

       // Trova il pulsante di login utilizzando il suo attributo di classe
        WebElement loginButton = driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary.btn-block"));

        // Clicca sul pulsante di login
        loginButton.click();
 
    try {

        // Attendi che l'elemento con id ".fa.fa-user.fa-2x" sia visibile
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(2));  
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fa.fa-user.fa-2x")));

        // Se siamo arrivati qui senza errori, il login è andato a buon fine
        System.out.println("Login effettuato con successo!");

    } catch (TimeoutException e) {
        // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato trovato entro il tempo specificato
        System.out.println("Utente/password errati! Login non riuscito.");
    }

    //Entrata nel pulsante Anagrafica
    WebElement anagraficaButton = driver.findElement(By.cssSelector(".fa.fa-clipboard-list.fa-2x"));
    anagraficaButton.click();
try {

        // Attendi che l'elemento con id ".fa.fa-user.fa-2x" sia visibile
        WebDriverWait wait = new WebDriverWait (driver, Duration.ofSeconds(2));  
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fas.fa-plus.fa-2x")));

        // Se siamo arrivati qui senza errori, il login è andato a buon fine
        System.out.println("Menù principale raggiunto");

    } catch (TimeoutException e) {
        // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato trovato entro il tempo specificato
        System.out.println("Menù principale non raggiunto");
    }
    }
    
}