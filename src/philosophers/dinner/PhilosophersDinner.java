package philosophers.dinner;

public class PhilosophersDinner {
    private static final int PHILOSOPHERS_NUMBER = 6;
    private static final int[] PHILOSOPHERS_EATING_DURATION = {1000,1000,1000,1000,1000,1000};
    private static final int[] PHILOSOPHERS_THINKING_DURATION = {1000,1000,1000,1000,1000,1000};
    
    public static void main(String[] args) {

		DinnerWithSemaphore dinner = new DinnerWithSemaphore(PHILOSOPHERS_NUMBER);
		
		for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) {
			new Philosopher(i, PHILOSOPHERS_EATING_DURATION[i], PHILOSOPHERS_THINKING_DURATION[i], dinner);
        }
        
    }
}
