package utilities;

/**
 * <h2>Enum representing various text formatting options for console outputs.</h2>
 * <p>
 * This enum provides flexibility in customising text styles, colours, and symbols for use across different
 * classes. It supports basic text formats like reset, colours, bold, underline, and background colours,
 * along with message-specific symbols (e.g., success, warning, error, informational, debug etc.).
 * </p>
 * <p>
 * The {@code ConsoleUtil.getTextFormat()} method can be used to retrieve these formats, allowing
 * different components of the application to easily apply consistent formatting. Additionally, the
 * {@code TextFormat} enum is compatible with stricter logging mechanisms, such as those implemented
 * in the {@code LoggerUtil} class, enabling a high degree of customisation for logs.
 * </p>
 *
 * <strong>Supported Formats:</strong>
 * <ul>
 *     <li>Reset formatting</li>
 *     <li>Text colors (e.g., black, red, green, etc.)</li>
 *     <li>Bold and underlined text formats</li>
 *     <li>Background colors</li>
 *     <li>Symbols for different log types (success, warning, error, etc.)</li>
 * </ul>
 *
 * @see ConsoleUtil#getTextFormat(TextFormat)
 * @see LoggerUtil
 */
public enum TextFormat {
    RESET,

    SUCCESS_MESSAGE_SYMBOL,
    WARNING_MESSAGE_SYMBOL,
    ERROR_MESSAGE_SYMBOL,
    INFORMATIONAL_MESSAGE_SYMBOL,
    DEBUG_MESSAGE_SYMBOL,
    FATAL_MESSAGE_SYMBOL,

    COLOUR_BLACK,
    COLOUR_RED,
    COLOUR_GREEN,
    COLOUR_YELLOW,
    COLOUR_BLUE,
    COLOUR_PURPLE,
    COLOUR_CYAN,
    COLOUR_WHITE,
    COLOUR_ORANGE,
    COLOUR_PINK,
    COLOUR_LIME,

    BOLD_BLACK,
    BOLD_RED,
    BOLD_GREEN,
    BOLD_YELLOW,
    BOLD_BLUE,
    BOLD_PURPLE,
    BOLD_CYAN,
    BOLD_WHITE,
    BOLD_ORANGE,
    BOLD_PINK,
    BOLD_LIME,

    UNDERLINE_BLACK,
    UNDERLINE_RED,
    UNDERLINE_GREEN,
    UNDERLINE_YELLOW,
    UNDERLINE_BLUE,
    UNDERLINE_PURPLE,
    UNDERLINE_CYAN,
    UNDERLINE_WHITE,
    UNDERLINE_ORANGE,
    UNDERLINE_PINK,
    UNDERLINE_LIME,

    BACKGROUND_BLACK,
    BACKGROUND_RED,
    BACKGROUND_GREEN,
    BACKGROUND_YELLOW,
    BACKGROUND_BLUE,
    BACKGROUND_PURPLE,
    BACKGROUND_CYAN,
    BACKGROUND_WHITE,
    BACKGROUND_ORANGE,
    BACKGROUND_PINK,
    BACKGROUND_LIME;
}