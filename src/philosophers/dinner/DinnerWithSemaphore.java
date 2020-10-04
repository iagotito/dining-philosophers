package philosophers.dinner;

import java.util.concurrent.Semaphore;

import java.util.ArrayList;

public class DinnerWithSemaphore extends Dinner {
    
    private Semaphore mutex;
     
    public DinnerWithSemaphore (int totalPhilosophers) {
        super();
        this.mutex = new Semaphore(1);
        this.totalPhilosophers = totalPhilosophers;
        this.states = new ArrayList<>(totalPhilosophers);
        this.philosophers = new Semaphore[this.totalPhilosophers];

        for(int i = 0; i < this.totalPhilosophers; i++) {
			this.states.add(States.THINKING);
			this.philosophers[i] = new Semaphore(0);
        }
        
        System.out.println(states);
    }

    @Override
    public void takeCutlery (int philosopherId) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        states.set(philosopherId, States.HUNGRY);
        if (canEat(philosopherId)) {
            ((Semaphore) philosophers[philosopherId]).release();
            states.set(philosopherId, States.EATING);
        }
        mutex.release();
        try {
            ((Semaphore) philosophers[philosopherId]).acquire();
            //System.out.println("Philosopher " + philosopherId + " taked the cutlery and started to eat.");
            System.out.println(states);
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
    }

    @Override
    public void returnCutlery (int philosopherId) {
        try {
            mutex.acquire();
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception");
        }
        //System.out.println("Philosopher " + philosopherId + " returned the cutlery.");
        System.out.println(states);
        states.set(philosopherId, States.THINKING);
        if (getRightState(philosopherId) == States.HUNGRY &&
            getRightState(getRight(philosopherId)) != States.EATING) {
                states.set(getRight(philosopherId),  States.EATING);
                ((Semaphore) philosophers[getRight(philosopherId)]).release();
        }
        if (getLeftState(philosopherId) == States.HUNGRY &&
            getLeftState(getLeft(philosopherId)) != States.EATING) {
                states.set(getLeft(philosopherId), States.EATING);
                ((Semaphore) philosophers[getLeft(philosopherId)]).release();
        }
        mutex.release();
    }
}
