package AnimalWorld;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Scene;

public class World {
	ArrayList<Food> foodList = new ArrayList<Food>();
	ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
	ArrayList<Animal> animalList = new ArrayList<Animal>();
	private int width;
	private int height;
	
	public World(Configuration config, Scene scene){
		this.width = (int) scene.getWidth();
		this.height = (int) scene.getHeight();
		
		Random rnd = new Random();
		
		// creating Obstacles
		for (int i = 0; i < /*config.getObstacles()*/ 1; i++){
			this.addObstacle();
		}
				
		// creating Food
		for (int i = 0; i < /*config.getFood()*/ 1; i++){
			this.addFood();
		}	
		

		// create Animals
		for (int i = 0; i < /*config.getBugs()*/ 1; i++){
			this.addLion();
		}
	}

	public void addObstacle(){
		Obstacle obstacle = new Obstacle(this);
		obstacleList.add(obstacle);
	}
	
	public void addFood(){
		Food food = new Food(this);
		foodList.add(food);
	}
	
	public void addLion(){
		Lion lion = new Lion(this);
		animalList.add(lion);
	}
	
	public void setWidth(int width){
		this.width = width;
	}
	
	public void setHeight(int height){
		this.height = height;
	}

	
	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}
}
