package cpp.TextAdvEditor;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import cpp.TextAdvEditor.Model.*;

public class ChapterEditor{
	
	private Chapter chapter;
	private ArrayList<Text> loose;
	private ArrayList<Text>	unFinished;
	private ArrayList<Text>	bookmark;
	private ArrayList<Text> tree;
	private Text currentNode;
	private Text selectedNode;
	
	private StringProperty story;
	
	public ChapterEditor(Chapter chapter){
		tree = chapter.getTree();
		if(tree == null){
			tree = new ArrayList<Text>();
			tree.add(new Text());
		}
		bookmark = new ArrayList<Text>();
		unFinished = new ArrayList<Text>();
		loose = new ArrayList<Text>();
		story = new SimpleStringProperty();
		updateText();
	}
		
	public void connect(){
		//TODO
	}
		
	protected void updateText(){
		if(currentNode != null){
			story.setValue(currentNode.getText());
			if(currentNode.getChildSize() > 1){
				ArrayList<Text> children = currentNode.getChild();
				for(int i = 0; i < children.size(); i++ ){
					story.concat("\n" + (i+1) + ") " + (Option)children.get(i));
				}
			}
		}
		
	}
	
	public StringProperty getStory(){
		return story;
	}
	
	public void removeChild(){
		//TODO
	}
	
	public void removeParent(){
		//TODO
	}
	
	public void delete(){
		deleteFromList(selectedNode, selectedNode.getChild());
		deleteFromList(selectedNode, selectedNode.getParent());
		
		selectedNode.setChild(null);
		selectedNode.setParent(null);
	}
	
	private void deleteFromList(Text node, ArrayList<Text> list){
		for(int i = 0; i < list.size(); i++){
			//TODO
		}
	}
	
	public void disconnect(){
		//TODO
	}
	
	public void addBookmark(){
		bookmark.add(selectedNode);
	}
	
	public boolean next(int input){
		if(input >= currentNode.getChildSize())return false;
		
		currentNode = currentNode.getChild(input);
		updateText();
		
		return true;
	}

	public Text createText() {
		Text text = new Text();
		tree.add(text);
		return text;
	}
	
	public void addChild(){
		Text nwText = new Text();
		int childNum = selectedNode.getChildSize();
		if( childNum == 0){
			selectedNode.addChild(nwText);
		}else if(childNum == 1){
			//TODO
			Text txtChild = selectedNode.getChild(0);
			selectedNode.addChild((Option)txtChild);
			selectedNode.addChild((Option)nwText);
		}else{
			selectedNode.addChild((Option)nwText);
		}
		nwText.addParent(selectedNode);
	}

	public void createOption(String title, String text,
			ArrayList<String> optionText) {
		//TODO
	}

	public void createEnd(String title, String text, String file) {
		//TODO
	}
	
	public ObservableList<String> getBookMark() {
		return createList(bookmark);
	}

	public ObservableList<String> getLooseNode() {
		return createList(loose);
	}

	public ObservableList<String> getunfinishedNode() {
		return createList(unFinished);
	}
	
	private ObservableList<String> createList(ArrayList<Text> list){
		ObservableList<String> options = FXCollections.observableArrayList();
		for(int i = 0; i < list.size(); i++){
			options.add(list.get(i).getTitle());
		}
		return options;
	}
	
	public ObservableList<String> getBookMark(String search) {
		return compList(bookmark,search);
	}

	public ObservableList<String> getLooseNode(String search) {
		return compList(loose,search);
	}

	public ObservableList<String> getOpenNode(String search) {
		return compList(unFinished,search);
	}
	
	private ObservableList<String> compList(ArrayList<Text> list, String search){
		ObservableList<String> options = FXCollections.observableArrayList();
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getTitle().toLowerCase().contains(search.toLowerCase())){
				options.add(list.get(i).getTitle());
			}
		}
		return options;
	}

	public void setBookMark(String selected) {
		currentNode = search(bookmark,selected);
	}

	public void setLooseNode(String selected) {
		currentNode = search(loose,selected);
	}

	public void setOpenNode(String selected) {
		currentNode = search(unFinished,selected);
	}
	
	private Text search(ArrayList<Text> list, String search){
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getTitle().equals(search)) return list.get(i);
		}
		return null;
	}
}
