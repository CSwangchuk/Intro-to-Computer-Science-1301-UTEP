import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Pokemon{
	//attributes
	String name;
	String type;
	int level =1;
	int health;
	double attackDamage;

	//constructors
	public Pokemon(String name, String type){
		this.name = name;
		this.type = type;
	}
	public Pokemon(String name, String type, int level, int health, double attackDamage){
		this.name = name;
		this.type = type;
		this.level = level;
		this.health = health;
		this.attackDamage = attackDamage;
	}


	// setters
	public void setName(String name){
		this.name = name;
	}
	public void setType(String type){
		this.type = type;
	}
	public void setLevel(int level){
		this.level = level;
	}
	public void setHealth(int health){
		this.health = health;
	}
	public void setAttackDamage(double attackDamage){
		this.attackDamage = attackDamage;
	}

	// getters
	public String getName(){
		return this.name;
	}
	public String getType(){
		return this.type;
	}
	public int getLevel(){
		return this.level;
	}
	public int getHealth(){
		return this.health;
	}
	public double getAttackDamage(){
		return this.attackDamage;
	}

	// leveup method increases the level and makes the health and attackdamage according to the level
	public void levelUp(){
		//variables for incrementing to make it easier to change if needed 
		int healthUp = 14;
		int levelIncrease = 1;
		double attackDamageUp = 1;

		//health increases by 14 points
		this.health += healthUp;
		//level increments by 1
		this.level +=  levelIncrease;
		//attack damage increments by 1
		this.attackDamage += attackDamageUp;
	}
	// speak method prints the pokemons name twice
	public void speak(){
		//Syntax to print the name twice 
		System.out.println(this.name+", "+this.name );
	}

	// getDetails method prints all the attributes of the pokemone like name type level health and attackDamage
	public void getDetails(){
		System.out.println("***Pokemon Details***");
		System.out.println("Name: "+this.name+ "( Type: "+this.type+", Level: "+ this.level+", health: "+ this.health+", Attack Damage: "+ this.attackDamage+")");
	}

	//the evolve method changes the pokemons name to his new evolution and prints a message of 
	//its update and then increases it required attribute by required amount
	public void evolve(){
		// this reads the file Evolve and goes through it to sees the pokemons next evolution and prints
		// and intitalizes its name to the new evolution.
		try{
			//syntax to intialize the file 
			File pokeEvolve = new File("Evolve.txt");
			// syntax to intialize a scanner to go through the file
			Scanner reader = new Scanner(pokeEvolve);
			

			/*
			Update the Pokémon’s name attribute to its evolved form,
				adjust its stats ( +1 Level,
				+20 health, and +3 attack damage), and display an evolution
				message to the user. After evolving, display the Pokémon’s new stats in a formatted
				output message.

			*/
			// a while loop to ititerate through the file and check if the pokemon has the evolution.
			while (reader.hasNext()){
				// these two are the evolutions one is the current evolution and the other is the next evolution 
				String currEvolution = reader.next();
				String nextEvolution = reader.next();
				//in case pokemon is already evolved
				if(this.name.equalsIgnoreCase(nextEvolution)){
					System.out.println("Pokemon has already evolved!");
					break;
				}
				//condtion to check the name given by the user and the name in the file 
				if (this.name.equalsIgnoreCase(currEvolution)){
					// if its the same name is changed to the next evolution and updated
					this.name = nextEvolution;
					// increses the pokemons stat according to the other method
					this.level = level +1;
					this.health = health +20;
					this.attackDamage= attackDamage+3;
					// printing the message with the new pokemon name 
					System.out.println("Your pokemon has evolved to "+ this.name+"!");

					break;
				}
			}	
		} catch(FileNotFoundException e){
			System.out.println("File doesn't exist");
		}
	}
}