package philosophers.dinner;

public abstract class Dinner {
    
    protected PhilosopherStates[] states;
	protected Object[] philosophers;
    protected int totalPhilosophers;

    public void take_cutlery(int philosopherId) {};

    public void return_cutlery(int philosopherId) {};

    protected boolean canEat (int philosopherId) {
		return (getRightState(philosopherId) != PhilosopherStates.EATING &&
				getLeftState(philosopherId) != PhilosopherStates.EATING);
	}

	protected PhilosopherStates getRightState (int philosopherId) {
		return states[getRight(philosopherId)];
	}

	protected int getRight (int position) {
		return (position + 1) % totalPhilosophers;
	}

	protected PhilosopherStates getLeftState (int philosopherId) {
		return states[getLeft((philosopherId))];
	}

	protected int getLeft (int position) {
		return (position + totalPhilosophers - 1) % totalPhilosophers;
	}

}
