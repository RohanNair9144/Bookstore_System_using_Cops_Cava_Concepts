package com.company;
// package of java class

import java.awt.*;
// This is used to open the Notepad

import java.io.*;
import java.util.Scanner;
// These all are used to deal with the Files

public class MiniProject {
    // The public class of java and the file name

    static Scanner in = new Scanner(System.in);
    // This is to take the inputs from the users

    static String PathName1 = "C:\\Users\\sahaj\\javaoop\\src\\com\\company\\BooksName.txt";
    static String PathName2 = "C:\\Users\\sahaj\\javaoop\\src\\com\\company\\AuthorsName.txt";
    static String PathName3 = "C:\\Users\\sahaj\\javaoop\\src\\com\\company\\Quantity.txt";
    static String PathName4 = "C:\\Users\\sahaj\\javaoop\\src\\com\\company\\Price.txt";
    static String PathName5 = "C:\\Users\\sahaj\\javaoop\\src\\com\\company\\Temp.txt";
    static String PathName6 = "C:\\Users\\sahaj\\javaoop\\src\\com\\company\\Receipt.txt";
    // These are the PathNames of the files that contains the information of the Book Store

    static String ans;
    // This is to ask user whether he wants to proceed the transaction or not

    public static void main(String[] args) {
        // The Driver method

        int TOTAL_BOOKS_SOLD = 0;
        // Total books bought by the user

        System.out.println(" ");
        System.out.println("----- Welcome to OOP Mini Project Book Store -----");
        System.out.println(" ");

        while (true) {

            System.out.print("Do you want to buy any Book(yes/no): ");
            String answer = in.next();
            System.out.println(" ");

            if (answer.equals("no")) {
                System.out.println("No Problem, Thanks for visiting !");
                System.out.println("Total Books sold were: " + TOTAL_BOOKS_SOLD);

                System.out.println(" ");
                System.out.print("Press any key to exit...");
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

                try {
                    input.readLine();
                }
                catch (Exception e) {
                    System.out.println(e);
                }

                return;
            }

            else if (answer.equals("yes")) {

                System.out.print("Enter the Name of the Book that you want to buy: ");
                in.nextLine();
                String Name = in.nextLine();
                System.out.println(" ");

                try {

                    File Book_Name = new File(PathName1);
                    File Author_Name = new File(PathName2);
                    File Quantity = new File(PathName3);
                    File Price = new File(PathName4);
                    File Temp = new File(PathName5);
                    // Opening all the necessary files

                    Scanner BookName = new Scanner(Book_Name);
                    Scanner AuthorName = new Scanner(Author_Name);
                    Scanner QuantityAvailable = new Scanner(Quantity);
                    Scanner PricePerBook = new Scanner(Price);
                    // Using Scanner Objects to read from the Files

                    int count = 0;
                    int num = 0;

                    while (BookName.hasNextLine()) {

                        count++;
                        String temp1 = BookName.nextLine();

                        if (temp1.equals(Name)) {
                            // If the Book is found, Display all the details of the Book
                            System.out.println("Book Name: " + temp1);
                            System.out.println("Book ID: " + count);

                            for (int i = 0; i < count - 1; i++) {
                                AuthorName.nextLine();
                                QuantityAvailable.nextLine();
                                PricePerBook.nextLine();
                            }

                            String temp2 = AuthorName.nextLine();
                            int temp3 = QuantityAvailable.nextInt();
                            int temp4 = PricePerBook.nextInt();
                            System.out.println("Author Name: " + temp2);
                            System.out.println("Available Quantity: " + temp3);
                            System.out.println("Price of One Book: " + temp4);
                            System.out.println(" ");

                            do {

                                System.out.print("Enter the Number of Copies you want to buy: ");
                                num = in.nextInt();
                                // Asking the User, how many copies are needed

                                if (num > temp3) {
                                    System.out.println("Sorry but only " + temp3 + " copies are left");
                                    System.out.println(" ");
                                }

                            }
                            while (num > temp3);

                            System.out.println(" ");

                            Calculate_Price(num, temp4, temp1, temp2);
                            // This will Calculate the Price to be paid by the user

                            if (ans.equals("yes") && Temp.createNewFile()) {

                                Scanner QuantityModify = new Scanner(Quantity);
                                FileWriter modify = new FileWriter(PathName5);
                                // This is to modify the information in the Inventory

                                int i = 1;

                                while (i != count) {
                                    modify.write(QuantityModify.nextInt() + "\n");
                                    i++;
                                }
                                // This loop is used to change the quantities of the sold book

                                modify.write(temp3 - num + "\n");
                                i += 1;

                                QuantityModify.nextInt();

                                while (i != 101) {
                                    modify.write(QuantityModify.nextInt() + "\n");
                                    i++;
                                }

                                modify.close();
                                QuantityModify.close();
                                // Closing the Scanners and FileWriters

                            }
                            break;
                        }

                        else if (!BookName.hasNextLine()){
                            count += 1;
                        }

                        else {
                            continue;
                        }
                    }

                    if (!BookName.hasNextLine() && count != 100) {
                        System.out.println("Sorry but this Book is not available right now");
                        System.out.println(" ");

                        BookName.close();
                        AuthorName.close();
                        QuantityAvailable.close();
                        PricePerBook.close();
                        // Closing the Scanners and FileWriters

                    }

                    else if (ans.equals("no")){

                        BookName.close();
                        AuthorName.close();
                        QuantityAvailable.close();
                        PricePerBook.close();
                        // Closing the Scanners and FileWriters

                    }

                    else {
                        BookName.close();
                        AuthorName.close();
                        QuantityAvailable.close();
                        PricePerBook.close();
                        // Closing the Scanners and FileWriters

                        Quantity.delete();
                        Temp.renameTo(Quantity);
                        Temp.delete();
                        TOTAL_BOOKS_SOLD += num;
                    }
                }

                catch (Exception e) {
                    System.out.println(" ");
                    System.out.println("Error has occurred");
                    System.out.println(e);
                    System.out.println(" ");
                    if (e.toString().contains("Input")) {
                        String temp = in.next();
                    }
                }

            }

            else {
                System.out.println("Please Enter the Correct data !");
                System.out.println(" ");
            }
        }
    }

