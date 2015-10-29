package cpp.TextAdvEditor.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import cpp.TextAdvEditor.ChapterEditor;

public class GetOptionController {

	private ChapterEditor chapter;
	private GetOption getOption;
	
	@FXML
	private ComboBox<String> box;
	
	@FXML
	private void setOption(){
		if(box.getValue()!=null){
			chapter.connect(Integer.parseInt(box.getValue()));
			getOption.close();
		}
	}
	
	@FXML
	private void close(){
		getOption.close();
	}	
	
	public void setChapter(ChapterEditor chapter, GetOption getOption) {
		this.chapter = chapter;
		this.getOption = getOption;
		ObservableList<String> options = FXCollections.observableArrayList();
		for(int i = 0; i < chapter.getChildSize(); i++){
			options.add(Integer.toString(i));
		}
		box.setItems(options);
	}
}
