package ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Settings;

public class MainController extends GeneralController {
	// FXML Components
	// language radio buttons
	@FXML private RadioButton englishLangRadioButton;
	@FXML private RadioButton czechLangRadioButton;
	// button to chose protocol 
	@FXML private Button dnsButton;	
	@FXML private Button llmrButton;
	@FXML private Button mdnsButton;
	@FXML private Button dohButton;
	@FXML private Button dotButton;
	//labels for protocol group
	@FXML private Label	basicDNSLabel;
	@FXML private Label multicastDNSLabel;
	@FXML private Label encryptedDNSLabel;
	//help image
	@FXML private Label dnsButtonHelp;
	@FXML private ImageView llmrButtonHelp;
	@FXML private ImageView mdnsButtonHelp;
	@FXML private ImageView dohButtonHelp;
	@FXML private ImageView dotButtonHelp;

	
 	private ToggleGroup languagegroup;
 	
	public static final String FXML_FILE_NAME="Main.fxml";
	public void initialize() {
		
		//
		LOGGER = Logger.getLogger(DNSController.class.getName());
		
		//setup toogle group
		languagegroup= new ToggleGroup();
		czechLangRadioButton.setToggleGroup(languagegroup);
		englishLangRadioButton.setToggleGroup(languagegroup);	
	}
	
	@FXML private void languageChanged(ActionEvent event) {
		language.changeLanguageBundle(czechLangRadioButton.isSelected());
		setLabels();
	}
	
	@FXML private void definedButtonFired(ActionEvent event) {
		String fxml_file = "";
		if(event.getSource().equals(dnsButton)) {
			fxml_file = DNSController.FXML_FILE_NAME; 
		}
		if(event.getSource().equals(mdnsButton)) {
			fxml_file = MDNSController.FXML_FILE_NAME;
		}
		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml_file));
				Stage newStage = new Stage();
				newStage.setScene(new Scene(loader.load()));
				GeneralController controller = (GeneralController) loader.getController();
				controller.setLanguage(language);
				controller.setSettings(settings);
				newStage.show();
				Stage mainStage = (Stage) dnsButton.getScene().getWindow();
				mainStage.close();
				controller.setLabels();
				controller.loadDataFromSettings();
 		}		
		catch (Exception e) {
			LOGGER.severe("Could not open new window:" + e.toString());
			Alert alert = new Alert(AlertType.ERROR,language.getLanguageBundle().getString("windowError"));
			alert.showAndWait();
		}
	}
	
	
	@FXML private void buttonFired(ActionEvent event) {
		LOGGER.warning("Calling a module which is not implemented");
		Alert alert = new Alert(AlertType.ERROR,language.getLanguageBundle().getString("notImplemented"));
		alert.showAndWait();
		
	}
	
	public void setSettings(Settings settings) {
		this.settings = settings;
	}
	
	public void setLabels() {
		basicDNSLabel.setText(language.getLanguageBundle().getString(basicDNSLabel.getId()));
		multicastDNSLabel.setText(language.getLanguageBundle().getString(multicastDNSLabel.getId()));
		encryptedDNSLabel.setText(language.getLanguageBundle().getString(encryptedDNSLabel.getId()));
	}
}
