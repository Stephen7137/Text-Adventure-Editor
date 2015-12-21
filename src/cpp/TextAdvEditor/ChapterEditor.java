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
	private Text currentNode;
	private Text selectedNode;
	Random keyGen;
	
	private StringProperty story;
	
	public ChapterEditor(){
		keyGen = new Random();
		tree = new ArrayList<Text>();
		currentNode = createText();
		tree.add(currentNode);
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
		
	public void updateText(){
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
		Text text = new Text(createKey());
		return text;
	}
	
	private int createKey(){
		return keyGen.nextInt(10000);
	}
	
	public int getKey(){
		return currentNode.getKey();
	}
	
	public int addChild(int i){
		Text pText = searchTree(i);
		Text nwText = createText();
		int childNum = pText.getChildSize();
		nwText.addParent(pText);
		if( childNum == 0){
			pText.addChild(nwText);
			tree.add(nwText);
			}else if(childNum == 1){
			Text txtChild = pText.popChild(0);
			tree.remove(txtChild);
			pText.addChild(new Option(txtChild));
			pText.addChild(new Option(nwText));
			tree.add(pText.getChild(0));
			tree.add(pText.getChild(1));
		}else{
			pText.addChild(new Option(nwText));
			tree.add(pText.getChild(pText.getChildSize() - 1));
		}
		return nwText.getKey();
	}
	
	private Text searchTree(int key){
		for(int i = 0; i < tree.size(); i++){
			if(tree.get(i).getKey() == key)return tree.get(i);
		}
		return null;
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

	public int currentKey() {
		return currentNode.getKey();
	}

	public void setSelected(int key) {
		selectedNode = searchTree(key);
	}

	public Text getSelected() {
		return selectedNode;
	}

	public void setCurrent(int selected) {
		currentNode = selectedNode;
		updateText();
	}

	public boolean isCurrent() {
		return currentNode == selectedNode;
	}

	public boolean curHasChildren() {
		return currentNode.getChildSize()>1;
	}
}
