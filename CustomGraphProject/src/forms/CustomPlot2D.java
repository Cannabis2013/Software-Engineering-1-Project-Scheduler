package forms;

import java.awt.Point;
import java.util.List;

import javax.swing.JFrame;

import forms.Plot.axis;

public class CustomPlot2D extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private Plot graph;
	private List<Point> data;
	public CustomPlot2D(List<Point> data) {
		this.setData(data);
		graph = new Plot();
		graph.setOrigoCoordinates(125, 60);
		graph.setMaxValAxis(axis.xAxis, getMaxValue(data, axis.xAxis));
		graph.setMaxValAxis(axis.yAxis, getMaxValue(data, axis.yAxis));
		
		for (Point point : data)
			graph.addPoint(point);
		
		add(graph);
		
		setSize(640, 400);
	}
	
	public List<Point> getData() {
		return data;
	}

	public void setData(List<Point> data) {
		this.data = data;
	}

	@Override
	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		super.setVisible(b);
	}
	
	private int getMaxValue(List<Point> list,axis ax)
	{
		int maxVal = 0;
		
		for (Point point : list) {
			int val = (ax == axis.xAxis) ? point.x : point.y;
			if(val > maxVal)
				maxVal = val;
		}
		
		return maxVal;
	}
}
