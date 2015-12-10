package cpp.TextAdvEditor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

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
	private Map<Integer,Text> lookup;
	private Text currentNode;
	private Text selectedNode;
	
	private StringProperty story;
	
	public ChapterEditor(){
		tree = new ArrayList<Text>();
		currentNode = createText();
		selectedNode = null;
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

	private Text createText() {
		Text text = new Text(getKey());
		tree.add(text);
		lookup.put(text.getKey(), text);
		return text;
	}
	
	public int getKey(){
		Random ranKey = new Random();
		int key = -1;
		do{
			key = ranKey.nextInt(10000);
		}while(lookup.containsKey(key));
		
		return key;
	}
	
	public int addChild(){
		Text nwText = createText();
		int childNum = selectedNode.getChildSize();
		if( childNum == 0){
			selectedNode.addChild(nwText);
		}else if(childNum == 1){
			//TODO
			Text txtChild = selectedNode.popChild(0);
			selectedNode.addChild((Option)txtChild);
			selectedNode.addChild((Option)nwText);
		}else{
			selectedNode.addChild((Option)nwText);
		}
		nwText.addParent(selectedNode);
		return nwText.getKey();
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
	
	public ObservableList<String> searchBookMark(String search) {
		return searchList(bookmark,search);
	}

	public ObservableList<String> searchLooseNode(String search) {
		return searchList(loose,search);
	}

	public ObservableList<String> searchOpenNode(String search) {
		return searchList(unFinished,search);
	}
	
	private ObservableList<String> searchList(ArrayList<Text> list, String search){
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

	public boolean hasNodes() {
		return tree.size() > 0;
	}
}
