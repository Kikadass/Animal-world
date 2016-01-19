package AnimalWorld;

import java.util.ArrayList;

import javafx.scene.Group;


public class World {
    private ArrayList<Group> targetsGroup = new  ArrayList<Group>();
    private ArrayList<Group> foodGroup = new  ArrayList<Group>();
    private ArrayList<Group> obstacleGroup = new  ArrayList<Group>();
    private ArrayList<Group> animalGroup = new  ArrayList<Group>();
    private ArrayList<Group> smellRangeGroup = new  ArrayList<Group>();
    private ArrayList<Group> statsGroup = new  ArrayList<Group>();
    private ArrayList<Group> idsGroup = new  ArrayList<Group>();
    ArrayList<Food> foodList = new ArrayList<Food>();
	ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
	ArrayList<ArrayList<Animal>> animalList = new ArrayList<ArrayList<Animal>>();
	private int width;
	private int height;
	private int day;
	private int year;
	
	public World(Group root, Configuration config){

        Lion.restartCounter();

        targetsGroup.add(new Group());
        foodGroup.add(new Group());
        obstacleGroup.add(new Group());
        animalGroup.add(new Group());
        smellRangeGroup.add(new Group());
        statsGroup.add(new Group());
        idsGroup.add(new Group());
        animalList.add(new ArrayList<Animal>());

		for (Group g : targetsGroup){
            root.getChildren().add(g);
		}

		for (Group g : foodGroup){
            root.getChildren().add(g);
		}

		for (Group g : obstacleGroup){
            root.getChildren().add(g);
		}
		for (Group g : animalGroup){
            root.getChildren().add(g);
		}
		for (Group g : smellRangeGroup){
            root.getChildren().add(g);
		}
		for (Group g : statsGroup){
            root.getChildren().add(g);
		}
        for (Group g : idsGroup){
            root.getChildren().add(g);
        }

		this.width =  NewMenu.getWidth();
		this.height =  NewMenu.getHeight();

		// creating Obstacles
		for (int i = 0; i < config.getObstacles(); i++){
			this.addObstacle();
		}
				
		// creating Food
		for (int i = 0; i < config.getFood(); i++){
			this.addFood();
		}	
		

		// create Animals
		for (int i = 0; i < config.getBugs(); i++){
			this.addLion();
		}

        hideAll();
	}

	public void addObstacle(){
		Obstacle obstacle = new Obstacle(this);

        if (obstacle.getBody().getRadius() > 0){
            obstacleList.add(obstacle);
            obstacleGroup.get(0).getChildren().add(obstacle.getBody());
        }
	}
	
	public void addFood() {
        Food food = new Food(this);

        if (food.getBody().getRadius() > 0) {
            foodList.add(food);
            foodGroup.get(0).getChildren().add(food.getBody());
        }
    }

	public void deleteFood(int i){
        foodList.remove(i);
        foodGroup.get(0).getChildren().remove(i);
	}

    public void addAnimal(int i){
        if (i == 0){
            addLion();
        }
    }

	public void addLion(){
		Lion lion = new Lion(this);

        if (lion.getBody().getRadius() > 0) {
            animalList.get(0).add(lion);
            animalGroup.get(0).getChildren().add(lion.getBody());
            smellRangeGroup.get(0).getChildren().add(lion.getSmellRange());
            statsGroup.get(0).getChildren().add(lion.getStats());
            targetsGroup.get(0).getChildren().add(lion.getProvisionalTarget().getBody());



            idsGroup.get(0).getChildren().add(lion.getID());


        }
    }

	public void removeAnimal(int j, int i){
		animalList.get(j).remove(i);
		animalGroup.get(j).getChildren().remove(i);
		smellRangeGroup.get(j).getChildren().remove(i);
		statsGroup.get(j).getChildren().remove(i);
		targetsGroup.get(j).getChildren().remove(i);
        idsGroup.get(j).getChildren().remove(i);

	}

	public int getDay() {
		return day;
	}

	public int getWidth(){
		return this.width;
	}
	
	public int getHeight(){
		return this.height;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public void hideSmellRange(){
		for (Group g : smellRangeGroup) {
			g.setVisible(false);
		}
	}

	public void hideTargets(){
		for (Group g : targetsGroup) {
			g.setVisible(false);
		}
	}

	public void hideStats(){
		for (Group g : statsGroup) {
			g.setVisible(false);
		}
	}

    public void hideID(){
        for (Group g : idsGroup) {
            g.setVisible(false);
        }
    }

    public void hideSpecificID(int i){
            idsGroup.get(i).setVisible(false);
    }

    public void hideAll(){
        hideSmellRange();
        hideTargets();
        hideStats();
        hideID();
    }

    public void showSpecificID(int i){
        idsGroup.get(i).setVisible(true);
    }

    public void showID(){
        for (Group g : idsGroup) {
            g.setVisible(true);
        }

    }

    public void showSmellRange(){
		for (Group g : smellRangeGroup) {
			g.setVisible(true);
		}

    }

    public void showTargets(){
		for (Group g : targetsGroup) {
			g.setVisible(true);
		}
    }

    public void showStats(){
		for (Group g : statsGroup) {
			g.setVisible(true);
		}
    }

    public void showAll(){
        showID();
        showSmellRange();
        showStats();
        showTargets();
    }
}