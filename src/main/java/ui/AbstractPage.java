package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class AbstractPage {

    public static final long DEFAULT_IMPLICIT_TIMEOUT_IN_SECONDS = 15;
    public static final long EXPLICIT_WAIT_IN_SECONDS = 20;

    protected final WebDriver driver;
    protected final WebDriverWait wait;

    public AbstractPage(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(DEFAULT_IMPLICIT_TIMEOUT_IN_SECONDS));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT_IN_SECONDS));
        PageFactory.initElements(driver,this);
    }

    public abstract Boolean isAt();

}
