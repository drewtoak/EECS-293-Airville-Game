package airville;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.EnumSet;

import static org.junit.Assert.*;

/**
 * Created by Andrew Hwang on 11/19/2015.
 */
public class FrequentFlierPassengerTest {

    @Test
    public void testGetPassengerProcessTime() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        FrequentFlierPassenger passenger = new FrequentFlierPassenger(slowPassengerTypes);

        double addedTime = (SlowPassengerType.EXCESS_BAGGAGE.getAddedTime() + SlowPassengerType.REROUTED.getAddedTime()+ SlowPassengerType.OVERBOOKED.getAddedTime() + Game.REGULAR_PROCESS_TIME);
        BigDecimal processedTime = BigDecimal.valueOf(addedTime);
        processedTime = processedTime.multiply(BigDecimal.valueOf(Game.FREQUENT_FLIER_TIME_MULTIPLIER));

        assertTrue(passenger.getPassengerProcessTime().equals(processedTime));
    }

    @Test
    public void testGetSlowSlowPassengerTypes() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        FrequentFlierPassenger passenger = new FrequentFlierPassenger(slowPassengerTypes);

        assertTrue(passenger.getSlowPassengerTypes().equals(slowPassengerTypes));
    }

    @Test
    public void testIsSlowPassenger() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        FrequentFlierPassenger passenger = new FrequentFlierPassenger(slowPassengerTypes);


        assertTrue(passenger.isSlowPassenger());
    }

}