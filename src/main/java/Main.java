import java.util.Scanner;

public class Main {
    public static int determineUtil(String input) {
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



    public static void main(String[] args) {

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
    }
}




