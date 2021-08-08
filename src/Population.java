import java.util.ArrayList;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Population {
    private Chromosome[] chromosomes;
    private int bestFitness;
    private int avgFitness;
    private int worstFitness;
    private int activeDataset;
    private int gen = 0;
    private int mutationRate;
    private ArrayList<Results> results = new ArrayList<>();

    public Population(int size){
        chromosomes = new Chromosome[size];
    }

    static void shuffleArray(int[] ar)
    {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }

    public Population initialize(int dataSet){
        this.activeDataset = dataSet;
        for(int i = 0; i<chromosomes.length; i++){
            chromosomes[i] = new Chromosome(5).initialize(); // - Use this to change rule size
        }
        return this;
    }

    public void calculateFitness(){
        bestFitness = 0;
        worstFitness = 0;
        avgFitness = 0;
        for(int i = 0; i<chromosomes.length; i++){
            Chromosome currentChromosome = chromosomes[i];
            currentChromosome = new Algorithm(currentChromosome,1).calculateFitness();
            chromosomes[i] = currentChromosome;
            avgFitness+= currentChromosome.getFitness();
            if(currentChromosome.getFitness()>bestFitness){
                bestFitness = currentChromosome.getFitness();
            }
            if(worstFitness==0||currentChromosome.getFitness()<worstFitness){
                worstFitness = currentChromosome.getFitness();
            }
        }
        avgFitness = avgFitness/chromosomes.length;
        if(gen%5 == 0) {
            results.add(new Results(gen, bestFitness, worstFitness, avgFitness, mutationRate, chromosomes.length));
        }
    }

    public Chromosome getChromosome(int i){
        return chromosomes[i];
    }

    public Chromosome[] getChromosomes(){
        return chromosomes;
    }
    public void print(){
        System.out.printf("%3s %47s %7s","id","chromosome","fitness");
        System.out.println();
        for(int i = 0; i< chromosomes.length; i++){
            Chromosome currentChromosome = chromosomes[i];
            System.out.format("%3s %47s %7s",i,currentChromosome.getGenes(),currentChromosome.getFitness());
            System.out.println();
        }
        System.out.println("== Population Stats ==");
        System.out.println("Best Fitness: "+bestFitness+"\tWorse Fitness: "+worstFitness+"\tAverage Fitness: "+avgFitness);
        int accuracy = 0;
        if (activeDataset == 1){
            accuracy = 32;
        } else {
            accuracy = 64;
        }
        accuracy = ((avgFitness*100)/accuracy);
        System.out.println("Average Accuracy = "+accuracy+"%");
        System.out.println("=======================");
    }

    public void printResults(){
        System.out.println("\n");
        for(int i=0; i<results.size(); i++){
            results.get(i).print();
        }
    }

    /**
     * @param mutationRate chance of individual gene mutation in %
     */
    public void mutate(int mutationRate){
        this.mutationRate = mutationRate;
        Random rand = new Random();
        for(int i = 0; i<chromosomes.length; i++){
            Chromosome currentChromosome = chromosomes[i];
            for(int j = 0; j<currentChromosome.getSize(); j++){
                int mutation = rand.nextInt(100);
                if(mutation<mutationRate){
                    currentChromosome.flipGene(j);
                }
            }
            chromosomes[i] = currentChromosome;


        }
        calculateFitness();
        System.out.println("== Genes have been mutated ==");
        gen++;
    }

    public void roulletteSelection(){
        Chromosome[] poolNew = new Chromosome[chromosomes.length];
        int totalFitness = 0;

        // Calculate size of roullette
        for (Chromosome chromosome : chromosomes) {
            totalFitness += (chromosome.getFitness());
        }
        Chromosome[] roullette = new Chromosome[totalFitness];
        int j = 0;
        // Load numbers into roullette
        for(int i = 0; i< chromosomes.length; i++){
            int x = chromosomes[i].getFitness();
            while(x!=0){
                roullette[j] = chromosomes[i];
                x--;
                j++;
            }
        }


        // Array to randomly pick chromosomes
        int[] randChoice = new int[roullette.length];
        for (int i = 0; i< roullette.length; i++){
            randChoice[i] = i;
        }
        shuffleArray(randChoice);

        int addedToNew = 0;
        for (int i = 0; i < poolNew.length; i++){
            poolNew[i]= roullette[randChoice[i]];
        }
        chromosomes = poolNew;
        calculateFitness();

        gen++;
    }

}
