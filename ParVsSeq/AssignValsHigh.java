import java.util.Random;

class AssignValsHigh implements Runnable {
		public AssignValsHigh(double[] list){
		    Random buddy = new Random(4048L);
		    double bill;
			for (int i = 4500000; i < 9000000; i++){ // fill the upper half
				bill = buddy.nextDouble();
				list[i] = bill;
			}
		}
		@Override 
	    public void run() {
	        ;
	    }
	}
