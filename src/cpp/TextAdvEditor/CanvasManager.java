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
	private int selected;
	
	public CanvasManager(int chID){
		chapterID = chID;
	}
	
	public void setCanvas(Canvas canvas, int key){
		diameter = radious*2;
		lookup = new ArrayList<TreePoint>();
		lookup.add(new TreePoint(radious,canvas.getHeight()/2 , key));
		selected = -1;
		this.canvas = canvas;
		gc = canvas.getGraphicsContext2D();
		update();
	}
	
	public void update(){
		gc.setFill(background);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setFill(Color.BLUE);
		gc.setStroke(Color.GREEN);
		gc.setLineWidth(5);
		draw();
	}
	
	private boolean checkArea(double x, double y){
		for(int i = 0; i < lookup.size(); i++){
			if(lookup.get(i).getXY().distance(x, y) <= diameter + distance) return false;
		}
		return true;
	}
	
	private void draw(){
		for(int i = 0; i < lookup.size(); i++){
			TreePoint point = lookup.get(i);
			gc.fillOval(point.getXY().getX() - radious, point.getXY().getY() - radious,
					diameter, diameter);
			for(int j = 0; j < lookup.get(i).getParentSize(); j++){
				TreePoint point1 = lookup.get(i).getParent(j);
				gc.strokeLine(point.getXY().getX(), point.getXY().getY(), 
						point1.getXY().getX(), point1.getXY().getY());
			}	
		}
		if(selected > -1){
			TreePoint point = searchTree();
			gc.strokeOval(point.getXY().getX()-radious, point.getXY().getY()-radious,
					diameter, diameter);
		}
	}
	
	public int onNode(double x, double y) {
		for(int i = 0; i < lookup.size(); i++){
			if(lookup.get(i).getXY().distance(x, y) <= radious){
				selected = lookup.get(i).getID();
				update();
				return selected;
			}
		}
		return -1;
	}

	public int getSelected() {
		return selected;
	}

	public void addChild(int key) {
		if(key >= 0){
			TreePoint pNode = searchTree();
			TreePoint cNode = new TreePoint(0,0,key);
			pNode.addChild(cNode);
			lookup.add(cNode);
			cNode.addParent(pNode);
			if(!pNode.childEmpty()){
				int n = pNode.childsize();
				int y = (int)pNode.getXY().getY() - ((n-1)*diameter + distance*(n-1))/2;
				int x = (int)pNode.getXY().getX() + nodeDist;
				for(int i = 0; i < n; i++){
					Point2D newPoint = new Point2D(x,y);
					pNode.getChild().get(i).setXY(newPoint);
					y+= (diameter + distance);
				}
			}
			update();
		}
	}
	
	private TreePoint searchTree(){
		for(int i = 0; i < lookup.size(); i++){
			if(lookup.get(i).getID() == selected) return lookup.get(i);
		}
		return null;
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
		selected = -1;
		update();
	}

	public void resetSelected() {
		selected = -1;
		update();
	}

	public int getChapterID() {
		return chapterID;
	}
}
