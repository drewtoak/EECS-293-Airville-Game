package airville;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.EnumSet;

import static org.junit.Assert.*;

/**
 * Created by Andrew Hwang on 11/19/2015.
 */
public class PassengerGroupTest {

    @Test
    public void testGetPassengerProcessTime() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        PassengerGroup passengers = new PassengerGroup(slowPassengerTypes, 11);

        double addedTime = (SlowPassengerType.EXCESS_BAGGAGE.getAddedTime() + SlowPassengerType.REROUTED.getAddedTime()+ SlowPassengerType.OVERBOOKED.getAddedTime() + Game.REGULAR_PROCESS_TIME);
        BigDecimal processedTime = BigDecimal.valueOf(addedTime);
        processedTime = processedTime.multiply(BigDecimal.valueOf(passengers.getNumberOfGroupMembers()));
        processedTime = processedTime.multiply(BigDecimal.valueOf(Game.PASSENGER_GROUP_TIME_MULTIPLIER));

        assertTrue(passengers.getPassengerProcessTime().equals(processedTime));
    }

    @Test
    public void testGetSlowPassengerTypes() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        PassengerGroup passengers = new PassengerGroup(slowPassengerTypes, 11);

        assertTrue(passengers.getSlowPassengerTypes().equals(slowPassengerTypes));
    }

    @Test
    public void testIsSlowPassenger() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        PassengerGroup passengers = new PassengerGroup(slowPassengerTypes, 11);


        assertTrue(passengers.isSlowPassenger());
    }

    @Test
    public void testGetNumberOfGroupMembers() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        PassengerGroup passengers = new PassengerGroup(slowPassengerTypes, 11);

        assertTrue(passengers.getNumberOfGroupMembers() == 11);
    }

    @Test
    public void testIsPartOfGroup() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE, SlowPassengerType.REROUTED, SlowPassengerType.OVERBOOKED);
        PassengerGroup passengers = new PassengerGroup(slowPassengerTypes, 11);


        assertTrue(passengers.isPartOfGroup());
    }

    @Test
    public void testProcessedGroupForDiamond() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE);
        PassengerGroup passengers = new PassengerGroup(slowPassengerTypes, 11);

        assertTrue(passengers.processedGroupForDiamond(BigDecimal.ONE));
    }
}