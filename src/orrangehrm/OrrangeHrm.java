package orrangehrm;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OrrangeHrm {
	WebDriver driver;
	@BeforeClass
	public void startBrowser()
	{
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("===Browser started====");
	}
	@Test
public void startApp()
{
	
	driver.get("http://opensource.demo.orangehrmlive.com/index.php/auth/login");
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	Assert.assertTrue(driver.getCurrentUrl().contains("auth/login"));
	System.out.println("App started");
}
	@Test(dependsOnMethods="startApp")
public void loginApp() throws InterruptedException
	{
		driver.findElement(By.xpath("/.//*[@id='txtUsername']")).sendKeys("Admin");
        driver.findElement(By.xpath(".//*[@id='txtPassword']")).sendKeys("admin");
        Thread.sleep(10000);
        driver.findElement(By.xpath("//*[@id='btnLogin']")).click();
        Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='menu_admin_viewAdminModule']/b")).isDisplayed());
        System.out.println("Logged in!!");

	}
	@Test(dependsOnMethods="loginApp")
public void logoutApp() throws InterruptedException
	{
		driver.findElement(By.xpath(".//*[@id='welcome']")).click();
		 Thread.sleep(10000);
		driver.findElement(By.xpath("//a[text()='Logout']")).click();
		Assert.assertTrue(driver.findElement(By.xpath(".//*[@id='txtUsername']")).isDisplayed());
		System.out.println("Logout successfull!!");
	}
	@AfterClass
	public void closeApp()
	{
		driver.quit();
	}
}
