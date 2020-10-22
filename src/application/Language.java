package application;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

	public static final String CZECH = "cz";
	public static final String ENGLISH = "en";
	public static final String INTERNATIONAL_BUNDLE_PATH = "application.Lang";
	private ResourceBundle languageBundle;
	private Locale locale;
	private String currentLanguage;
	
	public Language() {
		currentLanguage = Language.CZECH;
	}
	
	public void changeLanguageBundle(boolean isCzechSelected) {
	currentLanguage = isCzechSelected ? Language.CZECH:Language.ENGLISH;
	locale = new Locale(currentLanguage);
	languageBundle = ResourceBundle.getBundle(Language.INTERNATIONAL_BUNDLE_PATH,locale);
	}
	
	public ResourceBundle getLanguageBundle() {
		return languageBundle;
	}
	
	public String getCurrentLanguage() {
		return currentLanguage;
	}
}
