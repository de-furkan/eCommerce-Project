package utilities;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.Objects;

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
