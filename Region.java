import java.util.Random;
public class Region{
	// Attributes
	String name;
	String climate;
	int difficulty;
	Trainer[] trainerInRegion = new Trainer[10];
	Pokemon[] wildPokemoninRegion = new Pokemon[20];

	//Constructor
	public Region(String name,String climate,int difficulty){
		this.name = name;
		this.climate = climate;
		this.difficulty = difficulty;
	}
	public Region(String name, String climate, int difficulty, Trainer[] trainerInRegion){
		this.name = name;
		this.climate=climate;
		this.difficulty=difficulty;
		this.trainerInRegion = trainerInRegion;
	}

	//setters
	public void setName(String name){
		this.name = name;
	}
	public void setClimate(String climate){
		this.climate = climate;
	}
	public void setDifficulty(int difficulty){
		this.difficulty = difficulty;
	}
	public void setTrainerInRegion(Trainer[] trainerInRegion){
		this.trainerInRegion = trainerInRegion;
	}
	public void setWildPokemoninRegion(Pokemon[] wildPokemoninRegion){
		this.wildPokemoninRegion = wildPokemoninRegion;
	}

	//getters
	public String getName(){
		return this.name;
	}
	public String getClimate(){
		return this.climate;
	}
	public int getDifficulty(){
		return this.difficulty;
	}
	public Trainer[] getTrainerInRegion(){
		return this.trainerInRegion;
	}
	public Pokemon[] getWildPokemoninRegion(){
		return this.wildPokemoninRegion;
	}

	// adds a trainer in the trainer array 
	public void addTrainer(Trainer t){
		// boolean to make sure appropriate messages are printed
		boolean added = false;
		// loops through the array of trainers
		for (int i = 0; i<trainerInRegion.length; i++){
			// if there is an empty space adds the trainer
			if(trainerInRegion[i]==null){
				trainerInRegion[i] = t;
				System.out.println("Trainer "+trainerInRegion[i].getName()+" has been added");
				added = true;
				break;
			}
		}
		// if the array is full prints this message
		if (!added){
			System.out.println("Trainer not added, trainer slot full, remove a trainer");
		}
	}

	//removes a trainer from the region 
	public void removeTrainer(String name){
		/*
		goes through the array of trainers 
			if the name matches one of the trainers in the array
				removes the trainer (trainer at that plaee =null)
		if trainer isn't there print trainer not found 
		*/
		boolean found = false;
		for(int i = 0; i<trainerInRegion.length;i++){
			if (trainerInRegion[i]!=null&& name.equalsIgnoreCase(trainerInRegion[i].getName())){
				System.out.println("Trainer "+trainerInRegion[i].getName()+" has left the region");
				trainerInRegion[i] = null;
				found = true;
				break;
			}
		}
		if (!found){
			System.out.print("Trainer not found.");
		}
	}

