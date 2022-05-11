/* 
[Github link for project] https://github.com/mesuara/MineSweeperJava/blob/main/GridDisplay.java

[YouTube link]
https://youtu.be/mYUbK10HDsg

[GridDisplay class code]
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Insets;
import java.util.Scanner;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;


public class GridDisplay extends JFrame {
  //default 
 	private GridLayout defaultLayout;
  private JPanel panel;
  int win=0;


  
  public GridDisplay(){    
     Grid griD = new Grid();
        boolean[][] bombG = griD.getBombGrid();
    int[][] countG = griD.getCountGrid();
    //for cheating the system to test win feature
  for (int i = 0; i < griD.getNumRows(); i++) {
			for (int j = 0; j < griD.getNumColumns(); j++) {
        System.out.print(bombG[i][j] + " ");
      }
    System.out.println();
    }
    setSize(500,500);
    setResizable(false);
    setTitle("Amateur Minesweeper");
    defaultLayout = new GridLayout(griD.getNumRows(), griD.getNumColumns(),15,15 );
    panel = new JPanel();
    panel.setLayout(defaultLayout);

// make squares buttons
    
JButton[][] buttons =  new JButton[griD.getNumRows()][griD.getNumColumns()];
    // System.out.println(griD.getNumRows());
    	for (int i = 0; i < griD.getNumRows(); i++) {
			for (int j = 0; j < griD.getNumColumns(); j++) {
				buttons[i][j] = new JButton(""); 
        buttons[i][j].setMargin(new Insets(0, 0, 0, 0));
        buttons[i][j].setBackground(new Color(219, 204, 198));
        buttons[i][j].setOpaque(true);
        //buttons[i][j].setForeground(new Color(255, 124, 69));

        int row =i;
        int col =j;
				buttons[i][j].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						if (griD.isBombAtLocation(row, col)) {
       
							displayGL(griD, buttons);
              		int reply = JOptionPane.showConfirmDialog(null, "Would you like to play MineSweeper again?", "Bomb exploded", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			new GridDisplay();
		} else {
		    JOptionPane.showMessageDialog(null,"Goodbyeeeee.");  
		}
						}		else{
              int numB = griD.getCountAtLocation(row, col);
			        buttons[row][col].setText(String.valueOf(numB));

              buttons[row][col].setForeground(textCol(numB));
              win++;
              if(win == ((griD.getNumRows() * griD.getNumColumns()) -25)){
               int reply = JOptionPane.showConfirmDialog(null, "Would you like to play MineSweeper again?", "Youu won", JOptionPane.YES_NO_OPTION);
		if (reply == JOptionPane.YES_OPTION) {
			new GridDisplay();
		} else {
		    JOptionPane.showMessageDialog(null,"Goodbyeeeee.");  
		} 
              }
            }
					}
				});
        panel.add(buttons[i][j]);
			}
		}

    
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    add(panel);
    setVisible(true);
      
  }



  public void displayGL(Grid g, JButton[][] btn){
    					for (int i = 0; i < g.getNumRows(); i++) {
								for (int j = 0; j < g.getNumRows(); j++) {
                  
									if (g.isBombAtLocation(i, j)) {
                    btn[i][j].setIcon(new ImageIcon(getClass().getResource("bomb.png")));
                    btn[i][j].setMargin(new Insets(0, 0, 0, 0));
									} else {
                    int numB = g.getCountAtLocation(i,j);
								    btn[i][j].setText(String.valueOf(numB));
                    btn[i][j].setForeground(textCol(numB));
			              btn[i][j].setEnabled(false);
                    
									}
								}
                
							}
    
  }

    public Color textCol(int num){
    Color result = new Color(65, 33, 63);
    if(num == 1){
      result = new Color(65, 33, 63);
    }
    if(num == 2){
      result = new Color(29, 162, 151);
    }
    if(num > 2){
      result = new Color(255, 124, 69);
    }
    return result;
  }
  	public static void main(String[] args) {
		
		new GridDisplay();
	}
}
*/

import java.util.Random;   

public class Grid {
	private boolean[][] bombGrid;
	private int[][] countGrid;
	private int numRows;
	private int numColumns;
	private int numBombs;
	
	public Grid() {
		numRows = 10;
		numColumns = 10;
		numBombs = 25;
		bombGrid = new boolean[10][10];
		countGrid = new int[10][10];
		createBombGrid();
		createCountGrid();
		
	}
	
