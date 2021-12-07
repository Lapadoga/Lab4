package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String path = "";
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert the directory to file ");
        CSV csv = new CSV(scanner.next());
        try {
            csv.reading();
            String choice = "";
            while (!choice.equals("C")) {
                System.out.println("Choose what to do ");
                System.out.println("A. Get all the people out");
                System.out.println("B. Output all divisions");
                System.out.println("C. Exit");
                choice = scanner.next();
                switch (choice) {
                    case "A": {
                        for (int i = 0; i < csv.humans.size(); i++) {
                            System.out.println(csv.humans.get(i).toString());
                        }
                        break;
                    }
                    case "B": {
                        for (int i = 0; i < csv.divisions.size(); i++) {
                            System.out.println(csv.divisions.get(i).toString());
                        }
                        break;
                    }
                    case "C":
                        break;
                    default:
                        System.out.println("Incorrect value");
                }
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }
}
