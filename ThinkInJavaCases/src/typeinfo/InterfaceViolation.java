package typeinfo;

class B implements A {
	public void f() {}
	private void g() {
		System.out.println("B.g()");
	}
}

public class InterfaceViolation {
	public static void main(String[] args) {
		A a = new B();
		//a.g();//����ͨ����..The method g() is undefined for the type A
//		if(a instanceof B)
//		{
//			((B)a).g();//ǿתһ�¾Ϳ��Ե�����
//		}
		
	}
}
