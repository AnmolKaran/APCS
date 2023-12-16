// name: Anmol Karan  date: 2/5/23

import java.util.*;
import java.io.*;
public class SeniorsFirst
{
   public static final int CUSTOMERS_PER_MINUTE = 2;  
              
   public static void main(String[] args)
   {     
      PrintWriter outfile = setUpFile();      
      
      System.out.println("Seniors First Simulation! ");
      Scanner kb = new Scanner(System.in);
      System.out.print("How many cashiers? ");
      int number_of_cashiers = kb.nextInt();
      System.out.print("How long, in minutes, should the simulation run? ");
      int time = kb.nextInt();
      
      waitTimes(time, number_of_cashiers, outfile);  //run the simulation
   } 
    
   public static PrintWriter setUpFile()
   {
      PrintWriter outfile = null; 
      try
      {
         outfile = new PrintWriter(new FileWriter("customerWaitTimes.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      return outfile;
   }
  
   public static void outfileCashiersAndQueues(PrintWriter outfile, int min, ArrayList<PriorityQueue<Customer>> cashier)
   { 
      outfile.println("minute " + min + ": ");
      for( Queue<Customer> q : cashier )
      {
         outfile.print("          ");
         for( Customer c : q )
            outfile.print( c.toString()+" ");
         outfile.println();
      }
   }
  
   public static double calculateAverage(int totalMinutes, int customers)
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   
   public static void waitTimes(int time, int number_of_cashiers, PrintWriter outfile)
   {
      int customers = 0;
      int customersCheckedOut = 0;
      String[] classes = new String[]{"Senior", "Junior", "Sophomore", "Freshman"};
      int[] served = new int[]{0,0,0,0};
      int[] longestWait = new int[]{0,0,0,0};
      int[] totalWait = new int[]{0,0,0,0};
   
      ArrayList<PriorityQueue<Customer>> cashiers = new ArrayList<>();
      for(int i=0; i<number_of_cashiers; i++)
         cashiers.add( new PriorityQueue<Customer>() );
     /***************************************
           Write your code for the simulation.
           call outfileCashiersAndQueues() to write the queues to the file.  
      **********************************/       

      for(int i = 0; i <time; i++){
          for(Queue<Customer> thing: cashiers){
            if(thing.size() > 0){
               Customer firstperson = thing.peek();
               if(i- firstperson.getTotalTime() > firstperson.getArrivedAt()){
                  customersCheckedOut ++;
                  int personclass = firstperson.getstudentClass();
                  //System.out.println(personclass);
                  if(longestWait.length > 0){
                     
                     if(longestWait[personclass-1] < firstperson.getTotalTime()){
                        longestWait[personclass-1] = firstperson.getTotalTime();
                     }
                     
                  }else{
                     longestWait[personclass-1] = firstperson.getTotalTime();

                  }
                  totalWait[personclass-1] += firstperson.getTotalTime(); 
                  served[personclass-1] ++;
                  thing.remove();
               }
            }
         }
         for(int j = 0; j <CUSTOMERS_PER_MINUTE;j++){
            
            int indofSmallestQ = 0;
            int in = -1;
            for(PriorityQueue <Customer> q : cashiers){
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
      
      
         
         outfileCashiersAndQueues(outfile,i,cashiers);

      }
   
      
      System.out.println("Customer		Total		Longest		Average Wait");
      if(served[0] >0){
         System.out.println("Senior			"+served[0]+"			"+longestWait[0]+ "			" + totalWait[0]/served[0]);
      }else{
         System.out.println("Senior			"+served[0]+"			"+longestWait[0]+ "			" + totalWait[0]);

      }  
      if(served[1] >0){
         System.out.println("Junior			"+served[1]+"			"+longestWait[1]+ "			" + totalWait[1]/served[1]);
      }else{
         System.out.println("Junior			"+served[1]+"			"+longestWait[1]+ "			" + totalWait[1]);

      } 
      if(served[2] >0){
         System.out.println("Sophomore	   "+served[2]+"			"+longestWait[2]+ "			" + totalWait[2]/served[2]);
      }else{
         System.out.println("Sophomore	   "+served[2]+"			"+longestWait[2]+ "			" + totalWait[2]);

      } 
      if(served[3] >0){
         System.out.println("Freshman		 "+served[3]+"			"+longestWait[3]+ "			" + totalWait[3]/served[3]);
      }else{
         System.out.println("Freshman      "+served[3]+"			"+longestWait[3]+ "			" + totalWait[3]);

      } 
            
   
     /*  report the results to the screen in table form, like this:
         Customer		Total		Longest		Average Wait
         Senior			23			10			4.434782608695652
         Junior			18			40			7.666666666666667
         Sophomor			14			28			13.285714285714286
         Freshman			1			2			2.0
         */  
   
   
   
      outfile.close();	
   }
   
   /*  copy your Customer class and modify it for priority queues  */
   
   static class Customer implements Comparable<Customer>    
   {
      private int arrivedAt; // in minutes
      private int timeSpentAtCashier;
      private int totalTime;
      private int timeRemainingAtCashier;
      private int studentclass; //1 is senior 4 is freshman
      public Customer(int a, int t){
         arrivedAt = a;
         Random randy = new Random();
         int rand = randy.nextInt(5)+ 2;
         timeSpentAtCashier = rand;
         totalTime = t + timeSpentAtCashier;
         timeRemainingAtCashier = rand;
         Random randy2 = new Random();
         int rand2 = randy2.nextInt(4)+1;
         studentclass = rand2;
         
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
      public int getstudentClass(){
         return studentclass;
      }
      public int compareTo(Customer otherguy){
         int otherclass = otherguy.getstudentClass();
         return (otherclass - studentclass);
      }
      public String toString(){
         String strclass = "";
         if(studentclass== 1){
            strclass ="Se";
            
         }
         else if (studentclass == 2){
            strclass = "Ju";
         }
         else if (studentclass == 3){
            strclass = "So";
         }
         else if (studentclass == 4){
            strclass = "Fr";
         }
         return (timeRemainingAtCashier+"-"+strclass);
         
      }
   }

   
}

/******************************************************
to the screen:
   Seniors First Simulation! 
   How many cashiers? 4
   How long, in minutes, should the simulation run? 60
   Customer		Total		Longest		Average Wait
   Senior			23			10			4.434782608695652
   Junior			18			40			7.666666666666667
   Sophomor			14			28			13.285714285714286
   Freshman			1			2			2.0


to the file:

minute 0: 
          3-Fr 
          1-Fr 
          
          
minute 1: 
          2-Fr 
          
          1-Ju 
          1-So 
minute 2: 
          5-Ju 2-Fr 
          2-Se
          
minute 3: 
          4-Ju 2-Fr 
          1-Se 
          1-Ju 
          4-So 
minute 4: 
          3-Ju 2-Fr 
          2-Fr 
          3-Fr 
          3-So 
minute 5: 
          2-Ju 2-Fr 
          5-Ju 2-Fr 
          1-Se 3-Fr 
          2-So 
minute 6: 
          1-Ju 2-Fr 2-Ju 
          4-Ju 2-Fr 
          3-Fr 
          1-So 3-So 
minute 7: 
          2-Ju 2-Fr 
          3-Ju 2-Fr 6-Fr 
          5-Ju 3-Fr 
          3-So 
minute 8: 
          1-Ju 2-Fr 6-So 
          2-Ju 2-Fr 6-Fr 
          4-Ju 3-Fr 
          2-So 5-So 
minute 9: 
          6-So 2-Fr 
          1-Ju 2-Fr 6-Fr 
          3-Ju 3-Fr 3-Fr 
          5-Se 5-So 2-So 
minute 10: 
          5-Ju 2-Fr 6-So 2-Fr 
          2-Fr 6-Fr 
          2-Ju 3-Fr 3-Fr 
          4-Se 5-So 2-So 
minute 11: 
          4-Ju 2-Fr 6-So 2-Fr 
          4-So 2-So 2-Fr 6-Fr 
          1-Ju 3-Fr 3-Fr 
          3-Se 5-So 2-So 
minute 12: 
          3-Ju 2-Fr 6-So 2-Fr 
          3-So 2-So 2-Fr 6-Fr 
          3-Fr 4-Fr 3-Fr 
          2-Se 4-Ju 2-So 5-So 
minute 13: 
          2-Ju 2-So 6-So 2-Fr 2-Fr 
          2-So 2-So 2-Fr 6-Fr 
          5-Ju 3-Fr 3-Fr 4-Fr 
          1-Se 4-Ju 2-So 5-So 
minute 14: 
          1-Ju 2-So 6-So 2-Fr 2-Fr 
          1-So 2-So 2-Fr 6-Fr 3-So 
          4-Ju 4-Ju 3-Fr 4-Fr 3-Fr 
          4-Ju 5-So 2-So 
minute 15: 
          6-So 2-So 2-Fr 2-Fr 
          2-So 3-So 2-Fr 6-Fr 
          3-Ju 4-Ju 3-Fr 4-Fr 3-Fr 
          1-Se 4-Ju 2-So 3-So 5-So 
minute 16: 
          5-So 2-So 2-Fr 2-Fr 2-So 
          1-So 3-So 2-Fr 6-Fr 5-So 
          2-Ju 4-Ju 3-Fr 4-Fr 3-Fr 
          4-Ju 5-So 2-So 3-So 
minute 17: 
          4-So 2-So 2-Fr 2-Fr 2-So 4-Fr 
          3-So 5-So 2-Fr 6-Fr 
          1-Ju 4-Ju 3-Fr 4-Fr 3-Fr 
          3-Ju 2-Ju 2-So 3-So 5-So 
minute 18: 
          3-So 2-So 2-Fr 2-Fr 2-So 4-Fr 
          5-Ju 3-So 2-So 6-Fr 5-So 2-Fr 
          4-Ju 3-Fr 3-Fr 4-Fr 
          2-Ju 2-Ju 2-So 3-So 5-So 
minute 19: 
          2-So 2-So 2-Fr 2-Fr 2-So 4-Fr 
          4-Ju 3-So 2-So 6-Fr 5-So 2-Fr 
          3-Ju 2-So 3-Fr 4-Fr 3-Fr 6-Fr 
          1-Ju 2-Ju 2-So 3-So 5-So 
minute 20: 
          1-So 2-So 4-So 2-Fr 2-So 4-Fr 2-Fr 
          3-Ju 3-So 2-So 6-Fr 5-So 2-Fr 
          2-Ju 2-So 3-Fr 4-Fr 3-Fr 6-Fr 
          4-Se 2-Ju 1-Ju 3-So 5-So 2-So 
minute 21: 
          2-So 2-So 4-So 2-Fr 2-Fr 4-Fr 
          2-Ju 3-So 4-Ju 6-Fr 5-So 2-Fr 2-So 
          1-Ju 2-So 3-Fr 4-Fr 3-Fr 6-Fr 4-Fr 
          3-Se 2-Ju 1-Ju 3-So 5-So 2-So 
minute 22: 
          1-So 2-So 4-So 2-Fr 2-Fr 4-Fr 5-So 
          1-Ju 3-So 4-Ju 6-Fr 5-So 2-Fr 2-So 
          2-So 3-Fr 3-Fr 4-Fr 4-Fr 6-Fr 
          2-Se 2-Ju 2-Se 3-So 5-So 2-So 1-Ju 
minute 23: 
          2-So 2-Fr 4-So 2-Fr 3-Fr 4-Fr 5-So 
          4-Ju 3-So 2-So 6-Fr 5-So 2-Fr 
          1-So 3-Fr 3-So 4-Fr 4-Fr 6-Fr 3-Fr 
          1-Se 2-Ju 2-Se 3-So 5-So 2-So 1-Ju 
minute 24: 
          4-Ju 2-So 4-So 2-Fr 3-Fr 4-Fr 5-So 2-Fr 
          3-Ju 3-So 2-So 6-Fr 5-So 2-Fr 6-Fr 
          3-So 3-Fr 3-Fr 4-Fr 4-Fr 6-Fr 
          2-Se 2-Ju 1-Ju 3-So 5-So 2-So 
minute 25: 
          3-Ju 2-So 4-So 2-Fr 3-Fr 4-Fr 5-So 2-Fr 
          2-Ju 3-So 2-So 6-Fr 5-So 2-Fr 6-Fr 
          2-So 3-Fr 4-So 4-Fr 4-Fr 6-Fr 3-Fr 
          1-Se 2-Ju 4-Se 3-So 5-So 2-So 1-Ju 
minute 26: 
          2-Ju 2-So 4-So 2-Fr 3-Fr 4-Fr 5-So 2-Fr 
          2-Se 2-Ju 2-So 3-So 5-So 2-Fr 6-Fr 6-Fr 
          1-So 5-So 4-So 3-Fr 4-Fr 6-Fr 3-Fr 4-Fr 
          4-Se 2-Ju 1-Ju 3-So 5-So 2-So 
minute 27: 
          1-Ju 2-So 4-So 2-Fr 3-Fr 4-Fr 5-So 2-Fr 
          1-Se 2-Ju 2-So 3-So 5-So 2-Fr 6-Fr 6-Fr 
          4-So 5-So 3-Fr 3-Fr 4-Fr 6-Fr 4-Fr 
          3-Se 2-Ju 6-Se 3-So 5-So 2-So 1-Ju 4-So 
minute 28: 
          2-So 2-Fr 4-So 2-Fr 3-Fr 4-Fr 5-So 2-Fr 
          2-Ju 3-So 2-So 6-Fr 5-So 2-Fr 6-Fr 
          3-So 5-So 3-Fr 4-So 4-Fr 6-Fr 4-Fr 3-Fr 
          2-Se 2-Ju 6-Se 3-So 5-So 2-So 1-Ju 4-So 
minute 29: 
          3-Ju 2-So 4-So 2-Fr 3-Fr 4-Fr 5-So 2-Fr 2-Fr 
          1-Ju 3-So 2-So 5-So 5-So 2-Fr 6-Fr 6-Fr 
          2-So 5-So 3-Fr 4-So 4-Fr 6-Fr 4-Fr 3-Fr 
          1-Se 2-Ju 6-Se 3-So 5-So 2-So 1-Ju 4-So 
minute 30: 
          2-Ju 2-So 4-So 2-Fr 3-Fr 4-Fr 5-So 2-Fr 2-Fr 
          3-So 5-So 2-So 5-So 5-Fr 2-Fr 6-Fr 6-Fr 
          2-Se 2-So 3-Fr 5-So 4-Fr 6-Fr 4-Fr 3-Fr 4-So 
          6-Se 2-Ju 1-Ju 3-So 5-So 2-So 4-So 
minute 31: 
          1-Ju 2-So 4-So 2-Fr 3-Fr 4-Fr 5-So 2-Fr 2-Fr 
          2-So 5-So 2-So 5-So 5-Fr 2-Fr 6-Fr 6-Fr 6-Fr 
          1-Se 2-So 3-Fr 5-So 4-Fr 6-Fr 4-Fr 3-Fr 4-So 
          5-Se 2-Ju 1-Ju 3-So 5-So 2-So 4-So 4-So 
minute 32: 
          2-So 2-Fr 4-So 2-Fr 3-Fr 4-Fr 5-So 2-Fr 4-Fr 
          1-So 5-So 2-So 5-So 5-Fr 2-Fr 6-Fr 6-Fr 6-Fr 
          2-So 5-So 3-Fr 4-So 4-Fr 6-Fr 4-Fr 3-Fr 
          4-Se 2-Ju 1-Ju 2-Ju 5-So 2-So 4-So 4-So 3-So 
minute 33: 
          3-Se 2-So 4-So 2-Fr 2-Fr 4-Fr 5-So 2-Fr 4-Fr 3-Fr 
          5-So 5-So 2-So 6-Fr 5-Fr 2-Fr 6-Fr 6-Fr 
          1-So 5-So 3-Fr 4-So 4-Fr 6-Fr 4-Fr 3-Fr 6-Fr 
          3-Se 2-Ju 1-Ju 2-Ju 5-So 2-So 4-So 4-So 3-So 
minute 34: 
          2-Se 2-So 4-So 2-Fr 2-Fr 4-Fr 5-So 2-Fr 4-Fr 3-Fr 
          4-So 5-So 2-So 6-Fr 5-Fr 2-Fr 6-Fr 6-Fr 2-Fr 3-Fr 
          5-So 4-So 3-Fr 3-Fr 4-Fr 6-Fr 4-Fr 6-Fr 
          2-Se 2-Ju 1-Ju 2-Ju 5-So 2-So 4-So 4-So 3-So 
minute 35: 
          1-Se 2-So 4-So 2-Fr 2-Fr 4-Fr 5-So 2-Fr 4-Fr 3-Fr 
          3-So 5-So 2-So 6-Fr 5-Fr 2-Fr 6-Fr 6-Fr 2-Fr 3-Fr 
          1-Se 3-Se 3-Fr 4-So 5-So 6-Fr 4-Fr 6-Fr 3-Fr 4-Fr 
          1-Se 2-Ju 1-Ju 2-Ju 5-So 2-So 4-So 4-So 3-So 
minute 36: 
          2-So 2-So 4-So 2-Fr 2-Fr 4-Fr 5-So 2-Fr 4-Fr 3-Fr 
          2-So 5-So 2-So 6-Fr 5-Fr 2-Fr 6-Fr 6-Fr 2-Fr 3-Fr 
          3-Se 5-So 3-Fr 4-So 4-Fr 6-Fr 4-Fr 6-Fr 3-Fr 
          6-Se 2-Ju 1-Ju 2-Ju 5-So 2-So 4-So 4-So 3-So 
minute 37: 
          1-So 2-So 4-So 2-Fr 2-Fr 4-Fr 5-So 2-Fr 4-Fr 3-Fr 
          1-So 5-So 2-So 6-Fr 5-Fr 2-Fr 6-Fr 6-Fr 2-Fr 3-Fr 
          2-Se 5-Ju 3-Fr 4-So 5-So 6-Fr 4-Fr 6-Fr 3-Fr 4-Fr 
          5-Se 2-Ju 1-Ju 2-Ju 5-So 2-So 4-So 4-So 3-So 6-Fr 
minute 38: 
          4-So 2-So 5-So 2-Fr 6-So 4-Fr 2-Fr 2-Fr 4-Fr 3-Fr 
          2-So 5-So 2-Fr 6-Fr 2-So 5-Fr 6-Fr 6-Fr 2-Fr 3-Fr 
          1-Se 5-Ju 3-Fr 4-So 5-So 6-Fr 4-Fr 6-Fr 3-Fr 4-Fr 
          4-Se 2-Ju 1-Ju 2-Ju 5-So 2-So 4-So 4-So 3-So 6-Fr 
minute 39: 
          3-Ju 4-So 5-So 2-Fr 2-So 4-Fr 2-Fr 2-Fr 4-Fr 3-Fr 6-So 
          3-Se 2-So 2-Fr 6-Fr 5-So 5-Fr 6-Fr 6-Fr 2-Fr 3-Fr 2-So 
          5-Ju 5-So 3-Fr 4-So 4-Fr 6-Fr 4-Fr 6-Fr 3-Fr 
          3-Se 2-Ju 1-Ju 2-Ju 5-So 2-So 4-So 4-So 3-So 6-Fr 
minute 40: 
          2-Ju 4-So 5-So 2-Fr 2-So 4-Fr 2-Fr 2-Fr 4-Fr 3-Fr 6-So 
          2-Se 2-So 2-Fr 6-Fr 5-So 5-Fr 6-Fr 6-Fr 2-Fr 3-Fr 2-So 
          4-Ju 5-So 3-Fr 4-So 2-So 6-Fr 4-Fr 6-Fr 3-Fr 4-Fr 3-So 
          2-Se 2-Ju 1-Ju 2-Ju 5-So 2-So 4-So 4-So 3-So 6-Fr 
minute 41: 
          1-Ju 4-So 5-So 2-Fr 2-So 3-So 2-Fr 2-Fr 4-Fr 3-Fr 6-So 4-Fr 
          1-Se 2-So 2-Fr 6-Fr 5-So 5-Fr 6-Fr 6-Fr 2-Fr 3-Fr 2-So 
          3-Ju 5-So 3-Fr 4-So 2-So 6-Fr 4-Fr 6-Fr 3-Fr 4-Fr 3-So 
          1-Se 2-Ju 1-Ju 2-Ju 4-Ju 2-So 4-So 4-So 3-So 6-Fr 5-So 
minute 42: 
          4-So 2-So 5-So 2-Fr 6-So 3-So 2-Fr 2-Fr 4-Fr 3-Fr 4-Fr 
          2-So 5-So 2-So 6-Fr 2-So 2-Fr 6-Fr 6-Fr 2-Fr 3-Fr 5-Fr 
          2-Ju 5-So 4-So 4-So 2-So 3-Fr 4-Fr 6-Fr 3-Fr 4-Fr 3-So 6-Fr 
          1-Ju 2-Ju 2-So 2-Ju 4-Ju 5-So 4-So 4-So 3-So 6-Fr 
minute 43: 
          3-So 2-So 5-So 2-Fr 6-So 3-So 2-Fr 2-Fr 4-Fr 3-Fr 4-Fr 6-Fr 
          1-So 5-So 2-So 6-Fr 2-So 2-Fr 6-Fr 6-Fr 2-Fr 3-Fr 5-Fr 
          1-Ju 5-So 4-So 4-So 2-So 3-Fr 4-Fr 6-Fr 3-Fr 4-Fr 3-So 6-Fr 
          4-Se 1-Ju 2-So 2-Ju 2-Ju 5-So 4-So 4-So 3-So 6-Fr 4-Ju 
minute 44: 
          2-So 2-So 5-So 2-Fr 6-So 3-So 2-Fr 2-Fr 4-Fr 3-Fr 4-Fr 6-Fr 
          5-Ju 5-So 1-So 6-Fr 2-So 2-So 6-Fr 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 
          5-So 4-So 4-So 3-Fr 2-So 3-Fr 4-Fr 6-Fr 6-Fr 4-Fr 3-So 
          3-Se 1-Ju 3-Se 2-Ju 2-Ju 2-So 4-So 4-So 3-So 6-Fr 4-Ju 5-So 
minute 45: 
          1-So 2-So 5-So 2-Fr 6-So 3-So 2-Fr 2-Fr 4-Fr 3-Fr 4-Fr 6-Fr 4-Fr 
          4-Ju 5-So 1-So 6-Fr 2-So 2-So 6-Fr 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 
          3-Se 4-So 5-So 3-Fr 2-So 4-So 4-Fr 6-Fr 6-Fr 4-Fr 3-So 3-Fr 
          2-Se 1-Ju 3-Se 2-Ju 2-Ju 2-So 4-So 4-So 3-So 6-Fr 4-Ju 5-So 
minute 46: 
          5-So 2-So 3-So 2-Fr 6-So 6-Fr 2-Fr 2-Fr 4-Fr 3-Fr 4-Fr 4-Fr 
          4-Se 5-So 4-Ju 6-Fr 2-So 1-So 6-Fr 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 2-So 
          2-Se 4-So 5-So 3-Fr 2-So 4-So 4-Fr 6-Fr 6-Fr 4-Fr 3-So 3-Fr 2-Fr 
          1-Se 1-Ju 3-Se 2-Ju 2-Ju 2-So 4-So 4-So 3-So 6-Fr 4-Ju 5-So 
minute 47: 
          3-Se 2-So 5-So 2-Fr 6-So 3-So 2-Fr 2-Fr 4-Fr 3-Fr 4-Fr 4-Fr 6-Fr 
          3-Se 5-So 4-Ju 6-Fr 2-So 1-So 6-Fr 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 2-So 
          1-Se 4-So 5-So 3-Fr 2-So 4-So 4-Fr 6-Fr 6-Fr 4-Fr 3-So 3-Fr 2-Fr 
          3-Se 1-Ju 2-So 2-Ju 2-Ju 5-So 4-So 4-So 3-So 6-Fr 4-Ju 6-So 
minute 48: 
          2-Se 2-So 5-So 2-Fr 6-So 3-So 5-So 2-Fr 4-Fr 3-Fr 4-Fr 4-Fr 6-Fr 2-Fr 
          2-Se 5-So 4-Ju 6-Fr 2-So 1-So 6-Fr 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 2-So 
          5-So 4-So 4-So 3-Fr 2-So 3-Fr 4-Fr 6-Fr 6-Fr 4-Fr 3-So 2-Fr 
          2-Se 1-Ju 2-So 2-Ju 2-Ju 5-So 4-So 4-So 3-So 6-Fr 4-Ju 6-So 5-Fr 
minute 49: 
          1-Se 2-So 5-So 2-Fr 6-So 3-So 5-So 2-Fr 4-Fr 3-Fr 4-Fr 4-Fr 6-Fr 2-Fr 
          1-Se 5-So 4-Ju 6-Fr 2-So 1-So 6-Fr 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 2-So 6-Fr 
          4-So 4-So 4-So 3-Fr 2-So 3-Fr 4-Fr 6-Fr 6-Fr 4-Fr 3-So 2-Fr 5-Fr 
          1-Se 1-Ju 2-So 2-Ju 2-Ju 5-So 4-So 4-So 3-So 6-Fr 4-Ju 6-So 5-Fr 
minute 50: 
          5-So 2-So 3-So 2-Fr 6-So 2-Fr 5-So 2-Fr 4-Fr 3-Fr 4-Fr 4-Fr 6-Fr 
          4-Ju 5-So 1-So 6-Fr 2-So 2-So 6-Fr 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 6-Fr 
          3-So 4-So 4-So 3-Fr 2-So 3-Fr 4-Fr 6-Fr 6-Fr 4-Fr 3-So 2-Fr 5-Fr 3-Fr 
          1-Ju 2-Ju 2-So 2-Ju 4-Ju 5-So 4-So 4-So 3-So 6-Fr 4-Fr 6-So 5-Fr 
minute 51: 
          4-So 2-So 3-So 2-Fr 6-So 2-Fr 5-So 2-Fr 4-Fr 3-Fr 4-Fr 4-Fr 6-Fr 6-Fr 
          3-Ju 5-So 1-So 6-Fr 2-So 2-So 4-So 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 6-Fr 6-Fr 
          2-So 4-So 4-So 3-Fr 2-So 3-Fr 4-Fr 6-Fr 6-Fr 4-Fr 3-So 2-Fr 5-Fr 3-Fr 
          2-Ju 2-Ju 2-So 3-So 4-Ju 5-So 4-So 4-So 5-Fr 6-Fr 4-Fr 6-So 
minute 52: 
          3-So 2-So 3-So 2-Fr 6-So 2-Fr 5-So 2-Fr 4-Fr 3-Fr 4-Fr 4-Fr 6-Fr 6-Fr 
          2-Ju 5-So 1-So 6-Fr 2-So 2-So 4-So 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 6-Fr 6-Fr 
          1-So 4-So 4-So 3-Fr 2-So 3-Fr 4-Fr 6-Fr 6-Fr 4-Fr 3-So 2-Fr 5-Fr 3-Fr 
          2-Se 2-Ju 2-Ju 3-So 4-Ju 5-So 2-So 4-So 5-Fr 6-Fr 4-Fr 6-So 3-So 4-So 
minute 53: 
          3-Se 2-So 3-So 2-Fr 6-So 2-Fr 3-So 2-Fr 4-Fr 3-Fr 4-Fr 4-Fr 6-Fr 6-Fr 5-So 
          1-Ju 5-So 4-Ju 6-Fr 2-So 2-So 1-So 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 6-Fr 6-Fr 4-So 
          4-So 2-So 4-So 3-Fr 3-So 3-Fr 4-Fr 6-Fr 6-Fr 4-Fr 3-Fr 2-Fr 5-Fr 
          1-Se 2-Ju 2-Ju 3-So 4-Ju 5-So 2-So 4-So 5-Fr 6-Fr 4-Fr 6-So 3-So 4-So 
minute 54: 
          2-Se 2-So 3-So 2-Fr 6-So 2-Fr 3-So 2-Fr 4-Fr 3-Fr 4-Fr 4-Fr 6-Fr 6-Fr 5-So 
          4-Ju 5-So 1-So 6-Fr 2-So 2-So 4-So 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 6-Fr 6-Fr 
          2-Ju 2-So 4-So 3-Fr 3-So 3-Fr 4-So 6-Fr 6-Fr 4-Fr 3-Fr 2-Fr 5-Fr 4-Fr 5-So 
          2-Ju 2-Ju 2-So 3-So 4-Ju 5-So 4-So 4-So 5-Fr 6-Fr 4-Fr 6-So 3-So 
minute 55: 
          1-Se 2-So 3-So 2-Fr 6-So 2-Fr 3-So 2-Fr 4-Fr 3-Fr 4-Fr 4-Fr 6-Fr 6-Fr 5-So 
          3-Ju 5-So 1-So 6-Fr 2-So 2-So 4-So 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 6-Fr 6-Fr 4-Fr 
          1-Ju 2-So 4-So 3-Fr 3-So 3-Fr 4-So 6-Fr 6-Fr 4-Fr 3-Fr 2-Fr 5-Fr 4-Fr 5-So 
          5-Se 2-Ju 2-Ju 3-So 4-Ju 5-So 2-So 4-So 5-Fr 6-Fr 4-Fr 6-So 3-So 4-So 
minute 56: 
          3-So 2-So 3-So 2-So 6-So 2-Fr 5-So 2-Fr 4-Fr 3-Fr 4-Fr 4-Fr 6-Fr 6-Fr 2-Fr 
          2-Ju 5-So 1-So 6-Fr 2-So 2-So 4-So 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 6-Fr 6-Fr 4-Fr 
          4-So 2-So 4-So 3-Fr 3-So 3-Fr 5-So 6-Fr 6-Fr 4-Fr 3-Fr 2-Fr 5-Fr 4-Fr 
          4-Se 2-Ju 2-Ju 3-So 4-Ju 5-So 4-Ju 4-So 5-Fr 6-Fr 4-Fr 6-So 3-So 4-So 2-So 
minute 57: 
          2-Se 3-So 3-So 2-So 6-So 2-Fr 5-So 2-So 4-Fr 3-Fr 4-Fr 4-Fr 6-Fr 6-Fr 2-Fr 2-Fr 
          1-Ju 5-So 1-So 6-Fr 2-So 2-So 4-So 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 6-Fr 6-Fr 4-Fr 
          3-So 2-So 4-So 3-Fr 3-So 3-Fr 5-So 6-Fr 6-Fr 4-Fr 3-Fr 2-Fr 5-Fr 4-Fr 5-So 
          3-Se 2-Ju 2-Ju 3-So 4-Ju 5-So 4-Ju 4-So 5-Fr 6-Fr 4-Fr 6-So 3-So 4-So 2-So 
minute 58: 
          1-Se 3-So 3-So 2-So 6-So 2-Fr 5-So 2-So 4-Fr 3-Fr 4-Fr 4-Fr 6-Fr 6-Fr 2-Fr 2-Fr 
          3-Se 1-Ju 1-So 5-So 2-So 2-So 4-So 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 6-Fr 6-Fr 4-Fr 6-Fr 
          4-Ju 3-So 4-So 2-So 3-So 3-Fr 5-So 3-Fr 6-Fr 4-Fr 3-Fr 2-Fr 5-Fr 4-Fr 5-So 6-Fr 
          2-Se 2-Ju 2-Ju 3-So 4-Ju 5-So 4-Ju 4-So 5-Fr 6-Fr 4-Fr 6-So 3-So 4-So 2-So 
minute 59: 
          2-Se 3-So 3-So 2-So 6-So 2-Fr 5-So 2-So 4-Fr 3-Fr 4-Fr 4-Fr 6-Fr 6-Fr 2-Fr 2-Fr 
          2-Se 1-Ju 1-So 5-So 2-So 2-So 4-So 6-Fr 2-Fr 3-Fr 5-Fr 2-Fr 6-Fr 6-Fr 4-Fr 6-Fr 
          3-Ju 3-So 4-So 2-So 3-So 3-Fr 5-So 3-Fr 6-Fr 4-Fr 3-Fr 2-Fr 5-Fr 4-Fr 5-So 6-Fr 
          1-Se 2-Ju 2-Ju 3-So 4-Ju 5-So 4-Ju 4-So 5-Fr 6-Fr 4-Fr 6-So 3-So 4-So 2-So 5-Fr 

****************************************************/