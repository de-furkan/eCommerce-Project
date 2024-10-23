package utilities;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilities.enums.TextFormat;

/**
 * <h1>Utility class for logging messages with different levels, using Log4j2.</h1>
 * <p>
 * The {@code LoggerUtil} class provides logging functionality, supporting different log levels such as
 * debug, info, warning, error, and fatal. It also includes a custom log level for success messages.
 * The class uses Log4j2's {@code Logger} for structured logging, and integrates with the {@code ConsoleUtil}
 * class to add formatting and symbols for different log types.
 * </p>
 *
 * <h2>Key Features:</h2>
 * <ul>
 *     <li>Supports standard log levels: debug, info, warning, error, and fatal.</li>
 *     <li>Custom logging for success messages using the info log level.</li>
 *     <li>Log messages include symbols and formatted text for enhanced readability.</li>
 * </ul>
 *
 * <h2>Thread-Safety Considerations:</h2>
 * <p>
 * This class does not hold any state related to threading. Each instance is associated with a specific class
 * for logging, and logging is handled by Log4j2, which ensures thread-safe behaviour.
 * </p>
 *
 * <h2>Design Notes:</h2>
 * <ul>
 *     <li>Each {@code LoggerUtil} instance is tied to a specific class, simplifying log tagging.</li>
 *     <li>Static methods are not used, as each instance is designed to log for a specific class.</li>
 * </ul>
 *
 * <h2>Methods Provided:</h2>
 * <ul>
 *     <li>{@code debug(String)}: Logs a debug-level message if debug logging is enabled.</li>
 *     <li>{@code info(String)}: Logs an info-level message if info logging is enabled.</li>
 *     <li>{@code warn(String)}: Logs a warning-level message if warning logging is enabled.</li>
 *     <li>{@code error(String)}: Logs an error-level message if error logging is enabled.</li>
 *     <li>{@code fatal(String)}: Logs a fatal-level message if fatal logging is enabled.</li>
 *     <li>{@code success(String)}: Logs a success message using the info log level.</li>
 * </ul>
 *
 * @author Furkan O.
 * @version 1.0
 * @since 2024
 */
