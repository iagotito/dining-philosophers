package philosophers.dinner;

import java.util.Scanner;

public class PhilosophersDinner {
    private static final int PHILOSOPHERS_NUMBER = 5;
    private static final int[] PHILOSOPHERS_EATING_DURATION = {1000,1000,1000,1000,1000};
    private static final int[] PHILOSOPHERS_THINKING_DURATION = {1000,1000,1000,1000,1000};
    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Which solution do you want to run? [sem/mon]");
        String option = sc.nextLine();

        if (option.equals("sem")) {
            DinnerWithSemaphore dinner = new DinnerWithSemaphore(PHILOSOPHERS_NUMBER);
        } else if (option.equals("mon")) {
            DinnerWithSemaphore dinner = new DinnerWithSemaphore(PHILOSOPHERS_NUMBER);
        } else {
            System.out.println("Opção inválida. Tente novamente.");
            System.exit(1);
        }

		DinnerWithSemaphore dinner = new DinnerWithSemaphore(PHILOSOPHERS_NUMBER);
		
		for (int i = 0; i < PHILOSOPHERS_NUMBER; i++) {
			new Philosopher(i, PHILOSOPHERS_EATING_DURATION[i], PHILOSOPHERS_THINKING_DURATION[i], dinner);
        }
    }
}
