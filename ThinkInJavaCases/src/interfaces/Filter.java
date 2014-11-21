package interfaces;

public class Filter{
	public String name() {
		return getClass().getSimpleName();
	}
	Object process(Object input) { return input; }
}

class FilterAdapter implements Processor {
	Filter filter;//代理
	public FilterAdapter(Filter filter) {
		this.filter = filter;
	}
	
	public String name() {
		return filter.name();
	}
	public Object process(Object input) {
		return filter.process(input);
	}
}
class LowPass extends Filter {
	public String process(Object input) {//协同返回类型
		return "LowPass: " + ((String)input).toLowerCase();//强转（向下转型）为String然后再转为小写
	}
}
