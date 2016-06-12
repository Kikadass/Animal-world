package AnimalWorld;

import java.util.ArrayList;
import java.util.Random;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Animal is an abstract class that represents any animal in the world
 *@author Kikadass
 */
public abstract class Animal{
	private Circle Body= new Circle(), smellRange = new Circle();
    private Text stats = new Text();
	private String name;
	private Text ID = new Text();
	private double energy;
    private double food;
    private double metabolism;
    private int maxEnergy;
    private int maxFood;
	private int strength;
	private double foodCarring;
	private int minFoodCons;	// minimum of calories that the meat has to be in order to eat it
	private int age;			// in days
    private boolean gender;     // true = female
    private int pregnant = 0;
	private int angleRange;			// angleRange of normal movement
    private int lastAngle;
	private int lifeExpectancy;	// how old are can that specie live for
    private boolean poisoned = false;
	private double dx;
	private double dy;
	private double speed;
    private ArrayList<String> foodPreferences = new ArrayList<String>();
    private Target houseTarget = new Target(0, 0, 1);
    private Target foodTarget;
    private Target mainTarget = new Target (0,0, 1);
    private Target provisionalTarget = new Target(0, 0, 3);
    private int byeHome = 0;
    private int tries = 0;
    private int collisionCycles = -50;

    /**
     * Constructs and initializes Animal
     */
    public Animal(){
		this.energy = 0;
	}

    /**
     * Creates a Window so that the user can modify the attributes of an animal
     */
    public void modifyAnimal(){

        //Creating a GridPane container
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 350, 350);
        final Stage stage = new Stage();

        stage.setTitle("Modify Life Form");
        stage.setScene(scene);
        stage.sizeToScene();

        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        //Defining the Name text field
        final TextField name = new TextField();
        name.setPromptText("Enter a Name.");
        name.setPrefColumnCount(10);
        name.getText();
        GridPane.setConstraints(name, 1, 0, 2, 1);
        grid.getChildren().add(name);
        Label label1 = new Label("Name:");
        GridPane.setConstraints(label1, 0, 0);
        grid.getChildren().add(label1);


        //Defining the smellRange text field
        final TextField smellRange = new TextField();
        smellRange.setPromptText("eg. 30");
        smellRange.setPrefColumnCount(10);
        smellRange.getText();
        GridPane.setConstraints(smellRange, 1, 1, 2, 1);
        grid.getChildren().add(smellRange);
        Label label3 = new Label("Smell Range:");
        GridPane.setConstraints(label3, 0, 1);
        grid.getChildren().add(label3);


        //Defining the maxEnergy text field
        final TextField maxEnergy = new TextField();
        maxEnergy.setPromptText("eg. 200");
        GridPane.setConstraints(maxEnergy, 1, 2, 2, 1);
        grid.getChildren().add(maxEnergy);
        Label label4 = new Label("Max Energy:");
        GridPane.setConstraints(label4, 0, 2);
        grid.getChildren().add(label4);

        //Defining the Max Food text field
        final TextField maxFood = new TextField();
        maxFood.setPromptText("eg. 200");
        GridPane.setConstraints(maxFood, 1, 3, 2, 1);
        grid.getChildren().add(maxFood);
        Label label5 = new Label("Max Food:");
        GridPane.setConstraints(label5, 0, 3);
        grid.getChildren().add(label5);

        //Defining the Metabolism text field
        final TextField metabolism = new TextField();
        metabolism.setPromptText("eg. 0.05");
        GridPane.setConstraints(metabolism, 1, 4, 2, 1);
        grid.getChildren().add(metabolism);
        Label label6 = new Label("Metabolism:");
        GridPane.setConstraints(label6, 0, 4);
        grid.getChildren().add(label6);

        //Defining the Strength text field
        final TextField strength = new TextField();
        strength.setPromptText("eg. 200");
        GridPane.setConstraints(strength, 1, 5, 2, 1);
        grid.getChildren().add(strength);
        Label label7 = new Label("Strength:");
        GridPane.setConstraints(label7, 0, 5);
        grid.getChildren().add(label7);

