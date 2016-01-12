package AnimalWorld;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Font;

public class World {
    private Group targetsGroup = new Group();
    private Group foodGroup = new Group();
    private Group obstacleGroup = new Group();
    private Group animalGroup = new Group();
    private Group smellRangeGroup = new Group();
    private Group statsGroup = new Group();
    ArrayList<Food> foodList = new ArrayList<Food>();
	ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
	ArrayList<Animal> animalList = new ArrayList<Animal>();
	private int width;
	private int height;
	
	public World(Group root, Configuration config, Scene scene){

        root.getChildren().add(targetsGroup);
        root.getChildren().add(foodGroup);
        root.getChildren().add(obstacleGroup);
        root.getChildren().add(animalGroup);
        root.getChildren().add(smellRangeGroup);
        root.getChildren().add(statsGroup);

		this.width = (int) scene.getWidth();
		this.height = (int) scene.getHeight();

		// creating Obstacles
		for (int i = 0; i < config.getObstacles(); i++){
			this.addObstacle();
		}
				
		// creating Food
		for (int i = 0; i < config.getFood(); i++){
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
        obstacleGroup.getChildren().add(obstacle.getBody());
	}
	
	public void addFood(){
		Food food = new Food(this);
		foodList.add(food);
        foodGroup.getChildren().add(food.getBody());
	}

	public void deleteFood(int i){
        foodList.remove(i);
        foodGroup.getChildren().remove(i);
	}

	public void addLion(){
		Lion lion = new Lion(this);
		animalList.add(lion);
        animalGroup.getChildren().add(lion.getBody());
        smellRangeGroup.getChildren().add(lion.getSmellRange());
        statsGroup.getChildren().add(lion.getStats());
        targetsGroup.getChildren().add(lion.getProvisionalTarget().getBody());
        targetsGroup.getChildren().add(lion.getMainTarget().getBody());

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
