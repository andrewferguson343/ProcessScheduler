import java.util.Random;

public class schedule {
	//Andrew Ferguson Operating Systems
	
	// cpu clock simulates cpu clock time
	static int cpuClock = 0;

	public static void main(String[] args) {
		int processesComplete = 0;
		
		//create array of processes
		process[] processes = new process[100];		
		int[] serviceTimes = GenerateServiceTime();
		int[] arrivalTimes = GenerateArrivalTime();
		for (int i = 0; i < processes.length; i++)
		{
			processes[i] = new process(arrivalTimes[i], serviceTimes[i]);
		}
		
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
				else {
					cpuClock++;
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
		for (int i = 0; i < 2; i++)
		{	
			cpuClock++;
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
	
	public static int[] GenerateArrivalTime() {  
        int[] arrivalTimes = new int[100];
        arrivalTimes[0] = 0;
        int[] interArrivalTimes = new int[100];
        Random rand = new Random(); 
        //Generate 100 interarrival times
        for(int i = 0; i < arrivalTimes.length; i++)
        {
            interArrivalTimes[i] = 4 +  rand.nextInt(4);               

        }
        double totalInter = 0;
        for(int i = 1; i < interArrivalTimes.length; i++)
        {
            arrivalTimes[i] = arrivalTimes[i - 1] + interArrivalTimes[i];          
        }       

       return arrivalTimes;

     }
	public static int[] GenerateServiceTime() {		   
        int[] serviceTimes = new int[100];        
        Random rand = new Random(); 
        //Generate 100 interarrival times
        for(int i = 0; i < serviceTimes.length; i++)
        {
            serviceTimes[i] = 2 + rand.nextInt(3);      
        }
        return serviceTimes;

     }	
}

