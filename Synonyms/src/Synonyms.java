import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Scanner;

public class Synonyms {

    private HashMap<String, HashMap<String, Integer>> descriptors;

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

    }

    public void parseCorpus (URL[] corpus) {

        for (URL url:corpus) {
            try {
                Scanner scanner = new Scanner(url.openStream()).useDelimiter("[.?!]|\\Z");

                while (scanner.hasNext()) {
                    String sentence = scanner.next();
                    String[] wordList = sentence.split("\\s+");

                    for (String word:wordList) {
                        word = word.replaceAll("\\W+", "");
                        System.out.println(word);
                    }

                }
                scanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
