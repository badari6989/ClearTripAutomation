package com.clearTrip.flights.tests.pageActions;

import java.io.FileInputStream;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import com.clearTrip.flights.tests.TestBase;

public class SearchFlights_PageAction extends TestBase {

	@BeforeClass(alwaysRun = true)
	public void loadObjectLoacators() {
		loadLocators(
				"\\src\\test\\java\\com\\clearTrip\\flights\\tests\\pageActions\\SearchFlights_gui_map.properties");
	}

	public void loadLocators(String location) {
		if (prop == null) {
			// String
			// path=System.getProperty("user.dir")+"\\src\\test\\project.properties";
			String path = System.getProperty("user.dir") + location;
			System.out.println("User directory" + System.getProperty("user.dir"));
			prop = new Properties();
			try {
				FileInputStream fs = new FileInputStream(path);
				prop.load(fs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void openURL(String url) throws Exception {
		baseOpenURL(url);
	}

	public void enterFromLocation(String fromLocation) throws Exception {
		loadObjectLoacators();
		waitForelementPresentXpath((prop.getProperty("From")));
		sendkeysbyxpath((prop.getProperty("From")), fromLocation);
		List<WebElement> fromAutoComplete = findrowsintable(prop.getProperty("FromAutoComplete"));
		for (WebElement element : fromAutoComplete) {
			if (element.getText().equalsIgnoreCase("Hyderabad, IN - Rajiv Gandhi International (HYD)")) {
				element.click();
			}
		}
	}

	public void enterToLocation(String fromLocation) throws Exception {
		waitForelementPresentXpath((prop.getProperty("To")));
		sendkeysbyxpath((prop.getProperty("To")), fromLocation);
		List<WebElement> toAutoComplete = findrowsintable(prop.getProperty("ToAutoComplete"));
		for (WebElement element : toAutoComplete) {
			if (element.getText().equalsIgnoreCase("Bangalore, IN - Kempegowda International Airport (BLR)")) {
				element.click();
			}
		}
	}

	public void selectDepatureDate() throws Exception {
		waitForelementPresentXpath((prop.getProperty("DepartOn")));
		elementByXpathSubmit(prop.getProperty("DepartOn"));
		WebElement datePicker = elementByXpathWebElement(prop.getProperty("DatePicker"));
		List<WebElement> columns = datePicker.findElements(By.tagName("td"));
		for (WebElement cell : columns) {
			if (cell.getText().equals("31")) {
				cell.click();
				break;
			}
		}
	}

	public void selectAdults() throws Exception {
		waitForelementPresentId((prop.getProperty("Adults")));
		WebElement selectAdults = elementByIdWebElement(prop.getProperty("Adults"));
		Select dropdown = new Select(selectAdults);
		dropdown.selectByIndex(1);

	}

	public void searchFlight() throws Exception {
		elementByIdSubmit(prop.getProperty("SearchFlights"));
	}
}
