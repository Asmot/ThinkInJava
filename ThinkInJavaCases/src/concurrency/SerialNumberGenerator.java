package concurrency;

public class SerialNumberGenerator {
//	private static volatile int serialNumber = 0;//换成这个也是一样，不是它的问题
	private static int serialNumber = 0;
	public static int next() {
		return serialNumber++;//不安全。。。
	}
}
