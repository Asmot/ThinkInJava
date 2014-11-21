package io;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;

public class MappedIO {

	//下面有写入文件的操作，这两个分别是int文字和字符文件的大小
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
				//记录开始时间
				long start = System.nanoTime();
				//开始测试
				test();
				//计算时长
				double duration = System.nanoTime() - start;
				System.out.format("%.2f\n", duration/1.0e9);//精确到小数点后两位
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
				//通过流向文件中写入数据
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
				//获取文件通道
				FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel();
				//通过map产生IntBuffer,指定映射文件的初始位置和映射区域的长度
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
				 * 效果为先写入一个值，然后不停地复制写入前一个值
				 */
				RandomAccessFile accessFile = new RandomAccessFile(new File("temp.tmp"), "rw");
				//先写入一个1
				accessFile.writeInt(1);
				for(int i = 0; i < numOnUbuffInts; i++) {
					//int的大小为4个字节
					//先定位到上一个int的起始位置
					accessFile.seek(accessFile.length() - 4);
					//读取上一个字节，然后再文本末尾写入该值，复制的效果
					accessFile.writeInt(accessFile.readInt());
				}
				accessFile.close();
			}
		},
		new Tester("Mapped Read/Write") {
			@Override
			public void test() throws IOException {
				/*
				 * 效果为先写入一个值，然后不停地复制写入前一个值
				 */
				FileChannel fc = new RandomAccessFile("temp.tmp", "rw").getChannel();
				IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
				//先写入一个零
				ib.put(0);
				//i=1
				for(int i = 1; i < numOnUbuffInts; i++) {
					//取上一个的值放在当前位置
					//所以i才是从1开始
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
