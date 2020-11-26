package models;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.logging.Logger;

public class DomainConvert {
	
	
	private static Logger LOGGER = Logger.getLogger(DomainConvert.class.getName());
	
	public static String encodeDNS(String domain) {
		if (!Charset.forName("US-ASCII").newEncoder().canEncode(domain)) {
			domain = Punycode.toPunycode(domain);
		}
		String result = "";
		String splited [] = domain.split("\\.");
		for (String string : splited) {
			result += string.length() + string;
		}	
		result += "0";
		LOGGER.info("Domain name encode: " + result);
		return result;
	}
	
	public static String decodeDNS(String encodedDomain) {
		int passed = 0;
		String result = "";
		try {
			byte [] byteDomain = encodedDomain.getBytes("US-ASCII");
			int i = 67;
			while (i !=0 ) {
			char first = (char) byteDomain[passed];
			if(Integer.parseInt(Character.toString(first))==0) {
				break;
			}
			char second = (char) byteDomain[passed+1];
			if(Character.isDigit(second)) {
				int size =Integer.parseInt(Character.toString(first) + Character.toString(second));
				result += encodedDomain.substring(passed+2, passed+size+2) + ".";
				passed += size+2;
			}
			else {
				int size =Integer.parseInt(Character.toString(first));
				result += encodedDomain.substring(passed+1, passed+size+1) + ".";
				passed += size+1;
				}
			i--;
			}
 		} catch (UnsupportedEncodingException e) {
			LOGGER.severe("Cannot convert domain:  "+ encodedDomain +" " + e.toString());
		}
		result = result.substring(0,result.length()-1);
		LOGGER.info(result);
		return result;
	}
}
