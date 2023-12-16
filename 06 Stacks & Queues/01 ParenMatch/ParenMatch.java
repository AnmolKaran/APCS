// Name: Anmol Karan
// Date: 1/22/2023

import java.util.*;

public class ParenMatch
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> paren = new ArrayList<String>();       
      for( String sub : paren )
      {
         boolean good = checkParen(sub);
         if(good)
            System.out.println(sub+ "\t good!");
         else=``
            System.out.println(sub+ "\t BAD");
      }
   }
     
   //returns the index of the left parentheses or -1 if it is not there
   public static int isLeftParen(String p)
   {
      return LEFT.indexOf(p);
   }
  
   //returns the index of the right parentheses or -1 if it is not there
   public static int isRightParen(String p)
   {
      return RIGHT.indexOf(p);
   }
   @SuppressWarnings("unchecked") 
   public static boolean checkParen(String exp)
   {
   
     Stack myStack = new Stack();
     for(char cha:exp.toCharArray())
     {
         if(RIGHT.contains(""+cha) && myStack.empty())
         {            
               return false;
           
         }
         if( RIGHT.contains(""+cha) && LEFT.indexOf(((char)myStack.peek())) == RIGHT.indexOf(cha))
         {
         
            myStack.pop();
         }
      
         else if(LEFT.contains(""+cha))
         {
            myStack.push(cha);
         }
    
      }
      Boolean isEmpty = myStack.empty();
      return isEmpty;
     
   }
}

/*****************************************

Parentheses Match
5 + 7		 good!
( 15 + -7 )		 good!
) 5 + 7 (		 BAD
( ( 5.0 - 7.3 ) * 3.5 )		 good!
< { 5 + 7 } * 3 >		 good!
[ ( 5 + 7 ) * ] 3		 good!
( 5 + 7 ) * 3		 good!
5 + ( 7 * 3 )		 good!
( ( 5 + 7 ) * 3		 BAD
[ ( 5 + 7 ] * 3 )		 BAD
[ ( 5 + 7 ) * 3 ] )		 BAD
( [ ( 5 + 7 ) * 3 ]		 BAD
( ( ( ) $ ) )		 good!
( ) [ ]		 good!
 
 *******************************************/