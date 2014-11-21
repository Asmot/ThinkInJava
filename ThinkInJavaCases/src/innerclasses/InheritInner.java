package innerclasses;

class WithInner {
	class Inner {}
}

public class InheritInner extends WithInner.Inner {
	
//	No enclosing instance of type WithInner is available due to some intermediate constructor invocation
//	public InheritInner() {
//	}
	
	public InheritInner(WithInner wi, int i)
	{
		wi.super();//不能够没有
	}
	
	public InheritInner(WithInner wi) {
		wi.super();//不能没有
	}
	
	public static void main(String[] args) {
		WithInner wi = new WithInner();
		InheritInner inner = new InheritInner(wi);
	}
}
