package cpp.TextAdvEditor.Model;

import java.util.ArrayList;

public class Story {

	private String strName;
	private ArrayList<Text> chapter;
	
	public void setCHName(String chName){
		this.strName = chName;
	}
	
	public String getCHName(){
		return strName;
	}
	
	public void setTree(ArrayList<Text> newTree){
		chapter = newTree;
	}
	
	public ArrayList<Text> getTree(){
		return chapter;
	}
	
	public int numOfNode(){
		return chapter.size();
	}
}
