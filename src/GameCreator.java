import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicBoolean;

import konsoleadventures.logic.Adventurer;
import konsoleadventures.logic.Event;

public class GameCreator {
	Adventurer player;
	HashMap<String, Event> events = new HashMap<String, Event>();
    TextGame txtgame;
	String currInput = "";
	AtomicBoolean ready = new AtomicBoolean(false);
	String temp_read = "";

	
	public void addTextGame(TextGame txtg){
		this.txtgame = txtg;
	}
	
	public void addEvent(String name, Event e)
	{
		events.put(name, e);
	}
	
	public void addPlayer(Adventurer player){
		this.player = player;
	}
	
	public void initialize()
	{
		txtgame.addText("Welcome to Konsole Adventures!");
		txtgame.addText("What is your name?");
		String playername = read();
		print("Okay, " + playername);
		System.out.println("Current Input: " + currInput);

	}
	
	public void recieve(String input)
	{
		currInput = input;
		ready.set(true);
		System.out.print("Recieved --> Ready!");
	}
	
	
	public String read()
	{
		while(!ready.compareAndSet(true, false))
		{
			
		}
		return currInput;
	}
	
	
	
	/*public void setVar(final String var){
		
		final Timer timer = new Timer();
		
		timer.scheduleAtFixedRate(new TimerTask() {
			 @Override
			  public void run() {
				  System.out.println("Tick");
				  if (ready){
					var = currInput;
					System.out.println("Stop Ticking");
					ready = false;
					timer.cancel();
				  }
			  }
			}, (long)1000, (long)1000);
				
	}
	
*/
	
	public void print(final String input) //prints only if user just made input
	{
		final Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			  @Override
			  public void run() {
				  
				  if (ready.get()){
					txtgame.addText(input);
					ready.set(false);
					timer.cancel();
				  }
			  }
			}, (long)1000, (long)1000);
		

	
	}
}
