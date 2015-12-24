package cpp.TextAdvEditor.Model;
public class End extends Text {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7022159750827172354L;

	public End(int key) {
		super(key);
	}

	private String chName;
	
	public void setNextCh(String chName){
		this.chName = chName;
	}
	
	public String getNextFile(){
		return chName;
	}
}
