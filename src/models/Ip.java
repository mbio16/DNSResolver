package models;
import java.io.BufferedReader;
import java.io.InputStreamReader;
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
	
	
	public static String getPrimaryDNSIp() {
		try {
			 String [] lineParts = null;
			 ProcessBuilder builder = new ProcessBuilder("nslookup");
			        builder.redirectErrorStream(true);
			        Process p = builder.start();
			        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
			        String line;
			        while (true) {
			            line = r.readLine();
			            if (line == null) { break; }
			            if(line.contains("Address:")) {
			            lineParts = line.split(":");
			            break;
			            }
			        
			      }
			        String res = lineParts[1];
			       res =  res.replaceAll("\\s", ""); 
			       LOGGER.info("Found primary DNS ip addres");
			       return res;
		} catch (Exception e) {
			return "No primary address";
		}
	}
}