// Name: Anmol Karan 
// Date: 1/22/23

import java.util.*;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
   
      postfixExp.add("4 5 + 8 *");
   
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      /*  enter your code here  */
      Stack myStack =  new Stack<String>();
      String[] strSplit = pf.split(" ");
      
      ArrayList<String> strList = new ArrayList<String>(Arrays.asList(strSplit));
      
      ArrayList<String> operatorss = new ArrayList<String>(Arrays.asList(operators.split(" ")));
      //System.out.println(strList);
      for(String elem : strList){
         if(!(operatorss.contains(elem))){
            //System.out.println(elem.getClass());
            myStack.push(elem);
         }
         
         else if(operatorss.contains(elem)){
        
            if(elem.equals("!")){
               Object s = myStack.pop();
               double a = Double.parseDouble(""+ s);
               int fact = 1;
               for(int i=1;i<=a;i++){    
                  fact=fact*i;    
               }    
               myStack.push(String.valueOf(fact));
            }
            else{
               //System.out.println(myStack);
               Object s = myStack.pop();
               double a = Double.parseDouble(""+ s);              
               s = myStack.pop();
               double b = Double.parseDouble(""+ s);               
               if(elem.equals("+")){
               
                  myStack.push(String.valueOf(b+a));
               }
               else if(elem.equals( "-")){
               
                  myStack.push(String.valueOf(b-a));
               }
               else if(elem.equals("*")){
               
                  myStack.push(String.valueOf(b*a));
               }
               else if (elem.equals( "/")){
                  myStack.push(String.valueOf(b/a));
               }
               else if (elem.equals("%")){
                  myStack.push(String.valueOf(b%a));
               }
               else if (elem.equals("^")){
                  double fin = 1;
                  for(int i = 0; i <a; i ++){
                     fin *=b;
                  }
                  myStack.push(String.valueOf(fin));
                  
               }
             }
         }
         
      }
      Object s = myStack.pop();
      double a = Double.parseDouble(""+ s);
      return a;
   
   }
   
   public static double eval(double a, double b, String op)
   {
      return 0;
   }
   
   public static boolean isOperator(String op)
   {
      return true;
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/