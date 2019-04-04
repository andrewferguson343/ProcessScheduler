import java.util.*;
public class randomGenerator {
	public static void main(String []args){

        double[] arrivalTimes = new double[100];
        arrivalTimes[0] = 0;
        double[] interArrivalTimes = new double[100];
        Random rand = new Random(); 
        //Generate 100 interarrival times
        for(int i = 0; i < arrivalTimes.length; i++)
        {
            interArrivalTimes[i] = 4 + (8-4)* rand.nextDouble();
            //Inter-arr-time= 4 + (8-4)*rand()      

        }
        double totalInter = 0;
        for(int i = 1; i < interArrivalTimes.length; i++)
        {
            arrivalTimes[i] = arrivalTimes[i - 1] + interArrivalTimes[i];          
        }       

        for (int i = 0; i < arrivalTimes.length; i++)
        {
            System.out.println("Process " + i + ":\t inter-arrival:" + interArrivalTimes[i] + "\t Arrival:" + arrivalTimes[i]);

        }

     }

}
