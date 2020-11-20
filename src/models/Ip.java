package models;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Ip {
  private static Pattern VALID_IPV4_PATTERN = null;
  private static Pattern VALID_IPV6_PATTERN = null;
  private static final String ipv4Pattern = "(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])";
  private static final String ipv6Pattern = "([0-9a-f]{1,4}:){7}([0-9a-f]){1,4}";
  private static final Logger LOGGER = Logger.getLogger(Ip.class.getName());
  static {
    try {
      VALID_IPV4_PATTERN = Pattern.compile(ipv4Pattern, Pattern.CASE_INSENSITIVE);
      VALID_IPV6_PATTERN = Pattern.compile(ipv6Pattern, Pattern.CASE_INSENSITIVE);
    } catch (PatternSyntaxException e) {
      LOGGER.severe("Could not provide pattern in Iparsing");
    }
  }
  
  
  public static boolean isIpAddress(String ipAddress) {
    Matcher m1 = VALID_IPV4_PATTERN.matcher(ipAddress);
    if (m1.matches()) {
    	LOGGER.info("IPv4 address detected");
      return true;
    }
    if(VALID_IPV6_PATTERN.matcher(ipAddress).matches()){
    	LOGGER.info("IPv6 address detected");
    	return true;
    }
    else {    
    	LOGGER.info("Not IPv4 or Ipv6 address");
    	return false;
    }
  }

}
