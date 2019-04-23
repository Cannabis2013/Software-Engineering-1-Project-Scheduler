package forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GraphicsPlot2D extends JPanel {

	private static final long serialVersionUID = 1L;
	public Color backgroundColor = Color.BLACK;
	private Point internalOrigoCoordinates;
	private int verticalTopPadding = 40, horizontalPadding = 50, thickness = 4;;
	double maxHorizontalValue, maxVerticalValue;
	enum axis{xAxis,yAxis};
	
	List<Point> cachedData = new ArrayList<Point>();
	
	
	public GraphicsPlot2D() {
		internalOrigoCoordinates = new Point(0,0);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		drawLines(g2d);
		drawAxis(g2d);
		drawAxisLabels(g2d);
		drawCachedData(g2d);
	}
	
	public void addPoint(Point point)
	{
		cachedData.add(point);
	}
	
	
	public void setMaxValAxis(axis ax, int max)
	{
		if(ax == axis.xAxis)
			maxHorizontalValue = max;
		else
			maxVerticalValue = max;
	}
	
	@Override
	public void show() {
		setVisible(true);
	}
	
	public void setBackgroundColor(Color color)
	{
		backgroundColor = color;
		if(isShowing())
			paintAll(getGraphics());
	}
	
	private int mapX(double x)
	{
		int newX =  (int) (x + internalOrigoCoordinates.getX());
		
		return newX;
	}
	
	private double mapY(double y)
	{
		double newY = getHeight() - internalOrigoCoordinates.y - y;
		
		return newY;
	}
	
	public void setOrigoCoordinates(int x ,int y)
	{
		internalOrigoCoordinates.setLocation(x, y);
		update(getGraphics());
	}
	
	private double blockWidth(axis ax)
	{
		if(ax == axis.xAxis)
			return axisLenght(ax) / maxHorizontalValue;
		else
			return axisLenght(ax) / maxVerticalValue;
	}
	
	private double axisLenght(axis ax)
	{
		if(ax == axis.yAxis)
			return mapY(0) - verticalTopPadding;
		else
			return (getWidth() - horizontalPadding) - mapX(0);
	}
	
	private void drawLines(Graphics2D g)
	{
		double width = blockWidth(axis.yAxis);
		if(blockWidth(axis.yAxis) <= 5)
			width = (int) (20*blockWidth(axis.yAxis));
		g.setColor(Color.GRAY);
		
		for (double i = mapY(1) + 1; i >= verticalTopPadding - 2; i -= width) {
			int posY = (int) i;
			g.drawLine(mapX(0), posY, (int) (mapX(0) + axisLenght(axis.xAxis)), posY);
		}
		
		for (double i = mapX(1) - 1; i < getWidth() - horizontalPadding + 5; i += blockWidth(axis.xAxis)) {
			g.drawLine((int) i, (int) mapY(0), (int) i, verticalTopPadding);
		}
	}
	
	private void drawAxis(Graphics2D g)
	{
		double startPosX = mapX(0);
		double startPosY = mapY(0) - thickness/2;
		double hWidth = (getWidth() - horizontalPadding) - startPosX;
		double vHeight = mapY(0) - verticalTopPadding + thickness/2;
		
		g.setColor(Color.WHITE);
		
		g.fillRect((int) startPosX, (int) startPosY , (int)hWidth, thickness);
		g.fillRect((int)startPosX - thickness/2, verticalTopPadding , thickness, (int)vHeight);
	}
	
	
	private void drawAxisLabels(Graphics2D g)
	{
		String xAxisTitle = "Week";
		String yAxisTitle = "Hours";
		g.setFont(new Font("",1, 12));
		g.drawString(xAxisTitle, (int) (mapX(0) +  axisLenght(axis.xAxis) + 10), (int) mapY(0) + 4);
		g.drawString(yAxisTitle, (int) mapX(-17), verticalTopPadding - 10);
	}
	
	private void drawCachedData(Graphics2D g)
	{
		g.setColor(Color.green);
		int precedingYCoordinate = 0, currentIndex = 0;
		for(double i = 1;i <= maxHorizontalValue;i++)
		{
			int currentYVal;
			if(cachedData.get(currentIndex).x == i)
				currentYVal = cachedData.get(currentIndex++).y + precedingYCoordinate;
			else
				currentYVal = precedingYCoordinate;
			
			if(mapY(precedingYCoordinate) < mapY(0))
				g.drawLine((int) (mapX((i - 1)*blockWidth(axis.xAxis))), 
						(int) ((int) mapY(precedingYCoordinate*blockWidth(axis.yAxis))), 
						(int)(mapX(i*blockWidth(axis.xAxis))), 
						(int) mapY(precedingYCoordinate*blockWidth(axis.yAxis)));
			
			if(mapY(currentYVal) < mapY(0))
				g.drawLine((int) (mapX(i)*blockWidth(axis.xAxis)), 
						(int) mapY(precedingYCoordinate*blockWidth(axis.yAxis)), 
						(int)(mapX(i)*blockWidth(axis.xAxis)), 
						(int) mapY(currentYVal*blockWidth(axis.yAxis)));
			
			precedingYCoordinate = currentYVal;
		}
	}
}
