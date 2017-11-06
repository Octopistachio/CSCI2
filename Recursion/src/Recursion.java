/*
 * Recursion testing
 *
 * @author Matthew Wilson
 * @created 10/11/17
 */

import java.util.Arrays;

public class Recursion {

    /**
     * A method I needed to remove a single character from a string
     *
     * @param str The string a character is needed to be removed from
     * @param index The index of the ch
    public static String saracter
     * @return The string with the character removed
     */
    private static String removeChar(String str, int index){ return str.substring(0, index) + str.substring(index + 1); }
tringClean(String str) { return stringClean(str, 0); }

    private static String stringClean(String str, int count) {

    if(count < str.length() - 1) { //If the counter is less than the length of the string
        if (str.charAt(count) == str.charAt(count + 1)) return stringClean(removeChar(str, count + 1), 0); //If the current character is the same as the one to the right of it,
                                                                                                                        // recurse the method with the duplicate character removed from
                                                                                                                        // the string, and reset the count

        else return stringClean(str, count + 1); //If there aren't duplicates, recurse the same string, with the count plus 1
    }
    return str; //If the counter reaches the end of the string, return the string
}

    public static int countDigit(int num, int digit){ return countDigit(num, digit, 0); }

    private static int countDigit(int num, int digit, int timesAppeared){
        if(num > 0){ //If the number is greater than 0

            if(num%10 == digit) return countDigit(num/10, digit, timesAppeared + 1); //If the digit is equal to the last number in num,
                                                                                                        // return the number with the last digit missing and increase timesAppeared

            else return countDigit(num/10, digit, timesAppeared); //Else return the num without increasing timesAppeared
        }
        return timesAppeared; //Once num is <0, return timesAppeared
    }

    public static boolean isBalanced(String str){
        //If there are any spaces, remove them
        for(int i = 0; i < str.length(); i++) { if(str.charAt(i) == ' ') { str = removeChar(str, i); } }
        return isBalanced(str, 0);
    }

    private static boolean isBalanced(String str, int count) {

        if (str.length() % 2 != 0) return false; //If the string's length is not even, there is a missing/extra bracket, so return false
        if (count < str.length()) { //If the counter is less than the length of the string
            if (str.charAt(count) == '(' && str.charAt(count + 1) == ')') { //If the bracket is identical to the one next to it
                str = removeChar(str, count); //Remove the bracket
                str = removeChar(str, count); //Remove the bracket
                return isBalanced(str, 0); //Recurse the method, and reset the count
            } else if (str.charAt(count) == '[' && str.charAt(count + 1) == ']') { //For square brackets
                str = removeChar(str, count); //Remove the bracket
                str = removeChar(str, count); //Remove the bracket
                return isBalanced(str, 0); //Recurse the method, and reset the count
            } else if (str.charAt(count) == '{' && str.charAt(count + 1) == '}') { //For curly bracers
                str = removeChar(str, count); //Remove the bracket
                str = removeChar(str, count); //Remove the bracket
                return isBalanced(str, 0); //Recurse the method, and reset the count
            } else return isBalanced(str, count + 1); //If the bracket is not identical to the one next to it, recurse the method and increase the count by 1
        }
        return str.matches(""); //If the string is empty, return true
    }

    public static boolean splitArray(int [] array) { return splitArray(array, 0); }

    private static boolean splitArray(int [] array, int count) {

        Arrays.sort(array); //Sort the array

        if (array.length == 2) { return (array[0] == array[1]); } //If there are only 2 elements, check if they are equal
        else if (array.length == 3) { return (array[2] == array[0] + array[1]); }  //If there are 3 elements, check if the biggest is equal to the sum of the smaller values
        else {
                array[0] = array[0] + array[array.length - count - 1]; //Make the first element in the array itself plus the last element
                array[1] = array[1] + array[array.length - count - 2]; //Make the second element in the array itself plus the second to last element

                array = Arrays.copyOf(array, array.length - 2); //Make a copy of the array, but remove the last 2 values

                Arrays.sort(array); //Re-sort the array

                return splitArray(array, count + 1); //Increase the count by 1
        }
    }

    public static void trickyHanoi(int disks){ trickyHanoi(disks, "A", "B", "C"); }

    private static void trickyHanoi(int n, String start, String auxiliary, String end) {
        if (n == 1) { //If there is only one disk on a peg
            printHanoi(start, auxiliary); //Move it from the start to the second
            printHanoi(auxiliary, end); //Move it from the second to the end
        } else { //If there is more than one disk on a peg
            trickyHanoi(n - 1, start, auxiliary, end); //Recurse through the program
            printHanoi(start, auxiliary); //Move it from start to the second
            trickyHanoi(n - 1, end, auxiliary, start); //Recurse the program backwards
            printHanoi(auxiliary, end); //Move it from the second to the end
            trickyHanoi(n - 1, start, auxiliary, end); //Recurse through the program
        }
    }

    /**
     * Print the Towers of Hanoi text
     */
    private static void printHanoi(String a, String b) { System.out.println("Move disk from peg " + a + " to peg " + b + "."); }

}