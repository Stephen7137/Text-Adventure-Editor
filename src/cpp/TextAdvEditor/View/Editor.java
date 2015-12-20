package cpp.TextAdvEditor.View;

import cpp.TextAdvEditor.CanvasManager;
import cpp.TextAdvEditor.ChapterEditor;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
	
	@FXML
	private AnchorPane canvasPane;

	private CanvasManager cnvsManager;
	private ContextMenu contextMenu;
	ChapterEditor cHeditor;
	
	@FXML
	private void select(){
		
	}
	
	@FXML
	private void mouseClick(MouseEvent e){
//		if(e.getButton() == MouseButton.SECONDARY){
//			contextMenu.show(canvas, e.getScreenX(), e.getScreenY());
//		}else{
//			contextMenu.hide();
//		}
		if(e.getButton() == MouseButton.PRIMARY){
			System.out.println(cnvsManager.onNode(e.getX(),e.getY()));
		}
	}
	
	@FXML
	private void addChild(){
		cnvsManager.addChild(cHeditor.addChild(cnvsManager.getSelected()));
	}

	public void setCanvasManger(CanvasManager canvasManager, ChapterEditor cHeditor) {
		this.cHeditor = cHeditor;
		cnvsManager = canvasManager;
		cnvsManager.setCanvas(canvas,cHeditor.currentKey());
		
		canvasPane.heightProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observableValue,
					Number oldHeight, Number newHeight) {
				canvas.heightProperty().set((double) newHeight );
				cnvsManager.update();
			}
		});
	
		canvasPane.widthProperty().addListener(new ChangeListener<Number>(){
	
			@Override
			public void changed(ObservableValue<? extends Number> observableValue,
					Number oldWidth, Number newWidth) {
				canvas.widthProperty().set((double) newWidth );
				cnvsManager.update();
			}
		});
		
		createContextMenu();
	}

	private void createContextMenu() {
		
		MenuItem addChild = new MenuItem("add Child");
		MenuItem addNode = new MenuItem("set Current");
		
		contextMenu = new ContextMenu(addChild,addNode);
	}
}
