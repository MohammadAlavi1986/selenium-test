package me.smash;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest {

  private WebDriver webDriver;

  @FindBy(name = "uid")
  private WebElement username;
  @FindBy(name = "password")
  private WebElement password;
  @FindBy(name = "btnLogin")
  private WebElement btnLogin;

  @BeforeTest
  @Parameters("browser")
  public void setUp(String browser) {
    switch (browser) {
      case "firefox":
        webDriver = new FirefoxDriver();
        break;
      case "chrome":
        webDriver = new ChromeDriver();
        break;
      default:
        throw new IllegalArgumentException("unsupported browser '" + browser + "'");
    }

    webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    webDriver.get("http://demo.guru99.com/v4/");

    PageFactory.initElements(webDriver, this);
  }

  @AfterTest
  public void tearDown() {
    webDriver.quit();
  }

  @Test
  public void test1() throws IOException {
    username.sendKeys("mngr227463");
    password.sendKeys("ajAzavU");

    File screenshot =((TakesScreenshot)webDriver).getScreenshotAs(OutputType.FILE);
    try(FileOutputStream fos = new FileOutputStream("screenshot.png")) {
      Files.copy(Paths.get(screenshot.getAbsolutePath()), fos);
    }
    btnLogin.submit();

    assertThat(webDriver.findElement(By.cssSelector(".heading3 > td")).getText())
        .isEqualTo("Manger Id : mngr227463");
  }

}
