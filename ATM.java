import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATM {

	public static void main(String[] args) {
		new ATM().init();
	}

	public ATM() {

	}

	private void init() {
		// This could be declared globally, I guess...
		List<Integer> validBills = new ArrayList<>();

		// I'm adding highest to lowest on purpose so I can ensure the least
		// number of bills possible are provided to the user.
		validBills.add(100);
		validBills.add(50);
		validBills.add(20);
		validBills.add(10);

		// Obtain user input
		int amountRequested = getUserInput();

		// Keep track of how much is still left to hand out
		int amountRemaining = amountRequested;

		// Iterate through the list of valid bills, from highest value to lowest
		// value
		for (int bill : validBills) {
			int numOfBills = 0;
			while (amountRemaining - bill >= 0) {
				numOfBills++;
				amountRemaining -= bill;
			}
			System.out.println(numOfBills + " x $" + bill);
		}

	}

	private int getUserInput() {
		// Reads from user input
		Scanner scanner = new Scanner(System.in);

		// Validity flag
		boolean inputValid = false;

		// Stores the user's input
		int userInput = -1;

		// Loop until the user enters some valid data
		do {
			System.out.print("Enter withdrawal amount> ");

			// Check to see if the user entered an integer, as this is what we
			// require
			if (scanner.hasNextInt()) {
				userInput = scanner.nextInt();

				if (userInput < 0)
					System.out.println("Sorry, you must enter a positive value.");
				else if (userInput % 10 > 0)
					System.out.println(
							"Sorry, this ATM can only process transactions where the value is a factor of 10.");
				else
					inputValid = true;

			} else {
				System.out.println("Sorry, you must enter a whole number (integer).");

				// Let's consider the rest of the user's input, if any, as
				// garbage, and ask them for new input.
				try {
					// This looks terrible and there is probably a better way to
					// do this
					scanner.nextLine();
				} catch (Exception e) {
					// Nothing to do here.
				}
			}
		} while (inputValid == false);

		scanner.close();
		return userInput;
	}

}
