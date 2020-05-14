package com.revature.utility;

import java.util.Scanner;

public class ScannerSingleton {

    public static Scanner scan;
    private static ScannerSingleton scannerSingleton;

    private ScannerSingleton() {
    }

    public static String getInput(String command) {
        if (scan == null)
            scan = new Scanner(System.in);

        System.out.print(command + " >> ");
        String choice = scan.next();
        return choice;
    }

    public static Integer getIntInput(String command) {
        if (scan == null)
            scan = new Scanner(System.in);

        System.out.print(command + " >> ");
        String choice = scan.next();
        return Integer.valueOf(choice);
    }
}