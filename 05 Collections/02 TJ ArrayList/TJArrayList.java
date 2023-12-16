// Name: Anmol Karan  
// Date: 1/8/23

/**
 * Implements the cheat sheet's List interface.  Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * @override toString()
 */ 
@SuppressWarnings("unchecked")
public class TJArrayList<E>
{
   private int size;							//stores the number of objects
   private E[] myArray;
   @SuppressWarnings("unchecked")
   public TJArrayList()                
   {
      myArray = (E[]) new Object[10]; //default constructor instantiates a raw array with 10 cells
   
      size = 0;
   }
   public int size()
   {
      int s = 0;
      for(int i = 0; i <myArray.length; i++){
         if(myArray[i] !=null){
            s++;
         }
      }
      return s;
   }
 	/* appends obj to end of list; increases size;
      must be an O(1) operation when size < array.length, 
         and O(n) when it doubles the length of the array.
	  @return true  */
   @SuppressWarnings("unchecked")
   public boolean add(E obj)
   {
      if(size<myArray.length){
         for(int i = 0; i <myArray.length;i++){
            if (myArray[i] == null){
               myArray[i] = obj;
               size++;
               return true;
            }
         }
         return false;
      } else{
      
         E[] newArr = (E[]) new Object[myArray.length*2];
         for(int i = 0; i <newArr.length; i++){
            if(i <myArray.length){
               newArr[i] = myArray[i];
               
            }else{
               newArr[i] = obj;
               break;
            }            
         }
         myArray = newArr;
         size++;
         return true;
      }    
   }
   /* inserts obj at position index.  increments size. 
		*/
   public void add(int index, E obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      if(size >= myArray.length){
         
         E[] newArr = (E[]) new Object[myArray.length*2];

         for(int i = 0; i <newArr.length; i++){
            if(i <myArray.length){
               newArr[i] = myArray[i];
               
            }            
         }
         for(int i = newArr.length-1; i >index; i --){
            if(newArr[i-1] != null){
               newArr[i] = newArr[i-1];  
            }
         }
         newArr[index] = obj;
         myArray = newArr;
      }else{
         for(int i = myArray.length-1; i >index; i --){
            if(myArray[i-1] != null){
               myArray[i] = myArray[i-1];  
            }
         }
      }
      size++;
      myArray[index] = obj;
     
   }

   /* return obj at position index.  
		*/
   public E get(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      return(myArray[index]);
   }
   /**
    * Replaces obj at position index. 
    * @return the object is being replaced.
    */  
   public E set(int index, E obj) throws IndexOutOfBoundsException  
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E ret = myArray[index];
      myArray[index] = obj;
      return ret;
   
   }
 /*  removes the node from position index. shifts elements 
     to the left.   Decrements size.
	  @return the object that used to be at position index.
	 */
   public E remove(int index) throws IndexOutOfBoundsException  
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E ret = myArray[index];
      for(int i = index; i <myArray.length-1;i++){
         myArray[i] = myArray[i+1];
      }
      myArray[myArray.length-1] = null;
      size--;
      return ret;
     
   }
	   /*
		   This method compares objects.
         Must use .equals(), not ==
     	*/
   public boolean contains(E obj)
   {
      for(int i = 0; i <myArray.length; i ++){
         if(myArray[i] == null){
         
            return false;
         }
         if (myArray[i].equals(obj)){
            return true;
         }
      }  
      return false;
   
   }
	 /*returns a String of E objects in the array with 
       square brackets and commas.
     	*/
   public String toString()
   {
      String ret = "[";
      for(int i = 0; i<myArray.length;i++){
         if(myArray[i] !=null){
            
            if(i == myArray.length-1){
            
    
               
              ret += (myArray[i] + "]");
            }
            else if(myArray[i+1] !=null){
               ret+=myArray[i] + ", ";
            
            }else{
               ret +=myArray[i] + "]";
            }
         }
         
      }
      return ret;
   }
}