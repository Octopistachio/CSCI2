import sun.reflect.generics.tree.Tree;

import java.io.*;
import java.util.Scanner;
import java.util.TreeMap;

public class MorseCode {

    public MorseCode() throws Exception {
        TreeNode root = new TreeNode();
        createMorseTree();
    }

    private static TreeMap<Character, String> toCode = new TreeMap<>(); //Encodes characters to Morse Code

    private static TreeNode root = new TreeNode();

    public static class TreeNode {
        private char letter;
        private TreeNode left;
        private TreeNode right;

        public static final char NEWLETTER = '@';
        public static final char SPACE = '*';
        public static final char NEWLINE = '+';


        public TreeNode() {
            letter = NEWLETTER;
            left = null;
            right = null;
        }

        public char getLetter() { return letter; }
        public void setLetter(char letter) { this.letter = letter; }

        public TreeNode getLeft() { return left; }
        public void setLeft(TreeNode left) { this.left = left; }

        public TreeNode getRight() { return right; }
        public void setRight(TreeNode right) { this.right = right; }
    }

    public static void main(String[] args) throws Exception {
        encodeFile("mcgee.txt", "mcgeeMattCopy.encoded");
        decodeFile("mcgee.encoded", "mcgeeMattCopy.txt");
    }

    public static void encodeFile(String inputFileName, String outputFileName) throws Exception {

        PrintWriter writer = new PrintWriter(outputFileName); //The writer to the encoded file
        File file = new File(inputFileName); //The file to be encoded's input
        Scanner line = new Scanner(file); //The line reader

        while (line.hasNextLine()) { //While there is a new line

            char[] charArray = line.nextLine().toUpperCase().toCharArray(); //Make the line capital, and make it an array of characters
            int tabSpacingMax = 0; //The max number of spaces before the sentence starts
            int currentCount = 0; //The current count before the sentence begins

            for(char character:charArray){ //For each character in the line
                if(character != ' ') //If the character is not a space
                    break; //Break the loop
                tabSpacingMax++; //Else keep increasing the max
            }

            for (char character:charArray) { //For each character in the line
                if(currentCount >= tabSpacingMax) { //If the current count is not equal to the max
                    if (toCode.containsKey(character)) { //And if the current character is in the tree
                        String encodedCharacter = toCode.get(character); //Pull the encoded character from the tree
                        writer.write(encodedCharacter + "\n"); //And write it to the file
                    }
                }

                if(currentCount != tabSpacingMax) //If the current count does not equal the max
                    currentCount++; //Increase the current count
            }
            writer.write('+' + "\n"); //When reaching the end of the line, add a +, indicating a new line
        }
        writer.close(); //Close the writer
        line.close(); //Close the scanner
    }

    public static void decodeFile(String inputFileName, String outputFileName) throws Exception {

        PrintWriter writer = new PrintWriter(outputFileName); //The writer to the encoded file
        File file = new File(inputFileName); //The file to be encoded's input
        Scanner line = new Scanner(file); //The line reader

        String signal = "";
        StringBuffer result = new StringBuffer();
        TreeNode current = root;

        while (line.hasNextLine()) { //While there is a new line
            String value = line.nextLine();

            for (int i = 0; i < value.length(); i++) {
                signal = value.substring(i, i + 1);

                if (signal.equals(".")) {
                    System.out.println(".");
                    if (current.getLeft() != null) {
                        current = current.getLeft();
                    } else {
                        current.setLeft(new TreeNode());
                        current = current.getLeft();
                    }
                } else if (signal.equals("-")) {
                    System.out.println("-");
                    if (current.getRight() != null) {
                        current = current.getRight();
                    } else {
                        current.setRight(new TreeNode());
                        current = current.getRight();
                    }
                } else {
                    System.out.println("other");
                    current = root;
                }
            }

            System.out.println(current.getLetter());
        }
    }

    private void createMorseTree() throws Exception {

        if (toCode.isEmpty()) { //If the tree map is empty
            File file = new File("MorseCode.txt"); //Grab the MorseCode text file
            Scanner scanner = new Scanner(file); //Create a scanner for the file

            while (scanner.hasNextLine()) { //While there is a new line
                String line = scanner.nextLine().toUpperCase(); //Make the character uppercase
                String[] lineArray = line.split("\\s+"); //And split the line by whitespaces

                char character = lineArray[0].charAt(0); //Set the character to the first character on the line
                String morseValue = lineArray[1]; //Set the morse value to the value from the file

                toCode.put(character, morseValue); //Add the value and the character to the tree
            }
            scanner.close(); //Close the scanner

            toCode.put(' ', "*"); //Manually add the space to the tree
        }
    }
}

