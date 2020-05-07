package com.mensch.base;

import com.mensch.def.Constants;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;


public class PageManager {

    private PageManager() {
        // Takes approximately 3.5 seconds to init and open the browser
        Portal.INSTANCE.openPage();
    }

    public static PageManager getInstance() {
        return new PageManager();
    }


}
