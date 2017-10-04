package airville;

import java.math.BigDecimal;

/**
 * The Staff class is a class that controls a checked in line.
 *
 * @author Andrew Hwang
 * @version 1.0 Dec. 03, 2015
 */
public class Staff {

    /**
     * Holds the checked in line the staff member is in charge of.
     */
    public CheckedInLine checkedInLine;
    /**
     * Determines if a Supervisor is with the staff member.
     */
    private boolean supervisorPresent;

    /**
     * The Constructor takes in a checked in line that the Staff class will become in charge of.
     * Initializes without a supervisor present with the staff member.
     *
     * @param checkedInLine a line passed to this staff member.
     */
    public Staff(CheckedInLine checkedInLine) {
        this.checkedInLine = checkedInLine;
        this.supervisorPresent = false;
    }

    /**
     * Calculates the time it takes for the next passenger in line will take,
     * depending on the circumstances of whether a supervisor is present,
     * and if the next passenger in line is actually a group of passengers.
     * Also, rewards are awarded for successfully processing a passenger.
     *
     * @return the total time it takes to process the next passenger.
     */
    public BigDecimal processNextPassenger() {
        Passenger nextPassenger = this.checkedInLine.getNextPassenger();

        //get the next passenger in the line.
        BigDecimal passengerProcessTime = nextPassenger.getPassengerProcessTime();

        //if the supervisor is present, then multiply the passenger's process time by the supervisor's multiplier
        if (this.supervisorPresent) {
            passengerProcessTime = passengerProcessTime.multiply(BigDecimal.valueOf(Game.SUPERVISOR_TIME_MULTIPLIER));
            Game.NUMBER_OF_BUSY_SUPERVISORS--;
        }

        //if the passenger is part of a group, then see if the player processed them for a diamond.
        if (nextPassenger.isPartOfGroup()) {
            if (nextPassenger.processedGroupForDiamond(passengerProcessTime)) {
                Game.DIAMONDS++;
            }
            Game.POINTS += Game.REWARDED_POINTS*nextPassenger.getNumberOfGroupMembers();
        }
        //reward the player for processing.
        else {
            Game.POINTS += Game.REWARDED_POINTS;
        }
        return passengerProcessTime;
    }

    /**
     * Predicts the total processing time for the entire line,
     * but not taking into account if a supervisor is present.
     *
     * @return the predicted time for the line to be processed.
     */
    public BigDecimal processQueueOfPassengers(){
        BigDecimal processTimeOfLine = BigDecimal.ZERO;

        //get the process time of the whole line.
        for(Passenger passenger: this.checkedInLine.getQueueOfPassengers()) {
            processTimeOfLine = processTimeOfLine.add(passenger.getPassengerProcessTime());
        }
        return processTimeOfLine;
    }

    /**
     * Assigns a non-busy supervisor to help this staff member.
     * @throws EmployeeException all supervisors are busy.
     */
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
     * Un-assigns a busy supervisor who was currently helping this staff member.
     * @throws EmployeeException there are no supervisors here.
     */
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
     * Verifies if a Supervisor is currently helping the staff member.
     * @return true if there exists a supervisor, else false.
     */
    public boolean isSupervisorPresent() {
        return this.supervisorPresent;
    }

    /**
     * Gets the line that this staff member is assigned to.
     * @return this checked in line.
     */
    public CheckedInLine getCheckedInLine() {
        return this.checkedInLine;
    }

    /**
     * Assigns this staff member to a new line to process.
     * @param newLine the desired line to process.
     * @throws EmployeeException
     */
    public void setCheckedInLine(CheckedInLine newLine) throws EmployeeException {
        this.checkedInLine = newLine;
    }
}
