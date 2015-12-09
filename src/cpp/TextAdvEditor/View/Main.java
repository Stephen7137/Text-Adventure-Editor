package cpp.TextAdvEditor.View;
	
import java.io.IOException;

import cpp.TextAdvEditor.CanvasManager;
import cpp.TextAdvEditor.ChapterEditor;
import cpp.TextAdvEditor.ProjectManager;
import cpp.TextAdvEditor.Model.Chapter;
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
		
		ChapterEditor CHeditor = new ChapterEditor(new Chapter());
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Text Adv Editor GUI.fxml"));
		Scene scene = new Scene(loader.load(),width,height);
		TextAdvEditorControler controller = loader.getController();
		controller.setWriteChapter(new ProjectManager());
		loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("SimConsole.fxml"));
		controller.addTab("Console", loader.load());
		SimConsole console = loader.getController();
		console.setStory(CHeditor.getStory());
		loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Editor.fxml"));
		controller.addTab("Overview", loader.load());
		Editor editor = loader.getController();
		editor.setCanvasManger(new CanvasManager());
		return scene;
	}
			
	public static void main(String[] args) {
		launch(args);
	}
}
