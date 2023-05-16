package io;

import java.util.Scanner;

public class UserIOConsoleImpl implements UserIO{
    Scanner scanner = new Scanner(System.in);

    @Override
    public String getUserInput(String prompt) {
        System.out.printf("%s: ", prompt);
        return scanner.nextLine();
    }

    @Override
    public void displayMessage(String message) {
        System.out.println(message);
    }
}
