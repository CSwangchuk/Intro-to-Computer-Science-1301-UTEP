import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
/*
Chengay Samdrup Wangchuk
[CS1101] Comprehensive Lab 1
This work is to be done individually. It is not permitted to.
share, reproduce, or alter any part of this assignment for any
purpose. Students are not permitted to share code, upload
this assignment online in any form, or view/receive/
modifying code written by anyone else. This assignment is
part. of an academic course at The University of Texas at El
Paso and a grade will be assigned for the work produced
individually by
the student.
*/

public class CL1_Wangchuk{
	static int totalTickets = 0;
	static double total = 0;
	static double price = 0;
	static double totalCheckOut = 0;
	static double insuranceFee = 0;
	static double diff = 0;

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int choice;
		String ticket = "";
		
		do{
			// print the main menu
			introMenu();
			// prompt the user to choose
			choice  = getChoice(sc);
			//if they choose 1.Order
			if (choice == 1){
				orderMenu();
				String select = displayTicketType(ticket,sc);
				price = ticketChoice(select, sc);
			}
			if (choice == 2){
				eventPriceSummary();
			}
			if (choice == 3){
				System.out.println("Total tickets = "+ totalTickets);
				double cartTotal = calculateTotal(price);
				System.out.println("Subtotal = "+ total+"$");
				if (total == 0){
					System.out.println("No items have been picked");
					continue;
				}
			}
			if(choice == 4){
				System.out.print("Are you sure you would like to clear the contents of your cart?. ");
				String check  = sc.next();
				if (check.equalsIgnoreCase("yes")){
					total = 0;
					price = 0;
					totalTickets=0;
				} else{
					System.out.println("Cart not changed, returning to the main menu");
					continue;
				}
			}
			if (choice == 5){
				if(total!=0){
					checkOut(sc);
					printReceipt();
				}else{
					System.out.println("Cart is empty");
				}
			}
			if (choice == 6){
				System.out.println("Thank you for using Miner Ticketing System!");
				break;
			}
		}while(choice != 6);
		
	}
	//Prints the main menu
	public static void introMenu(){
		System.out.println("Hello Welcome to Miner Tickets!");

		System.out.println("1. Order");
		System.out.println("2. Events Price Summary");
		System.out.println("3. View Cart");
		System.out.println("4. Clear Cart Contents");
		System.out.println("5. Checkout");
		System.out.println("6. Exit Miner Ticket Reservation");
	}
	// Prompts the user to choose from the main menu
	public static int getChoice(Scanner sc){
		System.out.println("Choose one of the following options (1-6)");
		int choice  = sc.nextInt();
		// re-prompt the user to enter.
		while(choice < 1|| choice>6){
			System.out.println("Choose one of the following options (1-6)");
			choice  = sc.nextInt();
		}
		return choice;
	}

	/* 
	I am a beginner java programmer and you are an expert java programmer. Write me a meathod called eventPriceSummary. 
	All entries must have valid names, ticket types, and integer price values. 
	The program must have read permission for the file, and the Scanner object used inside the method should be able to read it without interruption. 
	If the file is missing or empty, an error message (“Error: tickets.txt file not found.” or “No events found in file.”) will be shown instead of crashing the program. 
	The method does not modify any external data, it only reads and displays results.
	*/
	public static void eventPriceSummary() {
	    try {
	        File file = new File("tickets.txt");
	        Scanner input = new Scanner(file);

	        int lowestPrice = Integer.MAX_VALUE;   // start high
	        int highestPrice = Integer.MIN_VALUE;  // start low
	        int totalPrice = 0;
	        int count = 0;

	        String lowestEvent = "";
	        String highestEvent = "";

	        // read all events line by line
	        while (input.hasNext()) {
	            String eventName = input.next();   // first value
	            String ticketType = input.next();  // second value
	            int price = input.nextInt();       // third value

	            // check for lowest price
	            if (price < lowestPrice) {
	                lowestPrice = price;
	                lowestEvent = eventName + " (" + ticketType + ")";
	            }

	            // check for highest price
	            if (price > highestPrice) {
	                highestPrice = price;
	                highestEvent = eventName + " (" + ticketType + ")";
	            }

	            totalPrice += price;
	            count++;
	        }

	        input.close();

	        // avoid division by zero
	        if (count == 0) {
	            System.out.println("No events found in file.");
	            return;
	        }

	        // calculate average
	        double averagePrice = (double) totalPrice / count;

	        // display results
	        System.out.println("===== Event Price Summary =====");
	        System.out.println("Lowest Priced Event : " + lowestEvent + " - $" + lowestPrice);
	        System.out.println("Highest Priced Event: " + highestEvent + " - $" + highestPrice);
	        System.out.printf("Average Price of All Events: $%.2f\n", averagePrice);

	    } catch (FileNotFoundException e) {
	        System.out.println("Error: tickets.txt file not found.");
	    } catch (Exception e) {
	        System.out.println("Error reading file data.");
	    }
	}
	// if user enters 1 prints the order section
	public static void orderMenu(){
		System.out.println("__________________________");
		System.out.println("1. Concert");
		System.out.println("2. Movie");
		System.out.println("3. Exhibition");
		System.out.println("4. Sports");
		System.out.println("5. Festival");
	}


	/*
	Then, call a method called displayTicketType(String ticket), all the
	available events within those ticket types shall be displayed, and you will
	ask the user to select an event to buy.
	*/

	public static String displayTicketType(String ticket, Scanner sc){
		
		System.out.println("Pick one of the following ticket types (1-5)");
		int orderChoice  = sc.nextInt();
		sc.nextLine();
		String type = "";
		String name ="";

		// Match the user input with a string
		if (orderChoice == 1){
			type = "Concert";
		}else if (orderChoice == 2){
			type = "Movie";
		}else if(orderChoice == 3){
			type = "Exhibition";
		}else if (orderChoice == 4){
			type = "Sports";
		}else if (orderChoice == 5){
			type = "Festival";
		}else{
			return "Error";
		}
		// reads the file and prints the events.
		try{
			File file =  new File("tickets.txt");
			Scanner input = new Scanner(file);
			System.out.println("_______________________________________");
			System.out.print("Available " + type + " Events: \n");
			boolean found = false; 

			while(input.hasNextLine()){

				//if the txt file does not have a string,exit
				if (!input.hasNext()){
					break;
				}
				name = input.next();

				//if the txt file does not have a string,exit
				if (!input.hasNext()){
					break;
				}
				String eventType = input.next();

				//if the txt file does not have a int,exit
				if (!input.hasNextInt()){
					break;
				}
				int ticketPrice = input.nextInt();

				// if the user input matches a event type from the txt file, print the events
				if(eventType.equalsIgnoreCase(type)){
					System.out.println(name +" price = "+ ticketPrice+"$");
					found = true;
					
		
				} else {
            	//If the line is incomplete or bad, skip it safely
            		if (input.hasNextLine()) {
               			input.nextLine();
            		}
            	}
			}
			input.close();

			System.out.println("What event would you like to buy tickets for? ");
			String ticketChoice = sc.nextLine();
			return ticketChoice;

		}catch (FileNotFoundException e){
			System.out.println("File Does not exist");
		}
		return"not found";
	}
	
	public static double ticketChoice(String eventName, Scanner sc){
		boolean found = false;

		try{
			File file =  new File("tickets.txt");
			Scanner input = new Scanner(file);

			while (input.hasNextLine()){
				
				if (!input.hasNext()){
					break;
				}
				String name = input.next();
				if (!input.hasNext()){
					break;
				}
				String eventType = input.next();

				if (!input.hasNextInt()){
					break;
				}
				int price = input.nextInt();

				if (eventName.equalsIgnoreCase(name)){
					System.out.println("Enter the number of tickets you would like to purchase: ");
					int ticket = sc.nextInt();
					found = true;
					totalTickets += ticket;
					total += ticket*price;
					return total;
				}
			}
			if (!found){
				System.out.println("Didnt find the event. Returning back to Main Menu");
			}

		}catch (FileNotFoundException e){
			System.out.println("File Does not exist");
		}
		return 0;
	}
	
	public static double calculateTotal(double total){
		return total;

	}

	public static void checkOut(Scanner sc){
		
		System.out.println("Tickets in cart : "+ totalTickets);
		System.out.println("Subtotal : "+ total);

		System.out.println("Would you like to add a discount code? ");
		sc.nextLine();
		String discount = sc.nextLine();

		if (discount.equals("STUDENT10")){
			diff = total*0.1;
			
		}else if(discount.equals("VIP20")){
			diff = total*0.2;
			
		}else{
			System.out.println("no discount applied");
		}

		System.out.println("Would you like to add a protection fee? ");
		String ask = sc.nextLine();

		if (ask.equalsIgnoreCase("yes")){
			System.out.println("Protection includes a $7.00 insurance fee");
			insuranceFee = 7;
			total += insuranceFee;
		}else{
			System.out.println("Protection not added");

		}
		String creditNumber = "";
		//loops the user to enter a 16 digit
		do{
			System.out.print("Please enter your credit card number(must be 16 digits) : ");
			creditNumber = sc.nextLine();
			if (creditNumber.length() ==16){
				break;
			}
		}while(creditNumber.length()!=16);
		double tax = 0.0825;
		totalCheckOut = tax*total;
		totalCheckOut = total+totalCheckOut;
		
	}
	// prints the receipt
	public static void printReceipt(){
		System.out.println("******** Receipt ********");
		System.out.println("Total tickets: "+ totalTickets);
		System.out.println("Insurance Fee : "+ insuranceFee );
		System.out.println("Total after Tax: "+ totalCheckOut);
		System.out.println("Discount : "+ diff);
		Double totalAmount = totalCheckOut- diff;
		System.out.println("Total amount : "+ totalAmount);
	}
}