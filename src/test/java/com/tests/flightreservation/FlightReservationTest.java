package com.tests.flightreservation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.flightreservation.*;

public class FlightReservationTest {
    private WebDriver driver;

    @BeforeTest
    public void setDriver(){
        WebDriverManager.chromedriver().setup();
        this.driver=new ChromeDriver();
    }

    @Test
    public void userRegistrationTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.goTo("https://d1uh9e7cu07ukd.cloudfront.net/selenium-docker/reservation-app/index.html");
        Assert.assertTrue(registrationPage.isAt());

        registrationPage.enterUserDetails("selenium", "docker");
        registrationPage.enterUserCredentials("selenium@docker.com", "docker");
        registrationPage.enterAddress("street1", "atlanta", "22564");
        registrationPage.register();
    }

    @Test(dependsOnMethods = "userRegistrationTest")
    public void registrationConfirmatinTest() {
        RegistrationConfirmationPage registrationConfirmationPage = new RegistrationConfirmationPage(driver);
        Assert.assertTrue(registrationConfirmationPage.isAt());
        registrationConfirmationPage.goGoToFlightsSearch();
    }

    @Test(dependsOnMethods = "registrationConfirmatinTest")
    public void flightSearchTest() {
        FlightSearchPage flightSearchPage = new FlightSearchPage(driver);
        Assert.assertTrue(flightSearchPage.isAt());
        flightSearchPage.setPassengers("2");
        flightSearchPage.searchFlights();
    }

    @Test(dependsOnMethods = "flightSearchTest")
    public void flightsSelectionTest(){
        FlightsSelectionPage flightsSelectionPage = new FlightsSelectionPage(driver);
        Assert.assertTrue(flightsSelectionPage.isAt());
        flightsSelectionPage.selectFlights();
        flightsSelectionPage.confirmFlights();
    }

    @Test(dependsOnMethods = "flightsSelectionTest")
    public void flightReservationConfirmationTest(){
        FlightConfirmationPage flightConfirmationPage = new FlightConfirmationPage(driver);
        Assert.assertTrue(flightConfirmationPage.isAt());
        Assert.assertEquals(flightConfirmationPage.getPrice(),"$1169 USD");
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }
}
