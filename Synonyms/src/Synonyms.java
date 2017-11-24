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
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Synonyms mySyn = new Synonyms(corpus);

        System.out.println(mySyn.calculateCosineSimilarity("shark", "whale"));
        System.out.println(mySyn.calculateCosineSimilarity("shark", "horse"));
        System.out.println(mySyn.calculateCosineSimilarity("shark", "dancing"));
        System.out.println(mySyn.calculateCosineSimilarity("shark", "kill"));


    }

    public Synonyms (URL[] corpus) {

        parseCorpus(corpus);

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

    public double calculateCosineSimilarity (String word1, String word2) {

        if (word1.equals(word2)) return 1; //Return 1 if the words are identical

        HashMap<String, Integer> word1Map = descriptors.get(word1);
        HashMap<String, Integer> word2Map = descriptors.get(word2);

        HashMap<String, Integer> word1Matches = new HashMap<>();
        HashMap<String, Integer> word2Matches = new HashMap<>();

        try {
            for (Map.Entry<String, Integer> entry1 : word1Map.entrySet()) { //For each word in the word1Map
                String key1 = entry1.getKey();

                for (Map.Entry<String, Integer> entry2 : word2Map.entrySet()) { //For each word in the word2Map
                    String key2 = entry2.getKey();

                    if (Objects.equals(key1, key2)) {

                        word1Matches.put(key1, word1Map.get(key1));
                        word2Matches.put(key2, word2Map.get(key2));
                    }
                }
            }
        }
        catch (NullPointerException e) {
            System.out.println("A word does not exist!");
            return -1;
        }

        double dotProduct = 0, magnitudeA = 0, magnitudeB = 0;

        //Calculate dot product
        for (Map.Entry<String, Integer> item : word1Matches.entrySet()) { //For each word in the word1Map
            String key = item.getKey();
            dotProduct += word1Matches.get(key) * word2Matches.get(key);
        }

        //Calculate magnitude a
        for (String key : word1Matches.keySet()) {
            magnitudeA += Math.pow(word1Matches.get(key), 2);
        }

        //Calculate magnitude b
        for (String key : word2Matches.keySet()) {
            magnitudeB += Math.pow(word2Matches.get(key), 2);
        }

        //return cosine similarity
        return dotProduct / Math.sqrt(magnitudeA * magnitudeB);

    }
}

