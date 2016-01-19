package konsoleadventures.logic;

public class Item {
	
	String name;
	String type;
	int hitdice;
	int mod;
	
	public Item(String name, String type, int hitdice, int mod)
	{
		this.name = name;
		this.type = type;
		this.hitdice = hitdice;
		this.mod = mod;
	}

}
