import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;


public class KonsoleArea implements Runnable {

	@Override
	public void run() {
		
		
		
		final JFrame txtFrame = new JFrame("Konsole");
        txtFrame.setLocation(500,500);
        
        //Text Field
        final JTextField console = new JTextField();
        txtFrame.add(console, BorderLayout.PAGE_END );
        
        //TxtGame area
        final TextGame txtgame = new TextGame(console);
        txtFrame.add(txtgame, BorderLayout.CENTER);

        final GameCreator game = new GameTest().gc;
		game.addTextGame(txtgame);
		
        console.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
            	game.recieve(console.getText());
            	txtgame.addText(console.getText());
            }});
        
		//initialize
		game.initialize();
        

        
        txtFrame.pack();
        txtFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        txtFrame.setVisible(true);
        
        
	}
	
	 public static void main(String[] args){
	        SwingUtilities.invokeLater(new KonsoleArea());
	    }
	
	

}
