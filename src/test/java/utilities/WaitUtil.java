package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.enums.TextFormat;

import java.time.Duration;
import java.util.NoSuchElementException;
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
            LOGGER.success("Successfully paused thread for" + " " + seconds + " " + "second(s).");
        } catch (InterruptedException e) {
            LOGGER.error(
                    "Thread interrupted during hardWaitForSeconds. Waiting for"
                    + "{" + seconds + "} seconds failed."
            );
            Thread.currentThread().interrupt();
        }
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
            LOGGER.error("Page load timed out after " + secondsToTimeout + " " + "seconds.");
        }
    }
    /**
     * <h2>{@code fluentWait(...)}: Waits for a WebElement to become visible using a fluent wait strategy.</h2>
     * <p>
     * This method applies a fluent wait to the specified element, allowing for a customised timeout duration
     * and polling interval. The fluent wait continually checks for the element's visibility within the specified
     * timeout and retries at the defined polling intervals. If the element does not appear within the timeout,
     * a timeout exception is thrown and logged.
     * </p>
     *
     * <h2>Usage:</h2>
     * <p>
     * Fluent waits are useful when waiting for elements that may appear dynamically after some time.
     * It is preferable to hard waits, as it continually polls the DOM instead of waiting for a fixed amount of time.
     * This method is particularly useful for handling dynamic web content where elements might load asynchronously.
     * </p>
     *
     * <pre><code>
     *     WebElement startBtn = WaitUtil.fluentWait(
     *                 By.xpath("//div[@id='start']/button"),
     *                 10,
     *                 1
     *         );
     * </code></pre>
     *
     * <h2>Exception Handling:</h2>
     * <ul>
     *     <li>If the WebDriver instance is null, an error is logged and the method returns {@code null}.</li>
     *     <li>If the element does not become visible within the timeout, a {@code TimeoutException} is caught and logged.</li>
     *     <li>If a WebDriver-related exception occurs, a fatal log is generated.</li>
     *     <li>Any other exceptions are caught and logged as unexpected errors.</li>
     * </ul>
     *
     * @param locator the {@code By} locator used to find the WebElement
     * @param timeoutInSeconds the maximum time to wait for the element to appear
     * @param pollEverySecond the polling interval (in seconds) between checks for the element
     * @return the visible WebElement, or {@code null} if the element is not found within the timeout
     * @see FluentWait
     * @see ExpectedConditions
     */
    public static WebElement fluentWait(By locator, int timeoutInSeconds, int pollEverySecond) {
        WebElement element = null;
        if (DriverUtil.getDriverInstance() == null) {
            LOGGER.error("Driver instance is null");
            return null;
        }
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(DriverUtil.getDriverInstance())
                    .withTimeout(Duration.ofSeconds(timeoutInSeconds))
                    .pollingEvery(Duration.ofSeconds(pollEverySecond))
                    .withMessage(
                            ConsoleUtil.getTextFormat(TextFormat.INFORMATIONAL_MESSAGE_SYMBOL)
                            + " "
                            + "Waiting for element to appear..."
                    ).ignoring(NoSuchElementException.class);

            element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            LOGGER.success("Successfully located and returned WebElement:" + " " + locator );
        } catch (TimeoutException e) {
            LOGGER.error(
                    "Timeout waiting for the element to appear:"
                            + " " + locator + " " + e.getMessage());
        } catch (WebDriverException e) {
            LOGGER.fatal("WebDriver exception occurred:" + " " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("An unexpected exception occurred:" + " " + e.getMessage());
        }

        if (element == null) {
            LOGGER.error(
                    "Couldn't locate element by locator:"
                            + " " + locator);
            return null;
        }
        return element;
    }
    /**
     * <h2>{@code waitForVisibility(...)}: Waits for the specified WebElement to become visible within a given timeout period.</h2>
     * <p>
     * This method applies an explicit wait to check for the visibility of the given WebElement.
     * It waits until the element becomes visible or until the specified timeout duration elapses.
     * If the element does not become visible in the specified time, or if the WebDriver is unavailable,
     * an appropriate error is logged.
     * </p>
     *
     * <h2>Usage:</h2>
     * <p>
     * Use this method when you need to ensure that an element is visible on the page before interacting with it.
     * Explicit waits are preferred for synchronisation in dynamic web pages where elements might take time
     * to appear.
     * </p>
     *
     * <pre><code>
     *     WebElement startBtn = WaitUtil.waitForVisibility(
     *                 driver.findElement(By.xpath("//div[@id='start']/button")),
     *                 10 // timeout after 10 seconds.
     *         );
     * </code></pre>
     *
     * <h2>Exception Handling:</h2>
     * <ul>
     *     <li>If the WebDriver instance is null, an error is logged and the method returns {@code null}.</li>
     *     <li>If the element is not visible within the specified timeout, a {@code TimeoutException} is caught and logged.</li>
     *     <li>If the element cannot be found, a {@code NoSuchElementException} is caught and logged.</li>
     *     <li>If a WebDriver-related error occurs, a {@code WebDriverException} is logged.</li>
     *     <li>Other exceptions, including invalid arguments, are caught and logged as unexpected errors.</li>
     * </ul>
     *
     * @param element the WebElement to wait for visibility.
     * @param timeout the maximum time to wait for the element to become visible, in seconds.
     * @return the visible WebElement, or {@code null} if the element is not visible within the timeout.
     * @see WebDriverWait
     * @see ExpectedConditions
     */
    public static WebElement waitForVisibility(WebElement element, int timeout) {
        if (DriverUtil.getDriverInstance() == null) {
            LOGGER.error("Driver instance is null");
            return null;
        }
        try {
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriverInstance(), Duration.ofSeconds(timeout));
            LOGGER.success(
                    "Successfully located and returned WebElement:" +
                            " " + element
            );
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            LOGGER.error(
                    "Timeout waiting for the element to appear:" +
                            " " + element + " " + e.getMessage());
        } catch (NoSuchElementException e) {
            LOGGER.error(
                    "Element could not be located:" +
                            " " + element + " " + e.getMessage());
        } catch (WebDriverException e) {
            LOGGER.error("WebDriver error occurred:" + " " + e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid argument provided:" + " " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred:" + " " + e.getMessage());
        }
        return null;
    }
    /**
     * <h2>{@code waitForVisibility(...)}: Waits for the WebElement, identified by the specified locator, to become visible within a given timeout period.</h2>
     * <p>
     * This method applies an explicit wait to locate the WebElement using the provided {@code By} locator,
     * waiting until the element becomes visible or the specified timeout is reached. If the element
     * does not become visible within the timeout period, an appropriate exception is caught and logged.
     * </p>
     *
     * <h2>Usage:</h2>
     * <p>
     * This method is ideal for ensuring that elements, which may be dynamically loaded or take time to render,
     * are visible before interacting with them. It should be used when the visibility of an element is necessary
     * to proceed with further operations.
     * </p>
     *
     * <pre><code>
     *     WebElement el = WaitUtil.fluentWait(
     *                 By.xpath("//div[@id='finish']"), // find By xpath locator
     *                 15, // timeout after 15 seconds.
     *                 1 // poll every 1 second.
     *         );
     * </code></pre>
     *
     * <h2>Exception Handling:</h2>
     * <ul>
     *     <li>If the WebDriver instance is null, an error is logged and the method returns {@code null}.</li>
     *     <li>If the element is not visible within the specified timeout, a {@code TimeoutException} is caught and logged.</li>
     *     <li>If the element cannot be found using the specified locator, a {@code NoSuchElementException} is caught and logged.</li>
     *     <li>If a WebDriver-related error occurs, a {@code WebDriverException} is logged.</li>
     *     <li>If an invalid argument is provided, an {@code IllegalArgumentException} is logged.</li>
     *     <li>Other exceptions are caught and logged as unexpected errors.</li>
     * </ul>
     *
     * @param locator the {@code By} locator used to find the WebElement
     * @param timeout the maximum time (in seconds) to wait for the element to become visible
     * @return the visible WebElement, or {@code null} if the element is not visible within the timeout
     * @see WebDriverWait
     * @see ExpectedConditions
     */
    public static WebElement waitForVisibility(By locator, int timeout) {
        if (DriverUtil.getDriverInstance() == null) {
            LOGGER.error("Driver instance is null");
            return null;
        }
        try {
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriverInstance(), Duration.ofSeconds(timeout));
            LOGGER.success(
                    "Successfully located and returned WebElement:" +
                            " " + locator
            );
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            LOGGER.error(
                    "Timeout waiting for the element to appear:" +
                            " " + locator + " " + e.getMessage());
        } catch (NoSuchElementException e) {
            LOGGER.error(
                    "Element could not be located:" +
                            " " + locator + " " + e.getMessage());
        } catch (WebDriverException e) {
            LOGGER.error("WebDriver error occurred:" + " " + e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Invalid argument provided:" + " " + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred:" + " " + e.getMessage());
        }
        return null;
    }
    /**
     * <h2>{@code waitForClickableElement(...)}: Waits for the specified WebElement to become clickable within a given timeout period.</h2>
     * <p>
     * This method applies an explicit wait to check if the given WebElement is clickable within the specified timeout.
     * If the element is not clickable within the timeout duration, or if the WebDriver instance is unavailable,
     * an appropriate exception is caught and logged.
     * </p>
     *
     * <h2>Usage:</h2>
     * <p>
     * This method should be used when you need to ensure that an element is clickable before interacting with it.
     * It is useful for elements that may be dynamically loaded or become interactable only after certain conditions
     * are met.
     * </p>
     *
     * <pre><code>
     *     WebElement el = WaitUtil.waitForClickableElement(
     *                 driver.findElement(By.xpath("//div[@id='finish']")), // locate element by xpath.
     *                 10 // timeout after 10 seconds.
     *         );
     * </code></pre>
     *
     * <h2>Exception Handling:</h2>
     * <ul>
     *     <li>If the WebDriver instance is null, an error is logged and the method returns {@code null}.</li>
     *     <li>If the element does not become clickable within the timeout, a {@code TimeoutException} is caught and logged.</li>
     *     <li>If the element cannot be located, a {@code NoSuchElementException} is caught and logged.</li>
     *     <li>If the element is not interactable, an {@code ElementNotInteractableException} is caught and logged.</li>
     *     <li>If a WebDriver-related error occurs, a {@code WebDriverException} is logged.</li>
     *     <li>If an invalid argument is provided, an {@code IllegalArgumentException} is logged.</li>
     *     <li>Other exceptions are caught and logged as unexpected errors.</li>
     * </ul>
     *
     * @param element the WebElement to wait for to become clickable
     * @param timeout the maximum time (in seconds) to wait for the element to become clickable
     * @return the clickable WebElement, or {@code null} if the element is not clickable within the timeout
     * @see WebDriverWait
     * @see ExpectedConditions
     */
    public static WebElement waitForClickableElement(WebElement element, int timeout) {
        if (DriverUtil.getDriverInstance() == null) {
            LOGGER.error("Driver instance is null");
            return null;
        }
        try {
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriverInstance(), Duration.ofSeconds(timeout));
            LOGGER.success("Successfully located and returned WebElement:" + " " + element);
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            LOGGER.error(
                    "Timeout waiting for the element to become clickable:"
                            + " " + element + " " + e.getMessage());
        } catch (NoSuchElementException e) {
            LOGGER.error(
                    "Element could not be found:"
                            + " " + element + e.getMessage());
        } catch (ElementNotInteractableException e) {
            LOGGER.error(
                    "Element is not interactable:"
                            + " "
                            + element
                            + e.getMessage());
        } catch (WebDriverException e) {
            LOGGER.error("WebDriver error occurred: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error(
                    "Invalid argument provided:"
                            + " " + element + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred: " + e.getMessage());
        }
        return null;
    }
    /**
     * <h2>{@code waitForClickableElement(...)}: Waits for the WebElement, identified by the specified locator, to become clickable within a given timeout period.</h2>
     * <p>
     * This method applies an explicit wait to check if the WebElement, located using the provided {@code By} locator,
     * becomes clickable within the specified timeout. If the element is not clickable within the timeout duration,
     * or if the WebDriver instance is unavailable, an appropriate exception is caught and logged.
     * </p>
     *
     * <h2>Usage:</h2>
     * <p>
     * This method should be used to ensure that elements, identified by locators, are clickable before interacting
     * with them. It is useful for handling scenarios where the element may not be immediately interactable due to
     * dynamic content or page loading.
     * </p>
     *
     * <pre><code>
     *     WebElement el = WaitUtil.waitForClickableElement(
     *                 By.xpath("//adiv[@id='finish']"),
     *                 10
     *         );
     * </code></pre>
     *
     * <h2>Exception Handling:</h2>
     * <ul>
     *     <li>If the WebDriver instance is null, an error is logged and the method returns {@code null}.</li>
     *     <li>If the element does not become clickable within the timeout, a {@code TimeoutException} is caught and logged.</li>
     *     <li>If the element cannot be located using the {@code By} locator, a {@code NoSuchElementException} is caught and logged.</li>
     *     <li>If the element is not interactable, an {@code ElementNotInteractableException} is caught and logged.</li>
     *     <li>If a WebDriver-related error occurs, a {@code WebDriverException} is logged.</li>
     *     <li>If an invalid argument is provided, an {@code IllegalArgumentException} is logged.</li>
     *     <li>Other exceptions are caught and logged as unexpected errors.</li>
     * </ul>
     *
     * @param locator the {@code By} locator used to find the WebElement
     * @param timeout the maximum time (in seconds) to wait for the element to become clickable
     * @return the clickable WebElement, or {@code null} if the element is not clickable within the timeout
     * @see WebDriverWait
     * @see ExpectedConditions
     */
    public static WebElement waitForClickableElement(By locator, int timeout) {
        if (DriverUtil.getDriverInstance() == null) {
            LOGGER.error("Driver instance is null");
            return null;
        }
        try {
            WebDriverWait wait = new WebDriverWait(DriverUtil.getDriverInstance(), Duration.ofSeconds(timeout));
            LOGGER.success("Successfully located and returned WebElement:" + " " + locator);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            LOGGER.error(
                    "Timeout waiting for the element to become clickable:"
                            + " " + locator + " " + e.getMessage());
        } catch (NoSuchElementException e) {
            LOGGER.error(
                    "Element could not be found:"
                            + " " + locator + e.getMessage());
        } catch (ElementNotInteractableException e) {
            LOGGER.error(
                    "Element is not interactable:"
                            + " "
                            + locator
                            + e.getMessage());
        } catch (WebDriverException e) {
            LOGGER.error("WebDriver error occurred: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error(
                    "Invalid argument provided:"
                            + " " + locator + e.getMessage());
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred: " + e.getMessage());
        }
        return null;
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
        LOGGER.info("Default implicit wait time set to:" + " " + DEFAULT_IMPLICIT_WAIT_TIME + " " + "seconds.");
        return DEFAULT_IMPLICIT_WAIT_TIME;
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