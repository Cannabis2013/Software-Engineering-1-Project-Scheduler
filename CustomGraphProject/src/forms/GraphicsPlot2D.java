package forms;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import toolkit.Pair;

public class GraphicsPlot2D extends JPanel {

	private static final long serialVersionUID = 1L;
	public Color backgroundColor = Color.BLACK;
	private Point internalOrigoCoordinates;
	private int verticalTopPadding = 80, horizontalPadding = 80, thickness = 2;;
	double maxHorizontalValue, maxVerticalValue;
	enum axis{xAxis,yAxis};
	
	List<Point> cachedData = new ArrayList<Point>();
	
	List<Pair<String,Integer>> points = new ArrayList<>();
	
	public GraphicsPlot2D() {
		internalOrigoCoordinates = new Point(0,0);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		drawAxis(g2d);
		drawGridLines(g2d);
		drawAxisTitles(g2d);
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
	
	private Point map(Point externalCoordinates)
	{
		int xPos = (int) (externalCoordinates.getX() + internalOrigoCoordinates.getX());
		int yPos = (int) (getHeight() - (int) internalOrigoCoordinates.getY() - externalCoordinates.getY());
		
		return new Point(xPos, yPos);
	}
	
	private int mapX(int x)
	{
		int newX = (int) x + (int) internalOrigoCoordinates.getX();
		
		return newX;
	}
	
	private int mapY(int y)
	{
		int newY = getHeight() - internalOrigoCoordinates.y - y;
		
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
	
	private int axisLenght(axis ax)
	{
		if(ax == axis.yAxis)
			return mapY(0) - verticalTopPadding;
		else
			return (getWidth() - horizontalPadding) - mapX(0);
	}
	
	private void drawAxis(Graphics2D g)
	{
		int startPosX = mapX(0);
		double startPosY = mapY(0) - thickness/2;
		int hWidth = (getWidth() - horizontalPadding) - startPosX;
		int vHeight = mapY(0) - verticalTopPadding + thickness/2;
		
		g.setColor(Color.WHITE);
		
		g.fillRect(startPosX, (int) startPosY , hWidth, thickness);
		g.fillRect(startPosX - thickness/2, verticalTopPadding , thickness, vHeight);
	}
	
	private void drawGridLines(Graphics2D g)
	{
		// Draw vertical lines
		int horizontalBlockWidth = (int) blockWidth(axis.xAxis);
		for (int i = 1; i <= maxHorizontalValue; i++ ) {
			int X = mapX(i*horizontalBlockWidth);
			int startY = mapY(4);
			g.fillRect(X,startY, 1, 8);
			
			g.setColor(Color.gray);
			g.drawLine(X, mapY(0),X , verticalTopPadding);
			g.setColor(Color.white);
		}
		
		int verticalBlockWidth = (int) blockWidth(axis.yAxis);
		int horizontalBoundary = mapX(0) + axisLenght(axis.xAxis);
		for (int i = 1; i <= maxVerticalValue; i++ ) {
			int X = mapX(-4);
			int startY = mapY(i*verticalBlockWidth);
			g.fillRect(X, startY,8,1);
			
			g.setColor(Color.gray);
			g.drawLine(mapX(0), startY, horizontalBoundary, startY);
			g.setColor(Color.white);
		}
	}
	
	private void drawAxisTitles(Graphics2D g)
	{
		String xAxisTitle = "Week";
		String yAxisTitle = "Hours";
		g.setFont(new Font("",1, 12));
		g.drawString(xAxisTitle, mapX(0) + axisLenght(axis.xAxis) + 10, mapY(0) + 4);
		g.drawString(yAxisTitle, mapX(-17), verticalTopPadding - 10);
	}
	
	private void drawCachedData(Graphics2D g)
	{
		g.setColor(Color.green);
		for (Point point : cachedData) {
			g.fillArc(mapX(point.x * (int) blockWidth(axis.xAxis)) - 4, mapY(point.y*(int) blockWidth(axis.yAxis) + 4), 8, 8, 0, 360);
			
		}
	}
}
