package cpp.TextAdvEditor;

import java.io.Serializable;
import java.util.ArrayList;

import cpp.TextAdvEditor.Model.Story;
import cpp.TextAdvEditor.Model.Text;

public class SaveProject implements Serializable{
	
	protected static final long serialVersionUID = 232323;
	private ArrayList<Text> bookmark;
	private ArrayList<Text> open;
	private ArrayList<Text> loose;
	private Story story;
	
	public SaveProject(ChapterEditor editor){
		//TODO
	}
}
