import javax.xml.soap.Node;
import java.io.*;
import java.util.Arrays;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class MorseCode {

    private static TreeMap<Character, String> toCode = new TreeMap<>(); //Encodes characters to Morse Code


    public static void main(String[] args) throws Exception {
        encodeFile("mcgee.txt", "mcgeeMyCopy.encoded");
    }

    public static void encodeFile(String inputFileName, String outputFileName) throws Exception {


        createMorseTree();

        PrintWriter writer = new PrintWriter(outputFileName);
        File file = new File(inputFileName);
        Scanner line = new Scanner(file);

        while (line.hasNextLine()) {

            char[] charArray = line.toString().toUpperCase().toCharArray();

            for (char character:charArray) {
                if(toCode.containsKey(character)) {
                    String encodedCharacter = toCode.get(character);
                    writer.write(encodedCharacter + "\n");
                    System.out.println(character + " = " + toCode.get(character));
                }
            }

        }
        writer.close();
        line.close();


    }

    private static void createMorseTree() throws Exception{
        File file = new File("MorseCode.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().toUpperCase();
            String[] lineArray = line.split("\\s+");

            char character = lineArray[0].charAt(0);
            String morseValue = lineArray[1];

            toCode.put(character, morseValue);
        }
        scanner.close();

        toCode.put(' ', "*");
        toCode.put('\n', "+");
    }
}

