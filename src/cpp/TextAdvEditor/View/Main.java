package cpp.TextAdvEditor.View;
	
import cpp.TextAdvEditor.ProjectManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	int height = 600;
	int width = 800;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("Text Adv Editor GUI.fxml"));
			Parent root = loader.load();
			Scene scene = new Scene(root,width,height);
			TextAdvEditorControler controller = loader.getController();
			controller.setWriteChapter(new ProjectManager());
			primaryStage.setMinHeight(scene.getHeight());
			primaryStage.setMinWidth(scene.getWidth());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
			
	public static void main(String[] args) {
		launch(args);
	}
}
