package enumerated;

import static enumerated.Outcome.*;

public enum RoShamBo implements Competitor<RoShamBo>{
	PAPER(DRAW, LOSE, WIN),//如果自己是出布，对方出（布、剪刀和石头）输赢分别是（平局，输，赢）
	SCISSORS(WIN, DRAW, LOSE),
	ROCK(LOSE, WIN, DRAW);
	
	
	private Outcome vPaper, vScissors, vRock;
	//构造函数接受三个参数 依次是 布、剪刀和石头
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
	
	//以下部分可以单独写为工具方法
	
	/**
	 * <T extends Competitor<T>>规定传入的参数必须是Competitor<T>类型
	 * 这个不是返回值 这个方法是void的，没有返回
	 */
	public static <T extends Competitor<T>> void match(T a, T b) {
		System.out.println(a + " vs " + b + ": " + a.complete(b));
	}
	/**
	 * 随机产生指定数量个场景（对象）进行游戏
	 * 调用了Enums工具方法Enums.random
	 * <T extends Enum<T> & Competitor<T>> 规定传入的参数必须同时是Enum<T>和Competitor<T>类型
	 * @param rsbClass 用来比赛的对象
	 * @param size 数量
	 */
	public static <T extends Enum<T> & Competitor<T>> 
	void play(Class<T> rsbClass, int size) {
		for(int i = 0; i < size; i++) {
			match(Enums.random(rsbClass), Enums.random(rsbClass));
		}
	}
}
