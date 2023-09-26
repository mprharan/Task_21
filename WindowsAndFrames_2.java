package task_21;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowsAndFrames_2 {

	public static WebDriver driver = null;

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.navigate().to("https://the-internet.herokuapp.com/windows");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		String Child;
		String expected1 = "New Window";
		String expected = "The Internet";

		WebElement click = driver.findElement(By.xpath("//a[text()=\"Click Here\"]"));
		click.click();

		String Parent = driver.getWindowHandle();

		Set<String> s = driver.getWindowHandles();

		Iterator<String> Iterator = s.iterator();

		while (Iterator.hasNext()) {
			Child = Iterator.next();
			driver.switchTo().window(Child);
		}

		String actual1 = driver.getTitle();

		if (actual1.equalsIgnoreCase(expected1)) {
			System.out.println("This is child window");
		} else {
			System.out.println("This is not a child window");
		}
		driver.close();

		driver.switchTo().window(Parent);
		
		String actual = driver.getTitle();

		if (actual.equalsIgnoreCase(expected)) {
			System.out.println("This is parent window");
		} else {
			System.out.println("This is not a parent window");
		}

		driver.quit();

	}

}
