package cpp.TextAdvEditor.View;

import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import cpp.TextAdvEditor.ChapterEditor;

public class NodeTypeController {

	final private int optionNum = 9;
	final private String fileName = "OptionText.fxml";
	private ChapterEditor chapter;
	private CreateNode node;
	private int type;
	private TextArea[] optionTexts;
	
	@FXML
	private ScrollPane pane;
	
	@FXML
	private TextArea text;
	
	@FXML
	private TextField title;
	
	@FXML
	private TextField file;
	
	public void setChapter(ChapterEditor chapter, CreateNode node, int type){
		this.type = type;
		this.chapter = chapter;
		this.node = node;
		if(type == 1){
			optionTexts = new TextArea[optionNum];
			GridPane grid = new GridPane();
			for(int i = 0; i < optionTexts.length; i++){
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource(fileName));
					grid.add(loader.load(),0, i);
					OptionPane controller = loader.getController();
					controller.setText("Option " + (i + 1));
					optionTexts[i] = controller.getTextField();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			pane.setContent(grid);
		}
	}
	
	@FXML
	private void enter(){
		if(chapter.validateTitle(title.getText())){
			switch(type){
			case 0:
				chapter.createText(title.getText(),text.getText());
				break;
			case 1:
				chapter.createOption(title.getText(),text.getText(),getOptionText());
				break;
			case 2:
				chapter.createEnd(title.getText(),text.getText(),file.getText());
			}
			node.close();
		}else{
			new ErrorBox("Error: Title","Title already taken.");
		}
	}
	
	@FXML
	private void cancel(){
		node.close();
	}
	
	private ArrayList<String> getOptionText(){
		ArrayList<String> optionTxt = new ArrayList<String>();
		for(int i = 0; i < optionTexts.length; i++){
			String tmp = optionTexts[i].getText();
			if(!tmp.equals("")) optionTxt.add(tmp);
		}
		return optionTxt;
	}
}
