package enumerated;

public enum EnumFunction {
	//��������Ӳ��������൱�����ڵ��� EnumFunction(String desc)���캯��
	WEST("this is west"),
	NORTH("this is north"),
	EAST("this is east"),
	SOUTH("this is souch");//���Ҫ���巽��������ֺŲ��ܶ�
	
	//��Щ�����������enumʵ��֮��
	private String description;
	
	//�������publicûʲô������Ϊֻ����enum�ڲ�ʹ��
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
