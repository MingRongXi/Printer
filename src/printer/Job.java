package printer;

public class Job {
	private String user;
	private int number_of_pages;
	


	public Job(String user, int number_of_pages) {
		this.user = user;
		this.number_of_pages = number_of_pages;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the number_of_pages
	 */
	public int getNumber_of_pages() {
		return number_of_pages;
	}

	/**
	 * @param number_of_pages the number_of_pages to set
	 */
	public void setNumber_of_pages(int number_of_pages) {
		this.number_of_pages = number_of_pages;
	}
	
	
	
}
