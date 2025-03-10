package login;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class autoLogin {
	
	@Test
    public void verifyTheAccount() {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(options);

        // Maximize window
        driver.manage().window().maximize();

        // Set implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

        // Open the Website
        driver.get("https://tutorialsninja.com/demo/");

        // Click on "My Account"
        driver.findElement(By.xpath("//span[text()='My Account']")).click();

        // Click on "Register"
        driver.findElement(By.linkText("Register")).click();

        // Fill out registration form
        driver.findElement(By.id("input-firstname")).sendKeys("Ram");
        driver.findElement(By.id("input-lastname")).sendKeys("Yadav");
        driver.findElement(By.id("input-email")).sendKeys(newEmail());
        driver.findElement(By.id("input-telephone")).sendKeys("7267928537");
        driver.findElement(By.id("input-password")).sendKeys("12345");
        driver.findElement(By.id("input-confirm")).sendKeys("12345");
        driver.findElement(By.name("agree")).click();

        // Wait for "Continue" button and click
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='Continue']")));
        continueButton.click();

        // Close browser
       // driver.quit();
        
        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());
        String expectedHeading="Your Account Has Been Created!";
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='common-success']//h1")).getText(),expectedHeading);
       
        String actualProperDetails1="Congratulations! Your new account has been successfully created!";
        String actualProperDetails2="You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String actualProperDetails3="If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
        String actualProperDetails4="contact us";
        
        String ExpectedProperDetail=driver.findElement(By.id("content")).getText();
        
        Assert.assertTrue(ExpectedProperDetail.contains(actualProperDetails1));
        Assert.assertTrue(ExpectedProperDetail.contains(actualProperDetails2));
        Assert.assertTrue(ExpectedProperDetail.contains(actualProperDetails3));
        Assert.assertTrue(ExpectedProperDetail.contains(actualProperDetails4));
        
        driver.findElement(By.xpath("//a[text()='Continue']")).click();
        Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed());
        
        driver.quit();
    }
    
    public String newEmail() {
    	Date date=new Date();
		String dateString=date.toString();
		String stringWithoutSpace=dateString.replaceAll(" ","");
		String noSpaceAndColonString=stringWithoutSpace.replaceAll(":","");
		String email=noSpaceAndColonString+"@gmail.com";
		return email;
    }
    
}
