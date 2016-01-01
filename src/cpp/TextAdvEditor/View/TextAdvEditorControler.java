package cpp.TextAdvEditor.View;

import java.util.Optional;

import cpp.TextAdvEditor.ChapterEditor;
import cpp.TextAdvEditor.ProjectManager;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TextAdvEditorControler {
		
	/**
	 * The object that holds the chapter and is used to
	 * save and load the chapter.
	 */
	private ProjectManager manager;
	private ChapterEditor cHeditor;
	Editor editor;
	private Stage owner;	
	
	@FXML
	private TabPane tabPane;
	
	@FXML
	private MenuItem addBook;
	
	@FXML
	private MenuItem removeBook;
	
	@FXML
	private void addBookmark(){
		cHeditor.addBookmark();
	}
	
	@FXML
	private void removeBookmark(){
		cHeditor.removeBookmark();
	}
	
	
	@FXML
	private void setCurrent(){
		editor.setCurrent();
	}
	
	@FXML
	private void setStart(){
		editor.setStart();
	}
	
	@FXML
	private void bookmark(){
		DisplaySearch search = new DisplaySearch();
		setSelected(search.getSearch(cHeditor.getBookMark(),owner,
				cHeditor.getSelectedKey()));
	}
	
	@FXML
	private void noChild(){
		DisplaySearch search = new DisplaySearch();
		setSelected(search.getSearch(cHeditor.getNoChildNode(),owner,
				cHeditor.getSelectedKey()));
	}
	
	@FXML
	private void noParent(){
		DisplaySearch search = new DisplaySearch();
		setSelected(search.getSearch(cHeditor.getNoParentNode(),owner,
				cHeditor.getSelectedKey()));
	}
	
	@FXML
	private void tree(){
		DisplaySearch search = new DisplaySearch();
		setSelected(search.getSearch(cHeditor.getAllNode(),owner,
				cHeditor.getSelectedKey()));
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
		manager.load();
	}
	
	@FXML
	private void export(){
		if(cHeditor.validate()){
			manager.export();
		}else{
			if(noParents()){
				manager.export();
			}
		}
	}
	
	@FXML
	private void update(){
		if(cHeditor.isNull()){
			addBook.setDisable(true);
			removeBook.setDisable(true);
		}else{
			if(cHeditor.currentBookmark()){
				addBook.setDisable(true);
				removeBook.setDisable(false);
			}else{
				addBook.setDisable(false);
				removeBook.setDisable(true);
			}
		}
		
	}
	
	private boolean noParents(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Node with no Parent");
		alert.setHeaderText(null);
		alert.setContentText("Node contains no Parent!\n"
				+ "Node will be inaccessible in the Text Adventure.\n"
				+ "Are you sure you want to continue?");
		
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No");
		
		alert.getButtonTypes().setAll(buttonYes, buttonNo);
		
		Optional<ButtonType> results = alert.showAndWait();
		if(results.get() == buttonYes) return true;
		return false;
	}
			
	public void setWriteChapter(ProjectManager manager, Stage owner){
		this.owner = owner;
		this.manager = manager;	
	}
	
	public void addTab(String string, AnchorPane load) {
		Tab tab = new Tab();
		tab.setText(string);
		tab.setContent(load);
		tabPane.getTabs().add(tab);
	}
	
	private void setSelected(int key){
		editor.SetSelected(key);
	}

	public void setData(Editor editor, ChapterEditor cHeditor) {
		this.editor = editor;
		this.cHeditor = cHeditor;
		update();
	}
}
