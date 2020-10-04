package philosophers.dinner;

import java.util.Scanner;

public class MainPhilosophersDinner {
    private static final int PHILOSOPHERS_NUMBER = 7;
    private static final int PHILOSOPHERS_EATING_DURATION = 1000;
    private static final int PHILOSOPHERS_THINKING_DURATION = 1000;
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Dinner dinner;

        System.out.println("Which solution do you want to run? [sem/mon]");
        String option = sc.nextLine();

        if (!option.equals("sem") && !option.equals("mon")) {
            System.out.println("Invalid option. Try again.");
            System.exit(1);
        }

        if (option.equals("sem")) {
            dinner = new DinnerWithSemaphore(PHILOSOPHERS_NUMBER);
        } else {
            dinner = new DinnerWithMonitor(PHILOSOPHERS_NUMBER);
        }
		
		for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) {
			new Philosopher(i, PHILOSOPHERS_EATING_DURATION, PHILOSOPHERS_THINKING_DURATION, dinner);
        }

        sc.close();
    }
}
