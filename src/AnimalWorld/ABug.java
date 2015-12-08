package AnimalWorld;

import java.util.Random;

import javax.swing.JOptionPane;

public class ABug {
	private AWorld world; 
	private String species, name;
	private char symbol;		//  Could be the first character of the species name
	private int h_pos, v_pos, energy, ID;
	private int MaxSensinDist;
	private static int counter;
	private int size;
	char[][] map;
	
	public enum Direction {NORTH, SOUTH, EAST, WEST}
	
	public static void Addcounter(){
		counter++;
	}
	
	public static int Getcounter(){
		return counter;
	}
		
	public ABug(){
		this.species = new String();
		this.name = new String();
		this.h_pos = 0;
		this.v_pos = 0;
		this.energy = 0;
		this.ID = 0;
		Random rnd = new Random();
		this.MaxSensinDist = rnd.nextInt(19)+1;
		Addcounter();
	}
	
	public ABug(String species, String name, char symbol, int h_pos, int v_pos, int energy, int ID, int MaxSensinDist){
		this.species = species;
		this.name = name;
		this.symbol = symbol;
		this.h_pos = h_pos;
		this.v_pos = v_pos;
		this.energy = energy;
		this.ID = ID;
		this.MaxSensinDist = MaxSensinDist;
		Addcounter();
	}
	
	public void setWorld(AWorld w){
		this.world = w;
		this.map = this.world.map;
	}
	
	public void setMap(char[][] map){
		this.map = map;
	}
	
	public void setSpecies(String species){
		this.species = species;
	}
	
	public void setName(String name){
		this.name = name;
		this.symbol = name.charAt(0);
	}
		
	public void setMaxSensinDist(int MaxSensinDist){
		this.MaxSensinDist = MaxSensinDist;
	}
	
	public void setEnergy(int energy){
		this.energy = energy;
	}
	
	public int getEnergy(){
		return this.energy;
	}
	
	public void AddEnergy(int energy){
		this.energy += energy;
	}
	
	public String getSymbolID(){
		String symbolID = Character.toString(this.symbol) + " " + Integer.toString(this.ID);
		return symbolID;
	}
	
	// return a serie of attributes
	public String toString(){
		// add all atributes together into a string with labels
		// ";" is between each value
		String toReturn ="h_pos: " + Integer.toString(this.h_pos) + ";v_pos: " + Integer.toString(this.v_pos) + ";"; 
		return toReturn;
		
	}
	
	// return all atributes in a string
	public String toText(){
		// add all atributes together into a string with labels
		// ";" is between each value
		String toReturn ="species: " + species + ";name: " + this.name + ";symbol: " + symbol + ";h_pos: " + Integer.toString(this.h_pos) + ";v_pos: " + Integer.toString(this.v_pos) + ";energy: " + Integer.toString(this.energy) + ";ID: " + Integer.toString(this.ID) + ";"; 
		return toReturn;
		
	}
		
	public void printBug(){
		// string to print
		String print = new String();
		String attributes = this.toText();
		// printing all attributes one by one
		for (int j = 0; j < attributes.length(); j++){
			if (attributes.charAt(j) == ';') print += "\n";
			else  print += attributes.charAt(j);
		}
		JOptionPane.showMessageDialog(null, print);
	}
	
	public void createUsersBug(){
		//input of all data and convert it into the appropiate class
		this.species = JOptionPane.showInputDialog(null, "What is the species you would like to create?");
		this.name = JOptionPane.showInputDialog(null, "What will be the name of the species?");
		this.symbol = name.charAt(0);	// if the user does not want to input a symbol, the symbol will be the first char of the name
		
		do{
		String h_pos1 = JOptionPane.showInputDialog(null, "What is the horizontal position of the Bug?");
		this.h_pos = Integer.parseInt(h_pos1);
		}while(h_pos > this.map.length);
		
		do{
		String v_pos1 = JOptionPane.showInputDialog(null, "What is the vertical position of the Bug?");
		this.v_pos = Integer.parseInt(v_pos1);
		}while(v_pos > this.map.length);
		
		String energy1 = JOptionPane.showInputDialog(null, "What is the energy of the Bug?");
		this.energy = Integer.parseInt(energy1);
		
		String ID1 = JOptionPane.showInputDialog(null, "What is the ID of the Bug?");
		this.ID = Integer.parseInt(ID1);
		
		this.map[this.v_pos][this.h_pos] = this.symbol;
	}
	
