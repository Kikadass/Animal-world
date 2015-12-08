package AnimalWorld;

import java.util.Random;

import javax.swing.JOptionPane;


public class AWorld{
	char[][] map;
	ABug[] BugList;
	private int width = 25;
	private int height = 25;
	
	public AWorld(){
		this.map = new char[height][width];
		
		Random rnd = new Random();
		
		// creating random food
		int n = rnd.nextInt(100);
		for (int i = 0; i < n; i++){
			int x,y;
			do{
				x = rnd.nextInt(this.width);
				y = rnd.nextInt(this.height);
			}while(map[y][x] != '\u0000');
			map[y][x] = (char) (rnd.nextInt(8)+49);
		}

		//creating random obstacles
		n = rnd.nextInt(100);
		for (int i = 0; i < n; i++){
			int x,y;
			do{
				x = rnd.nextInt(this.width);
				y = rnd.nextInt(this.height);
			}while(map[y][x] != '\u0000');
			map[y][x] = 'O';
		}
		
		// create Random bugs
		n = rnd.nextInt(5)+1;
		this.BugList = new ABug[n];
		for (int i = 0; i < n; i++){
			ABug Bug = new ABug();
			this.BugList[i] = Bug;
			this.BugList[i].setMap(this.map);
			this.BugList[i].createRandomBug();
		}
		
	}

	public AWorld(Configuration config){
		this.map = new char[config.getSize()][config.getSize()];
		Random rnd = new Random();
		
		// creating food
		for (int i = 0; i < config.getFood(); i++){
			int x,y;
			do{
				x = rnd.nextInt(config.getSize());
				y = rnd.nextInt(config.getSize());
			}while(map[y][x] != '\u0000');
			map[y][x] = (char) (rnd.nextInt(8)+49);
		}

		//creating random obstacles
		for (int i = 0; i < config.getObstacles(); i++){
			int x,y;
			do{
				x = rnd.nextInt(config.getSize());
				y = rnd.nextInt(config.getSize());
			}while(map[y][x] != '\u0000');
			map[y][x] = 'O';
		}
		
		// create Bugs
		this.BugList = new ABug[config.getBugs()];
		for (int i = 0; i < config.getBugs(); i++){
			ABug Bug = new ABug();
			this.BugList[i] = Bug;
			this.BugList[i].setMap(this.map);
			this.BugList[i].createRandomBug();
		}
		
	}

	public void printWorld(){
		for (int i = 0; i < this.map.length+2; i++){
			System.out.print("| ");
			for (int j = 0; j < this.map.length; j++){
				if (i == 0 || i == this.map.length+1) System.out.print("- ");
				else if (this.map[i-1][j] == '\u0000') System.out.print("  ");
				else System.out.print(this.map[i-1][j] + " ");
			}
			System.out.println("|");
		}
	}
	
	public ABug[] deleteBug(int j, ABug[] BugList){
		ABug[] newBugList = new ABug[BugList.length-1];
		boolean deleted = false;
		
		for (int i = 0; i < BugList.length; i++){
			if (i == j){
				i++;
				BugList[j].die();
				deleted = true;
			}
			if (i == BugList.length) return newBugList;
			if (deleted) newBugList[i-1] = BugList[i];
			else newBugList[i] = BugList[i];
		}
		return newBugList;
	}
	
	public void AddBug(){
		ABug[] newBugList = new ABug[this.BugList.length+1];
		
		for (int i = 0; i < this.BugList.length; i++){
			newBugList[i] = this.BugList[i];
		}
		ABug Bug = new ABug();
		Bug.setMap(this.map);
		Bug.createUsersBug();
		newBugList[newBugList.length-1] = Bug;

		this.BugList = newBugList;
	}
	
	public void worldUpdate(boolean toggle) {
		for (int j = 0; j < this.BugList.length; j++){
			int movement = this.BugList[j].move(this.BugList[j].getDirectionOfFood());
			if (movement > 0) this.BugList[j].AddEnergy(movement);
			this.BugList[j].AddEnergy(-1);
			if (this.BugList[j].getEnergy() < 0){
				this.BugList = this.deleteBug(j, this.BugList);
			}
			
			if (toggle){
				this.printWorld();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException{			
		Configuration config = new Configuration();
		//config = config.StartLoad();
		AWorld world = new AWorld(config);
		
		world.printWorld();
		
		String cycles1 = JOptionPane.showInputDialog(null, "Amount of cycles that you would like to do?");
		int cycles = Integer.parseInt(cycles1);
		
		boolean toggle = true;
		
		for (int i = 0; i < cycles; i++){
			world.worldUpdate(toggle);
		}
		world.printWorld();
		
	}
}
