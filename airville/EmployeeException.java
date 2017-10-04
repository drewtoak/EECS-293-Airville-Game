package airville;

/**
 * The EmployeeException class extends Exception and throws an exception for errors that occur in the Staff and Robot functions
 *
 * @author Andrew Hwang
 * @version 1.0 Dec. 03, 2015
 */
public class EmployeeException extends Exception {

    /**
     * The EmployeeErrorCode is an enum class for different Employee errors.
     */
    public enum EmployeeErrorCode {
        ALL_SUPERVISORS_ARE_BUSY, NO_SUPERVISORS_PRESENT, PASSENGERS_STIL_PRESENT;
    }

    // holds the employee error code of the exception.
    private EmployeeErrorCode employeeErrorCode;

    /**
     * The Constructor takes in an error code for the exception.
     *
     * @param employeeErrorCode the specified error code of the employee exception.
     */
    public EmployeeException(EmployeeErrorCode employeeErrorCode) {
        this.employeeErrorCode = employeeErrorCode;
    }

    /**
     * Retrieves the game error code of the exception.
     *
     * @return employee error code.
     */
    public EmployeeErrorCode getEmployeeErrorCode() {
        return this.employeeErrorCode;
    }
}
