/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package heuristic1;

import java.util.ArrayList;

/**
 *
 * @author dimitris
 */

// Using this class i will create the new Pattern
public class NewPattern {
    // An array of lists which stores each "bucket"
    // These buckets form the new access pattern and they consist of several query sets
    ArrayList<QuerySet>[] finalSets;
    
    // How much queries are created
    int numBuckets;
    
    // First queries 
    public NewPattern(ArrayList<QuerySet> firstQueries, int numSets, int numElements) {
        // Worst case
        QuerySet[][] newqueries;
       ArrayList<Integer> QuerySetsNum;
        
        newqueries = new QuerySet[numSets][numSets];
        numBuckets = 0;
        QuerySetsNum = new ArrayList<>();
        int curr_bucket = 0;
        // Here we store the queries to be added
        // I will create a path for which neighboring elements will be added 
        
        
        
        // I will test the lastQuery found to find the path
        QuerySet lastQuery = firstQueries.get(0);
        // Remove first item. When a query is added on a path
        // I will remove this element.
        
        // The tested query (to be added) index
        int curr_index = 0;
        firstQueries.remove(0);
        ArrayList<QuerySet> pendingQueries = new ArrayList<>();
        pendingQueries.add(lastQuery);
        
        while(!firstQueries.isEmpty() || !pendingQueries.isEmpty()) {
            int i;
            
            if(!firstQueries.isEmpty()) {
                for(i = 0; i < numElements; i++) {
                    if(firstQueries.get(curr_index).getElements()[i] == lastQuery.getElements()[i]) {
                        lastQuery = firstQueries.get(curr_index);
                        pendingQueries.add(lastQuery);
                        break;

                    }
                }

                if(curr_index == firstQueries.size() - 1) {


                    for(int j =0; j < pendingQueries.size(); j++) {
                        newqueries[curr_bucket][j] = pendingQueries.get(j);
                    }
                    QuerySetsNum.add(pendingQueries.size());
                    firstQueries.removeAll(pendingQueries);

                    pendingQueries.clear();
                    if(!firstQueries.isEmpty()) {
                        lastQuery = firstQueries.get(0);
                        pendingQueries.add(lastQuery);
                        firstQueries.remove(0);
                    }
                    curr_bucket++;
                    curr_index = 0;
                    numBuckets++;
                }
                else
                    curr_index++;
            }
            
            else {
                for(int j =0; j < pendingQueries.size(); j++) {
                    newqueries[curr_bucket][j] = pendingQueries.get(j);
                }
                QuerySetsNum.add(pendingQueries.size());
                pendingQueries.clear();
                numBuckets++;
            }
            
        }
        
        
        if(!pendingQueries.isEmpty()) {
            System.exit(1);
        }
        
        finalSets = new ArrayList[numBuckets];
        // Reduce the size as much as possible
        for(int k = 0; k < numBuckets; k++) {
            finalSets[k] = new ArrayList<>();
            for(int j = 0; j < QuerySetsNum.get(k); j++) {
                
                finalSets[k].add(newqueries[k][j]);
                
            }
        }
        
        
    }
    // Measures worst access time from the set. To calculate this I 
    // assume that each element needs 1 bucket and that |P| = N, where N are 
    // The distinct items
    
    int measureWorstAccess(int elements, int numItems) {
        // A value for max buckets calculated
        int lastMax = 0;
        
        // An array of boolean values for each item.
        // It will store true if the item was found in a super set 
        // else it will be false
        boolean[] itemsFound = new boolean[numItems];
        
        
        for(int i = 0; i < numBuckets; i++) {
            // A value for the local max found
            int newMax = 0;
            
            // Initiate for each super-set
            for(int k = 0; k < numItems; k++)
                itemsFound[k] = false;
        
            for(int j = 0; j < finalSets[i].size(); j++) {
                for(int k = 0; k < elements; k++) {
                    // Check all the subsets and try to check common items
                    itemsFound[finalSets[i].get(j).getElements()[k]] = true;
                }
            }
            
            // When the loop has ended we will measure how many trues where found
            for(int k = 0; k < numItems; k++) {
                if(itemsFound[k])
                    newMax++;
            }
            
            if(newMax > lastMax)
                lastMax = newMax;
        }
        
        return lastMax;
    }
}
