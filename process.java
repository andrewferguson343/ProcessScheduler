
public class process {
	public int processArrivalTime;
	public int processEndTime;
	public int processServiceTime;
	public int processStartTime;
	public int processTurnaroundTime;
	public int processTotalWaitTime;
	

	public process(int arrivalTime, int serviceTime)	{
		 processArrivalTime = arrivalTime;			
		 processServiceTime = serviceTime;
		 processStartTime = -1;
		 processEndTime = -1;
		 processTurnaroundTime = 0;
		 processTotalWaitTime = 0;
	}
}
