import com.sun.org.apache.regexp.internal.RE;

public class TestRecursion {

    public static void main(String[] args) {

        System.out.println("Should be abc: " + Recursion.stringClean("aabbbbbbcccc"));
        System.out.println("Should be Helo: " + Recursion.stringClean("Hello"));
        System.out.println("Should be ea: " + Recursion.stringClean("eeeeeeeeaaa"));

        System.out.println();

        System.out.println("Should be 5: " + Recursion.countDigit(696969696, 6));
        System.out.println("Should be 3: " + Recursion.countDigit(222, 2));
        System.out.println("Should be 1: " + Recursion.countDigit(12345, 1));
        System.out.println("Should be 0: " + Recursion.countDigit(123414, 5));

        System.out.println();

        System.out.println("Should be true: " + Recursion.isBalanced("( { [   ] } )"));
        System.out.println("Should be false: " + Recursion.isBalanced("( { ) } "));
        System.out.print("Should be true : " + Recursion.isBalanced("{ { { [ [ ( ) ] ] } } }"));


    }

}
