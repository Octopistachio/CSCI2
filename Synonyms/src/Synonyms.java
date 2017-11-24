import javax.print.DocFlavor;
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
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Synonyms mySyn = new Synonyms(corpus);

    }

    public Synonyms (URL[] corpus) {

        parseCorpus(corpus);
        printDescriptor();

    }

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

    public void printDescriptor(){

        for (Map.Entry<String, HashMap<String, Integer>> entry : descriptors.entrySet()) {
            String key = entry.getKey();
            HashMap<String, Integer> value = entry.getValue();

            System.out.println("Word: " + key + "\tWords in the same sentence: " + value);
        }

    }

}

