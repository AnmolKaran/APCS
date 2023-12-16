 // Name: Anmol Karan pd4   
 // Date: 1/11/23
 // use for-each loops or iterators, not regular for-loops
import java.io.*;
import java.util.*;
public class IteratorLab
{
   public static void main(String[] args)
   {
      System.out.println("Iterator Lab\n");
      int[] rawNumbers = {1, 2, 3, -2, 8};
      for(int n : rawNumbers )
         System.out.print(n + " ");    
      ArrayList<Integer> numbers = createNumbers(rawNumbers);
      System.out.println("\nArrayList: "+ numbers);      //Implicit Iterator!
      System.out.println("Count negative numbers: " + countNeg(numbers));
      System.out.println("Average: " + average(numbers));
      replaceNeg(numbers);
      System.out.println("Replace negative numbers: " + numbers);
      deleteZero(numbers);
      System.out.println("Delete zeros: " + numbers);
      String[] rawMovies = {"High_Noon", "High_Noon", "Star_Wars", "Tron", "Mary_Poppins", 
               "Dr_No", "Dr_No", "Mary_Poppins", "High_Noon", "Tron"};
      ArrayList<String> movies = createMovies(rawMovies);
      System.out.println("Movies: " + movies);
      System.out.println("Movies: " +  removeDupes(movies));
   }
      // pre: an array of int values 
   	// post: return an ArrayList containing all the values
   public static ArrayList<Integer> createNumbers(int[] rawNumbers) 
   {
    ArrayList<Integer> intList = new ArrayList<Integer>();
    for(Integer in: rawNumbers){
      intList.add(in);
    }  
    return intList;
   }
      // pre: an array of just Strings  
   	// post: return an ArrayList containing all the Strings
   public static ArrayList<String> createMovies(String[] rawWords) 
   {
      ArrayList<String> myList = new ArrayList<String>();
      for ( String str : rawWords )
         myList.add( str );
      return myList;
   }
   
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the number of negative values in the ArrayList a
   public static int countNeg(ArrayList<Integer> a)
   {
      int count = 0;
      for(Integer in: a){
         if(in< 0){
            count++;
         }
      }
      return count;
   }
   	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: return the average of all values in the ArrayList a
   public static double average(ArrayList<Integer> a)
   {
      double sum = 0;
      for(Integer in: a){
         
         sum+= in;
        
      }  
      int len = a.size();
      double avg = sum/len;
      return avg;  
   }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: replaces all negative values with 0 
   public static void replaceNeg(ArrayList<Integer> a)
   {
      ListIterator<Integer> li = a.listIterator();  
	   while( li.hasNext() ){
		   if( li.next() < 0 ){
			   li.remove(); 
            li.add(0);
         }
      }
      
      
   }
     	// pre: ArrayList a is not empty and contains only Integer objects
   	// post: deletes all zeros in the ArrayList a
   public static void deleteZero(ArrayList<Integer> a)
   {
      ListIterator<Integer> li = a.listIterator();  
	   while( li.hasNext() ){
		   if( li.next() == 0 ){
			   li.remove(); 
            
         }
      }

   }
      // pre: ArrayList a is not empty and contains only String objects
   	// post: return a new ArrayList without duplicate movie titles
      //       the parameter a is not modified!
		// strategy: start with an empty array and add movies as needed
   public static ArrayList<String> removeDupes(ArrayList<String> a)
   {
      ArrayList<String> fin = new ArrayList<String>();
      ListIterator<String> li = a.listIterator(); 
      String val;
 
	   while( li.hasNext() ){
         val = li.next();
		   if(! (fin.contains(val))){
			   fin.add(val);
         }
      }
      return fin;
      
   }
}

