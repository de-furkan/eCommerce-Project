package utilities;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

public class WaitUtil {
    /*
     *****************************************
     *          1. constants
     *****************************************
     */
    private static final LoggerUtil LOGGER = new LoggerUtil(WaitUtil.class);

    /*
        The default duration for implicit waits used by WebDriver operations is 0 seconds.
        This constant defines the default implicit wait time of 30 seconds. The constant is
        retrieved by the getDefaultImplicitWaitTime() method, ensuring modularity, clarity, and
        preventing the accidental change of the assigned value.

        During this time (of 30 seconds), the WebDriver will poll the DOM at regular intervals
        to check if the element is available.
     */
    private static final Duration DEFAULT_IMPLICIT_WAIT_TIME = Duration.ofSeconds(30);
    /*
     *****************************************
     *          2. static fields
     *****************************************
     */

    /*
     *****************************************
     *          3. static blocks
     *****************************************
     */

    /*
     *****************************************
     *          4. instance fields
     *****************************************
     */

    /*
     *****************************************
     *          5. constructors
     *****************************************
     */
    private WaitUtil() {};

    /*
     *****************************************
     *          6. public methods
     *     (including getters/setters)
     *****************************************
     */
    /**
     * <h2>{@code hardWaitForSeconds(...)}: Pauses the current thread for a specified number of seconds.</h2>
     * <p>
     * This method causes the current thread to sleep for the given number of seconds,
     * simulating a hard wait. It uses {@code Thread.sleep()} to achieve the delay, and
     * handles interruptions by logging an error message and re-interrupting the thread.
     * </p>
     *
     * <h2>Usage:</h2>
     * <p>
     * This method is typically used when a hard wait is needed, such as during debugging or when a
     * specific time delay is required. It is not recommended for regular use in production test code,
     * as hard waits can reduce the efficiency and stability of automated tests.
     * </p>
     * <pre><code>
     *     // pause current thread for 2 seconds.
     *     WaitUtil.hardWaitForSeconds(2);
     * </code></pre>
     *
     * <h2>Exception Handling:</h2>
     * <ul>
     *     <li>If the thread is interrupted while sleeping, an {@code InterruptedException} is caught,
     *     logged as an error, and the thread is re-interrupted.</li>
     *     <li>
     *         <pre><code>Thread.currentThread().interrupt();</code></pre> is invoked after catching
     *         {@code InterruptedException} to ensure proper handling of interruptions, especially
     *         within parallel execution environments where multiple threads may be running simultaneously.
     *     </li>
     * </ul>
     *
     * @param seconds the number of seconds to pause the current thread.
     * @throws InterruptedException if the thread is interrupted while sleeping.
     */
    public static void hardWaitForSeconds(int seconds) {
        try {
            Duration duration = Duration.ofSeconds(seconds);
            Thread.sleep(duration.toMillis());
        } catch (InterruptedException e) {
            LOGGER.error(
                    "Thread interrupted during hardWaitForSeconds. Waiting for"
                    + "{" + seconds + "} seconds failed."
            );
            Thread.currentThread().interrupt();
        }
    }
    /**
     * <h2>{@code getDefaultImplicitWaitTime()}: Retrieves the default implicit wait time for WebDriver operations.</h2>
     * <p>
     * This method returns the pre-configured duration of the implicit wait time used by the WebDriver.
     * Implicit waits instruct the WebDriver to poll the DOM for a specified duration when attempting
     * to locate elements, ensuring that the test waits for the element to be present before throwing an exception.
     * </p>
     *
     * <h2>Usage:</h2>
     * <p>
     * Implicit waits are typically used when synchronisation between WebDriver and the web application is needed.
     * It ensures that WebDriver will wait for elements to appear before interacting with them. However, it's
     * important to note that explicit waits are often preferred in complex scenarios.
     * </p>
     *
     * <pre><code>
     *     driver.get.manage().timeouts().implicitlyWait(WaitUtil.getDefaultImplicitWaitTime());
     * </code></pre>
     * @return the default duration for implicit waits, defined by {@code DEFAULT_IMPLICIT_WAIT_TIME}.
     */
    public static Duration getDefaultImplicitWaitTime() {
        return DEFAULT_IMPLICIT_WAIT_TIME;
    }
    /**
     * <h2>{@code waitForFullPageLoad(...)}: Waits for the web page to fully load by checking the document's ready state.</h2>
     * <p>
     * This method waits until the web page has completely loaded by polling the document's ready state
     * using JavaScript. The page is considered fully loaded when the ready state is {@code "complete"}.
     * The method uses a timeout to avoid waiting indefinitely, and logs the result of the operation.
     * If the page fails to load within the specified time, an error is logged.
     * </p>
     *
     * <h2>Usage:</h2>
     * <p>
     * This method is useful when ensuring that the page is fully loaded before interacting with elements.
     * The WebDriver will wait until the page is in a ready state for the specified timeout duration.
     * </p>
     *
     * <pre><code>
     *     WaitUtil.waitForFullPageLoad(10); // time in seconds
     * </code></pre>
     *
     * <h2>Exception Handling:</h2>
     * <ul>
     *     <li>If the page fails to load within the given timeout, an error message is logged.</li>
     *     <li>If the WebDriver instance is null, a specific error message is logged.</li>
     * </ul>
     *
     * @param secondsToTimeout the maximum number of seconds to wait for the page to load.
     * @throws org.openqa.selenium.TimeoutException if the page does not load within the specified timeout.
     * @see WebDriverWait
     * @see JavascriptExecutor
     */
    public static void waitForFullPageLoad(int secondsToTimeout) {
        ExpectedCondition<Boolean> pageLoadCondition =
                driver -> {
                    assert driver != null;
                    return Objects.equals(
                            ((JavascriptExecutor) driver).executeScript(
                            "return document.readyState"
                    ), "complete");
                };

        try {
            LOGGER.info("Waiting for the page load to complete to ready state...");
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriverInstance(), Duration.ofSeconds(secondsToTimeout));
            wait.until(pageLoadCondition);
            LOGGER.success("Page successfully loaded");
        } catch (Throwable error) {
            if (DriverUtil.getDriverInstance() == null) {
                LOGGER.error("Driver instance is null");
            }
            LOGGER.error("Page load timed out after " + secondsToTimeout + " seconds.");
        }
    }
    /*
     *****************************************
     *          7. protected methods
     *****************************************
     */

    /*
     *****************************************
     *          8. private methods
     *****************************************
     */
}