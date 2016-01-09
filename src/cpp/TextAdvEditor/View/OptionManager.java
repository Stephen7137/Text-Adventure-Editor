package cpp.TextAdvEditor.View;

import java.io.IOException;
import java.util.ArrayList;

import cpp.TextAdvEditor.Model.NodeText;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class OptionManager {

	AnchorPane option;
	GridPane grid;
	FXMLLoader loader;
	Node[] optionNode;
	
	public OptionManager(AnchorPane option){
		this.option = option;
		loader = new FXMLLoader();
		loader.setLocation(OptionManager.class.getResource("OptionText.fxml"));
	}
	
	public void setOption(ArrayList<NodeText> optionText){
		optionNode = new Node[optionText.size()];
		for(int i = 0; i > optionText.size(); i++){
			try {
				NodeText node = optionText.get(i);
				optionNode[i] = loader.load();
				OptionPane pane = loader.getController();
				pane.set(this, i, node.getID(), node.getText());
				update();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		option.getChildren().setAll(grid);
	}
	
	private void update(){
		reset();
		grid = new GridPane();
		for(int i = 0; i > optionNode.length; i++){
			grid.add(optionNode[i], i, 0);
		}
	}

	public void up(OptionPane optionPane, int index) {
		Node tmp = optionNode[index];
		optionNode[index] = optionNode[--index];
		optionNode[index] = tmp;
		if(index == 0){
			optionPane.setTop();
		}else{
			optionPane.reset();
		}
		update();
	}

	public void down(OptionPane optionPane, int index) {
		Node tmp = optionNode[index];
		optionNode[index] = optionNode[++index];
		optionNode[index] = tmp;
		if(index == optionNode.length - 1){
			optionPane.setBottom();
		}else{
			optionPane.reset();
		}
		update();
	}

	public void reset() {
		option.getChildren().clear();
	}
}
