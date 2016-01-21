package AnimalWorld;

import java.util.ArrayList;

import javafx.scene.Group;


/**
 *World represents the Whole world in which the animals are going to live
 * @author Kikadass
 */
public class World {
    private ArrayList<Group> targetsGroup = new  ArrayList<Group>();
    private Group foodGroup = new Group();
    private Group habitatsGroup = new Group();
    private ArrayList<Group> obstacleGroup = new  ArrayList<Group>();
    private ArrayList<Group> animalGroup = new  ArrayList<Group>();
    private ArrayList<Group> smellRangeGroup = new  ArrayList<Group>();
    private ArrayList<Group> statsGroup = new  ArrayList<Group>();
    private ArrayList<Group> idsGroup = new  ArrayList<Group>();
    private ArrayList<Food> foodList = new ArrayList<Food>();
    private ArrayList<Habitat> habitatsList = new ArrayList<Habitat>();
    private ArrayList<Obstacle> obstacleList = new ArrayList<Obstacle>();
    private ArrayList<ArrayList<Animal>> animalList = new ArrayList<ArrayList<Animal>>();
	private int width;
	private int height;
	private int day;
	private int year;
    private static boolean full = false;

    /**
     * Constructs and initializes a World with a root, a config file and animal types chosen
     * @param root main root
     * @param config    config file
     * @param animalTypes   chosen animal types
     */
    public World(Group root, Configuration config, int animalTypes){

        Lion.restartCounter();
		Zebra.restartCounter();

		for (int i = 0; i < animalTypes; i++) {
			targetsGroup.add(new Group());
			animalGroup.add(new Group());
			smellRangeGroup.add(new Group());
			statsGroup.add(new Group());
			idsGroup.add(new Group());
			animalList.add(new ArrayList<Animal>());
		}

		obstacleGroup.add(new Group());


		for (Group g : targetsGroup){
            root.getChildren().add(g);
		}

		root.getChildren().add(foodGroup);

        root.getChildren().add(habitatsGroup);

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

		this.width =  MainAnimation.getWidth();
		this.height =  MainAnimation.getHeight();

		// creating Obstacles
		for (int i = 0; i < config.getObstacles() && !full; i++){
			this.addObstacle();
		}

        full = false;

        // creating Grass
        for (int i = 0; i < config.getGrass() && !full; i++){
            this.addGrass();
        }

        full = false;

		// creating Food
		for (int i = 0; i < config.getFood() && !full; i++){
			this.addFood();
		}

        full = false;

        // creating Habitats
        for (int i = 0; i < config.getHabitats() && !full; i++){
            this.addHabitat();
        }

        full = false;

		// create Lions
		for (int i = 0; i < config.getLions() && !full; i++){
			this.addLion();
		}

        full = false;

		// create Zebras
		for (int i = 0; i < config.getZebras() && !full; i++){
			this.addZebra();
		}

        full = false;

        hideAll();
	}

    /**
     *Adds obstacles to the world
     */
    public void addObstacle(){
		Obstacle obstacle = new Obstacle(this);

        if (obstacle.getBody().getRadius() > 0){
            obstacleList.add(obstacle);
            obstacleGroup.get(0).getChildren().add(obstacle.getBody());
        }
	}

    /**
     *Adds Food to the World
     */
    public void addFood() {
        Food food = new Food(this);

        if (food.getBody().getRadius() > 0) {
            foodList.add(food);
            foodGroup.getChildren().add(food.getBody());
        }
        else full = true;
    }

    /**
     *Adds a habitat to the world
     */
    public void addHabitat(){
        Habitat habitat = new Habitat(this);

        if (habitat.getBody().getRadius() > 0) {
            habitatsList.add(habitat);
            habitatsGroup.getChildren().add(habitat.getArea());
        }
        else full = true;
    }

    /**
     *adds grass to the world
     */
    public void addGrass() {
		Grass food = new Grass(this);

		if (food.getBody().getRadius() > 0) {
			foodList.add(food);
			foodGroup.getChildren().add(food.getBody());
		}
        else full = true;

    }

    /**
     * deletes food from the world
     * @param i index for food that is going to be deleted
     */
    public void deleteFood(int i){
        foodList.remove(i);
        foodGroup.getChildren().remove(i);
	}

    /**
     * Adds animal into the world
     * @param i type of animal wanted
     */
    public void addAnimal(int i){
        if (i == 0){
            addLion();
        }
		if (i == 1){
			addZebra();
		}
    }

