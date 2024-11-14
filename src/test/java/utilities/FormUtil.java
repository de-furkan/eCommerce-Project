package utilities;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.Objects;
import java.util.Random;
/**
 * <h1>Utility class extending {@link GenericElementUtils} to provide form-related interactions with web elements.</h1>
 *
 * <p>The {@code FormUtil} class contains methods that will specifically handle elements on a form.</p>
 *
 * <h2>Class Structure</h2>
 * <ul>
 *     <li><strong>Constants</strong>: Contains the {@code LOGGER} for consistent logging throughout the class.</li>
 *     <li><strong>Constructors</strong>: Private constructor to prevent instantiation of this utility class.</li>
 *     <li><strong>Public Methods</strong>: Various methods to handle form elements.
 * </ul>
 *
 * <h2>Usage</h2>
 * <p>This class is designed for automated form handling, particularly for dropdown elements,
 * where selection can be made based on the option's index, visible text, value, or randomly.
 * Each method incorporates wait handling to ensure the target element is interactable.</p>
 *
 * <pre><code>
 *     FormUtil.selectRandomDropdownOption(
 *                 DriverUtil.getDriverInstance()
 *                  .findElement(By.cssSelector("body #dropdown")),
 *                  10
 *         );
 * </code></pre>
 *
 * <h2>Logging</h2>
 * <p>Logging is performed with {@link LoggerUtil}, providing both success and warning/error messages based on the
 * outcome of each interaction. Logging aids in tracking actions for troubleshooting and debugging purposes.</p>
 *
 * <h2>Exceptions</h2>
 * <p>Exceptions related to element interaction, visibility, and timeouts are managed primarily through
 * {@link WaitUtil}, which handles standard WebDriver exceptions. Any additional handling is mentioned in individual methods.</p>
 *
 * @since 2024
 * @see GenericElementUtils
 * @see WaitUtil
 * @see LoggerUtil
 * @author Furkan O.
 * @version 1.0
 */
public class FormUtil extends GenericElementUtils{
    /*
     *****************************************
     *          1. constants
     *****************************************
     */
    private static final LoggerUtil LOGGER = new LoggerUtil(FormUtil.class);
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
    private FormUtil() {}
    /*
     *****************************************
     *          6. public methods
     *     (including getters/setters)
     *****************************************
     */
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
    /**
     * <h2>{@code selectDropdownByValue(...)}: Selects an option from a dropdown menu based on the specified value attribute.</h2>
     *
     * <p>This method waits for the dropdown element to be clickable within the provided timeout period.
     * It then selects an option with a matching value if the dropdown contains options.
     * A log entry confirms the successful selection by value.</p>
     *
     * <p>This method assumes that exceptions are handled through the {@link WaitUtil#waitForClickableElement(WebElement, int)}</p>
     *
     * @param element the {@link WebElement} representing the dropdown menu.
     * @param timeout the maximum wait time, in seconds, for the dropdown to become clickable.
     * @param value the value attribute of the option to be selected within the dropdown.
     *
     */
    public static void selectDropdownByValue(WebElement element, int timeout, String value) {
        Select dropdown = new Select(Objects.requireNonNull(WaitUtil.waitForClickableElement(element, timeout)));
        if (!dropdown.getOptions().isEmpty()) {
            dropdown.selectByValue(value);
            LOGGER.success("Successfully selected dropdown option by value:" + " " + value);
        }
    }
    /**
     * <h2>{@code selectRandomDropdownOption(...)}: Selects a random enabled option from a dropdown menu within a set number of attempts.</h2>
     *
     * <p>This method waits for the specified dropdown element to become clickable within the provided timeout.
     * It then attempts to select a random enabled option from the dropdown, with a maximum of 5 tries.
     * If an enabled option is successfully selected, the option's text is logged. If a disabled option is encountered,
     * the method logs a warning and retries up to the maximum attempts.</p>
     *
     * <p>This method assumes that exceptions are handled through the {@link WaitUtil#waitForClickableElement(WebElement, int)}</p>
     *
     * @param element the {@link WebElement} representing the dropdown menu
     * @param timeout the maximum wait time, in seconds, for the dropdown to become clickable
     *
     */
    public static void selectRandomDropdownOption(WebElement element, int timeout) {
        Select dropdown = new Select(Objects.requireNonNull(WaitUtil.waitForClickableElement(element, timeout)));

        if (!dropdown.getOptions().isEmpty()) {
            Random random = new Random();
            int randomOption;
            final int maxTries = 5;
            try {
                for (int i = 1; i <= maxTries; i++) {
                    randomOption = random.nextInt(dropdown.getOptions().size());
                    WebElement randomEl = dropdown.getOptions().get(randomOption);

                    if (randomEl.isEnabled()) {
                        dropdown.selectByIndex(randomOption);
                        LOGGER.success("Successfully selected a random dropdown option: " + randomEl.getText());
                        return;
                    } else {
                        LOGGER.warn("Selected option is disabled. Try:" + " " + i + "/" + maxTries);
                    }
                }
            } catch (UnsupportedOperationException e) {
                LOGGER.warn("Disabled option. Re-selecting....");
            }
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