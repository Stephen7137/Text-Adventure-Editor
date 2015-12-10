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
	private Map<Integer,TreePoint> lookup;
	private Color background = Color.WHITE;
	private Canvas canvas;
	
	public void setCanvas(Canvas canvas){
		diameter = radious*2;
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
		Integer[] keys = (Integer[]) lookup.keySet().toArray();
		for(int i = 0; i < keys.length; i++){
			if(lookup.get(keys[i]).xy.distance(x, y) <= diameter + distance) return false;
		}
		return true;
	}
	
	private void draw(){
		gc.strokeOval(radious, canvas.getHeight()/2,
				diameter, diameter);
	}
	
	public int onNode(double x, double y) {
		x-=radious;
		y-=radious;
		Integer[] keys = (Integer[]) lookup.keySet().toArray();
		for(int i = 0; i < keys.length; i++){
			if(lookup.get(keys[i]).xy.distance(x, y) <= radious) return keys[i];
		}
		return -1;
	}
	
	class TreePoint{
		Point2D xy;
		ArrayList<Integer> child;
		
		public TreePoint(double x, double y, Text text){
			xy = new Point2D(x,y);
		}
	}
}
