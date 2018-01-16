package botpackage;

import org.openqa.selenium.WebDriver;

public class Platform {
	private String startUrl;
	
	
	
	public String getStartUrl() {
		return startUrl;
	}

	public void setStartUrl(String startUrl) {
		this.startUrl = startUrl;
	}

	
	public Platform(String startUrl) {
		this.startUrl = startUrl;
	}

	public Platform() {
		
	}

	public void connect(WebDriver driver) {
		driver.get(this.startUrl);
	}
}
