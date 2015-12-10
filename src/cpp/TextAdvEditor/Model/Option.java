package cpp.TextAdvEditor.Model;

public class Option extends Text {

	public Option(int key) {
		super(key);
	}

	private String optText;
	
	public void setOptText(String optText){
		this.optText = optText;
	}
	
	public String getOptText(){
		return optText;
	}
}
