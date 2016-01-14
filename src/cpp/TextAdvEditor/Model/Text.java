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
		return child==null? 0:child.length;
	}
	
	public int getParentSize(){
		return parent.size();
	}
	
	public void addChild(Text text){
		if(child != null){
			Text[] newChild = new Text[child.length+1];
			for(int i = 0; i < child.length; i++){
				newChild[i] = child[i];
			}
			newChild[newChild.length-1] = text;
			child = newChild;
		}else{
			child = new Text[1];
			child[0] = text;
		}
	}
	
	public void addParent(Text parent){
		this.parent.add(parent);
	}

	public Text popChild(int i) {
		Text text = child[i];
		removeChild(text);
		return text;
	}
	
	public boolean isChild(Text text){
		if(child != null){
			for(int i = 0; i < child.length; i++){
				if(child[i] == text) return true;
			}
		}
		return false;
	}

	public void removeChild(Text text) {
		Text[] newChild = new Text[child.length-1];
		int j = 0;
		for(int i = 0; i < child.length; i++){
			if(child[i] == text) i++;
			newChild[j++] = child[i];
		}
		child = newChild;
	}

	public void removeParent(Text parent) {
		this.parent.remove(parent);
	}
	
	public boolean hasChildren(){
		if(child == null) return false;
		return child.length > 1;
	}
}
