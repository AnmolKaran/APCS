//Name: Ammol Karan  
//Date: 5/18/23

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

import java.util.*;
import java.io.*;

interface AdjacencyMatrix
{
   public int[][] getGrid();
   public int[][] readGrid(String fileName);
   public boolean isNeighbor(int from, int to);
   public int countEdges();
   public List<Integer> getNeighbors(int source);
   public String showAllNeighbors();
   public String toString();  //returns the grid as a String
}

interface WithNames
{
   public void readNames(String fileName);
   public Map<String, Integer> getNamesAndNumbers();
   public String toStringNamesAndNumbers();  // each line contains number-name, ex: 0-Pendleton
   public boolean isNeighbor(String from, String to);
}
  
interface Warshall
{    
   public int countReachables();
   public boolean isReachable(String from, String to);  
   public List<String> getReachables(String from);
   public String toStringReachability(); //displays the reachability matrix with 2 spaces in front of each value
   public void allPairsReachability();   // Warshall's Algorithm. fills the reachability matrix                                  
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted();  //Floyd's Algorithm
}

/***********************  the graph  ******************/
public class AdjMat implements AdjacencyMatrix//, WithNames//, Warshall//, Floyd
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> namesAndNumbers = null;    // maps name to number
   private  ArrayList<String> nameList = null;  //reverses the map, index-->name
   private int[][] reachability = null; //reachability matrix for Warshall, cost matrix for Floyd
 
 /*  write constructors, accessor methods, and instance methods   */  
   public AdjMat(String matrixFile){
      grid =readGrid(matrixFile);
       
      
   }
   public int getCost(int from, int to){
      return reachability[from][to];
   }  
   public int getCost(String from, String to){
      return reachability[namesAndNumbers.get(from)][namesAndNumbers.get(to)];

   }
   public void allPairsWeighted(){
   reachability = grid;
   for(int v = 0; v< grid.length; v++){
         for(int r=  0; r <grid.length; r ++){
            for(int c = 0; c < grid.length; c ++){
               reachability[r][r] = 0;
               int sum = getCost(r,v) + getCost(v,c);
               if( sum < getCost(r,c)){
                  reachability[r][c] = sum;
               }
            }
         }
      }
   }
   public AdjMat(String matrixFile,String fileNames){
      grid = readGrid(matrixFile);
      namesAndNumbers = new TreeMap< String, Integer>();
      nameList = new ArrayList<String>();
      readNames(fileNames);
      reachability = grid;
      
   }
   public List<String> getReachables(String from){
      int a = namesAndNumbers.get(from);
     
      List<String> b= new ArrayList<String>();
      int len = reachability.length;
      
      for(int i = 0; i <len; i ++)
      {
         if (reachability[a][i] == 1){
            b.add(nameList.get(i));
         }
      }
      return b;
   }
   public void allPairsReachability(){
      reachability = grid;
      for(int v = 0; v< grid.length; v++){
         for(int r=  0; r <grid.length; r ++){
            for(int c = 0; c < grid.length; c ++){
               if (reachability[r][v] == 1 && reachability[v][c] == 1){
                  reachability[r][c] = 1;
               }
            }
         }
      }
   }
   public boolean isReachable(String f,String t){
      

      return grid[namesAndNumbers.get(f)][namesAndNumbers.get(t)] == 1;     
   }
   
   public int countReachables(){
      int num = 0;
      
      for(int i = 0; i < grid.length; i++){
         for(int j = 0; j <grid.length; j++){
            if (grid[i][j] == 1){
               num ++;
            }
         }
      }
      return num;
   }
   public int[][] getGrid(){ return grid;}
   public int[][] readGrid(String fileName){
      Scanner infile = null;
      try{
         infile = new Scanner(new File(fileName));
         int size = infile.nextInt();
         int[][] g = new int[size][size];
         for(int r = 0; r< size; r++){
            for(int c =0; c < size; c++){
               g[r][c] =  infile.nextInt();
            }
         }  
         return g;
         

      }
      catch (FileNotFoundException e){
         System.out.println("File not found.");
      }
      return null;
   }
   
   public boolean isNeighbor(int from, int to){ return grid[from][to] != 0;}
   public int countEdges(){
      int count = 0;
      //cou t is in grid
      
      for(int r = 0; r<grid.length; r++)
      {
         for(int c = 0; c<grid[0].length; c++)
         {
            if(grid[r][c] == 1){
               count++;
            }
         }
         
      
      }
      
      
      
      return count;
   
   }
   
   public List<Integer> getNeighbors(int source){
      List <Integer> neighbors = new ArrayList<Integer>();
      for( int i = 0 ; i < grid.length; i++){
         if(grid[source][i] > 0){
            neighbors.add(i);
         }
      }
      
      
      return neighbors;
   }
   
   public String showAllNeighbors(){
      String toRet = "";
      for(int r = 0; r < grid.length; r++){
         toRet += r +": " + getNeighbors(r).toString() + "\n";
         
      }
      return toRet;
      
   }
   
   public String toString(){
      String s = "";
      for(int i =0 ; i < grid.length; i ++){
         for(int j = 0; j < grid[i].length; j++){
            s += (grid[i][j] + "  ");
            
            
         }
         s += "\n";
         
      }
      return s;
   
   }

   /**************  implement the WithNames interface ************/
   
   public void readNames(String fileName){
      Scanner infile = null;
      try{
         infile = new Scanner(new File(fileName));
         int size = infile.nextInt();
         infile.nextLine();//discard first enter afte rnumber
         for(int i = 0; i < size; i ++){
            String city = infile.nextLine().trim();
            namesAndNumbers.put(city,i);
            nameList.add(city);
         }
      
      }catch(FileNotFoundException e){
         System.out.println("File not found.");
      }
   }
   public Map<String, Integer> getNamesAndNumbers(){return namesAndNumbers;}
   public String toStringNamesAndNumbers(){
      String toRet = "";
      for(int i = 0; i < nameList.size(); i ++){
         toRet += i + "-" + nameList.get(i) + "\n";
      }
      
      return toRet;
   
   }  // each line contains number-name, ex: 0-Pendleton
   public boolean isNeighbor(String from, String to){
      int f = namesAndNumbers.get(from);
      int t = namesAndNumbers.get(to);  
      return grid[f][t] != 0;
   }
   
   
      /************  implement the Warshall interface ************/
                
   /*************  implement the Floyd interface  *********/
  
  
  
  
}