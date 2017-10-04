package airville;

/**
 * The GameException class extends Exception and throws an exception for errors that occur in Game functions.
 *
 * @author Andrew Hwang
 * @version 1.0 Dec. 03, 2015
 */
public class GameException extends Exception{

    /**
     * The GameErrorCode is an enum class for different Game errors.
     */
    public enum GameErrorCode {
        HIRE_MORE_STAFFS, BUILD_MORE_ROBOTS, NEED_MORE_POINTS, NEED_MORE_DIAMONDS, SUPERVISOR_CANNOT_BE_SET;
    }

    // holds the game code error of the exception.
    private GameErrorCode gameErrorCode;

    /**
     * The Constructor takes in an error code for the exception.
     *
     * @param gameErrorCode the specified error code of the game exception.
     */
    public GameException(GameErrorCode gameErrorCode) {
        this.gameErrorCode = gameErrorCode;
    }

    /**
     * Retrieves the game error code of the exception.
     *
     * @return game error code.
     */
    public GameErrorCode getGameErrorCode() {
        return this.gameErrorCode;
    }
}
