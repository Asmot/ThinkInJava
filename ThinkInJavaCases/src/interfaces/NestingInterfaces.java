package interfaces;

class A {
	interface B {
		void f();
	}
	public class Bimp implements B {
		public void f() {};
	}
	private class Bimp2 implements B {
		public void f() {};
	}
	
	public interface C {
		void f();
	}
	class Cimp implements C {
		public void f() {}
	}
	private class Cimp2 implements C {
		public void f() {}
	}
	
	private interface D {
		void f();
	}
	private class Dimp implements D {
		public void f() {}
	}
	public class Dimp2 implements D {
		public void f() {}
	}
	
	public D getD() { return new Dimp2(); }
	private D dRef;
	public void receiveD(D d) {
		dRef = d;
		dRef.f();
	}
}

interface E {
	interface G {
		void f();
	}
	//这里不加修饰也是public 但是不能是private的
	public interface H {
		void f();
	}
	void g();
}

public class NestingInterfaces {
	
	public class Bimp implements A.B {
		public void f() { System.out.println("Bimp A.B f()"); }
	}
	class Cimp implements A.C {
		public void f() {}
	}
	//因为A.D是private的所有不能向上面一样实现
	//class Cimp implements A.D
	class Eimp implements E {
		public void g() {}
	}
	class DGimp implements E.G {
		public void f() {}
	}
	class Eimp2 implements E {
		public void g() {}
		class EG implements E.G {
			public void f() {}
		}
	}
	
	public static void main(String[] args) {
		A a = new A();
		
		//A.Dimp2 ad = a.getD();//: cannot convert from A.D to A.Dimp2
		//A.Dimp2 dip2 = a.getD();//cannot convert from A.D to A.Dimp2
		//a.getD().f();//The type A.D is not visible
		A a2 = new A();
		a2.receiveD(a.getD());
	}
}
