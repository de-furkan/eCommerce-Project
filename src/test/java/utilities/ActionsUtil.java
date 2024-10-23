package utilities;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
/**
 * <h1>Utility class providing commonly used Selenium WebDriver Actions methods.</h1>
 * <p>
 * This class encapsulates various static utility methods for performing mouse and keyboard actions
 * such as scrolling, clicking, double-clicking, context-clicking, and drag-and-drop operations.
 * It ensures thread safety by creating new instances of {@link org.openqa.selenium.interactions.Actions}
 * for each method call rather than maintaining a static reference.
 * </p>
 * <p>
 * Usage of this class ensures that actions like scrolling and clicking are safely and efficiently
 * executed across different threads, leveraging Selenium's {@link org.openqa.selenium.interactions.Actions} API.
 * </p>
 * <h2>Design Notes:</h2>
 * <ul>
 *   <li>Methods here avoid referencing the {@link org.openqa.selenium.interactions.Actions} class statically
 *   to ensure thread-safety and enable garbage collection.</li>
 *   <li>Private constructor to prevent instantiation.</li>
 * </ul>
 *
 * <h2>Thread-Safety Consideration:</h2>
 * <p>
 * For each action performed, this class retrieves a new instance of the WebDriver via the
 * {@link DriverUtil#getDriverInstance()} method to guarantee thread-safety in environments
 * where multiple threads are used to interact with WebElements.
 * </p>
 *
 * <h2>Methods Provided:</h2>
 * <ul>
 *   <li>{@code scrollToElement(WebElement element)}: Scrolls the viewport to a specific element.</li>
 *   <li>{@code scrollPageDown()}: Scrolls the page down using the {@code PAGE_DOWN} key.</li>
 *   <li>{@code clickOnElement(WebElement element)}: Clicks on a WebElement.</li>
 *   <li>{@code dragAndDrop(WebElement source, WebElement target)}: Performs a drag-and-drop action.</li>
 *   <!-- For more method summaries please refer to method documentations. -->
 * </ul>
 *
 * @author Furkan O.
 * @version 1.0
 * @since 2024
 */
