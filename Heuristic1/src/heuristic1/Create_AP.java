/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristic1;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author dimitris
 */

// Creates an access pattern to test the heuristic


public class Create_AP {
    
    // The queries
    private ArrayList<QuerySet> queries;
    
    
    // m: Total elements
    // sets: Number of sets
    // maxEls: max elements in a set
    
    public Create_AP() {
        queries = new ArrayList<>();
    }
    
    public Create_AP(int m, int sets, int maxELs) {
        queries = new ArrayList<>();
        for(int i = 0; i < sets; i++) {
            // Add the whole query
            queries.add(new QuerySet(maxELs, m));
            
        }
    }
    
    ArrayList<QuerySet> getQueries() {
        return queries;
    }
    
    // This method will store the access pattern on a file
    void toFile(int sets, int elements, int numItems) throws IOException {
        StringBuilder forWrite = new StringBuilder();
        try (//        FileWriter writer = new FileWriter(file.getAbsolutePath());
                BufferedWriter writer = new BufferedWriter(new FileWriter("queries"+sets+elements+numItems+".txt"))) {
            // First row: How many elements there are
            forWrite.append(elements);
            forWrite.append("\n");
            forWrite.append(sets);
            forWrite.append("\n");
            for(int i = 0; i < queries.size(); i++) {
                for(int k = 0; k < elements; k++) {
                    forWrite.append(queries.get(i).getElements()[k]);
                    forWrite.append("\n");
                }
                
                writer.write(forWrite.toString());
                forWrite.delete(0, forWrite.length());
            }
            
        }
    }
    
    void read(int sets, int elements, int numItems) throws IOException {
        Scanner reader = new Scanner(new java.io.File("queries"+sets+elements+numItems+".txt"));
        
        // The firsy 2 lines are the basic
        reader.nextLine();
        reader.nextLine();
        
        queries = new ArrayList<>();
        
        
        for(int i = 0; i < sets; i++) {
            queries.add(new QuerySet(elements));
            
            for(int j = 0; j < elements; j++) {
                queries.get(i).setElement(j, Integer.valueOf(reader.nextLine()));
            }
        }
        
        
    }
    
    // One query set to add some elements
    
}

class Popularities {
    private final double [] probabilities;


    public Popularities(int maxElements) {
        // How popular each data item is

        probabilities = new double[maxElements + 1];
        probabilities[0] = 0.0;

        for(int i = 1; i < maxElements; i++) {
            // Get next popularity
            // There is a universal maxPopularity
            probabilities[i] = (Math.sqrt(1.0/((double) i)))/maxElements;

            // In this part of the code we have some popularities



        }

        for(int i = 1; i < maxElements; i++) {
            probabilities[i] += probabilities[i - 1];

        }

    }

    double[] getProbabilities() {
        return probabilities;
    }
}
    
class QuerySet {
//    int numElements;
    private int[] elements;
    // Max elements in the query
    private int els;
    
    public QuerySet(int num) {
        els = num;
        elements = new int[num];
    }
    
    public QuerySet(int num, int maxElements) {
//        numElements = num;
        els = num;
        elements = new int[num];

        // Initialize the set
        double[] probabilities = (new Popularities(maxElements)).getProbabilities();

        for(int i = 0; i < num; i++) {
            double guessed = ThreadLocalRandom.current().nextDouble(0, probabilities[maxElements - 1]);

            for(int j = 0; j < maxElements - 2; j++) {
                if(probabilities[j] <= guessed && probabilities[j + 1] >= guessed) {
                    // Element = 1, 2, ..., maxElements
                    elements[i] = j + 1;
                    break;

                }
            }


        }
        // Sort array
        for (int i = 0; i < num; i++) {
            for (int j = i + 1; j < num; j++) {

            //7
            if (elements[i] > elements[j]) {
                    int tempValue = elements[i];
                    elements[i] = elements[j];
                    elements[j] = tempValue;
                }
            }
        }
        
    }
    
    void setElement(int k, int newData) {
        elements[k] = newData;
    }
    int[] getElements() {
        return elements;
    }
    
    
}
