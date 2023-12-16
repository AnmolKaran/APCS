// Name: Anmol Karan
// Date:  2/15/23
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   public static final String operators = "+ - * / % ^ !";
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Codepost
   {
      return root;
   }
    
   public void buildTree(String str)
   {
     	Stack<TreeNode> stk = new Stack<>();
      String[] tokens = str.split(" ");
      for(String token:tokens){
         TreeNode t = new TreeNode(token);
         if(isOperator(token)){
            //System.out.println(token);
             TreeNode first = (TreeNode)stk.pop(); 
             TreeNode sec = (TreeNode)stk.pop(); 
             TreeNode tree = new TreeNode(token, sec, first); 
             stk.push(tree); 
         }else{
            stk.push(t);
            
         }
         

      } 
      root = (TreeNode)stk.pop();
 
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      if(t == null){
         return 0.0;
      }
      String val = (String) t.getValue();
      if(!(isOperator(val))){
         return Double.parseDouble((String)t.getValue());
         
         
      }
      else{
         return computeTerm((String)t.getValue(), evaluateNode(t.getLeft()), evaluateNode(t.getRight()));
      }
   }
   
   private double computeTerm(String s, double a, double b)
   {
     if(s.equals("+")){
        return a +b;
     }
     else if(s.equals("-")){
        return a- b;}
     else if(s.equals("*")){
        return a * b;}
     else if (s.equals("/")){
        return (a /b); }
     else if (s.equals("%")){
        return a%b;}
     else{
         double fin = 1;
         for(int i = 0; i <a; i ++){
               fin *=b;
          }
          return fin;

     
     
     }
   }
   
   private boolean isOperator(String s)
   {
      
      if( "+ - * / % ^ !".contains(s)){
         return true;
      }      
      
      return false;
      
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
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
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
           
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());   //recurse left
      toReturn += t.getValue() + " ";              //process root

      toReturn += inorderTraverse(t.getRight());  //recurse right
      return toReturn;  
      	       			
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";              //process root
      toReturn += preorderTraverse(root.getLeft());   //recurse left
      toReturn += preorderTraverse(root.getRight());  //recurse right
      return toReturn;
   }
   
  /* extension */
   // public String inorderTraverseWithParentheses()
   // {
      // return inorderTraverseWithParentheses(root);
   // }
//    
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
      // return "";
   // }
}