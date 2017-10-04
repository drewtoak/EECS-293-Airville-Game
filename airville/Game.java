package airville;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * The Game class holds constants used to to calculate process times and the control of employees and lines.
 * Defaults any variables that may have their values altered.
 *
 * @author Andrew Hwang
 * @version 1.0 Dec. 03, 2015
 */
public class Game {
    
    //Constants used for processing time:
    public static final double REGULAR_PROCESS_TIME = 1;
    public static final double FREQUENT_FLIER_TIME_MULTIPLIER = 0.75;
    public static final double PASSENGER_GROUP_TIME_MULTIPLIER = 0.75;
    public static final double EXCESS_BAGGAGE_PROCESS_TIME = 5;
    public static final double REROUTED_PROCESS_TIME = 7;
    public static final double OVERBOOKED_PROCESS_TIME = 12;
    public static final double SUPERVISOR_TIME_MULTIPLIER = 0.5;

    // Constants used for determining how points and diamonds are handled:
    public static final int REWARDED_POINTS = 10;
    public static int POINTS;
    public static int DIAMONDS;
    public static int POINTS_FOR_HIRE;
    public static int DIAMONDS_FOR_HIRE;

    // Constants used for handling all staff members:
    public static int NUMBER_OF_SUPERVISORS;
    public static int NUMBER_OF_BUSY_SUPERVISORS;
    public static int NUMBER_OF_STAFFS;
    public static int NUMBER_OF_BUSY_STAFFS;
    public static int NUMBER_OF_ROBOTS;
    public static int NUMBER_OF_BUSY_ROBOTS;
    public static List<Staff> LIST_OF_STAFFS;
    public static List<Robot> LIST_OF_ROBOTS;

    // Constants used for redirected passengers:
    public static Queue<Passenger> QUEUE_OF_REDIRECTED_PASSENGERS;
    public static CheckedInLine LINE_OF_REDIRECTED_PASSENGERS;

    /**
     * The Constructor does not take in any parameters,
     * but sets the default values for any dynamic variables.
     * These variables can be adjusted in this constructor.
     */
    public Game() {
        POINTS = 100;
        DIAMONDS = 10;
        POINTS_FOR_HIRE = 50;
        DIAMONDS_FOR_HIRE = 5;

        NUMBER_OF_SUPERVISORS = 1;
        NUMBER_OF_BUSY_SUPERVISORS = 0;
        NUMBER_OF_STAFFS = 1;
        NUMBER_OF_BUSY_STAFFS = 0;
        NUMBER_OF_ROBOTS = 1;
        NUMBER_OF_BUSY_ROBOTS = 0;

        LIST_OF_STAFFS = new LinkedList<>();
        LIST_OF_ROBOTS = new LinkedList<>();

        QUEUE_OF_REDIRECTED_PASSENGERS = new LinkedList<>();
        LINE_OF_REDIRECTED_PASSENGERS = new CheckedInLine(QUEUE_OF_REDIRECTED_PASSENGERS);
    }

    /**
     * Sets a checked in line to a specified non-busy staff member,
     * except for a Supervisor, since they need to be set to both another staff member and line.
     *
     * @param employeeType the type of employee
     * @param checkedInLine is a line that does not have an employee processing it.
     * @throws GameException need hire more employees or can't set a supervisor to a line.
     */
    public void setStaff(EmployeeType employeeType, CheckedInLine checkedInLine) throws GameException {
        if (employeeType.equals(EmployeeType.STAFF)) {
            if (NUMBER_OF_STAFFS > NUMBER_OF_BUSY_STAFFS) {
                Staff staff = new Staff(checkedInLine);
                LIST_OF_STAFFS.add(staff);
                NUMBER_OF_BUSY_STAFFS++;
            }
            else {
                throw new GameException(GameException.GameErrorCode.HIRE_MORE_STAFFS);
            }
        }
        else if (employeeType.equals(EmployeeType.ROBOT)) {
            if (NUMBER_OF_ROBOTS > NUMBER_OF_BUSY_ROBOTS) {
                Robot robot = new Robot(checkedInLine);
                LIST_OF_ROBOTS.add(robot);
                NUMBER_OF_BUSY_ROBOTS++;
            }
            else {
                throw new GameException(GameException.GameErrorCode.BUILD_MORE_ROBOTS);
            }
        }
        else {
            throw new GameException(GameException.GameErrorCode.SUPERVISOR_CANNOT_BE_SET);
        }
    }

    /**
     * Shifts either a Staff or Robot to a new line to process.
     *
     * @param staff is specified to be shifted.
     * @param checkedInLine a new line that needs to be processed.
     * @throws EmployeeException
     */
    public void shiftStaffToLine(Staff staff, CheckedInLine checkedInLine) throws EmployeeException {
        staff.setCheckedInLine(checkedInLine);
    }

    /**
     * Assign a Supervisor to help either a Staff or Robot.
     *
     * @param staff is specified for assistance
     * @throws EmployeeException
     */
    public void setSupervisorToStaff(Staff staff) throws EmployeeException {
        staff.setSupervisor();
    }

    /**
     * Un-assign a Supervisor from helping a Staff or Robot that is currently being helped.
     *
     * @param staff is specified to remove assistance.
     * @throws EmployeeException
     */
    public void removeSupervisorFromStaff(Staff staff) throws EmployeeException {
        staff.removeSupervisor();
    }

    /**
     * Hires a new employee at a cost of points.
     *
     * @param employeeType the specified employee to hire.
     * @throws GameException need more points for purchase.
     */
    public void hireWithPoints(EmployeeType employeeType) throws GameException {
        if (POINTS >= POINTS_FOR_HIRE) {
            if (employeeType.equals(EmployeeType.STAFF)) {
                NUMBER_OF_STAFFS++;
            }
            else if (employeeType.equals(EmployeeType.ROBOT)) {
                NUMBER_OF_ROBOTS++;
            }
            else {
                NUMBER_OF_SUPERVISORS++;
            }
            POINTS = POINTS - POINTS_FOR_HIRE;
        }
        else {
            throw new GameException(GameException.GameErrorCode.NEED_MORE_POINTS);
        }
    }

    /**
     * Redeems a new employee at a cost of Diamonds.
     *
     * @param employeeType the specified employee to redeem.
     * @throws GameException need more diamonds for purchase.
     */
    public void hireWithDiamonds(EmployeeType employeeType) throws GameException {
        if (DIAMONDS >= DIAMONDS_FOR_HIRE) {
            if (employeeType.equals(EmployeeType.STAFF)) {
                NUMBER_OF_STAFFS++;
            }
            else if (employeeType.equals(EmployeeType.ROBOT)) {
                NUMBER_OF_ROBOTS++;
            }
            else {
                NUMBER_OF_SUPERVISORS++;
            }
            DIAMONDS = DIAMONDS - DIAMONDS_FOR_HIRE;
        }
        else {
            throw new GameException(GameException.GameErrorCode.NEED_MORE_DIAMONDS);
        }
    }

    /**
     * Forms a line for a Queue of redirected passengers.
     */
    public void makeRedirectedLine() {
        LINE_OF_REDIRECTED_PASSENGERS.setQueueOfPassengers(QUEUE_OF_REDIRECTED_PASSENGERS);
    }
}
