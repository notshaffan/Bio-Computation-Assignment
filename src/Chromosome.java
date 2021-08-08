import java.util.Arrays;
import java.util.Random;

public class Chromosome {
    private int[] genes;
    private int fitness = 0;
    public Chromosome(int size){
        genes = new int[size];
    }
    public Chromosome initialize(){
        Random rand = new Random();
        for (int i = 0; i < (genes.length); i++){
            genes[i] = rand.nextInt(3);
        }


        return this;
    }

    public int getGene(int i){
        return genes[i];
    }

    public String getGenes(){
        return Arrays.toString(genes);
    }

    public int getFitness(){
        return fitness;
    }

    public void setFitness(int newFitness){
        fitness=newFitness;
    }

    public int getSize(){
        return genes.length;
    }

    public void flipGene(int i){
        if(genes[i]==1){
            genes[i]=0;
        } else {
            genes[i]=1;
        }
    }
}
