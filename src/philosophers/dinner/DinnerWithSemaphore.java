package philosophers.dinner;

import java.util.concurrent.Semaphore;

public class DinnerWithSemaphore extends Dinner {
    
    private Semaphore mutex;
     
    public DinnerWithSemaphore (int totalPhilosophers) {
        super();
        this.mutex = new Semaphore(1);
        this.totalPhilosophers = totalPhilosophers;
        this.states = new PhilosopherStates[this.totalPhilosophers];
        this.philosophers = new Semaphore[this.totalPhilosophers];

        for(int i = 0; i < this.totalPhilosophers; i++) {
			this.states[i] = PhilosopherStates.THINKING;
			this.philosophers[i] = new Semaphore(0);
		}
    }

    @Override
    public void take_cutlery (int philosopherId) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        states[philosopherId] = PhilosopherStates.HUNGRY;
        if (canEat(philosopherId)) {
            ((Semaphore) philosophers[philosopherId]).release();
            states[philosopherId] = PhilosopherStates.EATING;
        }
        mutex.release();
        try {
            ((Semaphore) philosophers[philosopherId]).acquire();
            System.out.println("Philosopher " + philosopherId + " got the cutlery");
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
    }

    @Override
    public void return_cutlery (int philosopherId) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        System.err.println("Philosopher " + philosopherId + " returned the cutlery");
        states[philosopherId] = PhilosopherStates.THINKING;
        if (getRightState(philosopherId) == PhilosopherStates.HUNGRY &&
            getRightState(getRight(philosopherId)) != PhilosopherStates.EATING) {
                states[getRight(philosopherId)] = PhilosopherStates.EATING;
                ((Semaphore) philosophers[getRight(philosopherId)]).release();
        }
        if (getLeftState(philosopherId) == PhilosopherStates.HUNGRY &&
            getLeftState(getLeft(philosopherId)) != PhilosopherStates.EATING) {
                states[getLeft(philosopherId)] = PhilosopherStates.EATING;
                ((Semaphore) philosophers[getLeft(philosopherId)]).release();
        }
        mutex.release();
    }
}
