package utilities.enums;

import java.util.Objects;

/**
 * <h1>Enum representing supported browser types for WebDriver instances.</h1>
 * <p>
 * The {@code BrowserType} enum defines various browser types, including standard and headless modes,
 * supported by Selenium for WebDriver operations. This enum is used for initialising browser instances
 * in a consistent manner across the test framework. Each browser type corresponds to a specific
 * WebDriver implementation, with separate entries for headless modes where supported.
 * </p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *     <li>Support for commonly used browsers: Chrome, Firefox, Edge, Safari, and Internet Explorer.</li>
 *     <li>Separate entries for headless modes (Chrome, Firefox, and Edge) for headless testing.</li>
 *     <li>Provides the canonical class path of the {@code BrowserType} enum for reference.</li>
 * </ul>
 *
 * <h2>Parallel Execution and Headless Mode Considerations:</h2>
 * <ul>
 *     <li><strong>Safari:</strong> Safari does not support parallel execution. Only one WebDriver instance can
 *     be active at a time.</li>
 *     <li><strong>Internet Explorer:</strong> While Internet Explorer supports parallel execution,
 *     it is considered a legacy browser and can present challenges during parallel testing.</li>
 *     <li><strong>Headless Mode:</strong> Native headless support is not available for Safari or
 *     Internet Explorer in Selenium.</li>
 * </ul>
 *
 * <h2>Further Reading:</h2>
 * <ul>
 *     <li><a href="https://developer.apple.com/documentation/webkit/about_webdriver_for_safari">WebDriver for Safari</a></li>
 *     <li><a href="https://github.com/SeleniumHQ/selenium/issues/5985">Selenium Headless Mode Issue</a></li>
 *     <li><a href="https://stackoverflow.com/a/46649328">Headless Mode Discussion on StackOverflow</a></li>
 * </ul>
 * @since 2024
 * @author Furkan O.
 * @version 1.0
 * @see org.openqa.selenium.WebDriver
 * @see org.openqa.selenium.chrome.ChromeDriver
 * @see org.openqa.selenium.firefox.FirefoxDriver
 * @see org.openqa.selenium.edge.EdgeDriver
 * @see org.openqa.selenium.safari.SafariDriver
 * @see org.openqa.selenium.ie.InternetExplorerDriver
 */
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