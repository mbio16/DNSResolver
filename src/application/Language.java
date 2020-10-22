package application;

import java.util.Locale;
import java.util.ResourceBundle;

public class Language {

	public static final String CZECH = "cz";
	public static final String ENGLISH = "en";
	public static final String INTERNATIONAL_BUNDLE_PATH = "application.Lang";
	private ResourceBundle languageBundle;
	private Locale locale;
	
	
	public void changeLanguageBundle(boolean isCzechSelected) {
	String lang = isCzechSelected ? Language.CZECH:Language.ENGLISH;
	locale = new Locale(lang);
	languageBundle = ResourceBundle.getBundle(Language.INTERNATIONAL_BUNDLE_PATH,locale);
	}
	
	public ResourceBundle getLanguageBundle() {
		return languageBundle;
	}
}
