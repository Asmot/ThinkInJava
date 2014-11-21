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
		wi.super();//���ܹ�û��
	}
	
	public InheritInner(WithInner wi) {
		wi.super();//����û��
	}
	
	public static void main(String[] args) {
		WithInner wi = new WithInner();
		InheritInner inner = new InheritInner(wi);
	}
}
