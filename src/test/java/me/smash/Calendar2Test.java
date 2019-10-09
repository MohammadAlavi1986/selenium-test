package me.smash;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Calendar2Test {

  private WebDriver driver;

  @FindBy(xpath = "//*[@id=\"example\"]/div/span/span/span[2]/span[1]")
  private WebElement dateBtn;
  @FindBy(xpath = "//*[@id=\"example\"]/div/span/span/span[2]/span[2]")
  private WebElement timeBtn;
  @FindBy(xpath = "//*[@class=\"k-widget k-calendar\"]/div[1]/a[3]")
  private WebElement nextBtn;
  @FindBy(xpath = "//*[@class=\"k-widget k-calendar\"]/div[1]/a[1]")
  private WebElement prevBtn;


  @BeforeTest
  public void setUp() throws IOException {
    driver = new ChromeDriver();
/*
    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    capabilities.setCapability("ignoreZoomSetting", true);
    capabilities.setPlatform(Platform.WINDOWS);


    driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
*/

    driver.get("https://demos.telerik.com/kendo-ui/datetimepicker/index");
    PageFactory.initElements(driver, this);
/*
    nextBtn = driver.findElement(By.xpath("//*[@class=\"k-widget k-calendar\"]/div[1]/a[3]"));
    prevBtn = driver.findElement(By.xpath("//*[@class=\"k-widget k-calendar\"]/div[1]/a[1]"));
*/
  }

  @Test
  public void testCalendar() {
    dateBtn.click();

/*
    WebDriverWait wait = new WebDriverWait(driver, 3);
    wait.until(ExpectedConditions.elementToBeClickable(prevBtn));
*/

/*
    prevBtn.click();
    prevBtn.click();
    prevBtn.click();
*/

    File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try (FileOutputStream fos = new FileOutputStream("test.png")) {
      Files.copy(Paths.get(screenshot.getAbsolutePath()), fos);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  @AfterTest
  public void tearDown() {
    driver.quit();
  }
}
