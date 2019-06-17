import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * 
 * @author leole
 */
public class TicTacToe{

    /************************************/
    /* Main Runner Method: Do not edit. */
    public static void main(String[]args){

        // scanner for getting input
        Scanner sc = new Scanner(System.in);

        // create vars to store player input
        int row;
        int col;

        // create a new instance of a TicTacToe game
        TicTacToe game = new TicTacToe();

        // create a new blank game grid
        char[][] grid = game.initializeGrid();

        // set the game state to 'c' for 'continue'
        char gameState = 'c';

        // declare a boolean to keep track of who's turn it is
        boolean playerToggle = true;

        // main game loop
        while(true){
            // print out the current game board
            game.printGrid(grid);

            // check to see if the game is over (won or drawn)
            gameState = game.checkGameState(grid);
            if(gameState == 'w'){
                if(playerToggle){
                    System.out.println("Game over! X won!");
                }else{
                    System.out.println("Game over! O won!");
                }
                break;
            }else if(gameState == 'd'){
                System.out.println("Game over! It's a draw!");
                break;
            }
            // get the current player's desired move location (row and col)
            row = game.getRow(sc);
            col = game.getCol(sc);

            // flip to other player's turn
            playerToggle = !playerToggle;

            // try to place a token
            char[][] newGrid;
            if(playerToggle){
                newGrid = game.placeToken(grid, 'X', row, col);
            }else{
                newGrid = game.placeToken(grid, 'O', row, col);
            }
            // compare this new grid to the old grid to see if the move was legal
            if(game.sameValues(grid, newGrid)){
                System.out.println("Illegal move! Try again.");
                playerToggle = !playerToggle;
            }else{
                // if it was legal, set the current board to the new one
                for(int i=0 ; i<grid.length ; i++){
                    for(int j=0 ; j<grid.length ; j++){
                        grid[i][j] = newGrid[i][j];
                    }
                }
            }
        }
        sc.close();
    }
    /************************************/

    public int getRow(Scanner sc){
        /* Your Code Here */
        boolean isValidValue = false;
        int row = 0;
        
        do{
            try{

                row = sc.nextInt();
                
                isValidValue = true;
                
                if(row == 1){
                    ++row;
                }
                else if(row == 2){
                    row += row;
                }
            }
            catch(InputMismatchException ex){
                System.out.println(ex.toString());
                sc.nextLine();
                
            }

        }while(!isValidValue);
        
        
        return row;
    }

    public int getCol(Scanner sc){
        /* Your Code Here */
        
        boolean isValidValue = false;
        int col = 0;
        
        do{
            try{
                
                col = sc.nextInt();
                
                isValidValue = true;
                
                if(col == 1){
                    ++col;
                }
                else if(col == 2){
                    col += col;
                }
            }
            catch(InputMismatchException ex){
                System.out.println(ex.toString());
                sc.nextLine();
            }

        }while(!isValidValue);
        
        return col;
        
    }

    public char checkGameState(char[][] grid){
        /* Your Code Here */
        char gameResult = 'd';
        boolean isLeftDiagonalWin;
        boolean isRightDiagonalWin;
        
        boolean isTopRowWin;
        boolean isMidRowWin;
        boolean isLowRowWin;
        
        boolean isLeftColWin;
        boolean isMidColWin;
        boolean isRightColWin;
                       
        isLeftDiagonalWin = (grid[0][0] != ' ') && grid[0][0] == grid[2][2] &&
                            grid[2][2] == grid[4][4] &&
                            grid[4][4] == grid[0][0];
        
        isRightDiagonalWin =(grid[0][4] != ' ') && grid[0][4] == grid[2][2] &&
                            grid[2][2] == grid[4][0] &&
                            grid[0][4] == grid[4][0];
        
        isTopRowWin = (grid[0][0] != ' ') && grid[0][0] == grid[0][2] &&
                      grid[0][2] == grid[0][4] &&
                      grid[0][0] == grid[0][4];
        
        isMidRowWin = (grid[2][0] != ' ') && grid[2][0] == grid[2][2] &&
                      grid[2][2] == grid[2][4] &&
                      grid[2][0] == grid[2][4];
        
        isLowRowWin = (grid[4][0] != ' ') && grid[4][0] == grid[4][2] &&
                      grid[4][2] == grid[4][4] &&
                      grid[4][0] == grid[4][4];
        
        isLeftColWin = (grid[0][0] != ' ') && grid[0][0] == grid[2][0] &&
                      grid[2][0] == grid[4][0] &&
                      grid[0][0] == grid[4][0];
        
        isMidColWin =  (grid[0][2] != ' ') && grid[0][2] == grid[2][2] &&
                      grid[2][2] == grid[4][2] &&
                      grid[0][2] == grid[4][2];
        
        isRightColWin =  (grid[0][4] != ' ') && grid[0][4] == grid[2][4] &&
                      grid[2][4] == grid[4][4] &&
                      grid[0][4] == grid[4][4];

        if(isTopRowWin || isMidRowWin || isLowRowWin ||
           isLeftColWin || isMidColWin || isRightColWin ||
           isLeftDiagonalWin || isRightDiagonalWin){
            gameResult = 'w';
        }
        else{
            
            spaceCheck:for(int row = 0; row < grid.length; ++row){
                for(int col = 0; col < grid[row].length; ++col){
                    if(grid[row][col] == ' '){
                        gameResult = 'c';
                        break spaceCheck;
                    }                                                 
                }                        
            }                        
        }
        
        return gameResult;
    }

    public char[][] initializeGrid(){
        /* Your Code Here */
        char[][] blankBoard = new char[5][5];
        
        for(int row = 0; row < blankBoard.length; ++row){
            
            if(row % 2 == 0){
                for(int col = 0; col < blankBoard[row].length; ++col){
                    if(col % 2 == 0){
                        blankBoard[row][col] = ' ';
                    }
                    else{
                        blankBoard[row][col] = '|';
                    }                
                }
            }
            else{
                for(int col = 0; col < blankBoard[row].length; ++col){
                    blankBoard[row][col] = '-';
                }
            }
        }
        return blankBoard;
    }

    public boolean sameValues(char[][] gridA, char[][] gridB){
        /* Your Code Here */
        boolean isSameArraysResult = true;
        
        resultFound:for(int  x = 0; x < gridA.length; ++x){
            for(int y = 0; y < gridA[x].length; ++y){
                if(gridA[x][y] == gridB[x][y]){
                    isSameArraysResult = false;
                    break resultFound;
                }
            }
        }
        return isSameArraysResult;
    }

    public char[][] placeToken(char[][] grid, char token, int row, int col){
        /* Your Code Here */
        
        char[][] newGrid = new char[grid.length][grid[0].length];
                
        try{
            if(grid[row][col] == ' ' && grid[row][col] != 'X' && grid[row][col] != 'O'){
                grid[row][col] = token;
                for(int x = 0; x < grid.length; ++x){
                    for(int y = 0; y < grid[x].length; ++y){
                        newGrid[x][y] = grid[x][y];
                    }
                }
            }
            else{
                newGrid = grid;
            }
            
        }
        catch(ArrayIndexOutOfBoundsException ex){
            System.out.println(ex.toString());
        }
        
        return newGrid;
    }

    public void printGrid(char[][] grid){
        /* Your Code Here */
        
        for(int row = 0; row < grid.length; ++row){
            for(int col = 0; col < grid[row].length; ++col){
                System.out.print(grid[row][col]);
            }
            System.out.println();
        }
    }
}
