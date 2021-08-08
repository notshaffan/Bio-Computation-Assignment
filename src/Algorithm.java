public class Algorithm {
    private int[][] testSet;
    private Chromosome chromosome;

    private int compare(int operator, int a, int b){
        switch (operator){
            case 0: // Logical AND
                if((a==1)&&(b==1)){
                    return 1;
                } else { return 0; }
            case 1: // Logical OR
                if((a==1)||(b==1)){
                    return 1;
                } else { return 0; }
            case 2: // Logical NAND
                if((a==0)&&(b==0)){
                    return 1;
                } else { return 0; }
            default: return 0;
        }
    }

    public Algorithm(Chromosome chromosome, int dataSet){
        //chromosome.initialize();
        this.chromosome = chromosome;
        FileHandler fh = new FileHandler(Integer.toString(dataSet));
        testSet = fh.getTestSet();

    }

    public Chromosome calculateFitness(){
        int fitness = 0;
        for (int i = 0; i<testSet.length; i++) {
            int[] currentIndividual = testSet[i];
            int a = currentIndividual[0];
            int b = currentIndividual[1];
            int operator = chromosome.getGene(0);
            int result = compare(operator, a, b);
            int x = 2;
            for (int j = 2; j < chromosome.getSize(); j++) {
                b = currentIndividual[x];
                operator = chromosome.getGene(j);
                result = compare(operator, b, result);
                x++;
                if(x>=currentIndividual.length){
                    x = 0;
                }
            }
            if(result == currentIndividual[currentIndividual.length-1]){
                fitness++;

            }
            chromosome.setFitness(fitness);
        }
        return chromosome;
    }


}
