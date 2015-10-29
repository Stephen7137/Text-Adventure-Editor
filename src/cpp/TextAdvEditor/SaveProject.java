package cpp.TextAdvEditor;

import java.io.Serializable;
import java.util.ArrayList;
import cpp.TextAdvEditor.Model.Node;
import cpp.TextAdvEditor.Model.WriteChapter;

public class SaveProject implements Serializable{
	
	protected static final long serialVersionUID = 232323;
	protected ArrayList<Node> bookmark;
	protected ArrayList<Node> open;
	protected ArrayList<Node> loose;
	protected Node selected0;
	protected Node selected1;
	protected WriteChapter chapter;
	
	public SaveProject(ChapterEditor editor){
		bookmark = editor.bookmark;
		chapter = editor.chapter;
		loose = editor.loose;
		open = editor.open;
		selected0 = editor.selected0;
		selected1 = editor.selected1;
	}
}
