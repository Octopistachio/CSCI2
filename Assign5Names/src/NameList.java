import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class NameList {

    public ArrayList<String> Names = new ArrayList<>(); //The list of all names

    public NameList() {

        try { //Try/catch to make sure the file path exists

            File file = new File("names-data.txt"); //Create a file object
            FileReader fileReader = new FileReader(file); //Read from the file
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line; //The current line that fileReader is on

            while ((line = bufferedReader.readLine()) != null) { //While the reader has not reached the end of the file

                char[] charArray = line.toCharArray(); //Change the string to an array of characters
                StringBuilder str = new StringBuilder(); //Create a string builder

                for (char element : charArray) { //For each element in the array
                    if (element == ' ') break; //If the current char is a space, end the loop
                    else str.append(element); //If the current char is not a space, add it to the string
                }
                Names.add(str.toString()); //Add the string to Names
            }
            fileReader.close(); //End the fileReader to avoid errors

        } catch (IOException e) { //Catch any IOExceptions
            e.printStackTrace(); //Print the error to the console
        }

    }

}