	public Grid (int rows, int columns) {
		numRows = rows;
		numColumns = columns;
		numBombs = 25;
		bombGrid = new boolean[numRows][numColumns];
		countGrid = new int[numRows][numColumns];
		createBombGrid();
		createCountGrid();
	}
	
	public Grid (int rows, int columns, int bombs) {
		numRows = rows;
		numColumns = columns;
		numBombs = bombs;
		bombGrid = new boolean[numRows][numColumns];
		countGrid = new int[numRows][numColumns];
		createBombGrid();
		createCountGrid();
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public int getNumColumns() {
		return numColumns;
	}
	
	public int getNumBombs() {
		return numBombs;
	}
	
	public boolean[][] getBombGrid(){
		boolean[][] bombResult = new boolean[numRows][numColumns];
		for (int i = 0; i<numRows; i++) {
			for (int k = 0; k<numColumns; k++) {
				bombResult[i][k] = bombGrid[i][k];
			}
		}
		return bombResult;
	}
	
	public int[][] getCountGrid(){
		int[][] gridResult = new int[numRows][numColumns];
		for (int i = 0; i<numRows; i++) {
			for (int k = 0; k<numColumns; k++) {
				gridResult[i][k] = countGrid[i][k];
			}
		}
		return gridResult;
	}
	
	public boolean isBombAtLocation(int row, int col) {
		
		return bombGrid[row][col];

	}
	
	public int getCountAtLocation(int row, int col) {
    int result =0;
    int leftCol = col - 1;
    int upRow = row -1;
    int rightCol = col+1;
    int downRow = row+1;
/*
EXAMPLE A
[F][F][F] -> [1][2][2]
[F][T][T] -> [2][3][2]
[T][F][F] -> [2][3][2]

eg. getCountAtLocation(0,2);
[F][F]([F]) --> check [0,2](x,y),[0,1](x,y-1),[1,1](x+1,y-1),[1,2](x+1,y)
                checks that throw the error and need to skip if 
                (x-1,y), (x-1, y-1) -) less than zero
[F][T][T] 
[T][F][F] 

*/    
    if (isBombAtLocation(row, col)) {
			result++;
		}

    if(upRow >= 0 && rightCol < numColumns && isBombAtLocation(row -1 , col +1)){
      result ++;
    }

    if(rightCol < numColumns && isBombAtLocation(row , col +1)){
      result++;
    }

    if(downRow < numRows && rightCol < numColumns && isBombAtLocation(row +1, col +1)){
      result++;
    }

    if (downRow < numRows && isBombAtLocation(row+1,col)) {
			result += 1;
		}
    if(upRow >= 0 && isBombAtLocation(row-1,col)){
      result++;
    }

    if(downRow < numRows && leftCol >= 0 && isBombAtLocation(row + 1,col - 1)){
      result++;
    }

    if(upRow >= 0 && leftCol >= 0 && isBombAtLocation(row-1,col-1)){
      result++;
    }
    if(leftCol >= 0 && isBombAtLocation(row, col-1)){
      result++;
    }

    //gotta check if it's the end of grid 
    //in that case can't use the loop, doing manual until further brainstorm 
    // loop below can go under 0 if the argument for row is passed as 0
    // for(int i =row-1; i < row+1;i++ ){
    //   for(int j = colum-1; j < colum +1; j++){
    //     if(bombGrid[i][j]){
    //       result++;
    //     }
    //   }
    // }

          
		
		return result;
	}
	
	private void createBombGrid() {
		for (int i = 0; i<numRows; i++) {
			for (int k = 0; k<numColumns; k++) {
				bombGrid[i][k] = false;
			}
		}
		for (int i = 0; i<numBombs; i++) {
			
	    Random random = new Random(); 
      int randNumR = random.nextInt(numRows);
      int randNumC = random.nextInt(numColumns);
			      // System.out.println(randNumC);
      //       System.out.println(randNumR);
			if (bombGrid[randNumR][randNumC]) {
				i--;
			}
			else {
				bombGrid[randNumR][randNumC] = true;
			}
		}
		
	}
	
	private void createCountGrid() {
		for (int i = 0; i<numRows; i++) {
			for (int k = 0; k<numColumns; k++) {
				countGrid[i][k] = getCountAtLocation(i, k);
			}
		}
	}
	
}