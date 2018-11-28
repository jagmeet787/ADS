package ADS.Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class JobScheduling {
	
	// return the number of scheduled jobs with given set
	public static List<Job> scheduleJobs(Job[] jobs) {
		if ( jobs.length == 0 ) return null;
		// O(nlogn)
		Arrays.sort(jobs);
		// O(n)
		ArrayList<Job> scheduledJobs = new ArrayList<>();
		/*
		scheduledJobs.add(jobs[0]);
		int globalStart = jobs[0].startTime;
		int globalEnd = jobs[0].endTime;
		for ( int i = 1; i < jobs.length; i++ ) {
			if ( jobs[i].startTime < globalEnd && jobs[i].endTime > globalEnd )
				continue;
			if ( jobs[i].endTime > globalStart && jobs[i].startTime < globalStart )
				continue;
			globalEnd = jobs[i].endTime;
			scheduledJobs.add(jobs[i]);
		}
		*/
		int globalEnd = Integer.MIN_VALUE;
		for ( Job j : jobs ) {
			// if the start time of the job is less than the 
			// end time of the last scheduled job the schedule it
			if ( j.startTime >= globalEnd ) {
				globalEnd = j.endTime;
				scheduledJobs.add(j);
			}
		}
		return scheduledJobs;
	}
	
	public static int minMachinesRequired(Job[] jobs) {
		if ( jobs.length == 0 ) return 0;
		
		// Sort the array based on the starting times of the job
		Arrays.sort(jobs, new Comparator<Job>(){
				@Override
				public int compare(Job a, Job b) {
					return a.startTime - b.startTime;
				}
			});

		// Min heap to store machines in where busyUntil is the key
		PriorityQueue<Machine> machines = new PriorityQueue<>(
				new Comparator<Machine>(){
					@Override
					public int compare(Machine m1, Machine m2) {
						return m1.busyUntil - m2.busyUntil;
					}
				});
		
		// for each job sorted based on start time 
		// get the machine with minimum busyUntil time and check if it possible to 
		// schedule this job on that machine if yes then schedule else add a new machine
		// O(n*(lg(d)) where d is the max number of overlaps and n is number of jobs
		
		machines.add(new Machine(Integer.MIN_VALUE));
		
		for ( Job j : jobs ) {
			Machine m = machines.peek();
			// if the machien is free
			if ( m.busyUntil <= j.startTime ) {
				// get that machine from the min heap
				m = machines.poll();
				// update the busyUntil time
				m.busyUntil = j.endTime;
			} else {
				// else create a new machine
				m = new Machine(j.endTime);
			}
			// add the machine to the min heap
			machines.add(m);
		}
		// return the size of the machine
		return machines.size();
	}
	
	public static int minMachinesRequiredPriority() {
		// Using BBST
		return 0;
	}
}