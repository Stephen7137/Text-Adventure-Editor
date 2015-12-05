package cpp.TextAdvEditor.Model;

import java.util.ArrayList;

public class Text {

	private String title;
	private String text;
	private ArrayList<Text> parent;
	private ArrayList<Text> child;
	
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
	
	public void setParent(ArrayList<Text> parent){
		this.parent = parent;
	}
	
	public ArrayList<Text> getParent(){
		return parent;
	}
	
	public void setChild(ArrayList<Text> child){
		this.child = child;
	}
	
	public ArrayList<Text> getChild(){
		return child;
	}
	
	public Text getChild(int i){
		return child.get(i);
	}
	
	public int getChildSize(){
		return child.size();
	}
}
