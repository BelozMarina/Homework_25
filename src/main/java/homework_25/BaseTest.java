package homework_25;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    public WebDriver driver;
    public DesiredCapabilities cap = new DesiredCapabilities();

@Parameters("browser")
    @BeforeClass
    public void setUp(String browser) throws MalformedURLException {

    Date d = new Date();
    System.out.println(browser+" " + d.toString());

        if (browser.equals("opera")) {
            cap.setPlatform(Platform.ANY);
            cap.setBrowserName("opera");
            OperaOptions options = new OperaOptions();
            options.merge(cap);
        } else if (browser.equals("firefox")) {
            cap.setPlatform(Platform.ANY);
            cap.setBrowserName("firefox");
            FirefoxOptions options = new FirefoxOptions();
            options.merge(cap);
        } else if (browser.equals("chrome")) {
            cap.setPlatform(Platform.ANY);
            cap.setBrowserName("chrome");
            ChromeOptions options = new ChromeOptions();
            options.merge(cap);
        }

        driver = new RemoteWebDriver(new URL("http:localhost:4444/wd/hub"), cap);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }

    @BeforeMethod
    public void getSite() {
        driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
    }


    @AfterClass
    public void closeDriver() {
        driver.close();
    }


}
