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

    protected static List<String> readInputFile(String path) {
        // Returns a list of all lines from input text file path given
        // Returns an empty list if no file is found
        List<String> lines = new ArrayList<>();

        try {
            File f = new File(path);
            if (f.exists()) {
                lines = Files.readAllLines(Paths.get(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    protected static List<String> getUserInputData(boolean isCF) {
        Scanner sc = new Scanner(System.in);

        System.out.printf("Please enter the absolute path to the text file you'd like to %s%n",
                isCF ? "format into columns." : "schedule.");
        String path = sc.nextLine();

        return readInputFile(path);
    }



    public static void main(String[] args) {

       boolean isCF = getUserUtilChoice(); // True if user requested ColumnFormatter, false if Scheduler

       List<String> inputData = getUserInputData(isCF);
    }
}
