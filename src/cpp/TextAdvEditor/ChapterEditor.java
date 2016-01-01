package cpp.TextAdvEditor;

import java.util.ArrayList;
import java.util.Random;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import cpp.TextAdvEditor.Model.*;

public class ChapterEditor{
	
	private int chapterID;
	private ArrayList<Text> noParent;
	private ArrayList<Text>	noChild;
	private ArrayList<Text>	bookmark;
	private ArrayList<Text> tree;
	private Text currentNode;
	private Text selectedNode;
	private Stack memory;
	Random keyGen;
	
	private StringProperty story;
	private Text start;
	
	public ChapterEditor(int chapterID, ArrayList<Text> tree){
		this.chapterID = chapterID;
		memory = new Stack(20);
		keyGen = new Random();
		this.tree = tree;
		currentNode = null;
		selectedNode = null;
		bookmark = new ArrayList<Text>();
		noParent = new ArrayList<Text>();
		noChild = new ArrayList<Text>();
		story = new SimpleStringProperty();
		updateText();
	}
		
	public int connect(int id){
		return addChild(searchTree(id));
	}
		
	public void updateText(){
		if(currentNode != null){
			story.setValue(currentNode.getText());
		}
	}
	
	public StringProperty getStory(){
		return story;
	}
	
	public void delete(){
		
		ArrayList<Text> child = selectedNode.getChild();
		for(int i = 0; i < child.size(); i++){
			child.get(i).removeParent(selectedNode);
			if(child.get(i).getParentSize() == 0){
				noParent.add(child.get(i));
			}
		}
		
		ArrayList<Text> parent = selectedNode.getChild();
		for(int i = 0; i < parent.size(); i++){
			parent.get(i).removeChild(selectedNode);
			if(parent.get(i).getChildSize() == 0){
				noChild.add(parent.get(i));
			}
		}		
		
		selectedNode.setChild(null);
		selectedNode.setParent(null);
		
		tree.remove(selectedNode);
		selectedNode = null;
		System.out.println(tree.size());
	}
	
	public int disconnect(int key){
		Text node = searchTree(key);
		selectedNode.removeChild(node);
		if(selectedNode.getChildSize() == 0 && !noChild.contains(selectedNode)){
			noChild.add(selectedNode);
		}
		node.removeParent(selectedNode);
		if(node.getParentSize() == 0 && !noParent.contains(node)){
			noParent.add(node);
		}
		return node.getKey();
	}
	
	public void addBookmark(){
		bookmark.add(selectedNode);
	}
	
	public void removeBookmark(){
		bookmark.remove(selectedNode);
	}
		
	public boolean next(int input){
		if(input >= currentNode.getChildSize())return false;
		
		currentNode = currentNode.getChild(input);
		memory.push(currentNode);
		updateText();
		
		return true;
	}
	
	public boolean back(){
		
		Text back = memory.pop();
		if(back != null){
			currentNode = back;
		}
		updateText();
		
		return true;
	}


	public int createText() {
		Text newText = new Text(createKey());
		noParent.add(newText);
		noChild.add(newText);
		tree.add(newText);
		selectedNode = newText;
		return newText.getKey();
	}
	
	private int createKey(){
		return keyGen.nextInt(10000);
	}
	
	public int getKey(){
		if(currentNode == null) return -1;
		return currentNode.getKey();
	}
	
	public int addChild(Text text){
		if(text != null){
			
			text.addParent(selectedNode);
			if(noParent.contains(text)){
				noParent.remove(text);
			}
			
			selectedNode.addChild(text);
			if(noChild.contains(selectedNode)){
				noChild.remove(selectedNode);
			}	
			
			return text.getKey();
		}
		return -1;
	}
	
	private Text searchTree(int key){
		if(key >= 0){
			for(int i = 0; i < tree.size(); i++){
				if(tree.get(i).getKey() == key)return tree.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<NodeText> getBookMark() {
		return createList(bookmark);
	}

	public ArrayList<NodeText> getNoChildNode() {
		return createList(noChild);
	}

	public ArrayList<NodeText> getNoParentNode() {
		return createList(noParent);
	}
	
	public ArrayList<NodeText> getAllNode() {
		return createList(tree);
	}
	
	private ArrayList<NodeText> createList(ArrayList<Text> list){
		ArrayList<NodeText> textList = new ArrayList<NodeText>();
		for(Text text : list){
			textList.add(new NodeText(text.getTitle(),text.getKey()));
		}
		return textList;
	}

	public boolean hasNodes() {
		return tree.size() > 0;
	}

	public int currentKey() {
		if(currentNode == null) return -1;
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

	public void load(int chapterID, Text currentNode,
			ArrayList<Text> noParent, ArrayList<Text> noChild,
			ArrayList<Text> bookmark) {
		this.chapterID = chapterID;
		this.currentNode = currentNode;
		this.noParent = noParent;
		this.noChild = noChild;
		this.bookmark = bookmark;
		updateText();
	}

	public int getCHID() {
		return chapterID;
	}

	public Text saveCurrent() {
		return currentNode;
	}

	public ArrayList<Text> saveNoParent() {
		return noParent;
	}

	public ArrayList<Text> saveNoChild() {
		return noChild;
	}

	public ArrayList<Text> saveBookmark() {
		return bookmark;
	}

	public void loadTree(ArrayList<Text> tree, Text text) {
		this.start = text;
		this.tree = tree;
	}

	public boolean isChild(int key) {
		Text text = searchTree(key);
		return selectedNode.isChild(text);
	}
	
	public boolean isEmpty() {
		if(!selectedNode.getTitle().equals("")) return false;
		if(!selectedNode.getText().equals("")) return false;
		if(!selectedNode.getOptText().equals("")) return false;
		return true;
	}

	public boolean currentBookmark() {
		return bookmark.contains(selectedNode);
	}

	public boolean isNull() {
		return selectedNode == null;
	}
	
	public int getSelectedKey() {
		if(selectedNode != null){
			return selectedNode.getKey();
		}
		return -1;
	}
	
	public int createStart(){
		start = searchTree(createText());
		return start.getKey();
	}
	
	public void setStart() {
		start = selectedNode;
	}
	
	public Text getStart() {
		return start;
	}
	
	class Stack{
		
		Text[] stack;
		int size;
		int max;
		int pointer;
		
		public Stack(int max){
			stack = new Text[max];
			this.max = max;
			size = 0;
			pointer = 0;
		}
		
		public void push(Text text){
			pointer = (pointer + 1)%stack.length;
			stack[pointer] = text;
			if(size < max){
				size++;
			}else{
				size = max;
			}
		}
		
		public Text pop(){
			Text pop = stack[pointer];
			stack[pointer] = null;
			if(pointer == 0){
				pointer = max - 1;
			}else{
				pointer--;
			}
			if(size > 0){
				size--;
			}else{
				size = 0;
			}
			return pop;
		}
	}

	public boolean validate() {
		return ((noParent.size()==1 && noParent.contains(start)) 
				|| (noParent.size()==0 && start!=null));
	}
}
