package models;

import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.util.Random;


public class UInt16{
  public static final int MAX_VALUE = 65535;
  public static final int MIN_VALUE = 0;
  
  private int value;

  public UInt16() {
	  
  }

  public UInt16(int value) {
	  if(value > MAX_VALUE || value<MIN_VALUE)
		  throw new BufferOverflowException();
    this.value = value;
  }

 public int getValue() {
	 return value;
 }
  public int compareTo(UInt16 other) {
    return(value - other.value);
  }

  public double doubleValue() {
    return value;
  }

  public boolean equals(Object o) {
    return (o instanceof UInt16) && (((UInt16) o).value == value);
  }

  public int hashCode() {
    return value;
  }

  public int intValue() {
    return value;
  }

  public String toString() {
    return "" + value;
  }
  
  public byte[] getAsBytes() {	  
	  return ByteBuffer.allocate(2).putShort((short) value).array();
  } 
  
  public String getAsHex() { 
	    String res = "";
	    for (byte b : getAsBytes()) {
	        res = String.format("%02x", b)+res;
	    }
	    return res;
  }
  public UInt16 loadFromBytes(byte [] bytes) {
	  byte [] a = new byte [] {(byte) 0x00,(byte) 0x00,bytes[1],bytes[0]};
	  ByteBuffer wrapper = java.nio.ByteBuffer.wrap(a);
	  return new UInt16(wrapper.getInt());
  } 
  public UInt16 generateRandom() {
	  Random random = new Random();
	  return new UInt16(random.nextInt(MAX_VALUE));
	  //System.out.println(value);
	   
  }
}

