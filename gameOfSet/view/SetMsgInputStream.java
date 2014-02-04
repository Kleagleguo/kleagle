/** 
 * This class is designed for both server
 * and client to read messages 
 * @author 			Bingkun Guo
 * Lab Section:		C
 * Email:			guobingkun@wustl.edu
 * Lab:				Lab5: Set Multiuser Game 
 * File:			SetMsgInputStream.java
 */

package lab5.view;

import java.io.DataInputStream;
import java.io.IOException;

public class SetMsgInputStream {
	
	private DataInputStream dis;
	
	/**
	 * Constructor of SetMsgInputStream, which takes a DataInputStream
	 * @param dis
	 */
	public SetMsgInputStream(DataInputStream dis) {
		this.dis = dis;
	}
	
	/**
	 * Read a byte and return it
	 * @return
	 */
	public byte readByte() {
		try {
			return dis.readByte();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	/**
	 * Read a UTF string and return it
	 * @return
	 */
	public String readUTF() {
		try {
			return dis.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Read a short and return it
	 * @return
	 */
	public short readShort() {
		try {
			return dis.readShort();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
}
