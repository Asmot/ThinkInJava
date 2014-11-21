package typeinfo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class HiddenImplementation {
	
	public static void main(String[] args) {
		A a = HiddenC.makeA();
		a.f();
		//((C)a).g();//这里不行了，会找不到C
		
		//but
		try {
			callHiddenMethod(a, "g");
			callHiddenMethod(a, "f");
			callHiddenMethod(a, "u");
			callHiddenMethod(a, "v");
			callHiddenMethod(a, "w");
			
		} catch (NoSuchMethodException | SecurityException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	static void callHiddenMethod(Object o, String methodName) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
	{
		Method m = o.getClass().getDeclaredMethod(methodName);
		m.setAccessible(true);
		m.invoke(o);
	}

}
