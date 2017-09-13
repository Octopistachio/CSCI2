import java.lang.reflect.Array;
import java.util.Scanner;

public class twentyfortyeight {

    //Create variables
    public static int gridWidth = 4; //Number of squares wide the grid will be
    public static int gridHeight = gridWidth; //Number of squares high the grid will be

    public static int[][] grid = new int[gridWidth][gridHeight]; //Create an array for the grid

    public static boolean lossConditionScreenFilled = false;

    public static void main(String[] args) {

        //At the start of the game
        placePiece(); //Place two numbers randomly
        placePiece();

        //Continues throughout the game
        while(!gameOver()) {
            switch(printBoard()){ //Print the board, and check what direction the user input

                case 'u': moveUp(); break;
                case 'U': moveUp(); break;
                case 'd': moveDown(); break;
                case 'D': moveDown(); break;
                case 'l': moveLeft(); break;
                case 'L': moveLeft(); break;
                case 'r': moveRight(); break;
                case 'R': moveRight(); break;

            }

            placePiece(); //Randomly place a new piece
        }

        System.exit(0); //When the while loop ends, terminate the program

    }

    public static void placePiece(){

        //Initialize variables
        int randomSquareX = (int)(Math.random()*gridWidth); //Pick a random spot on the grid, on the X coord
        int randomSquareY = (int)(Math.random()*gridHeight);  //Pick a random spot on the grid, on the Y coord
        int randomNumber; //Either a 2 or a 4, depending on randomNumberSelector
        int randomNumberSelector = (int)(Math.random()*10); //Pick a random number, 1-10
        // This will determine whether it appears as a 2 or a 4
        boolean gridIsFull = true;

        //There is a 10% chance that the number picked is a 4, and a 90% chance it's a 2
        if(randomNumberSelector == 9)
            randomNumber = 4;
        else
            randomNumber = 2;


        //Check to see if the grid is full
        for (int i = 0; i < gridWidth; i++)
            for (int j = 0; j < gridHeight; j++)
                if(grid[i][j] == 0) {
                    gridIsFull = false;
                    break;
                }

        //Check to see if the location selected is empty
        if(!gridIsFull){
            if(grid[randomSquareX][randomSquareY] != 0) { //If the location is not empty
                while (grid[randomSquareX][randomSquareY] != 0) { //Loop until an empty location is found
                    randomSquareX = (int) (Math.random() * gridWidth); //Pick a random spot on the grid, on the X coord
                    randomSquareY = (int) (Math.random() * gridHeight);  //Pick a random spot on the grid, on the Y coord
                }
            }

            //Place randomNumber into the grid
            grid[randomSquareX][randomSquareY] = randomNumber;
        }
        else {
            lossConditionScreenFilled = true;
        }



    }

    public static char printBoard() {

        //Loop through the 2D array and print out a 4 x 4 grid

        for (int i = 0; i < gridWidth; i++) {

                for (int j = 0; j < gridHeight; j++) {

                    if(grid[i][j] == 0)
                        System.out.print("|---|");
                    else
                        System.out.print("| " + grid[i][j] + " |");

            }

            System.out.println();

        }

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



        return userDirection;

    }


    public static void moveLeft() {

        //Move the numbers as far to the left as possible
        //Loop through the array
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {

                if (grid[i][j] != 0) { //If the location is not empty


                    for (int k = 0; k <= j; k++) { //Loop through all of the empty squares to the left of the current number


                        //If the current square isn't filled with a zero
                        if (grid[i][k] == 0) {

                            grid[i][k] = grid[i][j]; //fill the new square to the old number
                            grid[i][j] = 0; //fill the old square with a zero

                        }

                    }

                    if(j<gridHeight-1){ //If j is in any square but the ones on the right edge
                        if(grid[i][j] == grid[i][j+1]){ //And if the number matches the one to the right of it

                            grid[i][j] = grid[i][j] * 2; //Multiply the leftmost number by 2
                            grid[i][j+1] = 0; //And set the old number to 0

                        }
                    }

                }
            }
        }
    }

    public static void moveRight(){

        //Move the numbers as far to the right as possible
        //Loop through the array
        for (int i = 0; i < gridWidth; i++) {
            for (int j = 0; j < gridHeight; j++) {

                if (grid[i][j] != 0) { //If the location is not empty


                    for (int k = gridHeight-1; k > j; k--) { //Loop through all of the empty squares to the right of the current number

                        //If the current square isn't filled with a zero
                        if (grid[i][k] == 0) {

                            grid[i][k] = grid[i][j]; //fill the new square to the old number
                            grid[i][j] = 0; //fill the old square with a zero

                        }

                    }

                    if(j>0){ //If j is in any square but the ones on the left edge
                        if(grid[i][j] == grid[i][j-1]){ //And if the number matches the one to the left of it

                            grid[i][j] = grid[i][j] * 2; //Multiply the rightmost number by 2
                            grid[i][j-1] = 0; //And set the old number to 0

                        }
                    }

                }
            }
        }
    }



    public static void moveUp(){
        System.out.println("Grid moved up!");
    }

    public static void moveDown(){
        System.out.println("Grid moved down!");
    }

    public static boolean gameOver(){

        if(lossConditionScreenFilled){
            System.err.println("The grid filled up! Game over!"); //The player lost, because the screen is filled up
            return true;
        }
        else
            return false;
    }
}

