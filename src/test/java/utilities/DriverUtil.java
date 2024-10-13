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
     * initialisation.
     * </p>
     *
     * <p>
     * This method requires a BrowserType argument, provided through the BrowserType enum class.
     * </p>
     *
     * @param browser the browser type for which the WebDriver is initialised (e.g., CHROME, FIREFOX, EDGE, etc.).
     * @throws RuntimeException if an unsupported or unrecognised browser type is provided.
     * @see BrowserType
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
    }
    /*
     *****************************************
     *          9. Override methods
     *****************************************
     */
}