import java.io.Serializable;

public class Stage implements Serializable{
	
	String stageName;
	int maxViewers;
	
	public Stage(String stageName, int maximumViewers){
		this.stageName = stageName;
		this.maxViewers = maximumViewers;
	}
	// get/set voor naam van stage
	public String getName(){
		return stageName;
	}
	public void setName(String name){
		stageName = name;
	}
	
	// get/set voor maximum aantal viewers van stage
	public int getMaxViewers(){
		return maxViewers;
	}
	public void setMaxViewers(int max){
		maxViewers = max;
	}
}