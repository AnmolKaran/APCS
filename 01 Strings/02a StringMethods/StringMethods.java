//Name: 
//Date: 
public class StringMethods
{
   public static void main(String[] args)
   {
      String s = "Internet",  s2 = "net", s3 = "   Inter net ";
      
      String s7 = s.substring(5);   			//  
      String s8 = s.substring(0, 5);			// 
      String s9 = s.substring(2, 6);			//
      
      int pos11 = s.indexOf('e');				// 
      int pos12 = s.indexOf('x');				// 
      int pos13 = s.indexOf('e', 4);			// 
      int pos14 = s.lastIndexOf('e');			// 
      int pos15 = s.lastIndexOf('e', 4);		// 	
      int pos16 = s.lastIndexOf('e', 2);		// 
      int pos17 = s.indexOf(s2);					// 
      int pos18 = s.indexOf(s2, 6);		   	// 
      int pos19 = s.lastIndexOf(s2);	 		// 
      int pos20 = s.lastIndexOf(s2, 6);		// 
      
      boolean isSame22 = s.equals(s2);			//
      boolean isSame23 = s.equalsIgnoreCase("internet");// 
      int result24 = s.compareTo("internet");// 
      int result25 = s2.compareTo(s);			// 
      int result26 = s.compareToIgnoreCase("internet");// 
   	
      String s28 = s.toUpperCase();				//
      String s29 = s.replace("n", "");		//
      String s30 = s3.trim();						//
   	// String methods never change the String object for which they are
   	// called.  They build and return a new string instead. For example,
   	// s3.replace('a','A') by itself is useless, because s3 remains unchanged.
   	// The technical term is "immutable," as in "Strings are immutable."
   	
      char ch = s.charAt(0);								// 
      boolean isSame36 = (ch == 'I');					// 
      boolean isLetter37 = Character.isLetter(ch);	// 
      boolean isCap38 = Character.isUpperCase(ch); // 
      char ch39 = Character.toLowerCase(ch);			// 
      String s40 = ch39 + s.substring(1);				// 
   	// three ways to visit each character of a string
      for(int i = 0; i < s.length(); i++)
         System.out.print(s.substring(i, i+1)+" ");//  
      for(int i = 0; i < s.length(); i++)
         System.out.print(s.charAt(i)+" ");			//  
      char[] chArray = s.toCharArray();
      for(int i = 0; i < chArray.length; i++)		
         System.out.print(chArray[i]+" ");			//  
      System.out.println();
      
      // Strings can be split:   String[] split(String separator)
      // The method split() returns an array of substrings split around 
      // the specified separator, which is then removed
      String[] abra = "ABRACADABRA".split("A");
      for(String str : abra)
         System.out.print(str+" ");                   //
      System.out.println();
      String[] abra2 = "ABRACADABRA".split("BR");
      for(String str : abra2)
         System.out.print(str+" ");                   //
      System.out.println();
      String[] words = "A sentence with lots of words".split(" ");
      for(String str : words)
         System.out.println(str+" ");                 //
                                                      //
                                                         
      /*   String Methods #1 
      1. The string dateStr represents a date in the format "mm/dd/yyyy".   
      Write a code fragment that changes dateStr to the format "dd-mm-yy".  
      For example, "09/16/2008" becomes "16-09-08".  
      */     
      String dateStr = "05/15/2022";
      String firstPart = dateStr.substring(0,2);
      String secondPart = dateStr.substring(3,5);
      String thirdPart = dateStr.substring(8,10);
      String newStr = firstPart + "-"+secondPart + "-"+thirdPart;
      System.out.println(newStr);
   
   
   
      
      /*  String Methods #2  
      2.	Given a line of text, print each word on its own line, but don't 
      print the blank lines.  */ 
      String line = "Fall    Sports (football, golf,    cheerleading, volleyball, field hockey, cross country) start in 1 week. ";    
      //split line using space
      //if new item in array is equal to nothing then take it out
      String[] splitted = line.split(" ");
      int finalarray_length = splitted.length;
      for (int i = 0; i <splitted.length;i++){
         if (splitted[i] == " "){
            finalarray_length--;
         }
      }
      String[] finalarray = new String[finalarray_length];
      for (int i = 0; i <splitted.length; i++){
         if (splitted[i] != " "){
            System.out.println(splitted[i]);
         }
      }
   
   
      /*   String Methods #3 
      3. Given a line of text, remove all punctuation from that line.
      One way is to replace each punctuation mark with "".
      */      
      String str = "RT @TJCheer2015: Freshman & Sophomores: Interested in cheer at TJ? Email: thomasjeffersoncheer@gmail.com";
      String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
      
      String[] splittedbychar = str.split("");
      String[] punc = punct.split("");
      
      for (int i = 0; i <(punc).length; i++){
         str = str.replace(punc[i], "");
                  
      }
      System.out.println(str);
      
      
      /*   String Methods #4 
      4. Given a line of text, remove all punctuation from that line.
      One way is to keep all characters that are letters or a space.
      */      
      String str2 = "a @galaxy far, far away --right there! on the (TJ planetarium} ceiling. https://t.co/XfoqbyA9JY";
      String letters = " ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
      String cleaned = "";
      
      for (int i = 0; i <str2.length(); i++){
           //System.out.println(str2i]);
         if (!(letters.contains(String.valueOf(str2.charAt(i))))){
           str2 = str2.replace(String.valueOf(str2.charAt(i)), "");
         }
      }
      System.out.println(str2);  
       
   }
}

   /******************************
I n t e r n e t I n t e r n e t I n t e r n e t 
  BR C D BR 
 A ACADA A 
 A 
 B 
 R 
 A 
 C 
 A 
 D 
 A 
 B 
 R 
 A 
 16-09-08
 Fall
 Sports
 (football,
 golf,
 cheerleading,
 volleyball,
 field
 hockey,
 cross
 country)
 start
 in
 1
 week.
 RT TJCheer2015 Freshman  Sophomores Interested in cheer at TJ Email thomasjeffersoncheergmailcom
 a galaxy far far away right there on the TJ planetarium ceiling httpstcoXfoqbyAJY
 
 ********************************/