package cpp.TextAdvEditor.View;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class OptionPane {

	@FXML
	private Text option;
	
	@FXML
	private TextArea optionText;

	public void setText(String string) {
		option.setText(string);		
	}

	public TextArea getTextField() {
		return optionText;
	}
	
	
}
