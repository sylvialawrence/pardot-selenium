package junitpack;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import utility.Constant;

public class junittest1 {
 
 WebDriver driver = new FirefoxDriver();

 @Test
 public void test() throws Exception {
  try 
  {
	  //Maximize window
	  driver.manage().window().maximize();
	  
	  /*Step 1: Login to Pardot*/
	  
	  driver.get("https://pi.pardot.com");
	  driver.findElement(By.xpath("//input[@name='email_address']")).sendKeys("pardot.applicant@pardot.com");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Applicant2012");
	  driver.findElement(By.xpath("//input[@name='commit']")).submit();
	  System.out.print("Logged in to Pardot \n");
	  
	  /*End Step 1*/
	  
	  /*Step 2: Create list step by step with 2 second gap between each step so the action is complete before the next action starts*/
	  
	  //Hover over to Marketing->Segmentation->Lists 
	  Actions builder = new Actions(driver); 
      WebElement mainmenu1 = driver.findElement(By.id("mark-tog"));
      builder.moveToElement(mainmenu1 ).build().perform();
      Thread.sleep(500); //wait for 0.5 second before proceeding. This allows sub menu to appear properly before trying to click on it
      WebElement submenu1 =  driver.findElement(By.xpath("//ul[@id='dropmenu-marketing']//li[contains(@class,'dropdown-submenu')]//a[contains(@href,'segmentation')]")); //Find the submenu
      builder.moveToElement(submenu1).build().perform();
      Thread.sleep(500);
      WebElement submenu2=  driver.findElement(By.xpath("//ul[@id='dropmenu-marketing']//li[contains(@class,'dropdown-submenu')]//ul//li//a[contains(@href,'/list')]")); //Find the 2nd level submenu
      builder.moveToElement(submenu2).click().build().perform();
      Thread.sleep(2000);
      
      //Click on 'Add List'
      driver.findElement(By.xpath("//div[contains(@class,'module')]//div[contains(@class,'header clearfix')]//span[contains(@class,'link_to_create pull-right')]//a")).click();	
      Thread.sleep(2000);
      
      //Type in list name 
      driver.findElement(By.xpath("//div[contains(@class,'controls')]//input[contains(@name,'name')]")).sendKeys(Constant.listname);
      Thread.sleep(2000); //2 seconds for user to see what was typed in
      
      //Choose folder as 'Uncategorized'
	  driver.findElement(By.xpath("//div[contains(@class,'controls')]//div[contains(@class,'input-prepend input-append folder-chooser')]//button")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//input[contains(@class,'ember-view ember-text-field filter-by')]")).sendKeys("Uncategorized");
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[contains(@class,'well well-small media folder-row clearfix no-select')]")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.id("select-asset")).click();
	  Thread.sleep(2000);
	  
	  //Save information and create list
	  driver.findElement(By.id("save_information")).submit();
	  
	  //Print message
	  System.out.print("Created a list named '"+Constant.listname+"'\n");
	  Thread.sleep(2000);
	  
	  /*End Step 2*/
	  
	  /*Step 3: Try re-creating list with the same name*/
	  
	  //Hover over to Marketing->Segmentation->Lists again
	  Actions action = new Actions(driver); 
      WebElement mainmenuRepeat = driver.findElement(By.id("mark-tog"));
      action.moveToElement(mainmenuRepeat).build().perform();
      Thread.sleep(500); //wait for 0.5 second before proceeding. This allows sub menu to appear properly before trying to click on it
      WebElement submenu1Repeat =  driver.findElement(By.xpath("//ul[@id='dropmenu-marketing']//li[contains(@class,'dropdown-submenu')]//a[contains(@href,'segmentation')]")); //Find the submenu
      action.moveToElement(submenu1Repeat).build().perform();
      Thread.sleep(500);
      WebElement submenu2Repeat =  driver.findElement(By.xpath("//ul[@id='dropmenu-marketing']//li[contains(@class,'dropdown-submenu')]//ul//li//a[contains(@href,'/list')]")); //Find the 2nd level submenu
      action.moveToElement(submenu2Repeat).click().build().perform();
      Thread.sleep(2000);
      
      //Click on 'Add List'	
      driver.findElement(By.xpath("//div[contains(@class,'module')]//div[contains(@class,'header clearfix')]//span[contains(@class,'link_to_create pull-right')]//a")).click();
      Thread.sleep(2000);
      
      //Type in list name 
      driver.findElement(By.xpath("//div[contains(@class,'controls')]//input[contains(@name,'name')]")).sendKeys(Constant.listname);
      Thread.sleep(2000); //2 seconds for user to see what was typed in
      
      //Choose folder as 'Uncategorized'
	  driver.findElement(By.xpath("//div[contains(@class,'controls')]//div[contains(@class,'input-prepend input-append folder-chooser')]//button")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//input[contains(@class,'ember-view ember-text-field filter-by')]")).sendKeys("Uncategorized");
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[contains(@class,'well well-small media folder-row clearfix no-select')]")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.id("select-asset")).click();
	  Thread.sleep(2000);
	  
	  //Save information and create list
	  driver.findElement(By.id("save_information")).submit();
	  Thread.sleep(2000);
	  
	  //This will capture error message
	  String actual_msg=driver.findElement(By.id("error_for_name")).getText();
	  if (actual_msg!= null) {System.out.print("Error re-creating list named '"+Constant.listname+"'\n");  }
	  Thread.sleep(2000);
	  
	  //Close the pop-up by clicking on cancel
	  driver.findElement(By.xpath("//form[@id='dynamicList-form']//div[contains(@class,'modal-footer')]//a")).click();
	  
	  /*End Step 3*/
	  
	  /*Step 4: Rename the original list*/
	  
	  //Go to original list to rename
	  String url = driver.findElement(By.linkText(Constant.listname)).getAttribute("href");
	  driver.navigate().to(url);
	  driver.findElement(By.linkText("Edit")).click();
	  Thread.sleep(2000);
	  
	  //Rename the list
	  driver.findElement(By.id("name")).sendKeys("-renamed");
	  driver.findElement(By.id("save_information")).submit();
	  Thread.sleep(2000);
	  System.out.print("Original list renamed\n");
	  
	  /*End Step 4*/
	  
	  /*Step 5: Create a list with the original name*/
	  
	  //Hover over to Marketing->Segmentation->Lists again
	  Actions navigate = new Actions(driver); 
      WebElement mainmenuRepeat2 = driver.findElement(By.id("mark-tog"));
      navigate.moveToElement(mainmenuRepeat2).build().perform();
      Thread.sleep(500); //wait for 0.5 second before proceeding. This allows sub menu to appear properly before trying to click on it
      WebElement submenu1Repeat2 =  driver.findElement(By.xpath("//ul[@id='dropmenu-marketing']//li[contains(@class,'dropdown-submenu')]//a[contains(@href,'segmentation')]")); //Find the submenu
      navigate.moveToElement(submenu1Repeat2).build().perform();
      Thread.sleep(500);
      WebElement submenu2Repeat2 =  driver.findElement(By.xpath("//ul[@id='dropmenu-marketing']//li[contains(@class,'dropdown-submenu')]//ul//li//a[contains(@href,'/list')]")); //Find the 2nd level submenu
      navigate.moveToElement(submenu2Repeat2).click().build().perform();
      Thread.sleep(2000);
	  
      //Click on 'Add List'
      driver.findElement(By.xpath("//div[contains(@class,'module')]//div[contains(@class,'header clearfix')]//span[contains(@class,'link_to_create pull-right')]//a")).click();	
      Thread.sleep(2000);
      
      //Type in list name 
      driver.findElement(By.xpath("//div[contains(@class,'controls')]//input[contains(@name,'name')]")).sendKeys(Constant.listname);
      Thread.sleep(2000); //2 seconds for user to see what was typed in
      
      //Choose folder as 'Uncategorized'
	  driver.findElement(By.xpath("//div[contains(@class,'controls')]//div[contains(@class,'input-prepend input-append folder-chooser')]//button")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//input[contains(@class,'ember-view ember-text-field filter-by')]")).sendKeys("Uncategorized");
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[contains(@class,'well well-small media folder-row clearfix no-select')]")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.id("select-asset")).click();
	  Thread.sleep(2000);
	  
	  //Save information and create list
	  driver.findElement(By.id("save_information")).submit();
	  Thread.sleep(2000);
	  
	  driver.navigate().to("https://pi.pardot.com/list");
	  Thread.sleep(2000);
	  String listNameCheck = driver.findElement(By.linkText(Constant.listname)).getText();
	  if (listNameCheck == Constant.listname)
	  { System.out.print("Error creating list with original name\n");  } else { System.out.print("Created list with original name\n");  }
	  
	  /*End Step 5*/
	  
  }catch (Exception e)
  {
	  System.out.print(e);
	  throw(e);
  }
 }
}