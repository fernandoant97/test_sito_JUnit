import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;

class testTest {

    private WebDriver driver;
    ChromeDriverService service = new ChromeDriverService.Builder()
            .withLogOutput(System.out)
            .build();

    @BeforeEach
    void setUp() throws URISyntaxException {
        // Questo codice viene eseguito prima di ogni test
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\lucis_ck21\\Desktop\\ing\\progetto_test\\chromedriver.exe");
        driver = new ChromeDriver();

        // Esegui test di connessione
        testConnessione();

        // Esegui il test di login prima di ogni test, altrimenti non si potrebbe
        // testare il sito successivamente
        login();
    }

    @AfterEach
    void tearDown() {
        // Questo codice viene eseguito dopo ogni test
        if (driver != null) {
        driver.quit();
        }
    }

    @Test
    void testConnessione() throws URISyntaxException {
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
                fail("Nessuna connessione al server locale");
                return;
            }
        } catch (IOException e) {
            System.out.println("Nessuna connessione al server locale");
            driver.quit();
            fail("Nessuna connessione al server locale");
            return;
        }
    }

    void login() {
        // Visita una pagina web
        driver.get("http://localhost:8080/");

        WebElement usernameInput = driver.findElement(By.cssSelector("input[name='username']"));
        usernameInput.sendKeys("user");

        WebElement passwordInput = driver.findElement(By.cssSelector("input[name='password']"));
        passwordInput.sendKeys("user");

        // Trova il pulsante di login utilizzando il suo attributo di classe
        WebElement loginButton = driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary.btn-block"));

        // Clicca sul pulsante di login
        loginButton.click();

        try {

            // Attendi che l'elemento con id ".fa.fa-user.fa-2x" sia visibile
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".fa.fa-user.fa-2x")));

            // Se siamo arrivati qui senza errori, il login è andato a buon fine
            System.out.println("Login effettuato con successo!");

        } catch (TimeoutException e) {
            // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
            // trovato entro il tempo specificato
            System.out.println("Utente/password errati! Login non riuscito.");
            fail("Utente/password errati! Login non riuscito."); // Questo farà fallire il test
        }
    }

    @Test  
    void loginErrato(){
        logout();
        driver.get("http://localhost:8080/");
        WebElement usernameInput = driver.findElement(By.cssSelector("input[name='username']"));
        usernameInput.sendKeys("michele");

        WebElement passwordInput = driver.findElement(By.cssSelector("input[name='password']"));
        passwordInput.sendKeys("mick");

        // Trova il pulsante di login utilizzando il suo attributo di classe
        WebElement loginButton = driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary.btn-block"));

        // Clicca sul pulsante di login
        loginButton.click();

        try {

            // Attendi che l'elemento con id ".fa.fa-user.fa-2x" sia visibile
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/form/img")));

            // Se siamo arrivati qui senza errori, il login è andato a buon fine
            System.out.println("Utente/password errati! Login non riuscito.");

        } catch (TimeoutException e) {
            // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
            // trovato entro il tempo specificato
            System.out.println("Login effettuato");
            fail("Login effettuato"); // Questo farà fallire il test
        }
    }

    @Test
    void logout(){
        WebElement omino = driver.findElement(By.xpath("//i[@class='fa fa-user fa-2x']"));
        omino.click();
        WebElement logout = driver.findElement(By.xpath("//a[normalize-space()='Logout']"));
        logout.click();

        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".btn.btn-lg.btn-primary.btn-block")));

            // Se siamo arrivati qui senza errori, il login è andato a buon fine
            System.out.println("Logout effettuato!");

        } catch (TimeoutException e) {
            // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
            // trovato entro il tempo specificato
            System.out.println("Errore logout");
            fail("Errore logout"); // Questo farà fallire il test
        }
    }

    @Test
    void loginDirettore(){
        logout();
        WebElement usernameInput = driver.findElement(By.cssSelector("input[name='username']"));
        usernameInput.sendKeys("fabi");

        WebElement passwordInput = driver.findElement(By.cssSelector("input[name='password']"));
        passwordInput.sendKeys("fabi");

        // Trova il pulsante di login utilizzando il suo attributo di classe
        WebElement loginButton = driver.findElement(By.cssSelector(".btn.btn-lg.btn-primary.btn-block"));

        // Clicca sul pulsante di login
        loginButton.click();

        try {

            // Attendi che l'elemento con id ".fa.fa-user.fa-2x" sia visibile
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[normalize-space()='Gestione Utenti']")));

            // Se siamo arrivati qui senza errori, il login è andato a buon fine
            System.out.println("Login direttore effettuato con successo!");

        } catch (TimeoutException e) {
            // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
            // trovato entro il tempo specificato
            System.out.println("Utente/password errati! Login direttore non riuscito.");
            fail("Utente/password errati! Login direttore non riuscito."); // Questo farà fallire il test
        }
    }

    @Test
    @Order(1)
    void testAnagrafica() {
        WebElement anagraficaButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/a/div/h4"));
        anagraficaButton.click();

        try {
            // Aspetta 5 secondi
            Thread.sleep(5000);

            String html = driver.getPageSource();
            if (html.contains("Nuovo")) { // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
                System.out.println("Menù principale raggiunto");
            } else {
                System.out.println("Menù principale non raggiunto");
                fail("Menù principale non raggiunto");
            }
        } catch (InterruptedException e) {
            // Se si verifica un'eccezione durante l'attesa, stampa lo stack trace e segnala
            // un fallimento
            e.printStackTrace();
            fail("Menù principale non raggiunto");
        }
    }

    void entryAnagrafica() {
        WebElement anagraficaButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[1]/a/div/h4"));
        anagraficaButton.click();

    }

    @Test
    void testNuovo() {
        entryAnagrafica();
        WebElement nuovoButton = driver.findElement(By.xpath("//a[@href='/RichiediAggiungiArticolo']//div[@class='service']//h4[contains(text(),'Nuovo')]"));
        nuovoButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Annulla']")));

            // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
            System.out.println("Nuovo articolo");
        } catch (TimeoutException e) {
            // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
            // trovato entro il tempo specificato
            System.out.println("Nuovo articolo non pervenuto");
            fail("Nuovo articolo non pervenuto");
        }
    }

    @Test
    void testSearch() {
        entryAnagrafica();
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[1]/a[2]/div/i"));
        searchButton.click();
        try {

            // Attendi che l'elemento con id "fas fa-plus fa-2x" sia visibile
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//*[@id=\"mask\"]/form/div[5]/div[2]/input")));

            // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
            System.out.println("searchButton");
        } catch (TimeoutException e) {
            // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
            // trovato entro il tempo specificato
            System.out.println("searchButton non pervenuto");
            fail("searchButton non pervenuto");
        }
    }

    @Test
    void testTaglie() {
        entryAnagrafica();
        WebElement taglieButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[2]/a[1]/div/i"));
        taglieButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(
                    ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"mask\"]/form/div/div[3]/input")));

            // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
            System.out.println("Tabella taglie");
        } catch (TimeoutException e) {
            // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
            // trovato entro il tempo specificato
            System.out.println("Tabella taglie non raggiungibile");
            fail("Tabella taglie non raggiungibile");
        }
    }

    @Test
    void testColore() {
        entryAnagrafica();
        WebElement coloreButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[2]/a[2]/div/i"));
        coloreButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//*[@id=\"mask\"]/form/div[3]/div[3]/input")));

            // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
            System.out.println("Colori");
        } catch (TimeoutException e) {
            // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
            // trovato entro il tempo specificato
            System.out.println("Tabella colori non disponibile");
            fail("Tabella colori non disponibile");
        }
    }

    @Test
    void testReparti() {
        entryAnagrafica();
        WebElement repartiButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[3]/a[1]/div/i"));
        repartiButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//*[@id=\"mask\"]/form/div[3]/div[3]/input")));

            // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
            System.out.println("Reparti");
        } catch (TimeoutException e) {
            // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
            // trovato entro il tempo specificato
            System.out.println("Tabella reparti non disponibile");
            fail("Tabella reparti non disponibile");
        }
    }

    @Test
    void testRaggruppamenti() {
        entryAnagrafica();
        WebElement raggruppamentiButton = driver
                .findElement(By.xpath("//*[@id=\"content\"]/div/div/div[3]/a[2]/div/i"));
        raggruppamentiButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//*[@id=\"mask\"]/form/div[3]/div[2]/input")));

            // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
            System.out.println("Raggruppamenti");
        } catch (TimeoutException e) {
            // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
            // trovato entro il tempo specificato
            System.out.println("Tabella raggruppamenti non disponibile");
            fail("Tabella raggruppamenti non disponibile");
        }
    }

    @Test
    void testCategorie() {
        entryAnagrafica();
        WebElement categorieButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[3]/a[3]/div/i"));
        categorieButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//*[@id=\"mask\"]/form/div[3]/div[2]/input")));
            // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
            System.out.println("Categorie");
        } catch (TimeoutException e) {
            // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
            // trovato entro il tempo specificato
            System.out.println("Tabellacategorie non disponibile");
            fail("Tabella categorie non disponibile");
        }
    }

    @Test
    void testFornitoreNuovo() {
        entryAnagrafica();
        WebElement fornitorenuovoButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[4]/a[1]/div/i"));
        fornitorenuovoButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//*[@id=\"mask\"]/form/div[7]/div[3]/input")));

            // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
            System.out.println("Fornitore Nuovo");
        } catch (TimeoutException e) {
            // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
            // trovato entro il tempo specificato
            System.out.println("Tabella fornitore non disponibile");
            fail("Tabella fornitore non disponibile");
        }
    }

    @Test
    void testFornitore() {
        entryAnagrafica();
        WebElement fornitoreButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[4]/a[2]/div/i"));
        fornitoreButton.click();
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"intestazione\"]/td[4]")));

            // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
            System.out.println("Fornitore");
        } catch (TimeoutException e) {
            // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
            // trovato entro il tempo specificato
            System.out.println("Tabella fornitore non disponibile");
            fail("Tabella fornitore non disponibile");
        }
    }

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    class Gestione {
        @Test
        @Order(1)
        void gestioneUtenti(){
            loginDirettore();
            WebElement gestioneButton = driver.findElement(By.xpath("//a[@href='/RichiediUtenti']//div[@class='service']"));
            gestioneButton.click();

            String html = driver.getPageSource();
            if (html.contains("Elenco Utenti")) { // Se visualizziamo questa stringa, la pagina è stata raggiunta
                System.out.println("Pagina successiva raggiunta");
            } else {
                System.out.println("Pagina successiva non raggiunta");
                fail("Pagina successiva non raggiunta");
            }
        }

        @Test
        @Order(2)
        void nuovoUtente(){
            gestioneUtenti();
            WebElement nuovoButton = driver.findElement(By.xpath("//input[@value='Nuovo Utente']"));
            nuovoButton.click();

            String html = driver.getPageSource();
            if (html.contains("Nuovo Utente")) { // Se visualizziamo questa stringa, la pagina è stata raggiunta
                System.out.println("Pagina successiva raggiunta");
            } else {
                System.out.println("Pagina successiva non raggiunta");
                fail("Pagina successiva non raggiunta");
            }
        }

        @Test
        @Order(3)
        void inserisciDati(){
            nuovoUtente();
            WebElement username = driver.findElement(By.id("username"));
            WebElement password = driver.findElement(By.id("password"));
            WebElement confermaPassword = driver.findElement(By.id("cpassword"));
            WebElement categoria = driver.findElement(By.id("categoria"));
            username.sendKeys("utente");
            password.sendKeys("utente");
            confermaPassword.sendKeys("utente");
            categoria.sendKeys("Standard");

            WebElement salvaButton = driver.findElement(By.cssSelector("input[value='Salva']"));
            // Clicca sul pulsante di ricerca
            salvaButton.click();

           String html = driver.getPageSource();
            if (html.contains("Elenco Utenti")) { // Se visualizziamo questa stringa, la pagina è stata raggiunta
                System.out.println("Pagina successiva raggiunta");
            } else {
                System.out.println("Pagina successiva non raggiunta");
                fail("Pagina successiva non raggiunta");
            }
        }

        @Test
        @Order(4)
        void selezionaUtente(){
            // Trova tutti gli elementi della tabella che hanno come ID "dati"
            List<WebElement> righe = driver.findElements(By.xpath("//*[@id=\"dati\"]"));

            // Itera attraverso le righe della tabella
            for (WebElement riga : righe) {
                // Ottieni il valore della riga
                String valore = riga.getText();

                // Verifica se il valore è "utente"
                if (valore.equals("utente")) {
                    WebElement utenteButton = driver.findElement(By.xpath("//*[@id=\"dati\"]/td[1]"));
                    utenteButton.click();
                }else {
                System.out.println("Utente non trovato");
                }
            }
        }

    }
    
    @Nested
    class Nuovo {
        @Test
        void testFillandSend() {
            entryAnagrafica();
            entryNuovo();
            WebElement descrizioneInput = driver.findElement(By.xpath("//input[@id='descrizione']"));
            descrizioneInput.sendKeys("ARTICOLO56") ;
            WebElement stagioneInput = driver.findElement(By.xpath("//input[@id='stagione']"));
            stagioneInput.sendKeys("AI20") ;
            WebElement salvaButton = driver.findElement(By.xpath("//input[@value='Salva']"));
            salvaButton.click();

            WebElement annullaButton = driver.findElement(By.xpath("//input[@value='Annulla']"));
            annullaButton.click();

            String html = driver.getPageSource();
            if (html.contains("Articoli")) { // Se visualizziamo questa stringa, la pagina è stata raggiunta
                System.out.println("Pagina precedente raggiunta");
            } else {
                System.out.println("Pagina precedente non raggiunta");
                fail("Pagina precedente non raggiunta");
            }

            WebElement searchButton = driver.findElement(By.xpath("//a[@href='/RichiediCercaArticolo']//div[@class='service']//i[@class='fas fa-search fa-2x']"));
            searchButton.click();
            WebElement codiceInput = driver.findElement(By.xpath("//input[@name='codice']"));
            codiceInput.sendKeys("56");
            WebElement cercaButton = driver.findElement(By.xpath("//input[@value='Cerca']"));
            cercaButton.click();
            WebElement articoloButton = driver.findElement(By.id("dati"));
            articoloButton.click();
            String html2 = driver.getPageSource();
            if (html2.contains("Modifica Articolo")) { // Se visualizziamo questa stringa, la pagina è stata raggiunta
                System.out.println("Articolo esistente");
            } else {
                System.out.println("Articolo non esistente");
                fail("Articolo non esistente");
            }

        }

        void entryNuovo(){
        WebElement nuovoButton = driver.findElement(By.xpath("//a[@href='/RichiediAggiungiArticolo']//div[@class='service']//h4[contains(text(),'Nuovo')]"));
        nuovoButton.click();
        } 
    }

 

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    class Cerca {

        @BeforeEach
        void accessoTotale() {
            entryAnagrafica();
            entrySearch();
        }

        @Test
        @Order(1)
        void searchAll() { // Test che restituisce tutti gli elementi senza una particolare specifica

            // Trova il pulsante di ricerca utilizzando il suo attributo di classe
            WebElement cercaButton = driver.findElement(By.cssSelector("input[value='Cerca']"));
            // Clicca sul pulsante di ricerca
            cercaButton.click();
            // Seleziona la riga di dati sulla base dell'ID
            WebElement row = driver.findElement(By.id("dati"));

            // Ottieni tutti i td in quella riga
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Ora che hai i td, puoi ottenere il testo da ognuno di essi
            String codice_ver = cells.get(0).getText();

            // Verifichiamo che all'interno del campo "codice" ci sia almeno un numero.Ciò
            // implica che una riga della
            // tabella sia piena e che anche senza riempire i campi, otteniamo un risultato.

            if (codice_ver.matches(".*\\d.*")) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

        @Test
        @Order(2)
        void successoCerca() {
            WebElement codice = driver.findElement(By.cssSelector("input[name='codice']"));
            WebElement descrizione = driver.findElement(By.cssSelector("input[name='descrizione']"));
            WebElement codiceFornitore = driver.findElement(By.cssSelector("input[name='codiceFornitore']"));
            WebElement codiceRF = driver.findElement(By.cssSelector("input[name='codiceRF']"));
            WebElement stagione = driver.findElement(By.cssSelector("input[name='stagione']"));
            codice.sendKeys("13");
            descrizione.sendKeys("ARTICOLO13");
            codiceFornitore.sendKeys("2");
            codiceRF.sendKeys("");
            stagione.sendKeys("AI20");
            // Trova il pulsante di ricerca utilizzando il suo attributo di classe
            WebElement cercaButton = driver.findElement(By.cssSelector("input[value='Cerca']"));
            // Clicca sul pulsante di ricerca
            cercaButton.click();
            // Seleziona la riga di dati sulla base dell'ID
            WebElement row = driver.findElement(By.id("dati"));

            // Ottieni tutti i td in quella riga
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Ora che hai i td, puoi ottenere il testo da ognuno di essi
            String codice_ver = cells.get(0).getText();
            String descrizione_ver = cells.get(1).getText();
            String fornitore_ver = cells.get(2).getText();
            String stagione_ver = cells.get(3).getText();

            // Verifica i valori
            if (codice_ver.equals("13") && descrizione_ver.equals("ARTICOLO13") &&
                    fornitore_ver.equals("FORNITORE2") && stagione_ver.equals("AI20")) {
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }
        }

        @Test
        @Order(3)
        void seleziona_da_successoCerca() {
            successoCerca();
            WebElement successoButton = driver.findElement(By.id("dati"));
            successoButton.click();
            String html = driver.getPageSource();
            if (html.contains("Modifica Articolo")) { // Se visualizziamo questa stringa, la pagina è stata raggiunta
                System.out.println("Pagina successiva raggiunta");
            } else {
                System.out.println("Pagina successiva non raggiunta");
                fail("Pagina successiva non raggiunta");
            }
        }

        @Test
        @Order(4)
        void fallimentoCerca() {
            WebElement codice_fail = driver.findElement(By.cssSelector("input[name='codice']"));
            WebElement descrizione_fail = driver.findElement(By.cssSelector("input[name='descrizione']"));
            WebElement searchButton = driver.findElement(By.xpath("//input[@value='Cerca']"));
            codice_fail.sendKeys("14");
            descrizione_fail.sendKeys("ARTICOLO13");
            searchButton.click();

            // Returns height, width, x and y coordinates referenced element
            Rectangle res =  driver.findElement(By.className("table")).getRect();
            int altezza_tabella=res.getHeight();
            if (altezza_tabella==100) {

                // Se siamo arrivati a questo punto, la tabella non è vuota
                System.out.println("La tabella è effettivamente vuota");

            } else{
                // Se è stata generata un'eccezione NoSuchElementException, la tabella è vuota
                System.out.println("Nella tabella è presente almeno un elemento");
                fail ("Nella tabella è presente almeno un elemento");
            }
        }

        @Test
        @Order(5)
        void annullaRicerca() {
            WebElement annullaButton = driver.findElement(By.xpath("//input[@value='Annulla']"));
            annullaButton.click();
            try {

                // Attendi che l'elemento con id "fas fa-plus fa-2x" sia visibile
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/RichiediCercaArticolo']//div[@class='service']//i[@class='fas fa-search fa-2x']")));

                // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
                System.out.println("Uscita effettuata");
            } catch (TimeoutException e) {
                // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
                // trovato entro il tempo specificato
                System.out.println("Uscita non effettuata");
                fail("Uscita non effettuata");
            }

            // Aggiungi una pausa di 2 secondi
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Test
        @Order(6)
        void home() {
            WebElement homeButton = driver.findElement(By.xpath("//i[@class='fa fa-home fa-2x']"));
            homeButton.click();
            try {
                // Attendi che l'elemento con id "fas fa-plus fa-2x" sia visibile
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions
                        .presenceOfElementLocated(By.xpath("//i[@class='fa fa-user fa-2x']")));

                // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
                System.out.println("Pagina Home raggiunta");
            } catch (TimeoutException e) {
                // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
                // trovato entro il tempo specificato
                System.out.println("Pagina Home non raggiunta");
                fail("Pagina Home non raggiunta");
            }

        }
        void entrySearch(){
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[1]/a[2]/div/i"));
        searchButton.click();
        }
    }

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    class Taglie {
        
        @BeforeEach
        void accessoTotale() {
            entryAnagrafica();
            entryTaglie();
        }

        @Test
        @Order (1)
        void Nuova() {
            WebElement nuovaButton = driver.findElement(By.xpath("//input[@value='Nuova']"));
            nuovaButton.click();

            String html = driver.getPageSource();
            if (html.contains("Nuova Tabella Taglie")) { // Se visualizziamo questa stringa, la pagina è stata raggiunta
                System.out.println("Pagina successiva raggiunta");
            } else {
                System.out.println("Pagina successiva non raggiunta");
                fail("Pagina successiva non raggiunta");
            }

            
        }

        @Test
        @Order (2)
        void Inserisci() {
            WebElement nuovaButton = driver.findElement(By.xpath("//input[@value='Nuova']"));
            nuovaButton.click();
            WebElement id = driver.findElement(By.xpath("//input[@id='id']"));
            WebElement nazione = driver.findElement(By.cssSelector("input[name='nazione']"));
            WebElement aggiungiButton = driver.findElement(By.xpath("//button[@class='btn']"));
            aggiungiButton.click();
            WebElement nome = driver.findElement(By.xpath("//td[@contenteditable='true']"));
            id.sendKeys("IT");
            nazione.sendKeys("ITALIA");
            nome.sendKeys(("6XL"));

            // Trova il pulsante di ricerca utilizzando il suo attributo di classe
            WebElement salvaButton = driver.findElement(By.cssSelector("input[value='Salva']"));
            salvaButton.click();

            try {

                // Attendi che l'elemento con id "fas fa-plus fa-2x" sia visibile
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@value='Nuova']")));

                // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
                System.out.println("Inserimento effettuato");
            } catch (TimeoutException e) {
                // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
                // trovato entro il tempo specificato
                System.out.println("Inserimento non effettuato");
                fail("Inserimento non effettuato");
            }

          
        }

        @Test
        @Order(3)
        void verificaCampo_id_nazione(){
            WebElement tagliaButton = driver.findElement(By.id("dati"));
            // Seleziona la riga di dati sulla base dell'ID
            WebElement row = driver.findElement(By.id("dati"));

            // Ottieni tutti i td in quella riga
            List<WebElement> cells = row.findElements(By.tagName("td"));

            // Ora che hai i td, puoi ottenere il testo da ognuno di essi
            String id_ver = cells.get(0).getText();
            String nome_ver = cells.get(1).getText();
            // Verifica i valori
            if (id_ver.equals("IT") && nome_ver.equals("ITALIA") ) {
                tagliaButton.click();
                System.out.println("Test passed");
            } else {
                System.out.println("Test failed");
            }

        }
        @Test
        @Order(4)
        void annullaRicerca() {
            WebElement annullaButton = driver.findElement(By.xpath("//input[@value='Annulla']"));
            annullaButton.click();
            try {
                // Attendi che l'elemento con id "fas fa-plus fa-2x" sia visibile
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/RichiediCercaArticolo']//div[@class='service']//i[@class='fas fa-search fa-2x']")));

                // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
                System.out.println("Uscita effettuata");
            } catch (TimeoutException e) {
                // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
                // trovato entro il tempo specificato
                System.out.println("Uscita non effettuata");
                fail("Uscita non effettuata");
            }

            // Aggiungi una pausa di 2 secondi
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        void entryTaglie(){
        WebElement taglieButton = driver.findElement(By.xpath("//h4[normalize-space()='Tabelle Taglie']"));
        taglieButton.click();
        }
    }

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    class Colore {
        @BeforeEach
        void accessoTotale() {
            entryAnagrafica();
            entryColore();
        }
        
        @Test
        @Order(1)
        void inserisciColore(){
            WebElement nome = driver.findElement(By.xpath("//input[@id='nome']"));
            nome.sendKeys("ANTRACITE");
            WebElement salvaButton = driver.findElement(By.xpath("//input[@value='Salva']"));
            salvaButton.click();
        }

        @Test
        @Order(2)
        void eliminaColore(){
            
            // Trova tutti gli elementi della tabella che hanno come ID "dati"
            List<WebElement> righe = driver.findElements(By.xpath("//tr[@id='dati']"));

            // Itera attraverso le righe della tabella
            for (WebElement riga : righe) {
                // Ottieni il valore della riga
                String valore = riga.getText();

                // Verifica se il valore è "ANTRACITE"
                if (valore.equals("ANTRACITE")) {
                    WebElement eliminaButton = driver.findElement(By.cssSelector("body > div:nth-child(2) > div:nth-child(2) > table:nth-child(1) > tbody:nth-child(1) > tr:nth-child(2) > td:nth-child(2) > button:nth-child(1)"));
                    eliminaButton.click();
                }else {
                System.out.println("Colore non trovato");
                }
            }
        }

        @Test
        @Order(5)
        void annullaRicerca() {
            WebElement annullaButton = driver.findElement(By.xpath("//input[@value='Annulla']"));
            annullaButton.click();
            try {
                // Attendi che l'elemento con id "fas fa-plus fa-2x" sia visibile
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/RichiediCercaArticolo']//div[@class='service']//i[@class='fas fa-search fa-2x']")));

                // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
                System.out.println("Uscita effettuata");
            } catch (TimeoutException e) {
                // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
                // trovato entro il tempo specificato
                System.out.println("Uscita non effettuata");
                fail("Uscita non effettuata");
            }

            // Aggiungi una pausa di 2 secondi
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        
        void entryColore(){
            WebElement coloreButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[2]/a[2]/div/i"));
            coloreButton.click();
        }
    }

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    class Reparti {
        @BeforeEach
        void accessoTotale() {
            entryAnagrafica();
            entryReparti();
        }

        @Test
        @Order(1)
        void nuovoRep(){
            WebElement nome = driver.findElement(By.id("nome"));
            nome.sendKeys("CUCINA");
            //salvo elemento
            WebElement salva = driver.findElement(By.xpath("//input[@value='Salva']"));
            salva.click();
        }

        @Test
        @Order(2)
        void eliminaRep() {
            // Ottieni tutti gli elementi della tabella che hanno come ID "dati"
            List<WebElement> righe = driver.findElements(By.xpath("//tr[@id='dati']"));

            // Itera attraverso le righe della tabella
            for (WebElement riga : righe) {
                // Ottieni il valore della colonna "Nome" della riga corrente
                WebElement nomeColonna = riga.findElement(By.xpath("./td[1]"));
                String valoreNome = nomeColonna.getText();

                // Verifica se il valore "Nome" è "CUCINA"
                if (valoreNome.equals("CUCINA")) {
                    // Trova il bottone "Elimina" nella riga corrente
                    WebElement eliminaButton = riga.findElement(By.xpath("./td[2]/button"));
                    eliminaButton.click();
                    break; // Esci dal ciclo dopo aver eliminato la riga desiderata
                }
            }
        }

        @Test
        @Order(5)
        void annullaRicerca() {
            WebElement annullaButton = driver.findElement(By.xpath("//input[@value='Annulla']"));
            annullaButton.click();
            try {
                // Attendi che l'elemento con id "fas fa-plus fa-2x" sia visibile
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/RichiediCercaArticolo']//div[@class='service']//i[@class='fas fa-search fa-2x']")));

                // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
                System.out.println("Uscita effettuata");
            } catch (TimeoutException e) {
                // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
                // trovato entro il tempo specificato
                System.out.println("Uscita non effettuata");
                fail("Uscita non effettuata");
            }

            // Aggiungi una pausa di 2 secondi
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        void entryReparti() {
            WebElement repartiButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div/div[3]/a[1]/div/i"));
            repartiButton.click();
        }
    }
    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    class Raggruppamenti {
        @BeforeEach
        void accessoTotale() {
            entryAnagrafica();
            entryRagg();
        }

        @Test
        @Order(1)
        void inserisciTipo(){
            WebElement nome = driver.findElement(By.id("nome"));
            nome.sendKeys("POLO");
            // Trova l'elemento del menu a tendina
            WebElement menu = driver.findElement(By.xpath("//*[@id=\"reparto\"]"));
            // Crea un oggetto Select basato sull'elemento del menu a tendina
            Select select = new Select(menu);
            // Seleziona un'opzione in base al valore
            select.selectByValue("1");

            WebElement salvaButton = driver.findElement(By.xpath("//input[@value='Salva']"));
            salvaButton.click();
        }

        @Test
        @Order(2)
        void eliminaTipo(){            
            // Trova tutti gli elementi della tabella che hanno come ID "dati"
             List<WebElement> righe = driver.findElements(By.xpath("//table[@class='table']/tbody/tr"));

            // Itera attraverso le righe della tabella
            for (int i = 0; i < righe.size(); i++) {
                // Aggiorna la lista delle righe ad ogni iterazione
                righe = driver.findElements(By.xpath("//table[@class='table']/tbody/tr"));

                // Ottieni il valore della colonna "Nome" della riga corrente
                WebElement riga = righe.get(i);
                // Ottieni il valore della colonna "Nome" della riga
                WebElement nomeColonna = riga.findElement(By.xpath(".//td[1]"));
                String valoreNome = nomeColonna.getText();

                // Verifica se il valore "Nome" è "POLO"
                if (valoreNome.equals("POLO")) {
                    // Trova il bottone "Elimina" nella riga corrente
                    WebElement eliminaButton = riga.findElement(By.xpath(".//button[contains(text(), 'Elimina')]"));
                    eliminaButton.click();
                    }else {
                    System.out.println("Tipo non trovato");
                }
            }
        }

        @Test
        @Order(3)
        void annullaRicerca() {
            WebElement annullaButton = driver.findElement(By.xpath("//input[@value='Annulla']"));
            annullaButton.click();
            try {
                // Attendi che l'elemento con id "fas fa-plus fa-2x" sia visibile
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/RichiediCercaArticolo']//div[@class='service']//i[@class='fas fa-search fa-2x']")));

                // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
                System.out.println("Uscita effettuata");
            } catch (TimeoutException e) {
                // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
                // trovato entro il tempo specificato
                System.out.println("Uscita non effettuata");
                fail("Uscita non effettuata");
            }

            // Aggiungi una pausa di 2 secondi
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        void entryRagg(){
            WebElement raggruppamentiButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[3]/a[2]/div/i"));
            raggruppamentiButton.click();
        }
    }

    @Nested
    @TestMethodOrder(OrderAnnotation.class)
    class Categorie {
        @BeforeEach
        void accessoTotale() {
            entryAnagrafica();
            entryCategorie();
        }

        @Test
        @Order(1)
        void inserisciNuovaCat(){
            WebElement nome = driver.findElement(By.id("nome"));
            nome.sendKeys("PANTALONCINI");
            // Trova l'elemento del menu a tendina
            WebElement menu = driver.findElement(By.xpath("//*[@id=\"raggruppamento\"]"));
            // Crea un oggetto Select basato sull'elemento del menu a tendina
            Select select = new Select(menu);
            // Seleziona un'opzione in base al valore
            select.selectByValue("17");

            WebElement salvaButton = driver.findElement(By.xpath("//*[@id=\"mask\"]/form/div[3]/div[3]/input"));
            salvaButton.click();
        }

        @Test
        @Order(2)
        void eliminaElemento() {
            List<WebElement> righe = driver.findElements(By.xpath("//tr[@id='dati']"));

            for (WebElement riga : righe) {
                WebElement nomeColonna = riga.findElement(By.xpath(".//td[1]"));
                String valore = nomeColonna.getText();

                if (valore.equals("PANTALONCINI")) {
                    WebElement eliminaButton = riga.findElement(By.xpath(".//td[3]/button"));
                    eliminaButton.click();
                    break; // Esci dal ciclo dopo aver eliminato l'elemento desiderato
                } else {
                    System.out.println("Elemento non trovato");
                }
            }
        }

        @Test
        @Order(3)
        void annullaRicerca() {
            WebElement annullaButton = driver.findElement(By.xpath("//input[@value='Annulla']"));
            annullaButton.click();
            try {
                // Attendi che l'elemento con id "fas fa-plus fa-2x" sia visibile
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/RichiediCercaArticolo']//div[@class='service']//i[@class='fas fa-search fa-2x']")));

                // Se siamo arrivati qui senza errori possiamo accedere alle altre operazioni
                System.out.println("Uscita effettuata");
            } catch (TimeoutException e) {
                // Se si verifica un'eccezione di timeout, significa che l'elemento non è stato
                // trovato entro il tempo specificato
                System.out.println("Uscita non effettuata");
                fail("Uscita non effettuata");
            }

            // Aggiungi una pausa di 2 secondi
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        void entryCategorie(){
            WebElement categorieButton = driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[3]/a[3]/div/i"));
            categorieButton.click();
        }
    }
}
