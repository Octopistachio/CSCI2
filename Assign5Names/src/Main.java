import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        NameRecord name = new NameRecord(reader);
        System.out.println(name.getName());
        System.out.println(name.getRank(0));
        System.out.println(name.getRank(1));


    }
}
