package cpp.TextAdvEditor.View;

import cpp.TextAdvEditor.CanvasManager;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Editor {
	
	@FXML
	private Canvas canvas;
	
	@FXML
	private ComboBox<String> bookmark;
	
	@FXML
	private AnchorPane textInput;

	private CanvasManager cnvsManager;
	private ContextMenu contextMenu;
	
	@FXML
	private void select(){
		
	}
	
	@FXML
	private void mouseClick(MouseEvent e){
		cnvsManager.onNode(e.getX(),e.getY());
		if(e.getButton() == MouseButton.SECONDARY){
			contextMenu.show(canvas, e.getScreenX(), e.getScreenY());
		}else{
			contextMenu.hide();
		}
		cnvsManager.addCircle(e.getX(), e.getY());
	}

	public void setCanvasManger(CanvasManager canvasManager) {
		cnvsManager = canvasManager;
		cnvsManager.setGrphCont(canvas.getGraphicsContext2D(), 
				 canvas.getWidth(), canvas.getHeight());
		
		createContextMenu();
	}

	private void createContextMenu() {
		MenuItem addChild = new MenuItem("add Child");
		MenuItem addNode = new MenuItem("Create Node");
		
		contextMenu = new ContextMenu(addChild,addNode);
	}
}
