package cpp.TextAdvEditor;

import cpp.TextAdvEditor.Model.Chapter;
import cpp.TextAdvEditor.Model.End;
import cpp.TextAdvEditor.Model.Node;

public class Engine {

	private Chapter chapter;
	private Node lastNode;
	private Node currentNode;
	Boolean runGame;
	
	public Engine() {
		lastNode = null;
		currentNode = null;
		runGame = true;
	}
	
	public void play(String FileName) {
		loadChapter(FileName);
		currentNode = chapter.getCurrentNode();
		lastNode = null;
		
		while(runGame){
			switch(currentNode.getNodeType()){
			case 0:
				setNextNode(0);
				break;
			case 1:
				//Option option = (Option)currentNode;
				//setNextNode(option.getoText());
				break;
			case 2:
				endChapter();
			}
		}
	}
		
	public void setNextNode(int i){
		chapter.setNextNode(i);
		lastNode = currentNode;
		currentNode = chapter.getCurrentNode();
	}
	
	private void endChapter(){
		End end = (End) lastNode;
		String fileName = end.getNextFile();
		if(fileName != null){
			loadChapter(fileName);
			currentNode = chapter.getCurrentNode();
			lastNode = null;
		}else{
			runGame = false;
		}
	}
	
	private void loadChapter(String name){
		chapter = new Chapter(name);
	}

}
