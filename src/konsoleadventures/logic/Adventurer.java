package konsoleadventures.logic;
public class Adventurer {

	private String name;
	private int strength, dexterity, constitution, intelligence;
	private Item[] inventory = new Item[10];
	private Armor[] gear = new Armor[5];
	private int wepDmgMod, spellDmgMod, health, armor;
	private int gold;

	public Adventurer(String name, int[] stats) {
		this.name = name;
		this.strength = stats[0];
		this.dexterity = stats[1];
		this.constitution = stats[2];
		this.intelligence = stats[3];
		this.wepDmgMod = getModifier(strength);
		this.spellDmgMod = getModifier(intelligence);
		this.armor = getModifier(dexterity);
		this.health = constitution;
		this.gold = 20;
	}

	private int getModifier(int stat) {
		if (stat == 12 || stat == 13) {
			return 1;
		} else if (stat == 14 || stat == 15) {
			return 2;
		} else if (stat == 16 || stat == 17) {
			return 3;
		} else if (stat == 18) {
			return 4;
		} else
			return 0;
	}

	//decrements HP when hit
	public void takeHit(int dmg) {
		health -= dmg;
	}
	
	//adds item to inventory
	public boolean addItem(Item item)
	{
		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i] == null)
			{
				inventory[i] = item;
				return true;
			}
		}
		
		return false;
	}
	
	public boolean deleteItem(Item item)
	{
		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i].equals(item))
			{
				inventory[i] = null;
				return true;
			}
		}
		
		return false;
	}
	
	public boolean equipArmor(Armor a)
	{
		for (int i = 0; i < gear.length; i++)
		{
		if (gear[i] != null)
		{
			if (a.type.equals(gear[i].type)){
				gear[i] = null;
				gear[i] = a;
				armor += a.armorclass;
				return true;
			}
			
		}
		else if (gear[i] == null){
			gear[i] = a;
			armor += a.armorclass;
			return true;
		}
		}
			return false;
	}
	

	//returns the damage modifier due to the player's stats on a weapon attack
	public int getWepDamageMod() {
		return wepDmgMod;
	}

	//returns the damage modifier due to the player's stats on a spell attack
	public int getSpellDamageMod() {
		return spellDmgMod;
	}

	public int getHealth() {
		return health;
	}

	public int getArmor() {
		return armor;
	}
	
	public String getName(){
		return name;
	}
	
	public int getGold(){
		return gold;
	}
	
	public String[] getInventory(){
		String[] inv = new String[inventory.length];
		for (int i = 0; i < inventory.length; i++)
		{
			if (inventory[i] == null)
				inv[i] = "Empty";
			else
				inv[i] = inventory[i].name;
		}
		return inv;
	}
	
	public String[] getGear(){
		String[] gr = new String[gear.length];
		for (int i = 0; i < gear.length; i++)
		{
			if (gear[i] == null)
				gr[i] = "Empty";
			else
				gr[i] = gear[i].name;
		}
		return gr;
	}


	//returns the attack roll
	public int hitRoll() {
		return (int) ((Math.random() * 20) + 1) + wepDmgMod;
	}

	//returns the damage roll
	public int dmgRoll(Item i)
	 {	
		 int dmgmod = 0;
		 if (i.type.equals("weapon"))
		 	dmgmod = wepDmgMod;
		 else
			dmgmod = spellDmgMod;
		 return (int)((Math.random()* i.hitdice) + 1) + i.mod + dmgmod;
	 }

}
