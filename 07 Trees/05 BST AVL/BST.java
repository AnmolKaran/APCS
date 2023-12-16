// Name: Anmol Karan
// Date: 3/6/23

interface BSTinterface
{
   public int size();
   public TreeNode getRoot() ;
   public boolean contains(String obj);
   public void add(String obj);          //does not balance
   public void addBalanced(String obj);  //AVL
   public void remove(String obj);       //does not re-balance
   //public void removeBalanced(String obj); //extension
   public String min();
   public String max();
   public String display();
   public String toString();
}

public class BST implements BSTinterface
{
   /*  copy your BST code  here  */
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
     return size;
   }
   public TreeNode getRoot()   //accessor method
   {
      return root;
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      root = add(root, s);
      //System.out.println((String)root.getValue());
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if(t == null)
         return new TreeNode(s);
      TreeNode q = t, p = null;
      while (q!= null){
         p = q;
         if(s.compareTo((String)p.getValue()) <=0){
            q = (p.getLeft());
         }
         else{
            q =(p.getRight());
         }
      }
      if(s.compareTo((String)p.getValue()) <=0){
         p.setLeft(new TreeNode(s,null,null));
         size ++;
      }
      else{
         p.setRight(new TreeNode(s));
         size++;
      }
      return t;
   }
     /*************************
      Copy the display() method from TreeLab. 
      **********************/
   public String display()
   {
         return display(root,0);
     
   }
   private String display(TreeNode t, int level) //recursive helper method
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public boolean contains( String obj)
   {
      return contains(root, obj);
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if(t == null){
         return false;
      }
      if(x.compareTo((String)t.getValue())<0)
         return contains(t.getLeft(), x);
      if(x.compareTo((String)t.getValue())> 0)
         return contains(t.getRight(),x);
      else
         return true;
      
      
   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if(t == null){
         return null;
      }
      while(t.getLeft()!=null)
         t = t.getLeft();
      return (String)t.getValue();
   }
   
   public String max()
   {
      return max(root);
      
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if(t == null){
         return null;
      }
      if(t.getRight() == null){
         return (String)t.getValue();
      }
      else{
         return max(t.getRight());
      }
   }
   
   public String toString()
   {
      return(toString(root));
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      if(t == null){
         return " ";
      }
      else{
         return (toString(t.getLeft()) + (String)(t.getValue()) +toString(t.getRight())) ;
        
      
      }
      
   }

   /*  precondition:  target must be in the tree.
                      implies that tree cannot be null.
   */
   public void remove(String target)
   {
      if (root == null)
         return;
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode root, String target)
   {
      // case 1a
      TreeNode current = root;
      
      TreeNode parent = null;
      
      
      int compareval = (target).compareTo((String)current.getValue());
      
      if(compareval == 0){
         if(current.getLeft() != null && current.getRight() != null){
            TreeNode p = current;
            TreeNode q = current.getLeft();
            while(q.getRight() != null){
               p = q;
               q = q.getRight();
            }
            current.setValue(q.getValue());
            if(p == current) 
               current.setLeft(q.getLeft());
            
            else
               p.setRight(q.getLeft());
            return current;
         }
         else if (current.getLeft() !=null) return current.getLeft();
         else if (current.getRight() != null) return current.getRight();
         else 
            return null;
       
      } 
      else if (target.compareTo((String)current.getValue()) < 0)
        current.setLeft(remove(current.getLeft(), target));
      else
         current.setRight(remove(current.getRight(), target));
      return current;
    }
      


      

   /*  start the addBalanced methods */
   private int calcBalance(TreeNode current) //height to right minus 
   {                                           //height to left
      
      return height(current.getRight()) - height(current.getLeft());
      
      
   }

   private int height(TreeNode current)   //from TreeLab
   {
    if(current == null){
            return -1;}
      else{
        return  1+ Math.max(height(current.getLeft()), height(current.getRight()));
      }
      
      
   }

   public void addBalanced(String value)
   {
      add(value);      
      root = balanceTree( root );   // for an AVL tree. Put in the arguments you want.
   }
   private TreeNode balanceTree( TreeNode current )  //recursive.  Whatever makes sense.
   {
      if(current == null){
         return current;
      }
      if(calcBalance(current) >1){ //right heavy
         if(calcBalance(current.getRight())<0){ //right left
            current = rightLeftRotate(current);

         }
         else{  //left rotation
            current = leftRotate(current);
         }
      }  
      else if(calcBalance(current)<-1){ //left heavy
         if(calcBalance(current.getLeft()) >0){ //left right
            current = leftRightRotate(current);

         }
         else{ //right rotation
            current = rightRotate(current);

         }
      
      }
      current.setLeft(balanceTree(current.getLeft()));
      current.setRight(balanceTree(current.getRight()));
      return current;

   }
   
   // 4 rotation helper methods
 
   private TreeNode rightRotate(TreeNode current){
     TreeNode newRoot = current.getLeft();
     current.setLeft(newRoot.getRight());
     newRoot.setRight(current);
     return newRoot;
   }
   
   private TreeNode leftRightRotate(TreeNode current){
      current.setLeft(leftRotate(current.getLeft()));
      return rightRotate(current);
   
   }
   private TreeNode leftRotate(TreeNode current){
      TreeNode newRoot = current.getRight();
      current.setRight(newRoot.getLeft());
      newRoot.setLeft(current);
      return newRoot;
   }
   private TreeNode rightLeftRotate(TreeNode current){
      current.setRight(rightRotate(current.getRight()));
      return leftRotate(current);
   
   }
}