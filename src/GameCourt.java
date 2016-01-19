/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

/**
 * GameCourt
 * 
 * This class holds the primary game logic of how different objects 
 * interact with one another.  Take time to understand how the timer 
 * interacts with the different methods and how it repaints the GUI 
 * on every tick().
 *
 */
@SuppressWarnings("serial")
public class GameCourt extends JPanel {

	// the state of the game logic
	private Actor player;          // the Black Square, keyboard control
	private Actor enemy;
	private Circle snitch;          // the Golden Snitch, bounces
	private Poison poison;          // the Poison Mushroom, doesn't move
	
	public boolean playing = false;  // whether the game is running
	private JLabel status;       // Current status text (i.e. Running...)
	//public static final GameCreator game = GameTest.gc;

	// Game constants
	public static final int COURT_WIDTH = 300;
	public static final int COURT_HEIGHT = 300;
	public static final int SQUARE_VELOCITY = 4;
	// Update interval for timer in milliseconds 
	public static final int INTERVAL = 35; 

	public GameCourt(JLabel status){
		// creates border around the court area, JComponent method
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
        
        // The timer is an object which triggers an action periodically
        // with the given INTERVAL. One registers an ActionListener with
        // this timer, whose actionPerformed() method will be called 
        // each time the timer triggers. We define a helper method
        // called tick() that actually does everything that should
        // be done in a single timestep.
		Timer timer = new Timer(INTERVAL, new ActionListener(){
			public void actionPerformed(ActionEvent e){
				tick();
			}
		});
		timer.start(); // MAKE SURE TO START THE TIMER!

		// Enable keyboard focus on the court area
		// When this component has the keyboard focus, key
		// events will be handled by its key listener.
		setFocusable(true);

		// this key listener allows the square to move as long
		// as an arrow key is pressed, by changing the square's
		// velocity accordingly. (The tick method below actually 
		// moves the square.)
		
		
		addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.v_x = -SQUARE_VELOCITY;
					player.setCurrentDirection(Direction.LEFT);
				}
				else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.v_x = SQUARE_VELOCITY;
					player.setCurrentDirection(Direction.RIGHT);
				}
				else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					player.v_y = SQUARE_VELOCITY;
				}
				else if (e.getKeyCode() == KeyEvent.VK_UP) {
					player.jumped = true;
				}
				else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					player.drawsword = true;
				}
				
				else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
					player.spellcast = true;
					System.out.println("Spell Cast!");
					}
					
				
			}
			public void keyReleased(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_UP) {
					player.v_y = 0;
				}
				
				else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.v_x = 0;
				}
				
				
			}
		});

		this.status = status;
	}

	/** (Re-)set the state of the game to its initial state.
	 */
	public void reset() {

		player = new Actor(COURT_WIDTH, COURT_WIDTH, 0, "pb_right.png", "pb_left.png", "pb_spell_right.png",
				"pb_spell_left.png", "pb_wep_right.png", "pb_wep_left.png");
		//player.addCharacter(game.player);
		
		enemy = new Actor(COURT_WIDTH, COURT_WIDTH, COURT_WIDTH - 100, "pb_right.png", "pb_left.png", "pb_spell_right.png",
				"pb_spell_left.png", "pb_wep_right.png", "pb_wep_left.png");
		enemy.setCurrentDirection(Direction.LEFT);
		poison = new Poison(COURT_WIDTH, COURT_HEIGHT);
		snitch = new Circle(COURT_WIDTH, COURT_HEIGHT);

		playing = true;
		status.setText("Running...");

		// Make sure that this component has the keyboard focus
		requestFocusInWindow();
	}

    /**
     * This method is called every time the timer defined
     * in the constructor triggers.
     */
	void tick(){
		if (playing) {
			// advance the square and snitch in their
			// current direction.
			
			player.move();
			enemy.move();
			snitch.move();
			

			// make the snitch bounce off walls...
			snitch.bounce(snitch.hitWall());
			
			// ...and the mushroom
			snitch.bounce(snitch.hitObj(poison));
		
			// check for the game end conditions
			if (player.intersects(poison)) { 
				playing = false;
				status.setText("You lose!");

			} else if (player.intersects(snitch)) {
				playing = false;
				status.setText("You win!");
			}  
			
			if (player.intersects(enemy)){
				System.out.println("Player hit enemy!");
				player.bounce(player.currentDirection);
			}
			
			if (player.spell.willIntersect(enemy))
			{
				System.out.println("Spell Hit enemy!!");
				System.out.println("Spell pos= " + player.spell.pos_x);
				System.out.println("Enemy pos= " + enemy.pos_x);
				player.spell.hitEnemy = true;
				player.spell.hit(player.spell.currentDirection);
				player.spell.vanish();
				player.spell.hitEnemy = false;

			}
				
			
			// update the display
			repaint();
		} 
	}

	@Override 
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		player.draw(g);
		enemy.draw(g);
		poison.draw(g);
		snitch.draw(g);
		//System.out.println("painted!");
	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(COURT_WIDTH,COURT_HEIGHT);
	}
}
