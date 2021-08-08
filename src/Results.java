public class Results {
    private int gen;
    private int bestFitness;
    private int worstFitness;
    private int avgFitness;
    private int mutationRate;
    private int population;
    private int numberOfRules;

    public Results (int gen, int bestFitness, int worstFitness, int avgFitness, int mutationRate, int population){
        this.gen = gen;
        this.bestFitness = bestFitness;
        this.worstFitness=worstFitness;
        this.avgFitness=avgFitness;
        this.mutationRate=mutationRate;
        this.population=population;
        this.numberOfRules=3;
    }

    public void print(){
        System.out.println("\""+gen+"\",\""+bestFitness+"\",\""+worstFitness+"\",\""+avgFitness+"\",\""+mutationRate+"\",\""+population+"\",\""+numberOfRules+"\"");
    }
}
