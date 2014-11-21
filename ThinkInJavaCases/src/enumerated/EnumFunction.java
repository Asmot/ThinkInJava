package enumerated;

public enum EnumFunction {
	//括号里面加参数，就相当于是在调用 EnumFunction(String desc)构造函数
	WEST("this is west"),
	NORTH("this is north"),
	EAST("this is east"),
	SOUTH("this is souch");//如果要定义方法，这个分号不能丢
	
	//这些方法必须放在enum实例之后
	private String description;
	
	//这里和用public没什么区别，因为只能在enum内部使用
	private EnumFunction(String desc) {
		description = desc;
	}
	
	public String getDesc() {
		return description;
	}
	
	public static void main(String[] args) {
		for(EnumFunction e : EnumFunction.values()) {
			System.out.println(e + ": " + e.getDesc());
		}
	}
}
