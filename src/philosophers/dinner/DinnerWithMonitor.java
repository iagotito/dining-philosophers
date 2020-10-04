package philosophers.dinner;

public class DinnerWithMonitor extends Dinner{
	
	public DinnerWithMonitor(int totalPhilosophers) {
		super();
		this.totalPhilosophers = totalPhilosophers;
		this.states = new PhilosopherStates[this.totalPhilosophers];
		this.philosophers = new Object[this.totalPhilosophers];
		
		for(int i = 0; i < this.totalPhilosophers; i++) {
			this.states[i] = PhilosopherStates.THINKING;
			this.philosophers[i] = new Object();
		}
	}
	
	@Override
	public void take_cutlery(int philosopherId) {
		states[philosopherId] = PhilosopherStates.HUNGRY;
		synchronized (philosophers[philosopherId]) {

			if (canEat(philosopherId)) {
				states[philosopherId] = PhilosopherStates.EATING;
			} else {
				try {
                    this.philosophers[philosopherId].wait();
                    System.out.println("Philosopher " + philosopherId + " is get cutlery with success");
                } catch (InterruptedException e) {
					System.out.println("Interrupted Exception");
                }
			}
		}
	}

	@Override
	public void return_cutlery(int philosopherId) {
		states[philosopherId] = PhilosopherStates.THINKING;
		
		if(getRightState(philosopherId) == PhilosopherStates.HUNGRY &&
			getRightState(getRight(philosopherId)) != PhilosopherStates.EATING) {
			states[getRight(philosopherId)] = PhilosopherStates.EATING;
			synchronized (philosophers[getRight(philosopherId)]) {
				philosophers[getRight(philosopherId)].notify();
			}
		}
		if(getLeftState(philosopherId) == PhilosopherStates.HUNGRY &&
			getLeftState(getLeft(philosopherId)) != PhilosopherStates.EATING) {
			states[getLeft(philosopherId)] = PhilosopherStates.EATING;
			synchronized (philosophers[getLeft(philosopherId)]) {
				philosophers[getLeft(philosopherId)].notify();
			}
		}
	}
}
