package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
	//显示的使用Lock需要使用这个子类
	private Lock lock = new ReentrantLock();

	//没有超时机制的trylock
	public void untimed() {
		boolean captured = lock.tryLock();
		try {
			System.out.println("tryLock: " + captured);
		} finally {
			if(captured) {
				lock.unlock();
				System.out.println("unlock");
			}
		}
	}
	//使用超时机制的trylock
	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);//单位为秒 值是2
			System.out.println("tryLock(2, TimeUnit.SECONDS): " + captured);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(captured) {
				lock.unlock();
				System.out.println("unlock");
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		final AttemptLocking locking = new AttemptLocking();
		locking.untimed();
		locking.timed();
		
		System.out.println("====================");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				//取得锁之后停1秒再释放锁
				locking.lock.lock();
				System.out.println("Thread 2 hold lock");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				locking.lock.unlock();
			}
		}).start();
		//让线程2有充足的时间取得锁
		Thread.yield();
		Thread.sleep(1000);
		
		//再次尝试取锁
		locking.untimed();
		locking.timed();
	}
}
