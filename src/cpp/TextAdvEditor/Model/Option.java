package cpp.TextAdvEditor.Model;

public class Option extends Text {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5350051473876561916L;

	public Option(int key) {
		super(key);
	}

	public Option(Text txtChild) {
		super(txtChild.getKey());
		parent = txtChild.parent;
		child = txtChild.child;
		title = txtChild.text;
		text = txtChild.text;
		for(int i = 0; i < parent.size(); i++){
			parent.get(i).child.remove(txtChild);
			parent.get(i).child.add(this);
		}
		
		for(int i = 0; i < child.size(); i++){
			child.get(i).parent.remove(txtChild);
			child.get(i).parent.add(this);
		}
		
		txtChild.child = null;
		txtChild.parent = null;
	}

	private String optText;
	
	public void setOptText(String optText){
		this.optText = optText;
	}
	
	public String getOptText(){
		return optText;
	}
}
