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
		child = txtChild.child;
		title = txtChild.text;
		text = txtChild.text;
		for(int i = 0; i < txtChild.parent.size(); i++){
			txtChild.parent.get(i).parent.remove(txtChild);
			txtChild.parent.get(i).parent.add(this);
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
