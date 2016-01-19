package konsoleadventures.logic;
public class Choice {

	Consequence conseq;
	String header;
	Event event;
	int choice;

	public Choice(int c, String h, Event e) {
		choice = c;
		header = h;
		event = e;
	}

	public int getnumChoice() {
		return choice;
	}

	public String getHeader() {
		return header;
	}

	public Event getEvent() {
		return event;
	}

	public void addConsequence(Consequence c) {
		conseq = c;
	}

	public Consequence getConseq() {
		return conseq;
	}

	public void setHeader(String a) {
		header = a;
	}

}
