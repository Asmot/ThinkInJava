package strings;




public class InfiniteRecursion {
	
	//ÿ������û��ָ���̳е�ʱ�򣬶���Ĭ�ϼ̳�Object��
	//Object����һ��toString������������System.out.pritnln(ĳ����)�ſ���ֱ�Ӵ�ӡ����Ϊ�����Զ�����toString()����
	//������дһ������������ı�һЩ������
	public String toString() {
		return " InfiniteRecursion address: " + this + "\n";
	}
	
	public static void main(String[] args) {
//		System.out.println(new InfiniteRecursion());
		System.out.println(" test " + 1 + 2);
	}

}
