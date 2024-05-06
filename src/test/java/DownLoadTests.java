import extensions.AllureExtension;
import io.qameta.allure.Allure;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import steps.AllureSteps;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("Download files")
@Feature("DownLoadExtensions")
@ExtendWith(AllureExtension.class)
class DownLoadTests {
    WebDriver driver;
    AllureSteps allureSteps = new AllureSteps();

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    @DisplayName("Download WebDriverManager logo")
    void testDownloadWebDriverManagerLogo() throws IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/download.html");

        WebElement webdriverManagerLogoLink = driver.findElement(By.linkText("WebDriverManager logo"));
        String webdriverManagerLogoHref = webdriverManagerLogoLink.getAttribute("href");
        File webdriverManagerLogoFile = new File(".", "webdrivermanager.png");
        allureSteps.download(webdriverManagerLogoHref, webdriverManagerLogoFile);

        Allure.step("Verify WebDriverManager logo file download", () -> {
            assertThat(webdriverManagerLogoFile).exists();
            assertThat(webdriverManagerLogoFile.length()).isGreaterThan(0);
        });
    }

    @Test
    @DisplayName("Download WebDriverManager doc")
    void testDownloadWebDriverManagerDoc() throws IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/download.html");

        WebElement webdriverManagerDocLink = driver.findElement(By.linkText("WebDriverManager doc"));
        String webdriverManagerDocHref = webdriverManagerDocLink.getAttribute("href");
        File webdriverManagerDocFile = new File(".", "webdrivermanager.pdf");
        allureSteps.download(webdriverManagerDocHref, webdriverManagerDocFile);

        Allure.step("Verify WebDriverManager doc file download", () -> {
            assertThat(webdriverManagerDocFile).exists();
            assertThat(webdriverManagerDocFile.length()).isGreaterThan(0);
        });
    }

    @Test
    @DisplayName("Download Selenium-Jupiter logo")
    void testDownloadSeleniumJupiterLogo() throws IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/download.html");

        WebElement seleniumJupiterLogoLink = driver.findElement(By.linkText("Selenium-Jupiter logo"));
        String seleniumJupiterLogoHref = seleniumJupiterLogoLink.getAttribute("href");
        File seleniumJupiterLogoFile = new File(".", "selenium-jupiter.png");
        allureSteps.download(seleniumJupiterLogoHref, seleniumJupiterLogoFile);

        Allure.step("Verify Selenium-Jupiter logo file download", () -> {
            assertThat(seleniumJupiterLogoFile).exists();
            assertThat(seleniumJupiterLogoFile.length()).isGreaterThan(0);
        });
    }

    @Test
    @DisplayName("Download Selenium-Jupiter doc")
    void testDownloadSeleniumJupiterDoc() throws IOException {
        driver.get("https://bonigarcia.dev/selenium-webdriver-java/download.html");

        WebElement seleniumJupiterDocLink = driver.findElement(By.linkText("Selenium-Jupiter doc"));
        String seleniumJupiterDocHref = seleniumJupiterDocLink.getAttribute("href");
        File seleniumJupiterDocFile = new File(".", "selenium-jupiter.pdf");
        allureSteps.download(seleniumJupiterDocHref, seleniumJupiterDocFile);

        Allure.step("Verify Selenium-Jupiter doc file download", () -> {
            assertThat(seleniumJupiterDocFile).exists();
            assertThat(seleniumJupiterDocFile.length()).isGreaterThan(0);
        });
    }
}