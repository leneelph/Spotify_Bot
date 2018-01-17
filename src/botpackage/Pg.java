package botpackage;


import java.io.InputStream;

import java.net.HttpURLConnection;

import java.net.URL;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;


public class Pg {

	public static void main(String[] args) throws Exception {
		
		/* INITIALIZING & INSTANCIATIONS */
		
		
		
		
		/* TEST HTTP CONNEXION */
	    try {
	        URL testURL = new URL("https://google.fr");
	        HttpURLConnection connexion = (HttpURLConnection)testURL.openConnection();
	        InputStream flux = connexion.getInputStream();
	        
	        if (connexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
	        		System.out.println("Connection status : " + connexion.getResponseMessage() + ".");
	        }
	        flux.close(); 
	        connexion.disconnect();
	    } 
	    catch(Exception e) {
	    		System.out.println("ERROR creating HTTP connection.");
	    }
	   
	    
	    
	    /* KILL ALL FIREFOX PROCESSES BEFORE LAUNCHING FIREFOX */
	    while (CheckFirefoxProcess.isFirefoxRunning()) {
			CheckFirefoxProcess.killFirefoxProcesses();
		}
	    
	    /* SET GECKO DRIVER */
		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/geckodriver");
		
	    /* LAUNCH FIREFOX PAGE */
		FirefoxOptions options = new FirefoxOptions();
  	    WebDriver driver = null;
  		options.setHeadless(true); // set Firefox headless mode on
  	    try {
  	    		driver = new FirefoxDriver(options);
 	    		System.out.println("Loading GeckoDriver : OK.");
  	    }
  	    catch(IllegalStateException e1) {
  	    		System.out.println("ERROR : Couldn't locate the GeckoDriver file.");
  	    }
 	    
  	    driver.manage().window().setSize(new Dimension(1920, 1080)); // manually set the window size to full screen
  	    // Confirm FireFox Launch
	    if (CheckFirefoxProcess.isFirefoxRunning()) {
	    		System.out.println("Launching Firefox : OK.");
	    }
	    else {
	    		System.out.println("ERROR : Couldn't launch Firefox.");
	    }
	    
	    
	    
	    /* CONNEXION GMAIL */
	    Platform gmailPlatform = new Platform();
	    gmailPlatform.setStartUrl("https://google.fr");
	    gmailPlatform.connect(driver);
	    
	    
	    
		/* CLOSE THE BROWSER */
		driver.close();
		// Clear all Firefox Processes
		while (CheckFirefoxProcess.isFirefoxRunning()) {
			CheckFirefoxProcess.killFirefoxProcesses();
		}
	}
	
	
}
