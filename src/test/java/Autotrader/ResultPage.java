package Autotrader;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultPage {

    @FindBy (css = ".search-form__count")
    private WebElement searchResultCount;
    @FindBy (className = "listing-fpa-link")
    private WebElement topReult;


    public int getCountOfSearchResults() {
        String resultCount = searchResultCount.getText();
        resultCount = resultCount.replace(" cars found", "");
        resultCount = resultCount.replace(",", "");
        return Integer.parseInt(resultCount);
    }

    public void navigateToTopResult() {
        topReult.click();
    }

    public void getMakeModelOfTopResult() {

    }
}
