import java.util.*;

public class CompareSequentialWithParallelAssign{
	
	public static void main(String[] args){
		
		double[] phil = new double[9000000];
		StopWatch BigJilm = new StopWatch(); // using the stopwatch class from chapter 9 to compare the algorithms' runtime
		BigJilm.start();
		Random brad = new Random(4840L);
	    double bill;
		for (int i = 0; i < 9000000; i++){ // fill the lower half
			bill = brad.nextDouble();
			phil[i] = bill;
		}
		BigJilm.stop();		
		System.out.println("Sequential Initialization Time elapsed: " + BigJilm.getElapsedTime());
	   
        Runnable dave = new AssignValsLow(phil);
        Runnable sal = new AssignValsHigh(phil);
        Thread chuck = new Thread(dave);
        Thread bobuck = new Thread(sal);
        chuck.start();
        bobuck.start();
        try {
            chuck.join();
            bobuck.join();
        } catch (InterruptedException e){
            System.out.println("Whoops!");
        }
		BigJilm.stop();
		System.out.println("Parallel Initialization Time elapsed: " + BigJilm.getElapsedTime());
    }
}
