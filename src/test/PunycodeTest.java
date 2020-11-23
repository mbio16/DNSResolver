package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.Punycode;

class PunycodeTest {

	@Test
	void testToPunycode() {
		assertEquals("xn--hkyrky-ptac70bc.cz",Punycode.toPunycode("h·ËkyË·rky.cz"));
	}
	
	@Test
	void testFromPunnycode() {
		assertEquals("h·ËkyË·rky.cz",Punycode.fromPunnycode("xn--hkyrky-ptac70bc.cz"));
	}

}
