import java.util.Scanner;

/**
 * @Author Mohamed Shaffan Shahid (s1900096)
 * BSc (Hons) Computer Science UFCFY3-15-3 Bio Computation Jan 2021
 *
 * notes:
 * Dataset can be selected from the command line
 * Population size, mutation rate, and number of generations can be adjusted from the code below. Specific lines have been commented.
 * Rule size can be adjusted from Population.java, line 37
 *
 * To use:
 * First select a data set using 1 or 2 and press enter
 * This will generate and print out the initial population
 * press 1 to run mutation and roulette selection for the number of generations specified in the code.
 * 2 can be pressed at the end to generate the results in a csv-friendly format that
 * can be exported to excel to create graphs.
 **/

public class Main {
    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);
        int choice = 0;
        while(true) {
            System.out.println("Select Dataset");
            System.out.println("[1] - Data1.txt   [2] - Data2.txt");
            choice = scan.nextInt();
            if(choice==0||choice>2){
                System.out.println("[!} - Invalid choice");
            } else { break; }
        }
        System.out.println("Loaded dataset "+choice);
        Population population = new Population(1000).initialize(choice); // - Change population size from here
        population.calculateFitness();
        population.print();

        while(true) {
            choice = 0;
            while (true) {
                System.out.println("Select Action");
                System.out.println("[1] - Mutate & Roulette    [2] - Results");
                choice = scan.nextInt();
                if (choice == 0 || choice > 2) {
                    System.out.println("[!} - Invalid choice");
                } else {
                    break;
                }
            }
            if (choice == 1) {
                for (int i = 0; i<1000; i++) { // change the second number to change number of generations mutated for
                    population.mutate(1); // change number in the brackets to change mutation rate (value here is in %)
                    population.roulletteSelection();
                }
                population.print();
            } else {
                System.out.println("gen,bestCandidate,worstCandidate,average,mutationRate,population,numOfRules");
                population.printResults();
            }
        }
    }
}
