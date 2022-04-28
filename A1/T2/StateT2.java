package T2;

import java.util.*;

class StateT2 extends GlobalSimulationT2{
  private static enum Job {
    A,
    B
  }
  private Map<Job, Double> servingTime = Map.of(
    Job.A, 0.002,
    Job.B, 0.004
  );
  private int queue[] = {0, 0};
  public Job current;
	public int d = 1, lambda = 150, accumulated = 0, noMeasurements = 0;
	Random slump = new Random();
	
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
	
	private void arrival(){
    startServingIfEmpty(Job.A);
    addJobQueueSize(Job.A, 1);
		insertEvent(ARRIVAL, time + getExponential(lambda));
	}

  private void sleep() {
    startServingIfEmpty(Job.B);
    addJobQueueSize(Job.B, 1);
  }
	
	private void ready(){
    if(current == Job.A) {
      insertEvent(SLEEP, time + d);
      // Comment above and uncomment below for task 2
      // insertEvent(SLEEP, time + getExponential(1.0/d));
    }
    addJobQueueSize(current, -1);
    if(queueSize() != 0) {
      current = getNextJob();
      insertEvent(READY, time + servingTime.get(current));
    }
	}
	
	private void measure(){
		accumulated = accumulated + queueSize();
		noMeasurements++;
		insertEvent(MEASURE, time + 0.1);
	}

  /**
   * Adds the amount of a certain job in the queue. Negative numbers
   * are also supported in case of removing certain jobs from the queue.
   * @param job The job to add or subtract from.
   * @param nbr The number which should be added or removed of the given job.
   */
  private void addJobQueueSize(Job job, int nbr) {
    if(job == Job.A) {
      queue[0] += nbr;
    } else if(job == Job.B) {
      queue[1] += nbr;
    }
  }

  private int queueSize() {
    return queue[0] + queue[1];
  }

  /***
   * Kickstarts the server if the queue is empty (but a new job has arrived).
   * @param arrivedJob is the job which the server is going to start serving.
   */
  private void startServingIfEmpty(Job arrivedJob) {
    if(queueSize() == 0) {
      current = arrivedJob;
			insertEvent(READY, time + servingTime.get(current));
    }
  }

  /**
   * Get and remove the next job to serve in the queue.
   * If there is a job of the preferred type we retrieve
   * that job, otherwise any job suffices.
   * @return The job which was chosen and removed from the queue.
   */
  private Job getNextJob() {
    Job preferredJob = Job.B;
    // Comment above and uncomment below for task 3
    // Job preferredJob = Job.A;

    int pref = 1;
    Job unpreferred = Job.A;

    /* For task 3 we change the unpreffered job to B */
    if(preferredJob == Job.A) {
      pref = 0;
      unpreferred = Job.B;
    }

    if(queue[pref] != 0) {
      return preferredJob;
    } else {
      return unpreferred;
    }
  }

  /**
   * Used to generate an exponentially distributed variable.
   * @param lambda is also defined as 1/mean.
   * @return the generated value.
   */
  private double getExponential(double lambda) {
    return Math.log(1 - slump.nextDouble())/(-lambda);
  }
}