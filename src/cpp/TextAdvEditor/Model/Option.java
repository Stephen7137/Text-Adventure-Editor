package cpp.TextAdvEditor.Model;

import java.util.ArrayList;

public class Option extends Node {

	private ArrayList<String> oText;
	
	public Option(String title, String text, int nodeNum) {
		super(title,text, nodeNum, 1);
	}
	
	public void setoText(ArrayList<String> oText){
		this.oText = oText;
	}
	
	public ArrayList<String> getoText(){
		return oText;
	}
	
	public void addChildNode(Node child, int num){
		if(this.child.contains(child)){
			this.child.remove(child);
		}
		this.child.add(num,child);
	}
	
	public int setupChild(){
		return oText.size();
	}
}
