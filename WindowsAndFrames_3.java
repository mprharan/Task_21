package task_21;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class WindowsAndFrames_3 {

	public static WebDriver driver = null;

	@Test
	public void task() throws InterruptedException {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();

		driver.get("http://the-internet.herokuapp.com/nested_frames");
		
		WebElement topframe = driver.findElement(By.xpath("//frame[@name='frame-top']"));
		driver.switchTo().frame(topframe);
		
        List<WebElement> frames = driver.findElements(By.tagName("frame"));
        int numberOfFrames = frames.size();
        
        System.out.println("Number of frames in the middle frame: " + numberOfFrames);
		
		WebElement leftframe = driver.findElement(By.name("frame-left"));
		driver.switchTo().frame(leftframe);
		
		WebElement leftFrameMSG = driver.findElement(By.xpath("//body[contains(.,'LEFT')]"));
		
		assertEquals(leftFrameMSG.getText(),"LEFT");
		
		driver.switchTo().parentFrame();
		
		WebElement middleFrame = driver.findElement(By.name("frame-middle"));
		driver.switchTo().frame(middleFrame);
		
		WebElement middleFrameMSG = driver.findElement(By.id("content"));
		
		assertEquals(middleFrameMSG.getText(),"MIDDLE");
		
		driver.switchTo().parentFrame();
		
		WebElement rightFrame = driver.findElement(By.name("frame-right"));
		driver.switchTo().frame(rightFrame);
		
		WebElement rightFrameMSG = driver.findElement(By.xpath("//body[contains(.,'RIGHT')]"));
		
		assertEquals(rightFrameMSG.getText(),"RIGHT");
		
		driver.switchTo().parentFrame();
		
		
		WebElement bottomFrame = driver.findElement(By.name("frame-bottom"));
		driver.switchTo().frame(bottomFrame);
		
		WebElement bottomFrameMSG = driver.findElement(By.xpath("//body[contains(.,'BOTTOM')]"));
		
		assertEquals(bottomFrameMSG.getText(),"BOTTOM");
		
		driver.switchTo().parentFrame();
		
		driver.switchTo().frame(topframe);
		
		assertEquals(driver.getTitle(),"Frames");
		
		Thread.sleep(2000);
		
		driver.quit();
		
	}
	
}
