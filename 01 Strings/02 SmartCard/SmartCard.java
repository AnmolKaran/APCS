//name:    date:

import java.text.DecimalFormat;
import java.lang.Math;

public class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   /* enter the private fields */
   private double balance;
   private Boolean onTrain;
   private Station station;
   /* the one-arg constructor  */
   public SmartCard(double bal){
      balance = bal;
      station = null;
      onTrain = false;
   }
   
   public double getBalance(){
      return balance;
   }
   public String getFormattedBalance(){
      DecimalFormat d = new DecimalFormat("$0.00");
      return d.format(balance);
   }
   public Boolean getIsBoarded(){
      return onTrain;
   }
   public Station getBoardedAt(){
      return station;
   }
   
   public void board(Station s){
      if (onTrain == true){
         System.out.println("Error: already boarded?!");
         return;
      }
      if (balance< 0.5){
         System.out.println("Insufficient funds to board. Please add more money.");
         return;
      }
      station = s;
      onTrain = true;
   }
   
   public double cost(Station s){
      if(s.getZone() == station.getZone()){
         return 0.50;
      }else{
         double diff = Math.abs(s.getZone() - station.getZone());
         return (0.50 + (diff*0.75));
      }
      
      
   }
      
   public void exit(Station s){
      if (onTrain == false){
         System.out.println("Error: Did not board?!");
         return;
      }
      if (cost(s) >balance){
         System.out.println("Insufficient funds to exit. Please add more money.");
         return;
      }
      balance = balance - cost(s);
      onTrain = false;
      
      System.out.println("From " + station.getName() + " to " +s.getName() +" costs "+ df.format(cost(s)) +". SmartCard has " + df.format(balance));
      station = null;
   }
   
   public void addMoney(double d){
      balance = balance +d;
      System.out.println(df.format(d) + " added. Your new balance is " + df.format(balance));
      
   }
      
   /* write the instance methods  */
  
      
    

}
   
// ***********  start a new class.  The new class does NOT have public or private.  ***/
class Station
{
   private int zone;
   private String name;
   public Station(){
      zone = 0;
      name = "";
   }  
   public Station(String n, int z){
      zone = z;
      name = n;
   }
   public int getZone(){
      return zone;
   }
   public String getName(){
      return name;
   }
   
}

