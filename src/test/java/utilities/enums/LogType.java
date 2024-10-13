package utilities.enums;

import utilities.LoggerUtil;

/**
 * <h2>Enum representing simple log types used for basic console logging.</h2>
 * <p>
 * This enum provides four log types:
 * <ul>
 *     <li>{@code SUCCESS} - Represents successful operations.</li>
 *     <li>{@code WARNING} - Represents warnings or potential issues.</li>
 *     <li>{@code ERROR} - Represents error messages.</li>
 *     <li>{@code INFO} - Represents informational messages.</li>
 * </ul>
 * <strong>Note:</strong> This enum is intended for simple logs only. For more complex logging
 * needs, such as debugging or reporting, refer to the {@code LoggerUtil} class.
 *
 * @see LoggerUtil
 */
public enum LogType {
    SUCCESS,
    WARNING,
    ERROR,
    INFO;
}
