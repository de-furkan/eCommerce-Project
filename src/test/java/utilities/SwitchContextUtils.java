package utilities;

import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

public class SwitchContextUtils {
    /*
     *****************************************
     *          1. constants
     *****************************************
     */
    private static final LoggerUtil LOGGER = new LoggerUtil(SwitchContextUtils.class);
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
    private SwitchContextUtils() {}
    /*
     *****************************************
     *          6. public methods
     *     (including getters/setters)
     *****************************************
     */
    /**
     * <h2>{@code acceptAlert(...)}: Accepts an alert dialog if it is present within the specified timeout period.</h2>
     *
     * <p>This method waits for an alert to appear using {@link WaitUtil#waitForAlertContext(WebDriver, int)}.
     * Once the alert is detected, it switches to the alert context and accepts (dismisses) the alert.
     * If no alert appears within the timeout, or if any other issues occur during the operation,
     * appropriate log messages are recorded.</p>
     *
     * @param driver the {@link WebDriver} instance used to interact with the alert.
     * @param timeoutInSeconds the maximum wait time, in seconds, for the alert to appear.
     *
     * @throws NullPointerException if the provided {@code driver} is {@code null}.
     */
    public static void acceptAlert(WebDriver driver, int timeoutInSeconds) {
        try {
            WaitUtil.waitForAlertContext(driver, timeoutInSeconds);
            Alert alert = driver.switchTo().alert();
            alert.accept();
            LOGGER.success("SUCCESS: Alert accepted successfully.");
        } catch (TimeoutException e) {
            LOGGER.warn("No alert appeared within the timeout of " + timeoutInSeconds + " seconds.");
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred while accepting the alert: " + e.getMessage());
        }
    }
    /**
     * <h2>{@code dismissAlert(...)}: Dismisses an alert dialog if it is present within the specified timeout period.</h2>
     *
     * <p>This method waits for an alert to appear using {@link WaitUtil#waitForAlertContext(WebDriver, int)}.
     * Once the alert is detected, it switches to the alert context and dismisses the alert.
     * If no alert appears within the timeout, or if any other issues occur during the operation,
     * appropriate log messages are recorded.</p>
     *
     * @param driver the {@link WebDriver} instance used to interact with the alert.
     * @param timeoutInSeconds the maximum wait time, in seconds, for the alert to appear.
     *
     * @throws NullPointerException if the provided {@code driver} is {@code null}.
     */
    public static void dismissAlert(WebDriver driver, int timeoutInSeconds) {
        try {
            WaitUtil.waitForAlertContext(driver, timeoutInSeconds);
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            LOGGER.success("SUCCESS: Alert dismissed successfully.");
        } catch (TimeoutException e) {
            LOGGER.warn("No alert appeared within the timeout of " + timeoutInSeconds + " seconds.");
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred while dismissing the alert: " + e.getMessage());
        }
    }
    /**
     * <h2>{@code getAlertText(...)}: Retrieves the text content of an alert dialog if it is present within the specified timeout period.</h2>
     *
     * <p>This method waits for an alert to appear using {@link WaitUtil#waitForAlertContext(WebDriver, int)}.
     * Once the alert is detected, it switches to the alert context and retrieves its text content.
     * If no alert appears within the timeout or an error occurs, a warning or error log is recorded.</p>
     *
     * <p>If the alert text is successfully retrieved and is not blank, the method logs the alert text and returns it.
     * If no alert is present or the text is empty, an empty string is returned.</p>
     *
     * @param driver the {@link WebDriver} instance used to interact with the alert
     * @param timeoutInSeconds the maximum wait time, in seconds, for the alert to appear
     * @return the text of the alert, or an empty string if the alert text is blank or no alert appears
     *
     * @throws NullPointerException if the provided {@code driver} is {@code null}
     */
    public static String getAlertText(WebDriver driver, int timeoutInSeconds) {

        try {
            WaitUtil.waitForAlertContext(driver, timeoutInSeconds);
            Alert alert = driver.switchTo().alert();
            if (!(alert.getText() == null || alert.getText().isBlank())) {
                LOGGER.success("SUCCESS: Alert text successfully retrieved: " + alert.getText());
                return alert.getText();
            }
        } catch (TimeoutException e) {
            LOGGER.warn("No alert appeared within the timeout of " + timeoutInSeconds + " seconds.");
        } catch (Exception e) {
            LOGGER.error("An unexpected error occurred while retrieving alert text: " + e.getMessage());
        }
        return "";
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
