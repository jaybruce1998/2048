   import java.awt.event.*;
   public class keyListener extends KeyAdapter
   {
      private int key = 0;
      public void keyPressed(KeyEvent k)
      {
         key=k.getKeyCode();
      }
      public void setCode(int c)
      {
         key=c;
      }
      public int getCode()
      {
         return key;
      }
   }