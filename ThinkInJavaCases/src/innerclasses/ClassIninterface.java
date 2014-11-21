package innerclasses;

public interface ClassIninterface {
	void howdy();
	class Test implements ClassIninterface {
		public void howdy() {
			System.out.println("Howdy!");
		}
		public static void main(String[] args) { 
			new Test().howdy();
		}
	}
}
