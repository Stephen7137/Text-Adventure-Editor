package cpp.TextAdvEditor.View;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class OptionPane {

	@FXML
	private Text title;
	
	@FXML
	private TextArea optionText;
	
	@FXML
	private Button up;
	
	@FXML
	private Button down;
	
	OptionManager optionManager;
	int index;
	
	@FXML
	private Text ID;
	
	@FXML
	private void up(){
		optionManager.up(this, index);
	}
	
	@FXML
	private void down(){
		optionManager.down(this, index);
	}


	public TextArea getTextField() {
		return optionText;
	}
	
	public int getID() {
		return Integer.parseInt(ID.getText());
	}

	public void set(OptionManager optionManager, int index, int id, String text) {
		this.index = index;
		this.optionManager = optionManager;
		ID.setText(Integer.toString(id));
		optionText.setText(text);
	}
	
	public void setTop(){
		up.setDisable(true);
		down.setDisable(false);
	}
	
	public void setBottom(){
		up.setDisable(false);
		down.setDisable(true);
	}
	
	public void reset(){
		up.setDisable(false);
		down.setDisable(false);
	}

}
