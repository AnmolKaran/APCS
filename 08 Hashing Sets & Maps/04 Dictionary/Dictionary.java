//   Name: Anmol Karan
// Date: 3/14/23

import java.io.*;
import java.util.*;

public class Dictionary
{
   public static void main(String[] args) 
   {
      Scanner infile = null;
      PrintWriter outputFile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
         outputFile = new PrintWriter(new FileWriter("dictionaryOutput.txt"));
      }
      catch(Exception e)
      {
         System.out.println( e );
      }
      
      Map<String, Set<String>> eng2spn = makeDictionary( infile );
      outputFile.println("ENGLISH TO SPANISH");
      outputFile.println(display(eng2spn));
   
      Map<String, Set<String>>spn2eng = reverse(eng2spn);
      outputFile.println("SPANISH TO ENGLISH");
      outputFile.println(display(spn2eng));
      outputFile.close();
      
      System.out.println("File created.");
   }
   
   public static Map<String, Set<String>> makeDictionary(Scanner infile)
   {
      Map<String, Set<String>>  h = new HashMap<String, Set<String>>();
      int count = 0;
      String key = "";
      while(infile.hasNextLine()){  
         if(count !=0 &&count%2 == 1){
            
            if(!h.containsKey(key)){
               Set<String> y = new HashSet<String>();
               y.add(infile.nextLine());
               h.put(key,y);
            }else{
               h.get(key).add(infile.nextLine());
            
            }  
            
            
            
         }else{  
            key = infile.nextLine();   
           
         }
         count++;   
         
      }
      return h;
   }
   

   
   public static String display(Map<String, Set<String>> m)
   {
      String fin = "";
      for (String eng: m.keySet()){
         fin = fin + eng + " " + m.get(eng)+ "\n";
      }
      return fin;
   }
   
   public static Map<String, Set<String>> reverse(Map<String, Set<String>> dictionary)
   {
      Map<String, Set<String>>  h = new HashMap<String, Set<String>>();

      for(String s: dictionary.keySet()){
         for(String u: dictionary.get(s)){
            if(!h.containsKey(u)){
               Set<String> y = new HashSet<String>();
               y.add(s);
               h.put(u,y);
            }
            else{
              h.get(u).add(s);
            }
         }
      }
      return h;

   }
   
}


   /********************
	FILE INPUT:
   	holiday
		fiesta
		holiday
		vacaciones
		party
		fiesta
		celebration
		fiesta
     <etc.>
  *********************************** 
	FILE OUTPUT:
		ENGLISH TO SPANISH
			banana [banana]
			celebration [fiesta]
			computer [computadora, ordenador]
			double [doblar, doble, duplicar]
			father [padre]
			feast [fiesta]
			good [bueno]
			hand [mano]
			hello [hola]
			holiday [fiesta, vacaciones]
			party [fiesta]
			plaza [plaza]
			priest [padre]
			program [programa, programar]
			sleep [dormir]
			son [hijo]
			sun [sol]
			vacation [vacaciones]

		SPANISH TO ENGLISH
			banana [banana]
			bueno [good]
			computadora [computer]
			doblar [double]
			doble [double]
			dormir [sleep]
			duplicar [double]
			fiesta [celebration, feast, holiday, party]
			hijo [son]
			hola [hello]
			mano [hand]
			ordenador [computer]
			padre [father, priest]
			plaza [plaza]
			programa [program]
			programar [program]
			sol [sun]
			vacaciones [holiday, vacation]

**********************/