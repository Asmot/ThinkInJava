package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class CircularSet {
	private int[] array;
	private int len;
	private int index = 0;
	public CircularSet(int size) {
		array = new int[size];
		len = size;
		for(int i = 0; i < size; i++) {
			array[i] = -1;
		}
	}
	public synchronized void add(int i) {
		array[index] = i;
		index = ++index % len;//保证不越界
	}
	public synchronized boolean contains(int val) {
		for(int i = 0; i < len; i++) {
			if(array[i] == val) {
				return true;
			}
		}
		return false;
	}
}

public class SerialNumberChecker {
	private static final int SIZE = 10;
	private static CircularSet circularSet = new CircularSet(1000);
	private static ExecutorService exec = Executors.newCachedThreadPool();
	
	static class SerialChecker implements Runnable {

		@Override
		public void run() {
			while(true) {
				int serial = SerialNumberGenerator.next();
				if(circularSet.contains(serial)) {
					System.out.println("出现了重复: " + serial);
					System.exit(0);
				}
				circularSet.add(serial);
			}
		}
		
	}
	public static void main(String[] args) {
		for(int i = 0; i < SIZE; i++) {
			exec.execute(new SerialChecker());
		}
	}

}
