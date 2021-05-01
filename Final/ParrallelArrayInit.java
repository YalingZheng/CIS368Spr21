package Final;

import java.util.Random;
import java.util.concurrent.*;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParrallelArrayInit {
	public static void main(String[] args) {
		double[] list1 = new double[9000000];
		double[] list2 = new double[list1.length];
		long time = System.currentTimeMillis();
		parallelAssignValues(list1);
		System.out.println((System.currentTimeMillis() - time) + " msec - parallelAssignValues()");
		time = System.currentTimeMillis();
		sequentialAssignValues(list2);
		System.out.println((System.currentTimeMillis() - time) + " msec - sequentialAssignValues()");
	}

	public static void parallelAssignValues(double[] list) {
		RecursiveAction mainTask = new ParallelAssignValues(0, list.length, list);
		ForkJoinPool pool = new ForkJoinPool();
		pool.invoke(mainTask);
	}

	static class ParallelAssignValues extends RecursiveAction {
		private static final long serialVersionUID = 1L;
		private double[] list;
		private int start;
		private int THRESHOLD = 1000000;

		private int fin;

		public ParallelAssignValues(int start, int fin, double[] list) {
			this.list = list;
			this.start = start;
			this.fin = fin;
		}

		@Override
		protected void compute() {
			Random r = new Random();
			if (fin - start < THRESHOLD) {
				int middle = (start + fin) / 2;
				invokeAll(new ParallelAssignValues(start, middle, list), new ParallelAssignValues(middle, fin, list));
			} else {
				 for (int i=start; i<fin; i++){
			         list[i] = r.nextDouble();
			      }
			}
		}
	}

	public static void sequentialAssignValues(double[] list) {
		Random random = new Random();
		for (int i = 0; i < list.length; i++) {
			list[i] = random.nextDouble();
		}
	}
}
