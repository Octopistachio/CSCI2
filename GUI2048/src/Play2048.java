import java.util.Scanner;

/**
 * The TwentyFortyEight class is a game
 * about combining numbers together.
 */

public class Play2048 {

    private static int GRID_SIZE = 4; //Number of squares high the grid will be

    private static int[][] grid = new int[GRID_SIZE][GRID_SIZE]; //Create an array for the grid
    private static int[][] previousGrid = new int[GRID_SIZE][GRID_SIZE]; //A grid containing the values of the squares before a move was taken

    private static boolean isDoneCombining = false;

    private static int highestNumber = 2;
    private static int currentScore = 0;

    private static boolean lossConditionScreenFilled = false;
    private static boolean winConditionTwentyFortyEightReached = false;

    public static void main(String[] args) {

        //At the start of the game
        placePiece(); //Place two numbers randomly
        placePiece();

        //Continues throughout the game
        while(!gameOver()) {
            MakeArraysEqual(grid, previousGrid);
            printBoard();
            switch(userInput()){ //Print the board, and check what direction the user input

                case 'u': moveUp(); break;
                case 'U': moveUp(); break;
                case 'd': moveDown(); break;
                case 'D': moveDown(); break;
                case 'l': moveLeft(); break;
                case 'L': moveLeft(); break;
                case 'r': moveRight(); break;
                case 'R': moveRight(); break;

            }

            if(!DoArraysEqual(grid, previousGrid))
                placePiece(); //Randomly place a new piece
        }

        System.exit(0); //When the while loop ends, terminate the program

    }

    /**
     * Pick a random location in the array
     * and place either a 2 or a 4 there
     */
    static void placePiece(){

        //Initialize variables
        int randomSquareX = (int)(Math.random()*GRID_SIZE); //Pick a random spot on the grid, on the X coord
        int randomSquareY = (int)(Math.random()*GRID_SIZE);  //Pick a random spot on the grid, on the Y coord
        int randomNumber; //Either a 2 or a 4, depending on randomNumberSelector
        int randomNumberSelector = (int)(Math.random()*10); //Pick a random number, 1-10
        // This will determine whether it appears as a 2 or a 4
        boolean gridIsFull = true;

        //There is a 10% chance that the number picked is a 4, and a 90% chance it's a 2
        if(randomNumberSelector == 9)
            randomNumber = 4;
        else
            randomNumber = 2;

        if (randomNumber > highestNumber)
            highestNumber = randomNumber;

        //Check to see if the grid is full
        for (int i = 0; i < GRID_SIZE; i++)
            for (int j = 0; j < GRID_SIZE; j++)
                if(grid[i][j] == 0) {
                    gridIsFull = false;
                    break;
                }

        //Check to see if the location selected is empty
        if(!gridIsFull){
            if(grid[randomSquareX][randomSquareY] != 0) { //If the location is not empty
                while (grid[randomSquareX][randomSquareY] != 0) { //Loop until an empty location is found
                    randomSquareX = (int) (Math.random() * GRID_SIZE); //Pick a random spot on the grid, on the X coord
                    randomSquareY = (int) (Math.random() * GRID_SIZE);  //Pick a random spot on the grid, on the Y coord
                }
            }

            //Place randomNumber into the grid
            grid[randomSquareX][randomSquareY] = randomNumber;
        }
        else {
            lossConditionScreenFilled = true;
        }

    }

    /**
     *  Print out a grid to the console.
     */
    private static void printBoard() {

        for (int i = 0; i < GRID_SIZE; i++)
            for (int j = 0; j < GRID_SIZE; j++)
                if (grid[i][j] > highestNumber)
                    highestNumber = grid[i][j];

        System.out.println("Highest Number: " + highestNumber + " Current Score: " + currentScore);

        //Loop through the 2D array and print out a 4 x 4 grid
        for (int i = 0; i < GRID_SIZE; i++) {

            for (int j = 0; j < GRID_SIZE; j++) {

                if (grid[i][j] > highestNumber)
                    highestNumber = grid[i][j];

                if (grid[i][j] == 0)
                    System.out.print("|---|");
                else
                    System.out.print("| " + grid[i][j] + " |");
            }

            System.out.println();

        }


    }

    /**
     *  Accept the direction the user wants to
     *  move, and return it to the main.
     *
     *  @return     the input the user types in
     */
    private static char userInput() {
        //Have the user pick a direction to move
        Scanner reader = new Scanner(System.in);  //Reading from System.in
        System.out.print("Enter a direction (u,d,l,r): ");
        char userDirection = reader.next().charAt(0); //Scans the next token of the input as a char.

        //Check to see if the user typed in an acceptable direction
        while(userDirection != 'u' && userDirection != 'd' && userDirection != 'l' && userDirection != 'r' &&
                userDirection != 'U' && userDirection != 'D' && userDirection != 'L' && userDirection != 'R')
        {
            //If a proper direction isn't input, have them try inputting it again
            System.out.print("Enter a direction (u,d,l,r): ");
            userDirection = reader.next().charAt(0); //Scans the next token of the input as a char.
        }

        System.out.println();
        System.out.println();
        return userDirection;
    }


    /**
     *  Move all of the numbers to the
     *  left, and combine any numbers that
     *  match
     */


    static void moveLeft(){

        for (int i = 0; i < GRID_SIZE; i++){

            //Move tiles
            for(int j = GRID_SIZE - 1; j > 0; j--){

                    if(grid[i][j-1] == 0) {

                        grid[i][j - 1] = grid[i][j];
                        grid[i][j] = 0;

                    }

            }

            //Merge tiles
            for(int j = 0; j < GRID_SIZE-1; j++) {

                if(grid[i][j+1] == grid[i][j]) {
                    grid[i][j] *= 2;
                    grid[i][j+1] = 0;
                    currentScore += grid[i][j];
                }

            }

            //Move tiles again
            for(int j = GRID_SIZE - 1; j > 0; j--) {

                if(grid[i][j-1] == 0) {

                    grid[i][j - 1] = grid[i][j];
                    grid[i][j] = 0;

                }

            }

        }

    }

