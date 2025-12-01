/* Chengay Samdrup Wangchuk
[CS1101] Comprehensive Lab 3
This work is to be done individually. It is not
permitted
to. share, reproduce, or alter any part of this
assignment
for any purpose. Students are not permitted
to share code,
upload this assignment online in any form, or
view/receive/ modifying code written by
anyone else. This
assignment is part of an academic course at
The University
of Texas at El Paso and a grade will be
assigned for the
work produced individually by the student.
*/
// importing necessary java packages
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.util.Random;

// creating the main class
public class CL2wangchuk{
	//the main method
	public static void main(String[] args){
		// introducing a new randomizer 
		Random randomizer = new Random();
		// introducing a new scanner 
		Scanner input = new Scanner(System.in);
		// setting the choice to zero to execute the code
		int choice = 0;
		// calling the other classes by setting a new variable equal to that 
		Pokemon[] pokedex = new Pokemon[20];
		Region region = new Region("Kanto","Warm",1);
		// uisng try and catch to read the pokedex
		try{
		 	File pokeFile = new File("pokedex.txt");
		 	Scanner reader = new Scanner(pokeFile);
			int index = 0;
			// as long as the file has words
		 	while (reader.hasNext()){
		 		// setting the first text to name
		 		String name = reader.next();
		 		// the second to type
		 		String type = reader.next();
		 		// the third which is an integer to a number to health
		 		int health = reader.nextInt();
		 		// the fourth which could change to a double to attack damage
		 		double attackDamage = reader.nextDouble();
		 		// setting the level to 1 as all pokemons start with this
		 		int level =1;
		 		//filling up the pokedex with Pokemon classes
		 		pokedex[index] = new Pokemon(name,type,level,health,attackDamage);
		 		// increasing the index to ensure all index are filled;
		 		index++;
		 	}
		 	// closing the reader to ensure nothing flows out.
		 	reader.close();
		// making the catch statement
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		// using the do while loop to atleast go through the process once.
		do {
			// printing the main menu
			System.out.println("******************************\n" + 
			"* Welcome to Poke-Miner * "+
			"\n******************************");
			System.out.println("Options");
			System.out.println(
   			 "**************************\n" +
    		"1. Show Everything in Region\n" +
    		"2. Add/Remove Trainer to Region\n" +
    		"3. Add/Remove Wild Pokémon to Region\n" +
    		"4. Modify Trainer\n" +
    		"5. Add/remove Pokémon from Trainer\n" +
    		"6. List Pokémon in Trainer\n" +
    		"7. Evolve Trainers' Pokémon\n" +
    		"8. Stimulate Interaction\n" +
    		"9. Exit\n" +
    		"**************************"
			);
			// asking for user input 
			System.out.print("Select Option: ");
			choice = input.nextInt();
			input.nextLine();

			//using conditions to see if the user input matches the option values.
			//if choice is 1
			if (choice == 1){
				// calls the method from the region 
				region.describeRegion();
			// if choice is 2
			}else if (choice == 2){
				// askes users whether they would like to add or remove a trainer 
				System.out.println("Would you like to add or remove a trainer to/from the region?(add/remove) ");
				String userAddRemove = input.nextLine();
				// if they type add proceed inside this
				if(userAddRemove.equalsIgnoreCase("add")){
					//askes for trainer name 
					System.out.print("Trainer Name : ");
					String trainerName = input.nextLine();
					//creates a new trainer;
					Trainer newTrainer = new Trainer(trainerName);
					// adds them to the Trainer array (check region class method addTrainer for the process).
					region.addTrainer(newTrainer);

				// if the user types remove 
				}else if (userAddRemove.equalsIgnoreCase("remove")){
					// checks if there is any trainer in the region
					boolean found = false;
					for (int i=0; i <region.trainerInRegion.length; i++){
						if(region.trainerInRegion[i]!=null){
							found = true;
							break;
						}
					}
					//if there are no trainers then goes inside this and prints the message
					if(!found){
						System.out.println("No trainers in the region yet");
					// otherwise goes inside this and askes for username
					}else{

						System.out.print("Trainer Name: ");
						String trainerName = input.nextLine();
						// calls method from region(check region class remove trainer method for the process)
						region.removeTrainer(trainerName);
					}

				// if they type somtheing else then prints the message and goes back to main menu
				}else{
					System.out.println("Wrong input redirecting back to main menu ......");
					
				}

			// if they opt 3 does this 	
			}else if (choice ==3){
				//askes user if they would like to add or remove a wild pokemon in the region
				System.out.print("Would you like to add or remove wild Pokemon to/from the region? ");
				String userAddRemove = input.nextLine();
				// if they type add
				if (userAddRemove.equalsIgnoreCase("add")){
					// randomly selects a numver ranging from 0 to the pokedex array length-1
					int randomNum = randomizer.nextInt(0,pokedex.length);
					// if the pokedex isn't null
					if (pokedex[randomNum]!=null){
						// makes a copy to store the pokemon
						Pokemon pokeCopy = pokedex[randomNum];
						// initialize a new pokemon using the details of the pokecopy
						Pokemon newPoke = new Pokemon(pokeCopy.getName(),pokeCopy.getType(),pokeCopy.getLevel(), pokeCopy.getHealth(), pokeCopy.getAttackDamage());
						// adds the wild pokemon to the region (check the region class addWildPokemon method for the process)
						region.addWildPokemon(newPoke);
					// otherwise the pokedex is empty
					}else{
						System.out.println("Pokedex is empty");
					}
				//if the user type remove 
				}else if (userAddRemove.equalsIgnoreCase("remove")){
					// check if there are any wild pokemons in the region
					boolean hasPokemon = false;
					Pokemon[] wild = region.getWildPokemoninRegion();
					for(int i = 0; i<wild.length;i++){
						if(wild[i]!=null){
							hasPokemon = true;
							break;
						}
					}
					// if there is no pokemon then prints this message
					if(!hasPokemon){
						System.out.println("No pokemon in the region yet");
					// otherwise executes this 
					}else{
						//askes for the name of the wild pokemon 
						System.out.print("Wild Pokemon: ");
						String name = input.nextLine();
						//calls the region class removeWildPokemon class which returns a boolean value
						boolean removed = region.removeWildPokemon(name);
						// if its true we know that the process was a success 
						if(removed){
							System.out.println(name + " has fled the region");
						// otherwise we didn't find the pokemon
						}else{
							System.out.println("Pokemon not found");
						}
					}
				// otherwise go back to the main menu
				}else{
					System.out.println("Invalid option! Please type 'add' or 'remove'. Returning to menu.");
				}
			//if choice is 4 goes in this
			}else if(choice == 4){
			    Trainer[] trainer = region.getTrainerInRegion(); 

			    // check if there is at least one trainer
			    boolean hasTrainer = false;
			    for (int i = 0; i < trainer.length; i++) {
			        if (trainer[i] != null) {
			            hasTrainer = true;
			            break;
			        }
			    }
			    //if no trainer then doesthis
			    if (!hasTrainer) {
			        System.out.println("There are no trainers in this region yet.");
			    // other wise goes in this
			    } else {
			    	//askes for the trainer name 
			        System.out.print("Which Trainer? ");
			        String name = input.nextLine();
			        Trainer target = null;
			        //goes through the trainer array
			        for (int i = 0; i < trainer.length; i++){
			        	// if the trainer at that index is not null and their name matches
			            if (trainer[i] != null && name.equalsIgnoreCase(trainer[i].getName())){
			            	// we set the target equal to that trainer at  that index
			                target = trainer[i];
			                break; // stop once we find the trainer
			            }
			        }
			        // if the trainer not found then does this 
			        if (target == null) {
			            System.out.println("Trainer not found in region.");
			        // otherwise does this
			        } else {

			            // print trainer info first
			            System.out.println("Current trainer information:");
			            target.getDetails();
			            // askes what they would like to Modify
			            System.out.println("Would you like to modify name, champ status, partner or continue? ");
			            String userInput = input.nextLine();
			            // if name then
			            if (userInput.equalsIgnoreCase("name")){
			            	// askes for the new name 
			                System.out.print("New Trainer name : ");
			                String newName = input.nextLine();
			                // uses the setter in the trainer class
			                target.setName(newName);
			                System.out.println("Trainer's name has been successfully changed to " + newName);
			                // prints the new details
			                target.getDetails();
			            //if champ status
			            } else if(userInput.equalsIgnoreCase("champ status")){
			            	// check the current boolean value of the trainer by using getter
			                boolean check = target.getPokemonChampion();
			                // using the setter in the trainer class make it to opposite of the original 
			                target.setPokemonChampion(!check);
			                // print the new details
			                target.getDetails();
			            //if partner
			            } else if (userInput.equalsIgnoreCase("partner")){
			            	// call the choosepartner method
			                target.choosePartner(input);
			                // print the new details
			                target.getDetails();
			            // if continue
			            } else if (userInput.equalsIgnoreCase("continue")){
			            	// go back to main menu
			                System.out.println("Going back to main menu");
			            // otherwise tell them and go back to the main menu
			            } else{
			                System.out.println("Please enter a correct option. Going back to main menu.");
			            }
			        }
			    }
			// if choice is 5
			}else if(choice == 5){
			    // check if there is at least one trainer in the region
			    Trainer[] trainer = region.getTrainerInRegion();
			    boolean hasTrainer = false;
			    for (int i = 0; i < trainer.length; i++) {
			        if (trainer[i] != null) {
			            hasTrainer = true;
			            break;
			        }
			    }
			    // if no trainer then this 
			    if (!hasTrainer) {
			        System.out.println("There are no trainers in this region yet.");
			    // otherwise this 
			    } else {
			    	//ask which trainer 
			        System.out.print("Which trainer? : ");
			        String trainerName = input.nextLine();
			        Trainer target = null;
			        //set the trainer at the matching index to target
			        for (int i = 0; i < trainer.length; i++) {
			            if (trainer[i] != null && trainerName.equalsIgnoreCase(trainer[i].getName())) {
			                target = trainer[i];
			                break;
			            }
			        }
			        // if no trainer then this 
			        if(target == null){
			            System.out.println("Trainer not found");
			        // else does this 
			        }else{
			        	//askes whether they would lilke to add or remove a pokemon
			            System.out.print("Would you like to add or remove pokemon? ");
			            String ask = input.nextLine();
			            // if the type add
			            if(ask.equalIgnoreCase("add")){
			            	// gets a random wild pokemon 
			                Pokemon random = region.generateWildPokemon();
			                // if no pokemon then this
			                if (random == null) {
			                    System.out.println("There are no wild Pokémon in the region.");
			                // otherwise does this 
			                } else {
			                	// adds the random pokeom to the trainers team
			                    target.addPokemon(random);
			                    // prints their details 
			                    random.getDetails();
			                    // prints a message 
			                    System.out.println("Pokemon " + random.getName()
			                            + " has been added to trainer " + target.getName() + "'s team");
			                }
			            // if they chooese remove then this 
			            }else if (ask.equalsIgnoreCase("remove")){
			            	// check if there is a pokemon in the team
			            	boolean hasPokemon = false;
			            	Pokemon[] poke = target.getPokemonTeam();
			            	for(int i = 0; i<poke.length;i++){
			            		if(poke[i]!=null){
			            			hasPokemon = true;
			            			break;
			            		}
			            	}
			            	// if no then this 
			            	if(!hasPokemon){
			            		System.out.println("Trainer has no pokemon in the team");
			            	// otherwise askes them which pokemon to remove 
			            	}else{
				                System.out.print("Which pokemon would you like to remove? ");
				                String pokeName = input.nextLine();
				                // removes that pokemon 
				                target.removePokemon(pokeName);
				            }
				        // if not the two option then gives this message 
			            }else{
			                System.out.println("Wrong input. Redirecting back to main menu.");
			            }
			        }
			    }
			// if choice is 6
			}else if (choice==6){
				// checks if there is trainer 
				boolean hasTrainer = false;
				Trainer[] trainers = region.getTrainerInRegion();
				Trainer trainer = null;
				for (int i =0;i<trainers.length;i++){
					if(trainers[i]!=null){
						hasTrainer= true;
						break;
					}
				}
				//if no trainer then this 
				if(!hasTrainer){
					System.out.println("No trainer has been added to the region ");
				// if trainer there then this 
				}else{
					// askes the trainers name 
					System.out.print("Which trainers team do you want to see? ");
					String name = input.nextLine();
					// sets the trainer if the name matches 
					for (int i =0; i<trainers.length;i++){
						if (trainers[i] != null && name.equalsIgnoreCase(trainers[i].getName())){
							trainer = trainers[i];
							break;
						}
					}
					// checks if the trainer has a team 
					boolean hasTeam =false;
					// if no trainer then this 
					if(trainer==null){
						System.out.println("Trainer not found");
					// else this 
					}else{
						// calls the traines team by doing this 
						Pokemon[] pokemonT = trainer.getPokemonTeam();
						// goes through the trainers team and prints their details
						for (int i =0; i<pokemonT.length; i++){
							if (pokemonT[i]!=null){
								System.out.print((i+1)+". ");
								pokemonT[i].getDetails();
								hasTeam=true;
							}
						}
						// if team empty then this
						if(!hasTeam){
							System.out.println("Trainer "+name +" does not have any pokemon in their team");
						}
					}
				}
			// if choice 7
			}else if (choice == 7){
			    Trainer[] trainers = region.getTrainerInRegion();

			    // Check if there is at least one trainer in the region
			    boolean hasTrainer = false;
			    for (int i = 0; i < trainers.length; i++) {
			        if (trainers[i] != null) {
			            hasTrainer = true;
			            break;
			        }
			    }
			    // if no trainer then this 
			    if (!hasTrainer) {
			        System.out.println("There are no trainers in this region yet.");
			    // if trainer there then 
			    } else {
			    	// ask trainer name 
			        System.out.println("Which trainer? ");
			        String name = input.nextLine();
			        // loop through the array of trainers to see if any matches 
			        Trainer trainer = null;
			        for (int i = 0; i < trainers.length; i++) {
			            if (trainers[i] != null && name.equalsIgnoreCase(trainers[i].getName())) {
			            	// set trainer to that trainer at that index
			                trainer = trainers[i];
			                break;
			            }
			        }
			        //if there is no trainer then this 
			        if (trainer == null) {
			            System.out.println("Trainer not found");
			        // otherwise does this 
			        } else {
			        	// initializes a pokemonteam from the trainer class 
			            Pokemon[] pokemonTeam = trainer.getPokemonTeam();
			            // creates a pokemon with null values;
			            Pokemon evoPoke = null;
			            // prints the trainer details 
			            trainer.getDetails();
			            //askes the pokemon they want to evolve
			            System.out.println("Which pokemon do you want to evolve? ");
			            String pokeName = input.nextLine();
			            // check whether the name matches the index
			            boolean found = false;
			            for (int i = 0; i < pokemonTeam.length; i++) {
			                if (pokemonTeam[i] != null &&
			                    pokeName.equalsIgnoreCase(pokemonTeam[i].getName())) {
			                	// if matching then set the null pokemon equal to that pokemon
			                    evoPoke = pokemonTeam[i];
			                    evoPoke.evolve();   // does the evolve + message
			                    found = true;
			                    break;
			                }
			            }
			            // if no pokemon not here 
			            if (!found) {
			                System.out.println("Pokemon not found in this trainer's team.");
			            // otherwise prints their new stats
			            } else {
			                System.out.println("New stats after evolution:");
			                evoPoke.getDetails();
			            }
			        }
			    }
			// if choice is 8
			}else if (choice == 8){
			    Trainer[] trainers = region.getTrainerInRegion();

			    // Count how many trainers exist in the region
			    int trainerCount = 0;
			    for (int i = 0; i < trainers.length; i++) {
			        if (trainers[i] != null) {
			            trainerCount++;
			        }
			    }
			    // if there are less than 2 then prints this message 
			    if (trainerCount < 2) {
			        System.out.println("There are not enough trainers in the region for a battle.");
			    // otherwise does this
			    } else {
			    	// sets to trainers with null values
			        Trainer trainerA = null;
			        Trainer trainerB = null;
			        // askes user to enter name for the two trainers
			        System.out.print("Enter the name of the first trainer: ");
			        String trainer1 = input.nextLine();

			        System.out.print("Enter the name of the second trainer: ");
			        String trainer2 = input.nextLine();
			        // loops through the trainer array and check if the two given names exist 
			        for (int i = 0; i < trainers.length; i++){
			            if (trainers[i] != null && trainer1.equalsIgnoreCase(trainers[i].getName())){
			                trainerA = trainers[i];
			            }
			            if (trainers[i] != null && trainer2.equalsIgnoreCase(trainers[i].getName())){
			                trainerB = trainers[i];
			            }
			        }
			        //if they don't exist the prints this 
			        if (trainerA == null || trainerB == null){
			            System.out.println("Trainer not found in the region, please try again.");
			        // otherwise does this 
			        } else {
			            // avoid battling the same trainer vs themselves, 
			            if (trainerA == trainerB) {
			                System.out.println("Please choose two different trainers for the battle.");
			            } else {
			            	// engages in battle 
			                region.stimulateInteraction(trainer1, trainer2);
			            }
			        }
			    }
			// if choice is anything other than 1-8 then prints this 
			}else{
				System.out.println("Please enter a valid option");
			}

		}while(choice!=9);
		


	}
}