package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import java.util.List;
/**
 * <h1>Utility class for performing common interactions and actions on web elements.</h1>
 *
 * <p>The {@code GenericElementUtils} class provides a collection of static methods
 * designed to perform frequently used actions on {@link WebElement} objects, including
 * clicking, double-clicking, right-clicking, sending keys, and retrieving attribute or
 * CSS property values, among others.</p>
 *
 * <p>This utility class leverages timeout functionality to handle dynamic loading times,
 * ensuring that elements are interactable or visible before actions are performed.
 * Methods in this class rely on {@link WaitUtil} for wait management and on {@link ActionsUtil}
 * for complex actions like double-clicking and context-clicking.</p>
 *
 * <h2>Class Structure</h2>
 * <ul>
 *     <li><strong>Constants</strong>: Holds the LOGGER for consistent logging across all methods.</li>
 *     <li><strong>Public Methods</strong>: Provides functionality to interact with web elements
 *     in a reliable, wait-assisted manner.</li>
 *     <li><strong>Private Methods</strong>: (Currently not utilised) Reserved for any internal utility methods
 *     that may be added in the future.</li>
 * </ul>
 *
 * <h2>Usage</h2>
 * <p>Each method is designed to wait for a specified timeout period before executing the action,
 * providing stability for tests and interactions in dynamic environments.
 * Log messages are recorded for each action's success or failure, aiding in debugging and
 * traceability.</p>
 *
 * <pre><code>
 *     GenericElementUtils.getAttributeValueWithTimeout(
 *                 btnDel,
 *                 "onclick",
 *                 10
 *         );
 * </code></pre>
 *
 * <h2>Logging</h2>
 * <p>All actions are logged using {@link LoggerUtil}, with success logs upon completion of the action
 * and error logs if the action cannot be performed, whether due to visibility, interactivity, or
 * non-existent values.</p>
 *
 * <h2>Exceptions</h2>
 * <p>Exceptions specific to element visibility, interaction, and timeouts are primarily managed
 * by {@link WaitUtil}, which handles common WebDriver exceptions.</p>
 *
 * @since 2024
 * @see WaitUtil
 * @see ActionsUtil
 * @see LoggerUtil
 * @author Furkan O.
 * @version 1.0
 */
public class GenericElementUtils {

