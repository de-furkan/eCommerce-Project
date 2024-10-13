package utilities;

import utilities.enums.LogType;
import utilities.enums.TextFormat;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ConsoleUtil {
    /*
     *****************************************
     *          1. CONSTANTS
     *****************************************
     */
    // UK London time - GMT+1 hour to correct format.
    private static final ZonedDateTime LONDON_TIME = ZonedDateTime
            .now(ZoneId.of("GMT"))
            .plusHours(1);

    // Message symbols
    private static final String SUCCESS_MESSAGE_SYMBOL =            "🟢";
    private static final String WARNING_MESSAGE_SYMBOL =            "🟠";
    private static final String ERROR_MESSAGE_SYMBOL =              "🔴";
    private static final String INFORMATIONAL_MESSAGE_SYMBOL =      "ℹ️";
    private static final String DEBUG_MESSAGE_SYMBOL =              "🐞";
    private static final String FATAL_MESSAGE_SYMBOL =              "☠️";

    // ANSI for text format reset
    private static final String RESET_TEXT_FORMAT =                 "\u001B[0m";

    // ANSI for text format colours
    private static final String COLOUR_BLACK =                      "\u001B[30m";
    private static final String COLOUR_RED =                        "\u001B[31m";
    private static final String COLOUR_GREEN =                      "\u001B[32m";
    private static final String COLOUR_YELLOW =                     "\u001B[33m";
    private static final String COLOUR_BLUE =                       "\u001B[34m";
    private static final String COLOUR_PURPLE =                     "\u001B[35m";
    private static final String COLOUR_CYAN =                       "\u001B[36m";
    private static final String COLOUR_WHITE =                      "\u001B[37m";
    private static final String COLOUR_ORANGE =                     "\u001B[38;5;208m";
    private static final String COLOUR_PINK =                       "\u001B[95m";
    private static final String COLOUR_LIME =                       "\u001B[92m";

    // ANSI for text format bold style and colours
    private static final String BOLD_BLACK =                        "\u001B[1;30m";
    private static final String BOLD_RED =                          "\u001B[1;31m";
    private static final String BOLD_GREEN =                        "\u001B[1;32m";
    private static final String BOLD_YELLOW =                       "\u001B[1;33m";
    private static final String BOLD_BLUE =                         "\u001B[1;34m";
    private static final String BOLD_PURPLE =                       "\u001B[1;35m";
    private static final String BOLD_CYAN =                         "\u001B[1;36m";
    private static final String BOLD_WHITE =                        "\u001B[1;37m";
    private static final String BOLD_ORANGE =                       "\u001B[1;38;5;208m";
    private static final String BOLD_PINK =                         "\u001B[1;38;5;201m";
    private static final String BOLD_LIME =                         "\u001B[1;38;5;154m";

    // ANSI for text format underline style and colours
    private static final String UNDERLINE_BLACK =                   "\u001B[4;30m";
    private static final String UNDERLINE_RED =                     "\u001B[4;31m";
    private static final String UNDERLINE_GREEN =                   "\u001B[4;32m";
    private static final String UNDERLINE_YELLOW =                  "\u001B[4;33m";
    private static final String UNDERLINE_BLUE =                    "\u001B[4;34m";
    private static final String UNDERLINE_PURPLE =                  "\u001B[4;35m";
    private static final String UNDERLINE_CYAN =                    "\u001B[4;36m";
    private static final String UNDERLINE_WHITE =                   "\u001B[4;37m";
    private static final String UNDERLINE_ORANGE =                  "\u001B[4;38;5;208m";
    private static final String UNDERLINE_PINK =                    "\u001B[4;38;5;201m";
    private static final String UNDERLINE_LIME =                    "\u001B[4;38;5;154m";

    // ANSI for text format back-ground colours
    private static final String BACKGROUND_BLACK =                  "\u001B[40m";
    private static final String BACKGROUND_RED =                    "\u001B[41m";
    private static final String BACKGROUND_GREEN =                  "\u001B[42m";
    private static final String BACKGROUND_YELLOW =                 "\u001B[43m";
    private static final String BACKGROUND_BLUE =                   "\u001B[44m";
    private static final String BACKGROUND_PURPLE =                 "\u001B[45m";
    private static final String BACKGROUND_CYAN =                   "\u001B[46m";
    private static final String BACKGROUND_WHITE =                  "\u001B[47m";
    private static final String BACKGROUND_ORANGE =                 "\u001B[48;5;208m";
    private static final String BACKGROUND_PINK =                   "\u001B[48;5;201m";
    private static final String BACKGROUND_LIME =                   "\u001B[48;5;154m";
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
    private ConsoleUtil(){};
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
     * <h2>{@code printStylingOptions()}: Displays a comprehensive list of styling options for console outputs.</h2>
     * <p>
     * This method prints various symbols, colours, and text styles that can be used in console messages.
     * It includes:
     * <ul>
     *     <li>Console message symbols (e.g., success, warning etc.)</li>
     *     <li>Text colours (e.g., black, red, green, etc.)</li>
     *     <li>Bold text colours</li>
     *     <li>Underlined text colours</li>
     *     <li>Background colours</li>
     * </ul>
     * Each option is displayed along with its corresponding TextFormat code for easy reference.
     *
     * @see TextFormat
     */
    public static void printStylingOptions() {
        System.out.println("A list of styles and colours for your console outputs:");
        System.out.println(
                "==============================================\n"
              + "Console Message Symbols\n"
              + "=============================================="
        );
        System.out.println(SUCCESS_MESSAGE_SYMBOL + ": A SUCCESS message symbol.");
        System.out.println(WARNING_MESSAGE_SYMBOL + ": A WARNING message symbol.");
        System.out.println(ERROR_MESSAGE_SYMBOL +   ": A ERROR message symbol.");
        System.out.println(DEBUG_MESSAGE_SYMBOL +   ": A DEBUG message symbol.");

        System.out.println(
                "==============================================\n"
              + "Console Text Colours\n"
              + "=============================================="
        );
        System.out.println(COLOUR_BLACK     +   "COLOUR_BLACK"      +   RESET_TEXT_FORMAT);
        System.out.println(COLOUR_RED       +   "COLOUR_RED"        +   RESET_TEXT_FORMAT);
        System.out.println(COLOUR_GREEN     +   "COLOUR_GREEN"      +   RESET_TEXT_FORMAT);
        System.out.println(COLOUR_YELLOW    +   "COLOUR_YELLOW"     +   RESET_TEXT_FORMAT);
        System.out.println(COLOUR_BLUE      +   "COLOUR_BLUE"       +   RESET_TEXT_FORMAT);
        System.out.println(COLOUR_PURPLE    +   "COLOUR_PURPLE"     +   RESET_TEXT_FORMAT);
        System.out.println(COLOUR_CYAN      +   "COLOUR_CYAN"       +   RESET_TEXT_FORMAT);
        System.out.println(COLOUR_WHITE     +   "COLOUR_WHITE"      +   RESET_TEXT_FORMAT);
        System.out.println(COLOUR_ORANGE    +   "COLOUR_ORANGE"     +   RESET_TEXT_FORMAT);
        System.out.println(COLOUR_PINK      +   "COLOUR_PINK"       +   RESET_TEXT_FORMAT);
        System.out.println(COLOUR_LIME      +   "COLOUR_LIME"       +   RESET_TEXT_FORMAT);

        System.out.println(
                "==============================================\n"
              + "Console Text Colours BOLD\n"
              + "=============================================="
        );
        System.out.println(BOLD_BLACK       +   "BOLD_BLACK"        +   RESET_TEXT_FORMAT);
        System.out.println(BOLD_RED         +   "BOLD_RED"          +   RESET_TEXT_FORMAT);
        System.out.println(BOLD_GREEN       +   "BOLD_GREEN"        +   RESET_TEXT_FORMAT);
        System.out.println(BOLD_YELLOW      +   "BOLD_YELLOW"       +   RESET_TEXT_FORMAT);
        System.out.println(BOLD_BLUE        +   "BOLD_BLUE"         +   RESET_TEXT_FORMAT);
        System.out.println(BOLD_PURPLE      +   "BOLD_PURPLE"       +   RESET_TEXT_FORMAT);
        System.out.println(BOLD_CYAN        +   "BOLD_CYAN"         +   RESET_TEXT_FORMAT);
        System.out.println(BOLD_WHITE       +   "BOLD_WHITE"        +   RESET_TEXT_FORMAT);
        System.out.println(BOLD_ORANGE      +   "BOLD_ORANGE"       +   RESET_TEXT_FORMAT);
        System.out.println(BOLD_PINK        +   "BOLD_PINK"         +   RESET_TEXT_FORMAT);
        System.out.println(BOLD_LIME        +   "BOLD_LIME"         +   RESET_TEXT_FORMAT);

        System.out.println(
                "==============================================\n"
              + "Console Text COLOURED & UNDERLINED\n"
              + "=============================================="
        );
        System.out.println(UNDERLINE_BLACK  +   "UNDERLINE_BLACK"   +   RESET_TEXT_FORMAT);
        System.out.println(UNDERLINE_RED    +   "UNDERLINE_RED"     +   RESET_TEXT_FORMAT);
        System.out.println(UNDERLINE_GREEN  +   "UNDERLINE_GREEN"   +   RESET_TEXT_FORMAT);
        System.out.println(UNDERLINE_YELLOW +   "UNDERLINE_YELLOW"  +   RESET_TEXT_FORMAT);
        System.out.println(UNDERLINE_BLUE   +   "UNDERLINE_BLUE"    +   RESET_TEXT_FORMAT);
        System.out.println(UNDERLINE_PURPLE +   "UNDERLINE_PURPLE"  +   RESET_TEXT_FORMAT);
        System.out.println(UNDERLINE_CYAN   +   "UNDERLINE_CYAN"    +   RESET_TEXT_FORMAT);
        System.out.println(UNDERLINE_WHITE  +   "UNDERLINE_WHITE"   +   RESET_TEXT_FORMAT);
        System.out.println(UNDERLINE_ORANGE +   "UNDERLINE_ORANGE"  +   RESET_TEXT_FORMAT);
        System.out.println(UNDERLINE_PINK   +   "UNDERLINE_PINK"    +   RESET_TEXT_FORMAT);
        System.out.println(UNDERLINE_LIME   +   "UNDERLINE_LIME"    +   RESET_TEXT_FORMAT);

        System.out.println(
                "==============================================\n"
              + "Console Text BACKGROUND COLOURS\n"
              + "=============================================="
        );
        System.out.println(BACKGROUND_BLACK     +   " BACKGROUND_BLACK "  +   RESET_TEXT_FORMAT);
        System.out.println(BACKGROUND_RED       +   " BACKGROUND_RED "    +   RESET_TEXT_FORMAT);
        System.out.println(BACKGROUND_GREEN     +   " BACKGROUND_GREEN "  +   RESET_TEXT_FORMAT);
        System.out.println(BACKGROUND_YELLOW    +   " BACKGROUND_YELLOW " +   RESET_TEXT_FORMAT);
        System.out.println(BACKGROUND_BLUE      +   " BACKGROUND_BLUE "   +   RESET_TEXT_FORMAT);
        System.out.println(BACKGROUND_PURPLE    +   " BACKGROUND_PURPLE " +   RESET_TEXT_FORMAT);
        System.out.println(BACKGROUND_CYAN      +   " BACKGROUND_CYAN "   +   RESET_TEXT_FORMAT);
        System.out.println(BACKGROUND_WHITE     +   " BACKGROUND_WHITE "  +   RESET_TEXT_FORMAT);
        System.out.println(BACKGROUND_ORANGE    +   " BACKGROUND_ORANGE " +   RESET_TEXT_FORMAT);
        System.out.println(BACKGROUND_PINK      +   " BACKGROUND_PINK "   +   RESET_TEXT_FORMAT);
        System.out.println(BACKGROUND_LIME      +   " BACKGROUND_LIME "   +   RESET_TEXT_FORMAT);
    }

    /**
     * <h2>{@code getTextFormat(...)}: Returns the appropriate text format or symbol based on the provided {@code TextFormat} argument.</h2>
     * <p>
     * This method maps the {@code TextFormat} enum to the corresponding ANSI escape codes for text formatting,
     * colours, and symbols used in console output. It supports standard text, bold text, underlined text,
     * and background colours, as well as message symbols for different scenarios (i.e. success, warning etc.).
     * </p>
     * <p>
     * If the provided {@code TextFormat} is invalid, the method throws a {@code RuntimeException} with
     * guidance on valid arguments.
     * </p>
     *
     * @param textFormat the {@code TextFormat} enum representing the desired format or symbol.
     * @return the corresponding ANSI escape code or symbol as a string.
     * @throws RuntimeException if the {@code textFormat} is invalid or unsupported.
     * @see TextFormat
     */
    public static String getTextFormat(TextFormat textFormat) {
        //For default case
        String defaultCaseMessage =
                ERROR_MESSAGE_SYMBOL + " " +
                "The provided textFormat argument is unclear.\n" +
                "Please refer to:" + " " + TextFormat.class.getName() + " " + "for a list of valid arguments.";

        switch (textFormat) {
            case RESET:                                     //Reset text formatting
                return RESET_TEXT_FORMAT;

            case SUCCESS_MESSAGE_SYMBOL:                    //Success symbol
                return SUCCESS_MESSAGE_SYMBOL;
            case WARNING_MESSAGE_SYMBOL:                    //Warning symbol
                return WARNING_MESSAGE_SYMBOL;
            case ERROR_MESSAGE_SYMBOL:                      //Error symbol
                return ERROR_MESSAGE_SYMBOL;
            case INFORMATIONAL_MESSAGE_SYMBOL:              //Informational symbol
                return INFORMATIONAL_MESSAGE_SYMBOL;
            case DEBUG_MESSAGE_SYMBOL:                      //Debug symbol
                return DEBUG_MESSAGE_SYMBOL;
            case FATAL_MESSAGE_SYMBOL:                      //Fatal symbol
                return FATAL_MESSAGE_SYMBOL;

            case COLOUR_BLACK:                              //Black text
                return COLOUR_BLACK;
            case COLOUR_RED:                                //Red text
                return COLOUR_RED;
            case COLOUR_GREEN:                              //Green text
                return COLOUR_GREEN;
            case COLOUR_YELLOW:                             //Yellow text
                return COLOUR_YELLOW;
            case COLOUR_BLUE:                               //Blue text
                return COLOUR_BLUE;
            case COLOUR_PURPLE:                             //Purple text
                return COLOUR_PURPLE;
            case COLOUR_CYAN:                               //Cyan text
                return COLOUR_CYAN;
            case COLOUR_WHITE:                              //White text
                return COLOUR_WHITE;
            case COLOUR_ORANGE:                             //Orange text
                return COLOUR_ORANGE;
            case COLOUR_PINK:                               //Pink text
                return COLOUR_PINK;
            case COLOUR_LIME:                               //Lime text
                return COLOUR_LIME;

            case BOLD_BLACK:                                //Black bold text
                return BOLD_BLACK;
            case BOLD_RED:                                  //Red bold text
                return BOLD_RED;
            case BOLD_GREEN:                                //Green bold text
                return BOLD_GREEN;
            case BOLD_YELLOW:                               //Yellow bold text
                return BOLD_YELLOW;
            case BOLD_BLUE:                                 //Blue bold text
                return BOLD_BLUE;
            case BOLD_PURPLE:                               //Purple bold text
                return BOLD_PURPLE;
            case BOLD_CYAN:                                 //Cyan bold text
                return BOLD_CYAN;
            case BOLD_WHITE:                                //White bold text
                return BOLD_WHITE;
            case BOLD_ORANGE:                               //Orange bold text
                return BOLD_ORANGE;
            case BOLD_PINK:                                 //Pink bold text
                return BOLD_PINK;
            case BOLD_LIME:                                 //Lime bold text
                return BOLD_LIME;

            case UNDERLINE_BLACK:                           //Black underline and text
                return UNDERLINE_BLACK;
            case UNDERLINE_RED:                             //Red underline and text
                return UNDERLINE_RED;
            case UNDERLINE_GREEN:                           //Green underline and text
                return UNDERLINE_GREEN;
            case UNDERLINE_YELLOW:                          //Yellow underline and text
                return UNDERLINE_YELLOW;
            case UNDERLINE_BLUE:                            //Blue underline and text
                return UNDERLINE_BLUE;
            case UNDERLINE_PURPLE:                          //Purple underline and text
                return UNDERLINE_PURPLE;
            case UNDERLINE_CYAN:                            //Cyan underline and text
                return UNDERLINE_CYAN;
            case UNDERLINE_WHITE:                           //White underline and text
                return UNDERLINE_WHITE;
            case UNDERLINE_ORANGE:                          //Orange underline and text
                return UNDERLINE_ORANGE;
            case UNDERLINE_PINK:                            //Pink underline and text
                return UNDERLINE_PINK;
            case UNDERLINE_LIME:                            //Lime underline and text
                return UNDERLINE_LIME;

            case BACKGROUND_BLACK:                          //Black Background
                return BACKGROUND_BLACK;
            case BACKGROUND_RED:                            //Red Background
                return BACKGROUND_RED;
            case BACKGROUND_GREEN:                          //Green Background
                return BACKGROUND_GREEN;
            case BACKGROUND_YELLOW:                         //Yellow Background
                return BACKGROUND_YELLOW;
            case BACKGROUND_BLUE:                           //Blue Background
                return BACKGROUND_BLUE;
            case BACKGROUND_PURPLE:                         //Purple Background
                return BACKGROUND_PURPLE;
            case BACKGROUND_CYAN:                           //Cyan Background
                return BACKGROUND_CYAN;
            case BACKGROUND_WHITE:                          //White Background
                return BACKGROUND_WHITE;
            case BACKGROUND_ORANGE:                         //Orange Background
                return BACKGROUND_ORANGE;
            case BACKGROUND_PINK:                           //Pink Background
                return BACKGROUND_PINK;
            case BACKGROUND_LIME:                           //Lime Background
                return BACKGROUND_LIME;

            default:
                throw new RuntimeException(defaultCaseMessage);
        }
    }
    /**
     * <h2>{@code log(...)}:Formats and returns a log message {@code String} with the appropriate styling and symbol based on the provided {@code LogType}.</h2>
     * <p>
     * This method formats the log message by appending a symbol, colour, and bold style specific to the given {@code LogType}.
     * The log includes a timestamp provided by the {@link #getFormattedDateTime()} method, and resets the text styling at the end. The supported log types are:
     * <ul>
     *     <li>{@code SUCCESS} - Green text with a success symbol.</li>
     *     <li>{@code WARNING} - Orange text with a warning symbol.</li>
     *     <li>{@code ERROR} - Red text with an error symbol.</li>
     *     <li>{@code INFO} - Blue text with an informational symbol.</li>
     * </ul>
     * </p>
     *
     * <p>
     * The {@code LogType} argument references the LogType enum class.
     * </p>
     *
     * <quote>
     * If you need a stricter logging for debugging/reporting purposes, please refer to the {@link LoggerUtil} instead.
     * This method should <strong>ONLY</strong> be used for simple logs.
     * </quote>
     *
     * @param message the message to be logged.
     * @param logType the type of log, determining the format and symbol (e.g. SUCCESS, WARNING, ERROR, INFO).
     * @return a formatted log message as a string, including symbol, colour, and timestamp.
     * @see #getFormattedDateTime()
     * @see LogType
     * @see LoggerUtil
     */
    public static String log(String message, LogType logType) {
        String formattedMessage = "";
        switch (logType) {
            case SUCCESS:
                formattedMessage =
                        getTextFormat(TextFormat.SUCCESS_MESSAGE_SYMBOL) +
                        getTextFormat(TextFormat.BOLD_GREEN) +
                        " [" + getFormattedDateTime() + "]:" + " " + message +
                        getTextFormat(TextFormat.RESET);
                break;
            case WARNING:
                formattedMessage =
                        getTextFormat(TextFormat.WARNING_MESSAGE_SYMBOL) +
                                getTextFormat(TextFormat.BOLD_ORANGE) +
                                " [" + getFormattedDateTime() + "]:" + " " + message +
                                getTextFormat(TextFormat.RESET);
                break;
            case ERROR:
                formattedMessage =
                        getTextFormat(TextFormat.ERROR_MESSAGE_SYMBOL) +
                                getTextFormat(TextFormat.BOLD_RED) +
                                " [" + getFormattedDateTime() + "]:" + " " + message +
                                getTextFormat(TextFormat.RESET);
                break;
            case INFO:
                formattedMessage =
                        getTextFormat(TextFormat.INFORMATIONAL_MESSAGE_SYMBOL) +
                                getTextFormat(TextFormat.BOLD_BLUE) +
                                " [" + getFormattedDateTime() + "]:" + " " + message +
                                getTextFormat(TextFormat.RESET);
                break;
        }
        return formattedMessage;
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
     * <h2>{@code getFormattedDateTime()}: Returns the current date and time formatted as GMT+1 for the London time.</h2>
     * <p>
     * This method formats the current date and time using the pattern 
     * {@code 📅dd-MMM-yyyy  🕰️HH:mm:ss z} to include a date, time, and time zone. 
     * The time is displayed in the London time zone.
     * </p>
     *
     * @return a string representing the current date and time formatted as 
     *         {@code 📅dd-MMM-yyyy  🕰️HH:mm:ss z} for GMT+1 hour.
     * 
     * @see #log(String, LogType) 
     */
    private static String getFormattedDateTime() {
        String formattedDateTime;
        formattedDateTime = LONDON_TIME.format(DateTimeFormatter.ofPattern(
                "📅dd-MMM-yyyy  🕰️HH:mm:ss z"
        ));
        return formattedDateTime;
    }
    /*
     *****************************************
     *          9. Overloading Methods
     *****************************************
     */

    /*
     *****************************************
     *          10. Overriding Methods
     *****************************************
     */
}
