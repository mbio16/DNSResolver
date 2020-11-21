package ui;

import java.util.logging.Logger;


import application.Language;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import models.Ip;

public class DNSController extends GeneralController {
	
	public static final String FXML_FILE_NAME="DNS.fxml";
	//menu items
	@FXML private Menu actionMenu;
	@FXML private MenuItem backMenuItem;
	@FXML private Menu languageMenu;
	@FXML private RadioMenuItem czechRadioButton;
	@FXML private RadioMenuItem englishRadioButton;
	
	//butons
	@FXML private Button sendButton;
	
	//text fields
	@FXML private TextField domainNameTextField;
	@FXML private TextField dnsServerTextField;
	
	//radio buttons
	@FXML private RadioButton ipv4RadioButton;
	@FXML private RadioButton ipv6RadioButton;
	@FXML private RadioButton tcpRadioButton;
	@FXML private RadioButton udpRadioButton;
	@FXML private RadioButton dnssecYesRadioButton;
	@FXML private RadioButton dnssecNoRadioButton;
	@FXML private RadioButton recursiveQueryRadioButton;
	@FXML private RadioButton iterativeQueryRadioButton;
	@FXML private RadioButton cloudflareIpv4RadioButton;
	@FXML private RadioButton googleIpv4RadioButton;
	@FXML private RadioButton cznicIpv4RadioButton;
	@FXML private RadioButton cloudflareIpv6RadioButton;
	@FXML private RadioButton googleIpv6RadioButton;
	@FXML private RadioButton cznicIpv6RadioButton;
	@FXML private RadioButton otherDNSServerRadioButton;
	
	//checkboxes
	@FXML private CheckBox aCheckBox;
	@FXML private CheckBox aaaaCheckBox;
	@FXML private CheckBox nsCheckBox;
	@FXML private CheckBox mxCheckBox;
	@FXML private CheckBox soaCheckBox;
	@FXML private CheckBox cnameCheckBox;
	@FXML private CheckBox ptrCheckBox;
	
	//titledpane
	
	@FXML private TitledPane domainNameTitledPane;
	@FXML private TitledPane ipTitledPane;
	@FXML private TitledPane transportTitledPane;
	@FXML private TitledPane dnssecTitledPane;
	@FXML private TitledPane recordTypeTitledPane;
	@FXML private TitledPane dnsServerTitledPane;
	@FXML private TitledPane iterativeTitledPane;
	
	//toogleGroup
	private ToggleGroup ipToggleGroup;
	private ToggleGroup transportToggleGroup;
	private ToggleGroup dnssecToggleGroup;
	private ToggleGroup iterativeToggleGroup;
	private ToggleGroup dnsserverToggleGroup;
	public DNSController() {
		super();
		LOGGER = Logger.getLogger(DNSController.class.getName());		
	}
	
	public void  initialize() {
		ipToggleGroup = new ToggleGroup();
		ipv4RadioButton.setToggleGroup(ipToggleGroup);
		ipv6RadioButton.setToggleGroup(ipToggleGroup);
		
		transportToggleGroup = new ToggleGroup();
		tcpRadioButton.setToggleGroup(transportToggleGroup);
		udpRadioButton.setToggleGroup(transportToggleGroup);
		
		iterativeToggleGroup = new ToggleGroup();
		iterativeQueryRadioButton.setToggleGroup(iterativeToggleGroup);
		recursiveQueryRadioButton.setToggleGroup(iterativeToggleGroup);
		
		dnssecToggleGroup = new ToggleGroup();
		
		dnssecYesRadioButton.setToggleGroup(dnssecToggleGroup);
		dnssecNoRadioButton.setToggleGroup(dnssecToggleGroup);
		
		dnsserverToggleGroup = new ToggleGroup();
		cloudflareIpv4RadioButton.setToggleGroup(dnsserverToggleGroup);
		cloudflareIpv6RadioButton.setToggleGroup(dnsserverToggleGroup);
		googleIpv4RadioButton.setToggleGroup(dnsserverToggleGroup);
		googleIpv6RadioButton.setToggleGroup(dnsserverToggleGroup);
		cznicIpv4RadioButton.setToggleGroup(dnsserverToggleGroup);
		cznicIpv6RadioButton.setToggleGroup(dnsserverToggleGroup);
		otherDNSServerRadioButton.setToggleGroup(dnsserverToggleGroup);
	
	}
	
	public void setLabels() {
		//define group to iterate over it
		TitledPane titlePaneArray [] = new TitledPane []{domainNameTitledPane,
				ipTitledPane,
				transportTitledPane,
				dnssecTitledPane,
				recordTypeTitledPane,
				dnsServerTitledPane,
				iterativeTitledPane};
		
		//same for radio buttons
		RadioButton [] radioButtonArray = new RadioButton [] {
			dnssecYesRadioButton,
			dnssecNoRadioButton,
			iterativeQueryRadioButton,
			recursiveQueryRadioButton
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
		
		//set sendButton
		sendButton.setText(language.getLanguageBundle().getString(sendButton.getId()));
		
		if (language.getCurrentLanguage().equals(Language.CZECH)) {
			czechRadioButton.setSelected(true);
			englishRadioButton.setSelected(false);
		}
		else {
			czechRadioButton.setSelected(false);
			englishRadioButton.setSelected(true);
		}
	}
	
	public void loadDataFromSettings(){
		dnsServerTextField.setText(settings.getLastDNSServerUsed());
	}
	
	@FXML
	public void backButtonFirred(ActionEvent event) {
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
	
	@FXML public void czechSelected(ActionEvent event) {
		language.changeLanguageBundle(true);
		setLabels();
	}
	@FXML public void englishSelected(ActionEvent event) {
		language.changeLanguageBundle(false);
		setLabels();
	}
	
	@FXML public void otherDNSServerCheck(ActionEvent event) {
		
		if(otherDNSServerRadioButton.isSelected()) {
			LOGGER.info("Other DNS server is enabled");
			dnsServerTextField.setDisable(false);
		}
		else {
			dnsServerTextField.setDisable(true);
		}
	}
	
	@FXML public void sendButtonFired(ActionEvent event) {
		
		
		if(otherDNSServerRadioButton.isSelected() && Ip.isIpValid(dnsServerTextField.getText())) {
			settings.setLastDNSServerUsed(dnsServerTextField.getText());
			//System.out.println(Ip.getIpReversed(dnsServerTextField.getText()));
		}
	}
	
}
