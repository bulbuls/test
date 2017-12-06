package com.design.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Test {
	public static void main(String[] args) {
		String file = "F://360Downloads//开户帐号.txt";
		try {
			InputStream in = new FileInputStream(file);
			OutputStream out = new FileOutputStream("F://360Downloads//1.txt");
			int i = 0;
			byte[] b = new byte[10];
			while((i = in.read(b))!=-1){
				 System.out.println(new String(b,0,i));  
				out.write(b);
				
			}
			out.flush();
			out.close();
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
