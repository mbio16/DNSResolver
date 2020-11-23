package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import models.Ip;

class IpTest {
	
	
	@Test void isIpv4_1() {
		assertEquals(true,Ip.isIPv4Address("1.2.3.4"));
	}
	
	
	@Test void isIpv4_2()
	{
		assertEquals(false,Ip.isIPv4Address("1.2.3.290"));
	}
	
	@Test void isIpv4_3() {
		assertEquals(false, Ip.isIPv4Address("1.b.2.3"));
	}
	
	@Test void isIpv6_1() {
		assertEquals(true,Ip.isIpv6Address("aaaa:000d::0001"));
	}
	
	
	@Test void isIpv6_2()
	{
		assertEquals(false,Ip.isIpv6Address("aaaa:000d::000p"));
	}
	
	@Test void isIpv6_3() {
		assertEquals(false, Ip.isIPv4Address("aaaa.000d::000p"));
	}
	
	@Test void reversedPTRIpv4() {
		try {
			assertEquals("4.3.2.1.in-addr.arpa",Ip.getIpReversed("1.2.3.4"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test void reversedPRTIpv6() {
		try {
			assertEquals("1.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.0.d.0.0.0.a.a.a.a.ip6.arpa",Ip.getIpReversed("aaaa:000d::0001"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
