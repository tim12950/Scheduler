import java.util.ArrayList;
import java.util.Collections;

public class Scheduler {
	
	private ArrayList<Event> schedule = new ArrayList<Event>();
	private int numOfEvents = 0;
	//number of events in the events array most recently passed to createSchedule()
	private ArrayList<Integer> ftimes = new ArrayList<Integer>();
	//ftimes stand for finishing times
	private int index = 0;
	//the index of where we'll begin adding to the ftimes array the next time we call createSchedule()
	
	public Scheduler() {
		//empty constructor
	}
	
	public void createSchedule(ArrayList<Event> events) {
		if (events.size() == numOfEvents) {
			return;
		}
		//since we can't remove events, ArrayList<Event> e1 = ArrayList<Event> e2 iff e1.size() = e2.size();
		//so if we get an array list of events that's the same size as the one we got previously,
		//we know it's the same, unaltered array and so we don't need to re-schedule.
		
		numOfEvents = events.size();
		
		for (int i = index; i < numOfEvents; i++) {
			ftimes.add((events.get(i)).getEndTime());
		}
		Collections.sort(ftimes);
		index = numOfEvents - index;
		
		 //This populates and sorts the finishing times, and also sets index.
		
		/*
		 * How the greedy algorithm is implemented below:
		 * Since ftimes is the sorted (ascending) array of finishing times, we loop through it as follows:
		 * for each ftime, we search for (and are guaranteed to find) an event with an identical ftime in the events
		 * array, and add it to the schedule if the schedule is empty or if it is compatible with the last event in the
		 * schedule.
		 * 
		 * Note: Either ftimes contains duplicates or it doesn't.
		 * If it doesn't, then the association between the events and the ftimes under getEndTimes() is a bijection,
		 * and so each event is a candidate for membership within schedule (the second if-statement in the inner for-loop)  
		 * exactly once. Therefore, there is no possibility of adding the same event to the schedule more than once.
		 * 
		 * If ftimes does contain duplicates, then the association is not 1-1.
		 * Therefore, an event with the same ftime as another will be candidate for membership within schedule more than once,
		 * but this will NOT cause duplicates to appear in schedule. That is, suppose e_1,...,e_n are events that have the same
		 * ftimes, and that e_i is the first of these events added to the schedule. Then it is the only one of these events
		 * added to the schedule because the start times of the rest of the events are before the ftime of e_i, and the
		 * second if-statement in the inner for-loop will not allow such events to be added to the schedule.
		 * 
		 * Also: there may be multiple optimal schedules, and which one this implementation gives us is determined by the order
		 * in which we added the events.
		 */
		
		schedule.clear();
		//Important! If we don't clear we end up with duplicate events!
		
		for (int i = 0; i < numOfEvents; i++) {
			for (int j = 0; j < numOfEvents; j++) {
				if (ftimes.get(i) == ((events.get(j)).getEndTime())) {
					if (i == 0 || ((schedule.get(schedule.size()-1)).getEndTime()) <= (events.get(j)).getStartTime()) {
					//because of the way (L||R) is processed, R doesn't get evaluated when schedule.size()-1 would be = -1.
					schedule.add(events.get(j));
					break;
					//exit inner loop right after we get what we want
					}
				}
			}
		}
	}
	
	public ArrayList<Event> getSchedule() {
		return schedule;
	}
	
}
