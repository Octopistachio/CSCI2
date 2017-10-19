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

        if(count < str.length()) {

            if (str.charAt(count) == '(' && str.charAt(count + 1) == ')') {
                str = removeChar(str, count);
                str = removeChar(str, count);
                return isBalanced(str, 0);
            }
            else if (str.charAt(count) == '[' && str.charAt(count + 1) == ']') {
                str = removeChar(str, count);
                str = removeChar(str, count);
                return isBalanced(str, 0);
            }
            else if (str.charAt(count) == '{' && str.charAt(count + 1) == '}') {
                str = removeChar(str, count);
                str = removeChar(str, count);
                return isBalanced(str, 0);
            }
            else
                return isBalanced(str, count + 1);

        }
        if(str.matches(""))
            return true;

        return false;


    }



    /*private static  boolean isBalanced(String str, int count){
        System.out.println(str);

        if(count < str.length()/2) {

            if (str.charAt(count) == '{' && str.charAt(str.length() - 1 - count) == '}') return isBalanced(str, count + 1);
            else if (str.charAt(count) == '(' && str.charAt(str.length() - 1 - count) == ')') return isBalanced(str, count + 1);
            else if (str.charAt(count) == '[' && str.charAt(str.length() - 1 - count) == ']') return isBalanced(str, count + 1);
            else{
                if(str.charAt(count) == '{' && str.charAt(count + 1) == '}') {
                    str = str.substring(0, count) + str.substring(count + 1);
                    str = str.substring(0, count+1) + str.substring(count + 2);
                    return isBalanced(str, count + 1);
                }
                else if(str.charAt(count) == '[' && str.charAt(count + 1) == ']') {
                    str = str.substring(0, count) + str.substring(count + 1);
                    str = str.substring(0, count+1) + str.substring(count + 2);
                    return isBalanced(str, count + 1);
                }
                else if(str.charAt(count) == '(' && str.charAt(count + 1) == ')') {
                    str = str.substring(0, count) + str.substring(count + 1);
                    str = str.substring(0, count+1) + str.substring(count + 2);
                    return isBalanced(str, count + 1);
                }
                else return false;
            }
        }
        return true;
    }*/
}
