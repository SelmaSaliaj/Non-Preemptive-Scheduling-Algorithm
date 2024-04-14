package algorithm;

public class Process {
    private final String name;
    private final double arrivalTime;
    private final double executionTime;
    private double waitingTime;
    private int priority;

    /**
     * Constructs a Process object with the given attributes.
     *
     * @param name          The name of the process.
     * @param timeOfArrival The arrival time of the process.
     * @param executionTime The execution time required by the process.
     * @param priority      The priority number of the process.
     */
    public Process(String name, double timeOfArrival, double executionTime, int priority) {
        this.name = name;
        this.arrivalTime = timeOfArrival;
        this.executionTime = executionTime;
        this.priority = priority;
    }

    /**
     * Gets the name of the process.
     *
     * @return The name of the process.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the arrival time of the process.
     *
     * @return The arrival time of the process.
     */
    public double getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Gets the execution time of the process.
     *
     * @return The execution time of the process.
     */
    public double getExecutionTime() {
        return executionTime;
    }

    /**
     * Gets the waiting time of the process.
     *
     * @return The waiting time of the process.
     */
    public double getWaitingTime() {
        return waitingTime;
    }

    /**
     * Sets the waiting time of the process.
     *
     * @param waitingTime The waiting time of the process.
     */
    public void setWaitingTime(double waitingTime) {
        this.waitingTime = waitingTime;
    }

    /**
     * Sets the priority of the process.
     *
     * @param priority The priority of the process.
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Gets the priority of the process.
     *
     * @return The priority of the process.
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Returns a string representation of the Process object.
     *
     * @return A string representation of the Process object.
     */
    @Override
    public String toString() {
        return "algorithm.Process{" +
                "name='" + name + '\'' +
                ", arrivalTime=" + arrivalTime +
                ", executionTime=" + executionTime +
                ", priority=" + priority +
                '}';
    }
}
