package studio7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import studio7.util.ViewInputStream;
import studio7.util.ViewOutputStream;

public class StudioMain {
	ViewOutputStream vos;
	ViewInputStream vis;
	
	public StudioMain(){
		FileInputStream fis;
		try {
			fis = new FileInputStream(new File ("input.txt"));
			vis = new ViewInputStream(fis);
			FileOutputStream fos = new FileOutputStream(new File ("output.txt"));
			vos = new ViewOutputStream(fos);
		} catch (FileNotFoundException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void run(){
		while(vis.getInt()!= -1){
			int a = vis.read();
			if(a!= -1){vos.write(a);}
		}
	}
	
	
	public static void main(String[] args) {
		StudioMain main = new StudioMain();
		main.run();
	}
}
