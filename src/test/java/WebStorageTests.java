// import org.junit.jupiter.api.Test;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.html5.LocalStorage;
// import org.openqa.selenium.html5.SessionStorage;
// import org.openqa.selenium.html5.WebStorage;

// import static org.assertj.core.api.Assertions.assertThat;
// import static org.junit.jupiter.api.Assertions.assertEquals;

// public class WebStorageTests {

//     @Test
//     void openUrlTest() {
//         WebDriver driver = new ChromeDriver();
//         driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-storage.html");
//         assertEquals("Hands-On Selenium WebDriver with Java", driver.getTitle());
//         WebElement subtitle = driver.findElement(By.cssSelector("h5"));
//         assertEquals("Practice site", subtitle.getText());
//         driver.close();
//     }

//     @Test
//     void testWebStorage() {
//         WebDriver driver = new ChromeDriver();
//         driver.get("https://bonigarcia.dev/selenium-webdriver-java/web-storage.html");
//         WebStorage webStorage = (WebStorage) driver;

//         LocalStorage localStorage = webStorage.getLocalStorage();
//         System.out.printf("Local storage elements: {%s}\n", localStorage.size());

//         SessionStorage sessionStorage = webStorage.getSessionStorage();
//         sessionStorage.keySet()
//                 .forEach(key -> System.out.printf("Session storage: {%s}={%s}\n", key, sessionStorage.getItem(key)));
//         assertThat(sessionStorage.size()).isEqualTo(2);

//         sessionStorage.setItem("new element", "new value");
//         assertThat(sessionStorage.size()).isEqualTo(3);

//         driver.findElement(By.id("display-session")).click();
//         driver.close();
//     }
// }
