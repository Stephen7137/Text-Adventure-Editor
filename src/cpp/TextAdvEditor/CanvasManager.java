package cpp.TextAdvEditor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import cpp.TextAdvEditor.Model.SimpPoint2D;
import cpp.TextAdvEditor.Model.Text;
import cpp.TextAdvEditor.Model.TreePoint;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class CanvasManager {
	
	private GraphicsContext gc;
	private int radious = 15;
	private int diameter;
	private int distance = 10;
	private int nodeDist = 100;
	private ArrayList<TreePoint> lookup;
	private Color background = Color.WHITE;
	private int chapterID;
	private Canvas canvas;
	private int selectedID;
	private TreePoint selected;
	private Point2D toolText;
	private SimpPoint2D connect;
	private final int offset = 22;
	private int toolW;
	private int toolH;
	
	public CanvasManager(int chID){
		chapterID = chID;
	}
	
	public void setCanvas(Canvas canvas, int key){
		diameter = radious*2;
		lookup = new ArrayList<TreePoint>();
		this.canvas = canvas;
		gc = canvas.getGraphicsContext2D();
		toolW = toolH = diameter + 8;
		toolText = new Point2D(toolW/2 + 2, toolH - radious - 2);
		resetSelected();
	}
	
	public void update(){
		gc.setFill(background);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		draw();
		gc.setFill(background);
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(3);
		gc.fillRect(2, 2, toolW, toolH);
		gc.strokeRect(2, 2, toolW, toolH);
		drawNode(toolText.getX(), toolText.getY(), Color.BLUE);
	}
	
	private boolean checkArea(double x, double y){
		for(int i = 0; i < lookup.size(); i++){
			if(lookup.get(i).distance(x, y) <= diameter + distance) return false;
		}
		return true;
	}
	
	private void draw(){
		
		for(int i = 0; i < lookup.size(); i++){
			drawChild(lookup.get(i), Color.BLACK);
		}
		
		if(selected != null){
			drawChild(selected, Color.YELLOW);
		}
		
		for(int i = 0; i < lookup.size(); i++){
			drawNode(lookup.get(i).getX(), lookup.get(i).getY(), Color.BLUE);
		}
		
		if(selected != null){
			gc.setStroke(Color.ORANGE);
			gc.setLineWidth(5);
			gc.strokeOval(selected.getX() - radious, selected.getY() - radious,
					diameter, diameter);
			gc.setFill(Color.BLACK);
			gc.fillOval(connect.getX(), connect.getY(), distance, distance);
		}
	}
	
	private void drawNode(double x, double y, Paint color){
		gc.setLineWidth(5);
		gc.setFill(color);
		gc.fillOval(x - radious, y - radious,
				diameter, diameter);
		gc.setLineWidth(2);
		gc.setStroke(Color.BLACK);
		gc.strokeOval(x - radious, y - radious,
				diameter, diameter);
	}
	
	private void drawChild(TreePoint point, Paint color){
		gc.setLineWidth(5);
		gc.setStroke(color);
		for(int j = 0; j < point.childsize(); j++){
			TreePoint point1 = point.getChild(j);
			gc.strokeLine(point.getX(), point.getY(), 
					point1.getX(), point1.getY());
		}	
	}
	
	public int onNode(double x, double y, boolean selected) {
		for(int i = 0; i < lookup.size(); i++){
			if(lookup.get(i).distance(x, y) <= radious){
				if(selected){
					setSelected(lookup.get(i));
				}
				update();
				return lookup.get(i).getID();
			}
		}
		return -1;
	}

	public int getSelected() {
		return selectedID;
	}

	public void addChild(int key) {
		if(key >= 0){
			TreePoint cNode = new TreePoint(0,0,key);
			selected.addChild(cNode);
			lookup.add(cNode);
			cNode.addParent(selected);
			if(!selected.childEmpty()){
				int n = selected.childsize();
				int y = (int)selected.getY() - ((n-1)*diameter + distance*(n-1))/2;
				int x = (int)selected.getX() + nodeDist;
				for(int i = 0; i < n; i++){
					selected.getChild().get(i).setXY(x,y);
					y+= (diameter + distance);
				}
			}
			update();
		}
	}

	public int getID() {
		return chapterID;
	}

	public ArrayList<TreePoint> saveCanvas() {
		return lookup;
	}

	public void load(int chapterID, ArrayList<TreePoint> lookup) {
		this.chapterID = chapterID;
		this.lookup = lookup;
		resetSelected();
	}

	public void resetSelected() {
		selectedID = -1;
		selected = null;
		connect = null;
		update();
	}

	public int getChapterID() {
		return chapterID;
	}

	public void moveNode(double x, double y) {
		if(selected != null){
			selected.setXY(x, y);
			connect.setPoint(x - offset, y - offset);
			update();
		}
	}

	public boolean onSelected(double x, double y) {
		if(selected == null) return false;
		return selected.distance(x, y) <= radious;
	}

	public int tools(double x, double y) {
		if(x >= 0 && x <= toolW && y >=0 && y <= toolH ){
			if(toolText.distance(x,y) <= radious) return 0;
		}
		return -1;
	}
	
	public boolean onConnect(double x, double y){
		if(connect == null) return false;
		return connect.distance(x,y) <= distance;
		
	}

	public void createNode(double x, double y, int createNode) {
		TreePoint cNode = new TreePoint(x,y,createNode);
		lookup.add(cNode);
		setSelected(cNode);
	}
	
	private void setSelected(TreePoint node){
		selected = node;
		selectedID = selected.getID();
		connect = new SimpPoint2D(selected.getX() - offset, selected.getY() - offset);
	}

	public void drawConnect(double x, double y, boolean delete) {
		update();
		gc.setLineWidth(5);
		if(delete){
			gc.setStroke(Color.RED);
		}else{
			gc.setStroke(Color.GREEN);	
		}
		gc.strokeLine(selected.getX(), selected.getY(), x, y);
		//double lineW = gc.getLineWidth()/4;
		//gc.strokeOval(x - lineW, y - lineW, lineW, lineW);
		//gc.strokeOval(selected.getX() - lineW, selected.getY() - lineW, lineW, lineW);
	}

	public void connect(int ID) {
		if(ID >= 0){
			if(selected != null){
				TreePoint node = searchTree(ID);
				node.addParent(selected);
				selected.addChild(node);
			}
		}
		update();
	}

	private TreePoint searchTree(int ID) {
		for(int i = 0; i < lookup.size(); i++){
			if(lookup.get(i).getID() == ID) return lookup.get(i);
		}
		return null;
	}

	public void disconnect(int ID) {
		TreePoint point = searchTree(ID);
		selected.removeChild(point);
		point.removeParent(selected);
		update();
	}

	public void delete() {
		if(selected != null){
			ArrayList<TreePoint> child = selected.getChild();
			for(int i = 0; i < child.size(); i++){
				child.get(i).removeParent(selected);
			}
			ArrayList<TreePoint> parent = selected.getChild();
			for(int i = 0; i < parent.size(); i++){
				parent.get(i).removeChild(selected);
			}
			selected.delete();
			lookup.remove(selected);
			selected = null;
			selectedID = -1;
			
			update();
		}
	}
}
