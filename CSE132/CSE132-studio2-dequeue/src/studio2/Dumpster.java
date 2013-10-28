package studio2;

//
// Include Java I/O libraries
//
import java.io.*;

//
// Follow the studio instructions to complete this class
//
public class Dumpster {
	
	public File fl;
	public FileInputStream fis;
	public DataInputStream dis;
	public int i = 1;
	boolean done = false;
	public Dumpster(String fname) {
		fl = new File(fname);
		try {
			fis = new FileInputStream(fl);
			dis = new DataInputStream(fis);
		} catch (FileNotFoundException e) {
			// FIXME Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Dumpster output:");
		
	}
	
	/**
	 * read the next byte from the DataInputStream
	 * and print it out as a byte value
	 */
	public void dumpNextByte() {
		try {
			//char c = (char) dis.readByte();
			byte bt = dis.readByte();
			//short st = dis.readShort();
			//String th = this.toHex(bt);
			//int bt = dis.readInt();
			System.out.println(i + ":");
			i++;
			System.out.println(bt);
			//System.out.println(th);
			//System.out.println(st);
			//System.out.println(c);
		}
		catch (EOFException e) {	
			this.done = true;
		}
		catch (IOException e) {
			e.printStackTrace();	
		}
	}
	
	public String toHex(int n)
	{
		  String h = "" ;
		  int r=0;
		  int q=0;
		  int nn=n ;
		  do
		  {
		    r=nn % 16 ;
		    nn= nn / 16 ;
		    switch (r)
		    {
		      case 10: h = "A" + h; break ;
		      case 11: h = "B" + h; break ;
		      case 12: h = "C" + h; break ;
		      case 13: h = "D" + h; break ;
		      case 14: h = "E" + h; break ;
		      case 15: h = "F" + h; break ;
		      default: h = r + h; break ;
		    }
		  }
		while (nn > 0) ;
		return h ;
	}
	
	/**
	 * Are we done with the file?
	 * @return true iff there is no more to be read
	 */
	public boolean isDone() {
		return this.done; // fix
	}
	
	public static void main(String[] args) {
		//
		// Run this on test.txt and test.bin
		//
		Dumpster r = new Dumpster("test.txt");
		while (!r.isDone()) {
			r.dumpNextByte();
		}
	}
}