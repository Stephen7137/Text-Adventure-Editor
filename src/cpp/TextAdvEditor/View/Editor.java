package cpp.TextAdvEditor.View;

import java.util.ArrayList;

import cpp.TextAdvEditor.CanvasManager;
import cpp.TextAdvEditor.ChapterEditor;
import cpp.TextAdvEditor.Model.Text;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
	private AnchorPane canvasPane;
	
	@FXML
	private TextField title;
	
	@FXML
	private TextArea text;
	
	@FXML
	private AnchorPane option;

	private CanvasManager cnvsManager;
	ChapterEditor cHeditor;
	
	@FXML
	private void select(){
		
	}
	
	@FXML
	private void setCurrent(){
		cHeditor.setCurrent(cnvsManager.getSelected());
	}
	
	@FXML
	private void save(){
		Text selected = cHeditor.getSelected();
		selected.setTitle(title.getText());
		selected.setText(text.getText());
		if(cHeditor.isCurrent()){
				cHeditor.updateText();
			}
	}
	
	@FXML
	private void mouseClick(MouseEvent e){
		if(e.getButton() == MouseButton.PRIMARY){
			cHeditor.setSelected(cnvsManager.onNode(e.getX(),e.getY()));
			updateEditor(cHeditor.getSelected());
		}
	}
	
	@FXML
	private void addChild(){
		cnvsManager.addChild(cHeditor.addChild(cnvsManager.getSelected()));
	}
	
	private void updateEditor(Text selected) {
		if(selected!=null){
			title.setDisable(false);
			text.setDisable(false);
			title.setText(selected.getTitle());
			text.setText(selected.getText());
			if(selected.getChildSize()>1){
				ArrayList<Text> children = selected.getChild();
				for(int i = 0; i < children.size(); i++){
					
				}
			}else{
				//TODO
			}
		}else{
			disable();
		}
		
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
		
		if(cnvsManager.getSelected() == -1){
			disable();
		}
	}
	
	private void disable(){
		title.clear();
		text.clear();
		title.setDisable(true);
		text.setDisable(true);
	}
}
