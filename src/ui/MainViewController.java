package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainViewController {

	@FXML
	private Button btn_submit;
	
	@FXML
	void buttonFired(ActionEvent event) {
		System.out.println("Button fired");
	}
}
