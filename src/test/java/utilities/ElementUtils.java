package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.List;
import java.util.Objects;

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
    /**
     * <h2>{@code clickByIndex(...)}: Clicks on a web element located by its index within a list of elements found by the provided {@link By} locator.</h2>
     * <p>The method waits for the elements to become clickable within the specified timeout period, then clicks the element
     * at the given index.</p>
     *
     * <p>If the list of elements is not empty and the index is valid, the method clicks the specified element and logs
     * a success message. If the list is empty or {@code null}, no action is taken.</p>
     *
     * @param elements      The {@link By} locator used to identify the list of web elements.
     * @param timeout       The maximum amount of time (in seconds) to wait for the elements to become clickable.
     * @param elementIndex  The index of the element to be clicked within the list of located elements.
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
    public static void clickByIndex(By elements, int timeout, int elementIndex) {
        List<WebElement> listOfElements = WaitUtil.getListOfClickableElements(elements, timeout);
        assert listOfElements != null;
        if (!listOfElements.isEmpty()) {
            listOfElements.get(elementIndex).click();
            LOGGER.success("Successfully clicked on element index" + " " + elementIndex + " " + listOfElements.get(elementIndex));
        }
    }
    /**
     * <h2>{@code selectDropdownByIndex(...)}: Selects an option in a dropdown element by its index, after waiting for the dropdown to become clickable within
     * the specified timeout period.</h2>
     *
     * <p>The method waits for the dropdown element to be clickable using {@link WaitUtil#waitForClickableElement(WebElement, int)}
     * and then selects the option at the provided index. A success message is logged if the selection is successful.</p>
     *
     * @param element  The {@link WebElement} representing the dropdown.
     * @param timeout  The maximum time (in seconds) to wait for the dropdown to become clickable.
     * @param index    The index of the option to be selected within the dropdown.
     *
     * <p>Assumes that any exceptions, such as {@link NoSuchElementException}, {@link TimeoutException}, and
     * {@link ElementNotInteractableException}, are handled by {@link WaitUtil#waitForClickableElement(WebElement, int)}.</p>
     *
     * <p>This method logs:</p>
     * <ul>
     *     <li>Success when the dropdown option is selected by index.</li>
     *     <li>Error if the dropdown element is {@code null} or not clickable within the timeout period (handled by {@link WaitUtil}).</li>
     * </ul>
     *
     * @throws NullPointerException If the provided element is {@code null}.
     * @throws NoSuchElementException If the dropdown does not contain an option at the specified index.
     */
    public static void selectDropdownByIndex(WebElement element, int timeout, int index) {
        Select dropdown = new Select(Objects.requireNonNull(WaitUtil.waitForClickableElement(element, timeout)));
        if (!dropdown.getOptions().isEmpty()) {
            dropdown.selectByIndex(index);
            LOGGER.success("Successfully selected dropdown option by index" + " " + index);
        }
    }
    /**
     * <h2>{@code selectDropdownByVisibleText(...)}: Selects an option from a dropdown menu based on the visible text.</h2>
     *
     * <p>This method waits for the specified dropdown element to be clickable within the given timeout,
     * then selects an option matching the provided visible text. If the selection is successful,
     * a log message is recorded to confirm the action.</p>
     *
     * <p>This method assumes that exceptions are handled through the {@link WaitUtil#waitForClickableElement(WebElement, int)}</p>
     *
     * @param element the {@link WebElement} representing the dropdown menu
     * @param timeout the maximum time to wait, in seconds, for the dropdown to become clickable
     * @param text the visible text of the option to be selected within the dropdown
     *
     */
    public static void selectDropdownByVisibleText(WebElement element, int timeout, String text) {
        Select dropdown = new Select(Objects.requireNonNull(WaitUtil.waitForClickableElement(element, timeout)));
        if (!dropdown.getOptions().isEmpty()) {
            dropdown.selectByVisibleText(text);
            LOGGER.success("Successfully selected dropdown option by text:" + " " + text);
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
