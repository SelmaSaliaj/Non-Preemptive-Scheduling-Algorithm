package algorithm;

import static algorithm.NonPreemptiveScheduling.*;

public class Main {
    public static final String FILE_NAME = "input.txt";

    public static void main(String[] args) {
        inputValues(FILE_NAME);
        System.out.println("\nThe non-preemptive scheduling algorithm will be executed for" +
                " the given values below:");
        displayResults(inputProcesses);
        System.out.println("\nStarting the execution of the algorithm.\n");
        initiateWaitingQueue();
        displayQueueElements();
        scheduleProcesses();
        System.out.println("\nExtra details:\n");
        NonPreemptiveScheduling.toString(finalOrder);
        System.out.println("\nThe end of the algorithm.\n");
    }

}