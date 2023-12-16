// Name: Anmol Karan 
// Date: 1/29/23
//uses PostfixEval

import java.util.*;
public class InfixPostfixEval
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      List<String> infixExp = new ArrayList<>();
         
         
      infixExp.add("8 + 1 * 2 - 9 / 3");
         
         
         
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         System.out.println(infix + "\t\t\t" + pf );  
         System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
      String result = "";
      Stack myStack =  new Stack<String>();
      for(String i:nums){
         if(operators.contains(i)){
            if( !myStack.isEmpty() && isStrictlyLower(i, myStack.lastElement().toString())){
               
               myStack.push(i);
            }else{
            
               if(!(myStack.isEmpty())){
               
                  result +=myStack.pop() + " ";
               }
               myStack.push(i);
            }

         }
         else if (LEFT.contains(i)){
            myStack.push(i);
            
         }
         else if (RIGHT.contains(i)){
            Object lastElem = myStack.lastElement();
            while(!(LEFT.contains(myStack.lastElement().toString()))){
               result += myStack.pop() + " ";
            }
            myStack.pop();
         }
         else{
            result+=i + " ";
         }
         
      }
      while(!(myStack.isEmpty())){
         Object popped = myStack.pop();
         if (operators.contains(popped.toString())){
            
            result +=popped + " ";
         }
      }
      return result.trim();
     
   }
   
   //enter your precedence method below
   static boolean isStrictlyLower(String next, String top){
      
      if((next == "+" && top == "-") || (next == "+" &&top == "-")){
         return false;
      }
      if((next == "*" && top == "/") || (next == "*" &&top == "/")){
         return false;
      }

      if(operators.indexOf(next) < operators.indexOf(top)){
         return false;
      }
      return true;
   }
   
}


/********************************************

Infix  	-->	Postfix		-->	Evaluate
 5 - 1 - 1			5 1 - 1 -			3.0
 5 - 1 + 1			5 1 - 1 +			5.0
 12 / 6 / 2			12 6 / 2 /			1.0
 3 + 4 * 5			3 4 5 * +			23.0
 3 * 4 + 5			3 4 * 5 +			17.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * +			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + *			-100.0
 8 + 1 * 2 - 9 / 3			8 1 2 * + 9 3 / -			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - +			-10.0
 2 + 7 % 3			2 7 3 % +			3.0
 ( 2 + 7 ) % 3			2 7 + 3 %			0.0
      
***********************************************/