	public void createRandomBug(){
		Random rnd = new Random();
		String[] species = {"Monkey", "Bird", "Human", "Lion", "Elephant", "Fish"};
		this.species = species[rnd.nextInt(species.length)];
		String[] names = {"Aaron", "Barry", "Carlton", "Dexter", "Eliot", "Freeman"};
		this.name = names[rnd.nextInt(names.length)];
		this.symbol = this.name.charAt(0);
		int x,y;
		do{
			x = rnd.nextInt(25);
			y = rnd.nextInt(25);
		}while(this.map[y][x] != '\u0000');
		this.h_pos = x;
		this.v_pos = y;
		this.map[y][x] = this.symbol;
		this.energy = rnd.nextInt(20);
		this.ID = rnd.nextInt(1000);
	}
	
	public boolean smellFood(Direction d){
		System.out.println(this.MaxSensinDist + " " + this.h_pos + " " + this.v_pos + " " + this.energy);
		switch (d) {
	        case NORTH:
	        	for (int i = this.v_pos; i > (this.v_pos-this.MaxSensinDist) && i >= 0; i--){
		        	if (i <= this.v_pos-1 && ((int)this.map[i][this.h_pos] >= 65 && (int)this.map[i][this.h_pos] <= 90 || (int)this.map[i][this.h_pos] >= 97 && (int)this.map[i][this.h_pos] <= 122)){
		        		return false;
		        	}	
	        		
	        		if ((int)this.map[i][this.h_pos] >= 49 && (int)this.map[i][this.h_pos] <= 57){
	        			return true;
	        		}
	        	}
	        	return false;
	        case EAST:
	        	for (int i = this.h_pos; i < (this.h_pos+this.MaxSensinDist) && i < this.map.length; i++){
	        		if (i >= this.h_pos+1 && ((int)this.map[this.v_pos][i] >= 65 && (int)this.map[this.v_pos][i] <= 90 || (int)this.map[this.v_pos][i] >= 97 && (int)this.map[this.v_pos][i] <= 122)){
		        		return false;
		        	}	
	        		if ((int)this.map[this.v_pos][i] >= 49 && (int)this.map[this.v_pos][i] <= 57){
	        			return true;
	        		}
	        		else if(this.map[this.v_pos][i] == 'O') return false;
	        	}
	        	return false;
	        case SOUTH:
	        	for (int i = this.v_pos; i < (this.v_pos+this.MaxSensinDist) && i < this.map.length; i++){
	        		if (i >= this.v_pos+1 && ((int)this.map[i][this.h_pos] >= 65 && (int)this.map[i][this.h_pos] <= 90 || (int)this.map[i][this.h_pos] >= 97 && (int)this.map[i][this.h_pos] <= 122)){
		        		return false;
		        	}	
	        		if ((int)this.map[i][this.h_pos] >= 49 && (int)this.map[i][this.h_pos] <= 57){
	        			return true;
	        		}
	        		else if(this.map[i][this.h_pos] == 'O') return false;
	        	}
	        	return false;
	        case WEST:
	        	for (int i = this.h_pos; i > (this.h_pos-this.MaxSensinDist) && i >= 0; i--){
	        		if (i <= this.h_pos-1 && ((int)this.map[this.v_pos][i] >= 65 && (int)this.map[this.v_pos][i] <= 90 || (int)this.map[this.v_pos][i] >= 97 && (int)this.map[this.v_pos][i] <= 122)){
		        		return false;
		        	}	
	        		if ((int)this.map[this.v_pos][i] >= 49 && (int)this.map[this.v_pos][i] <= 57){
	        			return true;
	        		}
	        		else if(this.map[this.v_pos][i] == 'O') return false;
	        	}
	        	return false;
		}
		return false;
	}
		
