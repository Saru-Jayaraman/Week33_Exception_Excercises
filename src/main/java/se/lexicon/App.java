package se.lexicon;

import se.lexicon.exception.DuplicateNameException;
import se.lexicon.exception.InsufficientBalanceException;
import se.lexicon.exception.NameNotFoundException;
import se.lexicon.exception.OutOfBoundException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Stream;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ex4();
    }

//    Exercise 1: Basic Exception Handling
//    Task: Write a program that asks the user to input two integers and then divides the first number by the second.
//    Implement exception handling to manage the scenario where the user inputs zero as the second number.
//    Hint: Use try-catch to handle ArithmeticException.
    public static void ex1() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Enter first number: ");
            int first = scanner.nextInt();
            System.out.println("Enter second number: ");
            int second = scanner.nextInt();
            System.out.println("Quotient: " + (first / second));
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception: ");
            System.out.println("You have entered second number as 0 which leads to Infinite quotient value...");
        } catch(InputMismatchException e) {
            System.out.println("Input Mismatch Exception: Enter a valid number...");
        } catch (Exception e) {
            System.out.println("An Unexpected Error");
            e.printStackTrace();
        }
    }

//    Exercise 2: Multiple Exception Types
//    Task: Create a method that reads an integer from the user and checks whether it is within a certain range (e.g., 1 to 100).
//    Handle exceptions for invalid inputs (e.g., non-integer input) and out-of-range values.
//    Hint: Use try-catch to handle InputMismatchException and a custom exception for out-of-range values.
    public static void ex2() {
        int max = 101;
        int min = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a number (1 - 100): ");
        try {
            int number = scanner.nextInt();
            if(number > min && number < max) {
                System.out.println("Entered number is within the range...");
            } else
                throw new OutOfBoundException("Out of bound Exception", number);
        } catch (InputMismatchException e) {
            System.out.println("Input Mismatch Exception: Enter a valid number...");
        } catch (OutOfBoundException e) {
            System.out.println("OutOfBounds Exception: Entered number is out of range... Enter a number in the range of 1-100...");
        } catch (Exception e) {
            System.out.println("Unexpected Error");
            e.printStackTrace();
        }
    }

//    Exercise 3: Custom Exception
//    Task: Define a custom exception InsufficientBalanceException that is thrown when a withdrawal amount exceeds the account balance.
//    Implement a simple banking system that allows deposits and withdrawals, and handles the custom exception appropriately.
//    Hint: Create a BankAccount class and handle the custom exception using throw and throws.
    public static void ex3() {
        try {
            BankAccount bankAccount = new BankAccount(100, "Customer1", 500);
            bankAccount.depositAmount(100);
//            bankAccount.depositAmount(-100);
            bankAccount.withdrawAmount(50);
//            bankAccount.withdrawAmount(-100);
            bankAccount.withdrawAmount(600);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage() + e.getBalance());
        }
    }

//    Exercise 4: Nested try-catch Blocks
//    Task: Write a program that attempts to read a file and parse its contents as integers. Implement nested try-catch blocks to
//    handle potential exceptions like NoSuchFileException, IOException and NumberFormatException.
//    Hint: Use nested try-catch blocks where the outer block handles file-related exceptions, and the inner block handles
//    parsing-related exceptions.
    public static void ex4() {
        try {
            Path filePath = Paths.get("files/numbers");
            Stream<String> stringStream = Files.lines(filePath);
            try {
                ToIntFunction<String> toIntegersFunction = Integer::valueOf;
                int[] intArray = stringStream.mapToInt(toIntegersFunction).toArray();
                for (int i : intArray) {
                    System.out.println(i);
                }
            } catch (NumberFormatException e) {
                System.out.println();
            }
        } catch (InvalidPathException e) {
            System.out.println("InvalidPathException: Enter a valid path...");
        }  catch (NoSuchFileException e) {
            System.out.println("NoSuchFileException: Enter a valid filename...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An IO Exception occurred:");
            e.printStackTrace();
        }
    }

//    Exercise 5: finally Block
//    Task: Modify the program from Exercise 1 to include a 'finally' block that always executes, printing a message to the console,
//    regardless of whether an exception was thrown or not.
//    Hint: The finally block should follow the catch block.
    public static void ex5() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            System.out.println("Enter first number: ");
            int first = scanner.nextInt();
            System.out.println("Enter second number: ");
            int second = scanner.nextInt();
            System.out.println("Quotient: " + (first / second));
        } catch (ArithmeticException e) {
            System.out.println("Arithmetic Exception: You have entered second number as 0 which leads to Infinite quotient value...");
        } catch (InputMismatchException e) {
            System.out.println("Input Mismatch Exception: Enter a valid number...");
        } catch (Exception e) {
            System.out.println("An Unexpected Error");
            e.printStackTrace();
        } finally {
            System.out.println("Finally block executed...");
            if(scanner != null) {
                scanner.close();
            }
        }
    }