    private static void Calculate_Price(int quantity, int price, String Book, String Author) throws IOException {

        int total = price * quantity;
        System.out.println("Total Amount to be paid: " + total);
        System.out.println(" ");

        while (true) {

            System.out.print("Would you like to proceed the transaction(yes/no): ");
            ans = in.next();
            System.out.println(" ");

            if (ans.equals("yes")) {

                System.out.print("Please enter your Name: ");
                in.nextLine();
                String name = in.nextLine();
                System.out.print("Please enter your Mobile number: ");
                long phone = in.nextLong();
                System.out.println(" ");
                System.out.println("Thank you for visiting, Here is your Receipt");
                System.out.println(" ");

                File Receipt = new File(PathName6);
                // Creating a new Text File to Display the Receipt

                if (Receipt.createNewFile()){

                    FileWriter info = new FileWriter(PathName6);
                    info.write("----- OOP MINI PROJECT BOOK STORE -----\n");
                    info.write(" \n");
                    info.write("Name: " + name + "\n");
                    info.write("Mobile number: " + phone + "\n");
                    info.write("Name of the Purchased Book: " + Book + "\n");
                    info.write("Name of Author: " + Author + "\n");
                    info.write("Number of copies purchased: " + quantity + "\n");
                    info.write("Total Amount: " + total + "\n");
                    info.write("Paid Amount: " + total + "\n");
                    info.write(" \n");
                    info.write("Transaction Successful\n");
                    // Writing the information inside the Receipt

                    info.close();
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(Receipt);

                }

                Receipt.deleteOnExit();
                break;
            }

            else if (ans.equals("no")) {
                return;
            }

            else {
                System.out.println("Please enter the correct data !");
                System.out.println(" ");
            }

        }
    }
}
