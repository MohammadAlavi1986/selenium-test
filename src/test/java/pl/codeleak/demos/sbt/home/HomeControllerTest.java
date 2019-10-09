package pl.codeleak.demos.sbt.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.codeleak.selenium.support.SeleniumTest;

@SpringBootTest(properties = "server.port=9000", webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@SeleniumTest(baseUrl = "http://localhost:9000")
public class HomeControllerTest extends AbstractTestNGSpringContextTests {

  @Autowired
  private WebDriver driver;

  private HomePage homePage;

  @BeforeMethod
  public void setUp() throws Exception {
    homePage = PageFactory.initElements(driver, HomePage.class);
  }

  @Test(groups = {"IT"})
  public void containsActuatorLinks() {
    homePage.assertThat()
        .hasActuatorLink("autoconfig", "beans", "configprops", "dump", "env", "health", "info",
            "metrics", "mappings", "trace")
        .hasNoActuatorLink("shutdown");
  }

  @Test(enabled = false)
  public void failingTest() {
    homePage.assertThat()
        .hasNoActuatorLink("autoconfig");
  }

}
