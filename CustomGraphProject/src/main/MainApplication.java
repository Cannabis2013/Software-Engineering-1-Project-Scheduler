package main;

import java.awt.EventQueue;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import forms.CustomPlot2D;
import toolkit.Pair;

public class MainApplication {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				List<Point> dataTestList = new ArrayList<>();
				
				for (int i = 1; i <= 10; i++)
					dataTestList.add(new Point(i,i +3));
				
				CustomPlot2D cGraph = new CustomPlot2D(dataTestList);
				cGraph.setVisible(true);
			}
		});
	}
}