	// returns a random wild pokemon from the pokemon array
	public Pokemon generateWildPokemon(){
		/*
		returns a random pokemon from the wildPokemon array 
		how 
		goes through the wildPokemon array and randomly selects an index 
			if the index is not null
				select that pokemon
		*/
		// intializes the random object
		Random randomizer = new Random();
		boolean hasPokemon = false;
		// checks if the region is empty or not 
		for(int i =0; i<wildPokemoninRegion.length;i++){
			if(wildPokemoninRegion[i]!=null){
				hasPokemon = true;
				break;
			}
		}
		if(!hasPokemon){
			System.out.println("Region is empty");
			return null;
		}
		// goes thorugh and generates a random number and returns a pokemon
		while(true){
			// generates a random number from range 0 to wildPokemoninRegion.length-1
			int randomNum = randomizer.nextInt(0,wildPokemoninRegion.length);
			// if the pokemon is there at that index 
			if(wildPokemoninRegion[randomNum]!=null){
				//making a new variable to store the pokemon
				Pokemon chosen = wildPokemoninRegion[randomNum];
				//removing the pokemon from the wild pokemon list
				wildPokemoninRegion[randomNum] =null;
				// return that pokemon
				return chosen;
			}
		}
	}
	// ChatGPT prompt
	/*
	I am a beginner java programmer and you are an expert java programmer 
	make me a method public void describeRegion() 
	After the method executes,the region’s climate, difficulty, trainers, 
	and wild Pokémon are printed to the console.
	If any detail is missing or empty, the method prints an appropriate message instead. 
	Trainers and wild pokemon may contain null values 
	and the region object must be created.
	*/
	public void describeRegion() {

	    System.out.println("=== Region Details ===");

	    // Region name
	    if (name != null && !name.isEmpty()) {
	        System.out.println("Region Name: " + name);
	    } else {
	        System.out.println("Region Name: (not available)");
	    }

	    // Climate
	    if (climate != null && !climate.isEmpty()) {
	        System.out.println("Climate: " + climate);
	    } else {
	        System.out.println("Climate: (not available)");
	    }

	    // Difficulty
	    System.out.println("Difficulty: " + difficulty);

	    // Trainers
	    System.out.println("\n--- Trainers in Region ---");
	    boolean hasTrainer = false;
	    for (int i = 0; i < trainerInRegion.length; i++) {
	        if (trainerInRegion[i] != null) {
	            hasTrainer = true;
	            System.out.println((i + 1) + ". " + trainerInRegion[i].getName());

	            // Print that trainer's team (just names)
	            Pokemon[] team = trainerInRegion[i].getPokemonTeam();
	            boolean hasTeam = false;
	            StringBuilder teamNames = new StringBuilder();
	            if (team != null) {
	                for (int j = 0; j < team.length; j++) {
	                    if (team[j] != null) {
	                        if (hasTeam) teamNames.append(", ");
	                        teamNames.append(team[j].getName());
	                        hasTeam = true;
	                    }
	                }
	            }
	            if (hasTeam) {
	                System.out.println("   Pokémon Team: " + teamNames.toString());
	            } else {
	                System.out.println("   Pokémon Team: (no Pokémon)");
	            }
	        }
	    }
	    if (!hasTrainer) {
	        System.out.println("No trainers found in this region.");
	    }

	    // Wild Pokémon
	    System.out.println("\n--- Wild Pokémon in Region ---");
	    boolean hasWild = false;
	    for (int i = 0; i < wildPokemoninRegion.length; i++) {
	        if (wildPokemoninRegion[i] != null) {
	            hasWild = true;
	            Pokemon p = wildPokemoninRegion[i];
	            System.out.println((i + 1) + ". " + p.getName()
	                    + " (Type: " + p.getType()
	                    + ", Level: " + p.getLevel()
	                    + ", Health: " + p.getHealth()
	                    + ", Attack Damage: " + p.getAttackDamage() + ")");
	        }
	    }
	    if (!hasWild) {
	        System.out.println("No wild Pokémon found in this region.");
	    }

	    System.out.println("=========================\n");
	}