//    Exercise 6: Throwing Exceptions
//    Task: Write a method that takes a string as input and checks if it is a valid email address.
//    If not, throw an IllegalArgumentException with an appropriate message.
//    Hint: Use the throw keyword to manually throw an exception when the input is invalid.
    public static void ex6() {
        Scanner scanner;
        try {
            scanner = new Scanner(System.in);
            System.out.println("Enter a string: ");
            String email = scanner.next();
            String[] emailArray = email.split("@");
            if(emailArray[0].length() < 8 || !(emailArray[1].equalsIgnoreCase("@gmail.com")))
                throw new IllegalArgumentException("Illegal Argument Exception: Enter a valid Email...");
            else
                System.out.println("You have entered a valid email address...");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

//    Exercise 7: Custom Exception for List Operations
//    Task: Create a program that manages a list of names. Implement two methods: one for finding a name in the list and another
//    for adding a name to the list.
//    •If the name cannot be found, throw a custom exception NameNotFoundException.
//    •If a duplicate name is added to the list, throw another custom exception DuplicateNameException.
    public static void ex7() {
        ArrayList<String> fruitsList = new ArrayList<>();
        fruitsList.add("Apple");
        fruitsList.add("Orange");
        fruitsList.add("Pear");
        fruitsList.add("Grapes");
        try {
            System.out.println("Initial list of fruits: " + fruitsList);
            System.out.println();

            System.out.println("IS ORANGE FOUND: " + findFruit(fruitsList, "Orange"));
            System.out.println();

//            findFruit(fruitsList, "Guava");
//            System.out.println();

            addFruit(fruitsList, "Watermelon");
            System.out.println();

            addFruit(fruitsList, "Apple");
            System.out.println();
        } catch (NameNotFoundException e) {
            System.out.println(e.getMessage() + e.getName());
        } catch (DuplicateNameException e) {
            System.out.println(e.getMessage() + e.getName());
        }
    }

    private static boolean findFruit(List<String> fruitsList, String fruit) throws NameNotFoundException {
        if(!fruitsList.contains(fruit))
            throw new NameNotFoundException("FRUIT NOT FOUND EXCEPTION: ", fruit);
        else
            return true;
    }

    private static void addFruit(List<String> fruitsList, String fruit) throws DuplicateNameException {
        fruitsList.add(fruit);
        System.out.println("New list of fruits: " + fruitsList);
        long count = fruitsList.stream().filter(each -> each.equalsIgnoreCase(fruit)).count();
        if(count > 1)
            throw new DuplicateNameException("DUPLICATE FRUIT FOUND EXCEPTION: ", fruit);
    }

//    Exercise 8: Writing to a File Using Try-with-Resources
//    Task: Create a Java application that writes a string of text to a file. Use the try-with-resources statement to ensure that
//    resources are automatically closed after the operation is complete.
//    Hint: The try-with-resources statement automatically closes resources such as file streams that implement the AutoCloseable
//    interface.
    public static void ex8() {
        try (
                BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get("files/lexicon.txt"))
//                bufferedWriter = Files.newBufferedWriter(Paths.get("files/countries.txt"))
            )
        {
            String text = "Lexicon offers Java Full Stack and Dotnet Full Stack Programs. I am doing a Java course in Lexicon. It is very useful.";
            String[] textArray = text.split("\\.");
            for (String each : textArray) {
                bufferedWriter.append(each).append(".");
                bufferedWriter.newLine();
            }
//             List<String> countriesList = Arrays.asList("Sweden", "Denmark", "Norway", "Finland");
//             for(String each : countriesList) {
//                 bufferedWriter.write(each);
//                 bufferedWriter.newLine();
//             }
             bufferedWriter.flush();
        } catch (InvalidPathException e) {
            System.out.println("InvalidPathException: Enter a valid path...");
        }  catch (NoSuchFileException e) {
            System.out.println("NoSuchFileException: Enter a valid filename...");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("An I/O EXCEPTION occurred..." + e.getMessage());
            e.printStackTrace();
        } finally {
            System.out.println("Finally block is executed!");
        }
    }
}