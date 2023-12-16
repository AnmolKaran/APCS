// Name:   Anmol Karan
// Date: 5/29/23
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/**************** Graphs 3: EdgeList *****/
interface VertexInterface
{
   public String getName();
   public HashSet<Vertex> getAdjacencies();   
   
   /*
     postcondition: if the set already contains a vertex with the same name, the vertex v is not added
                    because adjacencies is a HashSet, this method should operate in O(1)
   */
   public void addAdjacent(Vertex v);
   /*
     postcondition:  returns as a string one vertex with its adjacencies, without commas.
                     for example, D [C A]
     */
   public String toString(); 
 
} 
 
/*************************************************************/
class Vertex implements VertexInterface, Comparable<Vertex> //2 vertexes are equal if and only if they have the same name
{
   private final String name;
   private HashSet<Vertex> adjacencies;
  /* enter your code here  */

   public Vertex(String n){
      name = n;
      adjacencies = new HashSet<>();
      
      
   }
   public int compareTo(Vertex v){
      return name.compareTo(v.name);
   }  
   public boolean equals(Object b){
      if (b instanceof Vertex){
         return equals((Vertex)b);
      }
      else{
         return false;
      }     
   }
   public boolean equals(Vertex v)
   {  return v.name.equals(name);
   }
   public int hashCode(){
      return name.hashCode();
   }  
   public String getName(){
      return name;
   }
    
   public HashSet<Vertex> getAdjacencies(){
      return adjacencies;   
   
   }
   public void addAdjacent(Vertex v){
      adjacencies.add(v);
   }

   public String toString(){
      String toRet = name + " [";
      for(Vertex i: adjacencies){
         toRet += i.getName() + " ";
        
      }
      return toRet.trim() + "]";
   }
    
  
  
}   

/*************************************************************/
interface AdjListInterface 
{
   public Set<Vertex> getVertices();
   public Vertex getVertex(String vName);
   public Map<String, Vertex> getVertexMap();  //this is just for codepost testing
   
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName);
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(log n)
   */
   public void addEdge(String source, String target); 
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString(); 

}

  
/********************** Graphs 4: DFS and BFS *****/
interface DFS_BFS
{
   public String depthFirstSearch(String name);
   public String breadthFirstSearch(String name);
   /*   extra credit  */
  // public String depthFirstRecur(String name);
  // public List<Vertex> depthFirstRecurHelper(Vertex v, List<Vertex> reachable);
}

/****************** Graphs 5: Edgelist with Cities *****/
interface EdgeListWithCities
{
   public void readData(String cities, String edges) throws FileNotFoundException;
   public int edgeCount();
   public int vertexCount();
   public boolean isReachable(String source, String target);
   public boolean isStronglyConnected(); //return true if every vertex is reachable from every 
                                          //other vertex, otherwise false 
}


/*************  start the Adjacency-List graph  *********/
public class AdjList implements AdjListInterface//, DFS_BFS//, EdgeListWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();
      
   /* constructor is not needed because of the instantiation above */
   
   public Set<Vertex> getVertices(){
      Set<Vertex> s =  new HashSet<>();
      for (String k : vertexMap.keySet()){
         s.add(vertexMap.get(k));
         
         
      }
      return s;
   }
   public Vertex getVertex(String vName){
      return vertexMap.get(vName);
   }
   public Map<String, Vertex> getVertexMap(){return vertexMap;}
   
   public void addVertex(String vName)
  {
      if(!vertexMap.keySet().contains(vName)){
      
      
      vertexMap.put(vName,new Vertex(vName));}
      
      
   }

   public void addEdge(String source, String target){
      vertexMap.get(source).addAdjacent(vertexMap.get(target));
      
   }

   public String toString(){
      String fin = "";
      
      for(String i : vertexMap.keySet()){
         fin  += vertexMap.get(i).toString() +"\n";
      }
      return fin;

   }


   public String depthFirstSearch(String name){
      Stack<Vertex> stk = new Stack<>();
      ArrayList<Vertex> al = new ArrayList<>();
      stk.push(vertexMap.get(name));
      while(!stk.isEmpty()){
         Vertex popped = stk.pop();
         if(!al.contains(popped)){
            al.add( popped);
            for(Vertex v: popped.getAdjacencies()){
               stk.push(v);
            }
         }
         
      }
      String toRet = "";
      for(Vertex v: al){
         toRet += v.getName() + " ";
         
      }
      return toRet;
   }  
   public String breadthFirstSearch(String name){
      ArrayList<Vertex> al = new ArrayList<>();
      Queue<Vertex> q = new LinkedList<>();
      q.add( vertexMap.get(name) );
      while(!q.isEmpty()){
         Vertex popped = q.remove()  ;
         if(!al.contains(popped)){
            al.add(  popped );
            for( Vertex v: popped.getAdjacencies()){
               q.add(   v );
            }
         }
      }
      String toRet = "";
      for(Vertex v: al){
         toRet += v.getName() + " ";
         
      }
      return toRet;
   
   
   }
   /* enter your code here  */
 
 
   public void readData(String cities, String edges) throws FileNotFoundException{
      Scanner city_infile = new Scanner(new File(cities));
      while(city_infile.hasNext())
         addVertex(city_infile.next());
      
      Scanner edge_infile = new Scanner(new File(edges));
      while( edge_infile.hasNext() )
      {
         String source = edge_infile.next();
         String target = edge_infile.next();
         addVertex(source);
         addVertex(target);
         addEdge(source,target);
         
   
      }
      
      
   }
   public int edgeCount(){
      int count = 0; 
      for(Vertex v: getVertices()){
         
         count += v.getAdjacencies().size();
      }
      return count;
   }
   
   public int vertexCount(){
      return vertexMap.size();
   
   }
   public boolean isReachable(String source, String target){
      return breadthFirstSearch(source).contains(target);
   }
   public boolean isStronglyConnected(){
   
      for(Vertex source: getVertices())
         for(Vertex target: getVertices())
            if( source != target && isReachable(source.getName(), target.getName()) == false){
               return false;
            }
      
      return true;   
   
   } //return true if every vertex is reachable from every 
                                          //other vertex, otherwise false 
 
 
 
 
}


