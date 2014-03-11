package nfc.reader.last;

import java.math.BigInteger;

public class NFC implements NFC_Interface{
	String tag_id;
	
	@Override
	public String get_NFC_Tag_ID() {
		// TODO Auto-generated method stub
		return tag_id;
	}

	@Override
	public void set_NFC_Tag_ID(String id) {
		// TODO Auto-generated method stub
		this.tag_id = id;
	}

	@Override
	public void set_NFC_Tag_ID(byte[] id) {
		// TODO Auto-generated method stub
		this.tag_id = bin2hex(id);
	}
	   // Parsing binary to string
    String bin2hex(byte[] data) {
    	    return String.format("%0" + (data.length * 2) + "X", new BigInteger(1,data));
    }
}
