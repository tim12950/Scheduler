
public class Event {

	private String title;
	protected String type;
	protected int startTime;
	protected int endTime;
	//startTime and endTime both in minutes.
	
	public Event(String title, int startHour, int startMin) throws Exception {
		if ((title.trim()).equals("")) {
			throw new Exception("\nEvent NOT added! Please give your event a title!");
		}
		if (startHour < 0 || startHour >23 || startMin < 0 || startMin > 59) {
			throw new Exception ("\nEvent NOT added! Note the bounds on the start hour and the start minute!");
		}
		else {
			this.title = title;
			startTime = (startHour * 60) + startMin;
		}
	}
	/*
	 * Note that in the above constructor, and the constructors that extend this one, if there are multiple unacceptable
	 * inputs, only one exception (the earliest one reached) will be thrown.
	 */
	
	public int getEndTime() {
		return endTime;
	}
	
	public int getStartTime() {
		return startTime;
	}
	
	public int formatHour(int timeInMin) {
		if (((timeInMin/60) % 12 ) == 0) {
			return 12;
		}
		else {
			return ((timeInMin/60) % 12);
		}
	}
	
	public String formatAMPM(int timeInMin) {
		if (timeInMin < 720) {
			return "AM";
		}
		else {
			return "PM";
		}
	}
	
	//override the toString() method
	public String toString() {
		return String.format("%s: %s (%d:%02d %s to %d:%02d %s)", type, title, formatHour(startTime), 
				(startTime % 60), formatAMPM(startTime), formatHour(endTime), (endTime % 60), formatAMPM(endTime));
	}
	
}
