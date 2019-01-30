import javax.swing.*;
import java.awt.*;
public class PanelOf2048 extends JPanel
{
   ImageIcon[] icons;
   String location;
   int[][] board;
   boolean notFound;
   public PanelOf2048(int[][] b)
   {
      super();
      location=System.getProperty("user.dir")+"/";
      board=b;
      icons=new ImageIcon[12];
      for(int i=0; i<icons.length; i++)
         icons[i] = new ImageIcon(location+(int)(Math.pow(2, i))+".png");
   }
   public void paintComponent(Graphics g)
   {
      for(int r=0; r<4; r++)
         for(int c=0; c<4; c++)
         {
            notFound=true;
            for(int i=0; i<icons.length&&notFound; i++)
               if(board[c][r]==Math.pow(2, i))
               {
                  notFound=false;
                  g.drawImage(icons[i].getImage(), r*100, c*100, 100, 100, null);
               }
         }
   }
}