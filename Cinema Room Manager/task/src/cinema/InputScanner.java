package cinema;

import java.util.Scanner;

public class InputScanner {

    private static Scanner scanner;
    private InputScanner(){

    }
    public static Scanner getInputScanner() {
        if(scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void closeInputScanner(){
        scanner.close();
    }

}
