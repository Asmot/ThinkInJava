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
		//a.g();//编译通不过..The method g() is undefined for the type A
//		if(a instanceof B)
//		{
//			((B)a).g();//强转一下就可以调用了
//		}
		
	}
}
