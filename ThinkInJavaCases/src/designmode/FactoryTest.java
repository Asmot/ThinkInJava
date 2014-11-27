package designmode;

//�����Ʒ��ɫ
interface Moveable {
	void run();
}

// �����Ʒ��ɫ
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

//�������и���Ľ�ɫ
//������������ɫʱ�ͻ�ܷ���

//Ҳ��ʹ�������ڲ���
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
//ʹ�������ڲ���Ļ� ����ĳ��󹤳��Լ����幤���Ͳ���Ҫ��

// ���󹤳�
abstract class VehicleFactory {
	abstract Moveable create();
}

// ���幤��
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
		//��Ҳ�����˶�̫������
		Moveable m = factory.create();
		m.run();
	}

}
