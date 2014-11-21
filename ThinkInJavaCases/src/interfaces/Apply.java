package interfaces;

import java.util.Arrays;

interface Processor {//�ӿ�ʵ��
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
	public String process(Object input) {//Эͬ��������
		return ((String)input).toUpperCase();//ǿת������ת�ͣ�ΪStringȻ����תΪСд
	}
}
class Downcase extends StringProfessor {
	public String process(Object input) {//Эͬ��������
		return ((String)input).toLowerCase();
	}
}
class Splitter extends StringProfessor {
	public String process(Object input) {//Эͬ��������
		//ǿת������ת�ͣ�ΪStringȻ�����ÿո���Ϊ���飬�ٰ�����תΪ�ַ�����ʽ
		return Arrays.toString(((String)input).split(" ")); 
	}
}

public class Apply {
	//������յĲ�����Processor
	public static void process(Processor p, Object s) {
		System.out.println("Using Processor " + p.name());
		System.out.println(p.process(s));
	}
	public static String s = "this is a test";
	public static void main(String[] args) {
		//���ݵĲ������ǵ�����
		process(new Upcase(), s);
		process(new Downcase(), s);
		process(new Splitter(), s);
		
		process(new FilterAdapter(new LowPass()), s);
	}
}
