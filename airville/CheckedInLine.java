package airville;

import java.util.Queue;

/**
 * The CheckedInLine class represents a Queue of Passengers.
 *
 * @author Andrew Hwang
 * @version 1.0 Dec. 03, 2015
 */
public class CheckedInLine {

    /**
     * Queue holding all the passengers in the line.
     */
    private Queue<Passenger> passengers;
    /**
     * determines whether the line is empty or not.
     */
    private boolean LINE_IS_EMPTY;

    /**
     * The Constructor takes in a Queue of Passengers as its parameter.
     * If the Queue is empty, the line is empty.
     *
     * @param passengers a set queue of passengers contained in a line.
     */
    public CheckedInLine(Queue<Passenger> passengers) {
        if (passengers.isEmpty()) {
            this.LINE_IS_EMPTY = true;
        }
        else {
            this.passengers = passengers;
            this.LINE_IS_EMPTY = false;
        }
    }

    /**
     * Gets the Passenger who is first/next in the Queue.
     * Catches a NullPointerException if there are no more passengers next in line.
     *
     * @return the Passenger at the front of the Queue, and removes him/her from the Queue.
     * @throws NullPointerException the Queue of passengers is empty so we cannot get the next passenger.
     */
    public Passenger getNextPassenger() throws NullPointerException{
        try {
            Passenger nextPassenger = this.passengers.poll();
            this.LINE_IS_EMPTY = passengers.isEmpty();
            return nextPassenger;
        }
        catch (NullPointerException e) {
            this.LINE_IS_EMPTY = true;
            throw new NullPointerException("The Queue of Passengers is empty!");
        }
    }

    /**
     * Gets the entire Queue of Passengers from the line.
     *
     * @return Queue of Passengers.
     */
    public Queue<Passenger> getQueueOfPassengers() {
        return this.passengers;
    }

    public void setQueueOfPassengers(Queue<Passenger> passengers) {
        this.passengers = passengers;
    }

    /**
     * Checks whether the line is empty by returning the boolean value of the field LINE_IS_EMPTY.
     *
     * @return boolean value of LINE_IS_EMPTY.
     */
    public boolean thisLineIsEmpty() {
       return this.LINE_IS_EMPTY;
    }
}
