package ADS.Greedy;

public class Machine {
	
	public int machineId;
	public static int machineCount = 0;
	public int busyUntil;
	
	public Machine(int endTime) {
		this.machineId = ++machineCount;
		this.busyUntil = endTime;
	}
	
	@Override
	public String toString() {
		return "[id:" + this.machineId + "->" + this.busyUntil + "]";
	}
}