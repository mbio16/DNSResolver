package ui;

import application.Language;
import models.Settings;

public class GeneralController {
	
	protected Language language;
	
	protected Settings settings;
	

	
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
}
