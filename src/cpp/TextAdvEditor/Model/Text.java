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
	private ArrayList<Text> parent;
	private ArrayList<Text> child;
	private int key;
	private String optText;
		
	public Text(int key){
		this.key = key;
		parent = new ArrayList<Text>();
		child = new ArrayList<Text>();
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
	
	public void addChild(Text child){
		this.child.add(child);
	}
	
	public void addParent(Text parent){
		this.parent.add(parent);
	}

	public Text popChild(int i) {
		return child.remove(i);
	}
	
	public boolean isChild(Text child){
		return this.child.contains(child);
	}

	public void removeChild(Text child) {
		this.child.remove(child);
	}

	public void removeParent(Text parent) {
		this.parent.remove(parent);
	}

	public String[] getChldOText() {
		String[] oText = new String[child.size()];
		for(int i = 0; i < oText.length; i++){
			oText[i] = child.get(i).getOptText();
		}
		return null;
	}
}
