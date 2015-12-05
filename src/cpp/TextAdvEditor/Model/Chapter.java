package cpp.TextAdvEditor.Model;

import java.util.ArrayList;

public class Chapter {

	private String chName;
	private ArrayList<Text> tree;
	
	public void setCHName(String chName){
		this.chName = chName;
	}
	
	public String getCHName(){
		return chName;
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
