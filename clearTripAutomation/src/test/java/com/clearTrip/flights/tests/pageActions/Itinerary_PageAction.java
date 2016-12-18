package com.clearTrip.flights.tests.pageActions;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;

import com.clearTrip.flights.tests.TestBase;

public class Itinerary_PageAction extends TestBase {

	@BeforeClass(alwaysRun = true)
	public void loadObjectLoacators() {
		loadLocators("\\src\\test\\java\\com\\clearTrip\\flights\\tests\\pageActions\\Itinerary_gui_map.properties");
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

	public void verifyItineraryPage() throws Exception {
		loadObjectLoacators();
		waitForelementPresentXpath((prop.getProperty("ItineraryText")));
		String textvlaue = getTextValue((prop.getProperty("ItineraryText")));
		System.out.println(textvlaue);
	}

	public void complete_Itinerary_Step1() throws Exception {
		waitForelementPresentId(prop.getProperty("SelectInsurance"));
		elementByIdSubmit(prop.getProperty("SelectInsurance"));
		elementByIdSubmit(prop.getProperty("ItineraryBooking"));
	}

	public void enterEmailDetails_Step2() throws Exception {
		waitForelementPresentXpath((prop.getProperty("email")));
		sendkeysbyxpath(prop.getProperty("email"), "badari33@mailinator.com");
		elementByXpathSubmit(prop.getProperty("subscription"));
		elementByIdSubmit(prop.getProperty("ContinueBooking"));
	}

	public void enterTravellerDetails_Step3() throws Exception {
		waitForelementPresentId((prop.getProperty("AdultTitle1")));
		WebElement selectTitle_pass1 = elementByIdWebElement(prop.getProperty("AdultTitle1"));
		Select selectTitle_passenger1 = new Select(selectTitle_pass1);
		selectTitle_passenger1.selectByIndex(1);
		sendkeysbyxpath(prop.getProperty("AdultFirstname1"), "badari");
		sendkeysbyxpath(prop.getProperty("AdultLastname1"), "nath");
		sendKeyBoardkeysbyxpath(prop.getProperty("AdultLastname1"), Keys.TAB);
		WebElement selectTitle_pass2 = elementByIdWebElement(prop.getProperty("AdultTitle2"));
		Select selectTitle_passenger2 = new Select(selectTitle_pass2);
		selectTitle_passenger2.selectByIndex(1);
		sendkeysbyxpath(prop.getProperty("AdultFirstname2"), "ramesh");
		sendkeysbyxpath(prop.getProperty("AdultLastname2"), "durga");
		sendkeysbyxpath(prop.getProperty("mobileNumber"), "9293189123");
		elementByXpathSubmit(prop.getProperty("TravellerBtn"));
		isAlertPresent();
	}

	public String paymentDetails_Step4() throws Exception {
		waitForelementPresentId((prop.getProperty("CreditCardNo")));
		sendkeysbId(prop.getProperty("CreditCardNo"), "0000");
		String makePayemtText = getValue(prop.getProperty("MakePayment"));
		System.out.println("makePayemtText: " + makePayemtText);
		return makePayemtText;
	}
}
