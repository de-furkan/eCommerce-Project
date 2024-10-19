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
     * If the element becomes visible within the specified timeout period, it is clicked.
     * In case of any errors during the interaction, appropriate log messages are generated.
     *
     * @param element           The WebElement to be clicked.
     * @param timeoutInSeconds   The maximum amount of time (in seconds) to wait for the element's visibility.
     *
     * @throws NoSuchElementException          If the element cannot be found in the DOM.
     * @throws ElementNotInteractableException If the element is present but not interactable.
     * @throws TimeoutException                If the element is not visible within the specified timeout.
     * @throws WebDriverException              If any WebDriver-related error occurs during the click.
     * @throws Exception                       If any other unexpected error occurs.
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
            try {
                visibleElement.click();
                LOGGER.success("Successfully clicked on element: " + visibleElement);
            } catch (NoSuchElementException e) {
                LOGGER.error("Element not found: " + visibleElement + " - " + e.getMessage());
            } catch (ElementNotInteractableException e) {
                LOGGER.error("Element is not interactable: " + visibleElement + " - " + e.getMessage());
            } catch (TimeoutException e) {
                LOGGER.error("Timed out waiting for element: " + visibleElement + " - " + e.getMessage());
            } catch (WebDriverException e) {
                LOGGER.error("WebDriver error: " + e.getMessage());
            } catch (Exception e) {
                LOGGER.error("Unexpected error occurred: " + e.getMessage());
            }
        } else {
            LOGGER.error("Element was not visible within the timeout period.");
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
