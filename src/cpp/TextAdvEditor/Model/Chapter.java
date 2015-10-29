package cpp.TextAdvEditor.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Chapter {

	private String fileName;
	private Node[] nodes;
	private Node currentNode;
	private int numNode;
	
	public Chapter(String fileName){
		this.fileName=fileName;
		
		File file = new File(fileName);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			createChapter(reader);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(fileName + " is currupted.");
			e.printStackTrace();
		}
		currentNode = nodes[0];
	}
	
	public void setNextNode(int i){
		Node node = currentNode.getNextNode(i);
		currentNode = node;
	}
	
	public Node getCurrentNode(){
		return currentNode;
	}
	
	public void setCurrentNode(Node node){
		currentNode = node;
	}
	
	private void createChapter(BufferedReader reader) throws IOException{
		
		String str = reader.readLine();
		if(str!=null){
			nodes = new Node[Integer.parseInt(str)];
			str = reader.readLine();
			if(nodes.length>0){
				numNode = 0;
				while(str!=null){
					String tmpStr[] = str.split(":");
					switch(tmpStr[1]){
					case "s":
						addStoryNode(reader,Integer.parseInt(tmpStr[2]));
						break;
					case "o":
						addOptNode(reader,Integer.parseInt(tmpStr[2]),Integer.parseInt(tmpStr[3]));
						break;
					case "f":
						addEndNode(reader,Integer.parseInt(tmpStr[2]));
					}
					
					str = reader.readLine();
				}
				setChildNodes();
			}			
		}
	}

	private void setChildNodes() {
		for(int i = 0 ; i < nodes.length; i++){
			switch(nodes[i].getNodeType()){
			case 0:
				Text text = (Text) nodes[i];
				text.setChildNode(nodes[i+1]);
				break;
			case 1:
			//	Option option = (Option) nodes[i];
			//	int[] num = option.getchildNodesNum();
			//	Node[] node = new Node[num.length];
			//	for(int j = 0; j < num.length; j++){
			//		node[j] = nodes[num[j]];
			//	}
			//	option.setChildNode(node);
			}
		}
	}

	private void addStoryNode(BufferedReader reader, int num) throws IOException {
		String[] str = startingText(reader,num);
		//nodes[numNode++] = new Text(str, numNode);
	}
	
	private void addOptNode(BufferedReader reader, int tNum, int num) throws IOException {
		String[] str = startingText(reader,tNum);
		//Option option = new Option(str, numNode);
		str = new String[num];
		for(int i = 0; i < num ; i++){
			str[i] = reader.readLine();
		}
		//option.setoText(str);
		
		int[] oNum = new int[3];
		for(int i = 0; i < num ; i++){
			oNum[i] = Integer.parseInt(reader.readLine());
		}
		//option.setchildNodesNum(oNum);
		
		//nodes[numNode++] = option;
	}
		
	private void addEndNode(BufferedReader reader, int num) throws IOException{
		String[] str = startingText(reader,num);
		//nodes[numNode++] = new End(str, reader.readLine(), numNode);
	}

	private String[] startingText(BufferedReader reader, int num) throws IOException{
		String[] str = new String[num];
		for(int i = 0; i < num ; i++){
			str[i] = reader.readLine();
		}
		return str;
	}
	
}
