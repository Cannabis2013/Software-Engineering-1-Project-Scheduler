package customGraphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JPanel;

import customGraphics.GraphicsPlot2D.axis;
import models.HourRegistrationModel;
import static_Domain.DateFormatizer;
																																			
public class GraphicsPlot2D extends JPanel {

	private static final long serialVersionUID = 1L;
	public Color backgroundColor = Color.BLACK;
	private Point internalOrigoCoordinates;
	private int verticalTopPadding = 40, horizontalPadding = 50, thickness = 4;;
	double maxHorizontalValue, maxVerticalValue;
	double vResolution = 1, hResolution = 1;
	enum axis{xAxis,yAxis};
	private String graphTitle = "GraphTitle";
	
	private List<myPoint<Integer, Double>> cachedData = new ArrayList<myPoint<Integer,Double>>();
	
	public GraphicsPlot2D() {
		internalOrigoCoordinates = new Point(50,50);
		
		setOrigoCoordinates(50, 50);
	}
	
	public List<myPoint<Integer,Double>> getData() {
		return cachedData;
	}

	public void setData(List<HourRegistrationModel> cachedData) {
		
		this.cachedData = (List<myPoint<Integer,Double>>) cachedData.stream().
				map(item -> new myPoint(DateFormatizer.getWeekOfDate(item.registrationDate()),item.hours()));
		
		setMaxValAxis(axis.xAxis, this.cachedData.size());
		setMaxValAxis(axis.yAxis, getSum(this.cachedData, axis.yAxis));
	}

	@Override
	public void setVisible(boolean b) {
		super.setVisible(b);
		setSize(getSize());
		setSize(getSize());
	}
	
	private int getSum(List<myPoint<Integer,Double>> list,axis ax)
	{
		int sum = 0;
		
		for (myPoint<Integer, Double> point : list)
			sum += (ax == axis.xAxis) ? point.getX() : point.getY();
		
		return sum;
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		if(cachedData.isEmpty())
			return;
		
		Graphics2D g2d = (Graphics2D) g;
		setAxisResolutions();
		
		g2d.setColor(backgroundColor);
		g2d.fillRect(0, 0, getWidth(), getHeight());
		
		drawLines(g2d);
		drawAxis(g2d);
		drawLabels(g2d);
		drawAxisLabels(g2d);
		drawCachedData(g2d);
		drawGraphTitle(g2d);
	}
	
	public void setGraphtitle(String title)
	{
		graphTitle = title;
	}
	
	public void setMaxValAxis(axis ax, int max)
	{
		if(ax == axis.xAxis)
			maxHorizontalValue = max;
		else
			maxVerticalValue = max;
	}
	
	public void setBackgroundColor(Color color)
	{
		backgroundColor = color;
		if(isShowing())
			paintAll(getGraphics());
	}
	
	private int mapX(double x)
	{
		return (int) (x + internalOrigoCoordinates.getX());
	}
	
	private double mapY(double y)
	{
		return getHeight() - internalOrigoCoordinates.y - y;
	}
	
	public void setOrigoCoordinates(int x ,int y)
	{
		internalOrigoCoordinates.setLocation(x, y);
		update(getGraphics());
	}
	
	private double blockWidth(axis ax) throws IllegalStateException
	{
		if(maxHorizontalValue == 0 || maxVerticalValue == 0)
			throw new IllegalStateException("The current state is not ready for this call.");
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
		// Draw y labels
		for (double i = 0; vResolution*i <= maxVerticalValue; i++) {
			String vLabel = Integer.toString((int) (vResolution*i));
			int width = g.getFontMetrics().stringWidth(vLabel);
			int posX = mapX(0) - width - thickness - 3, posY = (int) (mapY(i*verticalResolution()) + 4);
			g.drawString(vLabel, posX, posY);
		}
		
		// Draw x labels
		
		for (double i = 0; hResolution*i <= maxHorizontalValue; i++) {
			String hLabel = Integer.toString((int) (hResolution*i));
			int width = g.getFontMetrics().stringWidth(hLabel);
			int height = g.getFont().getSize();
			int posX = mapX(i*horizontalResolution()) - width/2, posY = (int) (mapY(0) + height + thickness);
			g.drawString(hLabel, posX, posY);
		}
	}
	
	private void drawAxis(Graphics2D g)
	{
		double startPosX = mapX(0);
		double startPosY = mapY(0);
		double hWidth = (getWidth() - horizontalPadding) - startPosX + 1;
		double vHeight = mapY(0) - verticalTopPadding + thickness + 1;
		
		g.setColor(Color.WHITE);
		
		g.fillRect((int) startPosX, (int) startPosY , (int)hWidth, thickness); // xAxis
		g.fillRect((int)startPosX - thickness, verticalTopPadding - 1, thickness, (int)vHeight); // yAxis
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
		double precedingYCoordinate = 0;
		int currentIndex = 0;
		for(double i = 1;i <= maxHorizontalValue;i++)
		{
			double currentYVal;
			if(cachedData.get(currentIndex).getX() == i)
				currentYVal = cachedData.get(currentIndex++).getY() + precedingYCoordinate;
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
	
	private void drawGraphTitle(Graphics2D g)
	{
		g.setColor(Color.WHITE);
		Font f = new Font("Arial",1,16);
		g.setFont(f);
		int width = g.getFontMetrics().stringWidth(graphTitle);
		double posX = getWidth()/2 - width/2, posY = verticalTopPadding - 24;
		g.drawString(graphTitle, (int) posX, (int) posY);
	}
	
	private void setAxisResolutions()
	{
		if(blockWidth(axis.yAxis) <= 0.5)
			vResolution = maxVerticalValue;
		else if(blockWidth(axis.yAxis) <= 1)
			vResolution = maxVerticalValue/2;
		else if(blockWidth(axis.yAxis) <= 3)
			vResolution = 10;
		else if(blockWidth(axis.yAxis) <= 5)
			vResolution = 5;
		else if(blockWidth(axis.yAxis) <= 10)
			vResolution = 2;
		else
			vResolution = 1;
		
		if(blockWidth(axis.xAxis) <= 5)
			hResolution = 20;
		else if(blockWidth(axis.xAxis) <= 15)
			hResolution = 10;
		else if(blockWidth(axis.xAxis) <= 25)
			hResolution = 2;
		else
			hResolution = 1;
		
	}
	
	private double verticalResolution()
	{
		return vResolution*blockWidth(axis.yAxis);
	}
	
	private double horizontalResolution()
	{
		return hResolution*blockWidth(axis.xAxis);
	}
}
