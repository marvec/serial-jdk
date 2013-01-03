package org.jboss.qa.drools.serial;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class SerialMain {

	public static class Schovanka implements Serializable {
		protected Map<String, Object> c = null;
	}
	
	public void test() throws Exception {
		Schovanka s = new Schovanka();
		s.c = new HashMap<String, Object>();
				
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(s);
		oos.close();
		byte[] s1buf = baos.toByteArray();
		
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(s1buf));
		Object o = ois.readObject();
		System.out.println(o.getClass().getName());		

		baos = new ByteArrayOutputStream();
		oos = new ObjectOutputStream(baos);
		oos.writeObject(o);
		oos.close();
		byte[] s2buf = baos.toByteArray();
		
		for (int i = 0; i < s1buf.length; i++) {
			if (s1buf[i] != s2buf[i]) {
				System.out.printf("%d vs %d\n", (int) s1buf[i], (int) s2buf[i]);
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		new SerialMain().test();
	}

}
