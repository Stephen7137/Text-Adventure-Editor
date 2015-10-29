package cpp.TextAdvEditor.View;

import java.io.IOException;

import cpp.TextAdvEditor.ChapterEditor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreateNode{

	Stage stage;
	ChapterEditor chapter;
	
	public CreateNode(ChapterEditor chapter){
		this.chapter = chapter;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("CreateNode.fxml"));
			Parent root = loader.load();
			CreateNodeController controller = loader.getController();
			controller.setChapter(chapter, this);
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setTitle("Create Node");
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void close(){
		stage.close();
	}

	public void text() {
		createStage("Text Node", "TextNode.fxml",0);
	}

	public void option() {
		createStage("Option Node", "OptionNode.fxml",1);
	}

	public void end() {
		createStage("End Node", "EndNode.fxml",2);
	}
	
	private void createStage(String title, String file,int num){
		close();
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
			Parent root = loader.load();
			NodeTypeController controller = loader.getController();
			controller.setChapter(chapter, this, num);
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setTitle(title);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