        //Defining the Angle of Movement text field
        final TextField angle = new TextField();
        angle.setPromptText("eg. 30");
        GridPane.setConstraints(angle, 1, 6, 2, 1);
        grid.getChildren().add(angle);
        Label label8 = new Label("Angle of Movement:");
        GridPane.setConstraints(label8, 0, 6);
        grid.getChildren().add(label8);

        //Defining the Speed text field
        final TextField speed = new TextField();
        speed.setPromptText("eg. 1.5");
        GridPane.setConstraints(speed, 1, 7, 2, 1);
        grid.getChildren().add(speed);
        Label label9 = new Label("Speed:");
        GridPane.setConstraints(label9, 0, 7);
        grid.getChildren().add(label9);

        //Defining the gender ChoiceBox
        final ChoiceBox genderCb  = new ChoiceBox(FXCollections.observableArrayList (
                "Choose Gender",
                new Separator(),
                "Male",
                "Female"
        ));
        genderCb.getSelectionModel().selectFirst();     // In order to have "Choose Gender" already selected

        GridPane.setConstraints(genderCb, 1, 8, 2, 1);
        grid.getChildren().add(genderCb);
        Label label10 = new Label("Gender:");
        GridPane.setConstraints(label10, 0, 8);
        grid.getChildren().add(label10);

        //Defining the Apply button
        Button apply = new Button("Apply");
        GridPane.setConstraints(apply, 0, 10);
        grid.getChildren().add(apply);

        //Defining the Current Values button
        Button current = new Button("Current Values");
        GridPane.setConstraints(current, 1, 10);
        grid.getChildren().add(current);

        //Defining the Clear button
        Button clear = new Button("Clear");
        GridPane.setConstraints(clear, 2, 10);
        grid.getChildren().add(clear);

        //Adding a Label
        final Label label = new Label();
        GridPane.setConstraints(label, 0, 9);
        GridPane.setColumnSpan(label, 2);
        grid.getChildren().add(label);



