package cpp.TextAdvEditor.View;

import java.util.Optional;

import cpp.TextAdvEditor.CanvasManager;
import cpp.TextAdvEditor.ChapterEditor;
import cpp.TextAdvEditor.Model.Text;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Editor {
	
	@FXML
	private Canvas canvas;
	
	@FXML
	private AnchorPane canvasPane;
	
	@FXML
	private TextField title;
	
	@FXML
	private TextArea text;
	
	@FXML
	private VBox option;

	private CanvasManager cnvsManager;
	private ChapterEditor cHeditor;
	private boolean onSelected;
	private boolean onConnect;
	private OptionManager optionManager;
	
	@FXML
	private void select(){
		
	}
	
	@FXML
	private void save(){
		cHeditor.setSelTitle(title.getText());
		cHeditor.setSelText(text.getText());
		if(cHeditor.selHasChildren()){
			cHeditor.setSelOText(optionManager.getOText());
		}
		if(cHeditor.isCurrent()){
			cHeditor.updateText();
		}
	}
	
	@FXML
	private void delete(){
		boolean delete = true;
		if(!cHeditor.isEmpty()){
			if(!deteleError()){
				delete = false;
			}
		}
		if(delete){
			cHeditor.delete();
			cnvsManager.delete();
		}
	}
	
	@FXML
	private void onPress(MouseEvent e){
		if(e.getButton() == MouseButton.PRIMARY){
			int press = e.getClickCount();
			
			switch(cnvsManager.tools(e.getX(),e.getY())){
			case 0:
				cnvsManager.createNode(e.getX(),e.getY(), cHeditor.createText());
				break;
			}
			if(cnvsManager.onSelected(e.getX(),e.getY())){
				onSelected = true;
			}
			if(cnvsManager.onConnect(e.getX(),e.getY())){
				onConnect = true;
			}
			
			if(cnvsManager.onNode(e.getX(),e.getY(),true) >= 0){
				cHeditor.setSelected(cnvsManager.getSelected());
				updateEditor();
			}else if( press > 1){
				disable();
				cHeditor.setSelected(-1);
				cnvsManager.resetSelected();
			}
		}
	}
	
	@FXML
	private void onRelease(MouseEvent e){
		onSelected = false;
		if(onConnect){
			if(cHeditor.isChild(cnvsManager.onNode(e.getX(),e.getY(),false))){
				cnvsManager.disconnect(cHeditor.disconnect(
						cnvsManager.onNode(e.getX(),e.getY(),false)));
			}else{
				cnvsManager.connect(cHeditor.connect(
					cnvsManager.onNode(e.getX(),e.getY(),false)));
			}
			updateEditor();
			onConnect = false;	
		}
	}
	
	@FXML
	private void follow(MouseEvent e){
		if(onSelected){
			cnvsManager.moveNode(e.getX(),e.getY());
		}
		if(onConnect){
			cnvsManager.drawConnect(e.getX(),e.getY(), cHeditor.isChild(
					cnvsManager.onNode(e.getX(),e.getY(),false)));
		}
	}
	
	private boolean deteleError(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Node");
		alert.setHeaderText(null);
		alert.setContentText("Node contains Text!\nAre you sure you want to delte node?");
		
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No");
		
		alert.getButtonTypes().setAll(buttonYes, buttonNo);
		
		Optional<ButtonType> results = alert.showAndWait();
		if(results.get() == buttonYes) return true;
		return false;
	}
	
	private void updateEditor() {
		if(!cHeditor.isNull()){
			title.setDisable(false);
			text.setDisable(false);
			title.setText(cHeditor.getSelTitle());
			text.setText(cHeditor.getSelText());
			if(cHeditor.selHasChildren()){
				option.setDisable(false);
				optionManager.setOption(cHeditor.getSelOText());
			}else{
				option.setDisable(true);
				optionManager.reset();
			}
		}else{
			disable();
		}
	}

	public void setCanvasManger(CanvasManager canvasManager, ChapterEditor cHeditor) {
		this.cHeditor = cHeditor;
		cnvsManager = canvasManager;
		onSelected = false;
		onConnect = false;
		cnvsManager.setCanvas(canvas,cHeditor.currentKey());
		optionManager = new OptionManager(option, cnvsManager);
		
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
		
		cnvsManager.StartNode(cHeditor.createStart());
		
		if(cnvsManager.getSelected() == -1){
			disable();
		}
	}
	
	public void SetSelected(int ID){
		cHeditor.setSelected(ID);
		cnvsManager.setSelected(ID);
		updateEditor();
	}
	
	private void disable(){
		title.clear();
		text.clear();
		title.setDisable(true);
		text.setDisable(true);
	}
	
	public void setCurrent(){
		cHeditor.setCurrent(cnvsManager.getSelected());
	}
	
	public void setStart(){
		cnvsManager.setStart();
		cHeditor.setStart();
	}
}
