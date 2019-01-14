package it.vinmar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class SampleIntegrationTest {

	private WebDriver driver;
	private DesiredCapabilities dc = new DesiredCapabilities();
	private String buildNumber = System.getenv("BUILD_NUMBER");
	private String accessKey = System.getenv("accessKey");

	@Test
	public void testExperitest() throws MalformedURLException {

		System.out.println("#### Build number " + buildNumber);
		System.out.println("#### SEETEST_IO_ACCESS_KEY  " + accessKey);

		dc.setCapability(CapabilityType.BROWSER_NAME, "chrome");
		dc.setCapability(CapabilityType.VERSION, "Any");
		dc.setCapability("stream", "jenkins_web");
		dc.setCapability("build.number", buildNumber);
		dc.setCapability("accessKey", accessKey);
		dc.setCapability("testName", "Jenkins Demo Web");

		driver = new RemoteWebDriver(new URL(System.getenv("url")), dc);
		driver.get("https://www.experitest.com/");
		WebElement find = new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.navbar-header")));
		assertNotNull(find, "Something wrong happened!");
	}

}