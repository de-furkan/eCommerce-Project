package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ThreadGuard;
import utilities.enums.BrowserType;
import utilities.enums.TextFormat;
/**
 * <h1>Utility class for managing WebDriver instances in a thread-safe manner.</h1>
 * <p>
 * The {@code DriverUtil} class provides static methods to handle the lifecycle of WebDriver instances,
 * including their initialisation, retrieval, and termination. It ensures that each thread has its own
 * WebDriver instance using {@code ThreadLocal}, making it suitable for parallel test execution environments.
 * </p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *     <li>Thread-safe WebDriver management using {@code ThreadLocal}.</li>
 *     <li>Support for multiple browsers, including Chrome, Firefox, Edge, Safari, and Internet Explorer.</li>
 *     <li>Headless mode support for browsers that allow headless execution.</li>
 *     <li>Methods for initialising, retrieving, and quitting WebDriver instances.</li>
 * </ul>
 *
 * <h2>Thread-Safety Considerations:</h2>
 * <p>
 * The WebDriver instance is stored in a {@code ThreadLocal} variable to ensure that each thread
 * has its own instance, preventing conflicts in parallel execution environments. The class also
 * uses {@code ThreadGuard} to further protect the WebDriver instances.
 * </p>
 *
 * <h2>Design Notes:</h2>
 * <ul>
 *     <li>This class follows the singleton pattern by preventing instantiation via a private constructor.</li>
 *     <li>Static methods handle the WebDriver lifecycle, including initialisation and termination.</li>
 * </ul>
 *
 * <h2>Methods Provided:</h2>
 * <ul>
 *     <li>{@code setDriverInstance(BrowserType)}: Initialises the WebDriver instance for a specific browser type.</li>
 *     <li>{@code getDriverInstance()}: Retrieves the WebDriver instance associated with the current thread.</li>
 *     <li>{@code quitDriver()}: Quits the WebDriver instance and removes it from the {@code ThreadLocal} storage.</li>
 * </ul>
 *
 * @author Furkan O.
 * @version 1.0
 * @since 2024
 */
public class DriverUtil {

    /*
     *****************************************
     *          1. CONSTANTS
     *****************************************
     */
    // Thread-safety for driver instance, useful for parallel testing modes.
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    /*
     *****************************************
     *          2. Static fields
     *****************************************
     */
    // Store browser type in variable.
    private static String browserName;

    /*
     *****************************************
     *          3. Instance fields
     *****************************************
     */

    /*
     *****************************************
     *          4. Constructors
     *****************************************
     */
    // No instantiation of this class, Singleton.
    private DriverUtil() {};

    /*
     *****************************************
     *          5. Static block
     *****************************************
     */

    /*
     *****************************************
     *          6. Public methods
     *****************************************
     */
    /**
     * <h2>{@code setDriverInstance(...)}: Sets the WebDriver instance for the specified browser type.</h2>
     * <p>
     * This method initialises the WebDriver if it is not already set,
     * ensuring that only one instance is created per thread using
     * ThreadLocal. It sets the value for the {@code driver} field and returns the WebDriver instance.
     * </p>
     *
     * <p>
     * This method requires a BrowserType argument, provided by the BrowserType enum class.
     * </p>
     *
     * @param browser the type of browser for which the WebDriver is to be initialised (e.g. CHROME, FIREFOX).
     * @see BrowserType
     * @see #initDriver(BrowserType)
     */
    public static void setDriverInstance(BrowserType browser) {
        browserName = browser.toString();
        if (DRIVER.get() == null) {
            initDriver(browser);
        }
    }
    /**
     * <h2>{@code getDriverInstance()}: Retrieves the WebDriver instance associated with the current thread.</h2>
     * <p>
     * This method checks if a WebDriver instance has been initialised for the current thread.
     * If the instance is not set, a {@code RuntimeException} is thrown. Otherwise, it returns
     * the WebDriver instance, ensuring thread-safe access through {@code ThreadLocal}.
     * </p>
     *
     * @return the WebDriver instance for the current thread
     * @throws RuntimeException if the WebDriver instance is not set for the current thread
     * @see #DRIVER
     */
    public static WebDriver getDriverInstance() {
        if (DRIVER.get() == null) {
            throw new RuntimeException("Driver instance not set.");
        }
        return DRIVER.get();
    }
    /**
     * <h2>{@code quitDriver()}: Quits the WebDriver instance and removes it from the ThreadLocal storage.</h2>
     * <p>
     * This method checks if the current WebDriver instance is active. If so, it quits the
     * WebDriver and removes it from the ThreadLocal storage to clean up resources. A success
     * message is printed upon successful termination.
     * </p>
     */
    public static void quitDriver() {
        try {
            if (DRIVER.get() != null) {
                DRIVER.get().quit();
                DRIVER.remove();
                System.out.println(
                        ConsoleUtil.getTextFormat(TextFormat.SUCCESS_MESSAGE_SYMBOL)
                        + browserName
                        + " driver successfully quit."
                );
                return;
            }
            throw new NoSuchMethodException();
        } catch (NoSuchMethodException e) {
            System.out.println(
                    ConsoleUtil.getTextFormat(TextFormat.ERROR_MESSAGE_SYMBOL)
                    + " "
                    + "DRIVER is null. Please set a driver instance."
            );
        }
    }
    /*
     *****************************************
     *          7. Protected methods
     *****************************************
     */

