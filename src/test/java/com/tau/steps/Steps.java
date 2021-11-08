package com.tau.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

public class Steps {

		private WebDriver driver;
		
		By ContextMenu = By.xpath("//*[@id=\"myGrid\"]/div/div[2]/div[2]/div[1]/div[2]/div/div[2]/div[2]/div[3]/span/span");
	    By FilterButtonLanguage = By.xpath("//*[@id=\"example-wrapper\"]/div[3]/div/div[1]/span[2]/span");
	    By SearchTextFieldLanguage = By.id("ag-413-input");
	    String LanguageKey = "France";
	    By LanguageTableValue = By.xpath("//*[@id=\"myGrid\"]/div/div[2]/div[2]/div[3]/div[2]/div/div/div[1]/div[2]");

	    //Test2
	    By CountryMenu = By.xpath("//*[@id=\"myGrid\"]/div/div[2]/div[2]/div[1]/div[2]/div/div[2]/div[3]/div[3]/span/span");
	    By FilterButtonCountry = By.xpath("//*[@id=\"grid-wrapper\"]");
	    By SearchTextFieldCountry = By.id("ag-319-input");
	    String CountryKey = "Belgium";
	    By CountryTableValue = By.xpath("//*[@id=\"myGrid\"]/div/div[2]/div[2]/div[3]/div[2]/div/div/div/div[3]");

	    By JanValue = By.xpath("//*[@id=\"myGrid\"]/div/div[2]/div[2]/div[3]/div[2]/div/div/div/div[6]");
	    String JanNumber = "66,433" ;
		
     @Before()
		public void SetUp() {
			System.out.print("Start AG-Grid Project");
	        System.setProperty("webdriver.chrome.driver","C:\\Webdriver Resources\\chromedriver_win32\\chromedriver.exe");
	        this.driver = new ChromeDriver();
	        final ChromeOptions options = new ChromeOptions();
	        options.addArguments("--incognito");
	        this.driver.manage().window().maximize();
	        String AGridURL = new String ("https://www.ag-grid.com/example.php");
	        this.driver.navigate().to(AGridURL);
		}
		
	@Given("Making Language Filter on “French”")
	@Given("Making Country Filter on “Belgium”")
	public void Language_Country_Filter() {
		
	    WebElement ContextMenuHover = driver.findElement(ContextMenu);
        Actions action = new Actions(driver);
        action.moveToElement(ContextMenuHover);
        action.click().build().perform();

        //select the filter icon
        this.driver.findElement(FilterButtonLanguage).click();

        //write “French” in the search criteria field
        WebElement TextBoxLanguage = driver.findElement(SearchTextFieldLanguage);
        TextBoxLanguage.sendKeys(Keys.ENTER);
        this.driver.findElement(SearchTextFieldLanguage).sendKeys(LanguageKey);	
	
		WebElement CountryMenuHover = driver.findElement(CountryMenu);
	    action.moveToElement(CountryMenuHover);
	    action.click().build().perform();
	
	    //select the filter icon
	    this.driver.findElement(FilterButtonCountry).click();
	
	    //write “Belgium”
	    WebElement TextBoxCountry = driver.findElement(SearchTextFieldCountry);
	    this.driver.findElement(SearchTextFieldCountry).sendKeys(CountryKey);
	    TextBoxCountry.sendKeys(Keys.ENTER);
	
}
	
	
	@When("I verify Selected data on “French” and “Belgium”")
	public void verify_Selected_data_on_French_and_Belgium() {
		
		String LanguageTableText;
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(LanguageTableValue));
        LanguageTableText = driver.findElement(LanguageTableValue).getText();
        Assert.assertEquals(LanguageTableText,LanguageKey);
        
      //Assert Country result is Belgium
        String CountryTableText;
        wait.until(ExpectedConditions.visibilityOfElementLocated(CountryTableValue));
        CountryTableText = driver.findElement(CountryTableValue).getText();
        Assert.assertEquals(CountryTableText,CountryKey);
        
	}
	
	@Then("Jan row is appeare with right value")
	public void Jan_row_is_appeare_with_right_value() {
		
		String JanActualText ;
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(JanValue));
        JanActualText = driver.findElement(JanValue).getText();
        Assert.assertEquals(JanActualText,JanNumber);
	}
	
	@After()
	public void Clean() {
		System.out.println("CleanUp");
        this.driver.close();
	}

}
