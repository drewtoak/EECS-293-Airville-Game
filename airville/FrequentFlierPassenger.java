package airville;

import java.math.BigDecimal;
import java.util.EnumSet;

/**
 * The FrequentFlierPassenger class extends the Passenger abstract class;
 * sets up a frequent flier passenger.
 *
 * @author Andrew Hwang
 * @version 1.0 Dec. 03, 2015
 */
public class FrequentFlierPassenger extends Passenger {

    /**
     * The Constructor takes in the same parameters as the Constructor in the Passenger class.
     *
     * @param slowPassengerTypes type of the Frequent Flier
     */
    public FrequentFlierPassenger(EnumSet<SlowPassengerType> slowPassengerTypes) {
        super(slowPassengerTypes);
    }

    /**
     * Overrides the getPassengerProcessTime function in the Passenger class:
     * the total process time is altered by a multiplier, because the process time for a frequent flier is shorter than a regular flier.
     *
     * @return the total time of a frequent flier as a BigInteger.
     */
    @Override
    public BigDecimal getPassengerProcessTime() {
        BigDecimal passengerProcessTime = BigDecimal.valueOf(Game.REGULAR_PROCESS_TIME);

        //for each slow passenger type add its time value to the passenger's total process time.
        for (SlowPassengerType passengerType: this.slowPassengerTypes) {
            passengerProcessTime = passengerProcessTime.add(BigDecimal.valueOf(passengerType.getAddedTime()));
        }

        //multiply the passenger's process time by the frequent flier's multiplier.
        passengerProcessTime = passengerProcessTime.multiply(BigDecimal.valueOf(Game.FREQUENT_FLIER_TIME_MULTIPLIER));
        return passengerProcessTime;
    }
}
