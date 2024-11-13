package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    public static void doubleClickElementWithTimeout(WebElement element, int timeoutInSeconds) {
        WebElement visibleElement = WaitUtil.waitForClickableElement(element, timeoutInSeconds);
        if (visibleElement != null) {
            ActionsUtil.doubleClickOnElement(visibleElement);
            LOGGER.success("SUCCESS: double-clicked on element: " + visibleElement);
        }
    }

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

    public static void clearAndSendKeysWithTimeout(WebElement element, String sendKeys , int timeoutInSeconds) {
        WebElement visibleElement = WaitUtil.waitForClickableElement(element, timeoutInSeconds);
        if (visibleElement != null) {
            visibleElement.clear();
            visibleElement.sendKeys(sendKeys);
            LOGGER.success("SUCCESS: cleared text field and inserted text: " +  sendKeys);
        }
    }

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
