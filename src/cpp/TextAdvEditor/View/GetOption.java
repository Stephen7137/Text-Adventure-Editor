package cpp.TextAdvEditor.View;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import cpp.TextAdvEditor.ChapterEditor;

public class GetOption {

	Stage stage;
	ChapterEditor chapter;
	
	public GetOption(ChapterEditor chapter) {
		try {
			this.chapter = chapter;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("OptionNum.fxml"));
			Parent root = loader.load();
			GetOptionController controller = loader.getController();
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

	public void close() {
		stage.close();
	}
}
