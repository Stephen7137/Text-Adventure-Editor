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
	private double x;
	private double y;
	private ArrayList<TreePoint> parent;
	private ArrayList<TreePoint> child;
	
	public TreePoint(double x, double y, int id){
		this.x = x;
		this.y = y;
		parent = new ArrayList<TreePoint>();
		child = new ArrayList<TreePoint>();
		this.id = id;
	}
	
	public int getID(){
		return id;
	}
	
	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public ArrayList<TreePoint> getChild(){
		return child;
	}
	
	public TreePoint getChild(int j){
		return child.get(j);
	}
	
	public ArrayList<TreePoint> getParent(){
		return parent;
	}

	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;		
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

	public double distance(double x2, double y2) {
		x2 -= x;
		y2 -= y;
		x2 *= x2;
		y2 *= y2;
		return Math.sqrt(x2 + y2);
	}
}
