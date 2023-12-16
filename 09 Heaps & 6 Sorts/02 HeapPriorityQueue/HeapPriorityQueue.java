 //Name:   Anmol Karan
 //Date:  5/10/23
 
import java.util.*;

/* implement the API for java.util.PriorityQueue
 *     a min-heap of objects in an ArrayList<E> in a resource class
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public ArrayList<E> getHeap()   //for Codepost
   {
      return myHeap;
   }
   
   public int lastIndex()
   {
      return myHeap.size()-1;
      
      
   }
   
   public boolean isEmpty()
   {
      if( myHeap.size() <2){
         return true;
      }
      return false;
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(lastIndex());
      return true;
   }
   
   public E remove()
   {
      if( myHeap.size() < 2){
         return null;
      }
      E var = myHeap.get(1);
      swap(1,myHeap.size()-1);

      myHeap.remove(myHeap.size()-1);
      heapDown(1, myHeap.size());
      
      
		return var;
      
      
   }
   
   public E peek()
   {
      if( myHeap.size() < 2){
         return null;
      }
      return myHeap.get(1);
   }
   
   //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapUp(int k)
   {
      if((k/2)<1 ){
         
      }
      else {
         E kvar = myHeap.get(k);

         E var = myHeap.get(k/2);
         if (var.compareTo(kvar) <0){
				return;
         }
         swap(k/2,k);
			heapUp(k/2);
            
      }
      
      
   }
   
   private void swap(int a, int b)
   {
      E fin = myHeap.get(a);
		myHeap.set(a, myHeap.get(b));
		myHeap.set(b, fin);
   }
   
  //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapDown(int k, int lastIndex)
   {
      int l = 2*k;
		int r = l + 1;
      E fin = null;

		if (k >=lastIndex && l>lastIndex) {
		}
      else if (l< lastIndex && r < lastIndex && k < lastIndex){
         if(myHeap.get(l).compareTo(myHeap.get(r))< 0){
            fin = myHeap.get(l);
         }else{
            fin = myHeap.get(r);
         }
         if (myHeap.get(k).compareTo(fin) > 0) {
				if (myHeap.get(l).compareTo(fin)== 0) {
					swap(k, l);
					heapDown(l, lastIndex);
				} else {
					swap(k, r);
					heapDown(r, lastIndex);
				}
			}
      
      }else if ((l < lastIndex && myHeap.get(k).compareTo(myHeap.get(l)) > 0)) { 
 			swap(k, l);
			heapDown(l, lastIndex);
		}
         
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
