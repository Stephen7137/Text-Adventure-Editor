package cpp.TextAdvEditor.View;

import cpp.TextAdvEditor.ChapterEditor;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SimConsole {

	@FXML
	private TextField input;
	
	@FXML
	private Button next;
	
	@FXML
	private TextArea story;
	
	ChapterEditor cHeditor;
	
	@FXML
	private void next(){
		String usrInput = input.getText();
		if(usrInput.length() > 0){
			cHeditor.next(Integer.parseInt(usrInput));
			input.clear();
		}else{
			cHeditor.next(0);
		}
		if(cHeditor.curHasChildren()){
			next.setText("Enter");
		}else{
			next.setText("Next");
		}
	}
	
	@FXML
	private void back(){
		//cHeditor.back();
	}

	public void setStory(StringProperty textArea) {
		this.story.textProperty().bind(textArea);	
	}

	public void cHeditor(ChapterEditor cHeditor) {
		this.cHeditor = cHeditor;
		this.story.textProperty().bind(cHeditor.getStory());
	}
}
