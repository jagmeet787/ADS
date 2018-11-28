package ADS.Greedy;

public class Job implements Comparable<Job>{
	public int startTime;
	public int endTime;
	
	public Job(int startTime, int endTime) {
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	@Override
	public int compareTo(Job other) {
		return this.endTime - other.endTime;
	}
	
	@Override 
	public String toString() {
		return "[" + this.startTime + " " + this.endTime + "]";
	}
}