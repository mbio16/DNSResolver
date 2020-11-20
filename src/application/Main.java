package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import models.Settings;
import ui.GeneralController;
import javafx.scene.Scene;


public class Main extends Application {
	
	private Settings settings;
	private Language language;
	
	public static String MAIN_STAGE_FXML_FILE = "/ui/Main.fxml";
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
	}
	
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
