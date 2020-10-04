package philosophers.dinner;

public class Philosopher implements Runnable {
    
    private int id;
    private int thinkingDuration;
	private int eatingDuration;
	private Dinner dinner;

    public Philosopher(int id, int thinkingDuration, int eatingDuration, Dinner dinner) {
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
			takeCutlery();
			eat();
			returnCutlery();
		}
    }
    
    private void think () {
		try {
			//System.out.println("Philosopher " + this.id + " is thinking.");
			Thread.sleep(this.thinkingDuration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	private void takeCutlery () {
		//System.out.println("Philosopher " + this.id + " is trying get cutlery.");
		dinner.takeCutlery(this.id);
	}

	private void eat () {
		try {
			Thread.sleep(this.eatingDuration);
			//System.out.println("Philosopher " + this.id + " end eating.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
	
	private void returnCutlery () {
		dinner.returnCutlery(this.id);
	}
}
