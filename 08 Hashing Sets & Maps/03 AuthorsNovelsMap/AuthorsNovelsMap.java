//  name: Anmol Karan
// date: 3/14/23

import java.io.*;
import java.util.*;

public class AuthorsNovelsMap
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim()+".txt";
      Scanner inputFile = new Scanner(new File(inFileName));
      //System.out.print("\nEnter output file name: ");
      //String outFileName = keyboard.nextLine().trim();
   
      AuthorsMap authors = readAndMakeTheList(inputFile);
      PrintWriter outputFile = new PrintWriter(new FileWriter("authorsNovelsOut.txt"));
      outputFile.println( authors.toString() );
      inputFile.close(); 						
      outputFile.close();
      System.out.println("File created.");
   }
   
   public static AuthorsMap readAndMakeTheList(Scanner inputFile)
   {
      AuthorsMap theAuthors = new AuthorsMap();
      while(inputFile.hasNextLine())
      {
         theAuthors.readOneLine(inputFile.nextLine());
      }
      return theAuthors;
   }
}

class AuthorsMap extends TreeMap<String, Set<String>>
{
  /**   you get a TreeMap for free  **/
   Map<String, String> h = new TreeMap<String, String>();
    
   /** extracts the author and book from oneLine.
       calls addAuthorOrNovel      
      */
   public void readOneLine(String oneLine) 
   { 
      String[] splitted = oneLine.split(", ");
      addAuthorOrNovel(splitted[0], splitted[1]);     
   }
   
   /**  either inserts a new Author mapping, or updates a previous Author mapping
        */
   public void addAuthorOrNovel(String name, String book)
   {
      Set<String> h;
      name = name.toUpperCase();
      if(!this.containsKey(name.toUpperCase())){
         h= new TreeSet<String>();
         h.add(book);
         this.put(name.toUpperCase(),h);         
      }
      else{
         h = this.get(name.toUpperCase());
         h.add(book);
      }
      
      
   
   }
   
   public String toString(){
   String fin = "";
   
      for(String name: this.keySet()){
         fin = fin+ name +":" ;
         for(String book:this.get(name)){
            fin=fin+ " " +book + ",";
            
         }
         fin = fin.substring(0,fin.length()-1) + "\n";
            
      }
      return fin;

   }
}
   

/**********************  SAMPLE RUN  ********************************
   /******** Output file for infile2:
   
   DOSTOEVSKI: Crime and Punishment, The Possessed, The Brothers Karamazov, The Grand Inquisitor
   FLAUBERT: Madame Bovary, A Simple Heart, Memoirs of a Madman, Sentimental Education
   STENDHAL: The Red and the Black
   TOLSTOY: Anna Karenina, War and Peace, The Death of Ivan Illyich, The Kreutzer Sonata
   
    **********************************/