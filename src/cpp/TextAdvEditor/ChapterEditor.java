package cpp.TextAdvEditor;

import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import cpp.TextAdvEditor.Model.*;

public class ChapterEditor{
	
	/**
	 * 
	 */
	protected ArrayList<Node> bookmark;
	protected ArrayList<Node> open;
	protected ArrayList<Node> loose;
	protected Node selected0;
	protected Node selected1;
	protected WriteChapter chapter;
	private StringProperty main;
	private StringProperty child;
	
	public ChapterEditor(String title){
		chapter = new WriteChapter(title);
		bookmark = new ArrayList<Node>();
		open = new ArrayList<Node>();
		loose = new ArrayList<Node>();
		main = new SimpleStringProperty();
		child = new SimpleStringProperty();
		updateNodeText();
	}
		
	public void connect(){
		if(selected0 != null && selected1 != null){
			selected0.addChildNode(selected1);
			selected1.addParentNode(selected0);
		}	
	}
	
	public void connect(int num){
		if(selected0 != null || selected1 != null){
			Option option = (Option) selected0;
			option.addChildNode(selected1,num);
			selected1.addParentNode(selected0);
		}
	}
	
	protected void updateNodeText(){
		main.setValue(selected0 != null ? toString(selected0) : null);
		
		if(selected1 == null){
			if(selected0 != null){
				String text = "";
				for(int i = 0; i < selected0.childSize(); i++){
					text = text + toString(selected0.getChild(i)) + "\n\n";
				}
				child.setValue(text);
			}else{
				child.setValue(null);
			}
		}else{
			child.setValue(toString(selected1));
		}
	}
	
	public StringProperty getMainText(){
		return main;
	}
	
	public StringProperty getChildText(){
		return child;
	}
	
	private String toString(Node node){
		if(node == null) return "";
		String text = node.getText();
		if(node.getNodeType()==1){
			Option option = (Option) node;
			ArrayList<String> oText = option.getoText();
			for(int i = 0; i < oText.size(); i++ ){
				text = text + "\n" + (i+1) + ") " + oText.get(i);
			}
		}
		return text;		
	}

	public void deleteChild(){
		delete(selected1);
		selected1=null;
	}
	
	public void deleteParent(){
		delete(selected0);
		selected0=null;
	}
	
	private void delete(Node node){
		if(node != null){
			if(node.hasChild()){
				for(int i = 0; i < node.childSize(); i++){
					loose.add(node.getChild(i));
				}
				node.clearChild();
			}
			if(node.hasParent()){
				for(int i = 0; i < node.parentSize(); i++){
					node.getParent(i).removeChildNode(node);;
				}
				node.clearParent();
			}
		}	
	}
	
	public void disconnect(){
		if(selected0 != null || selected1 != null){
			selected0.removeChildNode(selected1);
			selected1.removeParentNode(selected0);
			if(!selected1.hasParent()) loose.add(selected1);
		}	
	}
	
	public void addBookmark(){
		bookmark.add(selected0);
	}
	
	public boolean next(String input){
		if(selected0 != null){
			if(selected0.hasChild()){
				if(selected0.getNodeType()==1){
					int num = Integer.parseInt(input);
					selected0 = selected0.getChild(num);
				}else{
					selected0 = selected0.getChild(0);
					selected1 = null;
				}	
			}
		}
		updateNodeText();
		return true;
	}

	public void createText(String title, String text) {
		Node node = chapter.createText(title, text);
		validate(node);
		open.add(node);
	}

	public void createOption(String title, String text,
			ArrayList<String> optionText) {
		Node node = chapter.createOption(title, text,optionText);
		validate(node);
		open.add(node);
	}

	public void createEnd(String title, String text, String file) {
		Node node  = chapter.createEnd(title, text, file);
		validate(node);		
		open.add(node);
	}
	
	private void validate(Node node){
		if(selected0 == null){
			selected0 = node;
		}else{
			selected1 = node;
		}
		updateNodeText();
	}
	
	public int getNodeType(){
		return selected0.getNodeType();
	}
	
	public int getChildSize(){
		return selected0.childSize();
	}

	public ObservableList<String> getBookMark() {
		return createList(bookmark);
	}

	public ObservableList<String> getLooseNode() {
		return createList(loose);
	}

	public ObservableList<String> getOpenNode() {
		return createList(open);
	}
	
	private ObservableList<String> createList(ArrayList<Node> list){
		ObservableList<String> options = FXCollections.observableArrayList();
		for(int i = 0; i < list.size(); i++){
			options.add(list.get(i).getTitle());
		}
		return options;
	}
	
	public boolean validateTitle(String title){
		ArrayList<Node> node = chapter.getNodes();
		for(int i = 0; i < node.size(); i++){
			if(node.get(i).getTitle().toLowerCase().contains(title.toLowerCase())){
				return false;
			}
		}
		return true;
	}
	
	public ObservableList<String> getBookMark(String search) {
		return compList(bookmark,search);
	}

	public ObservableList<String> getLooseNode(String search) {
		return compList(loose,search);
	}

	public ObservableList<String> getOpenNode(String search) {
		return compList(open,search);
	}
	
	private ObservableList<String> compList(ArrayList<Node> list, String search){
		ObservableList<String> options = FXCollections.observableArrayList();
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getTitle().toLowerCase().contains(search.toLowerCase())){
				options.add(list.get(i).getTitle());
			}
		}
		return options;
	}

	public void setBookMark(String selected) {
		selected0 = search(bookmark,selected);
	}

	public void setLooseNode(String selected) {
		selected0 = search(loose,selected);
	}

	public void setOpenNode(String selected) {
		selected0 = search(open,selected);
	}
	
	private Node search(ArrayList<Node> list, String search){
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getTitle().equals(search)) return list.get(i);
		}
		return null;
	}
	
	public boolean isEmpty(){
		return chapter.getNodeNum() == 0;
	}
}
