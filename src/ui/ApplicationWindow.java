package ui;

import javax.swing.*;

public class ApplicationWindow
{
	public static void main(String[] args)
	{
		JFrame frame = new HomeFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Analysis");
		frame.setVisible(true);
	}
}
