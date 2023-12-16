// Name: Anmol Karan 
// Date: 12/11/22

//  DoubleLinkedList, circular, with a dummy head node
//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

class DLL  
{
   private int size;
   private DLNode head; //points to a dummy node--very useful--don't mess with it
   public DLL()  {
  
     head= new DLNode();
     head.setNext(head);
     head.setPrev(head);
     //make it circular
     
        
   } 
   
   /* two accessor methods  */
   public int size()
   {
      return size;
   }
   public DLNode getHead()
   {
      return head;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index (the list is zero-indexed).  
      increments size. 
      no need for a special case when size == 0.
	   */
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      int count = -1;
      DLNode current = head;
      DLNode next = current.getNext();

      while(count <index){
         
         current = next;
         next = current.getNext();  
         count++; 
      }
      DLNode prev = current.getPrev();
      DLNode ne = new DLNode(obj,prev,current);
      prev.setNext(ne);
      current.setPrev(ne);
      size++;
   }
   
    /* return obj at position index (zero-indexed). 
    */
   public Object get(int index) throws IndexOutOfBoundsException
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      int count = -1;
      DLNode current = head;
      DLNode next = current.getNext();

      while(count <index){
         
         current = next;
         next = current.getNext();  
         count++; 
      }
      return current.getValue();
   }
   
   /* replaces obj at position index (zero-indexed). 
        returns the obj that was replaced.
        */
   public Object set(int index, Object obj) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      int count = -1;
      DLNode current = head;
      DLNode next = current.getNext();

      while(count <index){
         
         current = next;
         next = current.getNext();  
         count++; 
      }
      Object currentsval = current.getValue();
      DLNode prev = current.getPrev();
      DLNode ne = new DLNode();
      ne.setValue(obj);
      ne.setNext(next);
      next.setPrev(ne);
      prev.setNext(ne);
      ne.setPrev(prev);
      return currentsval;
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object in the node that was removed. 
        */
   public Object remove(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      int count = -1;
      DLNode current = head;
      DLNode next = current.getNext();

      while(count <index){
         
         current = next;
         next = current.getNext();  
         count++; 
      }
      Object currentsval = current.getValue();
      DLNode prev = current.getPrev();
      prev.setNext(next);
      next.setPrev(prev);
      size--;
      return currentsval;
   }
   
  	/* inserts obj to front of list, increases size.
	    */ 
   public void addFirst(Object obj)
   {
      DLNode current = head;
      DLNode next = current.getNext();
      DLNode ne = new DLNode();
      ne.setValue(obj);
      ne.setPrev(current);
      current.setNext(ne);
      ne.setNext(next);
      next.setPrev(ne); 
      size++;
      
   }
   
   /* appends obj to end of list, increases size.
       */
   public void addLast(Object obj)
   {
      DLNode current = head;
      DLNode last = current.getPrev();
      DLNode neww = new DLNode();
      neww.setValue(obj);
      neww.setNext(current);
      current.setPrev(neww);
      neww.setPrev(last);
      last.setNext(neww);
      size++;      
   }
   
   /* returns the first element in this list  
      */
   public Object getFirst()
   {
      DLNode current = head;
      return current.getNext().getValue();
   }
   
   /* returns the last element in this list  
     */
   public Object getLast()
   {
      DLNode current = head;
      DLNode prev = current.getPrev();
      return prev.getValue();
   }
   
   /* returns and removes the first element in this list, or
      returns null if the list is empty  
      */
   public Object removeFirst()
   {
     
      DLNode current = head;
      if(size == 0){
         return null;
      }
      Object firstVal = current.getNext().getValue();
      DLNode last = current.getPrev();
      DLNode seconditem = current.getNext().getNext();
      current.setNext(seconditem);
      seconditem.setPrev(current);
      size--;
      return firstVal;
   }
   
   /* returns and removes the last element in this list, or
      returns null if the list is empty  
      */
   public Object removeLast()
   {
      DLNode current = head;
      if(size == 0){
         return null;
      }
      Object oglast = current.getPrev().getValue();
      DLNode newlast = current.getPrev().getPrev();
      newlast.setNext(current);
      current.setPrev(newlast);
      size--;
      return oglast;
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
      String fin = "[";
      DLNode current = head;
      int count = 0;
      current = current.getNext();
      while(current !=head){
         if(count == 0){
            fin+=current.getValue();
            count = 1;
         }else{
            fin += ", " + current.getValue();
            
         }
         current = current.getNext();
      }
      fin+= "]";
      return fin;
   }
}