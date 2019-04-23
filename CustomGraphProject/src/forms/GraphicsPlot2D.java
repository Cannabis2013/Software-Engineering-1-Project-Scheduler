package forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class GraphicsPlot2D extends JPanel {

	private static final long serialVersionUID = 1L;
	public Color backgroundColor = Color.BLACK;
	private Point internalOrigoCoordinates;
	private int verticalTopPadding = 80, horizontalPadding = 50, thickness = 4;;
	double maxHorizontalValue, maxVerticalValue;
	double vResolution = 1, hResolution = 1;
	enum axis{xAxis,yAxis};
	
	List<Point> cachedData = new ArrayList<Point>();
	
	
	public GraphicsPlot2D() {
		internalOrigoCoordinates = new Point(0,0);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		
		if(blockWidth(axis.yAxis) <= 1)
			vResolution = 20;
		else if(blockWidth(axis.yAxis) <= 5)
			vResolution = 10;
		else if(blockWidth(axis.yAxis) <= 10)
			vResolution = 2;
		else
			vResolution = 1;
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		drawLines(g2d);
		drawAxis(g2d);
		drawLabels(g2d);
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
	
	private double blockWidth(axis ax) throws IllegalStateException
	{
		if(maxHorizontalValue == 0 || maxVerticalValue == 0)
			throw new IllegalStateException("The current state is illegal.");
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
		
		g.setColor(Color.DARK_GRAY);
		
		for (double i = 0; vResolution*i <= maxVerticalValue; i++)
			g.drawLine(mapX(0), (int) mapY(i*verticalResolution()), (int) (mapX(0) + axisLenght(axis.xAxis)), (int) mapY(i*verticalResolution()));
		
		for (double i = mapX(1) - 1; i < getWidth() - horizontalPadding + 5; i += horizontalResolution())
			g.drawLine((int) i, (int) mapY(0), (int) i, verticalTopPadding);
	}
	
	private void drawLabels(Graphics2D g)
	{
		for (double i = 0; vResolution*i <= maxVerticalValue; i++) {
			String vLabel = Integer.toString((int) (vResolution*i));
			int width = g.getFontMetrics().stringWidth(vLabel);
			int posX = mapX(0) - width - 5, posY = (int) (mapY(i*verticalResolution()) + 3);
			g.drawString(vLabel, posX, posY);
		}
	}
	
	private double verticalResolution()
	{
		return vResolution*blockWidth(axis.yAxis);
	}
	
	private double horizontalResolution()
	{
		return hResolution*blockWidth(axis.xAxis);
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
						(int) (mapY(precedingYCoordinate*blockWidth(axis.yAxis))), 
						(int)(mapX(i*blockWidth(axis.xAxis))), 
						(int) mapY(precedingYCoordinate*blockWidth(axis.yAxis)));
			
			if(mapY(currentYVal) < mapY(0))
				g.drawLine((int) (mapX(i*blockWidth(axis.xAxis))), 
						(int) mapY(precedingYCoordinate*blockWidth(axis.yAxis)), 
						(int)(mapX(i*blockWidth(axis.xAxis))), 
						(int) mapY(currentYVal*blockWidth(axis.yAxis)));
			
			precedingYCoordinate = currentYVal;
		}
	}
}
