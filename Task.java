
public class Task {
	private String creator;
	private String executor;
	private String task;
	private int state;
	private int num;
	private static int num_all_task = 0;
	
	public Task(String creator, String executor, String task) {
		this.creator = creator;
		this.executor = executor;
		this.task = task;
		this.state = 0;
		this.num = num_all_task;
		incrNumTask();
	}

	public String toString() {
		return "|Tache : " + task + " | Createur : " + creator  +" | Executeur : " + executor +" | Etat : "+getState()+"|";
	}

	public String getCreator() {
		return creator;
	}

	public String getExecutor() {
		return executor;
	}

	public String getTask() {
		return task;
	}
	
	public void incrNumTask(){
		num_all_task++;
	}
	
	public void changeState() {
		this.state = 1;
	}
	
	public int getNumTask(){
		return num;
	}
	
	public String getState() {
		if (state == 0) {
			return "TODO";
		}
		return "DONE";
	}

}










//STOP COPIER
