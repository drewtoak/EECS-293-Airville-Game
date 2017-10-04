package airville;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

/**
 * Created by Andrew Hwang on 12/3/2015.
 */
public class StaffTest {

    @Test
    public void testProcessNextPassenger() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        Passenger passenger = new Passenger(slowPassengerTypes);
        Queue<Passenger> passengerLine = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        Staff staff = new Staff(checkedInLine);

        double addedTime = (SlowPassengerType.EXCESS_BAGGAGE.getAddedTime() + Game.REGULAR_PROCESS_TIME);
        BigDecimal processedTime = BigDecimal.valueOf(addedTime);
        processedTime = processedTime.multiply(BigDecimal.valueOf(passengerGroup.getNumberOfGroupMembers()));
        processedTime = processedTime.multiply(BigDecimal.valueOf(Game.PASSENGER_GROUP_TIME_MULTIPLIER));

        assertTrue(staff.processNextPassenger().equals(processedTime));
    }

    @Test
    public void testSetSupervisor() throws Exception {
        Game start = new Game();
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        Passenger passenger = new Passenger(slowPassengerTypes);
        Queue<Passenger> passengerLine = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        Staff staff = new Staff(checkedInLine);

        staff.setSupervisor();

        assertTrue(staff.isSupervisorPresent());
    }

    @Test
    public void testRemoveSupervisor() throws Exception {
        Game start = new Game();
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        Passenger passenger = new Passenger(slowPassengerTypes);
        Queue<Passenger> passengerLine = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        Staff staff = new Staff(checkedInLine);

        staff.setSupervisor();
        staff.removeSupervisor();

        assertFalse(staff.isSupervisorPresent());
    }

    @Test
    public void testSetCheckedInLine() throws Exception {
        Game start = new Game();
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE);
        EnumSet<SlowPassengerType> slowPassengerTypes2 = EnumSet.of(SlowPassengerType.OVERBOOKED);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        PassengerGroup passengerGroup2 = new PassengerGroup(slowPassengerTypes2, 12);
        Passenger passenger = new Passenger(slowPassengerTypes);
        Passenger passenger2 = new Passenger(slowPassengerTypes2);
        Queue<Passenger> passengerLine = new LinkedList<>();
        Queue<Passenger> passengerLine2 = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        passengerLine2.add(passengerGroup2);
        passengerLine2.add(passenger2);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        CheckedInLine checkedInLine2 = new CheckedInLine(passengerLine2);
        Staff staff = new Staff(checkedInLine);

        staff.setCheckedInLine(checkedInLine2);

        assertTrue(staff.getCheckedInLine().equals(checkedInLine2));
    }

    @Test
    public void testProcessQueueOfPassengers() throws Exception {
        Game start = new Game();
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        Passenger passenger = new Passenger(slowPassengerTypes);
        Queue<Passenger> passengerLine = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        Staff staff = new Staff(checkedInLine);

        double addedTime = SlowPassengerType.EXCESS_BAGGAGE.getAddedTime() + Game.REGULAR_PROCESS_TIME;
        BigDecimal processedTime = BigDecimal.valueOf(addedTime);
        processedTime = processedTime.multiply(BigDecimal.valueOf(passengerGroup.getNumberOfGroupMembers()));
        processedTime = processedTime.multiply(BigDecimal.valueOf(Game.PASSENGER_GROUP_TIME_MULTIPLIER));
        processedTime = processedTime.add(BigDecimal.valueOf(addedTime));

        assertTrue(staff.processQueueOfPassengers().equals(processedTime));
    }

    @Test
    public void testIsSupervisorPresent() throws Exception {
        Game start = new Game();
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        Passenger passenger = new Passenger(slowPassengerTypes);
        Queue<Passenger> passengerLine = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        Staff staff = new Staff(checkedInLine);

        assertFalse(staff.isSupervisorPresent());
    }
}