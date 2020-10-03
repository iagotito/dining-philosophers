package philosophers.dinner;

import java.util.concurrent.Semaphore;

public class DinnerWithSemaphore {
    
    private Semaphore mutex;
	private PhilosopherStates[] states;
	private Semaphore[] philosophers;
    private int totalPhilosophers;
    
    public DinnerWithSemaphore (int totalPhilosophers) {
        this.mutex = new Semaphore(1);
        this.totalPhilosophers = totalPhilosophers;
        this.states = new PhilosopherStates[this.totalPhilosophers];
        this.philosophers = new Semaphore[this.totalPhilosophers];

        for(int i = 0; i < this.totalPhilosophers; i++) {
			this.states[i] = PhilosopherStates.THINKING;
			this.philosophers[i] = new Semaphore(0);
		}
    }

    public void take_cutlery (int philosopherId) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        states[philosopherId] = PhilosopherStates.HUNGRY;
        if (canEat(philosopherId)) {
            philosophers[philosopherId].release();
            states[philosopherId] = PhilosopherStates.EATING;
        }
        mutex.release();
        try {
        philosophers[philosopherId].acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
    }

    public void return_cutlery (int philosopherId) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        states[philosopherId] = PhilosopherStates.THINKING;
        if (getRightState(philosopherId) == PhilosopherStates.HUNGRY &&
            getRightState(getRight(philosopherId)) != PhilosopherStates.EATING) {
                states[getRight(philosopherId)] = PhilosopherStates.EATING;
                philosophers[getRight(philosopherId)].release();
        }
        if (getLeftState(philosopherId) == PhilosopherStates.HUNGRY &&
            getLeftState(getLeft(philosopherId)) != PhilosopherStates.EATING) {
                states[getLeft(philosopherId)] = PhilosopherStates.EATING;
                philosophers[getLeft(philosopherId)].release();
        }
        mutex.release();
    }

    private boolean canEat (int philosopherId) {
        return (getRightState(philosopherId) != PhilosopherStates.EATING &&
                getLeftState(philosopherId) != PhilosopherStates.EATING);
    }

    private PhilosopherStates getRightState (int philosopherId) {
        return states[getRight(philosopherId)];
    }

    private int getRight (int position) {
        return (position + 1) % totalPhilosophers;
    }

    private PhilosopherStates getLeftState (int philosopherId) {
        return states[getLeft((philosopherId))];
    }

    private int getLeft (int position) {
        return (position + totalPhilosophers - 1) % totalPhilosophers;
    }
}
