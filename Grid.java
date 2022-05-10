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