package cpp.TextAdvEditor.Model;
public class End extends Text {

	private String chName;
	
	public void setNextCh(String chName){
		this.chName = chName;
	}
	
	public String getNextFile(){
		return chName;
	}
}
