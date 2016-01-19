import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Spell extends GameObj {
	public static final int SIZE = 20;
	public static final int INIT_X = 0;
	public static final int INIT_Y = 0;
	public static final int INIT_VEL_X = 0;
	public static final int INIT_VEL_Y = 0;
	public static final String right_img = "resources/spells/ice_spell.png";
	public static final String left_img = "resources/spells/ice_spell.png";
	public Actor caster;
	private BufferedImage currimg;
	int launched = 0;
   	int launched_y= 0;
	int launched_x= 0;
	Direction launchDir = Direction.RIGHT;
	boolean hitEnemy = false;

	
	public Spell(int courtWidth, int courtHeight, Actor caster){
        super(INIT_VEL_X, INIT_VEL_Y, caster.pos_x, caster.pos_y, 
        		SIZE, SIZE, courtWidth, courtHeight);
        
        this.caster = caster;
        
}
	
	 @Override
	 public void move(){
	 		
		 if (launchDir.equals(Direction.RIGHT) && !hitEnemy){
			 v_x = 5;
		 }
		 else if (launchDir.equals(Direction.LEFT) && !hitEnemy)
			 v_x = -5;
		 
			pos_x += v_x;
			pos_y += v_y;

			clip();
	 	 
	 	 
	 	 if (pos_x >= max_x || pos_x <= 0)
	 	 {
	 		vanish();
	 	 }
	 	 
	 	}
	 
	 public void vanish()
	 {
 		 System.out.println("Spell Stopped! @: " + pos_x);
		 caster.spellcast = false;
 		 launched = 0;
 		 this.bounce(getCurrentDirection());
 		 hitEnemy= false;
 		 this.setCurrentDirection(Direction.RIGHT);
 		 
	 }

    @Override
    public void draw(Graphics g) {
    	
    	if (launched == 1)//if just launched (initializes launch, only does this once)
    	{
    		launched_y = caster.getPosition().y;
    		launched_x = caster.getPosition().x;
    		launchDir = caster.getCurrentDirection();
    		pos_x = launched_x;
    		pos_y = launched_y;
    		launched ++;
    	}
    	
    	move();
        changeImg();
        g.drawImage(currimg, pos_x, launched_y, width, height, null); 
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
    			createImg(left_img);
    	}
    	
    	else if (currentDirection.equals(Direction.RIGHT)) {
    			createImg(right_img);
    	}

    

}
}