import java.util.Scanner;

public class preemptiveSJF {
	
	void pre_SJF () {
	
		System.out.println("  +-----------------------------------------+");
		System.out.println("  | Preemptive Shortest Job First (Pre-SJF) |");
		System.out.print("  +-----------------------------------------+");
		System.out.println (" \n  The number of processes this program can handle range from 3 to 10 processes\n");
		System.out.print("  Please enter the relevant number of processes : ");
		Scanner input = new Scanner(System.in);
		int number_Of_Processes = input.nextInt();

		int process_Id[] = new int[number_Of_Processes]; // process_Id is an array that takes process number of processes
		int arrival_Time[] = new int[number_Of_Processes]; // arrival_Time is an array to store arrival times of each process
		int burst_Time[] = new int[number_Of_Processes]; // burst_Time is array to store burst times of each process
		int finish_Time[] = new int[number_Of_Processes]; // finish_Time is an array to store finish times of each process
		int turnaround_Time[] = new int[number_Of_Processes];// array used for displaying turn around time of each process
		int waiting_Time[] = new int[number_Of_Processes];  // array used for displaying waiting time of each process
		int flag[] = new int[number_Of_Processes];  // acts as a flag to check if process is completed
		int b[]= new int[number_Of_Processes];   // another array to store burst time of processes

		int i; 
		int st = 0; //refers to system time
		int total = 0; // refers to total number of processes
		
		float average_TAT = 0; //refers to average turn around time
		float average_WT = 0; //refers to average waiting time
 
		for (i = 0 ; i < number_Of_Processes ; i++) {
    	
			process_Id[i] = i;
			System.out.println("  ---------------------------------------------------\n");
			System.out.print(" Enter the arrival time of process " + (i+1) + " : ");
			arrival_Time[i] = input.nextInt();
			System.out.print("\n");
			System.out.print(" Enter the burst time of process " + (i+1) + " : ");
			burst_Time[i] = input.nextInt();
			b[i] = burst_Time[i];
			flag[i] = 0;
     
		}
		
		System.out.println("\n\t+----------------+--------------+------------+ ");
		System.out.println("\t| Process Number | Arrival Time | Burst Time | ");
		System.out.println("\t+----------------+--------------+------------+ ");
		for(i = 0 ; i < number_Of_Processes ; i++) {
			System.out.println("\t|        " + (process_Id[i] + 1) +  "       |       " + arrival_Time[i] + "      |      " + b[i] + "     | ");
			System.out.println("\t+----------------+--------------+------------+ ");
		}
    
		while(true) {
    	
			int min = 99;
			int c = number_Of_Processes;
			
			if (total == number_Of_Processes)
				break;
    
			for ( i = 0 ; i < number_Of_Processes ; i++) {
     
				if ((arrival_Time[i] <= st) && (flag[i] == 0) && (burst_Time[i] < min)) {
    	 
					min = burst_Time[i];
					c = i;
				}
			}
    
			if (c == number_Of_Processes)
				st++;
			
			else {
				burst_Time[c]--;
				st++;
				
				if (burst_Time[c] == 0) {
					finish_Time[c] = st;
					flag[c] = 1;
					total++;
				}
			}
		}
    
		for(i = 0; i < number_Of_Processes ; i++)
		{
			turnaround_Time[i] = finish_Time[i] - arrival_Time[i];
			waiting_Time[i] = turnaround_Time[i] - b[i];
			average_WT += waiting_Time[i];
			average_TAT += turnaround_Time[i];
		}
    
		System.out.println("\n\t+----------------+--------------+------------+-------------+-----------------+--------------+ ");
		System.out.println("\t| Process Number | Arrival Time | Burst Time | Finish Time | Turnaround Time | Waiting Time | ");
		System.out.println("\t+----------------+--------------+------------+-------------+-----------------+--------------+ ");
		
		for(i = 0 ; i < number_Of_Processes ; i++) {
			System.out.println("\t|        " + (process_Id[i] + 1) +  "       |       " + arrival_Time[i] + "      |      " + b[i] + "     |       " + finish_Time[i] + "     |         " + turnaround_Time[i] + "       |      " + waiting_Time[i] + "       |");
			System.out.println("\t+----------------+--------------+------------+-------------+-----------------+--------------+ ");
		}
    
		System.out.println("\n The average Turnaround time is " + (average_TAT / number_Of_Processes));
		System.out.println("\n The average Waiting time is " + (average_WT / number_Of_Processes) + "\n");
		input.close();
		
	//Display Gantt Chart
		System.out.println(" Gantt Chart : ");
		System.out.print("\n+");
		for ( i = 1 ; i < number_Of_Processes ; i++){
			if(arrival_Time[i] > finish_Time[i-1]){
				System.out.print("----+");
			}
			System.out.print("----+");
		}
		System.out.print("----+");
		System.out.print("\n| ");
		System.out.print("P" + (process_Id[0] + 1) + " | ");
		for ( i = 1 ; i < number_Of_Processes ; i++){
			if(arrival_Time[i] > finish_Time[i-1]){
				System.out.print("   | ");				
			}
			System.out.print("P" + (process_Id[i] + 1) + " | ");		
		}
				
		System.out.print("\n+");
		for ( i = 1 ; i < number_Of_Processes ; i++){
			if(arrival_Time[i] >= finish_Time[i-1]){
				System.out.print("----+");				
			}
			System.out.print("----+");
		}
	}
	 public static void main(String[] args) 
	    {
	        Scanner input = new Scanner(System.in);
	        preemptiveSJF obj = new preemptiveSJF();
	        obj.pre_SJF();

	    }
}