package printer;

import java.io.FileNotFoundException;

public class FiFo extends Simulator{
	private int totalTime = 0;
	//waitTimeΪÿһҳ�ĵȴ�ʱ�䣬���õ�һ���µ�ҳʱ��������waitTime��Ϊ0
	private int waitTime = -1;
	private Event loadEvent = workload.top();
	private Event waitEvent = null;
	//status��ǵ�ǰ��һ����ҵ�Ƿ����ڱ���ӡ
	private boolean status = true;
	private int page = 0;
	private int totalJob = 0;
	private float totalLatency = 0;
	
	
	public FiFo() throws FileNotFoundException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public void simulate() throws FileNotFoundException {	
		//���������ж���Ϊ��ʱ����������ִ��
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
	 * �÷������ϴ�������Ĺ����ϴ����ȴ���ӡ�Ķ���
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
			//����һ�δ�ӡʱ��waitEventΪ��
			if(waitEvent == null){
				waitEvent = object.top();
				waitTime = 0;
			}

			//��ǰ��ҵʱ��һ��ִ�е�����£���ҳ�����г�ʼ��
			if(status == true) {
				page = waitEvent.getJob().getNumber_of_pages();
				totalLatency = totalLatency + (totalTime - waitEvent.getArrive_time());
				totalJob++;
			}
			
			if(waitTime == 0 && status == true) {    //status�����һ�����waitTime����Թ������ÿһҳ
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