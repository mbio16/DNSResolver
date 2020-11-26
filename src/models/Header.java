package models;
public class Header {
	public UInt16 id;
	
	
	public Header() {
		//new UInt16();
		id = new UInt16().generateRandom();
		System.out.println(id.getAsHex());
		
		 UInt16 id2 = id.loadFromBytes(id.getAsBytes());
		System.out.println(id2.getValue());
	}
	public static void main(String[] args) {
		Header a = new Header();
	}
}
