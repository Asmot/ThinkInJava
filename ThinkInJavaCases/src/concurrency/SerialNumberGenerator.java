package concurrency;

public class SerialNumberGenerator {
//	private static volatile int serialNumber = 0;//�������Ҳ��һ����������������
	private static int serialNumber = 0;
	public static int next() {
		return serialNumber++;//����ȫ������
	}
}
