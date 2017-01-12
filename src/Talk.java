
public class Talk extends Event{
	
	public Talk(String title, int startHour, int startMin, int duration) throws Exception{
		super(title, startHour, startMin);
		if (duration <= 0) {
			throw new Exception("\nThe duration must be a positive integer!");
		}
		if (startTime + duration >= 1440) {
			throw new Exception("\nA talk may not reach or go past midnight!");
		}
		endTime = startTime + duration;
		type = "Talk";
	}

}
