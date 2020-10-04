package philosophers.dinner;

public class Philosopher implements Runnable {
    
    private int id;
    private int thinkingDuration;
	private int eatingDuration;
	private DinnerWithSemaphore dinner;

    public Philosopher(int id, int thinkingDuration, int eatingDuration, DinnerWithSemaphore dinner) {
		this.id = id;
		this.thinkingDuration = thinkingDuration;
		this.eatingDuration = eatingDuration;
		this.dinner = dinner;
        new Thread((Runnable)this, "Philosopher" + id).start();
    }

    @Override
	public void run() {
		while(true) {
			think();
			take_cutlery();
			eat();
			return_cutlery();
		}
    }
    
    private void think () {
		try {
			System.out.println("Philosopher " + this.id + " is thinking");
			Thread.sleep(this.thinkingDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	private void take_cutlery () {
		System.out.println("Philosopher " + this.id + " is trying get cutlery");
		dinner.take_cutlery(this.id);
	}

	private void eat () {
		try {
			System.out.println("Philosopher " + this.id + " is eating");
			Thread.sleep(this.eatingDuration);
			System.out.println("Philosopher " + this.id + " end eating");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	private void return_cutlery () {
		dinner.return_cutlery(this.id);
	}
}
