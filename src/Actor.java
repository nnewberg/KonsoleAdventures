/**
 * CIS 120 HW10
 * (c) University of Pennsylvania
 * @version 2.0, Mar 2013
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import konsoleadventures.logic.Adventurer;

/** A basic game object displayed as a black square, starting in the 
 * upper left corner of the game court.
 *
 */
public class Actor extends GameObj {
	public static final int SIZE = 32;
	public static int INIT_X = 0;
	public static final int INIT_Y = 0;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	
	public static String right_img;
	public static String left_img;
	public static String spell_right_img;
	public static String spell_left_img;
	public static String wep_right_img;
	public static String wep_left_img;
	private static BufferedImage currimg;
	public boolean spellcast = false;
	public boolean drawsword = false;
	public int hitTimer = 0;
	public Spell spell;
	public Adventurer character;
	
    /** 
     * Note that because we don't do anything special
     * when constructing a Square, we simply use the
     * superclass constructor called with the correct parameters 
     */
    public Actor(int courtWidth, int courtHeight, int initx, String rightimg, String leftimg, String spellrightimg,
    		String spellleftimg, String weprightimg, String wepleftimg){
    
        super(INIT_VEL_X, INIT_VEL_Y, initx, courtHeight, 
        		SIZE, SIZE, courtWidth, courtHeight);
        
        right_img = "resources/characters/" + rightimg;
        left_img = "resources/characters/" + leftimg;
        spell_right_img = "resources/characters/" + spellrightimg;
        spell_left_img = "resources/characters/" + spellleftimg;
        wep_right_img = "resources/characters/" + weprightimg;
        wep_left_img = "resources/characters/" + wepleftimg;
        createImg(right_img);
		spell = new Spell(max_x, max_y, this);

    }
    
    public void addCharacter(Adventurer character){
    	this.character = character;
    }
    
 public void jump(){
		if (jumpcount < 8)
		{
			v_y = -7;
			jumpcount ++;
		} 
		else if (jumpcount >= 8  && jumpcount < 16)
		{
			v_y = 7;
			jumpcount ++;
		}
		else if (jumpcount == 16)
		{
			v_y = 0;
			jumpcount = 0;
			jumped = false;
		}
 }
    
    @Override
public void move(){
		if (jumped)
		{
			jump();
		}
		
		pos_x += v_x;
		pos_y += v_y;

		clip();
	}

    @Override
    public void draw(Graphics g) {
    	
    	if (spellcast)
    	{
    		if (spell.launched == 0) //initial launching
    		{
    		spell.launched = 1;
    		}
    		
    		spell.draw(g);
    	}
    	
    	changeImg();
    	
    	if (drawsword)
    	{
    		if (hitTimer > 10){
    			drawsword = false;
    			hitTimer = 0;
    		}
    		else
    			hitTimer ++;
    		
    		System.out.println("Hit Timer= " + hitTimer);
    			
    	}
    	
        g.drawImage(currimg, pos_x, pos_y, width, height, null); 
    }
    
    public void createImg (String src) {
    	try {
			
		currimg = ImageIO.read(new File(src));
			
		} catch (IOException e) {
			System.out.println("Internal Error:" + e.getMessage());
		}
    }
    
    public void changeImg () {
    	if (currentDirection.equals(Direction.LEFT)) {
    		
    		if (spellcast)
        		createImg(spell_left_img);
    		else if(drawsword)
    		{
    			createImg(wep_left_img);
    		}
    		else
    			createImg(left_img);
    	}
    	
    	else if (currentDirection.equals(Direction.RIGHT)) {
    		if (spellcast)
        		createImg(spell_right_img);
    		else if (drawsword)
    		{
    			createImg(wep_right_img);
    		}
    		else
    			createImg(right_img);
    	}
    	
    	
    }
    
    public Point getPosition () {
    	return new Point (this.pos_x, this.pos_y);
    }

}
