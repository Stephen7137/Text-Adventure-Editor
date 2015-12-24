package cpp.TextAdvEditor.Model;

import java.io.Serializable;
import java.util.ArrayList;

import javafx.geometry.Point2D;

public class TreePoint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6720937507684278878L;
	private int id;
	private transient Point2D xy;
	private ArrayList<TreePoint> parent;
	private ArrayList<TreePoint> child;
	
	public TreePoint(double x, double y, int id){
		xy = new Point2D(x,y);
		parent = new ArrayList<TreePoint>();
		child = new ArrayList<TreePoint>();
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	public Point2D getXY(){
		return xy;
	}
	
	public ArrayList<TreePoint> getChild(){
		return child;
	}
	
	public ArrayList<TreePoint> getParent(){
		return parent;
	}

	public void setXY(Point2D newPoint) {
		xy = newPoint;		
	}

	public int childsize() {
		return child.size();
	}

	public void addChild(TreePoint cNode) {
		child.add(cNode);
	}
	
	public void addParent(TreePoint pNode) {
		parent.add(pNode);
	}

	public boolean childEmpty() {
		return child.isEmpty();
	}

	public int getParentSize() {
		return parent.size();
	}

	public TreePoint getParent(int j) {
		return parent.get(j);
	}
}
