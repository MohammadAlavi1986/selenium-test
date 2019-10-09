package me.smash;

import static org.assertj.core.api.Assertions.assertThat;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
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
  public void setUp() {
    webDriver = new ChromeDriver();
    webDriver.get("http://demo.guru99.com/v4/");

    PageFactory.initElements(webDriver, this);
  }

  @AfterTest
  public void tearDown() {
    webDriver.quit();
  }

  @Test
  public void test1() {
    username.sendKeys("mngr227463");
    password.sendKeys("ajAzavU");
    btnLogin.submit();

    assertThat(webDriver.findElement(By.cssSelector(".heading3 > td")).getText())
        .isEqualTo("Manger Id : mngr227463");
  }

}
