package junitpack;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import utility.Constant;

public class unittest2 {
 
 WebDriver driver = new FirefoxDriver();

 @Before
 public void setup () {
  driver.manage().window().maximize();
  driver.get("https://pi.pardot.com");
 }
 
 @After
 public void aftertest() {
  driver.quit(); 
 }
 
 @Test
 public void test() throws Exception {
  try 
  {  
	  /*Step 1: Login to Pardot*/
	  
	  driver.findElement(By.xpath("//input[@name='email_address']")).sendKeys("pardot.applicant@pardot.com");
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Applicant2012");
	  driver.findElement(By.xpath("//input[@name='commit']")).submit();
	  System.out.print("Logged in to Pardot \n");
	  
	  /*End Step 1*/
	  
	  /*Step 6: Create new prospect*/
	  
	  //Hover over to Prospects->Prospect List 
	  Actions prospectBuilder = new Actions(driver); 
      WebElement mainmenu = driver.findElement(By.id("pro-tog"));
      prospectBuilder.moveToElement(mainmenu).build().perform();
      Thread.sleep(500); //wait for 0.5 second before proceeding. This allows sub menu to appear properly before trying to click on it
      WebElement submenu=  driver.findElement(By.xpath("//ul[@id='dropmenu-prospects']//li//a[contains(@href,'/prospect')]")); 
      prospectBuilder.moveToElement(submenu).click().build().perform();
      Thread.sleep(500);
	  
      //Click on 'Add Prospect'
      driver.findElement(By.xpath("//a[@id='pr_link_create']")).click();
	  Thread.sleep(2000);
	  
	  //Fill in the form
	  String prospectForValue = driver.findElement(By.xpath("//label[text()='First Name']")).getAttribute("for");
	  driver.findElement(By.id(prospectForValue)).sendKeys(Constant.prospectname);
	  driver.findElement(By.xpath("//input[@id='email']")).sendKeys(Constant.prospectemail);
	  Select campaignDropdown = new Select(driver.findElement(By.id("campaign_id")));
	  campaignDropdown.selectByIndex(1);
	  Select profileDropdown = new Select(driver.findElement(By.id("profile_id")));
	  profileDropdown.selectByIndex(1);
	  driver.findElement(By.xpath("//input[@id='score']")).sendKeys(""+Constant.prospectscore);
	  
	  //Save the form
	  driver.findElement(By.name("commit")).submit();
	  Thread.sleep(2000);
	  
	  System.out.print("Created new prospect named '"+Constant.prospectname+"'\n");
	  
	  /*End Step 6*/
	  
	  /*Step 7: Add the new prospect to the newly created list*/
	  
	  driver.findElement(By.linkText("Lists")).click();
	  Thread.sleep(2000);
	  
	  //Select the list to add 
	  driver.findElement(By.linkText("Select a list to add...")).click();
	  Thread.sleep(2000);
	  
	  //Enter the list name in the search box and press enter
	  driver.findElement(By.xpath("//div[contains(@class,'chzn-search')]//input")).sendKeys(Constant.listname);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[contains(@class,'chzn-search')]//input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  
	  //Save the list membership
	  driver.findElement(By.name("commit")).submit();
	  
	  System.out.print("Added the new prospect to the newly created list\n");
	  
	  /*End Step 7*/
	  
	  /*Step 8: Ensure if prospect is added to the new list*/
	  
	  //Go to the list page 
	  driver.navigate().to("https://pi.pardot.com/list");
	  Thread.sleep(2000);
	  String url2 = driver.findElement(By.linkText(Constant.listname)).getAttribute("href");
	  driver.navigate().to(url2);
	  Thread.sleep(2000);
	  
	  //Check and assert if prospect is added to the list, otherwise print error message
	  assertTrue("Verification failed: Prospect not added to list.",Constant.prospectname.equals(driver.findElement(By.linkText(Constant.prospectname)).getText()));
	  
	  System.out.print("Checked and asserted that the new prospect has been added to the new list\n");
	  
	  /*End Step 8*/
	  
	  /*Step 9: Send text-only email to list*/
	  
	  //Hover over to Marketing->Emails->New List Email
	  Actions emailBuilder = new Actions(driver); 
      WebElement emailMainmenu = driver.findElement(By.id("mark-tog"));
      emailBuilder.moveToElement(emailMainmenu).build().perform();
      Thread.sleep(500); //wait for 0.5 second before proceeding. This allows sub menu to appear properly before trying to click on it
      WebElement emailSubmenu1 =  driver.findElement(By.xpath("//ul[@id='dropmenu-marketing']//li[contains(@class,'dropdown-submenu')]//a[contains(@href,'email')]")); //Find the submenu
      emailBuilder.moveToElement(emailSubmenu1).build().perform();
      Thread.sleep(500);
      WebElement emailSubmenu2 =  driver.findElement(By.xpath("//ul[@id='dropmenu-marketing']//li[contains(@class,'dropdown-submenu')]//ul//li//a[contains(@href,'/email/draft/edit')]")); //Find the 2nd level submenu
      emailBuilder.moveToElement(emailSubmenu2).click().build().perform();
      Thread.sleep(500);
	  
      //Fill in the form
      driver.findElement(By.id("name")).sendKeys(Constant.emailName);
      //Choose folder. I've chosen 'Uncategorized' folder
	  driver.findElement(By.xpath("//div[contains(@class,'controls')]//div[contains(@class,'input-prepend input-append folder-chooser')]//button")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//input[contains(@class,'ember-view ember-text-field filter-by')]")).sendKeys("Uncategorized");
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[contains(@class,'well well-small media folder-row clearfix no-select')]")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.id("select-asset")).click();
	  Thread.sleep(2000);
	  //Choose campaign. I've chosen my own campaign
	  driver.findElement(By.xpath("//div[contains(@class,'input-prepend input-append asset-chooser')]//button")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//input[contains(@class,'ember-view ember-text-field filter-by')]")).sendKeys("SylviaLawrence Campaign");
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[contains(@class,'well well-small media folder-row clearfix')]")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.id("select-asset")).click();
	  Thread.sleep(2000);
	  //Select 'Text' radio button
	  driver.findElement(By.id("email_type_text_only")).click();
	  Thread.sleep(2000);
	  //Save the form
	  driver.findElement(By.id("save_information")).click();
	  Thread.sleep(2000);
	  
	  //Choose email template
	  driver.findElement(By.xpath("//div[@id='template_select_list']//div[contains(@class,'content')]//ul//li[@data-id='29011']")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.id("template_confirm")).click();
	  Thread.sleep(2000);
	  
	  //Save the email
	  driver.findElement(By.id("save_footer")).click();
	  Thread.sleep(2000);
	  
	  //Fill in email with the 'to' list, sender details and subject (i.e. required fields)
	  driver.findElement(By.id("flow_sending")).click();
	  Thread.sleep(5000);
	  driver.findElement(By.xpath("//div[@id='email-wizard-list-select']//div//div[contains(@class,'chzn-container chzn-container-single')]")).click();
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='email-wizard-list-select']//div//div[contains(@class,'chzn-container chzn-container-single')]//div[contains(@class,'chzn-drop')]//div[contains(@class,'chzn-search')]//input")).sendKeys(Constant.listname);
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[@id='email-wizard-list-select']//div//div[contains(@class,'chzn-container chzn-container-single')]//div[contains(@class,'chzn-drop')]//div[contains(@class,'chzn-search')]//input")).sendKeys(Keys.RETURN);
	  Thread.sleep(2000);
	  Select senderDropdown = new Select(driver.findElement(By.name("a_sender[]")));
	  senderDropdown.selectByValue("1");
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[contains(@class,'span9 pull-left')]//div[contains(@class,'control_group sender_a_error')]//ul//li//span//input[contains(@name,'a_general_name')]")).clear();
	  driver.findElement(By.xpath("//div[contains(@class,'span9 pull-left')]//div[contains(@class,'control_group sender_a_error')]//ul//li//span//input[contains(@name,'a_general_name')]")).sendKeys("myName");
	  Thread.sleep(2000);
	  driver.findElement(By.xpath("//div[contains(@class,'span9 pull-left')]//div[contains(@class,'control_group sender_a_error')]//ul//li//span//input[contains(@name,'a_general_email')]")).clear();
	  driver.findElement(By.xpath("//div[contains(@class,'span9 pull-left')]//div[contains(@class,'control_group sender_a_error')]//ul//li//span//input[contains(@name,'a_general_email')]")).sendKeys("myEmail");
	  Thread.sleep(2000);
	  String emailSubject = driver.findElement(By.id("subject_a")).getAttribute("value");
	  if(emailSubject == null) { driver.findElement(By.id("subject_a")).sendKeys("Subject of the Email"); }
	  Thread.sleep(2000);
	  
	  //Save the email
	  driver.findElement(By.id("save_footer")).click();
	  Thread.sleep(2000);
	  
	  //Send the email (email is disabled, so email will not be sent in this example)
	  driver.findElement(By.linkText("Send Now")).click();
	  Thread.sleep(2000);
	  
	  System.out.print("Text only email sent to the list\n");
	  
	  /*End Step 9*/
	  
	  /*Step 10: Logout*/
	  
	  //Hover over to pardot.applicant@pardot.com->Sign Out
	  Actions logoutBuilder = new Actions(driver); 
      WebElement logoutMainmenu = driver.findElement(By.id("acct-tog"));
      logoutBuilder.moveToElement(logoutMainmenu).build().perform();
      Thread.sleep(500); //wait for 0.5 second before proceeding. This allows sub menu to appear properly before trying to click on it
      WebElement logoutSubmenu = driver.findElement(By.xpath("//ul[@id='dropmenu-account']//li//a[contains(@href,'/user/logout')]")); 
      logoutBuilder.moveToElement(logoutSubmenu).click().build().perform();
      Thread.sleep(500);
      
	  System.out.print("Logged out of Pardot\n");
	  
	  /*End Step 10*/
	  
	  Thread.sleep(5000);
	  
  }catch (Exception e)
  {
	  System.out.print(e);
	  throw(e);
  }
 }
}