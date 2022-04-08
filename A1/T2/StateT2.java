package T2;

import java.util.*;
import java.io.*;

class StateT2 extends GlobalSimulationT2{
  private static enum Job {
    A,
    B
  }
  private Map<Job, Double> servingTime = Map.of(
    Job.A, 0.002,
    Job.B, 0.004
  );
  private ArrayDeque<Job> q = new ArrayDeque<Job>();
  
  public Job current;
	public int lambda = 150, d = 1, accumulated = 0, noMeasurements = 0,
    totalCustomers = 0, rejectedCustomers = 0;

	Random slump = new Random(); // This is just a random number generator

	
	// The following method is called by the main program each time a new event has been fetched
	// from the event list in the main loop. 
	public void treatEvent(Event x){
		switch (x.eventType){
			case ARRIVAL:
				arrival();
				break;
      case SLEEP:
        sleep();
        break;
			case READY:
				ready();
				break;
			case MEASURE:
				measure();
				break;
		}
	}
	
	// The following methods defines what should be done when an event takes place. This could
	// have been placed in the case in treatEvent, but often it is simpler to write a method if 
	// things are getting more complicated than this.
	
	private void arrival(){
    startServingIfEmpty(Job.A);
    q.add(Job.A);
		insertEvent(ARRIVAL, time + getExponential());
	}

  private void sleep() {
    startServingIfEmpty(Job.B);
    q.add(Job.B);
  }
	
	private void ready(){
    if(current == Job.A) {
      insertEvent(SLEEP, time + d);
    }
    q.remove(current);
    if(!q.isEmpty()) {
      current = getNextJob();
      insertEvent(READY, time + servingTime.get(current));
    }
	}
	
	private void measure(){
		accumulated = accumulated + q.size();
		noMeasurements++;
		insertEvent(MEASURE, time + slump.nextDouble()*10);
	}

  /***
   * Kickstarts the server if the queue is empty.
   * @param job is the job which the server is going to start serving.
   */
  private void startServingIfEmpty(Job job) {
    if(q.isEmpty()) {
      current = job;
			insertEvent(READY, time + servingTime.get(current));
    }
  }

  private Job getNextJob() {
    Job preferredJob = Job.B;

    if(q.remove(preferredJob)) {
      return preferredJob;
    }
    
    /* Otherwise return whatever job is available */
    return q.pop();
  }

  private double getExponential() {
    return Math.log(1 - slump.nextDouble())/(-lambda);
  }
}