public class LoggerUtil {
    /*
     *****************************************
     *          1. CONSTANTS
     *****************************************
     */
    // Get the logger class
    private final Logger logger;
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
    /**
     * <h2>{@code LoggerUtil(...)}: Constructor initialises the logger for the specified class.</h2>
     * <p>
     * This constructor creates a logger instance associated with the given class, allowing
     * logging messages to be tagged with the class name. It provides flexibility by
     * avoiding the need to pass the class name on every logging method call.
     * </p>
     *
     * @param clazz the {@code Class} object for which the logger is initialised.
     */
    public LoggerUtil(Class<?> clazz) {
        this.logger = LogManager.getLogger(clazz);
    }
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
     * <h2>{@code debug(...)}: Logs a debug-level message if debugging is enabled.</h2>
     * <p>
     * This method formats the message with a debug symbol and logs it at the
     * debug level. If debugging is disabled, a message is displayed in the console
     * indicating that debug logging is not enabled.
     * </p>
     *
     * @param message the message to be logged
     */
    public void debug(String message) {
        if (isLoggingEnabled(Level.DEBUG)) {
            String formattedMessage = ConsoleUtil.getTextFormat(TextFormat.DEBUG_MESSAGE_SYMBOL) + message;
            this.logger.debug(formattedMessage);
        } else {
            loggingNotEnabledConsoleMessage("debug");
        }
    }
    /**
     * <h2>{@code info(...)}: Logs an info-level message if info logging is enabled.</h2>
     * <p>
     * This method formats the message with an info symbol and logs it at the
     * info level. If info logging is disabled, a message is displayed in the console
     * indicating that info logging is not enabled.
     * </p>
     *
     * @param message the message to be logged
     */
    public void info(String message) {
        if (isLoggingEnabled(Level.INFO)) {
            String formattedMessage = ConsoleUtil.getTextFormat(TextFormat.INFORMATIONAL_MESSAGE_SYMBOL) + message;
            this.logger.info(formattedMessage);
        } else {
            loggingNotEnabledConsoleMessage("info");
        }
    }
    /**
     * <h2>{@code warn(...)}: Logs a warning-level message if warning logging is enabled.</h2>
     * <p>
     * This method formats the message with a warning symbol and logs it at the
     * warning level. If warning logging is disabled, a message is displayed in the console
     * indicating that warning logging is not enabled.
     * </p>
     *
     * @param message the message to be logged
     */
    public void warn(String message) {
        if (isLoggingEnabled(Level.WARN)) {
            String formattedMessage = ConsoleUtil.getTextFormat(TextFormat.WARNING_MESSAGE_SYMBOL) + message;
            this.logger.warn(formattedMessage);
        } else {
            loggingNotEnabledConsoleMessage("warn");
        }
    }
    /**
     * <h2>{@code error(...)}: Logs an error-level message if error logging is enabled.</h2>
     * <p>
     * This method formats the message with an error symbol and logs it at the
     * error level. If error logging is disabled, a message is displayed in the console
     * indicating that error logging is not enabled.
     * </p>
     *
     * @param message the message to be logged
     */
    public void error(String message) {
        if (isLoggingEnabled(Level.ERROR)) {
            String formattedMessage = ConsoleUtil.getTextFormat(TextFormat.ERROR_MESSAGE_SYMBOL) + message;
            this.logger.error(formattedMessage);
        } else {
            loggingNotEnabledConsoleMessage("error");
        }
    }
    /**
     * <h2>{@code fatal(...)}: Logs a fatal-level message if fatal logging is enabled.</h2>
     * <p>
     * This method formats the message with a fatal symbol and logs it at the
     * fatal level. If fatal logging is disabled, a message is displayed in the console
     * indicating that fatal logging is not enabled.
     * </p>
     *
     * @param message the message to be logged
     */
    public void fatal(String message) {
        if (isLoggingEnabled(Level.FATAL)) {
            String formattedMessage = ConsoleUtil.getTextFormat(TextFormat.FATAL_MESSAGE_SYMBOL) + message;
            this.logger.fatal(formattedMessage);
        } else {
            loggingNotEnabledConsoleMessage("fatal");
        }
    }
    /**
     * <h2>{@code success(...)}: Logs a success-level message if info logging is enabled.</h2>
     * <p>
     * This method formats the message with a success symbol and logs it at the
     * info level, as there is no specific success level in Log4j. If info logging is
     * disabled, a message is displayed in the console indicating that info logging
     * is not enabled.
     * </p>
     *
     * <p>
     * <strong>Note:</strong> this is a custom method and therefore uses the {@code Level.INFO}.
     * </p>
     *
     * @param message the message to be logged
     */
    public void success(String message) {
        if (isLoggingEnabled(Level.INFO)) {
            String formattedMessage = ConsoleUtil.getTextFormat(TextFormat.SUCCESS_MESSAGE_SYMBOL) + message;
            this.logger.info(formattedMessage);
        } else {
            loggingNotEnabledConsoleMessage("info");
        }
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
     * <h2>{@code isLoggingEnabled(...)}: Checks if logging is enabled for the specified log level.</h2>
     * <p>
     * This method verifies whether the logger is enabled for the given logging level.
     * It ensures that the logger will only log messages when the specified level is active
     * according to the current Log4j configuration.
     * </p>
     *
     * @param level the logging {@code Level} to check (e.g., DEBUG, INFO, WARN, ERROR)
     * @return {@code true} if logging is enabled for the specified level, {@code false} otherwise
     */
    private boolean isLoggingEnabled(Level level) {
        return this.logger.isEnabled(level);
    }
    /**
     * <h2>{@code loggingNotEnabledConsoleMessage(...)}: Prints a console message indicating that logging is not enabled for the specified level.</h2>
     * <p>
     * If logging is not enabled for the provided level, this method outputs an error-style
     * message to the console, advising the user to check the Log4j configuration.
     * </p>
     *
     * @param level the name of the logging level (e.g., "debug", "info", "warn") that is not enabled
     */
    private void loggingNotEnabledConsoleMessage(String level) {
        System.out.println(
                ConsoleUtil.getTextFormat(TextFormat.ERROR_MESSAGE_SYMBOL) + level.toUpperCase() + " " +
                "logging is not enabled for this class. Please check the log4j2.xml file."
        );
    }
    /*
     *****************************************
     *          9. Overloading methods
     *****************************************
     */

    /*
     *****************************************
     *          10. Overriding methods
     *****************************************
     */
}