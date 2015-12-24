package cpp.TextAdvEditor.View;
	
import java.io.IOException;
import java.util.Random;

import cpp.TextAdvEditor.CanvasManager;
import cpp.TextAdvEditor.ChapterEditor;
import cpp.TextAdvEditor.ProjectManager;
import cpp.TextAdvEditor.Model.Story;
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
			primaryStage.setScene(scene);
			primaryStage.setTitle("TA Editor");
			primaryStage.setMinHeight(primaryStage.getHeight());
			primaryStage.setMinWidth(primaryStage.getWidth());
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Scene createScene() throws IOException{
		Random ran = new Random();
		Story story = new Story(ran.nextInt(10000));
		ChapterEditor cHeditor = new ChapterEditor(story.getSrtCh().getID(),
				story.getSrtCh().getTree());
		CanvasManager cnvsManager = new CanvasManager(story.getSrtCh().getID());
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Text Adv Editor GUI.fxml"));
		Scene scene = new Scene(loader.load(),width,height);
		TextAdvEditorControler controller = loader.getController();
		controller.setWriteChapter(new ProjectManager(story, cnvsManager, cHeditor));
		
		loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("SimConsole.fxml"));
		controller.addTab("Console", loader.load());
		SimConsole console = loader.getController();
		console.cHeditor(cHeditor);
		
		loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("Editor.fxml"));
		controller.addTab("Overview", loader.load());
		Editor editor = loader.getController();
		editor.setCanvasManger(cnvsManager, cHeditor);
		return scene;
	}
			
	public static void main(String[] args) {
		launch(args);
	}
}
