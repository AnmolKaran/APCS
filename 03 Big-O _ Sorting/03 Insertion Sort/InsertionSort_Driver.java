 //Name: Anmol Karan         
 //Date: 10/30/22

import java.util.*;
import java.io.*;

public class InsertionSort_Driver
{
   public static void main(String[] args) throws Exception
   {
      //Part 1, for doubles
      int n = (int)(Math.random()*100)+20;
      double[] array = new double[n];
      for(int k = 0; k < array.length; k++)
         array[k] = Math.random()*100;	
      
      
      Insertion.sort(array);  //students write
      print(array);
      
      if( isAscending(array) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
      System.out.println();
      
      //Part 2, for Strings
      int size = 100;
      Scanner sc = new Scanner(new File("declaration.txt"));
      Comparable[] arrayStr = new String[size];
      for(int k = 0; k < arrayStr.length; k++)
         arrayStr[k] = sc.next();	
   
      Insertion.sort(arrayStr);   //students write
      print(arrayStr);
      System.out.println();
      
      if( isAscending(arrayStr) )
         System.out.println("In order!");
      else
         System.out.println("Out of order  :-( ");
   }
   
   public static void print(double[] a)
   {
      // for(int k = 0; k < a.length; k++)
         // System.out.println(a[k]);
      for(double temp: a)         //for-each
         System.out.print(temp+" ");
      System.out.println();
   }
   
   public static void print(Object[] papaya)
   {
      for(Object temp : papaya)    
         System.out.print(temp+" ");
   }
   
   public static boolean isAscending(double[] a)
   {
      for(int i = 0; i <a.length-1; i++){
         if (a[i] >a[i+1]){
            return false;
         }
      }
      return true;
   }
   
   @SuppressWarnings("unchecked")//this removes the warning for Comparable
   public static boolean isAscending(Comparable[] a)
   {
      for(int i = 0; i <a.length-1; i++){
         if (a[i] .compareTo(a[i+1])>=1){
            return false;
         }
      }
      return true;
   }
}

//**********************************************************

class Insertion
{
   public static void sort(double[] array)
   { 
      for (int i = 1; i <array.length; i++){
   
         int shouldBeAt = shift(array,i,array[i]);
         //System.out.println("shouldbeAt: "+ shouldBeAt);
        // System.out.println("i: " + i);
         if(shouldBeAt < i){
            
         
            int curr = i;
            double og = array[curr];

            for(int j = curr-1; j >shouldBeAt; j--){
               //System.out.println("j: " +j);
               if(j <0){
                  break;
               }
               array[j+1] = array[j];
            }
            array[shouldBeAt] = og;
            //for (int p = 0 ; p < array.length; p++){
            //   System.out.print(array[p]+ " ");
            //   
           // }
         }
        // System.out.println("");

         
      }
         
   }
 
   private static int shift(double[] array, int index, double value)
   {
      if(index == 0){
         return index;
      }  
      if(value>= array[index-1]){
         return index;
      }
      if(value < array[index-1]){
         int ind = shift(array,index-1, value);
         return ind;
      } 
      return -1;
   }
 
   @SuppressWarnings("unchecked")
   public static void sort(Comparable[] array)
   { 
   
    for (int i = 1; i <array.length; i++){
   
         int shouldBeAt = shift(array,i,array[i]);
     
         if(shouldBeAt<i){
            
         
            int curr = i;
            Comparable og = array[curr];

            for(int j = curr-1; j >shouldBeAt; j--){
               if(j <0){
                  break;
               }
               array[j+1] = array[j];
            }
            array[shouldBeAt] = og;
      
         }

         
      }

   }
   
   @SuppressWarnings("unchecked")
   private static int shift(Comparable[] array, int index, Comparable value)
   {
      if(index == 0){
         return index;
      }  
      if(value.compareTo(array[index-1]) >=0){
         return index;
      }
      if(value.compareTo(array[index-1])<=-1){
         int ind = shift(array,index-1, value);
         return ind;
      } 
      return -1;
   
   
   }
}
