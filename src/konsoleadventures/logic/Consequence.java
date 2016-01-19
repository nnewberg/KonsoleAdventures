package konsoleadventures.logic;
public class Consequence {

	Event etochange;
	String change;

	public Consequence(Event e, String c) {
		etochange = e;
		change = c;
	}

	public Event getEvent() {
		return etochange;
	}

	public String getChange() {
		return change;
	}

}
