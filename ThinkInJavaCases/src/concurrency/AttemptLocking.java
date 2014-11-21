package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AttemptLocking {
	//��ʾ��ʹ��Lock��Ҫʹ���������
	private Lock lock = new ReentrantLock();

	//û�г�ʱ���Ƶ�trylock
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
	//ʹ�ó�ʱ���Ƶ�trylock
	public void timed() {
		boolean captured = false;
		try {
			captured = lock.tryLock(2, TimeUnit.SECONDS);//��λΪ�� ֵ��2
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
				//ȡ����֮��ͣ1�����ͷ���
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
		//���߳�2�г����ʱ��ȡ����
		Thread.yield();
		Thread.sleep(1000);
		
		//�ٴγ���ȡ��
		locking.untimed();
		locking.timed();
	}
}
