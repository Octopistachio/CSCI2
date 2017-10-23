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

        System.out.println("Should be true: " + Recursion.isBalanced("r { [ ] ( ) } )"));
        System.out.println("Should be true: " + Recursion.isBalanced("( { [ ] } )"));
        System.out.println("Should be true: " + Recursion.isBalanced("( ) { } [ ]"));
        System.out.println("Should be true: " + Recursion.isBalanced("[ ( ) ] { [ ( ) [ ] ] } "));
        System.out.println("Should be false: " + Recursion.isBalanced("[ ( ) "));
        System.out.println("Should be false: " + Recursion.isBalanced("[ ( ) }"));
        System.out.println("Should be false: " + Recursion.isBalanced("} ( ) }"));
        System.out.println("Should be false: " + Recursion.isBalanced("[ ( ) ] { [ ( ( [ ] ] } "));

        System.out.println();

        int[] array1 = {2,2}; System.out.println("Should be true: " + Recursion.splitArray(array1));
        int[] array2 = {5,3,2}; System.out.println("Should be true: " + Recursion.splitArray(array2));
        int[] array3 = {3,5,2}; System.out.println("Should be true: " + Recursion.splitArray(array3));
        int[] array4 = {3,5,3}; System.out.println("Should be false: " + Recursion.splitArray(array4));
        int[] array5 = {3,5,3}; System.out.println("Should be false: " + Recursion.splitArray(array5));

        System.out.println();


    }

}
