package cpp.TextAdvEditor.Model;

public class NodeText {
	
	private String text;
	private int ID;
	
	public NodeText(String text,int  ID){
		this.text = text;
		this.ID = ID;
	}
	
	public String getText(){
		return text;
	}
	
	public void setText(String text){
		this.text = text;
	}
	
	public int getID(){
		return ID;
	}
}
