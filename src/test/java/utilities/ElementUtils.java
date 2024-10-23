package utilities;

import org.openqa.selenium.*;

public class ElementUtils {
    /*
     *****************************************
     *          1. constants
     *****************************************
     */
    private static final LoggerUtil LOGGER = new LoggerUtil(ElementUtils.class);
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
    private ElementUtils() {}

    /*
     *****************************************
     *          6. public methods
     *     (including getters/setters)
     *****************************************
     */
    /**
     * <h2>{@code clickElementWithTimeout(...)}: Clicks on a specified web element, waiting for its visibility before attempting the click.</h2>
     * <p>If the element becomes visible within the specified timeout period, it is clicked.
     * In case of any errors during the interaction, appropriate log messages are generated.</p>
     *
     * <p>Any exceptions related to element click-ability, interaction, or timeouts are handled by the {@link WaitUtil#waitForClickableElement(WebElement, int)} method.</p>
     *
     * @param element           The WebElement to be clicked.
     * @param timeoutInSeconds   The maximum amount of time (in seconds) to wait for the element's visibility.
     *
     * <p>This method logs:</p>
     * <ul>
     *     <li>Success when the element is successfully clicked.</li>
     *     <li>Error when the element is not found, not interactable, times out, or any WebDriver error occurs.</li>
     * </ul>
     */
    public static void clickElementWithTimeout(WebElement element, int timeoutInSeconds) {
        WebElement visibleElement = WaitUtil.waitForClickableElement(element, timeoutInSeconds);
        if (visibleElement != null) {
            visibleElement.click();
            LOGGER.success("Successfully clicked on element: " + visibleElement);
        }
    }
    /**
     * <h2>{@code getTextWithTimeout(...)}: Retrieves the text of a specified web element, waiting for its visibility before attempting to access its text.</h2>
     * <p>If the element becomes visible within the specified timeout period, its text is retrieved and returned.
     * In case of any errors during the interaction, appropriate log messages are generated.</p>
     *
     * <p>Any exceptions related to element visibility, interaction, or timeouts are handled by the {@link WaitUtil#waitForVisibility(WebElement, int)} method.</p>
     *
     * @param element           The WebElement from which to retrieve the text.
     * @param timeoutInSeconds   The maximum amount of time (in seconds) to wait for the element's visibility.
     *
     * @return                  The text content of the element, or {@code null} if the element is not visible or an error occurs.
     *
     * <p>This method logs:</p>
     * <ul>
     *     <li>Errors when the element is not found, not interactable, stale, or any WebDriver error occurs.</li>
     *     <li>Errors if the element is not visible within the timeout period.</li>
     * </ul>
     */
    public static String getTextWithTimeout(WebElement element, int timeoutInSeconds) {
        WebElement visibleElement = WaitUtil.waitForVisibility(element, timeoutInSeconds);
        String webElementText = "Empty";
        if (visibleElement != null) {
            webElementText = visibleElement.getText();
            LOGGER.success("Successfully copied element text:" + " " + webElementText + " " + "from element:" + " " + element);
        }
        return webElementText;
    }
    /**
     * <h2>{@code sendKeysWithTimeout()}: Sends the specified text to a web element after waiting for it to become visible.</h2>
     * <p>If the element becomes visible within the given timeout period, the provided text is entered into the element.</p>
     *
     *
     * <p>This method assumes that all relevant exceptions, such as {@link NoSuchElementException},
     * {@link ElementNotInteractableException}, {@link TimeoutException}, and {@link WebDriverException},
     * are already managed by {@link WaitUtil#waitForVisibility(WebElement, int)}.</p>
     *
     * <p>This method logs:</p>
     * <ul>
     *     <li>Success when the text is successfully sent to the element.</li>
     *     <li>Error if the element is not visible or interactable within the timeout period (handled by {@link WaitUtil}).</li>
     * </ul>
     * @param element   The WebElement to which the text will be sent.
     * @param timeout   The maximum amount of time (in seconds) to wait for the element to become visible.
     * @param sendKeys  The text to be sent to the web element.
     *
     */
    public static void sendKeysWithTimeout(WebElement element, int timeout, String sendKeys) {
        WebElement visibleElement = WaitUtil.waitForVisibility(element, timeout);
        if (visibleElement != null) {
            visibleElement.sendKeys(sendKeys);
            LOGGER.success("Successfully sent the text" + " " + sendKeys + " " + "to the given element:" + " " + element);
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
