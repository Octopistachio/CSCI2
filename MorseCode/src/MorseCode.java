/**
 * This class takes files and translates them
 * to or from Morse Code
 *
 * @author Matt Wilson
 * @date 11/28/17
 */

import java.io.*;
import java.util.Scanner;
import java.util.TreeMap;

public class MorseCode{

    private TreeNode root;

    public MorseCode() throws Exception {
        root = new TreeNode();
        createMorseTree();
    }

    private TreeMap<Character, String> toCode = new TreeMap<>(); //Encodes characters to Morse Code

    public class TreeNode {
        private char letter;
        private TreeNode left;
        private TreeNode right;

        public TreeNode() {
            left = null;
            right = null;
        }

        public TreeNode getLeft() { return left; }
        public void setLeft(TreeNode left) { this.left = left; }

        public TreeNode getRight() { return right; }
        public void setRight(TreeNode right) { this.right = right; }
    }

    /**
     * Takes a file and translates it to Morse
     *
     * @param inputFileName The name of the file being translated
     * @param outputFileName The new file that will be created from the translation
     */
    public void encodeFile(String inputFileName, String outputFileName) throws Exception {

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

    /**
     * Takes a file and translates it from Morse
     *
     * @param inputFileName The name of the file being translated
     * @param outputFileName The new file that will be created from the translation
     */
    public void decodeFile(String inputFileName, String outputFileName) throws Exception {

        PrintWriter writer = new PrintWriter(outputFileName); //The writer to the decoded file
        File file = new File(inputFileName); //The file to be decoded's input
        Scanner line = new Scanner(file); //The line reader

        String signal = ""; //The morse signal
        TreeNode current = root; //The current node

        while (line.hasNextLine()) { //While there is a new line
            String value = line.nextLine(); //Set the value equal to the next line

            for (int i = 0; i < value.length(); i++) { //For each character on the line
                signal = value.substring(i, i + 1); //Grab the current character

                if (signal.equals(".")) { //If it is a .
                    if (current.getLeft() != null) { //And the left node isn't null
                        current = current.getLeft(); //Go left
                    } else { //If the node is null
                        current.setLeft(new TreeNode()); //Set the left node to a new node
                        current = current.getLeft(); //And get the left node
                    }
                } else if (signal.equals("-")) { //If it is a -
                    if (current.getRight() != null) { //And the right node isn't null
                        current = current.getRight(); //Go right
                    } else { //If the node is null
                        current.setRight(new TreeNode()); //Set the right node to a new node
                        current = current.getRight(); //And get the right node
                    }
                } else { //If it is not either of those signals
                    current = root; //Restart the loop
                }
            }
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

