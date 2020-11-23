package models;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;


public class Language {

	public static final String CZECH = "cz";
	public static final String ENGLISH = "en";
	public static final String INTERNATIONAL_BUNDLE_PATH = "models.Lang";
	private ResourceBundle languageBundle;
	private Locale locale;
	private String currentLanguage;
	
	
	private static final Logger LOGGER = Logger.getLogger(Language.class.getName());
	public Language() {
		currentLanguage = Language.CZECH;
	}
	
	public void changeLanguageBundle(boolean isCzechSelected) {
	currentLanguage = isCzechSelected ? Language.CZECH:Language.ENGLISH;
	locale = new Locale(currentLanguage);
	languageBundle = ResourceBundle.getBundle(Language.INTERNATIONAL_BUNDLE_PATH,locale);
	LOGGER.info("Changing language: " + currentLanguage);
	}
	
	public ResourceBundle getLanguageBundle() {
		return languageBundle;
	}
	
	public String getCurrentLanguage() {
		return currentLanguage;
	}
}
