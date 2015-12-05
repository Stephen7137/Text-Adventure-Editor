package cpp.TextAdvEditor.View;

import cpp.TextAdvEditor.ChapterEditor;
import cpp.TextAdvEditor.ProjectManager;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TextAdvEditorControler {
	
	/**
	 * The universal input consolidates the input of the
	 * two other inputs.
	 */
	private TextField usrInput;
	
	/**
	 * The object that stores the chapter and edits it as
	 * well.
	 */
	private ChapterEditor chapter;
	
	/**
	 * The object that holds the chapter and is used to
	 * save and load the chapter.
	 */
	private ProjectManager manager;
		
	@FXML
	private TabPane tabPane;
	


	
	@FXML
	private TextArea story;
	
	@FXML
	private TextArea parentNode;
	
	@FXML
	private TextArea childNode;
	
	@FXML
	private Canvas canvas;
	
	@FXML
	private void newNode(){
		new CreateNode(chapter);
	}
	
	@FXML
	private void next(){
		//TODO chapter.next(usrInput.getText());
	}

	@FXML
	private void connect(){
		//TODO
//		if(chapter.getNodeType() == 1){
//			new GetOption(chapter);
//		}else{
//			chapter.connect();
//		}
	}
	
	@FXML
	private void disconnect(){
		chapter.disconnect();
	}
	
	@FXML
	private void deleteParent(){
		//TODO chapter.deleteParent();
	}
	
	@FXML
	private void deleteChild(){
		//TODO chapter.deleteChild();
	}
	
	@FXML
	private void save(){
		manager.save();
	}
	
	@FXML
	private void saveAs(){
		manager.saveAs();
	}
	
	@FXML
	private void load(){
		
	}
	
	@FXML
	private void addBookMark(){
		
	}
	
	@FXML
	private void getBookMark(){
		new SearchList(chapter, 0);
	}
	
	@FXML
	private void getLooseNode(){
		new SearchList(chapter, 1);
	}
	
	@FXML
	private void getOpenNode(){
		new SearchList(chapter, 2);
	}
		
	public void setWriteChapter(ProjectManager manager){
		this.manager = manager;
		this.chapter = manager.getChapter();	
	}
	
	private void updateText(){
		story.textProperty().bind(chapter.getStory());
	}

	public void addTab(String string, AnchorPane load) {
		Tab tab = new Tab();
		tab.setText(string);
		tab.setContent(load);
		tabPane.getTabs().add(tab);
	}
}
