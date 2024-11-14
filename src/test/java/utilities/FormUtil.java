package utilities;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Objects;
import java.util.Random;

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
