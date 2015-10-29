package cpp.TextAdvEditor.View;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ErrorBox {
	
	Stage stage;
	
	public ErrorBox(String title,String message){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ErrorBox.fxml"));
			Parent root = loader.load();
			ErrorBoxController controller = loader.getController();
			controller.setChapter(this, message);
			Scene scene = new Scene(root);
			stage = new Stage();
			stage.setTitle(title);
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
