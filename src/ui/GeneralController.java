package ui;

import java.util.logging.Logger;

import models.Language;
import models.Settings;

public class GeneralController {
	
	protected Language language;
	
	protected Settings settings;
	
	protected Logger LOGGER;
	public Settings getSettings() {
		return settings;
	}



	public void setSettings(Settings settings) {
		this.settings = settings;
	}



	public Language getLanguage() {
		return language;
	}



	public void setLanguage(Language language) {
		this.language = language;
		//System.out.println("Language load to another window");
	}

	public void setLabels() {
		//To be overrited
	}
	
	public void loadDataFromSettings() {
		//to be overrited
	}
}