	public Direction getDirectionOfFood(){
		int distance1 = this.map.length+1;
		int direction = -1;
		
		
		if (smellFood(Direction.NORTH)){
			for (int i = this.v_pos; i > (this.v_pos-this.MaxSensinDist) && i >= 0; i--){
        		if ((int)this.map[i][this.h_pos] >= 49 && (int)this.map[i][this.h_pos] <= 57){
        			if ((this.v_pos - i) < distance1) {
        				distance1 = this.v_pos -i;
        				direction = 0;
        			}
        		}
        	}
		}
		if (smellFood(Direction.EAST)){
			for (int i = this.h_pos; i < (this.h_pos+this.MaxSensinDist) && i < this.map.length; i++){
        		if ((int)this.map[this.v_pos][i] >= 49 && (int)this.map[this.v_pos][i] <= 57){
        			if ((i - this.h_pos) < distance1) {
        				distance1 = i - this.h_pos;
        				direction = 1;
        			}
        		}
        	}
		}
		if (smellFood(Direction.SOUTH)){
			for (int i = this.v_pos; i < (this.v_pos+this.MaxSensinDist) && i < this.map.length; i++){
        		if ((int)this.map[i][this.h_pos] >= 49 && (int)this.map[i][this.h_pos] <= 57){
        			if ((i - this.v_pos) < distance1) {
        				distance1 = i - this.v_pos;
        				direction = 2;
        			}
        		}
        	}
		}
		if (smellFood(Direction.WEST)){
			for (int i = this.h_pos; i > (this.h_pos-this.MaxSensinDist) && i >= 0; i--){
        		if ((int)this.map[this.v_pos][i] >= 49 && (int)this.map[this.v_pos][i] <= 57){
        			if ((this.h_pos - i) < distance1) {
        				distance1 = this.h_pos - i;
        				direction = 3;
        			}
        		}
        	}
		}
		
		if (direction == -1){
			do{
				Random rnd = new Random();
				direction = rnd.nextInt(4);;
			}while( (direction == 0 && this.v_pos == 0) || (direction == 1 && this.h_pos == this.map.length-1) || (direction == 2 && this.v_pos == this.map.length-1) || (direction == 3 && this.h_pos == 0));
		}
		if (direction == 0) return Direction.NORTH;
		if (direction == 1) return Direction.EAST;
		if (direction == 2) return Direction.SOUTH;
		if (direction == 3) return Direction.WEST;
		
		return Direction.EAST;

	}
	
	public Direction getRandomDirectionToMove(){
		Random rnd = new Random();
		int direction = rnd.nextInt(4);
		if (direction == 0) return Direction.NORTH;
		if (direction == 1) return Direction.EAST;
		if (direction == 2) return Direction.SOUTH;
		if (direction == 3) return Direction.WEST;
		return Direction.NORTH;
	}
	
	public void UserMove(){
		String[] buttons = { "WEST", "NORTH", "EAST", "SOUTH" };

	    int rc = JOptionPane.showOptionDialog(null, "Where do you want your bug to go?", "Movement",
	        JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[2]);

	    if (rc == 0){
	    	Random rnd = new Random();
			int x = rnd.nextInt(this.energy);
			this.h_pos -= x;
	    }
	    if (rc == 1){
	    	Random rnd = new Random();
			int y = rnd.nextInt(this.energy);
			this.v_pos += y;
	    }
	    if (rc == 2){
	    	Random rnd = new Random();
			int x = rnd.nextInt(this.energy);
			this.h_pos += x;
	    }
	    if (rc == 3){
	    	Random rnd = new Random();
			int y = rnd.nextInt(this.energy);
			this.v_pos -= y;
	    }
	}
		