    /*
     *****************************************
     *          8. Private methods
     *****************************************
     */
    /**
     * <h2>{@code initDriver(...)}: Initialise the WebDriver for the specified browser type.</h2>
     * <p>
     * This method sets up the WebDriver based on the browser type provided. It supports
     * both standard and headless modes for various browsers, including Chrome, Firefox, Edge,
     * Safari, and Internet Explorer, all provided by the {@code BrowserType} enum class.
     * The WebDriver instance is protected by {@code ThreadGuard} to ensure thread safety in parallel
     * execution environments.
     * </p>
     *
     * <p>
     * If the browser type is invalid or unrecognised, a {@code RuntimeException} is thrown
     * with a descriptive message. The method also maximises the browser window after
     * initialisation. This method also sets the implicit wait time for WebDrivers
     * defined by the WaitUtil class.
     * </p>
     *
     * <p>
     * This method requires a BrowserType argument, provided through the BrowserType enum class.
     * </p>
     *
     * @param browser the browser type for which the WebDriver is initialised (e.g., CHROME, FIREFOX, EDGE, etc.).
     * @throws RuntimeException if an unsupported or unrecognised browser type is provided.
     * @see BrowserType
     * @see WaitUtil#getDefaultImplicitWaitTime()
     */
    private static void initDriver(BrowserType browser) {
        String successMessage =
                ConsoleUtil.getTextFormat(TextFormat.SUCCESS_MESSAGE_SYMBOL)
                + browser + " " + "driver successfully initialised.";
        String headless = "--headless";
        String defaultMessage =
                ConsoleUtil.getTextFormat(TextFormat.WARNING_MESSAGE_SYMBOL)
                        + " "
                        + "Driver definition unclear: "
                        + "'" + browser + "'."
                        + " " + "Please ensure a valid browser is selected."
                        + " " + "Check the following class for details: " + BrowserType.getClassPath();
        switch (browser) {
            case CHROME:
                DRIVER.set(ThreadGuard.protect(new ChromeDriver()));
                break;
            case FIREFOX:
                DRIVER.set(ThreadGuard.protect(new FirefoxDriver()));
                break;
            case EDGE:
                DRIVER.set(ThreadGuard.protect(new EdgeDriver()));
                break;
            case SAFARI:
                DRIVER.set(ThreadGuard.protect(new SafariDriver()));
                break;
            case EXPLORER:
                DRIVER.set(ThreadGuard.protect(new InternetExplorerDriver()));
                break;
            case CHROME_HEADLESS:
                DRIVER.set(ThreadGuard.protect(
                        new ChromeDriver(
                                new ChromeOptions().addArguments(
                                        headless
                                )
                        )
                ));
                break;
            case FIREFOX_HEADLESS:
                DRIVER.set(ThreadGuard.protect(
                        new FirefoxDriver(
                                new FirefoxOptions().addArguments(
                                        headless
                                )
                        )
                ));
                break;
            case EDGE_HEADLESS:
                DRIVER.set(ThreadGuard.protect(
                        new EdgeDriver(
                                new EdgeOptions().addArguments(
                                        headless
                                )
                        )
                ));
                break;
            default:
                throw new RuntimeException(defaultMessage);
        }
        System.out.println(successMessage);

        // maximise the browser window
        DRIVER.get().manage().window().maximize();

        /*
            Sets the WebDriver implicit wait time.
            Implicit waits are often used to handle situations where elements may not be
            immediately present in the DOM -
            allowing the WebDriver to wait for a specified time
            before throwing an exception.
         */
        DRIVER.get().manage().timeouts().implicitlyWait(WaitUtil.getDefaultImplicitWaitTime());
    }
    /*
     *****************************************
     *          9. Override methods
     *****************************************
     */
}