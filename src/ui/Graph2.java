package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JComponent;

public class Graph2 extends JComponent {
	ArrayList<Point2D> list = new ArrayList<Point2D>();
	ArrayList<Point2D> bigList = new ArrayList<Point2D>();
	
	private static final int GRAPH_WIDTH = 1200;
	private static final int GRAPH_HEIGHT = 400;
	private final int numLines = 4;
	private final int DIAMETER = 4;
	private final double COMPRESSION = 0.5;
	private final int TICK_LENGTH = 5;
	
	public Graph2(ArrayList<Point2D> points, ArrayList<Point2D> bigPoints)
	{
		bigList.add(new Point2D.Double(1,1));
		list = points;
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		Font font = new Font("Times New Roman", Font.BOLD, 20);
		
		//plot all points except large donations.
		double total = list.get(list.size()-1).getY();
		for(Point2D p : list)
		{
			double x = p.getX()*COMPRESSION;
			double y = GRAPH_HEIGHT - p.getY()/total*GRAPH_HEIGHT;
			
			Ellipse2D point = new Ellipse2D.Double(x, y, DIAMETER, DIAMETER);
			g2.fill(point);
			g2.draw(point);
		}
		Line2D baseLine = new Line2D.Double(0,GRAPH_HEIGHT,GRAPH_WIDTH,GRAPH_HEIGHT);
		Line2D l = lineOfBestFit();
		
		g2.draw(baseLine);
		
		//Draw the small lines of best fit
		for( Line2D ll : linesOfBestFit())
		{
			g2.setColor(Color.BLUE);
			g2.draw(ll);
		}
		g2.setColor(Color.RED);
		Line2D flippedLine = new Line2D.Double( l.getX1()*COMPRESSION, GRAPH_HEIGHT-l.getY1(), 
												2*l.getX2()*COMPRESSION, GRAPH_HEIGHT-l.getY2());
		g2.draw(flippedLine);
		
		//Make a list of large donations
		g2.setFont(font);
		for(Point2D p : bigList)
		{
			g2.setColor(Color.GREEN);
			g2.drawString(""+p.getY(), (int)(p.getX()*COMPRESSION), GRAPH_HEIGHT-20);
		}
		
		//draw date labels
		g2.setColor(Color.black);
		for(int i = 0; i<10; i++)
		{
			int x = (int)(365*COMPRESSION*i);
			g2.drawLine(x, GRAPH_HEIGHT-TICK_LENGTH, x, GRAPH_HEIGHT+TICK_LENGTH);
			g2.drawString(""+(2008+i), x, GRAPH_HEIGHT+20);
		}
	}
	
	private Line2D lineOfBestFit()
	{
		Line2D line = new Line2D.Double();
		int n = list.size();
		double sumX = 0, sumY = 0, sumXX = 0, sumXY = 0, slope = 0, intercept = 0;
		double total = list.get(n-1).getY();
		
		for(Point2D p : list)
		{
			double x = p.getX();
			double y = p.getY()/total*GRAPH_HEIGHT;
			
			sumX += x;
			sumY += y;
			sumXX+= x*x;
			sumXY+= x*y;
		}
		
		slope = (n*sumXY - sumX*sumY) / (n*sumXX - sumX*sumX);
		intercept = (sumY - slope*sumX)/n;		
		
		line = new Line2D.Double(0.0, intercept, GRAPH_WIDTH, 2*slope*GRAPH_WIDTH+intercept);
		return line;
	}
	
	private ArrayList<Line2D> linesOfBestFit()
	{
		ArrayList<Line2D> lines = new ArrayList<Line2D>();
		Line2D l = new Line2D.Double();
		
		int n = list.size()/numLines;
		int counter = 0;
		double sumX = 0, sumY = 0, sumXX = 0, sumXY = 0, slope = 0, intercept = 0, holdX = 0;
		double total = list.get(list.size()-1).getY();
		Point2D p1,p2;
		
		for(Point2D p : list)
		{
			double x = p.getX();
			double y = p.getY()/total*GRAPH_HEIGHT;
			
			sumX += x;
			sumY += y;
			sumXX+= x*x;
			sumXY+= x*y;
			if(counter == 0)
			{
				holdX = x;
			}
			counter++;
			if(counter == n)
			{
				slope = (n*sumXY - sumX*sumY) / (n*sumXX - sumX*sumX);
				intercept = (sumY - slope*sumX)/n;
				p1 = new Point2D.Double(holdX*COMPRESSION, GRAPH_HEIGHT - intercept - slope*holdX);
				p2 = new Point2D.Double(x*COMPRESSION, GRAPH_HEIGHT - intercept -slope*x);
				l = new Line2D.Double(p1,p2);
				
				lines.add(l);
				counter=0;
				sumX = 0; sumY = 0; sumXY = 0; sumXX = 0;
			}
		}
		return lines;
	}
}
