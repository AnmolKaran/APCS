// Name: Anmol Karan 
// Date: 2/14/23

import java.util.*;

public class TreeLab
{
   public static TreeNode root = null;
   public static String s = "XCOMPUTERSCIENCE";
   //public static String s = "XThomasJeffersonHighSchool"; 
   //public static String s = "XAComputerScienceTreeHasItsRootAtTheTop";
   //public static String s = "XA";   //comment out lines 44-46 below
   //public static String s = "XAF";  //comment out lines 44-46 below
   //public static String s = "XAFP";  //comment out lines 44-46 below
   //public static String s = "XDFZM";  //comment out lines 44-46 below 
   
   public static void main(String[] args)
   {
      root = buildTree( s );  //we are building trees of Strings only!
      System.out.print( display( root, 0) );
   
      System.out.print("\nPreorder: " + preorderTraverse(root));
      System.out.print("\nInorder: " + inorderTraverse(root));
      System.out.print("\nPostorder: " + postorderTraverse(root));
   
      System.out.println("\n\nNodes = " + countNodes(root));
      System.out.println("Leaves = " + countLeaves(root));
      System.out.println("Only children = " + countOnlys(root));
      System.out.println("Grandparents = " + countGrandParents(root));
   
      System.out.println("\nHeight of tree = " + height(root));
      System.out.println("Longest path = " + longestPath(root));
      System.out.println("Min = " + min(root));
      System.out.println("Max = " + max(root));	
   
      System.out.println("\nBy Level: ");
      System.out.println(displayLevelOrder(root));
   }
 
 /*  students, do not try to understand this method.
     */
   public static TreeNode buildTree(String s)
   {
      root = new TreeNode("" + s.charAt(1), null, null);
      for(int pos = 2; pos < s.length(); pos++)
         insert(root, "" + s.charAt(pos), pos, 
            (int)(1 + Math.log(pos) / Math.log(2)));
   
      insert(root, "AAA", 17, 5); 
      insert(root, "BBB", 18, 5); 
      insert(root, "CCC", 37, 6); //BBB's right child
      return root;
   }
   
    /*  students, do not try to understand this method.
     */
   public static void insert(TreeNode t, String s, int pos, int level)
   {
      TreeNode p = t;
      for(int k = level - 2; k > 0; k--)
      {
         if((pos & (1 << k)) == 0)
            p = p.getLeft();
         else
            p = p.getRight();
      }
      if((pos & 1) == 0)
         p.setLeft(new TreeNode(s, null, null));
      else
         p.setRight(new TreeNode(s, null, null));
   }
   
// tilt your head towards your left shoulder to see the tree
   public static String display(TreeNode t, int level)
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
   
   public static String preorderTraverse(TreeNode t)
   { 
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += t.getValue() + " ";              //process root
      toReturn += preorderTraverse(t.getLeft());   //recurse left
      toReturn += preorderTraverse(t.getRight());  //recurse right
      return toReturn;
   }
   
   public static String inorderTraverse(TreeNode t)
   {
        
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";              //process root

      toReturn += inorderTraverse(t.getRight());  //recurse right
      return toReturn;  
      	       						
   }
   
   public static String postorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += postorderTraverse(t.getLeft());   //recurse left

      toReturn += postorderTraverse(t.getRight());  //recurse right
      toReturn += t.getValue() + " ";              //process root

