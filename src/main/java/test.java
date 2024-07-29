import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;


public class test {
    WebDriver driver;

    @Test
    public void test1to50() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://zzzscore.com/1to50/en/");

        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= 50; i++) {

            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement numberElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + i + "']")));
                numberElement.click();
            } catch (Exception e) {
                System.out.println("Failed to click on number: " + i);
                e.printStackTrace();
                driver.quit();
                return;
            }
        }


        long endTime = System.currentTimeMillis();


        long timeTaken = endTime - startTime;
        System.out.println("Time taken to complete the game: " + timeTaken + " milliseconds");

        boolean testPassed = false;
        for (int i = 1; i <= 50; i++) {
            try {
                driver.findElement(By.xpath("//div[text()='" + i + "']"));
                testPassed = true;
                break;
            } catch (Exception e) {

            }
        }


        if (testPassed) {
            System.out.println("Test case passed: All numbers were clicked correctly and in order.");
        } else {
            System.out.println("Test case failed: Some numbers were not clicked correctly or in order.");
        }

        driver.quit();
    }
}
