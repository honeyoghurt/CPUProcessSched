import java.util.Scanner;

public class nonPreemptivePriority
{

    int burstTime[];
    int priority[];
    int arrivalTime[];
    int processId[];
    int numberOfProcess;

    void getProcessData(Scanner input) 
    {
        System.out.println("--------------------------------------------");
        System.out.println("|          Non-Preemptive Priority         |");
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println("Enter the number of Process for Scheduling: ");
        int inputNumberOfProcess = input.nextInt();
        numberOfProcess = inputNumberOfProcess;
        burstTime = new int[numberOfProcess];
        priority = new int[numberOfProcess];
        arrivalTime = new int[numberOfProcess];
        processId = new int[numberOfProcess];
        for (int i = 0; i < numberOfProcess; i++) 
        {
            System.out.print("Enter the burst time   for Process - " + (i+1) + " : ");
            burstTime[i] = input.nextInt();
            System.out.print("Enter the arrival time for Process - " + (i+1) + " : ");
            arrivalTime[i] = input.nextInt();
            System.out.print("Enter the priority     for Process - " + (i+1) + " : ");
            priority[i] = input.nextInt();
            processId[i] = i + 1;
            System.out.println();
        }
    }

    void sortAccordingArrivalTimeAndPriority(int[] at, int[] bt, int[] prt, int[] pid) 
    {

        int temp;
        for (int i = 0; i < numberOfProcess; i++) 
        {
            for (int j = 0; j < numberOfProcess - i - 1; j++) 
            {
                if (at[j] > at[j + 1] || prt[j] == prt[j+1]) 
                {
                    //swapping arrival time
                    temp = at[j];
                    at[j] = at[j + 1];
                    at[j + 1] = temp;

                    //swapping burst time
                    temp = bt[j];
                    bt[j] = bt[j + 1];
                    bt[j + 1] = temp;

                    //swapping priority
                    temp = prt[j];
                    prt[j] = prt[j + 1];
                    prt[j + 1] = temp;

                    //swapping process identity
                    temp = pid[j];
                    pid[j] = pid[j + 1];
                    pid[j + 1] = temp;

                }
                //sorting according to priority when arrival timings are same
                if (at[j] == at[j + 1]) 
                {                    
                    if(prt[j] > prt[j + 1]) 
                    {
                        //swapping arrival time
                        temp = at[j];
                        at[j] = at[j + 1];
                        at[j + 1] = temp;

                        //swapping burst time
                        temp = bt[j];
                        bt[j] = bt[j + 1];
                        bt[j + 1] = temp;

                        //swapping priority
                        temp = prt[j];
                        prt[j] = prt[j + 1];
                        prt[j + 1] = temp;

                        //swapping process identity
                        temp = pid[j];
                        pid[j] = pid[j + 1];
                        pid[j + 1] = temp;
                    }  
                }   
            }

        }
    }

