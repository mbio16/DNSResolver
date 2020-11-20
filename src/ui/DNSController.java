package ui;

import java.util.logging.Logger;

import application.Language;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class DNSController extends GeneralController {
	
	public static final String FXML_FILE_NAME="DNS.fxml";
	
	@FXML private Menu actionMenu;
	@FXML private MenuItem backMenuItem;
	@FXML private Menu languageMenu;
	@FXML private RadioMenuItem czechRadioButton;
	@FXML private RadioMenuItem englishRadioButton;
	@FXML private Button sendButton;
	
	public DNSController() {
		super();
		LOGGER = Logger.getLogger(DNSController.class.getName());
	}
	
	public void setLabels() {
		backMenuItem.setText(language.getLanguageBundle().getString(backMenuItem.getId()));
		actionMenu.setText(language.getLanguageBundle().getString(actionMenu.getId()));
		languageMenu.setText(language.getLanguageBundle().getString(languageMenu.getId()));
		if (language.getCurrentLanguage().equals(Language.CZECH)) {
			czechRadioButton.setSelected(true);
			englishRadioButton.setSelected(false);
		}
		else {
			czechRadioButton.setSelected(false);
			englishRadioButton.setSelected(true);
		}
	}
	
	@FXML
	public void backButtonFirred(ActionEvent event) {
		try {
				System.out.println(MainController.FXML_FILE_NAME);
				FXMLLoader loader = new FXMLLoader(getClass().getResource(MainController.FXML_FILE_NAME));
				Stage newStage = new Stage();
				newStage.setScene(new Scene(loader.load()));
				GeneralController controller = (GeneralController) loader.getController();
				controller.setLanguage(language);
				controller.setSettings(settings);
				newStage.show();
				Stage mainStage = (Stage) b.getScene().getWindow();
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
	
}
