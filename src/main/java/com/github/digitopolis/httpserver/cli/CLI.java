package com.github.digitopolis.httpserver.cli;

import java.util.Scanner;

public class CLI {
    public String getInput() {
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public void printMessage(String msg) {
        System.out.println(msg);
    }
}
