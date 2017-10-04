package airville;

/**
 * The SlowPassengerType is an enum class for different types of slow passengers.
 *
 * @author Andrew Hwang
 * @version 1.0 Dec. 03, 2015
 */
public enum SlowPassengerType {

    //Enum variables for slow passenger types. The values for each enum can be initialized in the Game class.
    EXCESS_BAGGAGE(Game.EXCESS_BAGGAGE_PROCESS_TIME), REROUTED(Game.REROUTED_PROCESS_TIME), OVERBOOKED(Game.OVERBOOKED_PROCESS_TIME);

    // Holds the amount of extra time a certain Passenger type takes to process.
    private final double addedTime;

    /**
     * Initializes the float field for the added times for each type of slow Passenger.
     *
     * @param addedTime a double value of the slow process time.
     */
    SlowPassengerType(double addedTime) {
        this.addedTime = addedTime;
    }

    /**
     * Gets the value of the added time for that enum type of Passenger.
     *
     * @return a double value of the added time.
     */
    public double getAddedTime() {
        return addedTime;
    }
}
