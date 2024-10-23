package utilities;

import java.io.*;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
/**
 * <h1>This class is responsible for loading application properties from a configuration file.</h1>
 *
 * <p>The static initialisation block loads the application properties from the file specified
 * by {@code CONFIGURATION_FILE_PATH}. The properties are loaded into the {@code PROPERTIES}
 * object for use throughout the application.
 *
 * <p>If any errors occur during this process, such as an invalid file path, I/O issues, or access
 * restrictions, appropriate error messages are logged via the {@code LOGGER}.
 *
 * <h2>Exception Handling:</h2>
 * <ul>
 * <li>{@code IOException} - if there is an error reading the configuration file.</li>
 * <li>{@code InvalidPathException} - if the specified configuration file path is invalid.</li>
 * <li>{@code SecurityException} - if access to the configuration file is denied due to security restrictions.</li>
 * </ul>
 *
 * @see IOException
 * @see InvalidPathException
 * @see SecurityException
 * @see Properties
 * @since 2024
 * @version 1.0
 * @author Furkan O.
 */
public class ConfigReader {
    /*
     *****************************************
     *          1. constants
     *****************************************
     */
    /*
        Static fields for configuration and logging purposes
        PROPERTIES: Holds the application properties loaded from a file.
        PROJECT_ROOT: Represents the root directory of the project.
        LOGGER: Utility for logging messages, associated with the ConfigReader class.
        CONFIGURATION_FILE_PATH: Defines the path to the configuration properties file within the project.
     */
    private static final Properties PROPERTIES = new Properties();
    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    private static final LoggerUtil LOGGER = new LoggerUtil(ConfigReader.class);
    private static final Path CONFIGURATION_FILE_PATH = Paths.get(PROJECT_ROOT, "/config.properties");
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
    static {
        try (FileInputStream file = new FileInputStream(CONFIGURATION_FILE_PATH.toFile())) {
            PROPERTIES.load(file);
        } catch (IOException e) {
            LOGGER.error(
                    "Error reading the configuration file:" + " " + e.getMessage());
        } catch (InvalidPathException e) {
            LOGGER.error(
                    "Invalid path to the configuration file:"
                            + " " + CONFIGURATION_FILE_PATH + " " + e.getMessage());
        } catch (SecurityException e) {
            LOGGER.error(
                    "Security exception - access denied to the configuration file:"
                            + " " + CONFIGURATION_FILE_PATH + " " + e.getMessage());
        }
    }
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
    private ConfigReader() {};
    /*
     *****************************************
     *          6. public methods
     *     (including getters/setters)
     *****************************************
     */
    /**
     * <h2>{@code getProperty(...)}: Retrieves the value of a property associated with the specified key from the loaded configuration.</h2>
     *
     * <p>This method fetches the value of the property identified by the provided {@code key} from
     * the {@code PROPERTIES} object. If the property is not found, a warning is logged.
     *
     * <h2>Usage:</h2>
     * <pre><code>
     *     ConfigReader.getProperty("key");
     * </code></pre>
     * @param key the key whose associated property value is to be returned.
     * @return the value of the specified property, or {@code null} if the property is not found.
     */

    public static String getProperty(String key) {
        String value = PROPERTIES.getProperty(key);
        if (value == null) {
            LOGGER.warn("Property with key '" + key + "' is not found.");
        }
        return value;
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