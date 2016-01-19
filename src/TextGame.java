import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.Timer;

public class TextGame extends JTextPane {
	
	boolean enterpressed = false;
	public static final int COURT_WIDTH = 300;
	public static final int COURT_HEIGHT = 300;
	String output = "";
	JTextField console;
	StringBuilder sb = new StringBuilder();



	
	public TextGame(JTextField txtf)
	{
		
	  this.setContentType("text/html");
	  this.setEditable(false);
	  this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	
	  
		
		setFocusable(true);
		console = txtf;

		
		Timer timer = new Timer(35, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				repaint();
			}
		});
		timer.start(); // MAKE SURE TO START THE TIMER!

	}
			
	@Override 
	public void paintComponent(Graphics g)
	{
		
		super.paintComponent(g);
		if (enterpressed)
		{
		this.setText(sb.toString());
		System.out.println("paint: " + output);
		enterpressed = false;
		}
	}
		

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(COURT_WIDTH,COURT_HEIGHT);
	}

	
	public void addText(String text) {
		sb.append(text + " <br>");
		enterpressed = true;
		console.setText("");
	}
}