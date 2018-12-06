package printer;

public class Event {
	private Job job;
	private int arrive_time;
	
	public Event(Job job, int arrive_time) {
		super();
		this.job = job;
		this.arrive_time = arrive_time;
	}
	
	/**
	 * @return the job
	 */
	public Job getJob() {
		return job;
	}
	/**
	 * @param job the job to set
	 */
	public void setJob(Job job) {
		this.job = job;
	}
	/**
	 * @return the arrive_time
	 */
	public int getArrive_time() {
		return arrive_time;
	}
	/**
	 * @param arrive_time the arrive_time to set
	 */
	public void setArrive_time(int arrive_time) {
		this.arrive_time = arrive_time;
	}

	
	
}
