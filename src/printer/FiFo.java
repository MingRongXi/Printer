package printer;

import java.io.FileNotFoundException;

public class FiFo extends Simulator{
	private int totalTime = 0;
	//waitTime为每一页的等待时间，当得到一个新的页时，将它的waitTime设为0
	private int waitTime = -1;
	private Event loadEvent = workload.top();
	private Event waitEvent = null;
	//status标记当前的一个作业是否正在被打印
	private boolean status = true;
	private int page = 0;
	private int totalJob = 0;
	private float totalLatency = 0;
	
	
	public FiFo() throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void simulate() throws FileNotFoundException {	
		//当两个队列都不为空时，则程序继续执行
		while(!workload.isEmpty() || !object.isEmpty()) {
			totalTime++;
			loadToPrinter();
			printObject();
		}
		
		System.out.println();
		System.out.println("*****************************");
		System.out.println("Total job : " + totalJob);
		System.out.println("Total latency : " + totalLatency);
		System.out.println("Mean latency : " + totalLatency/totalJob);
	}
	
	
	/*
	 * 该方法将上传队列里的工作上传到等待打印的队列
	 */
	private void loadToPrinter() {
		while(totalTime == loadEvent.getArrive_time()) {
			System.out.println("Arrive: " + loadEvent.getJob().getNumber_of_pages() + " pages from " + loadEvent.getJob().getUser() + " at  " + totalTime + " second");
			workload.pop();
			object.push(loadEvent);
			if(!workload.isEmpty()) {
				loadEvent = workload.top();
			}
			else {
				break;
			}
		}
	}
	
	private void printObject() {
		if(!object.isEmpty()) {
			//当第一次打印时，waitEvent为空
			if(waitEvent == null){
				waitEvent = object.top();
				waitTime = 0;
			}

			//当前作业时第一次执行的情况下，将页数进行初始化
			if(status == true) {
				page = waitEvent.getJob().getNumber_of_pages();
				totalLatency = totalLatency + (totalTime - waitEvent.getArrive_time());
				totalJob++;
			}
			
			if(waitTime == 0 && status == true) {    //status是针对一项工作，waitTime是针对工作里的每一页
				System.out.println("Service: " + page + " pages from " + waitEvent.getJob().getUser() + " at " + totalTime + " second");
				status = false;
			}
			
			waitTime++;
			if(waitTime == secondes_per_page) {
				page--;
				waitTime = 0;
				if(page == 0) {
					object.pop();
					if(!object.isEmpty()) {
						waitEvent = object.top();
						status = true;
					}
				}
			}
		}
	}
}