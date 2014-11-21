package strings;




public class InfiniteRecursion {
	
	//每个类在没有指明继承的时候，都是默认继承Object的
	//Object中有一个toString方法，所以在System.out.pritnln(某引用)才可以直接打印，因为它会自动调用toString()方法
	//这里重写一下这个方法，改变一些输出结果
	public String toString() {
		return " InfiniteRecursion address: " + this + "\n";
	}
	
	public static void main(String[] args) {
//		System.out.println(new InfiniteRecursion());
		System.out.println(" test " + 1 + 2);
	}

}
