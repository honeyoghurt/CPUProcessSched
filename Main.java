import java.util.Scanner;

public class Main {

	public static void main (String args[]) {
		
		System.out.println(" ------------------------------");
		System.out.println(" | PROCESS SCHEDULING PROGRAM |");
		System.out.print(" ------------------------------\n");
		System.out.println("\n Process scheduling algorithms available : \n");
		System.out.println(" 1. Preemptive Shortest Job First (Pre-SJF)");
		System.out.println(" 2. Non Preemptive Shortest Job First (Non Pre-SJF)");
		System.out.println(" 3. Non Preemptive Priority (Non Pre-Priority)");
		System.out.println(" 4. Close the program\n");
		System.out.print(" Please enter the number of your chosen scheduling algorithm : ");
		Scanner input1 = new Scanner(System.in);
		int choice = input1.nextInt();
		System.out.println("\n");
		System.out.flush();
		
		switch(choice)
        {
            case 1:
            {
    	        preemptiveSJF obj = new preemptiveSJF();
    	        obj.pre_SJF();
            }
                break;
            case 2:
            {
				shortestJobFirst process = new shortestJobFirst();
				process.SJF();
            }
                break;
            case 3:
            	{
            		Scanner input = new Scanner(System.in);
                    nonPreemptivePriority obj = new nonPreemptivePriority();
                    obj.getProcessData(input);
                    obj.priorityNonPreemptiveAlgorithm();
                    obj.ganttChart();
            	}
                break;
            case 4:
                System.exit(0);
            default:
                System.out.println(" Please enter a valid choice !!! ");
        }
		
	}
}
