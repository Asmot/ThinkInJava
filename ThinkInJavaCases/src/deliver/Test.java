package deliver;

public class Test {

	/**
	 * 基本类型 不存在引用 就是传递值
	 * @param a
	 */
	public static void change(int a) {
		a++;
	}
	
	public static void main(String[] args) {
		
//		int a = 0;
//		change(a);
//		System.out.println(a);
		
		Number num = new Number(10);
		System.out.println(num);
		//这里传递进去的是num引用过的地址的值
		changeObj(num);
		System.out.println(num);
		
	}
	
	/**
	 * 对象也是传递值
	 * @param o //通过传递过来的引用的地址值 得到了那个引用 
	 */
	public static void changeObj(Number o) {
		o.add();
		//所以这里改的就是外面传递进来的对象
		//并没有新建对象
	}

}

class Number {
	private int n = 0;
	public Number(int n) {
		this.n = n;
	}
	
	public void add() {
		n++;
	}
	
	public String toString() {
		return "Num:" + n;
	}
}
