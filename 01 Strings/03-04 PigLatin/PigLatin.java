// Name:   
// Date: 
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class PigLatin
{
   public static void main(String[] args) 
   {
      //part_1_using_pig();
      part_2_using_piglatenizeFile();
      
      /*  extension only    */
      //String pigLatin = pig("What!?");
      // System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      // pigLatin = pig("{(Hello!)}");
      // System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      // pigLatin = pig("\"McDonald???\"");
      // System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in); //input from the keyboard
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();     //reads up to white space
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   
   public static String pig(String s)
   {
      if(s.length() == 0){
         return "";
      }
      //remove and store the beginning punctuation 
      String begPunct ="" ;
      String endPunct = "";
      char[] chArray = new char[s.length()];
      for (int i = 0; i < s.length(); i++) {
            chArray[i] = s.charAt(i);
      }
      //System.out.println(chArray);
      //make chArray shorter and store begpunct and endpunct. the next part just turns s into new chArray
      for(int i = 0;i<s.length();i++){
 
         if((punct.indexOf(s.charAt(i)) !=-1)){
            begPunct = begPunct +s.charAt(i);  
         }else{
 
            break;
         }
      }
      s = s.substring(begPunct.length());

      for(int i = s.length()-1; i>-1;i--){
         //System.out.println(s.charAt(i));
         //System.out.println(i);
         if(punct.indexOf(s.charAt(i)) != -1){
            endPunct = endPunct + s.charAt(i);
         }else if (punct.indexOf(s.charAt(i))==-1){
            break;
         }
      }
      s = s.substring(0,s.length()-endPunct.length());   
      
         
           //System.out.println(s);
      //remove and store the ending punctuation 
         
         
      //START HERE with the basic case:
      //     find the index of the first vowel
      //     y is a vowel if it is not the first letter
      //     qu
      Boolean firstVowelfound = false;
      String movePart = "";
      String remainPart = "";
      String fin = "";
      for (int j = 0; j<s.length();j++){ 
         char ch = s.charAt(j);    
         for(int i = 0; i<vowels.length();i++){
            char vow = vowels.charAt(i);
            //System.out.println((Character.toString(ch).toUpperCase()));
            if (firstVowelfound == false && (ch == vow ||((Character.toString(ch).toUpperCase()).indexOf('Y') !=-1&& j !=0)) && fin =="" ){
               
               
               
               if(ch =='u' && j>=1&& s.charAt(j-1) == 'q'){
               
                 if (s.charAt(j+1)== vow){
                        firstVowelfound = true;
                        String firstL = Character.toString(s.charAt(0));
                        firstL = firstL.toLowerCase();
                        
                        movePart = s.substring(1,j+1);
                        remainPart = s.substring(j+1);
                        fin = remainPart+firstL+movePart + "ay";
                                          
                  }
               }else{
                  firstVowelfound = true;
                  String firstL = Character.toString(s.charAt(0));
                  firstL = firstL.toLowerCase();
                  if(j ==0){
                     movePart = s.substring(0,j);
                     remainPart = s.substring(j);
                     movePart = movePart.toLowerCase();
                     fin = remainPart+movePart +"way";

                  }else if (s.length()>1){
                     movePart = s.substring(1,j);
                     remainPart = s.substring(j);
                     if (j == 0){
                        fin = remainPart +firstL +movePart +"way";
                     }else{
                        fin = remainPart +firstL+movePart + "ay";
                     }  
                   }else{
                     movePart = s.substring(0,j);
                     remainPart = s.substring(j);
                     if (j == 0){
                        fin = remainPart  +movePart +"way";
                     }else{
                        fin = remainPart +movePart + "ay";
                     }  


                   }
               }             
            }
            
         }
      }
      if (firstVowelfound ==false){
         return "**** NO VOWEL ****";
      }
      
      

      //if no vowel has been found
      
      
      //is the first letter capitalized?
      
      if(Character.toString(s.charAt(0)).equals(Character.toString(s.charAt(0)).toUpperCase())){
      
         //fin = fin.toLowerCase();
         String firstLetter = Character.toString(fin.charAt(0));
         fin = fin.substring(1);
         fin = firstLetter.toUpperCase()+fin;
      }
      //return the piglatinized word 
      
      if (begPunct !=null){
         fin = begPunct +fin;
      }
      if (endPunct !=null){
         fin = fin+endPunct;
      }
      return fin;
      
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
    
      
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      
      
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile2 = null;
      try
      {
         infile2 = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   

      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      int numLines = 0;
      while(infile2.hasNextLine()) {
        infile2.nextLine();
        numLines++;
      }
      String[] linesNwords = new String[numLines];
      int iteration =-1;
      
      while (infile.hasNextLine()){

         iteration ++;
         String line = infile.nextLine();
         String[] lineSplitted = line.split(" ");
         for(int i = 0; i <lineSplitted.length;i++){
            //System.out.println(lineSplitted[i]);
            String latenized = pig(lineSplitted[i]);
            if(linesNwords[iteration] == null){
               linesNwords[iteration] = latenized+ " ";
            }else{
               linesNwords[iteration] = linesNwords[iteration]+ latenized+ " ";
            }
         }
      }
    
      for(int i = 0; i <linesNwords.length;i++){
         outfile.write("\n"+linesNwords[i]);
         
         
      }  
      
      
   
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s)
   {
      return "";
   }
}
