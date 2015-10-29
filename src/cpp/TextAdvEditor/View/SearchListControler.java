package cpp.TextAdvEditor.View;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SearchListControler {

	private SearchList searchList;

	@FXML
	private ComboBox<String> box;
	
	@FXML
	private TextField text;
	
	@FXML
	private void search(){
		if(text.getText()!=null) box.setItems(searchList.search(text.getText()));
	}
	
	@FXML
	private void select(){
		if(box.getValue()!=null){
			searchList.setValue(box.getValue());
			searchList.close();
		}
	}
	
	@FXML
	private void close(){
		searchList.close();
	}
	
	public void setChapter(SearchList searchList,
			ObservableList<String> options) {
		this.searchList = searchList;		
		box.setItems(options);
	}
}
