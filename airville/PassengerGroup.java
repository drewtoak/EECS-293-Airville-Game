package airville;

import java.math.BigDecimal;
import java.util.EnumSet;

/**
 * The PassengerGroup class extends the Passenger abstract class sets up a group of Passengers.
 *
 * @author Andrew Hwang
 * @version 1.0 Dec. 03, 2015
 */
public class PassengerGroup extends Passenger {

    /**
     * the variable's value holds the number of group members within the Passenger Group and cannot be altered.
     */
    private final int NUMBER_OF_GROUP_MEMBERS;

    /**
     * The Constructor takes in one more parameter than the Passenger class's Constructor;
     * the number of group members within the Passenger Group is taken in as a parameter along with the types of the Group.
     *
     * @param slowPassengerTypes set of slow passenger types for the group.
     * @param numberOfGroupMembers a number for the size of the group.
     */
    public PassengerGroup(EnumSet<SlowPassengerType> slowPassengerTypes, int numberOfGroupMembers) {
        super(slowPassengerTypes);
        this.NUMBER_OF_GROUP_MEMBERS = numberOfGroupMembers;
    }

    /**
     * Overrides the getPassengerProcessTime function in the Passenger class:
     * the total process time is altered by the number of group members that are being processed
     * and a multiplier, since processing a group would be faster than processing each member individually.
     *
     * @return the total time, accounting for the number of group members and the group multiplier, as a BigDecimal.
     */
    @Override
    public BigDecimal getPassengerProcessTime() {
        BigDecimal passengerProcessTime = BigDecimal.valueOf(Game.REGULAR_PROCESS_TIME);

        //for each slow passenger type add its time value to the passenger's total process time.
        for (SlowPassengerType passengerType: this.slowPassengerTypes) {
            passengerProcessTime = passengerProcessTime.add(BigDecimal.valueOf(passengerType.getAddedTime()));
        }

        //multiply the passenger's process time by the number of group members and the group member's multiplier.
        passengerProcessTime = passengerProcessTime.multiply(BigDecimal.valueOf(this.NUMBER_OF_GROUP_MEMBERS));
        passengerProcessTime = passengerProcessTime.multiply(BigDecimal.valueOf(Game.PASSENGER_GROUP_TIME_MULTIPLIER));
        return passengerProcessTime;
    }

    /**
     * Overrides the getNumberOfGroupMembers function in the Passenger class:
     * Gets the number of Passengers within the Group.
     *
     * @return the number of members as an int.
     */
    @Override
    public int getNumberOfGroupMembers() {
        return this.NUMBER_OF_GROUP_MEMBERS;
    }

    /**
     * Overrides the isPartOfGroup function in the Passenger class:
     * true for groups.
     *
     * @return is true, always in a group.
     */
    @Override
    public boolean isPartOfGroup() {
        return true;
    }

    /**
     * Overrides the processedGroupForDiamond function in the Passenger class:
     * Checks if the amount of time to process the entire group took less than 5 minutes,
     * and if the number of group members exceeds 10 people.
     *
     * @param groupProcessTime total process time of a group.
     * @return is true if all conditions met, and false if either condition violated.
     */
    @Override
    public boolean processedGroupForDiamond(BigDecimal groupProcessTime) {
        if (groupProcessTime.compareTo(BigDecimal.valueOf(5.0)) < 0) {
            if (this.NUMBER_OF_GROUP_MEMBERS > 10) {
                return true;
            }
        }
        return false;
    }
}
