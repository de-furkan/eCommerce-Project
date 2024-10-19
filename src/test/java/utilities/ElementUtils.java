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
    /**
     * <h2>{@code getTextWithTimeout(...)}: Retrieves the text of a specified web element, waiting for its visibility before attempting to access its text.</h2>
     * If the element becomes visible within the specified timeout period, its text is retrieved and returned.
     * In case of any errors during the interaction, appropriate log messages are generated.
     *
     * @param element           The WebElement from which to retrieve the text.
     * @param timeoutInSeconds   The maximum amount of time (in seconds) to wait for the element's visibility.
     *
     * @return                  The text content of the element, or {@code null} if the element is not visible or an error occurs.
     *
     * @throws NoSuchElementException          If the element cannot be found in the DOM.
     * @throws ElementNotInteractableException If the element is present but not interactable.
     * @throws StaleElementReferenceException  If the element becomes stale before or during text retrieval.
     * @throws WebDriverException              If any WebDriver-related error occurs while interacting with the element.
     * @throws Exception                       If any other unexpected error occurs.
     *
     * <p>This method logs:</p>
     * <ul>
     *     <li>Errors when the element is not found, not interactable, stale, or any WebDriver error occurs.</li>
     *     <li>Errors if the element is not visible within the timeout period.</li>
     * </ul>
     */

    public static String getTextWithTimeout(WebElement element, int timeoutInSeconds) {
        WebElement visibleElement = WaitUtil.waitForVisibility(element, timeoutInSeconds);
        String webElementText = null;

        if (visibleElement != null) {
            try {
                 webElementText = visibleElement.getText();
            } catch (NoSuchElementException e) {
                LOGGER.error("Element not found: " + visibleElement + " - " + e.getMessage());
            } catch (ElementNotInteractableException e) {
                LOGGER.error("Element is not interactable: " + visibleElement + " - " + e.getMessage());
            } catch (StaleElementReferenceException e) {
                LOGGER.error("Element became stale: " + visibleElement + " - " + e.getMessage());
            } catch (WebDriverException e) {
                LOGGER.error("WebDriver error occurred: " + e.getMessage());
            } catch (Exception e) {
                LOGGER.error("Unexpected error occurred: " + e.getMessage());
            }
        } else {
            LOGGER.error("Element was not visible within the timeout period.");
        }
        return  webElementText;
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
