package models;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.swing.filechooser.FileSystemView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Settings {

	private String lastDNSServerUsed;
	public static final String SETTINGS_FILE_NAME = "settings.json";
	public static final String SETTINGS_FOLDER_NAME="DNSClient";
	public static final String DNS_LAST_USED="DNS_LAST_USED";
	private String filePath;
	private File file;
	private static final Logger LOGGER = Logger.getLogger(Settings.class.getName());
	public Settings() {
		lastDNSServerUsed = "<ip>";
		checkIfFileExistsOrCreate();
		readValues();
		//System.out.println(lastDNSServerUsed);
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
		Map<String,String> jsonMap = new HashMap<>();
		jsonMap.put(DNS_LAST_USED,lastDNSServerUsed);
		JSONObject json = new JSONObject(jsonMap);
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(json.toString());
		fileWriter.close();
	}
	

	
	private void readValues() {
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(filePath));
			lastDNSServerUsed = (String) jsonObject.get(DNS_LAST_USED);
			
		} catch (Exception e) {
			LOGGER.severe("Could not parse settings from file: \n" + e.toString());
		}
	}
	
	public void setLastDNSServerUsed(String newIp) {
		lastDNSServerUsed = newIp;
	}
	
	public String getLastDNSServerUsed() {
		return lastDNSServerUsed;
	}
	
	public void appIsClossing() {
		file.delete();
		checkIfFileExistsOrCreate();
		LOGGER.info("Setting written in file");
	}
}
