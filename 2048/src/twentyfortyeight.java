import java.lang.reflect.Array;

public class twentyfortyeight {

    public static void main(String[] args) {

        createGrid();

    }

    //Generate Grid

    public static void createGrid() {

        //Create variables
        int gridWidth = 4; //Number of squares wide the grid will be
        int gridHeight = gridWidth; //Number of squares high the grid will be

        int[][] grid = new int[gridWidth][gridHeight]; //Create an array for the grid

        //Loop through the 2D array and print out a 4 x 4 grid

        for (int i = 0; i < gridWidth; i++) {

                for (int j = 0; j < gridHeight; j++) {

                System.out.print("[ ]");

            }

            System.out.println();

        }

    }
}
