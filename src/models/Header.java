package models;

import enums.Rcode;

public class Header {
	private UInt16 		id;
	private byte[] 		opCode;
	private boolean 	aa;
	private boolean 	tc;
	private boolean 	rd;
	private boolean 	ra;
	private boolean 	dnssec;
	private boolean 	ad;
	private boolean 	nonathethicate;
	private byte		rCode;
	private UInt16		QdCount;
	private UInt16		AnCount;
	private UInt16		NsCount;
	private UInt16		ArCount;

	
	
	public Header() {
	}
}
