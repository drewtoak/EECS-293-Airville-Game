package airville;

import java.math.BigDecimal;

/**
 * The Robot class extends the Staff class;
 * sets up an automated line to process a line of passengers.
 *
 * @author Andrew Hwang
 * @version 1.0 Dec. 03, 2015
 */
public class Robot extends Staff {

    /**
     * Determines if a Supervisor is helping this Robot
     */
    private boolean supervisorPresent;

    /**
     * The Constructor takes in the same value as the Constructor in the Staff class.
     * And functions similarly to the Staff's constructor.
     *
     * @param checkedInLine a line passed to this Robot.
     */
    public Robot(CheckedInLine checkedInLine) {
        super(checkedInLine);
        this.supervisorPresent = false;
    }

    /**
     * Overrides the processNextPassenger function in the Staff class:
     * if the next passenger is a slow type of passenger,
     * then that passenger is redirected to a line that will be processed by a Staff.
     *
     * @return total time it takes to process the next passenger.
     */
    @Override
    public BigDecimal processNextPassenger() {
        Passenger nextPassenger = this.checkedInLine.getNextPassenger();
        BigDecimal passengerProcessTime = nextPassenger.getPassengerProcessTime();
        //if the passenger is a slow type, then redirect them to a different line.
        if (nextPassenger.isSlowPassenger()) {
            Game.QUEUE_OF_REDIRECTED_PASSENGERS.add(nextPassenger);
            return null;
        }
        //same as Staff's processNextPassenger function.
        else {
            if (this.supervisorPresent) {
                passengerProcessTime = passengerProcessTime.multiply(BigDecimal.valueOf(Game.SUPERVISOR_TIME_MULTIPLIER));
                this.supervisorPresent = false;
                Game.NUMBER_OF_BUSY_SUPERVISORS--;
            }

            if (nextPassenger.isPartOfGroup()) {
                if (nextPassenger.processedGroupForDiamond(passengerProcessTime)) {
                    Game.DIAMONDS++;
                }
                Game.POINTS += Game.REWARDED_POINTS*nextPassenger.getNumberOfGroupMembers();
            }
            else {
                Game.POINTS += Game.REWARDED_POINTS;
            }
        }
        return passengerProcessTime;
    }

    /**
     * Sets a non-busy Supervisor to help this Robot.
     *
     * @throws EmployeeException all supervisors are busy.
     */
    @Override
    public void setSupervisor() throws EmployeeException{
        if (Game.NUMBER_OF_BUSY_SUPERVISORS < Game.NUMBER_OF_SUPERVISORS) {
            this.supervisorPresent = true;
            Game.NUMBER_OF_BUSY_SUPERVISORS++;
        }
        else {
            throw new EmployeeException(EmployeeException.EmployeeErrorCode.ALL_SUPERVISORS_ARE_BUSY);
        }
    }

    /**
     * Removes a busy Supervisor from helping this Robot.
     *
     * @throws EmployeeException EmployeeException there are no supervisors here.
     */
    @Override
    public void removeSupervisor() throws EmployeeException{
        if (this.isSupervisorPresent()) {
            this.supervisorPresent = false;
            Game.NUMBER_OF_BUSY_SUPERVISORS--;
        }
        else {
            throw new EmployeeException(EmployeeException.EmployeeErrorCode.NO_SUPERVISORS_PRESENT);
        }
    }

    /**
     * Verifies if a Supervisor is currently helping this Robot.
     *
     * @return a boolean whether the supervisor is present with this Robot.
     */
    @Override
    public boolean isSupervisorPresent() {
        return this.supervisorPresent;
    }

    /**
     * Shifts this Robot to a different line to process,
     * if the previous line was completely processed.
     *
     * @param newLine the desired line to process.
     * @throws EmployeeException there are still passengers in the automated line.
     */
    @Override
    public void setCheckedInLine(CheckedInLine newLine) throws EmployeeException{
        if (this.isTheLineEmpty()) {
            this.checkedInLine = newLine;
        }
        else {
            throw new EmployeeException(EmployeeException.EmployeeErrorCode.PASSENGERS_STIL_PRESENT);
        }
    }

    /**
     * Verifies if the line being processed is empty.
     *
     * @return a boolean if there is no line for the Robot to process.
     */
    public boolean isTheLineEmpty() {
        return this.checkedInLine.thisLineIsEmpty();
    }
}
