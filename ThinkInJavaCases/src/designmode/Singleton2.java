package designmode;
//����ʽ������.�ڵ�һ�ε��õ�ʱ��ʵ���� 
public class Singleton2 {

    //ע�⣬����û��final    
    private static Singleton2 single = null;
    //��̬�������� 
    public synchronized  static Singleton2 getInstance() {
         if (single == null) {  
             single = new Singleton2();
         }  
        return single;
    }
}