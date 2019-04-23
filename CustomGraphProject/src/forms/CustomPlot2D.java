package forms;

import java.awt.Dimension;
import java.awt.Point;
import java.util.List;

import javax.swing.JFrame;

import forms.GraphicsPlot2D.axis;

public class CustomPlot2D extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private GraphicsPlot2D graph;
	private List<Point> data;
	public CustomPlot2D(List<Point> data) {
		this.setData(data);
		graph = new GraphicsPlot2D();
		graph.setOrigoCoordinates(50, 50);
		graph.setMaxValAxis(axis.xAxis, getMaxValue(data, axis.xAxis));
		graph.setMaxValAxis(axis.yAxis, 75);
		
		for (Point point : data)
			graph.addPoint(point);
		
		add(graph);
		
		setSize(640, 400);
		setMinimumSize(new Dimension(320,200));
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
