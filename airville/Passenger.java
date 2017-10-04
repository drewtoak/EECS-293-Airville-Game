package airville;

import java.math.BigDecimal;
import java.util.EnumSet;

/**
 * The Passenger class is a class of a regular flier.
 *
 * @author Andrew Hwang
 * @version 1.0 Dec. 03, 2015
 */
public class Passenger {

    /**
     * EnumSet field labelled slowPassengerTypes: holds the enum slow types of the passenger.
     */
    public final EnumSet<SlowPassengerType> slowPassengerTypes;

    /**
     * The Passenger Constructor takes an EnumSet of PassengerType as a parameter and sets it to this Passenger.
     *
     * @param slowPassengerTypes set of slow passenger types for the passenger.
     */
    public Passenger(EnumSet<SlowPassengerType> slowPassengerTypes) {
        this.slowPassengerTypes = slowPassengerTypes;
    }

    /**
     * Gets the total time it takes for the Passenger to be processed.
     * Depends on the type of passenger and the value of the regular process time.
     *
     * @return the total time as a BigDecimal.
     */
    public BigDecimal getPassengerProcessTime() {
        BigDecimal passengerProcessTime = BigDecimal.valueOf(Game.REGULAR_PROCESS_TIME);

        //for each slow passenger type add its time value to the passenger's total process time.
        for (SlowPassengerType passengerType: this.slowPassengerTypes) {
            passengerProcessTime = passengerProcessTime.add(BigDecimal.valueOf(passengerType.getAddedTime()));
        }
        return passengerProcessTime;
    }

    /**
     * Gets the Passenger types of the Passenger.
     *
     * @return an EnumSet of SlowPassengerType.
     */
    public EnumSet<SlowPassengerType> getSlowPassengerTypes() {
        EnumSet<SlowPassengerType> allSlowPassengerTypes = this.slowPassengerTypes;
        return allSlowPassengerTypes;
    }

    /**
     * Checks if the Passenger is at least one of the slow types of passengers.
     *
     * @return true if the passenger is a slow type and false if he/she is not one.
     */
    public boolean isSlowPassenger() {
        return !this.slowPassengerTypes.isEmpty();
    }

    /**
     * Gets the number of passengers within the group,
     * but will always be one for a single Passenger.
     *
     * @return one, not a group.
     */
    public int getNumberOfGroupMembers() {
        return 1;
    }

    /**
     * Checks if the Passenger is part of a group.
     *
     * @return is false, never part of group.
     */
    public boolean isPartOfGroup() {
        return false;
    }

    /**
     * Checks if the the amount of time to process the group met the requirements for the Player to earn a Diamond.
     *
     * @param groupProcessTime total process time of a group.
     * @return is false, never part of group.
     */
    public boolean processedGroupForDiamond(BigDecimal groupProcessTime) {
        return false;
    }
}
