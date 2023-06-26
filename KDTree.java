package k_dtree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

public class KDTree {
	private int k = 2;
	private Node root;

	public KDTree(Node root) {
		super();
		this.root = root;
	}

	public void constructKDTree(Node root, int depth) {
		if (root.getList().size() <= 1) {
			return;
		}
		int axis = depth % k;
		if (axis == 0) {
			Collections.sort(root.getList(), new Comparator<Point2D>() {

				@Override
				public int compare(Point2D o1, Point2D o2) {
					// TODO Auto-generated method stub

					if (o1.x() >= o2.x()) {
						return 1;
					}
					return -1;

				}
			});
			int size = root.getList().size();
			double line = root.getList().get(size / 2).x();
			root.setLine(line);
			root.setPoint(root.getList().get(size / 2));
			double[] leftRect = { root.getRect()[0], root.getRect()[1], line, root.getRect()[3] };
			double[] rightRect = { line, root.getRect()[1], root.getRect()[2], root.getRect()[3] };
			ArrayList<Point2D> left = new ArrayList<>();
			ArrayList<Point2D> right = new ArrayList<>();
			for (int i = 0; i < size / 2; i++) {
				left.add(root.getList().get(i));
			}
			for (int i = size / 2; i < size; i++) {
				right.add(root.getList().get(i));
			}
			root.setLeft(new Node(left, leftRect));
			root.setRight(new Node(right, rightRect));
			constructKDTree(root.getLeft(), depth + 1);
			constructKDTree(root.getRight(), depth + 1);
		}
		if (axis == 1) {
			Collections.sort(root.getList(), new Comparator<Point2D>() {

				@Override
				public int compare(Point2D o1, Point2D o2) {
					// TODO Auto-generated method stub

					if (o1.y() >= o2.y()) {
						return 1;
					}
					return -1;

				}
			});
			int size = root.getList().size();
			double line = root.getList().get(size / 2).y();
			root.setLine(line);
			root.setPoint(root.getList().get(size / 2));
			double[] leftRect = { root.getRect()[0], root.getRect()[1], root.getRect()[2], line };
			double[] rightRect = { root.getRect()[0], line, root.getRect()[2], root.getRect()[3] };
			ArrayList<Point2D> left = new ArrayList<>();
			ArrayList<Point2D> right = new ArrayList<>();
			for (int i = 0; i < size / 2; i++) {
				left.add(root.getList().get(i));
			}
			for (int i = size / 2; i < size; i++) {
				right.add(root.getList().get(i));
			}
			root.setLeft(new Node(left, leftRect));
			root.setRight(new Node(right, rightRect));
			constructKDTree(root.getLeft(), depth + 1);
			constructKDTree(root.getRight(), depth + 1);
		}
	}

	public void draw() {
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.setPenRadius(0.01);
		for (Point2D point : root.getList()) {
			point.draw();
		}
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.setPenRadius();
		draw(root, 0);
	}

	public void draw(Node root, int depth) {
		if (root == null) {
			return;
		}
		int axis = depth % k;
		if (depth == 0) {
			StdDraw.line(root.getLine(), 0, root.getLine(), 1);
		}
		// Traverse the left Nodes
		draw(root.getLeft(), depth + 1);

		// Draw partrition line
		if (axis == 0) {
			StdDraw.line(root.getLine(), root.getRect()[1], root.getLine(), root.getRect()[3]);
		} else {
			StdDraw.line(root.getRect()[0], root.getLine(), root.getRect()[2], root.getLine());
		}
		// Traverse the right Nodes
		draw(root.getRight(), depth + 1);
	}

	public Point2D nearest(Node node, Point2D point, Point2D nearest, int depth) {
		int axis = depth % k;
		if (node.getList().size() <= 1) {
			Point2D onlyPoint = node.getList().get(0);
			if (onlyPoint.distanceSquaredTo(point) <= nearest.distanceSquaredTo(point)) {
				nearest = onlyPoint;
				return nearest;
			}
		}
		if (node.getPoint() != null) {
			if (node.getPoint().distanceSquaredTo(point) <= nearest.distanceSquaredTo(point)) {
				nearest = node.getPoint();
			}
		}

		if (axis == 0) {
			double distanceToLine = point.x() - node.getLine();
			if (distanceToLine < 0) {
				nearest = nearest(node.getLeft(), point, nearest, depth + 1);
			}
			if (nearest.distanceSquaredTo(point) >= distanceToLine * distanceToLine) {
				nearest = nearest(node.getRight(), point, nearest, depth + 1);
			}
		}
		if (axis == 1) {
			double distanceToLine = point.y() - node.getLine();
			if (distanceToLine < 0) {
				nearest = nearest(node.getLeft(), point, nearest, depth + 1);
			}
			if (nearest.distanceSquaredTo(point) >= distanceToLine * distanceToLine) {
				nearest = nearest(node.getRight(), point, nearest, depth + 1);
			}
		}
		return nearest;
	}

	public Point2D nearest(Point2D point) {
		return nearest(root, point, root.getPoint(), 0);
	}
}
