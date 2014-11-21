package enumerated;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

enum Explore { HERE, THERE}

public class Reflection {
 
	public static Set<String> analyze(Class<?> enumClass) {
		System.out.println("Interfaces:");
		for(Type t : enumClass.getGenericInterfaces())
			System.out.println(t);
		
		System.out.println("Base:" + enumClass.getSimpleName());
		
		System.out.println("Methods:");
		Set<String> methods = new TreeSet<String>();
		for(Method m : enumClass.getMethods())
			methods.add(m.getName());
		System.out.println(methods);
		
		return methods;
	}
	
	public static void main(String[] args) throws IOException {
		Set<String> exploreMetheds = analyze(Explore.class);
		Set<String> enumMethods = analyze(Enum.class);
		
		System.out.println("Explore.containsAll(Enum)? : " + exploreMetheds.containsAll(enumMethods));
		
		System.out.println("Explore.removeAll(Enum)");
		exploreMetheds.removeAll(enumMethods);
		System.out.println(exploreMetheds);

		/*
javap Explore.class
Compiled from "Reflection.java"
final class enumerated.Explore extends java.lang.Enum<enumerated.Explore> {
  public static final enumerated.Explore HERE;
  public static final enumerated.Explore THERE;
  static {};
  public static enumerated.Explore[] values();
  public static enumerated.Explore valueOf(java.lang.String);
}
		*/
			
	}
		
	

}
