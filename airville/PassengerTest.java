package airville;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.EnumSet;

import static org.junit.Assert.*;

/**
 * Created by Andrew Hwang on 12/2/2015.
 */
public class PassengerTest {

    @Test
    public void testGetPassengerProcessTime() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        Passenger passenger = new Passenger(slowPassengerTypes);

        double processedTime = SlowPassengerType.EXCESS_BAGGAGE.getAddedTime() + SlowPassengerType.REROUTED.getAddedTime()+ SlowPassengerType.OVERBOOKED.getAddedTime() + Game.REGULAR_PROCESS_TIME;

        assertTrue(passenger.getPassengerProcessTime().equals(BigDecimal.valueOf(processedTime)));
    }

    @Test
    public void testGetSlowPassengerTypes() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        Passenger passenger = new Passenger(slowPassengerTypes);

        assertTrue(passenger.getSlowPassengerTypes().equals(slowPassengerTypes));
    }

    @Test
    public void testIsSlowPassenger() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        Passenger passenger = new Passenger(slowPassengerTypes);

        assertTrue(passenger.isSlowPassenger());
    }

    @Test
    public void testGetNumberOfGroupMembers() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        Passenger passenger = new Passenger(slowPassengerTypes);

        assertTrue(passenger.getNumberOfGroupMembers() == 1);
    }

    @Test
    public void testIsPartOfGroup() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        Passenger passenger = new Passenger(slowPassengerTypes);

        assertFalse(passenger.isPartOfGroup());
    }

    @Test
    public void testProcessedGroupForDiamond() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        Passenger passenger = new Passenger(slowPassengerTypes);

        assertFalse(passenger.processedGroupForDiamond(BigDecimal.ONE));
    }
}