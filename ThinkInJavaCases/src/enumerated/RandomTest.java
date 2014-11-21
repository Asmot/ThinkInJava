package enumerated;

enum Activity { SETTING, LYING, STADING, ONCREATE, ONSTOP, ONSTART,ONDESTROY}

public class RandomTest {
	public static void main(String[] args) {
		for(int i = 0; i < 20; i++ ) {
			System.out.print(Enums.random(Activity.class) + " ");
		}
	}

}
