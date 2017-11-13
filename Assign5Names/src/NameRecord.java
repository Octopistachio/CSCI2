/*
* This class takes the list of names
* from NameList and the list of each ranking
* and outputs it.
*
* @author Matthew Wilson
* @date 11/3/2017
*/

import java.io.*;
import java.util.Scanner;

public class NameRecord {

    private String name;
    private int[] rankingArray = new int[12]; //The list of all the rankings
    private NameList nameListObj = new NameList();

    /**
     * Constructor for NameRecord
     *
     * @param name The name input by the user
     */
    public NameRecord(String name) {

        this.name = name;
        initializeRankings(); //Put the rankings into an array
    }

    public NameRecord(Scanner reader) {

        System.out.print("Input a name: "); //Console instructions
        this.name = reader.nextLine(); //Record the name the user types
        initializeRankings(); //Put the rankings into an array
    }

    /**
     * Take the rankings from the text file
     * and put it into an array
     */
    private void initializeRankings() {

        try (LineNumberReader rdr = new LineNumberReader(new FileReader("names-data.txt"))) { //Try reading from the text file

            int nameIndex = Search(name); //Get the line the name is on

            for (String line; (line = rdr.readLine()) != null; ) { //For each line in the text file
                if (rdr.getLineNumber() == nameIndex) { //If that line is the one that has the entered name

                    char[] charArray = line.toCharArray(); //Change the string to an array of characters

                    int placement = 1; //What place the number is on (ones, tens, hundreds)
                    int ranking = 0; //What rank the number is
                    int decadeIndex = rankingArray.length - 1; //The index in the array
                    for (int i = charArray.length - 1; i >= 0; i--) { //For each character in the array (in reverse)
                        if (charArray[i] != ' ') { //If the character is not a space
                            ranking += Character.getNumericValue(charArray[i]) * placement; //Increase the ranking
                            placement *= 10; //Increase the placement tenfold
                        } else { //If the character is a space
                            rankingArray[decadeIndex] = ranking; //Add the ranking to the array
                            decadeIndex--; //Decrease the index
                            ranking = 0; //Reset the ranking
                            placement = 1; //Reset the placement
                        }

                    }

                }

            }
        } catch (IOException e) { //Catch any IOExceptions
            e.printStackTrace(); //Print the error to the console
        }
    }

    /**
     * Getter
     *
     * @return The name the user input, in all lowercase
     */
    String getName() {
        return name.toLowerCase();
    }

    /**
     * Search for a name, and return what line that name is on
     * in the text file. If it does not exist, throw an error.
     *
     * @param target The name that is being searched for
     * @return What line the name is on
     */
    int Search(String target) {

        int nameIndex = 1; //The line number the name is on

        try {
            for (String element : nameListObj.Names) { //For each name
                if (target.toLowerCase().matches(element.toLowerCase())) break; //If the input name matches one in the list, end the loop
                else nameIndex++; //Else keep looping
            }
            if (nameIndex >= nameListObj.Names.size()) throw new RuntimeException("This name is not on the rankings!"); //If the index is higher than the size of the array, the name was not found. Throw an error.
        }
        catch (RuntimeException e) {
            System.err.println(e.toString());
        }

        return nameIndex;
    }

    /**
     * Getter
     *
     * @param decade
     * @return The ranking of the entered decade
     */
    int getRank(int decade) {
        return rankingArray[decade]<=0?1000:rankingArray[decade];
    }
}