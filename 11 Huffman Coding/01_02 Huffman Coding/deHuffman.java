// Name:   Anmol Karan           Date:  5/14/23
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   
   /*  
    To be consistent with the test in CodePost, 
    all the non-leaf nodes must store empty strings ("") 
    and each leaf node must store the letter as a String ("M").
    
    Do not store them as NULL and char!
    */  
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode fin = new TreeNode("");
      while(huffLines.hasNextLine()){
         String curr = huffLines.nextLine();
         String cha = curr.substring(0,1);
         String code = curr.substring(1);
         TreeNode temp = fin;
         String[] splitted = code.split("");
         for(int i = 0; i < splitted.length; i ++){
            if (splitted[i].equals("0")){
               if(temp.getLeft()== null){
                   temp.setLeft(new TreeNode(""));
               }
               temp = temp.getLeft();
               
            }
            
            else{
               if(temp.getRight() == null){
                  temp.setRight(new TreeNode(""));
               }
               temp = temp.getRight();
            }
         }
         temp.setValue(cha);
      }
      return fin;
   }
   
   public static String dehuff(String text, TreeNode root)
   {
      String[] splitted = text.split("");
      String fin = "";
      
      TreeNode temp = root;

      for(int i = 0; i < splitted.length; i ++){

         if(splitted[i] .equals("0")){
            temp = temp.getLeft();
            
         }
         else if (splitted[i].equals("1")){
            temp = temp.getRight();
         }
         if(temp.getRight() == null && temp.getLeft() == null){
            fin+=temp.getValue();
            temp = root;
         }
         
         
      }
      return fin;
     
      
   
   }
}

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
