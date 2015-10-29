package cpp.TextAdvEditor.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class WriteChapter implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fileName;
	private ArrayList<Node> nodes;
	private Node start;
	
	public WriteChapter(String fileName){
		this.fileName = fileName;
		nodes = new ArrayList<Node>();
		start = null;
	}
	
	public Node createText(String title, String text){
		Node node = new Text(title,text, -1);
		nodes.add(node);
		return node;
	}
	
	public Node createOption(String title, String text,ArrayList<String> Options){
		Option node = new Option(title, text, -1);
		node.setoText(Options);
		nodes.add(node);
		return node;
	}
	
	public Node createEnd(String title, String text, String fileName){
		Node node = new  End(title, text, fileName, -1);
		nodes.add(node);
		return node;
	}
	
	public void setStart(Node node){
		start = node;
	}
	
	public Node getStart(){
		return start;
	}
	
	public ArrayList<Node> getNodes(){
		return nodes;
	}
	
	public String getFileName(){
		return fileName;
	}
	
	public int getNodeNum(){
		return nodes.size();
	}
}
