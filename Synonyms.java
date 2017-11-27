/*
 * Takes four words and determines
 * how synonymous they are with one
 * word
 *
 * @author Matthew Wilson
 * @date 11/20/17
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Synonyms {

    private HashMap<String, HashMap<String, Integer>> descriptors = new HashMap<>();

    public static void main(String[] args) {

        URL[] corpus = new URL[0];

        try {
            corpus = new URL[]{

                    // pride and prejudice
                    new URL("https://www.gutenberg.org/files/1342/1342-0.txt"),
                    // the adventures of sherlock holmes
                    new URL("http://www.gutenberg.org/cache/epub/1661/pg1661.txt"),
                    // a tale of two cities
                    new URL("https://www.gutenberg.org/files/98/98-0.txt"),
                    // alice's adventures in wonderland
                    new URL("https://www.gutenberg.org/files/11/11-0.txt"),
                    // moby dick
                    new URL("https://www.gutenberg.org/files/2701/2701-0.txt"),
                    // war and peace
                    new URL("https://www.gutenberg.org/files/2600/2600-0.txt"),
                    // the importance of being Earnest
                    new URL("http://www.gutenberg.org/cache/epub/844/pg844.txt")

            };
        }
        catch (MalformedURLException e) { //Catch any errors
            e.printStackTrace();
        }

        Synonyms mySyn = new Synonyms(corpus); //Create the synonym object
        Scanner scanner = new Scanner(System.in); //Create a scanner

        System.out.print("Enter a word: ");
        String wordToCheck = scanner.next(); //What word to check against
        String[] synonyms = new String[4]; //An array of each potential synonym

        System.out.println("Enter the choices: ");
        for(int i = 0; i < synonyms.length; i++) { //For the max amount of synonyms
            System.out.print((i + 1) + ") ");
            synonyms[i] = scanner.next(); //Add the word to the synonym array
        }

        System.out.println();

        double highest = -2;
        String closest = "";
        for (String synonym : synonyms) { //For each synonym
            double similarity = mySyn.calculateCosineSimilarity(wordToCheck, synonym); //Check the cosine similarity between the word and each synonym
            System.out.println(synonym + " " + similarity); //Print it
            if(highest < similarity) closest = synonym; //If the current cosine is higher than the highest, set the closest synonym to the current synonym
        }

        System.out.println("\nThe closest synonym: " + closest); //Print the closest synonym

    }

    /**
     * Constructor
     *
     * @param corpus An array of url's
     */
    public Synonyms (URL[] corpus) { parseCorpus(corpus); }

    /**
     * Take an array of url's and put each word
     * into a hashmap
     *
     * @param corpus An array of url's
     */
    public void parseCorpus (URL[] corpus) {

        for (URL url:corpus) { //For each URL
            try {
                Scanner scanner = new Scanner(url.openStream()).useDelimiter("[.?!]|\\Z"); //Read each sentence in the URL

                while (scanner.hasNext()) { //While the file has another line
                    String sentence = scanner.next().toLowerCase(); //Read it and set it to lowercase
                    String[] wordsInSentence = sentence.split("\\s+"); //Make an array of each word in the sentence

                    for(int i = 0; i < wordsInSentence.length; i++) { //For each word in the sentence
                        wordsInSentence[i] = wordsInSentence[i].replaceAll("\\W+", ""); //Remove all non-characters
                    }

                    for (String word : wordsInSentence) { //For each word in the sentence

                        HashMap<String, Integer> wordCount = new HashMap<>(); //Create a temporary hashmap to hold the counts of each other word in the sentence

                        for (String otherWord : wordsInSentence) { //For each word in the sentence...

                            if (!Objects.equals(word, otherWord) && !Objects.equals(otherWord, "")) { //...That is not the current word or an empty character

                                if(descriptors.containsKey(word)) //If the main hashmap, descriptors, already has the word in it
                                    wordCount = descriptors.get(word); //Grab the counts of each other word already in it and add to the temporary hashmap

                                if(wordCount.get(otherWord) == null) //If the other word in the sentence is NOT in the hashmap
                                    wordCount.put(otherWord, 1); //Set its count to one
                                else //If the other word in the sentence IS in the hashmap
                                    wordCount.put(otherWord, wordCount.get(otherWord)+1); //Add 1 to that word's count
                            }

                        }

                        descriptors.put(word, wordCount); //Add our temporary hashmap to the main hashmap
                    }
                }
                scanner.close(); //Stop reading the file
            } catch (IOException e) { //Catch any exceptions
                e.printStackTrace(); //Print any exceptions
            }
        }
    }

    /**
     * Take two words and check the cosine similarity
     * between the two words.
     *
     * @param word1 Word to check
     * @param word2 The synonym
     * @return The cosine similarity between the two words
     */
    public double calculateCosineSimilarity (String word1, String word2) {

        if (word1.equals(word2)) return 1; //Return 1 if the words are identical
        else if(!descriptors.containsKey(word1) || !descriptors.containsKey(word2)) return -1; //Return -1 if a word isn't found
        else { //If each word is unique, and both exist

            double dotProduct = 0, word1Sum = 0, word2Sum = 0;

            HashMap<String, Integer> word1Map = descriptors.get(word1);
            HashMap<String, Integer> word2Map = descriptors.get(word2);

            for(Map.Entry<String, Integer> item : word1Map.entrySet())
                dotProduct += item.getValue() * word2Map.getOrDefault(item.getKey(), 0); //Multiply the value in word1Map with the value in word2Map and add it to the dotProduct

            for (Map.Entry<String, Integer> item : word1Map.entrySet()) //For each word in the word1Map
                word1Sum += item.getValue() * item.getValue(); //Square the current value and add it to the word1Sum

            for (Map.Entry<String, Integer> item : word2Map.entrySet()) //For each word in the word2Map
                word2Sum += item.getValue() * item.getValue(); //Square the current value and add it to the word2Sum


            return dotProduct / Math.sqrt(word1Sum * word2Sum); //Return cosine similarity
        }
    }
}