    /**
     *  Move all of the numbers to the
     *  right, and combine any numbers that
     *  match
     */
    static void moveRight(){

        for (int i = 0; i < GRID_SIZE; i++){

            //Move tiles
            for(int j = 0; j < GRID_SIZE - 1; j++){

                if(grid[i][j+1] == 0) {

                    grid[i][j + 1] = grid[i][j];
                    grid[i][j] = 0;

                }

            }

            //Merge tiles
            for(int j = GRID_SIZE - 1; j > 0; j--) {

                if(grid[i][j-1] == grid[i][j]) {
                    grid[i][j] *= 2;
                    grid[i][j-1] = 0;
                    currentScore += grid[i][j];
                }

            }

            //Move tiles again
            for(int j = 0; j < GRID_SIZE - 1; j++){

                if(grid[i][j+1] == 0) {

                    grid[i][j + 1] = grid[i][j];
                    grid[i][j] = 0;

                }

            }

        }
    }


    /**
     *  Move all of the numbers up,
     *  and combine any numbers that
     *  match
     */
    static void moveUp(){

        for (int j = 0; j < GRID_SIZE; j++){

            //Move tiles
            for(int i = GRID_SIZE - 1; i > 0; i--){

                if(grid[i-1][j] == 0) {

                    grid[i-1][j] = grid[i][j];
                    grid[i][j] = 0;

                }

            }

            //Merge tiles
            for(int i = 0; i < GRID_SIZE - 1; i++) {

                if(grid[i+1][j] == grid[i][j]) {
                    grid[i][j] *= 2;
                    grid[i+1][j] = 0;
                    currentScore += grid[i][j];
                }

            }

            //Move tiles again
            for(int i = GRID_SIZE - 1; i > 0; i--){

                if(grid[i-1][j] == 0) {

                    grid[i-1][j] = grid[i][j];
                    grid[i][j] = 0;

                }

            }

        }
    }

    /**
     *  Move all of the numbers down,
     *  and combine any numbers that
     *  match
     */
    static void moveDown(){

        for (int j = 0; j < GRID_SIZE; j++){

            //Move tiles
            for(int i = 0; i < GRID_SIZE - 1; i++){

                if(grid[i+1][j] == 0) {

                    grid[i+1][j] = grid[i][j];
                    grid[i][j] = 0;

                }

            }

            //Merge tiles
            for(int i = GRID_SIZE - 1; i > 0; i--) {

                if(grid[i-1][j] == grid[i][j]) {
                    grid[i][j] *= 2;
                    grid[i-1][j] = 0;
                    currentScore += grid[i][j];
                }

            }

            //Move tiles again
            for(int i = 0; i < GRID_SIZE - 1; i++){

                if(grid[i+1][j] == 0) {

                    grid[i+1][j] = grid[i][j];
                    grid[i][j] = 0;

                }

            }

        }
    }

    /**
     *  @return     true or false, depending on if the game has ended or not
     */
    static boolean gameOver(){

        if(lossConditionScreenFilled){
            System.err.println("The grid filled up! Game over!"); //The player lost, because the screen is filled up
            System.err.println("Your score was " + currentScore + "!"); //The player lost, because the screen is filled up
            return true;
        }
        else if(winConditionTwentyFortyEightReached){
            System.err.println("You reached 2048! You won!"); //The player won, because the player reached 2048

            //Ask the user if they wish to continue playing
            Scanner reader = new Scanner(System.in);  //Reading from System.in
            System.out.print("Continue (y, n): ");
            char userContinueValue = reader.next().charAt(0); //Scans the next token of the input as a char.

            //Check to see if the user typed in an acceptable value
            while(userContinueValue != 'y' && userContinueValue != 'n' && userContinueValue != 'Y' && userContinueValue != 'N')
            {
                //If a proper value isn't input, have them try inputting it again
                System.out.print("Continue (y, n): ");
                userContinueValue = reader.next().charAt(0); //Scans the next token of the input as a char.
            }

            if(userContinueValue == 'y' || userContinueValue == 'Y') {
                winConditionTwentyFortyEightReached = false;
                return false;
            }
            else {
                System.out.println("\nThanks for playing!");
                return true;
            }
        }

        return false;
    }

    static boolean DoArraysEqual(int arrayOne[][], int arrayTwo[][]) {

        boolean equal = true;

        if(arrayOne.length == arrayTwo.length)
            for (int i = 0; i < arrayOne.length; i++)
                for(int j = 0; j < arrayOne.length; j++)
                    if(arrayOne[i][j] != arrayTwo[i][j])
                        equal = false;

        return equal;

    }

    static void MakeArraysEqual(int arrayOne[][], int arrayTwo[][]) {

        if(arrayOne.length == arrayTwo.length)
            for (int i = 0; i < arrayOne.length; i++)
                for(int j = 0; j < arrayOne.length; j++)
                    arrayTwo[i][j] = arrayOne[i][j];

    }

    static int getGridSize() {
        return GRID_SIZE;
    }

    static int getHighestNumber() {
        return highestNumber;
    }

    static void setHighestNumber(int highestNumber) {
        Play2048.highestNumber = highestNumber;
    }

    static int getCurrentScore() {
        return currentScore;
    }

    static int[][] getGrid() {
        return grid;
    }

    static int getGrid(int i, int j) {
        return grid[i][j];
    }

    static int[][] getPreviousGrid() { return previousGrid; }
}

