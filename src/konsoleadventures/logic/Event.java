package konsoleadventures.logic;
import java.util.ArrayList;


public class Event {

ArrayList<Choice> eventchoices = new ArrayList<Choice>();
String description, title;

public void addTitle(String titl)
{
 title = titl; 
}

public void addDescrip(String descrip)
{
 description = descrip;
}

public void addChoice(int num, String header, Event choice)
{
  eventchoices.add(num-1, new Choice(num, header, choice));
}

public void addConseq(Event e, String change)
{ 
  
  for (int i= 0; i < eventchoices.size(); i++)
  {
    if (eventchoices.get(i).getEvent().equals(e))
    {
     eventchoices.get(i).addConsequence(new Consequence(e, change));
    }
}
}

public String getTitle()
{
 return title;
}


}
