package test;


import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import models.DomainConvert;

class Domain {

	@Test
	void asciiDomain() {
		assertEquals("4test6domain2cz0",DomainConvert.encodeDNS("test.domain.cz"));
	}
	
	@Test
	void utfDomain() {
		assertEquals("19xn--hkyrky-ptac70bc2cz0",DomainConvert.encodeDNS("h·ËkyË·rky.cz"));
	}

	@Test
	void asciDomainDecode() {
		assertEquals("test.domain.cz",DomainConvert.decodeDNS("4test6domain2cz0"));
	}
	@Test
	void utfDomainDecode() {
		assertEquals("xn--hkyrky-ptac70bc.cz",DomainConvert.decodeDNS("19xn--hkyrky-ptac70bc2cz0"));
	}
}
