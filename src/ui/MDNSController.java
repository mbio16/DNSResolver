package ui;

import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class MDNSController extends GeneralController {
	
	public static final String FXML_FILE_NAME="MDNS.fxml";
	
	
	//menu items
	@FXML protected Menu actionMenu;
	@FXML protected MenuItem backMenuItem;
	@FXML protected Menu languageMenu;
	@FXML protected RadioMenuItem czechRadioButton;
	@FXML protected RadioMenuItem englishRadioButton;
	
	//butons
	@FXML protected Button sendButton;
	
	//text fields
	@FXML protected TextField domainNameTextField;
	
	//radio buttons
	@FXML protected RadioButton ipv4RadioButton;
	@FXML protected RadioButton ipv6RadioButton;
	@FXML protected RadioButton dnssecYesRadioButton;
	@FXML protected RadioButton dnssecNoRadioButton;
	
	//checkboxes
	@FXML protected CheckBox aCheckBox;
	@FXML protected CheckBox aaaaCheckBox;
	@FXML protected CheckBox nsCheckBox;
	@FXML protected CheckBox mxCheckBox;
	@FXML protected CheckBox cnameCheckBox;
	@FXML protected CheckBox ptrCheckBox;
	
	//titledpane
	
	@FXML protected TitledPane domainNameTitledPane;
	@FXML protected TitledPane ipTitledPane;
	@FXML protected TitledPane dnssecTitledPane;
	@FXML protected TitledPane recordTypeTitledPane;
	@FXML protected TitledPane dnsServerTitledPane;
	@FXML protected TitledPane queryTitledPane;
	@FXML protected TitledPane responseTitledPane;
	
	
	//labels
	@FXML protected Label responseTimeLabel;
	@FXML protected Label responseTimeValueLabel;
	@FXML protected Label numberOfMessagesLabel;
	@FXML protected Label numberOfMessagesValueLabel;
	
	
	//toogleGroup
	protected ToggleGroup ipToggleGroup;
	protected ToggleGroup dnssecToggleGroup;
	
	public MDNSController() {
		super();
		LOGGER = Logger.getLogger(MDNSController.class.getName());
	}
	
	public void  initialize() {
		ipToggleGroup = new ToggleGroup();
		ipv4RadioButton.setToggleGroup(ipToggleGroup);
		ipv6RadioButton.setToggleGroup(ipToggleGroup);
		
		dnssecToggleGroup = new ToggleGroup();
		dnssecYesRadioButton.setToggleGroup(dnssecToggleGroup);
		dnssecNoRadioButton.setToggleGroup(dnssecToggleGroup);
		
	
	}
	
	public void setLabels() {	
		//define group to iterate over it
		TitledPane titlePaneArray [] = new TitledPane []{domainNameTitledPane,
				ipTitledPane,
				dnssecTitledPane,
				recordTypeTitledPane,
				responseTitledPane,
				queryTitledPane};
		
		//same for radio buttons
		RadioButton [] radioButtonArray = new RadioButton [] {
			dnssecYesRadioButton,
			dnssecNoRadioButton,
		};
		
		Label [] labelsArray = new Label [] {
				responseTimeLabel,
				numberOfMessagesLabel
		};
		
		//set labels to current language in menu
		backMenuItem.setText(language.getLanguageBundle().getString(backMenuItem.getId()));
		actionMenu.setText(language.getLanguageBundle().getString(actionMenu.getId()));
		languageMenu.setText(language.getLanguageBundle().getString(languageMenu.getId()));
			
		for (TitledPane titledPane : titlePaneArray) {
			titledPane.setText(language.getLanguageBundle().getString(titledPane.getId()));
		}
		
		for (RadioButton radioButton : radioButtonArray) {
			radioButton.setText(language.getLanguageBundle().getString(radioButton.getId()));
		}
		
		for (Label label : labelsArray) {
			label.setText(language.getLanguageBundle().getString(label.getId()));
		}
		
		
		//set sendButton
		sendButton.setText(language.getLanguageBundle().getString(sendButton.getId()));
	}
	@FXML public void czechSelected(ActionEvent event) {
		language.changeLanguageBundle(true);
		setLabels();
	}
	@FXML public void englishSelected(ActionEvent event) {
		language.changeLanguageBundle(false);
		setLabels();
	}
	
	@FXML public void backButtonFirred(ActionEvent event) {
		try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource(MainController.FXML_FILE_NAME));
				Stage newStage = new Stage();
				newStage.setScene(new Scene(loader.load()));
				GeneralController controller = (GeneralController) loader.getController();
				controller.setLanguage(language);
				controller.setSettings(settings);
				newStage.show();
				Stage mainStage = (Stage) sendButton.getScene().getWindow();
				mainStage.close();
				controller.setLabels();
 		}		
		catch (Exception e) {
			e.printStackTrace();
			Alert alert = new Alert(AlertType.ERROR,language.getLanguageBundle().getString("windowError"));
			alert.showAndWait();
		}
	}
	
	@FXML public void sendButtonFired(ActionEvent event) {
	}
	
	
}
