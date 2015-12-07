package cpp.TextAdvEditor.View;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SimConsole {

	@FXML
	private TextField input;
	
	@FXML
	private TextArea story;
	
	@FXML
	private int next(){
		return 0;
	}
	
	@FXML
	private void back(){
		
	}

	public void setStory(StringProperty textArea) {
		this.story.textProperty().bind(textArea);	
	}
}
