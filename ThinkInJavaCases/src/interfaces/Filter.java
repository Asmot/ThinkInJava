package interfaces;

public class Filter{
	public String name() {
		return getClass().getSimpleName();
	}
	Object process(Object input) { return input; }
}

class FilterAdapter implements Processor {
	Filter filter;//����
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
	public String process(Object input) {//Эͬ��������
		return "LowPass: " + ((String)input).toLowerCase();//ǿת������ת�ͣ�ΪStringȻ����תΪСд
	}
}
