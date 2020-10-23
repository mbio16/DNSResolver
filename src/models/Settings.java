package models;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.filechooser.FileSystemView;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Settings {

	private String lastDNSServerUsed;
	public static final String SETTINGS_FILE_NAME = "settings.json";
	public static final String SETTINGS_FOLDER_NAME="DNSResolver";
	public static final String DNS_LAST_USED="DNS_LAST_USED";
	private String filePath;
	
	public Settings() {
		checkIfFileExistsOrCreate();
		readValues();
		System.out.println(lastDNSServerUsed);
	}
	
	private void checkIfFileExistsOrCreate()  {
		String userDocumentsFolder = FileSystemView.getFileSystemView().getDefaultDirectory().getPath().toString();
		
		
		Path folderPath = Paths.get(userDocumentsFolder,SETTINGS_FOLDER_NAME);
		Path filePath = Paths.get(folderPath.toString(),SETTINGS_FILE_NAME);
		
		File folder = new File(folderPath.toString());
		File file = new File(filePath.toString());
		
		if(!folder.exists()) {
			folder.mkdirs();
		}
		if(!file.exists()) {
			try {
			file.createNewFile();
			setupJsonFile(file);
			}
			catch (Exception e) {
				System.out.println("Could not write settings file");
			}
		}
		this.filePath = file.getPath().toString();
		
	}
	
	private void setupJsonFile(File file) throws IOException {
		JSONObject json = new JSONObject();
		json.put(DNS_LAST_USED,"ahoj");
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.write(json.toJSONString());
		fileWriter.close();
	}
	
	private void readValues() {
		JSONParser jsonParser = new JSONParser();
		try {
			JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader(filePath));
			lastDNSServerUsed = (String) jsonObject.get(DNS_LAST_USED);
		} catch (Exception e) {
			System.out.println("file could not be parse");
		}
	}
	public static void main(String[] args) {
		Settings s = new Settings();
	}
}
