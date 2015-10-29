package cpp.TextAdvEditor.View;

import cpp.TextAdvEditor.ChapterEditor;
import cpp.TextAdvEditor.ProjectManager;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
	private TabPane tab;
	
	@FXML
	private TextField input0;
	
	@FXML
	private TextField input1;
	
	@FXML
	private TextArea story;
	
	@FXML
	private TextArea parentNode;
	
	@FXML
	private TextArea childNode;
	
	@FXML
	private void newNode(){
		new CreateNode(chapter);
	}
	
	@FXML
	private void next(){
		chapter.next(usrInput.getText());
	}

	@FXML
	private void connect(){
		if(chapter.getNodeType() == 1){
			new GetOption(chapter);
		}else{
			chapter.connect();
		}
	}
	
	@FXML
	private void disconnect(){
		chapter.disconnect();
	}
	
	@FXML
	private void deleteParent(){
		chapter.deleteParent();
	}
	
	@FXML
	private void deleteChild(){
		chapter.deleteChild();
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
	
	@FXML
	private void initialize(){
		usrInput = input0;
		tab.getSelectionModel().selectedItemProperty().addListener(
			    new ChangeListener<Tab>() {
			    	
			        @Override
			        public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
			        	input0.setText("");
			            input1.setText("");
			            if(t1.getText().equals("Selected Nodes")){
			            	usrInput = input0;
			            }else if(t1.getText().equals("Over View")){
			            	usrInput = input1;
			            }
			        }
			    }
			);
	}
	
	public void setWriteChapter(ProjectManager manager){
		this.manager = manager;
		this.chapter = manager.getChapter();
		updateText();	
	}
	
	private void updateText(){
		story.textProperty().bind(chapter.getMainText());
		parentNode.textProperty().bind(chapter.getMainText());
		childNode.textProperty().bind(chapter.getChildText());
	}
}
