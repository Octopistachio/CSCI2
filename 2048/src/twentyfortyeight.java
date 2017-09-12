import java.lang.reflect.Array;
import java.util.Scanner;

public class twentyfortyeight {

    //Create variables
    public static int gridWidth = 4; //Number of squares wide the grid will be
    public static int gridHeight = gridWidth; //Number of squares high the grid will be

    public static int[][] grid = new int[gridWidth][gridHeight]; //Create an array for the grid

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
        else
            System.err.println("Did not print a new number to the grid, as it is full!"); //INFO FOR DEBUGGING



    }

    public static char printBoard() {

        //Loop through the 2D array and print out a 4 x 4 grid

        for (int i = 0; i < gridWidth; i++) {

                for (int j = 0; j < gridHeight; j++) {

                    if(grid[i][j] == 0)
                        System.out.print("[---]");
                    else
                        System.out.print("[ " + grid[i][j] + " ]");

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


    public static void moveLeft(){
        System.out.println("Grid moved left!");

        //Loop through the array
        for (int i = 0; i < gridWidth; i++)
            for (int j = 0; j < gridHeight; j++)
                if(grid[i][j] != 0) { //If the location is not empty
                    if(grid[i][0] != grid[i][j]){ //If the number is not already all the way to the left

                        grid[i][0] = grid[i][j]; //Set the leftmost location to the new number
                        grid[i][j] = 0; //And set the old location to 0
                    }

                }


    }

    public static void moveRight(){
        System.out.println("Grid moved right!");
    }

    public static void moveUp(){
        System.out.println("Grid moved up!");
    }

    public static void moveDown(){
        System.out.println("Grid moved down!");
    }

    public static boolean gameOver(){
        return false;
    }
}

