import java.util.Random;

class AssignValsLow implements Runnable {
		public AssignValsLow(double[] list){
		    Random brad = new Random(4840L);
		    double bill;
			for (int i = 0; i < 4500000; i++){ // fill the lower half
				bill = brad.nextDouble();
				list[i] = bill;
			}
		}
		@Override 
	    public void run() {
	        ;
	    }
	}
