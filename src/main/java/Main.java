import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    protected static int determineUtil(String input) {
        // Returns '1' for the ColumnFormatter or '2' for the Scheduler
        // Input may be numbers directly, the utility name, or abbreviation of the utility
        // If utility can't be determined, returns 0
        input = input.toLowerCase();

        if (input.matches("[0-9]")) { // Input is only a single number
            if (Integer.parseInt(input) == 1) return 1;
            else if (Integer.parseInt(input) == 2) return 2;
            else return 0;
        }
        else if (input.contains("column")
                || input.contains("format")
                || input.equals("cf"))
            return 1;
        else if (input.contains("schedul")
                || input.equals("s"))
            return 2;
        else return 0;
    }

    protected static boolean getUserUtilChoice() {
        // Uses scanner to ask user which utility to call
        // Calls Main.determineUtil()
        // Returns true if utility is ColumnFormatter and false if utility is Scheduler
        System.out.println("For COLUMN FORMATTING, type ('1' or 'column formatting' or 'cf').");
        System.out.println("For SCHEDULING, type ('2' or 'scheduling' or 's').");

        Scanner sc = new Scanner(System.in);

        int utilFound = 0; // Equals 0 until utility is determined, then changing to 1 or 2 (ColumnFormatter or Scheduler)
        while (utilFound == 0) {
            try {
                String input = sc.nextLine();
                utilFound = determineUtil(input);

                if (utilFound != 0) { // Utility has been found
                    break;
                } else {
                    System.out.println("Unable to determine the desired tool. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.printf("You selected %s%n", utilFound == 1 ? "COLUMN FORMATTING" : "SCHEDULING");

        return utilFound == 1;
    }

    protected static ArrayList<String> readInputFile(String path) {
        // Returns a list of all lines from input text file path given
        // Returns an empty list if no file is found
        ArrayList<String> lines = new ArrayList<>();

        try {
            File f = new File(path);
            if (f.exists()) {
                lines = new ArrayList<>(Files.readAllLines(Paths.get(path)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    protected static ArrayList<String> getUserInputData(boolean isCF) {
        // Prompts user for file path, passes that path into Main.readInputFile(), and returns the result
        Scanner sc = new Scanner(System.in);

        System.out.printf("Please enter the absolute path to the text file you'd like to %s%n",
                isCF ? "format into columns." : "schedule.");
        String path = sc.nextLine();

        return readInputFile(path);
    }

    protected static ArrayList<String> runColumnFormatter(ArrayList<String> inputData) {
        /*

        ########################################### ColumnFormatter call here ###########################################

         */
        // Takes in input data as ArrayList,
        // Does colum formatting operations using ColumnFormatter object,
        // And outputs the resulting data in a new ArrayList
        Scanner sc = new Scanner(System.in);

        System.out.println("How many columns would you like to format your data into?");

        int numCol = 0;
        String input;
        while (numCol < 1) { // If input is not a single number or is less than 1, ask user to try again
            input = sc.nextLine();
            if (input.matches("[0-9]+")) {
                numCol = Integer.parseInt(input);
            }
            if (numCol < 1) System.out.println("Invalid input, please enter a whole number of columns of 1 or greater");
        }

        ColumnFormatter formatter = new ColumnFormatter(inputData, numCol);

        return formatter.getSetAsList();
    }

    protected static ArrayList<String> runScheduler(ArrayList<String> inputData) {
        /*

        ########################################### Scheduler call here ###########################################

         */
        // Takes input data as ArrayList,
        // Does scheduler operations using Scheduler object,
        // And outputs the resulting data in a new ArrayList
        Scanner sc = new Scanner(System.in);

        Scheduler sched = new Scheduler();

        ArrayList<String> result = new ArrayList<>();

        return result;
    }

    public static void main(String[] args) {

        boolean isCF = getUserUtilChoice(); // True if user requested ColumnFormatter, false if Scheduler

        ArrayList<String> inputData = getUserInputData(isCF);
        ArrayList<String> outputData;

        if (isCF) outputData = runColumnFormatter(inputData);
        else outputData = runScheduler(inputData);

        System.out.println("Your new data:\n");

        for (String line : outputData) {
            System.out.println(line);
        }

    }
}




