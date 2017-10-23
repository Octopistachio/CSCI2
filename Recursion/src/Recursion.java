/**
 * Recursion testing
 *
 * @author Matthew Wilson
 * @created 10/11/17
 */

public class Recursion {

    private static String removeChar(String str, int index){ return str.substring(0, index) + str.substring(index + 1); }

    public static String stringClean(String str) { return stringClean(str, 0); }

    private static String stringClean(String str, int count) {

    if(count < str.length() - 1) {
        if (str.charAt(count) == str.charAt(count + 1)) return stringClean(removeChar(str, count + 1));
        return stringClean(str, count + 1);
    }
    return str;
}
    public static int countDigit(int num, int digit){ return countDigit(num, digit, 0); }

    private static int countDigit(int num, int digit, int timesAppeared){
        if(num > 0){
            if(num%10 == digit) return countDigit(num/10, digit, timesAppeared + 1);
            return countDigit(num/10, digit, timesAppeared);
        }
        return timesAppeared;
    }

    public static boolean isBalanced(String str){
        //If there are any spaces, remove them
        for(int i = 0; i < str.length(); i++) { if(str.charAt(i) == ' ') { str = removeChar(str, i); } }
        return isBalanced(str, 0);
    }

    private static boolean isBalanced(String str, int count) {

        if (str.length() % 2 != 0) return false;
        if (count < str.length()) {

            if (str.charAt(count) == '(' && str.charAt(count + 1) == ')') {
                str = removeChar(str, count);
                str = removeChar(str, count);
                return isBalanced(str, 0);
            } else if (str.charAt(count) == '[' && str.charAt(count + 1) == ']') {
                str = removeChar(str, count);
                str = removeChar(str, count);
                return isBalanced(str, 0);
            } else if (str.charAt(count) == '{' && str.charAt(count + 1) == '}') {
                str = removeChar(str, count);
                str = removeChar(str, count);
                return isBalanced(str, 0);
            } else return isBalanced(str, count + 1);

        }
        return str.matches("");
    }

    public static boolean splitArray(int [] array) { return splitArray(array, 0); }

    private static boolean splitArray(int [] array, int count)
    {

        if(array.length == 2) return(array[count] == array[count + 1]);

        if(count<array.length) {


            if(array.length % 2 == 0) {



            }
            else {




                }

            }


        return false;
    }

}