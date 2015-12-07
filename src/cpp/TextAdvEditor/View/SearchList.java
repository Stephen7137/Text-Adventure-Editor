package cpp.TextAdvEditor.View;

import java.io.IOException;

import cpp.TextAdvEditor.ChapterEditor;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SearchList {

	Stage stage;
	ChapterEditor chapter;
	private int type;
	
	public SearchList(ChapterEditor chapter, int type) {
		
		this.type = type;
		stage = new Stage();
		ObservableList<String> options = null;
		switch(type){
		case 0:
			options = chapter.getBookMark();
			stage.setTitle("Search Bookmarks");
			break;
		case 1:
			options = chapter.getLooseNode();
			stage.setTitle("Search Loose Nodes");
			break;
		case 2:
			//options = chapter.getOpenNode();
			stage.setTitle("Search Open Nodes");
		}
		
		try {
			this.chapter = chapter;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchList.fxml"));
			Parent root = loader.load();
			SearchListControler controller = loader.getController();
			controller.setChapter(this, options);
			Scene scene = new Scene(root);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		stage.close();
	}

	public void setValue(String selected) {
		switch(type){
		case 0:
			chapter.setBookMark(selected);
			break;
		case 1:
			chapter.setLooseNode(selected);
			break;
		case 2:
			chapter.setOpenNode(selected);
		}
	}

	public ObservableList<String> search(String text) {
		switch(type){
		case 0:
			chapter.getBookMark(text);
			break;
		case 1:
			chapter.getLooseNode(text);
			break;
		case 2:
			chapter.getOpenNode(text);
		}
		return null;
	}

}