    /*
     *****************************************
     *          1. constants
     *****************************************
     */
    private static final LoggerUtil LOGGER = new LoggerUtil(GenericElementUtils.class);
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
            LOGGER.success("SUCCESS: clicked on element: " + visibleElement);
            return;
        }
    }
    /**
     * <h2>{@code doubleClickElementWithTimeout(...)}: Performs a double-click action on a specified element within a given timeout.</h2>
     *
     * <p>This method waits for the provided element to become clickable within the specified timeout period.
     * If the element is found and clickable, it performs a double-click action on the element and logs a success message.
     * If the element does not become clickable within the timeout, the method exits without performing any action.</p>
     *
     * @param element the {@link WebElement} to be double-clicked
     * @param timeoutInSeconds the maximum wait time, in seconds, for the element to become clickable
     *
     * @throws NullPointerException if the specified element is {@code null}
     */
    public static void doubleClickElementWithTimeout(WebElement element, int timeoutInSeconds) {
        WebElement visibleElement = WaitUtil.waitForClickableElement(element, timeoutInSeconds);
        if (visibleElement != null) {
            ActionsUtil.doubleClickOnElement(visibleElement);
            LOGGER.success("SUCCESS: double-clicked on element: " + visibleElement);
        }
    }
    /**
     * <h2>{@code rightClickElementWithTimeout(...)}: Performs a right-click (context-click) action on a specified element within a given timeout.</h2>
     *
     * <p>This method waits for the provided element to become clickable within the specified timeout period.
     * If the element is found and clickable, it performs a right-click action on the element and logs a success message.
     * If the element does not become clickable within the timeout, the method exits without performing any action.</p>
     *
     * @param element the {@link WebElement} to be right-clicked
     * @param timeoutInSeconds the maximum wait time, in seconds, for the element to become clickable
     *
     * @throws NullPointerException if the specified element is {@code null}
     */
    public static void rightClickElementWithTimeout(WebElement element, int timeoutInSeconds) {
        WebElement visibleElement = WaitUtil.waitForClickableElement(element, timeoutInSeconds);
        if (visibleElement != null) {
            ActionsUtil.contextClickOnElement(visibleElement);
            LOGGER.success("SUCCESS: right-clicked on element: " + visibleElement);
        }
    }
    /**
     * <h2>{@code clickElementByIndexWithTimeout(...)}: Clicks on a web element located by its index within a list of elements found by the provided {@link By} locator.</h2>
     * <p>The method waits for the elements to become clickable within the specified timeout period, then clicks the element
     * at the given index.</p>
     *
     * <p>If the list of elements is not empty and the index is valid, the method clicks the specified element and logs
     * a success message. If the list is empty or {@code null}, no action is taken.</p>
     *
     * @param elements The {@link By} locator used to identify the list of web elements.
     * @param index The index of the element to be clicked within the list of located elements.
     * @param timeoutInSeconds The maximum amount of time (in seconds) to wait for the elements to become clickable.
     *<br/><br/>
     * <p>This method assumes that any exceptions related to visibility, timeouts, or interaction with the elements are
     * handled by {@link WaitUtil#getListOfClickableElements(By, int)}.</p>
     *
     * <p>This method logs:</p>
     * <ul>
     *     <li>Success when the element at the specified index is successfully clicked.</li>
     *     <li>Error if no elements are found or if the list of elements is {@code null}.</li>
     * </ul>
     *
     * @throws IndexOutOfBoundsException If the provided index is out of bounds for the list of elements.
     */
    public static void clickElementByIndexWithTimeout(By elements, int index, int timeoutInSeconds) {
        List<WebElement> listOfElements = WaitUtil.getListOfClickableElements(elements, timeoutInSeconds);
        assert listOfElements != null;
        if (!listOfElements.isEmpty()) {
            listOfElements.get(index).click();
            LOGGER.success("SUCCESS: clicked on element index" + " " + index + " " + listOfElements.get(index));
        }
    }
    /**
     * <h2>{@code sendKeysWithTimeout(...)}: Sends the specified text to a web element after waiting for it to become visible.</h2>
     * <p>If the element becomes visible within the given timeout period, the provided text is entered into the element.</p>
     *
     *
     * <p>This method assumes that all relevant exceptions, such as {@link java.util.NoSuchElementException},
     * {@link org.openqa.selenium.ElementNotInteractableException}, {@link org.openqa.selenium.TimeoutException},
     * and {@link org.openqa.selenium.WebDriverException},
     * are already managed by {@link WaitUtil#waitForVisibility(WebElement, int)}.</p>
     *
     * <p>This method logs:</p>
     * <ul>
     *     <li>Success when the text is successfully sent to the element.</li>
     *     <li>Error if the element is not visible or interactable within the timeout period (handled by {@link WaitUtil}).</li>
     * </ul>
     * @param element The WebElement to which the text will be sent.
     * @param timeoutInSeconds The maximum amount of time (in seconds) to wait for the element to become visible.
     * @param sendKeys The text to be sent to the web element.
     *
     */
    public static void sendKeysWithTimeout(WebElement element, String sendKeys, int timeoutInSeconds) {
        WebElement visibleElement = WaitUtil.waitForVisibility(element, timeoutInSeconds);
        if (visibleElement != null) {
            visibleElement.sendKeys(sendKeys);
            LOGGER.success("SUCCESS: sent the text" + " " + sendKeys + " " + "to the given element:" + " " + element);
        }
    }
    /**
     * <h2>{@code clearAndSendKeysWithTimeout(...)}: Clears the existing text and sends new input to a specified element within a given timeout.</h2>
     *
     * <p>This method waits for the provided element to become clickable within the specified timeout period.
     * Once clickable, it clears any existing text from the element, inputs the specified text,
     * and logs a success message confirming the action. If the element does not become clickable within
     * the timeout, the method exits without performing any action.</p>
     *
     * @param element the {@link WebElement} representing the input field to be cleared and populated
     * @param sendKeys the text to be entered into the input field after clearing any existing text
     * @param timeoutInSeconds the maximum wait time, in seconds, for the element to become clickable
     *
     * @throws NullPointerException if the specified element is {@code null}
     */
    public static void clearAndSendKeysWithTimeout(WebElement element, String sendKeys , int timeoutInSeconds) {
        WebElement visibleElement = WaitUtil.waitForClickableElement(element, timeoutInSeconds);
        if (visibleElement != null) {
            visibleElement.clear();
            visibleElement.sendKeys(sendKeys);
            LOGGER.success("SUCCESS: cleared text field and inserted text: " +  sendKeys);
        }
    }
    /**
     * <h2>{@code clearFieldWithTimeout(...)}: Clears the text from a specified input field within a given timeout.</h2>
     *
     * <p>This method waits for the provided input field to become clickable within the specified timeout period.
     * If the field becomes clickable, any existing text is cleared, and a success message is logged.
     * If the field does not become clickable within the timeout, the method exits without performing any action.</p>
     *
     * @param element the {@link WebElement} representing the input field to be cleared
     * @param timeoutInSeconds the maximum wait time, in seconds, for the input field to become clickable
     *
     * @throws NullPointerException if the specified element is {@code null}
     */
    public static void clearFieldWithTimeout(WebElement element, int timeoutInSeconds) {
        WebElement visibleElement = WaitUtil.waitForClickableElement(element, timeoutInSeconds);
        if (visibleElement != null) {
            visibleElement.clear();
            LOGGER.success("SUCCESS: cleared text field.");
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
            LOGGER.success("SUCCESS: copied element text:" + " " + webElementText + " " + "from element:" + " " + element);
        }
        return webElementText;
    }
    /**
     * <h2>{@code getAttributeValueWithTimeout(...)}: Retrieves the value of a specified attribute from an element within a given timeout.</h2>
     *
     * <p>This method waits for the provided element to become clickable within the specified timeout period.
     * If the element is clickable, it retrieves the value of the specified attribute.
     * If the attribute is missing or blank, an error message is logged, and an empty string is returned.
     * A success message is logged if the attribute value is successfully retrieved.</p>
     *
     * @param element the {@link WebElement} from which the attribute value is to be retrieved
     * @param lookupAttribute the name of the attribute to be retrieved from the element
     * @param timeoutInSeconds the maximum wait time, in seconds, for the element to become clickable
     * @return the value of the specified attribute, or an empty string if the attribute is blank or does not exist
     *
     * @throws NullPointerException if the specified element is {@code null}
     */
    public static String getAttributeValueWithTimeout(WebElement element, String lookupAttribute,int timeoutInSeconds) {
        WebElement visibleElement = WaitUtil.waitForClickableElement(element, timeoutInSeconds);
        String attributeValue = "";
        if (visibleElement != null) {
            attributeValue = visibleElement.getAttribute(lookupAttribute);
            if (attributeValue == null || attributeValue.isBlank()) {
                LOGGER.error("ERROR: could not return the requested attribute value. " +
                        "The attribute is either blank or does not exist.");
                return "";
            }
        }
        LOGGER.success("SUCCESS: returned the specified attribute for " + lookupAttribute + ": " + attributeValue);
        return attributeValue;
    }
    /**
     * <h2>{@code getCssValueWithTimeout(...)}: Retrieves the value of a specified CSS property from an element within a given timeout.</h2>
     *
     * <p>This method waits for the provided element to become clickable within the specified timeout period.
     * If the element is clickable, it retrieves the value of the specified CSS property.
     * If the CSS value is blank, an error message is logged, and an empty string is returned.</p>
     *
     * @param element the {@link WebElement} from which the CSS value is to be retrieved
     * @param lookupCssValue the name of the CSS property to be retrieved from the element
     * @param timeoutInSeconds the maximum wait time, in seconds, for the element to become clickable
     * @return the value of the specified CSS property, or an empty string if the property is blank or does not exist
     *
     * @throws NullPointerException if the specified element is {@code null}
     */
    public static String getCssValueWithTimeout(WebElement element, String lookupCssValue,int timeoutInSeconds) {
        WebElement visibleElement = WaitUtil.waitForClickableElement(element, timeoutInSeconds);
        String cssValue = "";
        if (visibleElement != null) {
            cssValue = visibleElement.getCssValue(lookupCssValue);
            if (cssValue.isBlank()) {
                LOGGER.error("ERROR: could not return the requested CSS value. " +
                        "The CSS value is either blank or does not exist.");
                return "";
            }
        }
        return cssValue;
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