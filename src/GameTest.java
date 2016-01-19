import java.util.Arrays;

import konsoleadventures.logic.Adventurer;
import konsoleadventures.logic.Armor;


public class GameTest {
	public static GameCreator gc;
	
	public GameTest(){
	gc = new GameCreator();
	int[] stats = {15,16,12,11};
	Adventurer player = new Adventurer("Nick", stats);
	gc.addPlayer(player);
	}
	

	public static void main(String[] args) {
		int[] stats = {15,16,12,11};
		Adventurer player = new Adventurer("Nick", stats);
		player.equipArmor(new Armor("Chestplate of Swag", "chest", 7));
		player.equipArmor(new Armor("Kickass pants", "pants", 5));
		player.equipArmor(new Armor("Dope gloves", "gloves", 5));
		player.equipArmor(new Armor("Headpiece of ill", "helm", 3));
		player.equipArmor(new Armor("Boots of trill", "boots", 3));
		System.out.println(player.getHealth());
		System.out.println(player.getArmor());
		System.out.println(Arrays.toString(player.getInventory()));
		System.out.println(Arrays.toString(player.getGear()));
		
		
		 

	}

}
