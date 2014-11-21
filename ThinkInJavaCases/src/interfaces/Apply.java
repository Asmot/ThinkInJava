package interfaces;

import java.util.Arrays;

interface Processor {//接口实现
	public String name();
	Object process(Object input);
}
abstract class StringProfessor implements Processor {  
	public String name() {
		return getClass().getSimpleName();
	}
	public abstract Object process(Object input);
}

class Upcase extends StringProfessor {
	public String process(Object input) {//协同返回类型
		return ((String)input).toUpperCase();//强转（向下转型）为String然后再转为小写
	}
}
class Downcase extends StringProfessor {
	public String process(Object input) {//协同返回类型
		return ((String)input).toLowerCase();
	}
}
class Splitter extends StringProfessor {
	public String process(Object input) {//协同返回类型
		//强转（向下转型）为String然后再用空格拆分为数组，再把数组转为字符串形式
		return Arrays.toString(((String)input).split(" ")); 
	}
}

public class Apply {
	//这里接收的参数是Processor
	public static void process(Processor p, Object s) {
		System.out.println("Using Processor " + p.name());
		System.out.println(p.process(s));
	}
	public static String s = "this is a test";
	public static void main(String[] args) {
		//传递的参数都是导出类
		process(new Upcase(), s);
		process(new Downcase(), s);
		process(new Splitter(), s);
		
		process(new FilterAdapter(new LowPass()), s);
	}
}
