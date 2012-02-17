package com.horsefire.util;

/**
 * This converter always outputs lowercase hex strings, but can handle mixed or
 * uppercase
 */
public class NumberConverter {

	public String toHexString(byte[] bytes) {
		char[] hexArray = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		char[] hexChars = new char[bytes.length * 2];
		int v;
		for (int j = 0; j < bytes.length; j++) {
			v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v / 16];
			hexChars[j * 2 + 1] = hexArray[v % 16];
		}
		return new String(hexChars);
	}

	public String toHexString(long value) {
		return Long.toHexString(value);
	}

	public byte[] toByteArray(String hexString) {
		int len = hexString.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			int upper = Character.digit(hexString.charAt(i), 16);
			int lower = Character.digit(hexString.charAt(i + 1), 16);
			if (upper == -1 || lower == -1) {
				throw new NumberFormatException(hexString
						+ " is not a hex string");
			}
			data[i / 2] = (byte) ((upper << 4) + lower);
		}
		return data;
	}
}
