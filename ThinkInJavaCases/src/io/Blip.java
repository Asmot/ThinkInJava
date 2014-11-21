package io;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class Blip implements Externalizable {
	private int i;
	private String s;
	
	public Blip() {
		System.out.println("this will be executed");
	}
	public Blip(int i, String s) {
		this.i = i;
		this.s = s;
	}
	public String toString() {
		return "String : " + s + ", int :" + i;
	}
	
	@Override
	public void readExternal(ObjectInput arg0) throws IOException,
			ClassNotFoundException {
		//这段不能丢
		s = (String) arg0.readObject();
		i = arg0.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput arg0) throws IOException {
		//这段不能丢
		arg0.writeObject(s);
		arg0.writeInt(i);
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		//这里使用的是 Blip(int i, String s)构造器
		Blip blip = new Blip(1, "Test");
		System.out.println(blip);
		
		System.out.println("Saving");
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("temp.out"));
		out.writeObject(blip);
		out.close();
		
		System.out.println("Recovering");
		ObjectInputStream input = new ObjectInputStream(new FileInputStream("temp.out"));
		blip = (Blip) input.readObject();
		System.out.println(blip);
		
		
	}

}
