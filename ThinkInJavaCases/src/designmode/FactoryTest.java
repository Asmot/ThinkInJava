package designmode;

//抽象产品角色
interface Moveable {
	void run();
}

// 具体产品角色
class Plane implements Moveable {
	@Override
	public void run() {
		System.out.println("plane....");
	}
}

class Broom implements Moveable {
	@Override
	public void run() {
		System.out.println("broom.....");
	}
}

//还可以有更多的角色
//这样在新增角色时就会很方面

//也可使用匿名内部类
interface Factory {
	public Moveable create();
}
class Car implements Moveable {
	public void run() {
		System.out.println("Car.....");
	}
	public static Factory factory = new Factory() {
		
		@Override
		public Moveable create() {
			// TODO Auto-generated method stub
			return new Car();
		}
	};
}
//使用匿名内部类的话 下面的抽象工厂以及具体工厂就不需要了

// 抽象工厂
abstract class VehicleFactory {
	abstract Moveable create();
}

// 具体工厂
class PlaneFactory extends VehicleFactory {
	public Moveable create() {
		return new Plane();
	}
}

class BroomFactory extends VehicleFactory {
	public Moveable create() {
		return new Broom();
	}
}
//more class extends VehicleFactory




public class FactoryTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VehicleFactory factory = new BroomFactory();
		//这也体现了多太的性质
		Moveable m = factory.create();
		m.run();
	}

}
