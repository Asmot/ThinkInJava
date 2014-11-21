package concurrency;

import java.util.concurrent.TimeUnit;

class ADaemon implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("ADaemon Starting");
//			//�ڴ�ӡ���߳�������־֮���ٴ�ӡһ�¶���
//			for (int i = 0; i < 5; i++) {
//				System.out.println("ADaemon print " + i);
//				TimeUnit.SECONDS.sleep(1);
//			}
			TimeUnit.SECONDS.sleep(1);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			System.out.println("Always run?");
		}
	}
}

public class DaemonsDontRunFinally {

	public static void main(String[] args) throws InterruptedException {
		Thread thread = new Thread(new ADaemon());
		thread.setDaemon(true);//����Ϊ��̨�߳�
		thread.start();
		
		//��main����ͣ1��֮���ٽ���
		TimeUnit.SECONDS.sleep(1);
	}
}
