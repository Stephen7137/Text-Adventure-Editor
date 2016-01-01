package cpp.TextAdvEditor.View;

import java.io.IOException;
import java.util.ArrayList;

import cpp.TextAdvEditor.Model.NodeText;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DisplaySearch {

	String input;
	Stage stage;
	
	public int getSearch(ArrayList<NodeText> list, Stage owner, int currentID){
		
		stage = new Stage();
		input = null;
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("SearchNodes.fxml"));
		
		Scene scene = null;
		try {
			scene = new Scene(loader.load());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		SearchNodes search = loader.getController();
		search.setSearch(createList(list), this);
		
		stage.setScene(scene);
		stage.setTitle("TA Editor");
		stage.initModality(Modality.WINDOW_MODAL);
		stage.initOwner(owner);
		stage.showAndWait();
		
		return getID(list, currentID);
	}
	
	private int getID(ArrayList<NodeText> list, int currentID){
		if(input==null){
			return currentID;
		}else{
			for(NodeText text : list){
				if(text.getText().equals(input)) return text.getID();
			}
		}
		
		return -1;
	}
	
	private ObservableList<String> createList(ArrayList<NodeText> list){
		
		ObservableList<String> buffer =	FXCollections.observableArrayList();
		
		for(NodeText text : list){
			buffer.add(text.getText());
		}
		
		return buffer;
	}

	public void set(String value) {
		this.input = value;
		stage.close();
	}

	public void close() {
		input = null;
		stage.close();
	}
}
