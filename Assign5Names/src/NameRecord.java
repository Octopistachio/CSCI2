import java.io.*;
import java.util.Scanner;

public class NameRecord {

    private String name;
    private int[] rankingArray = new int[12]; //The list of all the rankings
    private NameList nameListObj = new NameList();

    //Constructor
    public NameRecord(Scanner reader) {

        System.out.print("Input a name: ");
        name = reader.nextLine();
        initializeRankings();
    }

    private void initializeRankings() {
        /* Ratings */
        try (LineNumberReader rdr = new LineNumberReader(new FileReader("names-data.txt"))) {

            int nameIndex = Search(name);

            for (String line; (line = rdr.readLine()) != null; ) {
                if (rdr.getLineNumber() == nameIndex) {

                    char[] charArray = line.toCharArray(); //Change the string to an array of characters

                    int placement = 1;
                    int ranking = 0;
                    int decadeIndex = rankingArray.length - 1;
                    for (int i = charArray.length - 1; i >= 0; i--) {

                        if (charArray[i] != ' ') {

                            ranking += Character.getNumericValue(charArray[i]) * placement;
                            placement *= 10;

                        } else {
                            rankingArray[decadeIndex] = ranking;
                            decadeIndex--;
                            ranking = 0;
                            placement = 1;
                        }

                    }
                    for (int i = 0; i < rankingArray.length; i++)
                        if(rankingArray[i] == 0)
                            rankingArray[i] = 1000;
                }

            }
        } catch (IOException e) { //Catch any IOExceptions
            e.printStackTrace(); //Print the error to the console
        }
    }

    String getName() {
        return name.toLowerCase();
    }

    /**
     *
     * @param target The name that is being searched for
     * @return What line the name is on
     */
    int Search(String target) {

        int nameIndex = 1; //The line number the name is on

        try {
            for (String element : nameListObj.Names) { //For each name
                if (target.toLowerCase().matches(element.toLowerCase())) //If the input name matches one in the list
                    break;
                nameIndex++;
            }
            if (nameIndex >= nameListObj.Names.size()) throw new RuntimeException("This name is not on the rankings!");
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }

        return nameIndex;
    }

    int getRank(int decade) {
        return rankingArray[decade];
    }
}