package cpp.TextAdvEditor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import cpp.TextAdvEditor.Model.Text;
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
	private Canvas canvas;
	private int selected;
	
	public void setCanvas(Canvas canvas, int key){
		diameter = radious*2;
		lookup = new ArrayList<TreePoint>();
		lookup.add(new TreePoint(radious,canvas.getHeight()/2 , key));
		selected = lookup.get(0).id;
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
			if(lookup.get(i).xy.distance(x, y) <= diameter + distance) return false;
		}
		return true;
	}
	
	private void draw(){
		for(int i = 0; i < lookup.size(); i++){
			TreePoint point = lookup.get(i);
			gc.fillOval(point.xy.getX() - radious, point.xy.getY() - radious,
					diameter, diameter);
			for(int j = 0; j < lookup.get(i).parent.size(); j++){
				TreePoint point1 = lookup.get(i).parent.get(j);
				gc.strokeLine(point.xy.getX(), point.xy.getY(), 
						point1.xy.getX(), point1.xy.getY());
			}
		}
	}
	
	public int onNode(double x, double y) {
		for(int i = 0; i < lookup.size(); i++){
			if(lookup.get(i).xy.distance(x, y) <= radious){
				selected = lookup.get(i).id;
				return selected;
			}
		}
		return -1;
	}
	
	class TreePoint{
		int id;
		Point2D xy;
		ArrayList<TreePoint> parent;
		ArrayList<TreePoint> child;
		
		public TreePoint(double x, double y, int id){
			xy = new Point2D(x,y);
			parent = new ArrayList<TreePoint>();
			child = new ArrayList<TreePoint>();
			this.id = id;
		}
	}

	public int getSelected() {
		return selected;
	}

	public void addChild(int key) {
		TreePoint pNode = searchTree();
		TreePoint cNode = new TreePoint(0,0,key);
		pNode.child.add(cNode);
		lookup.add(cNode);
		cNode.parent.add(pNode);
		if(!pNode.child.isEmpty()){
			int n = pNode.child.size();
			int y = (int)pNode.xy.getY() - ((n-1)*diameter + distance*(n-1))/2;
			int x = (int)pNode.xy.getX() + nodeDist;
			for(int i = 0; i < n; i++){
				Point2D newPoint = new Point2D(x,y);
				pNode.child.get(i).xy = newPoint;
				y+= (diameter + distance);
			}
		}
		update();
	}
	
	private TreePoint searchTree(){
		for(int i = 0; i < lookup.size(); i++){
			if(lookup.get(i).id == selected) return lookup.get(i);
		}
		return null;
	}
}
