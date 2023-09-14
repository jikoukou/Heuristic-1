/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristic1;

import java.io.IOException;


/**
 *
 * @author dimitris
 */
public class Heuristic1 {

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        Create_AP accessPattern;
        // I will try to compute worst access time for the values of the below sets
        int [] sets = {100, 1000, 10000};
        int [] elements = {100, 1000, 10000};
        int[] items = {10, 100, 1000, 10000};
        StringBuilder itemList = new StringBuilder("{");
        
        for(int k = 0; k < 3; k++) {
            itemList.append(items[k]);
            itemList.append(", ");
        }
        itemList.append(items[3]);
        itemList.append("}");
        
//        int sets = 30;
//        int elements = 30;
//        int numItems = 100; // (max)
        int worstAccessTime[] = new int[4];

        for(int i = 0; i < 4; i++) {
            int curr_set = sets[i];
            
            for(int j = 0; j < 3; j++) {
                int curr_element = elements[j];
                
                for(int k = 0; k < 4; k++) {
                    int curr_item = items[k];
                    
                    try {
                        // If the file exists create it
                        if(new java.io.File("queries"+curr_set+curr_element+curr_item+".txt").createNewFile()) {
                            accessPattern = new Create_AP(curr_item, curr_set, curr_element);
                            accessPattern.toFile(curr_set, curr_element, curr_item);
                        }
                        // else read it and recreate the access pattern
                        else {
                            accessPattern = new Create_AP();
                            accessPattern.read(curr_set, curr_element, curr_item);
                        }

                        // Create super sets from the created access pattern
                        NewPattern newPattern = new NewPattern(accessPattern.getQueries(), curr_set, curr_element);
                        worstAccessTime[k] = newPattern.measureWorstAccess(curr_element, curr_item);
                        
                    } catch(IOException  ex) {
                        System.out.println("Exception with the files");
                        System.exit(1);
                    }
                    
                    
                    
                }
                StringBuilder accessList = new StringBuilder("{");
                for(int k = 0; k < 3; k++) {
                    accessList.append(worstAccessTime[k]);
                    accessList.append(", ");
                }
                accessList.append(worstAccessTime[3]);
                accessList.append("}");
                
                System.out.println("plt.figure()");
                System.out.println("plt.plot("+itemList+","+accessList+",'r-')");
                System.out.println("plt.xlabel('items')");
                System.out.println("plt.ylabel('Worst access time')");
                System.out.println("plt.title('Elements= "+String.valueOf(curr_element)+", Sets = "+String.valueOf(curr_set)+"')\n");
            }
        }
            
        
        
        
    }
    
}
