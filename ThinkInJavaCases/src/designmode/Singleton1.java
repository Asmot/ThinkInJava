package designmode;
//����ʽ������.�����ʼ��ʱ���Ѿ�����ʵ���� 
public class Singleton1 {
  
	
    private static final Singleton1 single = new Singleton1();
    
    //��̬�������� 
    public static Singleton1 getInstance() {
        return single;
    }
}