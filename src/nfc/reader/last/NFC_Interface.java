package nfc.reader.last;

public interface NFC_Interface {
	String get_NFC_Tag_ID();
	void set_NFC_Tag_ID(String id);
	void set_NFC_Tag_ID(byte[] id);
}

