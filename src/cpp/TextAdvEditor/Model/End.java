package cpp.TextAdvEditor.Model;
public class End extends Node {

	private String nextFile;
	
	public End(String title, String text, String nextFile, int nodeNum) {
		super(title,text, nodeNum, 2);
		if(nextFile.equals("end")){
			this.nextFile=null;
		}else{
			this.nextFile=nextFile;
		}
	}
	
	public String getNextFile(){
		return nextFile;
	}

	@Override
	public Node getNextNode(int i) {
		return null;
	}

}
