package ui;



import application.Language;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

public class MainController {
	// FXML Components
	@FXML
	private RadioButton englishLangRadioButton;
	
	@FXML
	private RadioButton czechLangRadioButton;
	
	@FXML
	private Button dnsButton;
	
	@FXML
	private Button llmrButton;
	
	@FXML
	private Button mdnsButton;
	
	@FXML 
	private Button dohButton;
	
	@FXML
	private Button dotButton;
	
	@FXML 
	private Label	basicDNSLabel;
	
	@FXML
	private Label multicastDNSLabel;
	
	@FXML
	private Label encryptedDNSLabel;
	
	//Usage components
	private ToggleGroup languagegroup;
	
	private Language language;
	

	
	public void initialize() {
		language = new Language();
		language.changeLanguageBundle(true);
		languagegroup= new ToggleGroup();
		czechLangRadioButton.setToggleGroup(languagegroup);
		englishLangRadioButton.setToggleGroup(languagegroup);
	}



	@FXML
	private void languageChanged(ActionEvent event) {
		language.changeLanguageBundle(czechLangRadioButton.isSelected());
		basicDNSLabel.setText(language.getLanguageBundle().getString(basicDNSLabel.getId()));
		multicastDNSLabel.setText(language.getLanguageBundle().getString(multicastDNSLabel.getId()));
		encryptedDNSLabel.setText(language.getLanguageBundle().getString(encryptedDNSLabel.getId()));
	}
	
	@FXML
	private void buttonFirred(ActionEvent event) {
		Alert alert = new Alert(AlertType.ERROR,language.getLanguageBundle().getString("notImplemented"));
		alert.showAndWait();
	}
	
}
