
public class schedule {
	//Andrew Ferguson Operating Systems
	
	// cpu clock simulates cpu clock time
	static int cpuClock = 0;

	public static void main(String[] args) {
		int processesComplete = 0;
		
		//create array of processes
		process[] processes = new process[5];		
		processes[0] = new process(0, 75);
		processes[1] = new process(40, 10);
		processes[2] = new process(25, 10);
		processes[3] = new process(20, 80);
		processes[4] = new process(45, 85);
		
		// While there are still incomplete processes, run
		while(processesComplete < processes.length - 1)
		{
			// for each process in the array, run
			for(int i = 0; i < processes.length; i++)
			{				
				// if the process is incomplete and the process has arrived, run roundRobin on the process
				if(processes[i].processServiceTime > 0 && cpuClock >= processes[i].processArrivalTime)
				{
					processes[i] = RoundRobin(processes[i]);
					//If the round completed the process, add 1 to completed processes
					if(processes[i].processEndTime > 0)
					{						
						processesComplete++;
					}
				}				
			}
		}
		//Print out final information
		for(int i = 0; i < processes.length; i++)
		{
			System.out.println("Process " + (i + 1) + ":");
			System.out.println("  Start: " + processes[i].processStartTime);
			System.out.println("  End: " + processes[i].processEndTime);
			System.out.println("  Intitial wait: " + CalculateInitialWaitTime(processes[i]));
			System.out.println("  Total wait: " + (processes[i].processTotalWaitTime + CalculateInitialWaitTime(processes[i])));
			System.out.println("  Turnaround: " + calculateTurnaroundTime(processes[i]));			
		}
		
		System.out.println("Average Turnaround: " + CalculateAverageTurnaround(processes));
		System.out.println("Average Wait: " + CalculateAverageWaitTime(processes));
	}
	
	public static process RoundRobin(process currentProcess)
	{		
		process newProcess = currentProcess;		
		//if process does not have a start time, set it to the cpu clock
		// for every tick increase cpu clock by 1, decrease process service time
		// if process ends before time quantum, break and start a new process 
		for (int i = 0; i < 15; i++)
		{			
			if(newProcess.processStartTime == -1)
			{
				newProcess.processStartTime = cpuClock;
			}			
			newProcess.processServiceTime--;
			cpuClock++;			
			if(newProcess.processServiceTime <= 0)
			{
				newProcess.processEndTime = cpuClock;				
				break;
			}
			
		}
		return newProcess;	
	}
	
	
	//calculate the average turnaround
	public static double CalculateAverageTurnaround(process[] processes )
	{
		double totalTurnaroundTime = 0;
		for(int i = 0; i < processes.length; i++)
		{
			totalTurnaroundTime = totalTurnaroundTime + calculateTurnaroundTime(processes[i]);
		}
		
		return totalTurnaroundTime / processes.length;
	}
	
	//calculate average wait
	public static double CalculateAverageWaitTime(process[] processes)
	{
		double totalWaitTime = 0;
		for(int i = 0; i < processes.length; i++)
		{
			totalWaitTime = totalWaitTime + CalculateInitialWaitTime(processes[i]) ;
		}
		return totalWaitTime / processes.length;
	}
	
	//calculate turnaround
	public static int calculateTurnaroundTime(process finishedProcess)
	{
		return  finishedProcess.processEndTime - finishedProcess.processArrivalTime ;
	}
	
	//calculate initial wait
	public static int CalculateInitialWaitTime(process finishedProcess)
	{
		return finishedProcess.processStartTime - finishedProcess.processArrivalTime;
	}
}

