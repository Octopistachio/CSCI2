package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


/**
 * Find anagrams that match the word
 * the user input.
 *
 * @author Matthew Wilson
 */
public class Main {

    private static ArrayList<String> WordList = new ArrayList<>(); //The list of all words in dictionary.txt
    private static ArrayList<String> WordListSorted = new ArrayList<>(); //The list of all words in dictionary.txt, BUT each word is sorted alphabetically
    private static ArrayList<String> MatchingWords = new ArrayList<>(); //The list of all the words that are anagrams of the one the user input

    private static String userWord; //The word the user input

    public static void main(String[] args) {

        createLists(); //Load dictionary.txt into memory

        Scanner input = new Scanner(System.in); //Create the scanner object
        System.out.print("Enter a word: ");
        userWord = input.next(); //Set userWord to whatever the user inputs
        userWord = userWord.toLowerCase(); //Change userWord to lowercase to avoid errors

        String userWordSorted = sortWordAlphabetically(userWord); //Sort the word the user input alphabetically

        for( int i = 0; i < WordList.size(); i++){ //Loop through each word in dictionary.txt

            if(userWordSorted.matches(WordListSorted.get(i))) //If the sorted word from the dictionary.txt matches userWordSorted
                if(!userWord.matches(WordList.get(i))) //And if userWord is not the same word from dictionary.txt
                    MatchingWords.add(WordList.get(i)); //Add the word to the MatchingWords array list

        }

        printAnagrams(); //Print the results to the console

    }

    /**
     * Add all of the words into
     * the arrayLists of WordList and
     * WordListSorted.
     */
    private static void createLists(){

        try { //Try/catch to make sure the file path exists
            File file = new File("dictionary.txt"); //Create a file object with dictionary.txt
            FileReader fileReader = new FileReader(file); //Read from dictionary.txt
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line; //The current line that fileReader is on

            while ((line = bufferedReader.readLine()) != null) { //While the reader has not reached the end of dictionary.txt
                WordList.add(line); //Add the line to WordList
                WordListSorted.add(sortWordAlphabetically(line)); //Sort the word by character and add it to WordListSorted
            }
            fileReader.close(); //End the fileReader to avoid errors
        }
        catch (IOException e) { //Catch any IOExceptions
            e.printStackTrace(); //Print the error to the console
        }

    }

    /**
     * Sort the word by each character
     * alphabetically.
     *
     * @param word the word that needs to be sorted
     * @return the word that was received, but sorted by character, as a string
     */
    private static String sortWordAlphabetically(String word){

        char[] charArray = word.toCharArray(); //Send the string to an array of characters
        Arrays.sort(charArray); //Sort the array alphabetically
        return (new String(charArray)); //Return the array as a string

    }

    /**
     * Print the list of matching anagrams to the console.
     * This will appear differently depending on if there are
     * 0, 1, or at least 2 anagrams.
     */
    private static void printAnagrams(){

        if(MatchingWords.size() != 0){ //If the number of matching words is not zero
            if(MatchingWords.size() != 1){ //And if the number of matching words is not one
                System.out.print("The anagrams for \"" + userWord + "\" are "); //Print the start of a message
                for( int i = 0; i < MatchingWords.size(); i++) { //Loop through all of the words in MatchingWords
                    if(i != MatchingWords.size()-1) //If the word is not the last one in the list
                        System.out.print("\"" + MatchingWords.get(i) + ",\" "); //Print the word, and end in a comma and a space
                    else //If it is the last word in the list
                        System.out.print("and \"" + MatchingWords.get(i) + ".\""); //Print the word, and end in a period
                }
            }
            else //If there is only one matching word
                System.out.print("The anagram for \"" + userWord + "\" is \"" + MatchingWords.get(0) + ".\""); //Print the message, and the one matching word
        }
        else //If there are no matching words
            System.out.print("There are no anagrams for \"" + userWord + ".\""); //Inform the user

    }

}