    /**
     *Adds Lion into the world
     *
     */
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
        else full = true;

    }

    /**
     * Adds Zebra into the world
     */
    public void addZebra(){
		Zebra zebra = new Zebra(this);

		if (zebra.getBody().getRadius() > 0) {
			animalList.get(1).add(zebra);
			animalGroup.get(1).getChildren().add(zebra.getBody());
			smellRangeGroup.get(1).getChildren().add(zebra.getSmellRange());
			statsGroup.get(1).getChildren().add(zebra.getStats());
			targetsGroup.get(1).getChildren().add(zebra.getProvisionalTarget().getBody());
			idsGroup.get(1).getChildren().add(zebra.getID());
		}
        else full = true;
    }

    /**
     * Removes chosen animal from the world
     * @param j type of animal
     * @param i exact animal
     */
    public void removeAnimal(int j, int i){
		animalList.get(j).remove(i);
		animalGroup.get(j).getChildren().remove(i);
		smellRangeGroup.get(j).getChildren().remove(i);
		statsGroup.get(j).getChildren().remove(i);
		targetsGroup.get(j).getChildren().remove(i);
        idsGroup.get(j).getChildren().remove(i);
	}

    /**
     * Kills a chosen animal and it converts it into a certain type of meat
     * @param j type of animal
     * @param i exact animal
     */
    public void dieAnimal(int j, int i){
        Food food;
        if (j == 0) {
            food = new Food(this, "LionMeat", true, (int) animalList.get(j).get(i).getBody().getRadius(), animalList.get(j).get(i).getPosX(), animalList.get(j).get(i).getPosY());
        }
        else if (j == 1) {
            food = new Food(this, "ZebraMeat", true, (int) animalList.get(j).get(i).getBody().getRadius(), animalList.get(j).get(i).getPosX(), animalList.get(j).get(i).getPosY());
        }
        else{
            food = new Food(this, "Meat", true, (int) animalList.get(j).get(i).getBody().getRadius(), animalList.get(j).get(i).getPosX(), animalList.get(j).get(i).getPosY());
        }

        if (food.getBody().getRadius() > 0) {
            foodList.add(food);
            foodGroup.getChildren().add(food.getBody());
        }

        removeAnimal(j, i);
    }

    /**
     * Day Getter
     * @return day
     */
    public int getDay() {
		return day;
	}

    /**
     * World width getter
     * @return world's width
     */
    public int getWidth(){
		return this.width;
	}

    /**
     * World's Height Getter
     * @return world's height
     */
    public int getHeight(){
		return this.height;
	}

    /**
     * Year getter
     * @return year
     */
    public int getYear() {
		return year;
	}

    /**
     * AnimalList getter
     * @return All animals in a 2D array
     */
    public ArrayList<ArrayList<Animal>> getAnimalList() {
        return animalList;
    }

    /**
     * ObstacleList Getter
     * @return All obstacles in an array
     */
    public ArrayList<Obstacle> getObstacleList() {
        return obstacleList;
    }

    /**
     * HabitatsList Getter
     * @return All habitats in an array
     */
    public ArrayList<Habitat> getHabitatsList() {
        return habitatsList;
    }

    /**
     * FoodList Getter
     * @return All food in an array
     */
    public ArrayList<Food> getFoodList() {
        return foodList;
    }

    /**
     * Year Setter
     * @param year world's year
     */
    public void setYear(int year) {
		this.year = year;
	}

    /**
     * Day setter
     * @param day world's day
     */
    public void setDay(int day) {
		this.day = day;
	}

    /**
     * Width Setter
     * @param width world's width
     */
    public void setWidth(int width){
		this.width = width;
	}

    /**
     * Height Setter
     * @param height world's height
     */
    public void setHeight(int height){
		this.height = height;
	}

    /**
     *Hides all Smell Ranges from the scene
     */
    public void hideSmellRange(){
		for (Group g : smellRangeGroup) {
			g.setVisible(false);
		}
	}

    /**
     *Hides all Targets from the scene
     */
    public void hideTargets(){
		for (Group g : targetsGroup) {
			g.setVisible(false);
		}
	}

    /**
     *Hides all Stats from the scene
     */
    public void hideStats(){
		for (Group g : statsGroup) {
			g.setVisible(false);
		}
	}

    /**
     * Hides Specific Stats from a specific animal
     * @param j animal type
     * @param i exact animal
     */
    public void hideSpecificStats(int j, int i){
        statsGroup.get(j).getChildren().get(i).setVisible(false);
    }

    /**
     * Hides All IDs
     */
    public void hideID(){
        for (Group g : idsGroup) {
            g.setVisible(false);
        }
    }

    /**
     * Hides specific ID from a specific Type of animal
     * @param i animal's type
     */
    public void hideSpecificID(int i){
            idsGroup.get(i).setVisible(false);
    }

    /**
     *Hides all: Stats, Targets, ID's and Smell Ranges
     */
    public void hideAll(){
        hideSmellRange();
        hideTargets();
        hideStats();
        hideID();
    }

    /**
     * Shows specific ID from a specific animal type
     * @param i animal type
     */
    public void showSpecificID(int i){
        idsGroup.get(i).setVisible(true);
    }

    /**
     * Shows All IDs
     */
    public void showID(){
        for (Group g : idsGroup) {
            g.setVisible(true);
        }

    }

    /**
     *Shows All Smell Range
     */
    public void showSmellRange(){
		for (Group g : smellRangeGroup) {
			g.setVisible(true);
		}

    }

    /**
     *Shows All Targets
     */
    public void showTargets(){
		for (Group g : targetsGroup) {
			g.setVisible(true);
		}
    }

    /**
     *Shows All Stats
     */
    public void showStats(){
		for (Group g : statsGroup) {
			g.setVisible(true);
		}
    }

    /**
     * Shows Specific Stats from a specifi animal
     * @param j animal type
     * @param i exact animal
     */
    public void showSpecificStats(int j, int i){
       statsGroup.get(j).getChildren().get(i).setVisible(true);
    }


    /**
     * Deletes all data from world
     */
    public void deleteAll(){
        for (int i = 0; i < getAnimalList().size(); i++){
            while (0 < animalList.get(i).size()) {
                this.removeAnimal(i, 0);
            }
        }

        while (0 < foodGroup.getChildren().size()){
            foodGroup.getChildren().remove(0);
        }

        while (0 < habitatsGroup.getChildren().size()){
            habitatsGroup.getChildren().remove(0);
        }

        while (0 < obstacleGroup.get(0).getChildren().size()){
            obstacleGroup.get(0).getChildren().remove(0);
        }


        while (0 < foodList.size()){
            foodList.remove(0);
        }


        while (0 < habitatsList.size()){
            habitatsList.remove(0);
        }
        while (0 < obstacleList.size()){
            obstacleList.remove(0);
        }
    }
}