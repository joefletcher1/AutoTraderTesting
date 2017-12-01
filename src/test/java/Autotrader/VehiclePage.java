package Autotrader;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class VehiclePage {

    @FindBy (css = "#main-content > div.vehicle__secondarycol > section > div > div.seller_trade__location")
    private WebElement locationOfSeller;

    public int getRangeOfTopResult(String postcode) {
        String resultRange = locationOfSeller.getText();
        resultRange = resultRange.substring(0, resultRange.length() - (postcode.length() + 2));

        resultRange = resultRange.replaceAll("[^\\d.]", "");
        return Integer.parseInt(resultRange);
    }
}
