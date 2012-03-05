package output;

import input.Event;

import java.util.GregorianCalendar;
import java.util.List;

public class SortedList extends Output{
	
	
	
	public SortedList(List<Event> eventList) {
		super(eventList);
		// TODO Auto-generated constructor stub
	}

	
	public void generate(GregorianCalendar first, GregorianCalendar last)
	{
		

		try
		{
			// -- not sure exactly how to call this right now but once processor is done should be easy
			//Sorting sort = new Sorting(eventList);
			//sort.sorting(EventList); 
			
			writer("Calendar.html");

			br.write(header("Sorted Event List"));
			
			start = eventList.get(0).getStartDate();
			end = eventList.get(eventList.size()-1).getEndDate();
			
			Tag table = new Tag("table","border",1);
			
			table.addInnerHTML(rowCol(start,end,sort));

			br.write(table.getHTML());
			close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void generate()
	{
		

		try
		{
			// -- not sure exactly how to call this right now but once processor is done should be easy
			//Sorting sort = new Sorting(eventList);
			//sort.sorting(EventList); 
			
			writer("Calendar.html");

			br.write(header("Sorted Event List"));
			
			start = eventList.get(0).getStartDate();
			end = eventList.get(eventList.size()-1).getEndDate();
			
			Tag table = new Tag("table","border",1);
			
			table.addInnerHTML(rowCol());

			br.write(table.getHTML());
			close();
		}
		catch (Exception e)
		{
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
