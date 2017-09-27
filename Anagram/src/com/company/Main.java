package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static ArrayList<String> WordList = new ArrayList<>();
    private static ArrayList<String> WordListSorted = new ArrayList<>();
    private static ArrayList<String> MatchingWords = new ArrayList<>();

    private static String userWord;

    public static void main(String[] args) {

        createLists();

        Scanner input = new Scanner(System.in);
        System.out.print("Enter a word: ");
        userWord = input.next();

        String userWordSorted = sortWordAlphabetically(userWord);

        for( int i = 0; i < WordList.size(); i++){

            if(userWordSorted.matches(WordListSorted.get(i)))
                if(!userWord.matches(WordList.get(i)))
                    MatchingWords.add(WordList.get(i));

        }

        printAnagrams();

    }

    private static void createLists(){

        try {
            File file = new File("dictionary.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                WordList.add(line);
                WordListSorted.add(sortWordAlphabetically(line));
            }
            fileReader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static String sortWordAlphabetically(String word){

        char[] charArray = word.toCharArray();
        Arrays.sort(charArray);
        return (new String(charArray));

    }

    private static void printAnagrams(){

        if(MatchingWords.size() != 0){
            if(MatchingWords.size() != 1){
                System.out.print("The anagrams for \"" + userWord + "\" are ");
                for( int i = 0; i < MatchingWords.size(); i++) {
                    if(i != MatchingWords.size()-1)
                        System.out.print("\"" + MatchingWords.get(i) + ",\" ");
                    else
                        System.out.print("and \"" + MatchingWords.get(i) + ".\"");
                }
            }
            else
                System.out.print("The anagram for \"" + userWord + "\" is \"" + MatchingWords.get(0) + ".\"");
        }
        else
            System.out.print("There are no anagrams for \"" + userWord + ".\"");

    }

}