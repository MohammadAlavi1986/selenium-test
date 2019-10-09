package pl.codeleak.demos.sbt;

import java.io.IOException;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Main {

  public static void main(String[] args) throws IOException, InterruptedException {
    System.setProperty("webdriver.gecko.driver","/Users/mohammad/Downloads/geckodriver");
    DesiredCapabilities capabilities = DesiredCapabilities.firefox();
    capabilities.setCapability("marionette",true);
    FirefoxDriver driver= new FirefoxDriver(capabilities);


//    ChromeDriver driver = new ChromeDriver();

    driver.get("http://demo.guru99.com/test/tooltip.html");

    WebElement download = driver.findElement(By.xpath("//*[@id=\"download_now\"]"));

    WebDriverWait wait = new WebDriverWait(driver, 3);
    new Actions(driver).moveToElement(download).build().perform();
    System.out.println("link: " + driver.findElement(By.xpath("//*[@id=\"demo_content\"]/div/div/div/a")).getText());

    Thread.sleep(1000);
    driver.close();
    driver.quit();



/*
    System.out.println("mainWindow: " + mainWindow);
    windows.forEach(System.out::println);
*/


  }

  private static void test1() {
  /*
      WebDriver webDriver = new RemoteWebDriver(URI.create("http://localhost:4444").toURL(),
          DesiredCapabilities.firefox());*/

    ChromeDriver driver = new ChromeDriver();
//    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    driver.get("http://localhost:8080/");
    WebElement username = driver.findElement(By.name("username"));
    WebElement password = driver.findElement(By.name("password"));
    WebElement submit = driver.findElement(By.tagName("button"));

    username.sendKeys("greg");
    password.sendKeys("turnquist");
    submit.submit();

    WebElement managername = driver.findElement(By.id("managername"));
    System.out.println("managerName: " + managername.getText());

    System.out.println(driver.getCurrentUrl());
    WebDriverWait wait = new WebDriverWait(driver, 3);

    WebElement element = driver.findElement(By.cssSelector("div > input"));
    wait.until(ExpectedConditions.elementToBeClickable(element));

    element.clear();
    element.sendKeys("10");
  }
}