	public int move(Direction d){
		switch (d) {
	        case NORTH:
	        	if ((int)this.map[this.v_pos-1][this.h_pos] >= 49 && (int)this.map[this.v_pos-1][this.h_pos] <= 57){
	        		this.map[this.v_pos][this.h_pos] = '\u0000';
	        		int food = (int)this.map[this.v_pos-1][this.h_pos];
	        		this.v_pos--;
	        		this.map[this.v_pos][this.h_pos] = symbol; 
        			return food;
        		}
        		else if((int)this.map[this.v_pos-1][this.h_pos] >= 65 && (int)this.map[this.v_pos-1][this.h_pos] <= 90 || (int)this.map[this.v_pos-1][this.h_pos] >= 97 && (int)this.map[this.v_pos-1][this.h_pos] <= 122) return -1;
	        	this.map[this.v_pos][this.h_pos] = '\u0000';
	        	this.v_pos--;
	        	this.map[this.v_pos][this.h_pos] = symbol;
	        	return 0;
	        case EAST:
	        	if ((int)this.map[this.v_pos][this.h_pos+1] >= 49 && (int)this.map[this.v_pos][this.h_pos+1] <= 57){
	        		this.map[this.v_pos][this.h_pos] = '\u0000';
	        		int food = (int)this.map[this.v_pos][this.h_pos+1];
	        		this.h_pos++;
	        		this.map[this.v_pos][this.h_pos] = symbol;
        			return food;
        		}
        		else if((int)this.map[this.v_pos][this.h_pos+1] >= 65 && (int)this.map[this.v_pos][this.h_pos+1] <= 90 || (int)this.map[this.v_pos][this.h_pos+1] >= 97 && (int)this.map[this.v_pos][this.h_pos+1] <= 122) return -1;
	        	this.map[this.v_pos][this.h_pos] = '\u0000';
	        	this.h_pos++;
	        	this.map[this.v_pos][this.h_pos] = symbol;
	        	return 0;
	        case SOUTH:
	        	if ((int)this.map[this.v_pos+1][this.h_pos] >= 49 && (int)this.map[this.v_pos+1][this.h_pos] <= 57){
	        		this.map[this.v_pos][this.h_pos] = '\u0000';
	        		int food = (int)this.map[this.v_pos+1][this.h_pos];
	        		this.v_pos++;
	        		this.map[this.v_pos][this.h_pos] = symbol;
        			return food;
        		}
        		else if((int)this.map[this.v_pos+1][this.h_pos] >= 65 && (int)this.map[this.v_pos+1][this.h_pos] <= 90 || (int)this.map[this.v_pos+1][this.h_pos] >= 97 && (int)this.map[this.v_pos+1][this.h_pos] <= 122) return -1;
	        	this.map[this.v_pos][this.h_pos] = '\u0000';
	        	this.v_pos++;
	        	this.map[this.v_pos][this.h_pos] = symbol;
	        	return 0;
	        case WEST:
	        	if ((int)this.map[this.v_pos][this.h_pos-1] >= 49 && (int)this.map[this.v_pos][this.h_pos-1] <= 57){
	        		this.map[this.v_pos][this.h_pos] = '\u0000';
	        		int food = (int)this.map[this.v_pos][this.h_pos-1];
	        		this.h_pos--;
	        		this.map[this.v_pos][this.h_pos] = symbol;
        			return food;
        		}
        		else if((int)this.map[this.v_pos][this.h_pos-1] >= 65 && (int)this.map[this.v_pos][this.h_pos-1] <= 90 || (int)this.map[this.v_pos][this.h_pos-1] >= 97 && (int)this.map[this.v_pos][this.h_pos-1] <= 122) return -1;
	        	this.map[this.v_pos][this.h_pos] = '\u0000';
	        	this.h_pos--;
	        	this.map[this.v_pos][this.h_pos] = symbol;
	        	return 0;
		}
		return -1;
		
	}
	
	public void die(){
		this.map[this.v_pos][this.h_pos] = '\u0000';
		counter--;
	}
	
	public static void main(String[] args){	
		String nBugs1 = JOptionPane.showInputDialog(null, "Max of species that you want to input?");
		int nBugs = Integer.parseInt(nBugs1);
		ABug[] BugList = new ABug[nBugs];
		
		int response = -1;		// if the user wants to continue adding species or not
		
		// loop between all Bugs created
		do{
			// create a Bug
			ABug Bug = new ABug();
			Bug.createRandomBug();
			BugList[Getcounter()-1] = Bug ;
			
			// print bug's information
			Bug.printBug();
			
			// if there are still spaces in the array ask a question to the user
			if (nBugs > Getcounter()){
				// does the user want to continue adding species?
				do{
			    response = JOptionPane.showConfirmDialog(null, "Do you want to continue adding species?", "Confirm",
			            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			        if (response == JOptionPane.CLOSED_OPTION) {
			          System.out.println("No button clicked");
			        } 
				} while(response == JOptionPane.CLOSED_OPTION);
			}
			else JOptionPane.showMessageDialog(null, "Sorry but you reached the Max amount of species!");
		}while(response == JOptionPane.YES_OPTION && nBugs > Getcounter());
		
		for (int i = 0; i < Getcounter(); i++){
			BugList[i].move(BugList[i].getRandomDirectionToMove());
			// get all attributes in one string
			String attributes = BugList[i].toString();
			// print bug's information
			BugList[i].printBug();
		}
		
	}
	
}
