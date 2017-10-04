package airville;

import org.junit.Test;

import java.util.EnumSet;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.*;

/**
 * Created by Andrew Hwang on 11/19/2015.
 */
public class CheckedInLineTest {

    @Test
    public void testGetNextPassenger() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE);
        PassengerGroup passenger = new PassengerGroup(slowPassengerTypes, 11);
        Queue<Passenger> passengers = new LinkedList<>();
        passengers.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengers);

        assertTrue(checkedInLine.getNextPassenger().equals(passenger));
    }

    @Test
    public void testThisLineIsEmpty() throws Exception {
        Queue<Passenger> passengers = new LinkedList<>();
        CheckedInLine checkedInLine = new CheckedInLine(passengers);

        assertTrue(checkedInLine.thisLineIsEmpty());
    }

    @Test
    public void testGetQueueOfPassengers() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE);
        PassengerGroup passenger = new PassengerGroup(slowPassengerTypes, 11);
        Queue<Passenger> passengers = new LinkedList<>();
        passengers.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengers);

        assertTrue(checkedInLine.getQueueOfPassengers().equals(passengers));
    }
}