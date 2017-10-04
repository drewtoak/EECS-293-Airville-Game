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
public class RobotTest {

    @Test
    public void testProcessNextPassenger() throws Exception {
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.noneOf(SlowPassengerType.class);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        Passenger passenger = new Passenger(slowPassengerTypes);
        Queue<Passenger> passengerLine = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        Robot robot = new Robot(checkedInLine);

        BigDecimal processedTime = BigDecimal.valueOf(Game.REGULAR_PROCESS_TIME);
        processedTime = processedTime.multiply(BigDecimal.valueOf(passengerGroup.getNumberOfGroupMembers()));
        processedTime = processedTime.multiply(BigDecimal.valueOf(Game.PASSENGER_GROUP_TIME_MULTIPLIER));

        assertTrue(robot.processNextPassenger().equals(processedTime));
    }

    @Test
    public void testSetSupervisor() throws Exception {
        Game start = new Game();
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.noneOf(SlowPassengerType.class);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        Passenger passenger = new Passenger(slowPassengerTypes);
        Queue<Passenger> passengerLine = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        Robot robot = new Robot(checkedInLine);

        robot.setSupervisor();

        assertTrue(robot.isSupervisorPresent());
    }

    @Test
    public void testRemoveSupervisor() throws Exception {
        Game start = new Game();
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.noneOf(SlowPassengerType.class);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        Passenger passenger = new Passenger(slowPassengerTypes);
        Queue<Passenger> passengerLine = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        Robot robot = new Robot(checkedInLine);

        robot.setSupervisor();
        robot.removeSupervisor();

        assertFalse(robot.isSupervisorPresent());
    }

    @Test
    public void testSetCheckedInLine() throws Exception {
        Game start = new Game();
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.noneOf(SlowPassengerType.class);
        EnumSet<SlowPassengerType> slowPassengerTypes2 = EnumSet.noneOf(SlowPassengerType.class);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        PassengerGroup passengerGroup2 = new PassengerGroup(slowPassengerTypes2, 12);
        FrequentFlierPassenger passenger = new FrequentFlierPassenger(slowPassengerTypes);
        Passenger passenger2 = new Passenger(slowPassengerTypes2);
        Queue<Passenger> passengerLine = new LinkedList<>();
        Queue<Passenger> passengerLine2 = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        passengerLine2.add(passengerGroup2);
        passengerLine2.add(passenger2);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        CheckedInLine checkedInLine2 = new CheckedInLine(passengerLine2);
        Robot robot = new Robot(checkedInLine);

        robot.processNextPassenger();
        robot.processNextPassenger();

        robot.setCheckedInLine(checkedInLine2);

        assertTrue(robot.getCheckedInLine().equals(checkedInLine2));
    }

    @Test
    public void testIsTheLineEmpty() throws Exception {
        Game start = new Game();
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.noneOf(SlowPassengerType.class);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        Passenger passenger = new Passenger(slowPassengerTypes);
        Queue<Passenger> passengerLine = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        Robot robot = new Robot(checkedInLine);

        robot.processNextPassenger();
        robot.processNextPassenger();

        assertTrue(robot.isTheLineEmpty());
    }
}