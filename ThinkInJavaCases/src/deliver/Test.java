package deliver;

public class Test {

	/**
	 * �������� ���������� ���Ǵ���ֵ
	 * @param a
	 */
	public static void change(int a) {
		a++;
	}
	
	public static void main(String[] args) {
		
//		int a = 0;
//		change(a);
//		System.out.println(a);
		
		Number num = new Number(10);
		System.out.println(num);
		//���ﴫ�ݽ�ȥ����num���ù��ĵ�ַ��ֵ
		changeObj(num);
		System.out.println(num);
		
	}
	
	/**
	 * ����Ҳ�Ǵ���ֵ
	 * @param o //ͨ�����ݹ��������õĵ�ֵַ �õ����Ǹ����� 
	 */
	public static void changeObj(Number o) {
		o.add();
		//��������ĵľ������洫�ݽ����Ķ���
		//��û���½�����
	}

}

class Number {
	private int n = 0;
	public Number(int n) {
		this.n = n;
	}
	
	public void add() {
		n++;
	}
	
	public String toString() {
		return "Num:" + n;
	}
}
