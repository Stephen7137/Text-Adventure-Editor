package cpp.TextAdvEditor.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Story implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1561701032116116746L;
	private String strName;
	private ArrayList<Chapter> chapter;
	
	public Story(int ID){
		chapter = new ArrayList<Chapter>();
		chapter.add(new Chapter(ID));
	}
	
	public Chapter getSrtCh(){
		return chapter.get(0);
	}
	
	public void setCHName(String chName){
		this.strName = chName;
	}
	
	public String getCHName(){
		return strName;
	}
	
	public int numOfNode(){
		return chapter.size();
	}

	public Chapter getCH(int chapterID) {
		return search(chapterID);
		
	}
	
	private Chapter search(int chapterID){
		for(int i = 0; i < chapter.size(); i++){
			if(chapter.get(i).getID() == chapterID) return chapter.get(i);
		}
		return null;
	}
}
