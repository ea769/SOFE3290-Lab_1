package com.ontariotechu.sofe3980U;

import java.time.LocalTime;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        System.out.println("The current local time is: " + LocalTime.now());
        System.out.println("Binary Calculator (supports Add, OR, AND, Multiply)");
        System.out.println("Enter two binary numbers (only 0/1). Leading zeros are allowed.\n");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first binary number: ");
        Binary b1 = new Binary(sc.nextLine().trim());

        System.out.print("Enter second binary number: ");
        Binary b2 = new Binary(sc.nextLine().trim());

        System.out.println("\nFirst binary number is  " + b1.getValue());
        System.out.println("Second binary number is " + b2.getValue());

        Binary sum = Binary.add(b1, b2);
        Binary bor = Binary.or(b1, b2);
        Binary band = Binary.and(b1, b2);
        Binary prod = Binary.multiply(b1, b2);

        System.out.println("\nResults:");
        System.out.println("Add:      " + sum.getValue());
        System.out.println("OR:       " + bor.getValue());
        System.out.println("AND:      " + band.getValue());
        System.out.println("Multiply: " + prod.getValue());

        sc.close();
    }
}