    void priorityNonPreemptiveAlgorithm() 
    {
        //int finishTime[] = new int[numberOfProcess];
        int bt[] = burstTime.clone();
        int at[] = arrivalTime.clone();
        int prt[] = priority.clone();
        int pid[] = processId.clone();
        //int waitingTime[] = new int[numberOfProcess];
        //int turnAroundTime[] = new int[numberOfProcess];

        boolean flag = true;
        int ft = 0;
        int ind = 0, wt, tnt, min;
        int max = prt[0];
        float avg = 0;
        float avtnt = 0;

        sortAccordingArrivalTimeAndPriority(pid, prt, at, bt);
        System.out.println();
        System.out.println("Priority Scheduling Algorithm : ");
        System.out.println();
        System.out.format("%20s%20s%20s%20s\n", "ProcessID", "BurstTime", "ArrivalTime", "Priority");
        for (int i = 0; i < numberOfProcess; i++) {
            System.out.format("%20d%20d%20d%20d\n", pid[i], bt[i], at[i], prt[i]);
        }

        sortAccordingArrivalTimeAndPriority(at, pid, bt, prt);
        ft = at[0];
        sortAccordingArrivalTimeAndPriority(prt, pid, bt, at);
        for(int i = 1; i < numberOfProcess; i++){
            if(max < prt[i]){
                max = prt[i];
            }
        }
        min = max + 1;
        System.out.println();
        System.out.format("%20s%20s%20s\n", "ProcessID", "Waiting time", "Turnaround time");
        while (flag){
            for(int i = 0; i < numberOfProcess; i++){
                if(ft >= at[i] && min > prt[i] && pid[i] > 0){
                    ind = i;
                    min = prt[i];
                }
            }
            if(pid[ind] == -1){
                ft++;
                continue;
            }
            wt = ft - at[ind];
            ft += bt[ind];
            avg += wt;
            tnt = ft - at[ind];
            avtnt += tnt;

            System.out.format("%20d%20d%20d\n", pid[ind], wt, tnt );
            pid[ind] = -1;
            min = max + 1;
            flag = false;
            for(int k = 0; k < numberOfProcess; k++){
                if(pid[k] != -1){
                    flag = true;
                }
            }
        }
        avg = avg / numberOfProcess;
        avtnt /=  numberOfProcess;
        System.out.println("Average waiting time " + avg);
        System.out.println("Average turnaround time " + avtnt); 

        //--------------------------------------------------------------------------------------------------------------------------
        /*sortAccordingArrivalTimeAndPriority(prt, pid, bt, at);
        System.out.print(" ");
        for(int i = 0; i < numberOfProcess; i++){
            for(int j = 0; j < bt[i]; j++)
                System.out.print("--");
                System.out.print(" ");
        }
        System.out.print("\n|");

        for(int i = 0; i < numberOfProcess; i++){
            for(int j = 0; j < bt[i]-1; j++)
                System.out.print(" ");
                System.out.format("%2d",pid[i]);
            
                for(int j = 0; j < bt[i]-1; j++)
                    System.out.print(" ");
                    System.out.print("|");
        }
        System.out.print("\n ");

        for(int i = 0; i < numberOfProcess; i++){
            for(int j = 0; j < bt[i]; j++)
                System.out.print("--");
                System.out.print(" ");
        }
        System.out.print("\n");*/
        //---------------------------------------------------------------------------------------------------------------------------
        /*//calculating waiting & turn-around time for each process
        finishTime[0] = at[0] + bt[0];
        turnAroundTime[0] = finishTime[0] - at[0];
        waitingTime[0] = turnAroundTime[0] - bt[0];

        for (int i = 1; i < numberOfProcess; i++) 
        {
            finishTime[i] = bt[i] + finishTime[i - 1];
            turnAroundTime[i] = finishTime[i] - at[i];
            waitingTime[i] = turnAroundTime[i] - bt[i];
        }
        float sum = 0;
        for (int n : waitingTime) 
        {
            sum += n;
        }
        float averageWaitingTime = sum / numberOfProcess;

        sum = 0;
        for (int n : turnAroundTime) 
        {
            sum += n;
        }
        float averageTurnAroundTime = sum / numberOfProcess; 

        //print on console the order of processes along with their finish time & turn around time
        System.out.println("Priority Scheduling Algorithm : ");
        System.out.format("%20s%20s%20s%20s%20s%20s%20s\n", "ProcessId", "BurstTime", "ArrivalTime", "Priority", "FinishTime", "WaitingTime", "TurnAroundTime");
        for (int i = 0; i < numberOfProcess; i++) {
            System.out.format("%20d%20d%20d%20d%20d%20d%20d\n", pid[i], bt[i], at[i], prt[i], finishTime[i], waitingTime[i], turnAroundTime[i]);
        }

        System.out.format("%100s%20f%20f\n", "Average", averageWaitingTime, averageTurnAroundTime);*/
    }

    void ganttChart(){
        int finishTime = 0;
        int finish_time[] = new int[numberOfProcess];
        int max = priority[0];
        int min;
        int index = 0;
        boolean check = true;
        int a = 0;

        sortAccordingArrivalTimeAndPriority(arrivalTime, processId, burstTime, priority);
        finishTime = arrivalTime[0];
        sortAccordingArrivalTimeAndPriority(priority, processId, burstTime, arrivalTime);
        for(int i = 1; i < numberOfProcess; i++){
            if(max < priority[i]){
                max = priority[i];
            }
        }
        min = max + 1;
        System.out.println();
        System.out.println("Gantt Chart: ");
        for(int i = 0; i < numberOfProcess; i++){
            System.out.print("----------");
        }
        System.out.println();
        while(check){
            for(int i = 0; i < numberOfProcess; i++){
                if(finishTime >= arrivalTime[i] && min > priority[i] && processId[i] > 0){
                    index = i;
                    min = priority[i];
                }
            }
            if(processId[index] == -1){
                finishTime++;
                continue;
            }

            finishTime += burstTime[index];
            finish_time[a] = finishTime;
            a++;
            System.out.format("%5d%5s", processId[index], "  |");
            //System.out.println(finishTime);

            processId[index] = -1;
            min = max + 1;
            check = false;
            for(int k = 0; k < numberOfProcess; k++){
                if(processId[k] != -1){
                    check = true;
                }
            }
        }
        System.out.println();
        //System.out.println("Gantt Chart");
        for(int i = 0; i < numberOfProcess; i++){
            System.out.print("----------");
        }
        System.out.println();
        System.out.print("0");
        for (a = 0; a < numberOfProcess; a++){
            System.out.format("%10d",finish_time[a]);
        }
    }

    public static void main(String[] args) 
    {
        Scanner input = new Scanner(System.in);
        nonPreemptivePriority obj = new nonPreemptivePriority();
        obj.getProcessData(input);
        obj.priorityNonPreemptiveAlgorithm();
        obj.ganttChart();

    }
}