        //Setting an action for the Apply button
        apply.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((name.getText() != null && !name.getText().isEmpty()) &&
                    (smellRange.getText() != null && !smellRange.getText().isEmpty()) && Configuration.checkStrInt(smellRange.getText()) &&
                    (maxEnergy.getText() != null && !maxEnergy.getText().isEmpty()) && Configuration.checkStrInt(maxEnergy.getText()) &&
                    (maxFood.getText() != null && !maxFood.getText().isEmpty()) && Configuration.checkStrInt(maxFood.getText()) &&
                    (metabolism.getText() != null && !metabolism.getText().isEmpty()) && Configuration.checkStrDb(maxEnergy.getText()) &&
                    (strength.getText() != null && !strength.getText().isEmpty()) && Configuration.checkStrInt(strength.getText()) &&
                    (angle.getText() != null && !angle.getText().isEmpty()) && Configuration.checkStrInt(angle.getText()) &&
                    (speed.getText() != null && !speed.getText().isEmpty()) && Configuration.checkStrDb(speed.getText()) &&
                    (genderCb.getSelectionModel().getSelectedIndex() >= 2 )){

                    if (Double.parseDouble(smellRange.getText()) >= getBody().getRadius()+10) {
                        setName(name.getText());
                        setSmellRange(Double.parseDouble(smellRange.getText()));
                        setMaxEnergy(Integer.parseInt(maxEnergy.getText()));
                        setMaxFood(Integer.parseInt(maxFood.getText()));
                        setMetabolism(Double.parseDouble(metabolism.getText()));
                        setStrenght(Integer.parseInt(strength.getText()));
                        setAngleRange(Integer.parseInt(angle.getText()));
                        setSpeed(Double.parseDouble(speed.getText()));
                        if (genderCb.getSelectionModel().getSelectedIndex() == 2) setGender(false);
                        else setGender(true);
                        label.setText("Life Form changed!");
                    }
                    else    label.setText("Smell Range has to be bigger or equals than " + (getBody().getRadius()+10) + "!");

                }
                else {
                    label.setText("Please enter all details.");
                }
            }
        });

        //Setting an action for the current button
        current.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                name.setText(getName());
                smellRange.setText(String.valueOf((int)getSmellRange().getRadius()));
                maxEnergy.setText(String.valueOf(getMaxEnergy()));
                maxFood.setText(String.valueOf(getMaxFood()));
                metabolism.setText(String.valueOf(getMetabolism()));
                strength.setText(String.valueOf(getStrenght()));
                angle.setText(String.valueOf(getAngleRange()));
                speed.setText(String.valueOf(getSpeed()));
                label.setText(null);
            }
        });

        //Setting an action for the Clear button
        clear.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                name.clear();
                smellRange.clear();
                maxEnergy.clear();
                maxFood.clear();
                metabolism.clear();
                strength.clear();
                angle.clear();
                speed.clear();
                label.setText(null);
            }
        });

        stage.showAndWait();
    }

    /**
     * Name Getter
     * @return the animal's name
     */
    public String getName(){
		return this.name;
	}

    /**
     * ID Getter
     * @return animal's ID
     */
    public Text getID(){
		return this.ID;
	}

    /**
     * Energy Getter
     * @return animal's energy
     */
    public double getEnergy(){
		return this.energy;
	}

    /**
     * Strength Getter
     * @return animal's strength
     */
    public int getStrenght(){
		return this.strength;
	}

    /**
     * FoodCarring Getter
     * @return animal's amount of food that its carrying
     */
    public double getFoodCarring(){
		return this.foodCarring;
	}

    /**
     * MinFoodCons Getter
     * @return minimum amount energy that an food needs to have in order to eat it
     */
    public int getMinFoodCons(){
		return this.minFoodCons;
	}

    /**
     * SmellRange Getter
     * @return animal's Smell Range as a circle
     */
    public Circle getSmellRange(){
		return this.smellRange;
	}

    /**
     * Age Getter
     * @return animal's Getter
     */
    public int getAge(){
		return this.age;
	}

    /**
     * AngleRange Getter
     *
     * @return animal's angle range to move in
     */
    public int getAngleRange(){
		return this.angleRange;
	}

    /**
     * LifeExpectancy Getter
     * @return animal's LifeExpectancy
     */
    public int getLifeExpectancy(){
		return this.lifeExpectancy;
	}

    /**
     * Body Getter
     * @return animal's Body as a Circle
     */
    public Circle getBody(){
		return this.Body;
	}

    /**
     * Speed Getter
     * @return animal's speed
     */
    public double getSpeed() {
		return this.speed;
	}

    /**
     * Provisional Target Getter
     * @return animal's provisional target
     */
    public Target getProvisionalTarget() {
        return provisionalTarget;
    }

    /**
     * Food Getter
     * @return animal's amount of food consumed (in its body)
     */
    public double getFood() {
        return food;
    }

    /**
     * MaxFood Getter
     * @return animal's maximum amount of food consumption
     */
    public int getMaxFood() {
        return maxFood;
    }

    /**
     * MaxEnergy Getter
     * @return animal's maximum amount of energy
     */
    public int getMaxEnergy() {
        return maxEnergy;
    }

    /**
     * Stats Getter
     * @return animal's Stats as Text
     */
    public Text getStats() {
        return stats;
    }

    /**
     * MainTarget Getter
     * @return animal's main target
     */
    public Target getMainTarget() {
        return mainTarget;
    }

    /**
     * Metabolism Getter
     * @return animal's metabolism
     */
    public double getMetabolism() {
        return metabolism;
    }

    /**
     * PosX Getter
     * @return anmimal's position x in the world in pixels
     */
    public int getPosX(){
		return (int)(this.Body.getCenterX() + this.Body.getTranslateX());
	}

    /**
     * PosY Getter
     * @return animal's position y in the world in pixels
     */
    public int getPosY(){
		return (int)(this.Body.getCenterY() + this.Body.getTranslateY());
	}

    /**
     * Calculates animal's Provisional Target taking into account the main target
     *
     * Not taking into account the angle Range
     * The aim is to get to the main target directly
     */
    public void getLocalTarget(){
        double angle = getAngleTo(this.mainTarget.getBody().getCenterX(), this.mainTarget.getBody().getCenterY());
        int path = (int)this.smellRange.getRadius();
        int tX = (int) ((this.smellRange.getCenterX() + this.smellRange.getTranslateX()) + path * Math.cos(angle));
        int tY = (int) ((this.smellRange.getCenterY() + this.smellRange.getTranslateY()) + path * Math.sin(angle));
        this.provisionalTarget.getBody().setCenterX(tX);
        this.provisionalTarget.getBody().setCenterY(tY);
    }

    /**
     * Calculates a Random animal's Provisional Target taking into account the angleRange
     * not taking into account the main target
     */
    public void getRandomLocalTarget(){
        Random rand = new Random();
        int randomAttemptTracker = 0;
        int anAngle, tX, tY, path = (int)this.smellRange.getRadius();
        do{
            if (randomAttemptTracker < this.angleRange * 2){
                anAngle = rand.nextInt(this.angleRange * 2) + this.lastAngle - this.angleRange;
                randomAttemptTracker++;
            } else{
                // Too many attempts at picking an angle within the turn range were made
                anAngle = rand.nextInt(360);
                path = rand.nextInt((int)this.smellRange.getRadius());
            }
            double angle = Math.toRadians(anAngle);
            tX = (int) ((this.smellRange.getCenterX() + this.smellRange.getTranslateX()) + path * Math.cos(angle));
            tY = (int) ((this.smellRange.getCenterY() + this.smellRange.getTranslateY()) + path * Math.sin(angle));
        } while(!isValidTarget(tX, tY));
        this.provisionalTarget.getBody().setCenterX(tX);
        this.provisionalTarget.getBody().setCenterY(tY);
        this.lastAngle = anAngle;
    }

    /**
     * FoodPreferences Getter
     * @return animal's Food Preferences in an array of strings
     */
    public ArrayList<String> getFoodPreferences() {
        return foodPreferences;
    }

    /**
     * Checks if animal is poisoned
     * @return true if animal is poisoned
     */
    public boolean isPoisoned() {
        return poisoned;
    }

    /**
     * HouseTarget Getter
     * @return  animal's house target
     */
    public Target getHouseTarget() {
        return houseTarget;
    }

    /**
     * ByeHome Getter
     * @return animal's time for not checking collisions with the habbitat
     */
    public int getByeHome() {
        return byeHome;
    }

    /**
     * Tries Getter
     * @return animal's amount of tries to get to a main target but colliding with obstacles
     */
    public int getTries() {
        return tries;
    }

    /**
     * CollisionCycles Getter
     * @return Cycle in which the animal collided against an obstacle while trying to go to the main target
     */
    public int getCollisionCycles() {
        return collisionCycles;
    }

    /**
     * Gender Getter
     * @return animal's gender
     */
    public boolean getGender() {
        return gender;
    }

    /**
     * Pregnant Getter
     * @return time left in order to have another baby in cycles
     */
    public int getPregnant() {
        return pregnant;
    }

    /**
     * Pregnant Setter
     * @param pregnant time left in order to have another baby
     */
    public void setPregnant(int pregnant) {
        this.pregnant = pregnant;
    }

    /**
     * Gender Setter
     * @param gender animal's gender
     */
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    /**
     * Collision Cycles Setter
     * @param collisionCycles Cycle in which the animal collided against an obstacle while trying to go to the main target
     */
    public void setCollisionCycles(int collisionCycles) {
        this.collisionCycles = collisionCycles;
    }


    /**
     * Tries Setter
     * @param tries animal's amount of tries to get to a main target but colliding with obstacles
     */
    public void setTries(int tries) {
        this.tries = tries;
    }

    /**
     * ByeHome Setter
     * @param byeHome animal's time for not checking collisions with the habbitat
     */
    public void setByeHome(int byeHome) {
        this.byeHome = byeHome;
    }

    /**
     * HouseTarget Setter
     * @param houseTarget animal's house target
     */
    public void setHouseTarget(Target houseTarget) {
        this.houseTarget = houseTarget;
    }

    /**
     *Poisoned Setter
     * @param poisoned boolean that states if the animal is poisoned or not
     */
    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }

    /**
     * Adds a Food Preference into the array of Food Preferences
     * @param foodPreference food Preference to add into the Array
     */
    public void addFoodPreferences(String foodPreference) {
        this.foodPreferences.add(foodPreference);
    }

    /**
     * Metabolism Setter
     * @param metabolism amount of food and energy consumed in every cycle
     */
    public void setMetabolism(double metabolism) {
        this.metabolism = metabolism;
    }

    /**
     * Main Target Setter with a target as input
     * @param mainTarget animal's main target
     */
    public void setMainTarget(Target mainTarget) {
        this.mainTarget = mainTarget;
    }

    /**
     * Main Target Setter with x and y coordinates and radius
     * @param x x coordinate in pixels
     * @param y y coordinate in pixels
     * @param rad Main target radius
     */
    public void setMainTarget(double x, double y, double rad) {
        this.mainTarget.getBody().setCenterX(x);
        this.mainTarget.getBody().setCenterY(y);
        this.mainTarget.getBody().setRadius(rad);
    }

    /**
     * Clears the Main Target setting it's center position at (0, 0)
     */
    public void clearMainTarget() {
        this.mainTarget.getBody().setCenterX(0);
        this.mainTarget.getBody().setCenterY(0);
    }

    /**
     * Animal Stats Setter with maximum 2 decimal figures
     */
    public void setStats() {
        String print = "MaxFood: " + this.maxFood + "\n" +
                "Food: " + String.format( "%.2f", this.food) + "\n" +
                "MaxEnergy: " + this.maxEnergy + "\n" +
                "Energy: " +  String.format( "%.2f", this.energy) + "\n" +
                "Strength: " + this.strength + "\n" +
                "FoodCarrier: " + String.format( "%.2f", this.foodCarring) + "\n";
        this.stats.setText(print);
        this.stats.setTranslateX(this.getBody().getTranslateX());
        this.stats.setTranslateY(this.getBody().getTranslateY());
    }

    /**
     * Max Enery Setter
     * @param maxEnergy animal's maximum amount of energy
     */
    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    /**
     * MaxFood Setter
     * @param maxFood animal's maximum amount of food in its body
     */
    public void setMaxFood(int maxFood) {
        this.maxFood = maxFood;
    }

    /**
     * Food Setter
     * @param food animal's amount of food in its body
     */
    public void setFood(double food) {
        this.food = food;
    }

    /**
     * Speed Setter
     * @param speed animal's speed
     */
    public void setSpeed(double speed) {
		this.speed = speed;
	}

    /**
     * Name Setter
     * @param name animal's name
     */
    public void setName(String name){
		this.name = name;
	}

    /**
     * ID Setter
     * @param ID animal's ID
     */
    public void setID(int ID){
		String print = "ID: " + ID + "\n";

		this.ID.setText(print);
		this.ID.setTranslateX(this.getBody().getTranslateX());
		this.ID.setTranslateY(this.getBody().getTranslateY());
	}

    /**
     * Energy Setter
     * @param energy animal's energy
     */
    public void setEnergy(double energy){
		this.energy = energy;
	}

    /**
     * Strength Setter
     * @param strength animals strength
     */
    public void setStrenght(int strength){
		this.strength = strength;
	}

    /**
     * FoodCarring Setter
     * @param foodCarring animal's amount of food that its carrying
     */
    public void setFoodCarring(double foodCarring){
		this.foodCarring = foodCarring;
	}

    /**
     * MinFoodConsumtion Setter
     * @param minFoodCons animal's minimum acceptable amount of energy that food needs to have in order to eat it
     */
    public void setMinFoodCons(int minFoodCons){
		this.minFoodCons = minFoodCons;
	}

    /**
     * SmellRange Setter
     *
     * Sets a Circle with it's center at the animal's center with a radius as the one input
     * @param smellRange radius of animal's SmellRange
     */
    public void setSmellRange(double smellRange){
		this.smellRange.setRadius(smellRange);
		this.smellRange.setCenterX(this.Body.getCenterX());
		this.smellRange.setCenterY(this.Body.getCenterY());
	}

    /**
     * Age Setter
     * @param age animal's Age
     */
    public void setAge(int age){
		this.age = age;
	}

    /**
     * AngleRange Setter
     * @param angleRange animal's angle range to move in
     */
    public void setAngleRange(int angleRange){
		this.angleRange = angleRange;
	}

    /**
     * Life Expectancy Setter
     * @param lifeExpectancy animal's Life expectancy
     */
    public void setLifeExpectancy(int lifeExpectancy){
		this.lifeExpectancy = lifeExpectancy;
	}

    /**
     * Size Setter
     *
     * @param size animal's Size (Body's Radius)
     */
    public void setSize(int size){
		this.Body.setRadius(size);
	}

    /**
     * Dx Setter
     * @param dx change of the animal's x coordinate in every cicle
     */
    public void setDx(double dx){
		this.dx = dx;
	}

    /**
     * Dy Setter
     * @param dy change of the animal's x coordinate in every cicle
     */
    public void setDy(double dy){
		this.dy = dy;
	}

    /**
     * Last Angle Setter
     * @param lastAngle last angle used by the animal
     */
    public void setLastAngle(int lastAngle) {
        this.lastAngle = lastAngle;
    }


    /**
     * Calculates The angle the animal needs to get to the coordinates input
     *
     * @param targetX x coordinate
     * @param targetY y coordinate
     * @return angle needed to go to (TargetX, TargetY)
     */
    // get angle for going to the target
	public double getAngleTo(double targetX, double targetY){
        double thisX = Body.getCenterX() + Body.getTranslateX();
        double thisY = Body.getCenterY() + Body.getTranslateY();
        return Math.atan2(targetY - thisY, targetX - thisX);
    }


    /**
     * Calculates Dx and Dy needed in order to go to the provisional Target
     *
     * It takes into account the Speed, The angle and if the animal is poisoned or not
     */
    // Direct the bug to the target
    public void directDxDy(){
        double targetX = (this.provisionalTarget.getBody().getCenterX() + this.provisionalTarget.getBody().getTranslateX());
        double targetY = (this.provisionalTarget.getBody().getCenterY() + this.provisionalTarget.getBody().getTranslateY());
        double angle = getAngleTo(targetX, targetY);

        if (this.isPoisoned()){
            setDx((Math.cos(angle) * getSpeed()/(double)2));
            setDy((Math.sin(angle) * getSpeed()/(double)2));
        }
        else {
            setDx((Math.cos(angle) * getSpeed()));
            setDy((Math.sin(angle) * getSpeed()));
        }
    }

    /**
     * Checks if the target with the coordinates given is valid or not
     *
     * if the target is in a reachable place: enough far away from the walls  so that the animal can get as closest as possible to the wall without going passed it
     * @param tx x coordinate of the target
     * @param ty y coordinates of the target
     * @return return true if valid false if not
     */
    public boolean isValidTarget(int tx, int ty){
        if (tx > (MainAnimation.width-Body.getRadius()+smellRange.getRadius()) || ty > (MainAnimation.height-Body.getRadius()+smellRange.getRadius()-50) || tx < -smellRange.getRadius()+Body.getRadius() || ty < -smellRange.getRadius()+Body.getRadius()+30) {
            return false;
        }
        return true;
    }

    /**
     * Makes the Animal go back one step and turn from 90 to 270 degrees in order to avoid obstacles or walls
     */
    public void getOut(){
        Random rnd = new Random();
        this.Body.setTranslateX(this.Body.getTranslateX() - this.dx);
        this.Body.setTranslateY(this.Body.getTranslateY() - this.dy);

        this.lastAngle += rnd.nextInt(180)+90;
        if (this.lastAngle >= 360) this.lastAngle -= 360;

    }

    /**
     * Displays Animal Fixed parameters
     */
    public void displayAnimal(){
		String print;

        String g;
        if (this.gender) g = "Female";
        else g = "Male";

		print = "Name: " + this.name + "\n" +
				this.ID.getText() +
                "Gender: " + g + "\n"+
				"Size: " + this.getBody().getRadius() + "\n" +
				"Smell Range: " + this.smellRange.getRadius() + "\n" +
				"Max Energy: " + this.maxEnergy + "\n" +
				"Max Food: " + this.maxFood + "\n" +
				"Metabolism: " + this.metabolism + "\n" +
				"Strength: " + this.strength + "\n" +
				"Angle of movement: " + this.angleRange + "\n" +
				"Speed: " + this.speed ;

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Animal Chosen:");
		alert.setContentText(print);
        alert.setHeaderText(null);
        alert.getDialogPane().setMaxWidth(300);
		alert.showAndWait();
	}

    /**
     * Updates The main target
     *
     * if house target exists and the energy or the food are low (33%) go to house
     * else if food target exists and food is low(33%) go to food
     *
¡     */
    public void updateMainTarget(){
        if (this.houseTarget.getBody().getCenterX() != 0 && this.houseTarget.getBody().getCenterY() != 0) {
            if (this.byeHome == 0) {
                if (this.energy <= this.maxEnergy / 3 || this.food <= this.maxFood / 3) {
                    this.setMainTarget(this.houseTarget.getBody().getCenterX(), this.houseTarget.getBody().getCenterY(), this.houseTarget.getBody().getRadius());
                }
            }

        }
        /*
        else if (this.foodTarget.getBody().getCenterX() != 0 && this.foodTarget.getBody().getCenterY() != 0){
            if (this.food <= this.maxFood / 3) {
                // go to food
            }
        }
        */
    }

    /**
     * Update animal's target to reach main target
     *
     * if Reached main target or not
     *      get random target
     */
    public void updateTarget(){
        double x1 = this.mainTarget.getBody().getCenterX();
        double x2 = this.getPosX();
        double y1 = this.mainTarget.getBody().getCenterY();
        double y2 = this.getPosY();

        double distance1 = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
        double distance2 = this.getBody().getRadius() + this.mainTarget.getBody().getRadius();

        // if animal has reached main target delete it and look for new target
		if (this.mainTarget.getBody().getCenterX() != 0 &&  this.mainTarget.getBody().getCenterY() != 0) {
			if (distance1 <= distance2) {
				this.clearMainTarget();
                getRandomLocalTarget();
			}
			else getLocalTarget();
		}
		else if (this.provisionalTarget.getBody().getCenterX() == 0 &&  this.provisionalTarget.getBody().getCenterY() == 0){
			getRandomLocalTarget();
		}

        //if animal is tauching the inside the wall, go back and change angle and look for random target
		if (this.getPosX() > (MainAnimation.width - Body.getRadius()) || this.getPosY() > (MainAnimation.height - Body.getRadius() - 50) || this.getPosX() < Body.getRadius() || this.getPosY() < Body.getRadius() + 30) {
			this.getOut();
			getRandomLocalTarget();

		}
	}

    /**
     * Animal Consumes food that his body has.
     *
     * if he has consumed half of his food, he starts consuming from the carried food
     */
    public void updateFood(){
        double metabolism;

        if (this.isPoisoned()) {
            metabolism = this.metabolism*2;
        }
        else metabolism = this.metabolism;

        this.setFood(this.getFood()-1*metabolism);

        if (this.getFood() < this.getMaxFood()/2){
            if (this.foodCarring > 0){
                this.setFood(this.getFood() + 1*metabolism);
                this.setFoodCarring(this.getFoodCarring() - 1*metabolism);
            }
        }

    }

    /**
     *Update animal
     *
     * Update Target
     * Move
     * Update Food
     * Update Energy
     * Set Stats
     */
    public void update(){

        this.updateTarget();

        directDxDy();

		this.Body.setTranslateX(this.Body.getTranslateX()+ this.dx);
		this.Body.setTranslateY(this.Body.getTranslateY() + this.dy);
		this.smellRange.setTranslateX(this.Body.getTranslateX());
		this.smellRange.setTranslateY(this.Body.getTranslateY());
		this.ID.setTranslateX(this.Body.getTranslateX());
		this.ID.setTranslateY(this.Body.getTranslateY());

        this.updateFood();
        this.setEnergy(this.getEnergy()-1*this.getMetabolism());
        if (this.getPregnant() > 0) this.setPregnant(this.getPregnant()-1);

        this.setStats();
	}
}
