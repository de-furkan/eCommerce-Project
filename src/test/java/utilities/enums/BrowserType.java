package utilities.enums;

import java.util.Objects;

public enum BrowserType {
    CHROME,
    FIREFOX,
    EDGE,
    SAFARI,
    EXPLORER,
    CHROME_HEADLESS,
    FIREFOX_HEADLESS,
    EDGE_HEADLESS;

    public static String getClassPath() {
        String path = Objects.requireNonNull(BrowserType.class.getCanonicalName());
        return "Path: " + path;
    }

    /*
     ***************************************************************************************
     *                  ⚠️ WARNING INFORMATION FOR                                         *
     *               PARALLEL EXECUTION & HEADLESS MODES                                   *
     !**************************************************************************************
     !      >> PARALLEL EXECUTION SUPPORT: <<                                              *
     *                                                                                     *
     !      Parallel Execution does not work on SAFARI. Attempting parallel testing        *
     !      with multiple WebDriver instances of SAFARI may throw an error. Please note    *
     !      as of now SAFARI only supports a SINGLE WebDriver instance at a time.          *
     !      While INTERNET EXPLORER does support parallel execution, it is still a legacy  *
     !      browser and does have its challenges when parallel testing or using grid.      *
     *                                                                                     *
     !              -> SAFARI                       ❌                                     *
     !              -> INTERNET EXPLORER            ⚠️                                     *
     *                                                                                     *
     ?      To Read More:                                                                  *
     ?      https://developer.apple.com/documentation/webkit/about_webdriver_for_safari    *
     !                                                                                     *
     !      >> HEADLESS MODE NATIVE SELENIUM SUPPORT: <<                                   *
     *                                                                                     *
     !      Currently, --headless mode is NOT natively supported for the following         *
     !      browsers by Selenium:                                                          *
     !                                                                                     *
     !              -> SAFARI                       ❌                                     *
     !              -> INTERNET EXPLORER            ❌                                     *
     *                                                                                     *
     ?      To Read More:                                                                  *
     ?          [1] https://github.com/SeleniumHQ/selenium/issues/5985                     *
     ?          [2] https://stackoverflow.com/a/46649328                                   *
     !**************************************************************************************
     */
}