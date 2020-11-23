package models;
import java.util.logging.Logger;
import inet.ipaddr.IPAddress;
import inet.ipaddr.IPAddressString;

public class Ip {
	public static final Logger LOGGER = Logger.getLogger(Ip.class.getName());	
	
	
	public static boolean isIPv4Address(String stringAddress) {
	try {
	IPAddress ip = new IPAddressString(stringAddress).getAddress();
	return ip.isIPv4();
	}
	catch (Exception e) {
		return false;
	}
	}
	
	public static boolean isIpv6Address(String stringAddress) {
		try {
			IPAddress ip = new IPAddressString(stringAddress).getAddress();
			return ip.isIPv6();
		} catch (Exception e) {
			return false;
		}

	}
	
	public static boolean isIpValid(String stringAddress) {
		boolean a = Ip.isIPv4Address(stringAddress);
		boolean b = Ip.isIpv6Address(stringAddress);
		LOGGER.info("is IP :" + stringAddress +"valid-> " +((a||b)));
		return (a||b);
	}
	
	public static String getIpReversed(String stringAddress) throws Exception{
		if(Ip.isIpValid(stringAddress)) {
			return  new IPAddressString(stringAddress).getAddress().toReverseDNSLookupString();
		}
		else {
			throw new Exception("Not valid IP");
		}
	}
	
}