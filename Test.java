package k_dtree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import edu.princeton.cs.algs4.Point2D;
import kdtree.kdtr.KdTree;

public class Test {
	public static void main(String[] args) throws IOException {
		// unit tests [[3, 6], [17, 15], [13, 15], [6, 12], [9, 1], [2, 7], [10, 19]]

		KdTree kd = new KdTree();

		Random rd = new Random();
		Scanner sc = new Scanner(System.in);
		ArrayList<Point2D> list = new ArrayList<>();
		
		for (int i = 0; i < 100; i++) {
			list.add(new Point2D(rd.nextDouble(), rd.nextDouble()));
			/**
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			Point2D point  = new Point2D(x, y);
			list.add(point);
			*/
		}
		/** Test
		0.1 0.9
		0.2 0.3
		0.4 0.1
		0.3 0.7
		0.5 0.4
		0.6 0.8
		0.7 0.2
		0.8 0.8
		0.7 0.9
		0.9 0.6
		 */
		double[] rect = {0,0,1,1};
		Node root = new Node(list, rect);
		KDTree kDTree = new KDTree(root);
		double start = System.currentTimeMillis();
		kDTree.constructKDTree(root, 0);
		double end = System.currentTimeMillis();
		System.out.println((end - start)*1000);
		kDTree.draw();
		Point2D test = new Point2D(rd.nextDouble(), rd.nextDouble());
		System.out.println(kDTree.nearest(test));
		
		for (Point2D point2d : list) {
			kd.insert(point2d);
		}
		System.out.println(kd.nearest(test));
		System.out.println(kDTree.nearest(test).distanceSquaredTo(test));
		System.out.println(kd.nearest(test).distanceSquaredTo(test));
	}
}
