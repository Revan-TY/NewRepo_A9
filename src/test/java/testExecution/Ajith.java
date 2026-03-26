package testExecution;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Ajith {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//span[@role='button']")).click();
		driver.findElement(By.name("q")).sendKeys("Poco Mobiles");
		driver.findElement(By.xpath("(//*[name()and@fill='none'])[3]")).click();
		driver.findElement(By.xpath("(//div[@class='RG5Slk'])[2]")).click();
		
		Set<String> tabs = driver.getWindowHandles();
		Iterator<String> alltabs = tabs.iterator();
		alltabs.next();
		String tab_2 = alltabs.next();
		WebDriver window = driver.switchTo().window(tab_2);
		System.out.println(window.getTitle());
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//*[name()and@fill='none'and@height='24'])[9]")).click();
		driver.findElement(By.xpath("//img[@alt='Cart']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement buy = driver.findElement(By.xpath("(//div[.='Place Order '])[3]"));
		wait.until(ExpectedConditions.visibilityOf(buy));
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		String s = d.toString().replace(":", "_");
		File perm = new File("./ScreenShot/flipkartCartPage" + s + ".png");
		FileHandler.copy(temp, perm);
		driver.quit();
	}

}