	//Extra Credit 
	public void stimulateInteraction(String trainerA, String trainerB){
		// booleans to use to check if trainer has partner
		boolean hasPartnerA = false;
		boolean hasPartnerB = false;
		// initializing two trainer variables to use it to access the pokemons details
		Trainer personA =null;
		Trainer personB = null;
		// initializing two types to make this into the partner pokemons types
		String typeB="";
		String typeA="";

		//for loop to check through the trainer region array and see if the name matches trainer in the region
		for(int i =0; i<trainerInRegion.length;i++){
			// to make sure that the array is not null
			if(trainerInRegion[i]!= null){
				//if it matches then one trainer variable is equal to that
				if(trainerA.equalsIgnoreCase(trainerInRegion[i].getName())){
					personA = trainerInRegion[i];
					//if the trainer has partner then type is set to the type of pokemon
					if (personA.getPartner() != null){
						typeA = personA.getPartner().getType();
						hasPartnerA = true;
					}
				}
			}
		}
		// if the trainer has no partner then this message is printed
		if(!hasPartnerA){
			System.out.println("Trainer "+trainerA+" does not have a partner");
		}

		// same as above but for the second trainer 
		for(int i =0; i<trainerInRegion.length;i++){
			if (trainerInRegion[i]!= null){
				if(trainerB.equalsIgnoreCase(trainerInRegion[i].getName())){
					personB = trainerInRegion[i];
					if(personB.getPartner()!= null){
						typeB = personB.getPartner().getType();
						hasPartnerB = true;
					}
				}
			}
		}
		if(!hasPartnerB){
			System.out.println("Trainer "+trainerB+" does not have a partner");
		}

		// dont fight if no partner
		if(!hasPartnerB||!hasPartnerA){
			System.out.println("Trainer "+ trainerA +" says Hi! to trainer "+ trainerB);
			System.out.println("Trainer "+ trainerB +" says Hello! to trainer "+ trainerA);
			return;
		}
		//variables to adjust the damage according to the type 
		double x1 = 1;
		double x2 = 2;
		double xhalf = 0.5;

		// intializing the damage and health of the two pokemons 
		double damagePokeA = personA.getPartner().getAttackDamage();
		double damagePokeB = personB.getPartner().getAttackDamage();
		int orgHealthA = personA.getPartner().getHealth();
		int orgHealthB = personB.getPartner().getHealth();

		// checking the types they are against 
		// if they are same then damage is same 
		if(typeA.equalsIgnoreCase(typeB)){
			// normal damage
			damagePokeA = damagePokeA * x1;
			damagePokeB = damagePokeB * x1;
		// else if trainer A type is weak against trainer B then A will have half damage and B will have x2 damage
		} else if ((typeA.equalsIgnoreCase("fire") && typeB.equalsIgnoreCase("water")) ||
					(typeA.equalsIgnoreCase("water") && typeB.equalsIgnoreCase("grass")) ||
					(typeA.equalsIgnoreCase("grass") && typeB.equalsIgnoreCase("fire"))){
			// half damage for A 
			damagePokeA = damagePokeA * xhalf;
			// twice damage for B 
			damagePokeB = damagePokeB * x2;
		// otherwise we know that it wil be the opposite 
		} else {
			// twice damage for A 
			damagePokeA = damagePokeA * x2;
			// half damage for B
			damagePokeB = damagePokeB * xhalf;
		}
		// make a copy of their health so that we can reset it when needed 
		double healthA = (double)orgHealthA;
		double healthB = (double)orgHealthB;

		System.out.println("Stimulating interaction between two trainers....");
		System.out.println("Stimulating interaction between two trainers....");

		// loop to keep the battle going 
		while (healthA>0&&healthB>0){
			// A attacks B
			healthB = healthB - damagePokeA;
			System.out.print(personA.getName()+" uses "+personA.getPartner().getName()+ "(level: "+personA.getPartner().getLevel()+")" + " to speak ");
			personA.getPartner().speak();
			System.out.println(personB.getPartner().getName()+" health reaches "+ healthB);
			//check if B is dead
			if(healthB<=0){
				System.out.println("Trainer "+ trainerA +" has won the battle" );
				// reseting the health
				personA.getPartner().setHealth(orgHealthA);
				personB.getPartner().setHealth(orgHealthB);
				break;
			}
			//b attacks a 
			healthA = healthA - damagePokeB;
			System.out.print(personB.getName()+" uses "+personB.getPartner().getName()+ "(level: "+personB.getPartner().getLevel()+")" + " to speak ");
			personB.getPartner().speak();
			System.out.println(personA.getPartner().getName()+" health reaches " + healthA);
			// check if a is dead
			if(healthA<=0){
				System.out.println("Trainer "+ trainerB +" has won the battle" );
				// reseting the health
				personA.getPartner().setHealth(orgHealthA);
				personB.getPartner().setHealth(orgHealthB);
				break;
			}
		}
	}
	// prints the details of the wild pokemons 
	public void getDetails(){
		System.out.println("Wild Pokemon in Region");
		for(int i=0; i<wildPokemoninRegion.length;i++){
			if(wildPokemoninRegion[i]!=null){
				System.out.println("Name: "+wildPokemoninRegion[i].getName()+" Type: "+wildPokemoninRegion[i].getType()+" Level: "+wildPokemoninRegion[i].getLevel()+" Health: "+ wildPokemoninRegion[i].getHealth()+" Attack Damage: "+wildPokemoninRegion[i].getAttackDamage());
			}
		}
	}
	// adds a wild pokemon in the region 
	public void addWildPokemon(Pokemon p){
		for(int i =0; i<wildPokemoninRegion.length;i++){
			if(wildPokemoninRegion[i]==null){
				wildPokemoninRegion[i] = p;		
				System.out.println("There is a new wild Pokemon called "+wildPokemoninRegion[i].getName()+" in the region!");
				break;
			}
		}
	}
	// removes a wild pokemon in the region
	public boolean removeWildPokemon(String p){
		// checks if the pokemon is there or not 
		boolean found = false;
		for (int i =0;i<wildPokemoninRegion.length;i++){
			// if its not empty and name matches the pokemon in that index
			if (wildPokemoninRegion[i]!=null&&p.equalsIgnoreCase(wildPokemoninRegion[i].getName())){
				// set that index to null
				wildPokemoninRegion[i] = null;
				// returns true
				found = true;
				return found;
			}
		}
		//otherwise returns false
		return found;

	}

}