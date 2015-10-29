package cpp.TextAdvEditor.Model;

public class Text extends Node {

	private Node childNode;
	
	public Text(String title, String text, int nodeNum) {
		super(title,text, nodeNum, 0);
	}

	public void setChildNode(Node childNode){
		this.childNode=childNode;
	}
	
	@Override
	public Node getNextNode(int i) {
		return childNode;
	}
}
