package algorithm;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NonPreemptiveScheduling {
    public static List<Process> waitingQueue = new ArrayList<>();
    public static List<Process> finalOrder = new ArrayList<>();
    public static List<Process> inputProcesses = new ArrayList<>();
    public static double currentTime;

    /**
     * It is responsible for reading the requested input
     * from a given file
     *
     * @param filename   the name of the file we want to read from
     */
    public static void inputValues(String filename){
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))){
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null){
                String [] attributes  = line.split(",");
                String name = attributes[0];
                double arrivalTime = Double.parseDouble(attributes[1]);
                double executionTime = Double.parseDouble(attributes[2]);
                int priority = Integer.parseInt(attributes[3]);
                inputProcesses.add(new Process(name,arrivalTime,executionTime,priority));
            }
        } catch (IOException exception){
            System.out.println("There is a problem accessing the file.");
        }
    }

    /**
     * Sorts the input values based on the <i>arrival timing</i><br><br>
     * Adds the first processes, that are ready to be executed, into a
     * <b>waiting queue</b><br>
     * <br> Updates the <i>initial timing</i> of the program when needed
     */
    public static void initiateWaitingQueue(){
        displayCurrentTime();
        inputProcesses.sort(Comparator.comparing(Process::getArrivalTime));
        if(!inputProcesses.isEmpty()){
            updateWaitingQueue(currentTime);
            if(waitingQueue.isEmpty()){
                currentTime = inputProcesses.get(0).getArrivalTime();
                initiateWaitingQueue();
            }
        }
    }

    /**
     * It is the core of the non-preemptive scheduling algorithm.
     * <p>Responsible for:<ol>
     *     <li>sorts the value of the <b>waiting queue</b> based on the <i>priority number</i>
     *     and for values with <b>the same priority number</b> they are sorted based on the
     *     <i>arrival timing</i></li>
     *     <li>updating the value of the "program's timing" </li>
     *     <li>populating the <b>final order</b> of the queue</li>
     *     <li>removing the processes that have already been executed from the <b>waiting
     *     queue</b></li>
     * </ol></p>
     */
    public static void scheduleProcesses(){
        while (!waitingQueue.isEmpty()){
            waitingQueue.sort(Comparator.comparing(Process::getPriority)
                    .thenComparing(Process::getArrivalTime));
            Process activeProcess = waitingQueue.get(0);
            System.out.println("\nThe process being executed currently: " +
                    activeProcess.getName());
            if (activeProcess.getArrivalTime() > currentTime) {
                currentTime = activeProcess.getArrivalTime();
            }
            activeProcess.setWaitingTime(currentTime - activeProcess.getArrivalTime());
            currentTime += activeProcess.getExecutionTime();
            finalOrder.add(activeProcess);
            System.out.println(activeProcess.getName() +
                    " finished executing. CPU released. \n");
            waitingQueue.remove(activeProcess);
            if(!inputProcesses.isEmpty()) {
                updateWaitingQueue(currentTime);
            }
            displayCurrentTime();
            displayQueueElements();
        }
    }

    /**
     * Updates the values of the <b>waiting queue</b> based on the given timing <br><br>
     * Removes those processes from the initial list of processes
     *
     * @param currentTime  the running time the program is at the moment after executing
     *                     a number of processes
     */
    public static void updateWaitingQueue(double currentTime){
        for (Process pendingProcess : inputProcesses) {
            if(pendingProcess.getArrivalTime() <= currentTime){
                waitingQueue.add(pendingProcess);
            }
        }
        inputProcesses.removeAll(waitingQueue);
    }

    /**
     * Prints in the console the current running <b>time</b> of the program
     */
    public static void displayCurrentTime(){
        System.out.println("Current time: " + currentTime + "\n");
    }

    /**
     * Prints in the console the elements of the list. <br><br> When the list is empty,
     * it will print the message <i><b>"No processes are present."</b></i>
     *
     * @param processesList  the list we want to display the elements of
     */
    public static void displayResults(List<Process> processesList){
        if(processesList.isEmpty()){
            System.out.println("No processes are present.");
            return;
        }
        for (Process process: processesList) {
            System.out.println(process.toString());
        }
    }

    /**
     * Prints in the console the elements within the <b>waiting queue</b> and the <b>final
     * queue</b>
     */
    public static void displayQueueElements(){
        System.out.println("Within the waiting queue:");
        displayResults(waitingQueue);
        System.out.println("\nWithin the processed queue:");
        displayResults(finalOrder);
    }

    /**
     * Prints in the console extra information such as: <ol>
     *     <li>waiting time</li>
     *     <li>response time</li>
     * </ol>
     *
     * @param processesList the list, the elements of which, we want to get extra information
     *                     on
     */
    public static void toString(List<Process> processesList) {
        for (Process process: processesList) {
            double responseTime = process.getWaitingTime() + process.getExecutionTime();
            System.out.println(process.getName() + " --> waiting time: " +
                    process.getWaitingTime() + " , response time: " +
                    responseTime);
        }
    }
}
