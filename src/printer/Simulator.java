package printer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Simulator {
	protected final int secondes_per_page = 2;
	protected Queue<Event> workload = new Queue<>();
	protected Queue<Event> object = new Queue<>();
	
	public Simulator() throws FileNotFoundException {
		loadWorkLoad();
	}
	
	public void loadWorkLoad() throws FileNotFoundException {
		File file  = new File("bigfirst.run");
		Scanner input = new Scanner(file);
		
		while (input.hasNext()) {
			String jobInfo = input.nextLine();
			String[] attributes = jobInfo.split(" ");
			if (attributes.length == 3) {
				int page = Integer.parseInt(attributes[1]);
				int arriveTime = Integer.parseInt(attributes[0]);
				Job job = new Job(attributes[2], page);
				Event event = new Event(job, arriveTime);
				workload.push(event);
			}
		}
		input.close();
	}
	
}
