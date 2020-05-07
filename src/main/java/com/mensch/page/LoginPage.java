package com.mensch.page;


import com.mensch.def.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {

    protected final String url = "/login";

    public LoginPage(WebDriver driver) {
        super(driver);
        this.pageUrl = url;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, Constants.DEFAULT_TIMEOUT);
        //ExtendedFieldDecorator decorator = new ExtendedFieldDecorator(factory);
        //PageFactory.initElements(decorator, this);
    }

}
