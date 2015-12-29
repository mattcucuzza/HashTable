/* Hashtable Program
 * Author: Matthew Cucuzza
 * 
 * Program reads a file, reads the number of unique words, and stores it into
 * a hashtable using 5 different hashes 
 */

import java.util.*;
import java.lang.*;
import java.io.*;

public class HashProgram {
    
	// Static Class to Run Hash 
    static class DataA {
        String str;
        // Constructor
        public DataA(String str) {
        	// Sets the inputted string equal to str 
            this.str = str;   
        }
        
        // Performs first hash test with addition 
        public int hashingA(int m) {
        	int h = 0;
        	// Updates value of h with addition
        	for(int i = 0; i < str.length(); i++) {
        		h += (int)str.charAt(i);
        	}
        	// If h is negative make it positive
        	if (h < 0) {
        		h = -h; 
        	}
        	// Mods h based on size of hashtable 
        	return h%m;
        }
        
        // Performs second hash with multiplication
        public int hashingB(int m) {
        	int h = 1;
        	// Updates the value of h with multiplication 
        	for(int i = 0; i < str.length(); i++) {
        		h *= (int)str.charAt(i);
        	}
        	// If h is negative make it positive
        	if (h < 0) {
        		h = -h; 
        	}
        	// Mods h based on size of hashtable
        	return h%m;
        }
        
        // Performs third hash from book (page 269; 7.3) 
        public int hashingC(int m) {
        	int h = 0; 
        	// C = a Constant to multiply by in loop
        	int c = 5;
        	for(int i = 0; i < str.length(); i++) {
        		h = h*c + (int)str.charAt(i);
        	}
        	// If h is negative make it positive
        	if (h < 0) {
        		h = -h; 
        	}
        	// Mods h based on size of hashtable 
        	return h%m;
        }
        
        // Performs fourth hash function shrinking each one by 1/2
        public int hashingD(int m) {
        	int h = 0;
        	// C = Constant 
        	int c = 1/2; 
        	for (int i = 0; i < str.length(); i++) {
        		h = h*c + (int)str.charAt(i);
        	}
        	// If h is negative make it positive
        	if (h < 0) {
        		h = -h; 
        	}
        	// Mods h based on size of hashtable 
        	return h%m;
        }
        
        // Performs fifth hash function 
        public int hashingE(int m) {
        	int h = 0; 
        	int c = 2; 
        	for (int i = 0; i < str.length(); i++) {
        		h = c + (h << 6) + (h << 16) - h;
        	}
        	// If h is negative make it positive
        	if (h < 0) {
        		h = -h; 
        	}
        	// Mods h based on size of hashtable 
        	return h%m;
        } 
        
        // Overrides toString method and returns String
        public String toString() {
        	return str; 
        }
    }

    // Variables
    private Vector<DataA> words;
    long startTime; 
    long endTime; 

    // Method to read in Vector of words from file using Scanner 
    public void readWords(String fileName) {
        // Creates a Vector (endless array) of words
        words = new Vector<DataA>(100);
        File inFile = new File(fileName);

        try {
            Scanner file = new Scanner (inFile);
            // Delimiter takes Strings in file and reads all containing alphabet 
            file.useDelimiter("[^\\p{Alpha}]+");
            // Loops the file until null 
             while (file.hasNext()) {
            	 // Accesses the type DataA, reads the next String, converting to UpperCase
                 words.addElement(new DataA(file.next().toUpperCase()));
             }
             file.close();
        }
        
        catch (FileNotFoundException e) {
            System.out.println(e);
            System.exit(1);
        }
    }
    
    // Prints Vector of words from file 
    public void printWords() {
    	System.out.print("Strings from file: "); 
    	// Loop to print out Strings from file 
        for (DataA w: words) {
            System.out.print(w + ",");
        }
        System.out.println();
    }
    
    // First Hash Test
   public void runHashA() {
	   // Variable to store start time of test  
       startTime = System.currentTimeMillis();
        // Initializes Hashtable with type String 
	   HashSet <String> hs = new HashSet<String>(100);
	   System.out.println("Hashtable A Data: ");

	   // Loop to add elements
        for (DataA w: words) {
        	// Makes sure no duplicate words are stored
        	if (!hs.contains(w.str)) {
        		hs.add(w.str);
        	}
        } 
        
        /* Prints out the Hashtable
    	** Commented out this to make faster & cleaner output */
        
        //System.out.println("String Hashtable A: " + hs);
        
        // Prints the number of unique words stored 
        System.out.println("Size of Hashtable A: " + hs.size() + " unique words");
        
        System.out.print("Hashtable A Values: ");
        // Loop to store into hash, taking mod as parameter 
        // To print out Data Values add System.out.println in front of str.hashing
        for (DataA str: words) {
        	str.hashingA(hs.size());
        }
        System.out.println();
        
        // Variable to store end time of test 
        endTime = System.currentTimeMillis();
        // Prints out the total time taken to run loop
        System.out.println("Time to sort: " + (endTime - startTime) + " milliseconds");
    } 
    
   // Second Hash Test
    public void runHashB() { 
    	// Variable to store the start time of test
   	 	startTime = System.currentTimeMillis();
    	// Initializes Hashtable with type String 
    	HashSet<String> hs = new HashSet<String>(100);
    	System.out.println("Hashtable B Data:");
    	
    	// Loop to add elements
    	 for (DataA w: words) {
    		 // Makes sure no duplicate words are stored
    		 if (!hs.contains(w.str)) {
    			 hs.add(w.str);
    		 }
         } 
    	
    	 /* Prints out the Hashtable
     	 **	Commented out this to make faster & cleaner output */
    	 
    	 //System.out.println("String Hashtable B: " + hs);
    	 
    	 // Prints out the number of unique words stored
    	 System.out.println("Size of Hashtable B: " + hs.size() + " unique words");
       
    	 System.out.print("Hashtable B Values: ");
    	 // Loop to store into Hashtable taking mod as parameter
    	 // To print out Data Values add System.out.println in front of str.hashing
    	 for (DataA str: words) {
    		 str.hashingB(hs.size());
    	 }
    	 System.out.println();
    	 
    	 // Variable to store the end time of test
    	 endTime = System.currentTimeMillis();
    	 // Print out the total time taken to run test
    	 System.out.println("Time to sort: " + (endTime - startTime) + " milliseconds");
    }
    
