package enumerated;

import static enumerated.Outcome.*;

public enum RoShamBo implements Competitor<RoShamBo>{
	PAPER(DRAW, LOSE, WIN),//����Լ��ǳ������Է���������������ʯͷ����Ӯ�ֱ��ǣ�ƽ�֣��䣬Ӯ��
	SCISSORS(WIN, DRAW, LOSE),
	ROCK(LOSE, WIN, DRAW);
	
	
	private Outcome vPaper, vScissors, vRock;
	//���캯�������������� ������ ����������ʯͷ
	RoShamBo(Outcome paper, Outcome scissors, Outcome rock) {
		this.vPaper = paper;
		this.vScissors = scissors;
		this.vRock = rock;
	}
	@Override
	public Outcome complete(RoShamBo comptitor) {
		switch (comptitor) {
		case PAPER:
			return vPaper;
		case SCISSORS:
			return vScissors;
		case ROCK:
			return vRock;
		default:
			return null;
		}
	}
	public static void main(String[] argv) {
		play(RoShamBo.class, 20);
	}
	
	//���²��ֿ��Ե���дΪ���߷���
	
	/**
	 * <T extends Competitor<T>>�涨����Ĳ���������Competitor<T>����
	 * ������Ƿ���ֵ ���������void�ģ�û�з���
	 */
	public static <T extends Competitor<T>> void match(T a, T b) {
		System.out.println(a + " vs " + b + ": " + a.complete(b));
	}
	/**
	 * �������ָ�����������������󣩽�����Ϸ
	 * ������Enums���߷���Enums.random
	 * <T extends Enum<T> & Competitor<T>> �涨����Ĳ�������ͬʱ��Enum<T>��Competitor<T>����
	 * @param rsbClass ���������Ķ���
	 * @param size ����
	 */
	public static <T extends Enum<T> & Competitor<T>> 
	void play(Class<T> rsbClass, int size) {
		for(int i = 0; i < size; i++) {
			match(Enums.random(rsbClass), Enums.random(rsbClass));
		}
	}
}
