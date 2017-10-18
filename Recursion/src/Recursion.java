public class Recursion {

    public static String stringClean(String dirtyString) { return stringClean(dirtyString, 0); }

    private static String stringClean(String dirtyString, int count) {

    if(count < dirtyString.length() - 1) {
        if (dirtyString.charAt(count) == dirtyString.charAt(count + 1)) {
            dirtyString = dirtyString.substring(0, count + 1) + dirtyString.substring(count + 2);
            return stringClean(dirtyString, count);
        }
        return stringClean(dirtyString, count + 1);
    }

    return dirtyString;
}
    public static int countDigit(int num, int digit){
        return countDigit(num, digit, 0);
    }

    private static int countDigit(int num, int digit, int timesAppeared){
        if(num > 0){
            if(num%10 == digit){
                return countDigit(num/10, digit, timesAppeared + 1);
            }
            return countDigit(num/10, digit, timesAppeared);
        }
        return timesAppeared;
    }

    public static boolean isBalanced(String str){
        //If there are any spaces, remove them
        for(int i = 0; i < str.length(); i++) { if(str.charAt(i) == ' ') { str = str.substring(0, i) + str.substring(i + 1); } }
        return isBalanced(str, 0);
    }

    private static  boolean isBalanced(String str, int count){
        if(count < str.length()/2) {
            if (str.charAt(count) == '{' && str.charAt(str.length() - 1 - count) == '}') { return isBalanced(str, count + 1); }
            else if (str.charAt(count) == '(' && str.charAt(str.length() - 1 - count) == ')') { return isBalanced(str, count + 1); }
            else if (str.charAt(count) == '[' && str.charAt(str.length() - 1 - count) == ']') { return isBalanced(str, count + 1); }
            else return false;
        }
        return true;
    }
}
