package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class MinimumInterval {

    public static void main(String[] args) {
        // Invoking this method will generate the names for the attributes in the test files.
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter their name
        System.out.print("Enter 1 to generate params, 2 to generate the whole class: ");

        // Read the input provided by the user
        String name = scanner.nextLine();
        if (name.contentEquals("1")) {
            paramNameGenerator();
        } else if (name.contentEquals("2")) {
            dtoGenerator();
        } else if (name.contentEquals("3")) {
            // Hash value generator.
            String value = scanner.nextLine();
            String hexVersionOfHash = hashGenerator(value);
            System.out.print("Hash of the input: " + value + " = " + hexVersionOfHash);
        }
    }

    public static void paramNameGenerator() {
        // Const name
        String name = "private short testParam187695448param";
        for (int i = 1; i <= 5000; i++) {
            System.out.println(name + i + ";");
        }
    }

    public static void generateGettersAndSetters() {
        String name = "private short testParam187695448param";
        for (int i = 1; i <= 5000; i++) {
            String currentAttribute = name + i;
            // Format the parameter capitalization.
            String parameter = currentAttribute.split(" ")[2];
            String capParameter = Character.toUpperCase(parameter.charAt(0)) + parameter.substring(1);
            // Print setters.
            System.out.println("public void set" + capParameter + "( short pr) {");
            System.out.println("this." + parameter + " = pr;");
            System.out.println("}");
            // Print getter
            System.out.println("public short get" + capParameter + "( ) {");
            System.out.println("return this." + parameter + ";");
            System.out.println("}");
        }
    }

    /**
     * Generate the complete DTO class for the increase the Pojo (Optimized solution to generate).
     * And write the lines to a file.
     */
    public static void dtoGenerator() {
        // Define the name of the file to store the lines.
        String filePath = "Test .txt";

        // Write to file logic for the custom print stream.
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            PrintStream printStream = new PrintStream(System.out) {
                @Override
                public void println(String x) {
                    super.println(x);
                    try {
                        fileWriter.write(x + "\n");
                        fileWriter.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };

            System.setOut(printStream);

            // Start the file writing logic
            System.out.println("public class TempTest() {");

            // Call the utility method to generate all the parameters
            paramNameGenerator();

            // Generate the getters and setters
            generateGettersAndSetters();

            // Close the class definition
            System.out.println("}");

            // Close the file writer
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method return the Hashed value of the entered String keyword. (Optional)
     */
    public static String hashGenerator(String input) {
        try {
            // Message Digest Instance
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            // Convert the byte array to Hexadecimal string
            StringBuilder hexStringBuilder = new StringBuilder(2 * hashBytes.length);
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexStringBuilder.append('0');
                }
                hexStringBuilder.append(hex);
            }
            return hexStringBuilder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
