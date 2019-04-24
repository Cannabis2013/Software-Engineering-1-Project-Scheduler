package main;

import java.awt.EventQueue;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import forms.CustomPlot2D;

public class MainApplication {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				List<Point> dataTestList = new ArrayList<>();
				
				dataTestList.add(new Point(1, 2));
				dataTestList.add(new Point(2, 3));
				dataTestList.add(new Point(3, 5));
				dataTestList.add(new Point(4,12));
				dataTestList.add(new Point(8,23));
				dataTestList.add(new Point(9,29));
				dataTestList.add(new Point(10,29));
				dataTestList.add(new Point(12,36));
				
				CustomPlot2D cGraph = new CustomPlot2D(dataTestList);
				cGraph.setVisible(true);
			}
		});
	}
}
