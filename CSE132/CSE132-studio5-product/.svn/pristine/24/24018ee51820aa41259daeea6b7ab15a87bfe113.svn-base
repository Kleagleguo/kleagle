package lecture;
import studio5.RWLock;

public class MyColor {

   private int red, green, blue;

   public MyColor(int r, int g, int b) {
      setColor(r, g, b);
   }
   
   public void setColor(int r, int g, int b) {
      red   = r;  
      green = g; 
      blue  = b;
   }

   public int getIntensity() {
       return (red+green+blue)/3;
   }

   /**
    * What kind of locks do we need here
    * so .equals works correctly?
    */
   public boolean equals(Object o) {
      MyColor other = (MyColor)o;


      return false;
   }

   /**
    * What kind of locks do we need here
    * so .hashCode works correctly?
    */
   public int hashCode() {


	   return 0;


   }
}