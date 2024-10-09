package pages.flightreservation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.AbstractPage;

public class RegistrationConfirmationPage extends AbstractPage {

    @FindBy(id = "go-to-flights-search")
    private WebElement goToFlightsSearchButton;

    public RegistrationConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public Boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.goToFlightsSearchButton));
        return this.goToFlightsSearchButton.isDisplayed();
    }

    public void goGoToFlightsSearch() {
        this.goToFlightsSearchButton.click();
    }
}
