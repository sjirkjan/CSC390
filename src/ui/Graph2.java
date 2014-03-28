package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class Graph2 extends JComponent {
	ArrayList<Point2D> list = new ArrayList<Point2D>();

	private static final int FRAME_WIDTH = 400;
	private static final int FRAME_HEIGHT = 400;
	private static final int BORDER = 100;
	
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(FRAME_WIDTH,FRAME_HEIGHT + BORDER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ArrayList<Point2D> points = new ArrayList<Point2D>();
		points.add(new Point2D.Double(10, 0));
		points.add(new Point2D.Double(20, 20));
		points.add(new Point2D.Double(30, 40));
		points.add(new Point2D.Double(40, 80));
		points.add(new Point2D.Double(50, 40));
		points.add(new Point2D.Double(60, 20));
		
		Graph2 component = new Graph2(points);
		frame.add(component);
		
		frame.setVisible(true);
	}
	
	public Graph2(ArrayList<Point2D> points)
	{
		
		list = points;
	}
	
	public void paintComponent(Graphics g)
	{
		Graphics2D g2 = (Graphics2D)g;
		
		
		for(Point2D p : list)
		{
			int x = (int)p.getX();
			int y = FRAME_HEIGHT - (int)p.getY();
			System.out.println(x + " " + y);
			
			Ellipse2D point = new Ellipse2D.Double(x+3,y+3,6,6);
			g2.fill(point);
			g2.draw(point);
		}
		Line2D l = lineOfBestFit();
		g2.draw(l);
	}
	
	private Line2D lineOfBestFit()
	{
		Line2D line = new Line2D.Double();
		double sumX = 0, sumY = 0, sumXX = 0, sumXY = 0, slope = 0, intercept = 0;
		int n = list.size();
		for(Point2D p : list)
		{
			double x = p.getX();
			double y = p.getY();
			
			sumX += x;
			sumY += y;
			sumXX+= x*x;
			sumXY+= x*y;
		}
		
		slope = (n*sumXY - sumX*sumY) / (n*sumXX - sumX*sumX);
		intercept = (sumY - slope*sumX)/n;
		
		System.out.println(slope + " : " + intercept);
		line = new Line2D.Double(0.0,FRAME_HEIGHT-intercept, (double)FRAME_WIDTH,FRAME_HEIGHT-slope*FRAME_HEIGHT);
		return line;
	}

}
