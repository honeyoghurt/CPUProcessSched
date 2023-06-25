import java.util.*;
public class shortestJobFirst{
    void SJF(){
        Scanner input = new Scanner(System.in);
        System.out.println("--------------------------------------------");
        System.out.println("|     Non-Preemptive Shortest Job First    |");
        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println("How many processes?: ");
        int n = input.nextInt();
        Process processes[] = new Process[n];
        for(int i=0; i<n; i++){
            int j = i+1;
            System.out.println("Input the following for Process " + j);
            System.out.print("Arrival Time: ");
            int at = input.nextInt();
            System.out.print("Burst Time: ");
            int bt = input.nextInt();
            processes[i] = new Process(i, bt, at);
            System.out.println();
        }
        
        
        Process temp[] = new Process[n];
        float avgwt=0, avgta=0;
        for(int j=0; j<n; j++){
        int st=0, tot=0; //system time, number of completed processes
        
        while(true){
            int c=n, min=999;
            //for(int j=0; j<n; j++){
            if (tot == n) // if total no of processes = completed processes loop will be terminated
                break;
            for (int i=0; i<n; i++){
                /*
                * If i'th process arrival time <= system time and its flag=0 and burst<min
                * That process will be executed first
                */
                if ((processes[i].arrivalTime <= st) && (processes[i].flag == false) && (processes[i].burstTime < min)){
                    min = processes[i].burstTime;
                    c=i;
                    temp[j]=processes[i];
                }
            }
            /* If c==n means c value can not updated because there's no process arrival time< system time so we increase the system time */
            if (c==n)
                st++;
            else{
                processes[c].completeTime = st + processes[c].burstTime;
                st += processes[c].burstTime;
                processes[c].turnAroundTime = processes[c].completeTime - processes[c].arrivalTime;
                processes[c].waitingTime = processes[c].turnAroundTime - processes[c].burstTime;
                processes[c].flag = true;
                tot++;
                j++;
            }
        }
    }
        System.out.println("p  arrival  burst  completed turn waiting");
        for(int i=0;i<n;i++){
            int j = processes[i].processID + 1;
            avgwt+= processes[i].waitingTime;
            avgta+= processes[i].turnAroundTime;
            System.out.println(j+"\t"+processes[i].arrivalTime+"\t"+processes[i].burstTime+"\t"+processes[i].completeTime+"\t"+processes[i].turnAroundTime+"\t"+processes[i].waitingTime);
        }
        
        System.out.println("\nAverage Turnaround Time is "+ (float)(avgta/n));
        System.out.println("Average Waiting Time is "+ (float)(avgwt/n));
        System.out.println();
        for (int i=0; i<n; i++){
            processes[i]=temp[i];
        }
        System.out.print("Gantt Chart: ");
        //sortProcess2(processes, n);
        //for(int i=0;i<n;i++){
        //    System.out.print(processes[i].processID + " ");
        //}
        printGanttChart(processes, n);
        input.close();
        
    }
    
    public static void printGanttChart (Process p[], int n){
        int i, j;
        //Arrays.sort(p);
        int last = p[n-1].completeTime;
        if (n==1) { last += 0;}
        //else{ last += p[n-1].waitingTime;}
        //printing top bar
        System.out.println("");
        for(i=0; i<n; i++){
            for(j=0; j<p[i].burstTime; j++){
                System.out.print("--");
            }
            //System.out.print("--");
        }
        System.out.print("-");
        System.out.println("");
        //middle position
        System.out.print("|");
        for (i=0; i<n; i++){
            int k = p[i].processID + 1;
            for (j=0; j<p[i].burstTime-1; j++){ System.out.print(" ");}
            System.out.print(k);
            for (j=0; j<p[i].burstTime-1; j++){ System.out.print(" ");}
            System.out.print("|");
        }
        System.out.println(" ");
        // bottom bar
        for(i=0; i<n; i++){
            for(j=0; j<p[i].burstTime; j++){System.out.print("--");}
            //System.out.print(" ");
        }
        System.out.print("-");
        System.out.println(" ");

        //printing waiting time
        int minus = 0;
        for(i=0; i<n; i++) {
            if(p[i].waitingTime>9){ System.out.print(" ");};
            System.out.print(p[i].completeTime-p[i].burstTime);
            if(i+1<n){
                if(p[i+1].waitingTime>9){ minus = 2;}
            }
            if(i+1 == n){ if (last>9) minus = 1;}
            for(j=0; j<p[i].burstTime-minus; j++){ System.out.print("  ");}

        }
        if(last>9){ System.out.print(" ");}
        System.out.println(last);
    }
}


class Process implements Comparable<Process> {
    int processID = 0;
    int burstTime, arrivalTime, completeTime;
    int turnAroundTime, waitingTime;
    boolean flag = false;

    public Process(int pid, int bt, int at){
        processID = pid;
        burstTime = bt;
        arrivalTime = at;
    }

    @Override
    public String toString(){
        return  getClass().getSimpleName() + ": burst time= " + burstTime + ", arrival time= " + arrivalTime;
    }

    @Override
    public int compareTo(Process p){
        if(this.burstTime > p.burstTime) {return 1;}
        else if(this.burstTime == p.burstTime){return 0;}
        else {return -1;}
    }
}