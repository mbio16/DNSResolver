package enums;

public enum OpCode {
    QUERY((byte) 0x00),
    RESPONSE((byte) 0x01),
	SERVER_STATUS((byte) 0x02);
	public byte code;
	
    private OpCode(Byte code) {
        this.code = code;
    }
}

