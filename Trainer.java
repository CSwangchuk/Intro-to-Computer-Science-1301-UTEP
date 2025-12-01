import java.util.Scanner;

public class Trainer{
	//attributes
	String name;
	//the pokemon team has the max size of 6 
	//so the array should only take in 6 pokemons
	Pokemon[] pokemonTeam = new Pokemon[6];
	//the badges max limit is 5 hence the size should only be 5
	String[] badges = new String[5];
	// the trainer will only be champion if he has all badges 
	//so the iniital statement that he is a champion wil be false
	boolean isPokemonChampion = false;
	Pokemon partner;

	//Constructors
	public Trainer(String name){
		this.name = name;
	}
	public Trainer(String name, Pokemon[] pokemonTeam, String[] badges, boolean isPokemonChampion){
		this.name = name;
		this.pokemonTeam = pokemonTeam;
		this.badges = badges;
		this.isPokemonChampion = isPokemonChampion;
	}

	//setters
	public void setName(String name){
		this.name = name;
	}
	public void setPokemonTeam(Pokemon[] pokemonTeam){
		this.pokemonTeam = pokemonTeam;
	}
	public void setBadges(String[] badges){
		this.badges = badges;
	}
	public void setPokemonChampion(boolean isPokemonChampion){
		this.isPokemonChampion = isPokemonChampion;
	}
	//this setter takes in the name of the pokemon and checks if the pokemon is in the team
	// if its not there then it prints not found and if its there it sets the partner to the pokemon
	public void setPartner(String name){
		// boolean to check if the pokemon is there in the team
		boolean found = false;
		// for loop to go through the team and check if the name is there in the team
		for(int i = 0; i<pokemonTeam.length;i++){
			//condition to check if name is there
			if (pokemonTeam[i]!=null && name.equalsIgnoreCase(pokemonTeam[i].getName())){
				System.out.println("Pokemon found");
				this.partner = pokemonTeam[i];
				System.out.println(this.partner.getName()+ " is now your partner!");
				found = true;
				break;
			}
		}
		// this is the condition to see if pokemon is there in the team or not
		if(!found){
			System.out.println("Pokemon not found ");
		}
	}

	//getter
	public String getName(){
		return this.name;
	}
	public Pokemon[] getPokemonTeam(){
		return this.pokemonTeam;
	}
	public String[] getBadges(){
		return this.badges;
	}
	public boolean getPokemonChampion(){
		return this.isPokemonChampion;
	}
	public Pokemon getPartner(){
		return this.partner;
	}

	//this method adds pokemon to the pokemon array in the trainers
	// if the team is full it does not add any and if its empty it adds 
	// the pokemon to the team
	public void addPokemon(Pokemon p){
		// boolean added to confirm that pokemon p has been added 
		// this also relates to whether the team is full or not.
		boolean added = false;
		//loop to go through the team 
		for(int i =0; i<pokemonTeam.length; i++){
			// if the team at the current index is empty 
			// add the pokemon p to that index 
			if(pokemonTeam[i]==null){
				pokemonTeam[i]= p;
				added=true;
				break;
			}
		}
		// if pokemon p is not added = team is full 
		//print team is full
		if(!added){
			System.out.println("The pokemon team is full, please remove to add pokemon");
		}
	}

	public void removePokemon(String name){
		/*
		go through the pokemon team 
			if name matches pokemon at index and is not null
				remove pokemon (turn it into null?)
		if pokemon not found print not found 
		*/

		// boolean to check if found or not
		boolean found = false;
		// loop to go through the pokemon team
		for(int i =0; i<pokemonTeam.length;i++){
			// if the pokemon team at index is not null and the name is same as the name
			// of the pokemon at that index
			if(pokemonTeam[i]!=null && name.equalsIgnoreCase(pokemonTeam[i].getName())){
				// set the pokemon at the index to null meaning remove it 
				pokemonTeam[i]= null;
				System.out.println(name + " has been released");
				found = true;
				break;
			}
		}
		// if not found prints the message.
		if(!found){
			System.out.println("Pokemon not found");
		}
	}

	// this method trains all the pokemon in the trainers team.
	public void trainPokemon(){
		/*
		go through the pokemon team 
			check if its null or not
			level up each pokemon 
		How
		using the pokemon class levelup method
		*/

		//loop throught the pokemon team
		for (int i = 0; i<pokemonTeam.length;i++){
			// if its null skip that index and move on 
			if(pokemonTeam[i]!=null){
				// levelup the pokemon at that index
				pokemonTeam[i].levelUp();
			}
		}
	}

	// this method prints the details of the trainer
	// in formatted string 
	public void getDetails(){
		/*
		print all the details of the trainer 
		for the pokemon team only print the pokemon name 
		if no partner no need to print its details 
		*/

		//prints the name, whether trainer is champion 
		System.out.println("Trainer name : "+this.name+"\nIs Champion? : "+ this.isPokemonChampion);
		//checks if trainer has a partner if they have then prints the partners name.
		if(partner!=null){
			System.out.println("\nPartner: "+ this.partner.getName());
		}
		System.out.println("Badges: ");
		// prints the badges.
		for(int i = 0; i<badges.length;i++){
			if(badges[i]!=null){
				System.out.println((i+1)+". "+ badges[i]);
			}
		}
		System.out.println("Pokemon Team: ");
		//loops and prints the pokemons in the trainers team
		for(int i =0; i<pokemonTeam.length;i++){
			// checks whether it is null or not 
			if (pokemonTeam[i]!=null){
				System.out.println((i+1)+". " + pokemonTeam[i].getName());
			}
		}
	}

	public void choosePartner(Scanner input){
		/*
		this method is supposed to let the user 
		choose their partner from the trainers team.
		introduce a scanner 
		ask the user to choose a pokemon 
		check if pokemon is in the team (calling the setPartner)
		*/ 
		//ask the user their partner choice
		System.out.print("Enter the pokemon you want as your partner: ");
		String partnerName = input.nextLine();
		// call the setPartner and done.
		setPartner(partnerName);
	}


}