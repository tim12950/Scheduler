import java.util.Scanner;
import java.util.ArrayList;

public class Application {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		ArrayList<Event> events = new ArrayList<Event>();
		Scheduler schedulerObject = new Scheduler();
		
		//event variables:
		String title;
		int startHour;
		int startMin;
		int duration;
		
		int mOption;
		//main menu option
		int eOption;
		//event menu option
		boolean x;
		//x prevents success messages from printing when exceptions are thrown.
		
		/*
		 * Note the input.nextLine() calls in some of the catch blocks below;
		 * apparently, when you enter a non-int in response to a nextInt() prompt,
		 * the Scanner object (input) does not get rid of this non-int and so when
		 * you loop back to input.nextInt(), it doesn't prompt you again for an input
		 * but instead triggers another exception. One way to stop this is to call nextLine(),
		 * which reads the input and also skips to the next line where you can start afresh next time.
		 */
		
		do {
			System.out.print("\n>> MAIN MENU <<\n1. Add an Event\n2. Display Events\n3. Schedule"
					+ "\n4. Exit"
					+ "\n>> Select your option (1-4): ");
			try {
			mOption = input.nextInt();
			}
			catch (Exception e) {
				input.nextLine();
				System.out.print("\nInvalid input! Note the option numbers!");
				mOption = 0;
				/*
				 * set mOption to 0 here; otherwise, if mOption was assigned an int from 1-3 from previous
				 * iterations of the loop, one of the conditionals below will be true again and we won't
				 * be confronted with just the main menu.
				*/
			}
			System.out.println("");
			
			if (mOption == 1) {
				do {
				System.out.print("\n>> EVENT MENU <<\n1. Add a Talk\n2. Add a Lecture\n3. Return to Main Menu"
						+ "\n>> Select your option (1-3): ");
					try {
					eOption = input.nextInt();
					input.nextLine();
					/*
					 * input.nextLine() avoids the problem where the same scanner object, when invoked on by nextInt() 
					 * and nextLine() consecutively and in that order, does not prompt the user for the next line because 
					 * it thinks that a newline character, left behind by a nextInt() that did receive an int, is the next 
					 * line and it reads that. But, like I said above, nextLine also skips to a new line so when you call
					 * nextWhatever() after calling nextLine(), the Scanner object has to prompt you.
					*/
					}
					catch (Exception e) {
						input.nextLine();
						System.out.print("\nInvalid input! Note the option numbers!");
						eOption = 0;
						//set eOption to 0 here for the same reason we set moption to 0.
					}
					System.out.println("");
					
					if (eOption == 1) {
						try {
							System.out.print("Enter the title of the talk: ");
							title = input.nextLine();
							System.out.println("");
							System.out.print("Enter the start time\nHour (0-23): ");
							startHour = input.nextInt();
							System.out.print("Minute (0-59): ");
							startMin = input.nextInt();
							System.out.println("");
							System.out.print("Enter talk's duration (in minutes): ");
							duration = input.nextInt();
							try{
								events.add(new Talk(title, startHour, startMin, duration));
								x = true;
							}
							catch (Exception e) {
								System.out.println(e.getMessage());
								x = false;
							}
							if (x) {
								System.out.println("\nTalk added!");
							}
						}
						catch (Exception e){
							input.nextLine();
							System.out.println("\nInvalid input! You can try adding the talk again via the event menu:");
						}
					}
					else if (eOption == 2) {
						try {
							System.out.print("Enter the title of the lecture: ");
							title = input.nextLine();
							System.out.println("");
							System.out.print("Enter the start time\nHour (0-23): ");
							startHour = input.nextInt();
							System.out.print("Minute (0-59): ");
							startMin = input.nextInt();
							try{
								events.add(new Lecture(title, startHour, startMin));
								x = true;
							}
							catch (Exception e){
								System.out.println(e.getMessage());
								x = false;
							}
							if (x) {
								System.out.println("\nLecture added!");
							}
						}
						catch (Exception e) {
							input.nextLine();
							System.out.println("\nInvalid input! You can try adding the lecture again via the event menu:");
						}
					}
					else if (eOption == 3) {
						System.out.println("Back to the main menu!");
					}
					else {
						System.out.println("Please select one of the options by entering an integer between 1 and 3, inclusive, "
								+ "via the event menu:");
					}
				}
				while (eOption != 3);
			}
			else if (mOption == 2) {
				int count = events.size();
				
				if (count > 0) {
					for (int i = 0; i < count; i++) {
						System.out.println(events.get(i));}
				}
				else {
					System.out.println("No events to display!");
				}
			}
			else if (mOption == 3) {
				if (events.size() > 0) {
					schedulerObject.createSchedule(events);
					int count = (schedulerObject.getSchedule()).size();
					
					for (int i = 0; i < count; i++) {
						System.out.println((schedulerObject.getSchedule()).get(i));
					}
				}
				else {
					System.out.println("No schedule to display because there are no events!");
				}
			}
			else if (mOption == 4) {
				input.close();
				System.out.print("Goodbye!");
			}
			else {
				System.out.println("Please select one of the options by entering an integer between 1 and 4, inclusive, "
						+ "via the main menu:");
			}
		}
		while (mOption != 4);
	}
	
}
