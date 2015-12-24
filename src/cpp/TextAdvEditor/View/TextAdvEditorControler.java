package cpp.TextAdvEditor.View;

import cpp.TextAdvEditor.ProjectManager;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

public class TextAdvEditorControler {
		
	/**
	 * The object that holds the chapter and is used to
	 * save and load the chapter.
	 */
	private ProjectManager manager;
		
	@FXML
	private TabPane tabPane;
				
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
			
	public void setWriteChapter(ProjectManager manager){
		this.manager = manager;	
	}
	
	public void addTab(String string, AnchorPane load) {
		Tab tab = new Tab();
		tab.setText(string);
		tab.setContent(load);
		tabPane.getTabs().add(tab);
	}
}
