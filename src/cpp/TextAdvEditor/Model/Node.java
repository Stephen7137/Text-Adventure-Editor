package cpp.TextAdvEditor.Model;

import java.util.ArrayList;

public abstract class Node {
	
	private String title;
	private String text;
	private int nodeType;
	private int nodeNum;
	private ArrayList<Node> parent;
	protected ArrayList<Node> child;
	
	public Node(String title, String text,int nodeNum,int nodeType){
		this.title = title;
		this.text=text;
		this.nodeNum=nodeNum;
		this.nodeType=nodeType;
		parent = new ArrayList<Node>();
		child = new ArrayList<Node>();
	}
	
	public int getNodeType(){
		return nodeType;
	}
	
	public int getNodeNum(){
		return nodeNum;
	}
	
	public String getText(){
		return text;		
	}
	
	public void addChildNode(Node child){
		if(!this.child.contains(child)){
			this.child.add(child);
		}
	}
	
	public void addParentNode(Node parent){
		if(!this.parent.contains(parent)){
			this.parent.add(parent);
		}
	}
	
	public void removeChildNode(Node child){
		if(this.child.contains(child)){
			this.child.remove(child);
		}
	}
	
	public void removeParentNode(Node parent){
		if(this.parent.contains(parent)){
			this.parent.remove(parent);
		}
	}
	
	public Node getNextNode(int i){
		return child.get(i);
	}
	
	public boolean hasChild(){
		return !child.isEmpty();
	}
	
	public boolean hasParent(){
		return !parent.isEmpty();
	}

	public int childSize() {
		return child.size();
	}
	
	public int parentSize() {
		return parent.size();
	}

	public Node getChild(int i) {
		return child.get(i);
	}
	
	public Node getParent(int i) {
		return parent.get(i);
	}
	
	public void clearParent(){
		parent.clear();
	}
	
	public void clearChild(){
		child.clear();
	}
	
	public String getTitle(){
		return title;
	}
	
}
