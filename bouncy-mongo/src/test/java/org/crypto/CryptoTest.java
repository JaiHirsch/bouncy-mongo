package org.crypto;

import static org.junit.Assert.*;

import org.junit.Test;

public class CryptoTest {

	@Test
	public void hex() {
		System.out.println(Long.toHexString(3489025346735389727l));
	}
	
	@Test
	public void test() {
		String plain = "Zaphod's just zis guy, ya knöw?";
		String encrypted = Crypto.encrypt(plain,
				"000102030405060708090A0B0C0D0E0F");
		String decrypted = Crypto.decrypt(encrypted,
				"000102030405060708090A0B0C0D0E0F");
		if (decrypted != null && decrypted.equals(plain)) {
			assertEquals(plain, decrypted);
		} else {
			fail("Utter and complete failure.");
		}
	}

}
