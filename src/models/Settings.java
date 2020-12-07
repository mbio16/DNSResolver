package models;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import javax.swing.filechooser.FileSystemView;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class Settings {

	public static final String SETTINGS_FILE_NAME = "settings.json";
	public static final String SETTINGS_FOLDER_NAME="DNSClient";
	public static final String DNS_SERVERS="DNS_SERVERS";
	public static final String DOMAIN_NAMES_mDNS = "DOMAIN_NAMES_mDNS";
	public static final String DOMAIN_NAMES_DNS="DOMAIN_NAMES_DNS";
	private String filePath;
	private File file;
	private ArrayList<String> dnsServers;
	private ArrayList<String> domainNamesDNS;
	private ArrayList<String> domainNamesMDNS;
 	private static final Logger LOGGER = Logger.getLogger(Settings.class.getName());
	public Settings() {
		dnsServers = new ArrayList<String>();
		domainNamesDNS = new ArrayList<String>();
		domainNamesMDNS = new ArrayList<String>();
		checkIfFileExistsOrCreate();
		readValues();
		
		//System.out.println(dnsServers.toString());
		//System.out.println(domainNamesDNS.toString());
		//System.out.println(domainNamesMDNS.toString());
	}
	
	private void checkIfFileExistsOrCreate()  {
		String userDocumentsFolder = FileSystemView.getFileSystemView().getDefaultDirectory().getPath().toString();
		
		
		Path folderPath = Paths.get(userDocumentsFolder,SETTINGS_FOLDER_NAME);
		Path filePath = Paths.get(folderPath.toString(),SETTINGS_FILE_NAME);
		
		File folder = new File(folderPath.toString());
		file = new File(filePath.toString());
		
		if(!folder.exists()) {
			folder.mkdirs();
		}
		if(!file.exists()) {
			try {
			file.createNewFile();
			setupJsonFile();
			}
			catch (Exception e) {
				LOGGER.severe("Could not write to file: \n" + e.toString());
			}
		}
		this.filePath = file.getPath().toString();
	}
	
	private void setupJsonFile() throws IOException {
		Map<String,ArrayList<String>> jsonMap = new HashMap<>();
		jsonMap.put(DNS_SERVERS,dnsServers);
		jsonMap.put(DOMAIN_NAMES_DNS, domainNamesDNS);
		jsonMap.put(DOMAIN_NAMES_mDNS, domainNamesMDNS);
		JSONObject json = new JSONObject(jsonMap);
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(json.toString());
		fileWriter.close();
		jsonMap.clear();
	}
	

	
	private void readValues() {
		JSONParser jsonParser = new JSONParser();
		try {
			FileReader reader = new FileReader(filePath);
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);
			dnsServers = readJsonArraylist(DNS_SERVERS, jsonObject);
			domainNamesMDNS = readJsonArraylist(DOMAIN_NAMES_mDNS, jsonObject);
			domainNamesDNS = readJsonArraylist(DOMAIN_NAMES_DNS, jsonObject);
			reader.close();
			jsonObject = null;
			jsonParser = null;
		}
		catch (Exception e) {
			LOGGER.severe("Could not parse settings from file: \n" + e.toString());
			}
	}
	
	private ArrayList<String> readJsonArraylist(String key, JSONObject jsonObject){
		JSONArray jsonArray =  (JSONArray) jsonObject.get(key);
		ArrayList<String> list= new ArrayList<String>();
		for(int i=0;i<jsonArray.size();i++) {
			list.add((String) jsonArray.get(i));
		}
		return list;
	}
	public void addDNSServer(String ip) {
		
		if(Ip.isIpValid(ip)) {
			if (!dnsServers.contains(ip)) {
				dnsServers.add(ip);
			}
			else {
				LOGGER.info("IP address of DNS server already in list");
			}
		}
		else {
			LOGGER.info("IP address of DNS server is not valid");
		}
	}
	
	public void addMDNSDomain(String domain) {
		if(DomainConvert.isValidDomainName(domain)) {
			if (!domainNamesMDNS.contains(domain)) {
				domainNamesMDNS.add(domain);
				LOGGER.info("mDNS domain name added");
			}else {
				LOGGER.info("mDNS domain already in list");
			}			
		}
		else {
			LOGGER.warning("mDNs domain name is not valid");
		}
	}
	
	public void addDNSDomain(String domain) {
		if(DomainConvert.isValidDomainName(domain)) {
			if(!domainNamesDNS.contains(domain)) {
				domainNamesDNS.add(domain);
				LOGGER.info("DNS domain name added");
			}
			else {
				LOGGER.info("DNS domain already in list");
			}
		}
		else {
			LOGGER.warning("DNS domain name is not valid");
		}
	}
	public void appIsClossing() {
		file.delete();
		checkIfFileExistsOrCreate();
		LOGGER.info("Setting written in file");
	}
	
	public ArrayList<String> getDnsServers() {
		return dnsServers;
	}

	public ArrayList<String> getDomainNamesDNS() {
		return domainNamesDNS;
	}
	
	
	public ArrayList<String> getDomainNamesMDNS() {
		return domainNamesMDNS;
	}
}
