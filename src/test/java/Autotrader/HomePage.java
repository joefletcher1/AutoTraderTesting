package Autotrader;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage {

    @FindBy (css = "#postcode")
    private WebElement postcodeBox;
    @FindBy (css = "#radius")
    private WebElement searchRadius;
    @FindBy (css = "#oneSearchAdUsed")
    private WebElement usedCheckbox;
    @FindBy (css = "#oneSearchAdNearlyNew")
    private WebElement nearlyUsedCheckbox;
    @FindBy (css = "#oneSearchAdBrandNew")
    private WebElement newCheckbox;
    @FindBy (css = "#searchVehiclesMake")
    private WebElement vehicleMake;
    @FindBy (css = "#searchVehiclesModel")
    private WebElement wehicleModel;
    @FindBy (css = "#searchVehiclesPriceFrom")
    private WebElement minPrice;
    @FindBy (css = "#searchVehiclesPriceTo")
    private WebElement maxPrice;
    @FindBy (css = "#js-search-button")
    private WebElement searchButton;

    public void populatePostcode(String postcode) {
        postcodeBox.sendKeys(postcode);
    }

    public void clickSearchButton() {
        searchButton.click();
    }
    public void populateRange(String radiusInput) {
        Select dropdown = new Select(searchRadius);
        dropdown.selectByVisibleText(radiusInput);
    }
    public void populateUsedCheckbox(String isUsed) {
        if (isUsed.equals("FALSE")) {
            usedCheckbox.click();
        }
    }
    public void populateNearlyUsedCheckbox(String isUsed) {
        if (isUsed.equals("FALSE")) {
            nearlyUsedCheckbox.click();
        }
    }
    public void populateNewCheckbox(String isUsed) {
        if (isUsed.equals("FALSE")) {
            newCheckbox.click();
        }
    }
    public void populateMake(String make) {
        Select dropdown = new Select(vehicleMake);
        dropdown.selectByVisibleText(make);
    }


    public void populateAllStandardSearchFields(ExtentTest allSearchCriteria) {
    }
}
