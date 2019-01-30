import java.util.ArrayList;
import javax.swing.*;
import java.awt.event.*;
public class TheGame2048
{
   static ArrayList<Integer> numbers=new ArrayList<Integer>();
   static ArrayList<Integer> highest=new ArrayList<Integer>();
   static int[][] board=new int[4][4];
   static int randomR, randomC, blankR, blankC, direction, option, bigN, bigR, bigC, otherR, otherC, temp;
   static boolean moved, validMove, didSomething;
   static String inputValue="";
   static JFrame frame = new JFrame();
   static keyListener kl=new keyListener();
   static PanelOf2048 p=new PanelOf2048(board);
   public TheGame2048()
   {
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.addKeyListener(kl);
      frame.add(p);
      frame.setSize(416, 438);
      frame.setFocusable(true);
   }
   public static void fillBoard()
   {
      for(int r=0; r<board.length; r++)
         for(int c=0; c<board[0].length; c++)
            board[r][c]=1;
      for(int i=0; i<2; i++)
         putRandomNumber();
   }
   public static void putRandomNumber()
   {
      randomR=(int)(Math.random()*board.length);
      randomC=(int)(Math.random()*board[0].length);
      while(board[randomR][randomC]>1)
      {
         randomR++;
         if(randomR>=board.length)
         {
            randomR=0;
            randomC++;
         }
         if(randomC>=board[0].length)
            randomC=0;
      }
      if(Math.random()<.5)
         board[randomR][randomC]=2;
      else
         board[randomR][randomC]=4;
   }
   public static void showBoard()
   {
      for(int r=0; r<board.length; r++)
      {
         System.out.println("---------------------");
         for(int c=0; c<board[0].length; c++)
         {
            System.out.print("|");
            if(board[r][c]<2)
               System.out.print("    ");
            else
            {
               for(int i=1024; i>board[r][c]; i/=10)
                  System.out.print(" ");
               System.out.print(board[r][c]);
            }
         }
         System.out.println("|");
      }
      System.out.println("---------------------");
      System.out.println();
   }
   public static boolean canMoveUp()
   {
      for(int r=1; r<board.length; r++)
         for(int c=0; c<board[0].length; c++)
            if(board[r][c]>1&&(board[r][c]==board[r-1][c]||board[r-1][c]<2))
               return true;
      return false;
   }
   public static void moveUp()
   {
      for(int r=1; r<board.length; r++)
         for(int c=0; c<board[0].length; c++)
            if(board[r][c]>1)
            {
               moved=false;
               blankR=4;
               for(int i=1; !moved&&r-i>=0; i++)
                  if(!(board[r-i][c]==board[r][c]||board[r-i][c]<2))
                     moved=true;
                  else
                     if(board[r-i][c]==board[r][c])
                     {
                        board[r-i][c]*=2;
                        board[r][c]=1;
                        moved=true;
                     }
                     else
                        blankR=r-i;
               if(blankR<4)
               {
                  board[blankR][c]=board[r][c];
                  board[r][c]=1;
               }
            }
   }
   public static boolean canMoveDown()
   {
      for(int r=board.length-1; r>0; r--)
         for(int c=0; c<board[0].length; c++)
            if(board[r-1][c]>1&&(board[r][c]==board[r-1][c]||board[r][c]<2))
               return true;
      return false;
   }
   public static void moveDown()
   {
      for(int r=board.length-2; r>=0; r--)
         for(int c=0; c<board[0].length; c++)
            if(board[r][c]>1)
            {
               moved=false;
               blankR=4;
               for(int i=1; !moved&&r+i<board.length; i++)
                  if(!(board[r+i][c]==board[r][c]||board[r+i][c]<2))
                     moved=true;
                  else
                     if(board[r+i][c]==board[r][c])
                     {
                        board[r+i][c]*=2;
                        board[r][c]=1;
                        moved=true;
                     }
                     else
                        blankR=r+i;
               if(blankR<4)
               {
                  board[blankR][c]=board[r][c];
                  board[r][c]=1;
               }
            }
   }
   public static boolean canMoveLeft()
   {
      for(int r=0; r<board.length; r++)
         for(int c=1; c<board[0].length; c++)
            if(board[r][c]>1&&(board[r][c]==board[r][c-1]||board[r][c-1]<2))
               return true;
      return false;
   }
   public static void moveLeft()
   {
      for(int r=0; r<board.length; r++)
         for(int c=1; c<board[0].length; c++)
            if(board[r][c]>1)
            {
               moved=false;
               blankC=4;
               for(int i=1; !moved&&c-i>=0; i++)
                  if(!(board[r][c-i]==board[r][c]||board[r][c-i]<2))
                     moved=true;
                  else
                     if(board[r][c-i]==board[r][c])
                     {
                        board[r][c-i]*=2;
                        board[r][c]=1;
                        moved=true;
                     }
                     else
                        blankC=c-i;
               if(blankC<4)
               {
                  board[r][blankC]=board[r][c];
                  board[r][c]=1;
               }
            }     
   }
   public static boolean canMoveRight()
   {
      for(int r=0; r<board.length; r++)
         for(int c=1; c<board[0].length; c++)
            if(board[r][c-1]>1&&(board[r][c]==board[r][c-1]||board[r][c]<2))
               return true;
      return false;
   }
   public static void moveRight()
   {
      for(int r=0; r<board.length; r++)
         for(int c=board[0].length-2; c>=0; c--)
            if(board[r][c]>1)
            {
               moved=false;
               blankC=4;
               for(int i=1; !moved&&c+i<board[0].length; i++)
                  if(!(board[r][c+i]==board[r][c]||board[r][c+i]<2))
                     moved=true;
                  else
                     if(board[r][c+i]==board[r][c])
                     {
                        board[r][c+i]*=2;
                        board[r][c]=1;
                        moved=true;
                     }
                     else
                        blankC=c+i;
               if(blankC<4)
               {
                  board[r][blankC]=board[r][c];
                  board[r][c]=1;
               }
            }  
   }
   public static boolean topIsSolid()
   {
      for(int c=1; c<board.length; c++)
         if(board[0][c]<2||board[0][c]==board[0][c-1])
            return false;
      return true;
   }
   public static boolean leftIsSolid()
   {
      for(int r=1; r<board.length; r++)
         if(board[r][0]<2||board[r][0]==board[r-1][0])
            return false;
      return true;
   }
   public static void AIMove()
   {
      if(canMoveUp())
         moveUp();
      else
         if(canMoveLeft())
            moveLeft();
         else
            if(canMoveRight()&&topIsSolid())
               moveRight();
            else
               if(canMoveDown())
                  moveDown();
               else
                  moveRight();
   }
   public static void doRandomMove()
   {
      didSomething=false;
      direction=(int)(Math.random()*4);
      while(!didSomething)
      {
         if(direction==0)
            if(canMoveUp())
            {
               moveUp();
               didSomething=true;
            }
            else
               direction=1;
         if(direction==1)
            if(canMoveLeft())
            {
               moveLeft();
               didSomething=true;
            }
            else
               direction=2;
         if(direction==2)
            if(canMoveRight())
            {
               moveRight();
               didSomething=true;
            }
            else
               direction=3;
         if(direction==3)
            if(canMoveDown())
            {
               moveDown();
               didSomething=true;
            }
            else
               direction=0;
      }
   }
   public static boolean won()
   {
      for(int r=0; r<board.length; r++)
         for(int c=0; c<board[0].length; c++)
            if(board[r][c]>1024)
               return true;
      return false;
   }
   public static boolean lost()
   {
      return !(canMoveUp()||canMoveDown()||canMoveLeft()||canMoveRight());
   }
   public static void playGame()
   {
   
      kl.setCode(0);
      while(!(won()||lost()))
      {
         p.repaint();
         validMove=false;
         while(!validMove)
         {
            getInput();
            if(kl.getCode()==KeyEvent.VK_UP||kl.getCode()==KeyEvent.VK_W||kl.getCode()==KeyEvent.VK_NUMPAD8)
            {
               if(canMoveUp())
               {
                  validMove=true;
                  moveUp();
               }
            }
            else
               if(kl.getCode()==KeyEvent.VK_DOWN||kl.getCode()==KeyEvent.VK_S||kl.getCode()==KeyEvent.VK_NUMPAD2)
               {
                  if(canMoveDown())
                  {
                     validMove=true;
                     moveDown();
                  }
               }
               else
                  if(kl.getCode()==KeyEvent.VK_LEFT||kl.getCode()==KeyEvent.VK_A||kl.getCode()==KeyEvent.VK_NUMPAD4)
                  {
                     if(canMoveLeft())
                     {
                        validMove=true;
                        moveLeft();
                     }
                  }
                  else
                     if(kl.getCode()==KeyEvent.VK_RIGHT||kl.getCode()==KeyEvent.VK_D||kl.getCode()==KeyEvent.VK_NUMPAD6)
                     {
                        if(canMoveRight())
                        {
                           validMove=true;
                           moveRight();
                        }
                     }
            kl.setCode(0);
         }
         putRandomNumber();
      }
      p.repaint();
      if(won())
         System.out.println("You got 2048!");
      else
         System.out.println("You lost!");
   }
   public static void AIGame()
   {
      while(!(won()||lost()))
      {
         AIMove();
         putRandomNumber();
         p.repaint();
      }
      if(won())
         System.out.println("The AI got 2048!");
      else
         System.out.println("The AI lost!");
   }
   public static void getInput()
   {
      while(kl.getCode()<1)
         p.repaint();
   }
   public static void main(String[] args)
   {
      new TheGame2048();
      while(!inputValue.equals("3"))
      {
         inputValue="";
         while(!(inputValue.contains("1")||inputValue.contains("2")||inputValue.equals("3")))
         {
            inputValue = JOptionPane.showInputDialog("What would you like to do?\n1) Play 2048.\n2) Watch the AI play 2048.\n3) Exit.");
            if(inputValue==null||inputValue.equals(""))  
               System.exit(0);  
         }
         fillBoard();
         if(inputValue.contains("1"))
         {
            frame.setVisible(true);
            playGame();
         }
         if(inputValue.contains("2"))
         {
            frame.setVisible(true);
            AIGame();
         }
      }
      System.exit(0);
   }
}