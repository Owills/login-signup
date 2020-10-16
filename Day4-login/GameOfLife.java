 import javax.swing.*;
import java.awt.*;

public class GameOfLife extends JPanel {
    int cellSize; // size of the cell in pixels
    int numberOfCells; // the width and height of the grid in cells. for us, numberOfCells will be 60.
    int numberOfNeighbors;
    boolean[][] currentGen, nextGen; // the two arrays that contain the status of each cell. True is alive, False is dead.
    
    // This is the CONSTRUCTOR. All the setup for the initial game state goes here.
    public GameOfLife(){
        cellSize = 10;
        numberOfCells = 600 / cellSize;
        currentGen = new boolean[numberOfCells][numberOfCells];
        nextGen = new boolean[numberOfCells][numberOfCells];
        
        // STEP A: Set up the currentGen grid with random cell values here
        
        for(int num = 0; num< currentGen.length;num ++){
            for(int nim = 0; nim <currentGen[num].length; nim++){
                if(Math.random()>0.5){
                    currentGen[num][nim] = true;
                }else{
                    currentGen[num][nim] = false;
                }    
            }    
        }   
        
        
        
    }
    
    // STEP B: This method takes the row and col coordinates of a cell as parameters, and
    // returns the number of living neighbors that cell has.
    private int getNeighbors(int row, int col){
        int living =0;
        for(int i = row-1; i <=row +1; i ++){
            for(int j = col-1; j <=col +1; j ++){
                try{
                    if(currentGen[i][j]){
                        living ++;
                        if(i == row && j == col){
                            living --;
                        }    
                    }    
                } catch(Exception e){
                }    
            }    
        }    
        return living;
    }
    
    private void gameLoop(){
        // Here is the game loop. Anything that happens every frame goes inside this "while" loop.
        while (true){
            
            // STEP C: Calculate the next generation of cells here
            for(int num = 0; num< nextGen.length;num ++){
                for(int nim = 0; nim <nextGen[num].length; nim++){
                    if(getNeighbors(num,nim) <= 1){
                        nextGen[num][nim] = false;
                    }else if (getNeighbors(num,nim) == 2){
                        nextGen[num][nim] = currentGen[num][nim];
                    }else if (getNeighbors(num,nim) == 3){
                        nextGen[num][nim] = true;
                    }else if (getNeighbors(num,nim) >= 4){
                        nextGen[num][nim] = false;
                    }     
                }    
            }        
            
            // STEP D: Advance to the next generation here
            currentGen = nextGen;
            nextGen = new boolean[currentGen.length][currentGen.length];
            
            
            
            
            // Do not remove anything below this line
            repaint();
            // Thread.sleep determines how many milliseconds between each frame
            // You can change that value if you want it to go faster or slower
            try {Thread.sleep(333);} catch(Exception e) {}
        } // end of while loop
    }
    
    // The paintComponent method is called every frame by repaint();
    // This is where all the code goes for drawing the cells.
    public void paintComponent(Graphics g) { 
        super.paintComponent(g);
        
        // Paint the lines of the grid
        g.setColor(Color.gray);
        for (int x = cellSize; x < 600; x = x + cellSize) {
          g.drawLine(x,0,x,600);
        }
        for (int y = cellSize; y < 600; y = y + cellSize){
          g.drawLine(0,y,600,y);
        }
        
        // Paint the cells
        g.setColor(Color.black);
        for (int row = 0; row < numberOfCells; row++){
          for (int col = 0; col < numberOfCells; col++){
            if (currentGen[row][col] == true){
              g.fillRect(col*cellSize + 1, row*cellSize + 1, cellSize-1, cellSize-1);
            }
          }
        }
    }
    
    // This main method creates the game window and starts the game loop
    public static void main() { 
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                JFrame gameFrame = new JFrame("Conway's Game of Life");
                final GameOfLife gamePanel = new GameOfLife();
                
                // Game window 600x600 plus 22 pixels for the title bar
                gameFrame.setSize(600, 622);
                gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameFrame.add(gamePanel);
                gameFrame.setVisible(true);
                
                Thread loop = new Thread() {
                    public void run() {
                        gamePanel.gameLoop();
                    }
                };
                loop.start();
            }
        });
    }
    
}