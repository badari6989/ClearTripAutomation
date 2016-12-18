package com.clearTrip.flights.tests.pageActions;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeClass;

import com.clearTrip.flights.tests.TestBase;

public class BookFlight_PageAction extends TestBase {

	@BeforeClass(alwaysRun = true)
	public void loadObjectLoacators() {
		loadLocators("\\src\\test\\java\\com\\clearTrip\\flights\\tests\\pageActions\\BookFlight_gui_map.properties");
	}

	public void loadLocators(String location) {
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

	public void verifyBookPage() throws Exception {
		loadObjectLoacators();
		waitForelementPresentXpath((prop.getProperty("searchSummary")));
		String textvlaue = getTextValue((prop.getProperty("searchSummary")));
		System.out.println(textvlaue);
	}

	String priceInitialXpath = "//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[";
	String priceEndXpath = "]/table/tbody[2]/tr[1]/th[6]";
	String buttonInitialXpath = "//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[";
	String buttonEndXpath = "]/table/tbody[2]/tr[2]/td[3]/button";

	/**
	 * Book the flight which has lowest fare
	 */

	public String findLowestFare() throws Exception {
		List<WebElement> rows = findrowsintable(prop.getProperty("FlightsListView"));
		System.out.println(rows.size());
		int rnum = 1;
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		for (rnum = 1; rnum < rows.size() + 1; rnum++) {
			String className = driver
					.findElement(By.xpath("//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[" + rnum + "]"))
					.getAttribute("class");
			if (className.equalsIgnoreCase("listItem  nonBundled ")
					|| className.equalsIgnoreCase("listItem showTabs nonBundled ")) {
				String price_per_flight = driver.findElement(By.xpath(priceInitialXpath + rnum + priceEndXpath))
						.getText();
				String flightPrice = price_per_flight.substring(3, price_per_flight.length());
				String splitted[] = flightPrice.split(",");
				String appendedMoney = splitted[0] + splitted[1];
				int rs = Integer.parseInt(appendedMoney);
				arrayList.add(rs);
			}

		}
		Object minFare = Collections.min(arrayList);
		System.out.println("lowestFare: " + minFare);
		String fare = String.valueOf(minFare);
		String lowestFare = "";
		// if the lowest fare has 4 digits append , after first digit
		if (fare.length() == 4) {
			lowestFare = lowestFare.concat("Rs.").concat(fare.substring(0, 1)).concat(",")
					.concat(fare.substring(1, fare.length()));
			System.out.println(lowestFare);
		}
		// if the lowest fare has 5 digits append , after second digit
		if (fare.length() == 5) {
			lowestFare = lowestFare.concat("Rs.").concat(fare.substring(0, 2)).concat(",")
					.concat(fare.substring(2, fare.length()));
			System.out.println(lowestFare);
		}
		return lowestFare;
	}

	/**
	 * After identifying the lowest fare, book that flight
	 */
	public void bookLowstFareFlight(String lowestFare) throws Exception {
		int rnum = 1;
		List<WebElement> rows = findrowsintable(prop.getProperty("FlightsListView"));
		for (rnum = 1; rnum < rows.size() + 1; rnum++) {
			String className = driver
					.findElement(By.xpath("//*[@id='flightForm']/section[2]/div[4]/div/nav/ul/li[" + rnum + "]"))
					.getAttribute("class");
			if (className.equalsIgnoreCase("listItem  nonBundled ")
					|| className.equalsIgnoreCase("listItem showTabs nonBundled ")) {
				if (driver.findElement(By.xpath(priceInitialXpath + rnum + priceEndXpath)).getText()
						.equals(lowestFare)) {
					driver.findElement(By.xpath(buttonInitialXpath + rnum + buttonEndXpath)).click();
					break;
				} else {
					System.out.println("there is no lowest price");
					break;
				}

			}
		}

	}
}
