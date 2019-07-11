package Components;

import java.awt.AWTException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import resources.base;

public class SellicsUIComponents extends base{
	public WebDriver driver;
	public static Logger log=LogManager.getLogger(base.class.getName());	
	public SellicsUIComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
	}
	String workingDir = System.getProperty("user.dir");
	String ItemtoAdd=prop.getProperty("ItemtoAdd");
	base b = new base();
	String elementXpath="Getxpath";
	
	
	///----old app variable
	String uploadfilename=prop.getProperty("uploadfilename");
	
	public void Login() throws IOException, InterruptedException  {
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		String email=prop.getProperty("username");
		System.out.println("Username is "+ email);
		String Password=prop.getProperty("Password");
		System.out.println("Password is "+ Password);
		
		//Capturing screenshot of Home page
		b.getScreenshot("Homepage");
		//Accept cookie
		//Thread.sleep(3000);
		b.click("btnAcceptcookie");
		
		//perform login
		b.click("btnLogin");
		b.click("btnAgencyEdition");
		b.SetText("eltusername", email);
		b.SetText("eltPassword", Password);
		b.click("btnInnerLogin");
		Thread.sleep(2000);
		b.getScreenshot("AgencyEditionHomepageafterLogin");
	}
	
	//Verify verify side Navigation bar elements
	public void verifysideNavigationbarelements() throws SQLException, IOException {
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		String[] ExpectedHeaderelemnts=null;
		String datbasename=prop.getProperty("datbasename");
		String querytoexecute=prop.getProperty("querytoexecute");
		
		ExpectedHeaderelemnts=GetdbRecords(datbasename, querytoexecute);
		
		for(int i=0;i<5;i++)
		{
		System.out.println("Expected Header element"+i+"is "+ExpectedHeaderelemnts[i]);	
		}
		
		String ActualHeaderelements[]={"abc","abc","abc","abc","abc"};
		ActualHeaderelements[0]=b.getelementtext("Headerelement1");
		ActualHeaderelements[1]=b.getelementtext("Headerelement2");
		ActualHeaderelements[2]=b.getelementtext("Headerelement3");
		ActualHeaderelements[3]=b.getelementtext("Headerelement4");
		ActualHeaderelements[4]=b.getelementtext("Headerelement5");
		
		String Highlighelement="Headerelement";
		int j=1;
		//verify if the text of each header element is displayed correctly or not
		for(int i=0;i<5;i++)
		{
			try {
				
				Assert.assertTrue(ActualHeaderelements[i].equalsIgnoreCase(ExpectedHeaderelemnts[i]));
				Highlighelement=Highlighelement+j;
				System.out.println("Element to highlight is"+Highlighelement);
				b.drawHighlight(Highlighelement);
				log.info("Text is displayed correctly for the the element "+ExpectedHeaderelemnts[i]);
				System.out.println("Text is displayed correctly for the the element "+ExpectedHeaderelemnts[i]);
				Highlighelement="Headerelement";
				j++;
			} catch (Exception e) {

				log.error("Text is displayed wrongly for the the element "+ExpectedHeaderelemnts[i]+e);
				System.out.println("Text is displayed wrongly for the the element "+ExpectedHeaderelemnts[i]);
			}

			
		}
		
		}	

	
	//Verify VerifyChatterAnalyticssection link
	public void VerifyChatterAnalyticssection() throws IOException, InterruptedException {
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		
		//Click Charter box
		b.click("btnChatter");
		
		//Click About Analytics Sub link
		b.click("AnalyticsSublink");
				
		//Capture screenshot of result
		b.getScreenshot("AnalyticsSublinkpage");
		//Get expected text
		String Expectedtext=prop.getProperty("ExpectedAnalyticspageheading");
		System.out.println("Expected text is "+Expectedtext);
		
		//verify if navgated to our offers page correctly or not
		
		String Actualtext = b.getelementtext("eltAnalyticspageheading");
		
		System.out.println("Actual text is"+Actualtext);
		
		try {
			Assert.assertTrue(Actualtext.equalsIgnoreCase(Expectedtext));
			log.info("Text is displayed correctly for Analytics Subpage. ");
			System.out.println("Text is displayed correctly for Analytics Subpage. ");
			b.drawHighlight("eltAnalyticspageheading");
		} catch (Exception e) {

			log.error("Text is displayed wrongly for Analytics Subpage. "+e);
			System.out.println("Text is displayed wrongly Analytics Subpage. ");
		}	
		
	}
	
	//Verify Verify content monitoring section
		public void Verifycontentmonitoringsection() throws IOException, InterruptedException {
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			
			//Click Content & SEO
			b.click("btnContentSEO");
			
			//Click About content monitoring Sub link
			b.click("eltcontentmonitoringSublink");
					
			//Capture screenshot of result
			b.getScreenshot("contentmonitoringSublinkpage");
			//Get expected text
			String Expectedtext=prop.getProperty("Expectedcontentmonitoringpageheading");
			System.out.println("Expected text is "+Expectedtext);
			
			//verify if navgated to our offers page correctly or not
			
			String Actualtext = b.getelementtext("eltcontentmonitoringpageheading");
			
			System.out.println("Actual text is"+Actualtext);
			
			try {
				Assert.assertTrue(Actualtext.equalsIgnoreCase(Expectedtext));
				log.info("Text is displayed correctly for content  monitoring Subpage. ");
				System.out.println("Text is displayed correctly for content  monitoring Subpage. ");
				b.drawHighlight("eltcontentmonitoringpageheading");
			} catch (Exception e) {

				log.error("Text is displayed wrongly for content  monitoring Subpage. "+e);
				System.out.println("Text is displayed wrongly content  monitoring Subpage. ");
			}	
		Thread.sleep(3000);	
		}

		

}
