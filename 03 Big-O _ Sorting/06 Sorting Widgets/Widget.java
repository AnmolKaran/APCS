// Name:
// Date: 

public class Widget implements Comparable<Widget>
{
   //fields
   private int cubits;
   private int hands;
   
   //constructors
   Widget(){
      cubits = 0;
      hands = 0;
   }
    Widget(int c, int h){
      cubits = c;
      hands = h;
   }
   Widget(Widget copy){
      cubits = copy.cubits;
      hands = copy.hands;
   }
   //accessors and modifiers
   public int getCubits(){
      return cubits;
   }
   public int getHands(){
      return hands;
   }
   public void setCubits(int c){
      cubits = c;
   }
   public void setHands(int h){
      hands =h;
   }
   
   
   //compareTo and equals
   public int compareTo(Widget b){
      if (cubits >b.cubits && hands >b.hands){
         return 1;
      }else if(cubits == b.cubits && hands== b.hands){
         return 0;
      }
      else{
         return -1;
      }
      
   }
   public boolean equals(Widget b){
      if(compareTo(b) == 0){
         return true;
      }
      return false;
   }
   //toString
   public String toString(){
      return (cubits + " cubits " + hands + " hands");
   }
}
