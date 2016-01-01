package cpp.TextAdvEditor.View;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class SearchNodes {
	
	 DisplaySearch displaySearch;
	
	@FXML
	private TextField input;
	
	@FXML
	private ComboBox<String> selected;

	private ObservableList<String> list;

	@FXML
	private void cancel(){
		displaySearch.close();
	}
	
	@FXML
	private void setNode(){
		displaySearch.set(selected.getValue());
	}
	
	public void setSearch(ObservableList<String>  list, DisplaySearch displaySearch){
		this.displaySearch = displaySearch;
		this.list = list;
		input.textProperty().addListener(new ChangeListener<String>(){

			@Override
			public void changed(ObservableValue<? extends String> arg0,
					String arg1, String arg2) {
				updateList();
			}
			
		});
		updateList();
	}
	
	private void updateList(){
		
		ObservableList<String> buffer = FXCollections.observableArrayList();
		selected.getItems().clear();
		boolean hide = false;
		
		if(input.getText().equals("")){
			buffer = list;
			hide = true;
		}else{
			for(String item : list){
			
				if(item.toLowerCase().contains(input.getText().toLowerCase())){
					buffer.add(item);
				}			
			}
		}
		selected.getItems().addAll(buffer);
		
		if(hide){
			selected.hide();
		}else{
			selected.show();
		}
	}
}
