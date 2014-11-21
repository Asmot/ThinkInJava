package innerclasses;

interface Game { 
	boolean move(); 
}
interface GameFactory {
	Game getGame(); 
}


class Checkers implements Game {
	private Checkers() {}//构造方法用的丝private修饰，外部不可访问
	private int moves = 0;
	private static final int MOVES = 3;

	public boolean move() {
		System.out.println("Checkers move " + moves);
		return ++moves != MOVES;
	}
	public static GameFactory factory = new GameFactory() {
		@Override
		public Game getGame() {
			//返回一个Checkers对象
			//因为是内部类所有有权限访问private的构造函数
			return new Checkers();
		}
	};
}

class Chess implements Game {
	private Chess() {}//also private
	private int moves = 0;
	private static final int MOVES = 4;
	public boolean move() {
		System.out.println("Chess move " + moves);
		return ++moves != MOVES;
	}
	//使用匿名内部内
	public static GameFactory factory = new GameFactory() {
		public Game getGame() {
			return new Chess();
		}
	};
	
}

public class Games {
	public static void playGame(GameFactory factory) {
		Game game = factory.getGame();
		while(game.move())
		{
			//
		}
	}

	public static void main(String[] args) {
		playGame(Checkers.factory);
		playGame(Chess.factory);
	}
}
