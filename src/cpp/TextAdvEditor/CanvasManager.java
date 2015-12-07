package cpp.TextAdvEditor;

import java.util.ArrayList;

import cpp.TextAdvEditor.Model.Text;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasManager {
	
	private GraphicsContext gc;
	private ChapterEditor editor;
	private ArrayList<TreePoints> tree;
	private int radious = 15;
	private int diameter;
	private int distance = 10;
	private TreePoints selected;
	
	public CanvasManager(ChapterEditor editor){
		diameter = radious*2;
		this.editor = editor;
		tree = new ArrayList<TreePoints>();
	}

	public void setGrphCont(GraphicsContext graphicsContext2D, 
			double h, double w) {
		gc = graphicsContext2D;
		gc.setFill(Color.WHEAT);
		gc.fillRect(0, 0, w, h);;
		gc.setFill(Color.BLUE);
		gc.setStroke(Color.GREEN);
		gc.setLineWidth(5);
	}

	public void addCircle(double x, double y) {
		x-=radious;
		y-=radious;
		if(checkArea(x,y)){
			tree.add(new TreePoints(x, y, editor.createText()));
		}
		draw();
	}
	
	private boolean checkArea(double x, double y){
		for(int i = 0; i < tree.size(); i++){
			if(tree.get(i).xy.distance(x, y) <= diameter + distance) return false;
		}
		return true;
	}
	
	private void draw(){
		for(int i = 0; i < tree.size(); i++){
			Point2D xy = tree.get(i).xy;
			gc.strokeOval(xy.getX(), xy.getY(), diameter, diameter);
		}
	}
	
	class TreePoints{
		Point2D xy;
		Text text;
		
		public TreePoints(double x, double y, Text text){
			xy = new Point2D(x,y);
			this.text = text;
		}
	}

	public boolean onNode(double x, double y) {
		x-=radious;
		y-=radious;
		boolean isOnNode = false;
		for(int i = 0; i < tree.size(); i++){
			if(tree.get(i).xy.distance(x, y) <= radious){
				selected = tree.get(i);
				i = tree.size();
				isOnNode = true;
			}
		}
		draw();
		return isOnNode;
	}
}
