package hr.unipu.android.mozgalica.logic.fileimport.exceptions;

/**
 *
 * This exception is thrown when an unexpected element is found in a xml based file
 */
public class UnexpectedElementException extends Exception {
    /**
     * Constructor which creates an error string from the given string parameters
     *
     * @param elementName the name of the unexpected element
     */
    public UnexpectedElementException(String elementName) {
        super("Unexpected element: " + elementName + "!");
    }
}