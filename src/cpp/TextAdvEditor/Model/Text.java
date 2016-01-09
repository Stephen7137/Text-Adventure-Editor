package cpp.TextAdvEditor.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Text implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6064370901268149364L;
	private String title;
	private String text;
	private String optText;
	
	private ArrayList<Text> parent;
	private Text[] child;
	private int key;
		
	public Text(int key){
		title = "";
		text = "";
		optText = "";
		this.key = key;
		parent = new ArrayList<Text>();
	}
	
	public int getKey(){
		return key;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public String getText(){
		return text;
	}

	public void setOptText(String optText){
		this.optText = optText;
	}
	
	public String getOptText(){
		return optText;
	}
	
	public void setParent(ArrayList<Text> parent){
		this.parent = parent;
	}
	
	public ArrayList<Text> getParent(){
		return parent;
	}
	
	public void setChild(Text[] child){
		this.child = child;
	}
	
	public Text[] getChild(){
		return child;
	}
	
	public Text getChild(int i){
		return child[i];
	}
	
	public int getChildSize(){
		return child.length;
	}
	
	public int getParentSize(){
		return parent.size();
	}
	
	public void addChild(Text text){
		Text[] newChild = new Text[child.length+1];
		for(int i = 0; i < child.length; i++){
			newChild[i] = child[i];
		}
		newChild[newChild.length-1] = text;
		child = newChild;
	}
	
	public void addParent(Text parent){
		this.parent.add(parent);
	}

	public Text popChild(int i) {
		return child.remove(i);
	}
	
	public boolean isChild(Text text){
		for(int i = 0; i < child.length; i++){
			if(child[i] == text) return true;
		}
		return false;
	}

	public void removeChild(Text child) {
		this.child.remove(child);
	}

	public void removeParent(Text parent) {
		this.parent.remove(parent);
	}

	public void updateChild(NodeText[] oNode) {
		Text[] newChild = Text[child.size()];
		for(NodeText text: oNode){
			
		}
		child = newChild;
	}
}
