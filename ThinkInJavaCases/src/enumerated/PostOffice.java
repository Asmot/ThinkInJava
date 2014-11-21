package enumerated;

import java.util.Iterator;

class Mail {
	enum GeneralDelivery {YES, NO1, NO2, NO3, NO4, NO5}
	enum Scannability {UNSCANNABLE, YES1, YES2, YES3, YES4}
	enum Readability {ILLEGIBLE, YES1, YES2, YES3, YES4} 
	enum Address {INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6} 
	enum ReturnAddress {MISSING, OK1, OK2, OK3, OK4, OK5}
	
	GeneralDelivery generalDelivery;
	Scannability scannability;
	Readability readability;
	Address address;
	ReturnAddress returnAddress;
	
	static long counter = 0;
	long id = counter++;
	
	//��дtoString����
	public String toString() {
		return "Mail " + id;
	}
	
	//��ʾ��ϸ��ϸ
	public String details() {
		return toString() +
				", GeneralDelivery: " + generalDelivery +
				", Scannability: " + scannability +
				", Readability: " + readability +
				", Address: " + address +
				", ReturnAddress: " + returnAddress ;
				
	}
	
	//����һ�������Mail��������Ҳ���
	public static Mail randomMail() {
		Mail mail = new Mail();
		//����һ��Enums�е����ѡȡ����
		mail.generalDelivery = Enums.random(GeneralDelivery.class);
		mail.scannability = Enums.random(Scannability.class);
		mail.readability = Enums.random(Readability.class);
		mail.address = Enums.random(Address.class);
		mail.returnAddress = Enums.random(ReturnAddress.class);
		return mail;
	}
	
	/**
	 * ��������ƶ��ܹ���������
	 * @param count ����
	 * @return �Ե��������أ�����ʹ��for-each����
	 */
	public static Iterable<Mail> generator(final int count) {
		return new Iterable<Mail>() {
			int n = count;
			@Override
			public Iterator<Mail> iterator() {
				return new Iterator<Mail>() {

					@Override
					public boolean hasNext() {
						return n-- > 0;
					}

					@Override
					public Mail next() {
						return randomMail();
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
}

public class PostOffice {
	
	/*
	 * ���������ִ����ʼ��ķ�ʽ
	 * ÿһ�ֶ��᷵�ض�Ӧ��ֵtrue��false
	 * �������false��ʾ���ַ�ʽ������
	 */
	enum MailHandler {
		GENERAL_DELIVERY {
			@Override
			boolean handle(Mail m) {
				switch (m.generalDelivery) {
				case YES:
					System.out.println("Using general delivery for " + m);
					return true;
				default:
					break;
				}
				return false;
			}
			
		},
		MACHINE_SCAN {
			@Override
			boolean handle(Mail m) {
				switch (m.scannability) {
				case UNSCANNABLE:
					return false;
				default:
					switch (m.address) {
					case INCORRECT:
						return false;
					default:
						System.out.println("Delivering " + m + " atuomatically");
						return true;
					}
				}
			}
			
		},
		VISUAL_INSPECTION {
			@Override
			boolean handle(Mail m) {
				switch (m.readability) {
				case ILLEGIBLE:
					return false;
				default:
					switch (m.address) {
					case INCORRECT:
						return false;
					default:
						System.out.println("Delivering " + m + " normally");
						return true;
					}
				}
			}
			
		},
		RETURN_TO_SENOER {
			@Override
			boolean handle(Mail m) {
				switch (m.returnAddress) {
				case MISSING:
					return false;
				default:
					System.out.println("Returing " + m + " to sender");
					return true;
				}
			}
			
		};
		abstract boolean handle(Mail m);
	}

	/**
	 * ����MailHandler�еļ��ַ������δ�����ʼ���
	 * �����һ�ֿ��Դ����򷵻سɹ�
	 * ������еķ��������ܴ����ж����ʼ�Ϊ����
	 * @param m �ʼ�
	 */
	static void handle(Mail m) {
		for(MailHandler handler : MailHandler.values()) {
			if(handler.handle(m)) {
				return;
			}
		}
		System.out.println(m + " is a dead letter.");
	}
	
	public static void main(String[] args) {
		//�������10����
		for(Mail mail : Mail.generator(10)) {
			System.out.println(mail.details());
			handle(mail);
			System.out.println("===============================");
		}
	}

}
