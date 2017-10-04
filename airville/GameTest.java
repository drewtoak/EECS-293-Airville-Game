package airville;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.*;

/**
 * Created by Andrew Hwang on 12/3/2015.
 */
public class GameTest {

    @Test
    public void testSetStaff() throws Exception {
        Game start = new Game();
        EmployeeType staffType = EmployeeType.STAFF;
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        Passenger passenger = new Passenger(slowPassengerTypes);
        Queue<Passenger> passengerLine = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        Staff staff = new Staff(checkedInLine);
        start.setStaff(staffType, checkedInLine);
        List<Staff> staffList = Game.LIST_OF_STAFFS;
        Staff newStaff = staffList.get(staffList.size() - 1);

        assertTrue(newStaff.getCheckedInLine().equals(staff.getCheckedInLine()));
    }

    @Test
    public void testShiftStaffToLine() throws Exception {
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

        start.shiftStaffToLine(staff, checkedInLine2);

        assertTrue(staff.getCheckedInLine().equals(checkedInLine2));
    }

    @Test
    public void testSetSupervisorToStaff() throws Exception {
        Game start = new Game();
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        Passenger passenger = new Passenger(slowPassengerTypes);
        Queue<Passenger> passengerLine = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        Staff staff = new Staff(checkedInLine);

        start.setSupervisorToStaff(staff);

        assertTrue(staff.isSupervisorPresent());
    }

    @Test
    public void testRemoveSupervisorFromStaff() throws Exception {
        Game start = new Game();
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        Passenger passenger = new Passenger(slowPassengerTypes);
        Queue<Passenger> passengerLine = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        Staff staff = new Staff(checkedInLine);

        start.setSupervisorToStaff(staff);
        start.removeSupervisorFromStaff(staff);

        assertFalse(staff.isSupervisorPresent());
    }

    @Test
    public void testHireWithPoints() throws Exception {
        Game start = new Game();
        EmployeeType staffType = EmployeeType.STAFF;
        int initialNumberOfStaffs = Game.NUMBER_OF_STAFFS;
        start.hireWithPoints(staffType);
        int currentNumberOfStaffs = Game.NUMBER_OF_STAFFS;
        assertTrue(currentNumberOfStaffs > initialNumberOfStaffs);
    }

    @Test
    public void testHireWithDiamonds() throws Exception {
        Game start = new Game();
        EmployeeType staffType = EmployeeType.STAFF;
        int initialNumberOfStaffs = Game.NUMBER_OF_STAFFS;
        start.hireWithDiamonds(staffType);
        int currentNumberOfStaffs = Game.NUMBER_OF_STAFFS;
        assertTrue(currentNumberOfStaffs > initialNumberOfStaffs);
    }

    @Test
    public void testMakeRedirectedLine() throws Exception{
        Game start = new Game();
        EnumSet<SlowPassengerType> slowPassengerTypes = EnumSet.of(SlowPassengerType.EXCESS_BAGGAGE);
        EnumSet<SlowPassengerType> slowPassengerTypes2 = EnumSet.noneOf(SlowPassengerType.class);
        PassengerGroup passengerGroup = new PassengerGroup(slowPassengerTypes, 11);
        PassengerGroup passengerGroup2 = new PassengerGroup(slowPassengerTypes2, 12);
        FrequentFlierPassenger passenger = new FrequentFlierPassenger(slowPassengerTypes);
        Passenger passenger2 = new Passenger(slowPassengerTypes2);
        Queue<Passenger> passengerLine = new LinkedList<>();
        Queue<Passenger> passengerLine2 = new LinkedList<>();
        passengerLine.add(passengerGroup);
        passengerLine.add(passenger);
        passengerLine.add(passengerGroup2);
        passengerLine.add(passenger2);
        passengerLine2.add(passengerGroup);
        passengerLine2.add(passenger);
        CheckedInLine checkedInLine = new CheckedInLine(passengerLine);
        CheckedInLine checkedInLine2 = new CheckedInLine(passengerLine2);
        Robot robot = new Robot(checkedInLine);

        robot.processNextPassenger();
        robot.processNextPassenger();
        robot.processNextPassenger();
        robot.processNextPassenger();

        start.makeRedirectedLine();

        assertTrue(Game.LINE_OF_REDIRECTED_PASSENGERS.getQueueOfPassengers().equals(checkedInLine2.getQueueOfPassengers()));
    }
}