      return toReturn;  
      	       			
   }
   
   
   public static int countNodes(TreeNode t)
   {
      if(t == null){
         return 0;
         
      }
      else{
         return 1+countNodes(t.getLeft())+countNodes(t.getRight());
      }
   }
   
   public static int countLeaves(TreeNode t)
   {
      if(t == null){
         return 0;
      }
      else{
         if(t.getRight() == null && t.getLeft() ==null){
            return 1;

         }else{
           return countLeaves(t.getRight())+countLeaves(t.getLeft());

         }
      }
   }
   
   /*  hard way: use t.getLeft().getLeft(), etc.
       clever way:  use height(t)
       */   
   public static int countGrandParents(TreeNode t)
   {
      if(t == null){
         return 0;
      
      }
      
      int fin= 0; 
      if(height(t) >= 2) 
         fin++; 
      fin += countGrandParents(t.getLeft()); 

      fin += countGrandParents(t.getRight()); 
      return fin;
   
   }
   
   public static int countOnlys(TreeNode t)
   {
      if(t == null){
         return 0;
      }
      int fin = 0;
      fin += countOnlys(t.getLeft());
      if((t.getLeft() ==null &&t.getRight() !=null) ||(t.getLeft() !=null && t.getRight() == null)){ 
            fin++; 
      }
      fin+= countOnlys(t.getRight());   
      return fin;
   }
   
   
  /** returns the max of the heights to the left and the heights to the right  
      returns -1 in case the tree is null
    */
   public static int height(TreeNode t)
   { 
      if(t == null){
            return -1;}
      else{
        return  1+ Math.max(height(t.getLeft()), height(t.getRight()));
      }
      
      
   }
   
   /* The length of the longest path connecting any two nodes.  
      Usually connects two bottom-most leaves in the tree.  
      Often goes through root, but not always. 
   */
   public static int longestPath(TreeNode t)
   {
      if(t == null){
         return 0;
      }
      return height(t.getLeft() )+ height(t.getRight()) + 2;
   }
   
   
   @SuppressWarnings("unchecked")//this removes the warning message
   /*  Objects in a TreeNode must be cast to String or Comparable 
           in order to call .compareTo  
       */
   public static String min(TreeNode t)
   {
      return min(t, ""+t.getValue());  //calls the private recursive methdod
   }
   private static String min(TreeNode t, String min)
   {
      if(t == null){
         return null;
      }
      if(t.getLeft() == null && t.getRight() == null){
         return (String) t.getValue();
      }
      
      if(t.getLeft() == null){
         String temp = min(t.getRight(), t.getRight().getValue() + "");
         if(temp.compareTo(min) <0){
            return temp;
         }
         return min;
      }
      if(t.getRight() == null){
         String temp = min(t.getLeft(), t.getLeft().getValue() + "");
         if(temp.compareTo(min) <0){
            return temp;
         }
         return min;
      }
      
      String a = min(t.getLeft(), t.getLeft().getValue() + "");
      String b = min(t.getRight(), t.getRight().getValue() + "");
      if(a.compareTo(min) < 0){
         min = a;
      
      }
      if(b.compareTo(min) < 0){
         min = b;
      
      }
      return min;


   }
   
   @SuppressWarnings("unchecked")//this removes the warning message
   /*  Objects in a TreeNode must be cast to String or Comparable 
           in order to call .compareTo  */
   public static String max(TreeNode t)
   {
      return max(t, ""+t.getValue());
   }
   private static String max(TreeNode t, String max)
   {
      if(t == null){
         return null;
      }
      if(t.getLeft() == null && t.getRight() == null){
         return (String) t.getValue();
      }
      
      if(t.getLeft() == null){
         String temp = max(t.getRight(), t.getRight().getValue() + "");
         if(temp.compareTo(max) >0){
            return temp;
         }
         return max;
      }
      if(t.getRight() == null){
         String temp = max(t.getLeft(), t.getLeft().getValue() + "");
         if(temp.compareTo(max) >0){
            return temp;
         }
         return max;
      }
      
      String a = max(t.getLeft(), t.getLeft().getValue() + "");
      String b = max(t.getRight(), t.getRight().getValue() + "");
      if(a.compareTo(max) > 0){
         max = a;
      
      }
      if(b.compareTo(max) > 0){
         max = b;
      
      }
      return max;

 
   }
      
  /* this method is not recursive.  Use a local queue
     to store the children, if they exist, of the current TreeNode.
     */
   public static String displayLevelOrder(TreeNode t)
   {
      String fin = "";

      Queue<TreeNode> q = new LinkedList<TreeNode>();
      q.add(t);
      
      while(!q.isEmpty()){
         TreeNode node = q.remove();
         fin += (node.getValue() + " "); 
         if(node.getLeft() != null) {
             q.add(node.getLeft()); 
         }
         if(node.getRight() != null) {
              q.add(node.getRight()); 
         }

      }
      return fin;
      
   }
}

/***************************************************
  
       			E
 		E
 			C
 	M
 			N
 		T
 			E
 C
 			I
 		U
 			C
 	O
 			S
 					CCC
 				BBB
 		P
 				AAA
 			R
 
 Preorder: C O P R AAA S BBB CCC U C I M T E N E C E 
 Inorder: R AAA P BBB CCC S O C U I C E T N M C E E 
 Postorder: AAA R CCC BBB S P C I U O E N T C E E M C 
 
 Nodes = 18
 Leaves = 8
 Only children = 3
 Grandparents = 5
 
 Height of tree = 5
 Longest path = 8
 Min = AAA
 Max = U
 
 By Level: 
 C O M P U T E R S C I E N C E AAA BBB CCC 
     
 /*******************************************************/

