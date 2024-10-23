package utilities.enums;

import utilities.ConsoleUtil;
import utilities.LoggerUtil;
/**
 * <h1>Enum representing text formatting options and symbols for console output.</h1>
 * <p>
 * The {@code TextFormat} enum defines various ANSI escape codes and symbols for formatting text in the console,
 * including standard colours, bold and underlined styles, as well as background colours. This enum is used
 * within the utility classes, such as {@code ConsoleUtil}, to format console messages with styles and symbols.
 * </p>
 *
 * <h2>Formatting Options:</h2>
 * <ul>
 *     <li>Symbols: Success, warning, error, informational, debug, and fatal symbols for logging messages.</li>
 *     <li>Text Colours: Standard text colours including black, red, green, yellow, blue, purple, cyan, white, and more.</li>
 *     <li>Bold Text Colours: Bold versions of standard text colours for emphasis in console output.</li>
 *     <li>Underlined Text Colours: Underlined versions of standard text colours for emphasis or decoration.</li>
 *     <li>Background Colours: Background colour options to highlight or differentiate text in console output.</li>
 * </ul>
 *
 * <h2>Usage Example:</h2>
 * <pre>
 *     String successMessage =
 *          ConsoleUtil.getTextFormat(TextFormat.SUCCESS_MESSAGE_SYMBOL)
 *          + "Operation completed successfully!"
 *          + ConsoleUtil.getTextFormat(TextFormat.RESET);
 *     System.out.println(successMessage);
 * </pre>
 *
 * <h2>Integration:</h2>
 * <ul>
 *     <li>This enum integrates with {@code ConsoleUtil} for formatting and displaying styled console messages.</li>
 *     <li>It also works in conjunction with {@code LoggerUtil} for enhanced log messages with symbols and colours.</li>
 * </ul>
 *
 * @see ConsoleUtil
 * @see LoggerUtil
 * @since 2024
 * @author Furkan O.
 * @version 1.0
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