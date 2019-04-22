package cli;

import java.util.Scanner;

/**
 * Console
 */
public class Console {

    Scanner scanner;

    public static void main(String[] args) {

        Console c = new Console();
        c.start();

        c.waitForLine();

        c.close();
    }

    public void start() {
        scanner = new Scanner(System.in);
    }

    public void close() {
        getScanner().close();
    }

    public boolean waitForLine() {
        return getScanner().hasNextLine();
    }
    
    public Scanner getScanner() {
        return scanner;
    }
    
}