package io;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;

public class MappedIO {

	//������д���ļ��Ĳ������������ֱ���int���ֺ��ַ��ļ��Ĵ�С
	private static int numOfInts = 400000;
	private static int numOnUbuffInts = 20000;
	
	private abstract static class Tester {
		private String name;
		public Tester(String name) {
			this.name = name;
		}
		
		public abstract void test() throws IOException;
		
		public void runTest() {
			System.out.print(name + ":");
			
			try {
				//��¼��ʼʱ��
				long start = System.nanoTime();
				//��ʼ����
				test();
				//����ʱ��
				double duration = System.nanoTime() - start;
				System.out.format("%.2f\n", duration/1.0e9);//��ȷ��С�������λ
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private static Tester[] tests = {
		new Tester("Stream Write") {
			@Override
			public void test() throws IOException {
				//ͨ�������ļ���д������
				DataOutputStream dos = new DataOutputStream(
						new BufferedOutputStream(
								new FileOutputStream(new File("temp.tmp"))));
				for(int i = 0; i < numOfInts; i++) {
					dos.writeInt(i);
				}
				dos.close();
			}
		},
		new Tester("Mapped Write") {
			@Override
			public void test() throws IOException {
				//��ȡ�ļ�ͨ��
				FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel();
				//ͨ��map����IntBuffer,ָ��ӳ���ļ��ĳ�ʼλ�ú�ӳ������ĳ���
				IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
				for(int i = 0; i < numOfInts; i++) {
					ib.put(i);
				}
				fc.close();
			}
		},
		new Tester("Stream Read") {
			@Override
			public void test() throws IOException {
				DataInputStream dis = new DataInputStream(
						new BufferedInputStream(
								new FileInputStream(new File("temp.tmp"))));
				for(int i = 0; i < numOfInts; i++) {
					dis.readInt();
				}
				dis.close();
			}
		},
		new Tester("Mapped Read") {
			@Override
			public void test() throws IOException {
				FileChannel fc = new FileInputStream("temp.tmp").getChannel();
				IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
				while(ib.hasRemaining()) {
					ib.get();
				}
				fc.close();
			}
		},
		new Tester("Stream Read/Write") {
			@Override
			public void test() throws IOException {
				/*
				 * Ч��Ϊ��д��һ��ֵ��Ȼ��ͣ�ظ���д��ǰһ��ֵ
				 */
				RandomAccessFile accessFile = new RandomAccessFile(new File("temp.tmp"), "rw");
				//��д��һ��1
				accessFile.writeInt(1);
				for(int i = 0; i < numOnUbuffInts; i++) {
					//int�Ĵ�СΪ4���ֽ�
					//�ȶ�λ����һ��int����ʼλ��
					accessFile.seek(accessFile.length() - 4);
					//��ȡ��һ���ֽڣ�Ȼ�����ı�ĩβд���ֵ�����Ƶ�Ч��
					accessFile.writeInt(accessFile.readInt());
				}
				accessFile.close();
			}
		},
		new Tester("Mapped Read/Write") {
			@Override
			public void test() throws IOException {
				/*
				 * Ч��Ϊ��д��һ��ֵ��Ȼ��ͣ�ظ���д��ǰһ��ֵ
				 */
				FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel();
				IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
				//��д��һ����
				ib.put(0);
				//i=1
				for(int i = 1; i < numOnUbuffInts; i++) {
					//ȡ��һ����ֵ���ڵ�ǰλ��
					//����i���Ǵ�1��ʼ
					ib.put(ib.get(i - 1));
				}
				fc.close();
			}
		}
	};
	
	public static void main(String[] args) {
		for(Tester test : tests) {
			test.runTest();
		}
	}

}
