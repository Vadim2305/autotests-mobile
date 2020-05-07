package com.mensch.page;

import org.openqa.selenium.WebDriver;
import com.mensch.def.Constants;

public abstract class BasePage {

    protected String pageUrl = "";

    protected WebDriver driver;

    protected String getPageUrl() {
        return pageUrl;
    }

    protected void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void loadPage () {
        //driver.get(Constants.SERVER_URL + pageUrl);
    }
}
