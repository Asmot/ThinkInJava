package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AtomicityTest implements Runnable{

	private int i = 0;
	private boolean flag = true;
	
	//private synchronized int getValue() is ok
	private int getValue() {
		return i;
	}
	//用synchronized修饰的方法
	private synchronized void increase() {
		i++;
		i++;
	}
	
	public void exit() {
		flag = false;
	}
	
	@Override
	public void run() {
		while(flag) {
			increase();
		}
	}
	
	public static void main(String[] args) {
		ExecutorService exec = Executors.newCachedThreadPool();
		AtomicityTest test = new AtomicityTest();
		exec.execute(test);
		while(true) {
			int val = test.getValue();
			if(val % 2 != 0) {
				System.out.println(val);
				test.exit();
				break;
			}
		}
		exec.shutdown();
	}

}
