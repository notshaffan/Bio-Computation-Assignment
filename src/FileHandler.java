import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileHandler {
    private int[][] testSet;
    int[] gene;
    private int geneSize = 0;
    private int geneArraySize = 0;

    public FileHandler (String fileName){
        if (fileName.equals("1")){
            geneSize = 6;
            geneArraySize = 32;
        } else if (fileName.equals("2")){
            geneSize = 7;
            geneArraySize = 64;
        } else {
            System.out.println("File not Found");
        }

        gene = new int[geneSize];
        testSet = new int[geneArraySize][];

        try{
            Scanner sc = new Scanner(new File("src/Data/Data"+fileName+".txt"));
            sc.nextLine(); // skips first line of dataset.txt
            int gi = 0; // index of gene in gene array

            while(sc.hasNextLine()){
                int [] gene = new int[geneSize];
                String line = sc.nextLine();

                int i = 0; // index of value in gene

                for (int j = 0; j<line.length(); j++){
                    if(!Character.isWhitespace(line.charAt(j))){
                        gene[i] = Character.getNumericValue(line.charAt(j));
                        i++;
                    }
                }
                testSet[gi] = gene;
                gi++;

                //System.out.println(Arrays.toString(gene)); // print gene --

            }
        } catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    public int[][] getTestSet(){
        return testSet;
    }


}
