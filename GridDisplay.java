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
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;



public class GridDisplay extends JFrame {
  //default 
 	private GridLayout defaultLayout;
  private JPanel panel;
  int win=0;
  // String sound_track;
  // Music se = new Music();



  
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
					public void actionPerformed(ActionEvent event) {
						if (griD.isBombAtLocation(row, col)) {

              // playSound("bombsound.mp3");

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

// public void playSound(String soundName)
//  {
//    try 
//    {
//     AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile( ));
//     Clip clip = AudioSystem.getClip( );
//     clip.open(audioInputStream);
//     clip.start( );
//    }
//    catch(Exception ex)
//    {
//      System.out.println("Error with playing sound.");
//      ex.printStackTrace( );
//    }
//  }

  
  	public static void main(String[] args) {
		
		new GridDisplay();
	}
}