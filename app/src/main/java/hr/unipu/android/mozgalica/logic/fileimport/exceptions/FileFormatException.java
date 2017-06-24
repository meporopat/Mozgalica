package hr.unipu.android.mozgalica.logic.fileimport.exceptions;

/**
 *
 * This exception is thrown when a file does not have the expected format
 */
public class FileFormatException extends Exception {
    /**
     * Constructor which creates an error string from the given string parameters
     *
     * @param expectedType the expected file type
     */
    public FileFormatException(String expectedType) {
        super("The given file does not match the expected " + expectedType + " file structure!");
    }
}

