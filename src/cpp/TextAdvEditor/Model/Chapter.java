package cpp.TextAdvEditor.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Chapter implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8684899390329736855L;
	private String chName;
	private int ID;
	private ArrayList<Text> tree;
	
	public Chapter(int ID){
		tree = new ArrayList<Text>();
		this.ID = ID;
		tree.add(new Text(ID));
	}
	
	public void setCHName(String chName){
		this.chName = chName;
	}
	
	public String getCHName(){
		return chName;
	}
	
	public int getID(){
		return ID;
	}
	
	public void setTree(ArrayList<Text> newTree){
		tree = newTree;
	}
	
	public ArrayList<Text> getTree(){
		return tree;
	}
	
	public int numOfNode(){
		return tree.size();
	}
}
