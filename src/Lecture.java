
public class Lecture extends Event{
	
	public Lecture (String title, int startHour, int startMin) throws Exception {
		super(title, startHour, startMin);
		if (startTime + 60 >= 1440) {
			throw new Exception("\nA lecture may not reach or go past midnight!");
		}
		endTime = startTime + 60;
		type = "Lecture";
	}

}
