package com.mensch.page;


import com.mensch.decorator.ExtendedFieldDecorator;
import com.mensch.decorator.elements.Button;
import com.mensch.decorator.elements.TextField;
import com.mensch.def.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LoginPage extends BasePage {

    protected final String url = "/login";

    public LoginPage(WebDriver driver) {
        super(driver);
        this.pageUrl = url;
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, Constants.DEFAULT_TIMEOUT);
        ExtendedFieldDecorator decorator = new ExtendedFieldDecorator(factory);
        PageFactory.initElements(decorator, this);
    }

    static final String emailXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.EditText";
    static final String passXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.EditText";
    static final String loginXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.widget.Button";
    static final String playBtnXP = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[3]/android.view.ViewGroup[1]/android.widget.Button[2]";

    @FindBy(xpath = emailXP)
    public static TextField emailField;

    @FindBy(xpath = passXP)
    public static TextField passField;

    @FindBy(xpath = loginXP)
    public static Button loginBtn;

    public void loginApp(){
        emailField.waitAndType("vadim.po@mensch.io", driver);
        passField.waitAndType("Raswd123", driver);
        loginBtn.waitAndClick(driver);
    }

}
