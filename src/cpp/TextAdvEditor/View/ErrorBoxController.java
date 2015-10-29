package cpp.TextAdvEditor.View;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ErrorBoxController {

	ErrorBox errorBox;
	
	@FXML
	private Text text;
	
	public void setChapter(ErrorBox errorBox, String message) {
		this.errorBox = errorBox;
		text.setText(message);
	}

	@FXML
	private void close(){
		errorBox.close();
	}
	
}
