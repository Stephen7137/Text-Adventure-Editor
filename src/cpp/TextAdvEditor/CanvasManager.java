package cpp.TextAdvEditor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

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
	private Point2D node;
	private int toolW;
	private int toolH;
	
	public CanvasManager(int chID){
		chapterID = chID;
	}
	
	public void setCanvas(Canvas canvas, int key){
		diameter = radious*2;
		lookup = new ArrayList<TreePoint>();
		selectedID = -1;
		selected = null;
		this.canvas = canvas;
		gc = canvas.getGraphicsContext2D();
		toolW = diameter + 8;
		toolH = diameter + 8;
		node = new Point2D(toolW/2 + 2, toolH - radious - 2);
		update();
	}
	
	public void update(){
		gc.setFill(background);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setFill(Color.BLUE);
		gc.setStroke(Color.GREEN);
		gc.setLineWidth(5);
		draw();
		gc.setFill(background);
		gc.setStroke(Color.BLACK);
		gc.fillRect(2, 2, toolW, toolH);
		gc.strokeRect(2, 2, toolW, toolH);
		gc.setFill(Color.BLUE);
		gc.fillOval(node.getX() - radious, node.getY() - radious, diameter, diameter);
	}
	
	private boolean checkArea(double x, double y){
		for(int i = 0; i < lookup.size(); i++){
			if(lookup.get(i).distance(x, y) <= diameter + distance) return false;
		}
		return true;
	}
	
	private void draw(){
		for(int i = 0; i < lookup.size(); i++){
			TreePoint point = lookup.get(i);
			gc.fillOval(point.getX() - radious, point.getY() - radious,
					diameter, diameter);
			drawChild(lookup.get(i), Color.GREEN);
		}
		if(selected != null){
			gc.strokeOval(selected.getX()-radious, selected.getY()-radious,
					diameter, diameter);
			gc.setFill(Color.BLACK);
			gc.fillOval(selected.getX()- radious - distance, selected.getY()- radious - distance, distance, distance);
			drawChild(selected, Color.RED);
		}
	}
	
	private void drawChild(TreePoint point, Paint color){
		gc.setStroke(color);
		for(int j = 0; j < point.childsize(); j++){
			TreePoint point1 = point.getChild(j);
			gc.strokeLine(point.getX(), point.getY(), 
					point1.getX(), point1.getY());
		}	
	}
	
	public int onNode(double x, double y) {
		for(int i = 0; i < lookup.size(); i++){
			if(lookup.get(i).distance(x, y) <= radious){
				selected = lookup.get(i);
				selectedID = selected.getID();
				update();
				return selectedID;
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
		selected = null;
		selectedID = -1;
		update();
	}

	public void resetSelected() {
		selectedID = -1;
		selected = null;
		update();
	}

	public int getChapterID() {
		return chapterID;
	}

	public void moveNode(double x, double y) {
		if(selected != null){
			selected.setXY(x, y);
			update();
		}
	}

	public boolean onSelected(double x, double y) {
		if(selected == null) return false;
		return selected.distance(x, y) <= radious;
	}

	public int tools(double x, double y) {
		if(x >= 0 && x <= toolW && y >=0 && y <= toolH ){
			if(node.distance(x,y) <= radious) return 0;
		}
		return -1;
	}

	public void createNode(double x, double y, int createNode) {
		TreePoint cNode = new TreePoint(x,y,createNode);
		lookup.add(cNode);
		selected = cNode;
	}
}
