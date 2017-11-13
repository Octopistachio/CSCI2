import java.util.Scanner;

public class TestMain {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in); //Create scanner object
        NameRecord nameRecordObj = new NameRecord(reader); //Create NameRecord object
        System.out.println(nameRecordObj.getName()); //Print the name the user input
        for(int i = 0; i < 12; i++) //For each year
            System.out.println("In " + (1900 + 10 * i) +" the ranking was: " + nameRecordObj.getRank(i)); //Print the ranking during that decade
    }
}
