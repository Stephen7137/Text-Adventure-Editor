package cpp.TextAdvEditor.View;

import cpp.TextAdvEditor.ChapterEditor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class CreateNodeController {
	
	ChapterEditor chapter;
	CreateNode node;
	
	@FXML
	private ComboBox<String> nodeType;
	
	@FXML
	private void initialize(){
		ObservableList<String> options = 
			    FXCollections.observableArrayList( "Text", "Option","End");
		nodeType.setItems(options);
	}
	
	public void setChapter(ChapterEditor chapter, CreateNode node){
		this.chapter = chapter;
		this.node = node;
	}
	
	@FXML
	private void crtNode(){
		String text = nodeType.getValue();
		if(text!=null){
			switch(text){
			case "Text":
				node.text();
				break;
			case "Option":
				node.option();
				break;
			case "End":
				node.end();
			}
		}
	}
	
	@FXML
	private void cnclNode(){
		node.close();
	}
	
	public void update(CreateNode node){
		this.node = node;
	}
}

