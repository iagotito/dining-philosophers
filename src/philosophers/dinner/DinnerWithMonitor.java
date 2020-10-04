package philosophers.dinner;

import java.util.ArrayList;

public class DinnerWithMonitor extends Dinner{
	
	public DinnerWithMonitor(int totalPhilosophers) {
		super();
		this.totalPhilosophers = totalPhilosophers;
		this.states = new ArrayList<>(this.totalPhilosophers);
		this.philosophers = new Object[this.totalPhilosophers];
		
		for(int i = 0; i < this.totalPhilosophers; i++) {
			this.states.add(States.THINKING);
			this.philosophers[i] = new Object();
		}

		System.out.println(states);
	}
	
	@Override
	public void takeCutlery(int philosopherId) {
		states.set(philosopherId, States.HUNGRY);
		synchronized (philosophers[philosopherId]) {
			if (canEat(philosopherId)) {
				states.set(philosopherId, States.EATING);
			} else {
				try {
                    this.philosophers[philosopherId].wait();
                } catch (InterruptedException e) {
					System.out.println("Interrupted Exception");
                }
			}
			//System.out.println("Philosopher " + philosopherId + " take the cutlery and started do eat.");
			System.out.println(states);
		}
	}

	@Override
	public void returnCutlery(int philosopherId) {
		states.set(philosopherId, States.THINKING);
		//System.out.println("Philosopher " + philosopherId + " returned the cutlery.");
		System.out.println(states);
		
		if(getRightState(philosopherId) == States.HUNGRY &&
			getRightState(getRight(philosopherId)) != States.EATING) {
			states.set(getRight(philosopherId), States.EATING);
			synchronized (philosophers[getRight(philosopherId)]) {
				philosophers[getRight(philosopherId)].notify();
			}
		}
		if(getLeftState(philosopherId) == States.HUNGRY &&
			getLeftState(getLeft(philosopherId)) != States.EATING) {
			states.set(getLeft(philosopherId), States.EATING);
			synchronized (philosophers[getLeft(philosopherId)]) {
				philosophers[getLeft(philosopherId)].notify();
			}
		}
	}
}
