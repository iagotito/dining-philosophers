package philosophers.dinner;

import java.util.ArrayList;

public abstract class Dinner {

	protected ArrayList<States> states;
	protected Object[] philosophers;
    protected int totalPhilosophers;

    public void takeCutlery(int philosopherId) {};

    public void returnCutlery(int philosopherId) {};

    protected boolean canEat (int philosopherId) {
		return (getRightState(philosopherId) != States.EATING &&
				getLeftState(philosopherId) != States.EATING);
	}

	protected States getRightState (int philosopherId) {
		return states.get(getRight(philosopherId));
	}

	protected int getRight (int position) {
		return (position + 1) % totalPhilosophers;
	}

	protected States getLeftState (int philosopherId) {
		return states.get(getLeft((philosopherId)));
	}

	protected int getLeft (int position) {
		return (position + totalPhilosophers - 1) % totalPhilosophers;
	}

}
