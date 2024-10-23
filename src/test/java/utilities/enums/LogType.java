package utilities.enums;

import utilities.LoggerUtil;
/**
 * <h1>Enum representing different types of log levels used in the application.</h1>
 * <p>
 * The {@code LogType} enum defines the log levels available for categorising log messages
 * when using the logging framework. Each log type corresponds to a different level of severity or purpose,
 * which can be used in conjunction with logging utilities such as {@code LoggerUtil}.
 * </p>
 *
 * <h2>Log Levels:</h2>
 * <ul>
 *     <li><strong>SUCCESS:</strong> Represents success messages, typically used for positive outcomes or completion of tasks.</li>
 *     <li><strong>WARNING:</strong> Represents warning messages, indicating potential issues or areas that require attention.</li>
 *     <li><strong>ERROR:</strong> Represents error messages, typically used for failures or critical issues in the system.</li>
 *     <li><strong>INFO:</strong> Represents informational messages, used for general logging of actions or states.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     LoggerUtil logger = new LoggerUtil(MyClass.class);
 *     logger.debug(
 *          ConsoleUtil.getTextFormat(TextFormat.SUCCESS) + " " + "A success message"
 *     );
 * </pre>
 *
 * @see LoggerUtil
 * @since 2024
 * @author Furkan O.
 * @version 1.0
 */
public enum LogType {
    SUCCESS,
    WARNING,
    ERROR,
    INFO;
}