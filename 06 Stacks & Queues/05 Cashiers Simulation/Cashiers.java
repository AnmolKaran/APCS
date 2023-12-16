//name:  Anmol Karan     date:2/1/23

import java.util.*;
import java.io.*;
public class Cashiers
{
   public static final int CUSTOMERS_PER_MINUTE = 2;  
              
   public static void main(String[] args)
   {     
      System.out.println("Cashiers and Customers Simulation! ");
      Scanner kb = new Scanner(System.in);
      System.out.print("How many cashiers? ");
      int number_of_cashiers = kb.nextInt();
      System.out.print("How long, in minutes, should the simulation run? ");
      int time = kb.nextInt();
      
      waitTimes(time, number_of_cashiers);  //run the simulation
   } 
    
 
   public static void outputCashiersAndQueues(int min, ArrayList<Queue<Customer>> cashier)
   { 
      System.out.println("minute " + min + ": ");
      for( Queue<Customer> q : cashier )
      {
         System.out.print("          ");
         for( Customer c : q )
            System.out.print( c.toString()+" ");
         System.out.println();
      }
   }
  
   public static double calculateAverage(int totalMinutes, int customers)
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   
   public static void waitTimes(int time, int number_of_cashiers)
   {
      int customers = 0;
      int customersCheckedOut = 0;
      int thisCustomersTime;
      int totalMinutes = 0;
      int longestWaitTime = 0;
      int longestQueue = 0;
   
      ArrayList<Queue<Customer>> cashiers = new ArrayList<>();
      for(int i=0; i<number_of_cashiers; i++)
         cashiers.add( new LinkedList<Customer>() );
     /***************************************
           Write your code for the simulation.
           call outputCashiersAndQueues() to write the queues to the screen.   
      **********************************/       
   
   
   
      for(int i = 0; i <time; i ++){
         
         for(Queue<Customer> thing: cashiers){
            if(thing.size() > 0){
               Customer firstperson = thing.peek();
               if(i- firstperson.getTotalTime() > firstperson.getArrivedAt()){
                  customersCheckedOut ++;
                  
                  totalMinutes = firstperson.getTotalTime();
                  if(firstperson.getTotalTime() > longestWaitTime){
                     longestWaitTime = firstperson.getTotalTime();
                  }
                  thing.remove();
               }
            }
         }
         
         
         for(int j = 0; j <CUSTOMERS_PER_MINUTE;j++){
            
            int indofSmallestQ = 0;
            int in = -1;
            for(Queue <Customer> q : cashiers){
               in ++;
               if(q.size() <cashiers.get(indofSmallestQ).size()){
                  indofSmallestQ = in;
               }  
            }
            int t = 0;
            Queue<Customer> QtoJoin = cashiers.get(indofSmallestQ);
            for(Customer cus: QtoJoin){
               t = t + cus.getTimeSpentAtCashier();
            }
            
            Customer newguy = new Customer(i,t);
            customers++;
            QtoJoin.add(newguy);
          
            
            
         }
         for(Queue<Customer> q: cashiers){
            if(q.size() > longestQueue){
               longestQueue = q.size();
            }
         }
      
      
         System.out.println("minute " + i+":");
         
         for(Queue<Customer> qu:cashiers){
            int ind = 0;
            for(Customer c: qu){
               int TR = c.getTimeRemainingAtCashier();
               if(ind == 0){
                  System.out.print(TR + " ");
                  c.setTimeRemainingAtCashier(TR-1) ;
                
               }
               else{
                  System.out.print(c.getTimeSpentAtCashier()+ " ");
                  
               }
               ind++;
            }
            System.out.println();
           
         }
      }
   
      /*   report the data to the screen    */  
      //System.out.println();
      System.out.println("Number of cashiers = "+ number_of_cashiers);
      System.out.println("Customers joining each minute = "+ CUSTOMERS_PER_MINUTE);
      System.out.println("Customers who joined queues = " + customers);
      System.out.println("Customers who were checked out = " + customersCheckedOut);
      System.out.println("Total wait = "+(int)totalMinutes);
      System.out.println("Average wait time of those who were checked out = " + calculateAverage(totalMinutes, customersCheckedOut));
      System.out.println("Longest wait time of those who were checked out = " + longestWaitTime);
      System.out.println("Longest queue of customers = " + longestQueue);
   
   }
   
   static class Customer      
   {
      private int arrivedAt; // in minutes
      private int timeSpentAtCashier;
      private int totalTime;
      private int timeRemainingAtCashier;
      
      public Customer(int a, int t){
         arrivedAt = a;
         Random randy = new Random();
         int rand = randy.nextInt(5)+ 2;
         timeSpentAtCashier = rand;
         totalTime = t + timeSpentAtCashier;
         timeRemainingAtCashier = rand;
      }
      
      public int getTimeSpentAtCashier(){
         return timeSpentAtCashier;
      }  
      public int getArrivedAt(){
         return arrivedAt;
      }
      public int getTotalTime(){
         return totalTime;
      }
   
      public int getTimeRemainingAtCashier(){
         return timeRemainingAtCashier;
      }
      public void setTimeRemainingAtCashier(int t){
         timeRemainingAtCashier = t;
      }
   }

}

/******************************************************
 Cashiers and Customers Simulation! 
 How many cashiers? 3
 How long, in minutes, should the simulation run? 10
 minute 0: 
           4 
           5 
           
 minute 1: 
           3 6 
           4 
           5 
 minute 2: 
           2 6 
           3 3 
           4 6 
 minute 3: 
           1 6 6 
           2 3 6 
           3 6 
 minute 4: 
           6 6 5 
           1 3 6 
           2 6 6 
 minute 5: 
           5 6 5 4 
           3 6 5 
           1 6 6 
 minute 6: 
           4 6 5 4 
           2 6 5 5 
           6 6 2 
 minute 7: 
           3 6 5 4 3 
           1 6 5 5 
           5 6 2 4 
 minute 8: 
           2 6 5 4 3 
           6 5 5 5 
           4 6 2 4 5 
 minute 9: 
           1 6 5 4 3 5 
           5 5 5 5 3 
           3 6 2 4 5 
 Number of cashiers = 3
 Customers joining each minute = 2
 Customers who joined queues = 20
 Customers who were checked out = 4
 Average wait time of those who were checked out = 6.0
 Longest wait time of those who were checked out = 7
 Longest queue of customers = 6

****************************************************/