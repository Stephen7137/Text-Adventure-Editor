package cpp.TextAdvEditor.View;
	
import java.io.IOException;

import cpp.TextAdvEditor.ProjectManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	
	int height = 600;
	int width = 800;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Scene scene = createScene();
			primaryStage.setMinHeight(scene.getHeight());
			primaryStage.setMinWidth(scene.getWidth());
			primaryStage.setScene(scene);
			primaryStage.setTitle("TA Editor");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Scene createScene() throws IOException{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Text Adv Editor GUI.fxml"));
		Scene scene = new Scene(loader.load(),width,height);
		TextAdvEditorControler controller = loader.getController();
		controller.setWriteChapter(new ProjectManager());
		loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("SimConsole.fxml"));
		controller.addTab("Console", loader.load());
		loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Editor.fxml"));
		controller.addTab("Overview", loader.load());
		return scene;
	}
			
	public static void main(String[] args) {
		launch(args);
	}
}