    // Third Hash Test
    public void runHashC() { 
    	// Variable to store the start time of test
   	 	startTime = System.currentTimeMillis();
    	// Initializes Hashtable with type String 
    	HashSet<String> hs = new HashSet<String>(100);
    	System.out.println("Hashtable C Data:");
    	
    	// Loop to add elements
    	 for (DataA w: words) {
    		 // Makes sure no duplicate words are stored
    		 if (!hs.contains(w.str)) {
    			 hs.add(w.str);
    		 }
         } 
    	
    	 /* Prints out the Hashtable
     	 **	Commented out this to make faster & cleaner output */
    	 
    	 // System.out.println("String Hashtable C: " + hs);
    	 
    	 // Prints out the number of unique words stored
    	 System.out.println("Size of Hashtable C: " + hs.size() + " unique words");
      
    	 System.out.print("Hashtable B Values: ");
    	 // Loop to store into Hashtable taking mod as parameter
    	 // To print out Data Values add System.out.println in front of str.hashing
    	 for (DataA str: words) {
    		 str.hashingC(hs.size());
    	 }
    	 System.out.println();
    	 
    	 // Variable to store the end time of test
    	 endTime = System.currentTimeMillis();
    	 // Print out the total time taken to run test
    	 System.out.println("Time to sort: " + (endTime - startTime) + " milliseconds");
    }
    
    // Fourth Hash Test
    public void runHashD() { 
    	// Variable to store the start time of test
   	 	startTime = System.currentTimeMillis();
    	// Initializes Hashtable with type String 
    	HashSet<String> hs = new HashSet<String>(100);
    	System.out.println("Hashtable D Data:");
    	
    	// Loop to add elements
    	 for (DataA w: words) {
    		 // Makes sure no duplicate words are stored
    		 if (!hs.contains(w.str)) {
    			 hs.add(w.str);
    		 }
         } 
    	
    	 /* Prints out the Hashtable
   	  	 *	Commented out this to make faster & cleaner output */
    	 //System.out.println("String Hashtable D: " + hs);
    	 
    	 // Prints out the number of unique words stored
    	 System.out.println("Size of Hashtable D: " + hs.size() + " unique words");
        
    	 System.out.print("Hashtable D Values: ");
    	 // Loop to store into Hashtable taking mod as parameter
    	 // To print out Data Values add System.out.println in front of str.hashing
    	 for (DataA str: words) {
    		 str.hashingD(hs.size());
    	 }
    	 System.out.println();
    	 
    	 // Variable to store the end time of test
    	 endTime = System.currentTimeMillis();
    	 // Print out the total time taken to run test
    	 System.out.println("Time to sort: " + (endTime - startTime) + " milliseconds");
    }
    
    // Fifth Hash Test
    public void runHashE() { 
    	// Variable to store the start time of test
   	 	startTime = System.currentTimeMillis();
    	// Initializes Hashtable with type String 
    	HashSet<String> hs = new HashSet<String>(100);
    	System.out.println("Hashtable E Data:");
    	
    	// Loop to add elements
    	 for (DataA w: words) {
    		 // Makes sure no duplicate words are stored
    		 if (!hs.contains(w.str)) {
    			 hs.add(w.str);
    		 }
         } 
    	
    	 /* Prints out the Hashtable
    	 **	Commented out this to make faster & cleaner output */
    	 // System.out.println("String Hashtable E: " + hs);
    	 
    	 // Prints out the number of unique words stored
    	 System.out.println("Size of Hashtable E: " + hs.size() + " unique words");
        
    	 System.out.print("Hashtable E Values: ");
    	 // Loop to store into Hashtable taking mod as parameter
    	 // To print out Data Values add System.out.println in front of str.hashing
    	 for (DataA str: words) {
    		 str.hashingE(hs.size());
    	 }
    	 System.out.println();
    	 
    	 // Variable to store the end time of test
    	 endTime = System.currentTimeMillis();
    	 // Print out the total time taken to run test
    	 System.out.println("Time to sort: " + (endTime - startTime) + " milliseconds");
    }
 
    public static void main (String [] args) {
    	// Reads in file 
        String fileName = "words012800.txt";
        if (args.length >= 1) {
            fileName = args[0];
        }
        
    	System.out.println("--- Processing file: " + fileName + " ---");
    	System.out.println();
        
        HashProgram hash = new HashProgram();
        hash.readWords(fileName);
        // printWords commented out because of printing big files 
        
        //hash.printWords();
        
        // Run Hash Functions
        System.out.println();
        System.out.println("---------------------------------------");
        hash.runHashA();
        System.out.println("---------------------------------------");
        System.out.println();
        System.out.println("---------------------------------------");
        hash.runHashB();
        System.out.println("---------------------------------------");
        System.out.println();
        System.out.println("---------------------------------------");
        hash.runHashC();
        System.out.println("---------------------------------------");
        System.out.println();
        System.out.println("---------------------------------------");
        hash.runHashD();
        System.out.println("---------------------------------------");
        System.out.println();
        System.out.println("---------------------------------------");
        hash.runHashE();
        System.out.println("---------------------------------------");
    }
}