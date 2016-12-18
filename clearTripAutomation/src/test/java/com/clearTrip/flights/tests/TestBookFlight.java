package com.clearTrip.flights.tests;

import org.testng.annotations.Test;
import com.clearTrip.flights.tests.pageActions.BookFlight_PageAction;
import com.clearTrip.flights.tests.pageActions.Itinerary_PageAction;
import com.clearTrip.flights.tests.pageActions.SearchFlights_PageAction;

import junit.framework.Assert;

public class TestBookFlight extends TestBase {

	SearchFlights_PageAction searchFlights_PageAction = new SearchFlights_PageAction();
	BookFlight_PageAction bookFlight_PageAction = new BookFlight_PageAction();
	Itinerary_PageAction itinerary_PageAction = new Itinerary_PageAction();

	public void searchFlight() throws Exception {
		searchFlights_PageAction.baseOpenURL("https://www.cleartrip.com/");
		searchFlights_PageAction.enterFromLocation("Hyderabad, IN");
		searchFlights_PageAction.enterToLocation("Bangalore, IN");
		searchFlights_PageAction.selectDepatureDate();
		searchFlights_PageAction.selectAdults();
		searchFlights_PageAction.searchFlight();
	}

	public void bookFlight() throws Exception {
		bookFlight_PageAction.verifyBookPage();
		String lowestFare = bookFlight_PageAction.findLowestFare();
		bookFlight_PageAction.bookLowstFareFlight(lowestFare);
	}

	public void itinerary() throws Exception {
		itinerary_PageAction.verifyItineraryPage();
		itinerary_PageAction.complete_Itinerary_Step1();
		itinerary_PageAction.enterEmailDetails_Step2();
		itinerary_PageAction.enterTravellerDetails_Step3();
		String makePayemtText = itinerary_PageAction.paymentDetails_Step4();
		Assert.assertTrue(makePayemtText.equalsIgnoreCase("Make payment"));
	}

	@Test
	/**
	 * Book the lowest fare flight from Hyderbad to Bangalore on 31st december
	 * for 2 passengers
	 */
	public void bookHyderabadToBangaloreFlight() throws Exception {
		searchFlight();
		bookFlight();
		itinerary();
	}
}
