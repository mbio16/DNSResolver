package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import models.Settings;
import models.Language;
import ui.GeneralController;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	
	private Settings settings;
	private Language language;
	
	public static String MAIN_STAGE_FXML_FILE = "/ui/Main.fxml";
	public static String ICON_URI = "file:icon.png";
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// load language and settings
			this.settings = new Settings();
			this.language = new Language();
			this.language.changeLanguageBundle(true);
		
			// loading fxml 
			FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_STAGE_FXML_FILE));
			Stage newStage = new Stage();
			newStage.getIcons().add(new Image(ICON_URI));
			newStage.setScene(new Scene(loader.load()));
			
			//pass objects
			GeneralController controller = (GeneralController) loader.getController();
			controller.setLanguage(language);
			controller.setSettings(settings);
			
			//show scene
			newStage.show();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() {
		settings.appIsClossing();
		System.exit(0);
	}
	
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
