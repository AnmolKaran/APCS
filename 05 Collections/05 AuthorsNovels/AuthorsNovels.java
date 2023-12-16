//  Name: Anmol Karan     Date:1/15/23

import java.io.*;
import java.util.*;

public class AuthorsNovels
{
   public static void main(String[] args) throws IOException
   {
      /*   test the AuthorEntry object  */
      AuthorEntry a = new AuthorEntry("Aaaa");
      System.out.println("name: " + a.getName());
      System.out.println("AuthorEntry.toString(): " + a);
      AuthorEntry b = new AuthorEntry("Dd", "y");
      System.out.println("name: " + b.getName());
      b.addNovel("z");
      b.addNovel("y");
      b.addNovel("x");
      System.out.println("AuthorEntry.toString(): " + b);
      System.out.println(b.compareTo(a));   // 3
      System.out.println(a.compareTo(b));   // -3
      
      /*  start the lab  */
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nEnter input file name: ");
      String inFileName = keyboard.nextLine().trim()+".txt";
      Scanner inputFile = new Scanner(new File(inFileName));
      //System.out.print("\nEnter output file name: ");
      //String outFileName = keyboard.nextLine().trim();
      AuthorList authors = readAndMakeTheList(inputFile);
      String outFileName = "authorsNovelsOut.txt";
      PrintWriter outputFile = new PrintWriter(new FileWriter(outFileName));
      outputFile.println( authors.toString() );
      inputFile.close(); 						
      outputFile.close();
      System.out.println("Done.");
   }
   
   public static AuthorList readAndMakeTheList(Scanner inputFile)
   {
      AuthorList theList = new AuthorList();
      while(inputFile.hasNextLine())
      {
         theList.readOneLine(inputFile.nextLine());
      }
      return theList;
   }
}

class AuthorList extends ArrayList<AuthorEntry>
{
    /**   you get an ArrayList for free   **/
   public AuthorList()
   {
      super();
   }
     /** extracts the author and book from oneLine.
         calls addAuthorEntry      
      */
   public void readOneLine(String oneLine) 
   {  
      String[] splitted = oneLine.split(", ");
      addAuthorEntry(splitted[0], splitted[1]);
   }
      
    /** use a listIterator.  Needs to call .previous() 
          either inserts a new AuthorEntry object, or 
          adds a novel to a previous AuthorEntry object,
          in alphabetic order
    */
   public void addAuthorEntry(String name, String book)
   {
      Boolean added = false;
      
      
      ListIterator<AuthorEntry> pi = this.listIterator();  
      while(pi.hasNext()){
         if(pi.next().getName().toUpperCase().compareTo(name.toUpperCase()) == 0){
            pi.previous().addNovel(book);
            added = true;
            return;
         }
      }
      
      /**
      for (AuthorEntry entry: this){
         if (entry.getName() == name.toUpperCase()){
            //System.out.println("hi");
            entry.addNovel(book);
            added = true;
            return;
         }
      }*/
      if (added == false){
         ListIterator<AuthorEntry> li = this.listIterator();  
	      while( li.hasNext() ){
		      if( li.next().getName().compareTo(name.toUpperCase()) >=0){
			      li.previous();
               li.add(new AuthorEntry(name,book));
               return;
            }            
         }
         li.add(new AuthorEntry(name,book));
      

      }
      
      
      
   }
   
   public String toString()
   {  
      String fin ="";
      for(AuthorEntry i : this){
         fin  = fin + i.toString() +"\n";
      }
      return fin;
   }
}

class AuthorEntry implements Comparable<AuthorEntry>
{
   //fields
   private String name;
   private ArrayList<String> novels;
   
   //two constructors. argument n may be in lowercase. 
   public AuthorEntry(String n)
   {
      name = n.toUpperCase();
      novels = new ArrayList<String>();

   }
   public AuthorEntry(String n, String book)
   {
      name = n.toUpperCase();
      novels = new ArrayList<String>();
      novels.add(book);
   }
   
   /**  appends book to novels, but only if it is not already in that list.    
       */
   public void addNovel(String book)
   {
      if(!(novels.contains(book))){
      
         novels.add(book);
      }

   }
   
   /** two standard accessor methods  */
   
   public String getName(){
      return name;
   }
   public ArrayList getNovels(){
      return novels;
   }
   
   public int compareTo(AuthorEntry b){
      return this.name.compareTo(b.getName());
   }
   
   
        
   /**  pre:  name is not an empty string.  novels might be an empty ArrayList.
       uses:  either a for-each loop or an iterator
       post:  returns a string representation of this AuthorEntry in the format as 
              shown on each line of the output file.  
     */
   public String toString()
   {
      String books = "";
      if(!(novels.isEmpty())){
      
         for(String i : novels){
         
            books = books + i +", ";
         }
         if(books.length()>=1){
            books = books.substring(0, books.length() - 2);
         }

      }else{
         return name;
      }     
      
      return (name + ": " + books);
   }

}


/********************   Extension   
// class Author extends ArrayList<String> implements Comparable<Author>
// {
// }


/**********************  SAMPLE RUN  ********************************
 name: AAAA
 novels: []
 toString(): AAAA
 name: DD
 novels: [y, z, x]
 toString(): DD: y, z, x
 3
 -3
 
 Enter input file name: infile2
 Done.
 
 **********************************************************/
   /******** Output file for infile2:
   
   DOSTOEVSKI: Crime and Punishment, The Possessed, The Brothers Karamazov, The Grand Inquisitor
   FLAUBERT: Madame Bovary, A Simple Heart, Memoirs of a Madman, Sentimental Education
   STENDHAL: The Red and the Black
   TOLSTOY: Anna Karenina, War and Peace, The Death of Ivan Illyich, The Kreutzer Sonata
   
    */