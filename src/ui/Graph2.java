package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Graph2 extends JComponent {
	ArrayList<Point2D> list = new ArrayList<Point2D>();

	private static final int FRAME_WIDTH = 1200;
	private static final int FRAME_HEIGHT = 400;
	private static final int BORDER = 100;

	
	public Graph2(ArrayList<Point2D> points)
	{
		
		list = points;
	}

	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		System.out.println("--------------------------------------------------------");
		System.out.println(list.toString());
		System.out.println("--------------------------------------------------------");
		double total = list.get(list.size()-1).getY();
		for(Point2D p : list)
		{
			double x = p.getX();
			double y = FRAME_HEIGHT - p.getY()/total*FRAME_HEIGHT;
			System.out.println(x +  "    " + y + "   " + p.getY()); 
			Ellipse2D point = new Ellipse2D.Double(x, y, 4, 4);
			g2.fill(point);
			g2.draw(point);
		}
		Line2D baseLine = new Line2D.Double(0,400.0,FRAME_WIDTH,400.0);
		Line2D l = lineOfBestFit();
		
		g2.draw(baseLine);
		//g2.draw(l);
		Line2D flippedLine = new Line2D.Double(l.getX1(),FRAME_HEIGHT-l.getY1(),l.getX2(),FRAME_HEIGHT-l.getY2());
		g2.draw(flippedLine);
	}
	
	private Line2D lineOfBestFit()
	{
		Line2D line = new Line2D.Double();
		double sumX = 0, sumY = 0, sumXX = 0, sumXY = 0, slope = 0, intercept = 0;
		int n = list.size();
		double total = list.get(list.size()-1).getY();
		for(Point2D p : list)
		{
			double x = p.getX();
			double y = p.getY()/total*FRAME_HEIGHT;
			
			sumX += x;
			sumY += y;
			sumXX+= x*x;
			sumXY+= x*y;
		}
		
		slope = (n*sumXY - sumX*sumY) / (n*sumXX - sumX*sumX);
		intercept = (sumY - slope*sumX)/n;
		
		System.out.println("SLOPE:" + slope + "\nINTERCEPT:" + intercept);
		line = new Line2D.Double(0.0,intercept, (double)FRAME_WIDTH,slope*FRAME_WIDTH+intercept);
		return line;
	}
	
	private ArrayList<Line2D> linesOfGoodFit()
	{
		
		ArrayList<Line2D> lines = new ArrayList<Line2D>();
		Line2D l;
		
		double sumX = 0, sumY = 0, sumXX = 0, sumXY = 0, slope = 0, intercept = 0, holdX = 0;
		int numLines = 3;
		int n = list.size()/3;
		double total = list.get(list.size()-1).getY();
		int counter = 0;
		l = new Line2D.Double();
		Point2D p1,p2;
		
		for(Point2D p : list)
		{
			double x = p.getX();
			double y = p.getY()/total*FRAME_HEIGHT;
			
			sumX += x;
			sumY += y;
			sumXX+= x*x;
			sumXY+= x*y;
			if(counter == 0)
			{
				holdX = x;
			}
			if(counter == n)
			{
				slope = (n*sumXY - sumX*sumY) / (n*sumXX - sumX*sumX);
				intercept = (sumY - slope*sumX)/n;
				p1 = new Point2D.Double(holdX,FRAME_HEIGHT-intercept - slope*holdX);
				p2 = new Point2D.Double(x,FRAME_HEIGHT - intercept -slope*x);
				l = new Line2D.Double(p1,p2);
				lines.add(l);
			}
			counter++;
		}
		
		System.out.println("SLOPE:" + slope + "\nINTERCEPT:" + intercept);
		return lines;
	}
}