public class ActionsUtil {
    /*
     *****************************************
     *          1. CONSTANTS
     *****************************************
     */
    /*
        - Avoid referencing the Actions class as below.
        - Instead use an anonymous new Actions without referencing:
            - Ensures Thread-safety.
            - Ensures immediate 'garbage collection' after use.

        private static final Actions action = new Actions(DriverUtil.getDriverInstance());
    */
    /*
     *****************************************
     *          2. Static fields
     *****************************************
     */

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
    private ActionsUtil() {};
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
     * <h2>{@code scrollToElement(...)}: Scrolls the viewport until the specified WebElement is in view.</h2>
     * <p>
     * This method uses the Actions class to scroll the page so that the specified WebElement
     * becomes visible within the viewport. The WebDriver instance is obtained from the
     * DriverUtil utility class ensuring Thread-Safety.
     * </p>
     *
     * @param element the WebElement to scroll into view.
     * @throws NullPointerException if the specified element is null.
     * @see org.openqa.selenium.interactions.Actions#scrollToElement(org.openqa.selenium.WebElement)
     */
    public static void scrollToElement(WebElement element) {
        new Actions(DriverUtil.getDriverInstance()).scrollToElement(element).perform();
    }
    /**
     * <h2>{@code scrollPageDown()}: Scrolls the webpage down by simulating the PAGE_DOWN key press.</h2>
     * <p>
     * This method uses the Actions class to send the PAGE_DOWN key, which scrolls the page
     * down by approximately one viewport height. The WebDriver instance is retrieved from the DriverUtil
     * utility class ensuring Thread-Safety.
     * </p>
     *
     * @see org.openqa.selenium.interactions.Actions#sendKeys(CharSequence...)
     */
    public static void scrollPageDown() {
        new Actions(DriverUtil.getDriverInstance()).sendKeys(Keys.PAGE_DOWN).perform();
    }
    /**
     * <h2>{@code scrollPageUp()}: Scrolls the webpage up by simulating the PAGE_UP key press.</h2>
     * <p>
     * This method uses the Actions class to send the PAGE_UP key, which scrolls the page
     * up by approximately one viewport height. The WebDriver instance is retrieved from the DriverUtil
     * utility class ensuring Thread-Safety.
     * </p>
     *
     * @see org.openqa.selenium.interactions.Actions#sendKeys(CharSequence...)
     */
    public static void scrollPageUp() {
        new Actions(DriverUtil.getDriverInstance()).sendKeys(Keys.PAGE_UP).perform();
    }
    /**
     * <h2>{@code scrollPageRight()}: Scrolls the webpage horizontally to the right by simulating the ARROW_RIGHT key press.</h2>
     * <p>
     * This method uses the Actions class to send the ARROW_RIGHT key, which scrolls the page
     * to the right by a small increment. The WebDriver instance is retrieved from the DriverUtil utility class
     * ensuring Thread-Safety.
     * </p>
     *
     * @see org.openqa.selenium.interactions.Actions#sendKeys(CharSequence...)
     */
    public static void scrollPageRight(){
        new Actions(DriverUtil.getDriverInstance()).sendKeys(Keys.ARROW_RIGHT).perform();
    }
    /**
     * <h2>{@code scrollPageLeft()}: Scrolls the webpage horizontally to the left by simulating the ARROW_LEFT key press.</h2>
     * <p>
     * This method uses the Actions class to send the ARROW_LEFT key, which scrolls the page
     * to the left by a small increment. The WebDriver instance is retrieved from the DriverUtil utility class
     * ensuring Thread-Safety.
     * </p>
     *
     * @see org.openqa.selenium.interactions.Actions#sendKeys(CharSequence...)
     */
    public static void scrollPageLeft(){
        new Actions(DriverUtil.getDriverInstance()).sendKeys(Keys.ARROW_LEFT).perform();
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
     * <h2>{@code scrollToElementFirst(...)}: Scrolls the viewport to the specified WebElement if the {@code scrollToElementFirst} flag is true.</h2>
     * <p>
     * This method conditionally scrolls the page to bring the given WebElement into view, based on
     * the value of the {@code scrollToElementFirst} flag. If the flag is false, a {@code RuntimeException}
     * is thrown to indicate incorrect usage.
     * </p>
     *
     * @param element the WebElement to scroll to if scrolling is required.
     * @param scrollToElementFirst a boolean flag indicating whether to scroll to the element.
     * @throws RuntimeException if the {@code scrollToElementFirst} flag is set to false.
     * @throws NullPointerException if the specified element is null.
     * @see #scrollToElement(WebElement)
     */
    private static void scrollToElementFirst(WebElement element, boolean scrollToElementFirst) {
        if (scrollToElementFirst) {
            scrollToElement(element);
        } else {
            throw new RuntimeException("Incorrect usage, scrollToElementFirst is set to false.");
        }
    }
    /*
     *****************************************
     *          9. Overloading methods
     *****************************************
     */
    /**
     * <h2>{@code clickOnElement(...)}: Simulates a click action on the specified WebElement.</h2>
     * <p>
     * This method uses the Actions class to perform a click operation on the given
     * WebElement. The WebDriver instance is retrieved from the DriverUtil utility class ensuring
     * Thread-Safety.
     * </p>
     *
     * @param element the WebElement to be clicked.
     * @throws NullPointerException if the specified element is null.
     * @see org.openqa.selenium.interactions.Actions#click(org.openqa.selenium.WebElement)
     */
    public static void clickOnElement(WebElement element) {
        new Actions(DriverUtil.getDriverInstance()).click(element).perform();
    }
    /**
     * <h2>{@code clickOnElement(...)}: Clicks on the specified WebElement with an optional scroll-to-element action.</h2>
     * <p>
     * This method performs a click action on the given WebElement. If the
     * {@code scrollToElementFirst} flag is set to true, the method will first scroll the
     * element into view before performing the click. The WebDriver instance is retrieved
     * from the DriverUtil utility class ensuring Thread-Safety.
     * </p>
     *
     * @param element the WebElement to be clicked.
     * @param scrollToElementFirst if true, the method will scroll to the element before clicking.
     * @throws NullPointerException if the specified element is null.
     * @see org.openqa.selenium.interactions.Actions#click(org.openqa.selenium.WebElement)
     */
    public static void clickOnElement(WebElement element, boolean scrollToElementFirst) {
        scrollToElementFirst(element, scrollToElementFirst);
        new Actions(DriverUtil.getDriverInstance()).click(element).perform();
    }
    /**
     * <h2>{@code contextClickOnElement(...)}: Performs a context-click (right-click) on the specified WebElement.</h2>
     * <p>
     * This method uses the Actions class to simulate a right-click action on the
     * WebElement. The WebDriver instance is obtained from the DriverUtil utility class ensuring Thread-Safety.
     * </p>
     *
     * @param element the WebElement to be right-clicked.
     * @throws NullPointerException if the specified element is null.
     * @see org.openqa.selenium.interactions.Actions#contextClick(org.openqa.selenium.WebElement)
     */
    public static void contextClickOnElement(WebElement element) {
        new Actions(DriverUtil.getDriverInstance()).contextClick().perform();
    }
    /**
     * <h2>{@code contextClickOnElement(...)}: Performs a context-click (right-click) on the specified WebElement with an optional scroll-to-element action.</h2>
     * <p>
     * This method simulates a right-click (context-click) on the given WebElement. If the
     * {@code scrollToElementFirst} flag is set to true, the method will first scroll the
     * element into view before performing the right-click. The WebDriver instance is retrieved
     * from the DriverUtil utility class ensuring Thread-Safety.
     * </p>
     *
     * @param element the WebElement to be right-clicked.
     * @param scrollToElementFirst if true, the method will scroll to the element before performing the right-click.
     * @throws NullPointerException if the specified element is null.
     * @see org.openqa.selenium.interactions.Actions#contextClick(org.openqa.selenium.WebElement)
     */
    public static void contextClickOnElement(WebElement element, boolean scrollToElementFirst) {
        scrollToElementFirst(element, scrollToElementFirst);
        new Actions(DriverUtil.getDriverInstance()).contextClick().perform();
    }
    /**
     * <h2>{@code doubleClickOnElement(...)}: Performs a double click action on the specified WebElement.</h2>
     * <p>
     * This method uses the Actions class to perform a double-click operation on the given
     * WebElement. The WebDriver instance is retrieved from the DriverUtil utility class ensuring Thread-Safety.
     * </p>
     *
     * @param element the WebElement to be double-clicked.
     * @throws NullPointerException if the specified element is null.
     * @see org.openqa.selenium.interactions.Actions#doubleClick(org.openqa.selenium.WebElement)
     */
    public static void doubleClickOnElement(WebElement element) {
        new Actions(DriverUtil.getDriverInstance()).doubleClick(element).perform();
    }
    /**
     * <h2>{@code doubleClickOnElement(...)}: Performs a double-click action on the specified WebElement with an optional scroll-to-element action.</h2>
     * <p>
     * This method simulates a double-click on the given WebElement. If the
     * {@code scrollToElementFirst} flag is set to true, the method will first scroll the
     * element into view before performing the double-click. The WebDriver instance is retrieved
     * from the DriverUtil utility class ensuring Thread-Safety.
     * </p>
     *
     * @param element the WebElement to be double-clicked.
     * @param scrollToElementFirst if true, the method will scroll to the element before performing the double click.
     * @throws NullPointerException if the specified element is null.
     * @see org.openqa.selenium.interactions.Actions#doubleClick(org.openqa.selenium.WebElement)
     */
    public static void doubleClickOnElement(WebElement element, boolean scrollToElementFirst) {
        scrollToElementFirst(element, scrollToElementFirst);
        new Actions(DriverUtil.getDriverInstance()).doubleClick(element).perform();
    }
    /**
     * <h2>{@code hoverOnElement(...)}: Moves the mouse pointer over the specified WebElement.</h2>
     * <p>
     * This method uses the Actions class to simulate hovering over the given WebElement.
     * It triggers hover effects such as tooltips or CSS changes that occur on mouse-over.
     * The WebDriver instance is retrieved from the DriverUtil utility class ensuring Thread-Safety.
     * </p>
     *
     * @param element the WebElement to hover over.
     * @throws NullPointerException if the specified element is null.
     * @see org.openqa.selenium.interactions.Actions#moveToElement(org.openqa.selenium.WebElement)
     */
    public static void hoverOverOnElement(WebElement element) {
        new Actions(DriverUtil.getDriverInstance()).moveToElement(element).perform();
    }
    /**
     * <h2>{@code dragAndDrop(...)}: Performs a drag-and-drop action from the source WebElement to the target WebElement.</h2>
     * <p>
     * This method uses the Actions class to simulate a drag-and-drop operation between
     * two specified WebElements. The WebDriver instance is obtained from the DriverUtil utility class
     * ensuring Thread-Safety.
     * </p>
     *
     * @param source the WebElement to be dragged.
     * @param target the WebElement where the source will be dropped.
     * @throws NullPointerException if either the source or target WebElement is null.
     * @see org.openqa.selenium.interactions.Actions#dragAndDrop(org.openqa.selenium.WebElement, org.openqa.selenium.WebElement)
     */
    public static void dragAndDrop(WebElement source, WebElement target) {
        new Actions(DriverUtil.getDriverInstance()).dragAndDrop(source,target).perform();
    }
    /**
     * <h2>{@code dragAndDrop(...)}: Performs a drag-and-drop action on the source WebElement to a specific offset.</h2>
     * <p>
     * This method uses the Actions class to simulate dragging a WebElement from its current
     * position to an offset specified by the given x and y coordinates. The WebDriver instance
     * is retrieved from the DriverUtil utility class ensuring Thread-Safety.
     * </p>
     *
     * @param source the WebElement to be dragged.
     * @param x the horizontal offset to drag the element to.
     * @param y the vertical offset to drag the element to.
     * @throws NullPointerException if the source WebElement is null.
     * @see org.openqa.selenium.interactions.Actions#dragAndDropBy(org.openqa.selenium.WebElement, int, int)
     */
    public static void dragAndDrop(WebElement source, int x, int y) {
        new Actions(DriverUtil.getDriverInstance()).dragAndDropBy(source,x,y).perform();
    }
    /*
     *****************************************
     *          10. Overriding methods
     *****************************************
     */
}