package k_dtree;

import java.util.ArrayList;

import edu.princeton.cs.algs4.Point2D;

public class Node {
	private double line;
	private Point2D point;


	public Point2D getPoint() {
		return point;
	}

	public void setPoint(Point2D point) {
		this.point = point;
	}

	public double getLine() {
		return line;
	}

	public void setLine(double line) {
		this.line = line;
	}
	private double[] rect;
	private ArrayList<Point2D> list;
	
	
	public Node(ArrayList<Point2D> list, double[] rect) {
		super(); 
		this.rect = rect;
		this.list = list;
	}
	
	public ArrayList<Point2D> getList() {
		return list;
	}
	public void setList(ArrayList<Point2D> list) {
		this.list = list;
	}
	private Node left;
	private Node right;
	
	public double[] getRect() {
		return rect;
	}

	public void setRect(double[] rect) {
		this.rect = rect;
	}

	public Node getLeft() {
		return left;
	}
	public void setLeft(Node left) {
		this.left = left;
	}
	public Node getRight() {
		return right;
	}
	public void setRight(Node right) {
		this.right = right;
	}
	
	
}
