import java.io.Serializable;
import java.util.GregorianCalendar;

public class TimeLine implements Serializable{
	
	private GregorianCalendar startTime;
	private GregorianCalendar endTime;
	private String startHours;
	private String startMinutes;
	private String endHours;
	private String endMinutes;
	
	public TimeLine(int startHour, int startMinute, int endHour, int endMinute){
		startTime = new GregorianCalendar();
		endTime = new GregorianCalendar();
		setStartTime(startHour, startMinute);
		setEndTime(endHour, endMinute);
	}
	
	public String getStartMinutes(){
		return startMinutes;
	}
	public String getStartHours(){
		return startHours;
	}
	public String getEndHours(){
		return endHours;
	}
	public String getEndMinutes(){
		return endMinutes;
	}
	
	// get/set methode voor startTime, dit is de begin tijd van een festival.
	// de methode set heeft 2 ints, één voor de uren, één voor de minuten.
	// get/set methode voor endTime, dit is de eindtijd van een festival.
	// deze methodes geven  de tijd in uren en minuten terug, in de vorm van een string.
	// de standaard datum hebben we gekozen op 1-1-2016.
	public void setStartTime(int hourOfDay, int minute){
		startTime.set(2016, 1, 1, hourOfDay, minute);
	}
	public String getStartTime(){
		if(startTime.getTime().getHours() < 10)
		{
			startHours = "0" + startTime.getTime().getHours();
		}
		else
		{
			startHours = "" + startTime.getTime().getHours();
		}
		if(startTime.getTime().getMinutes() < 10){
			startMinutes = "0" + startTime.getTime().getMinutes();
		}
		else
		{
			startMinutes = "" + startTime.getTime().getMinutes();
		}
		String start = (startHours + ":" + startMinutes);
		return start;
	}
	public void setEndTime(int hourOfDay, int minute){
		endTime.set(2016, 1, 1, hourOfDay, minute);
	}
	public String getEndTime(){
		if(endTime.getTime().getHours() < 10)
		{
			endHours = "0" + endTime.getTime().getHours();
		}
		else
		{
			endHours = "" + endTime.getTime().getHours();
		}
		if(endTime.getTime().getMinutes() < 10){
			endMinutes = "0" + endTime.getTime().getMinutes();
		}
		else
		{
			endMinutes = "" + endTime.getTime().getMinutes();
		}
		String end = (endHours + ":" + endMinutes);
		return end;
	}
}

