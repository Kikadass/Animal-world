package AnimalWorld;

import java.util.ArrayList;
import java.util.Random;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

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
	private int strenght;
	private double foodCarring;
	private int minFoodCons;	// minimum of calories that the meat has to be in order to eat it
	private int age;			// in days
	private int forgetfulness;
	private int nestX;
	private int nestY;
	private int angleRange;			// angleRange of normal movement
    private int lastAngle;
	private int lifeExpectancy;	// how old are can that specie live for
	private int minSize;
	private int maxSize;
    private boolean poisoned = false;
	private double dx;
	private double dy;
	private double speed;
    private ArrayList<String> foodPreferences = new ArrayList<String>();
    private Target houseTarget = new Target(0, 0, 1);
    private Target foodTarget;
    private Target waterTarget;
    private Target mainTarget = new Target (0,0, 1);
    private Target provisionalTarget = new Target(0, 0, 3);
    private int byeHome = 0;
    private int tries = 0;
    private int collisionCycles = -50;

    public Animal(){
		this.name = new String();
		this.energy = 0;
	}

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
        GridPane.setConstraints(name, 1, 0);
        grid.getChildren().add(name);
        Label label1 = new Label("Name:");
        GridPane.setConstraints(label1, 0, 0);
        grid.getChildren().add(label1);

        //Defining the smellRange text field
        final TextField smellRange = new TextField();
        smellRange.setPromptText("eg. 30");
        smellRange.setPrefColumnCount(10);
        smellRange.getText();
        GridPane.setConstraints(smellRange, 1, 1);
        grid.getChildren().add(smellRange);
        Label label3 = new Label("Smell Range:");
        GridPane.setConstraints(label3, 0, 1);
        grid.getChildren().add(label3);


        //Defining the maxEnergy text field
        final TextField maxEnergy = new TextField();
        maxEnergy.setPromptText("eg. 200");
        GridPane.setConstraints(maxEnergy, 1, 2);
        grid.getChildren().add(maxEnergy);
        Label label4 = new Label("Max Energy:");
        GridPane.setConstraints(label4, 0, 2);
        grid.getChildren().add(label4);

        //Defining the Max Food text field
        final TextField maxFood = new TextField();
        maxFood.setPromptText("eg. 200");
        GridPane.setConstraints(maxFood, 1, 3);
        grid.getChildren().add(maxFood);
        Label label5 = new Label("Max Food:");
        GridPane.setConstraints(label5, 0, 3);
        grid.getChildren().add(label5);

        //Defining the Metabolism text field
        final TextField metabolism = new TextField();
        metabolism.setPromptText("eg. 0.05");
        GridPane.setConstraints(metabolism, 1, 4);
        grid.getChildren().add(metabolism);
        Label label6 = new Label("Metabolism:");
        GridPane.setConstraints(label6, 0, 4);
        grid.getChildren().add(label6);

        //Defining the Strength text field
        final TextField strength = new TextField();
        strength.setPromptText("eg. 200");
        GridPane.setConstraints(strength, 1, 5);
        grid.getChildren().add(strength);
        Label label7 = new Label("Strength:");
        GridPane.setConstraints(label7, 0, 5);
        grid.getChildren().add(label7);

        //Defining the Angle of Movement text field
        final TextField angle = new TextField();
        angle.setPromptText("eg. 30");
        GridPane.setConstraints(angle, 1, 6);
        grid.getChildren().add(angle);
        Label label8 = new Label("Angle of Movement:");
        GridPane.setConstraints(label8, 0, 6);
        grid.getChildren().add(label8);

        //Defining the Speed text field
        final TextField speed = new TextField();
        speed.setPromptText("eg. 1.5");
        GridPane.setConstraints(speed, 1, 7);
        grid.getChildren().add(speed);
        Label label9 = new Label("Speed:");
        GridPane.setConstraints(label9, 0, 7);
        grid.getChildren().add(label9);

        //Defining the Apply button
        Button apply = new Button("Apply");
        GridPane.setConstraints(apply, 0, 9);
        grid.getChildren().add(apply);

        //Defining the Current Values button
        Button current = new Button("Current Values");
        GridPane.setConstraints(current, 1, 9);
        grid.getChildren().add(current);

        //Defining the Clear button
        Button clear = new Button("Clear");
        GridPane.setConstraints(clear, 2, 9);
        grid.getChildren().add(clear);

        //Adding a Label
        final Label label = new Label();
        GridPane.setConstraints(label, 0, 8);
        GridPane.setColumnSpan(label, 2);
        grid.getChildren().add(label);



        //Setting an action for the Apply button
        apply.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if ((name.getText() != null && !name.getText().isEmpty()) &&
                    (smellRange.getText() != null && !smellRange.getText().isEmpty()) &&
                    (maxEnergy.getText() != null && !maxEnergy.getText().isEmpty()) &&
                    (maxFood.getText() != null && !maxFood.getText().isEmpty()) &&
                    (metabolism.getText() != null && !metabolism.getText().isEmpty()) &&
                    (strength.getText() != null && !strength.getText().isEmpty()) &&
                    (angle.getText() != null && !angle.getText().isEmpty()) &&
                    (speed.getText() != null && !speed.getText().isEmpty())){

                    if (Double.parseDouble(smellRange.getText()) >= getBody().getRadius()+10) {
                        setName(name.getText());
                        setSmellRange(Double.parseDouble(smellRange.getText()));
                        setMaxEnergy(Integer.parseInt(maxEnergy.getText()));
                        setMaxFood(Integer.parseInt(maxFood.getText()));
                        setMetabolism(Double.parseDouble(metabolism.getText()));
                        setStrenght(Integer.parseInt(strength.getText()));
                        setAngleRange(Integer.parseInt(angle.getText()));
                        setSpeed(Double.parseDouble(speed.getText()));
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
                smellRange.setText(String.valueOf(getSmellRange().getRadius()));
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

	public String getName(){
		return this.name;
	}
	
	public Text getID(){
		return this.ID;
	}
	
	public double getEnergy(){
		return this.energy;
	}
	
	public int getStrenght(){
		return this.strenght;
	}
	
	public double getFoodCarring(){
		return this.foodCarring;
	}
	
	public int getMinFoodCons(){
		return this.minFoodCons;
	}
	
	public Circle getSmellRange(){
		return this.smellRange;
	}
	
	public int getAge(){
		return this.age;
	}
	
	public int getForgetfulness(){
		return this.forgetfulness;
	}
	
	public int getNestX(){
		return this.nestX;
	}
	
	public int getNestY(){
		return this.nestY;
	}
	
	public int getAngleRange(){
		return this.angleRange;
	}
	
	public int getLifeExpectancy(){
		return this.lifeExpectancy;
	}
	
	public int getSize(){
		return (int) this.Body.getRadius();
	}
	
	public int getMinSize(){
		return this.minSize;
	}
	
	public int getMaxSize(){
		return this.maxSize;
	}
	
	public double getDx(){
		return this.dx;
	}
	
	public double getDy(){
		return this.dy;
	}
	
	public Circle getBody(){
		return this.Body;
	}

	public double getSpeed() {
		return this.speed;
	}

    public Target getProvisionalTarget() {
        return provisionalTarget;
    }

    public double getFood() {
        return food;
    }

    public int getMaxFood() {
        return maxFood;
    }

    public int getMaxEnergy() {
        return maxEnergy;
    }

    public Text getStats() {
        return stats;
    }

    public Target getMainTarget() {
        return mainTarget;
    }

    public double getMetabolism() {
        return metabolism;
    }

	public int getPosX(){
		return (int)(this.Body.getCenterX() + this.Body.getTranslateX());
	}

	public int getPosY(){
		return (int)(this.Body.getCenterY() + this.Body.getTranslateY());
	}

    public void getLocalTarget(){
        double angle = getAngleTo(this.mainTarget.getBody().getCenterX(), this.mainTarget.getBody().getCenterY());
        int path = (int)this.smellRange.getRadius();
        int tX = (int) ((this.smellRange.getCenterX() + this.smellRange.getTranslateX()) + path * Math.cos(angle));
        int tY = (int) ((this.smellRange.getCenterY() + this.smellRange.getTranslateY()) + path * Math.sin(angle));
        this.provisionalTarget.getBody().setCenterX(tX);
        this.provisionalTarget.getBody().setCenterY(tY);
    }

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

    public ArrayList<String> getFoodPreferences() {
        return foodPreferences;
    }

    public boolean isPoisoned() {
        return poisoned;
    }

    public Target getHouseTarget() {
        return houseTarget;
    }

    public int getByeHome() {
        return byeHome;
    }

    public int getTries() {
        return tries;
    }

    public int getCollisionCycles() {
        return collisionCycles;
    }

    public void setCollisionCycles(int collisionCycles) {
        this.collisionCycles = collisionCycles;
    }


    public void setTries(int tries) {
        this.tries = tries;
    }

    public void setByeHome(int byeHome) {
        this.byeHome = byeHome;
    }

    public void setHouseTarget(Target houseTarget) {
        this.houseTarget = houseTarget;
    }

    public void setPoisoned(boolean poisoned) {
        this.poisoned = poisoned;
    }

    public void addFoodPreferences(String foodPreference) {
        this.foodPreferences.add(foodPreference);
    }

    public void setMetabolism(double metabolism) {
        this.metabolism = metabolism;
    }

    public void setMainTarget(Target mainTarget) {
        this.mainTarget = mainTarget;
    }

    public void setMainTarget(double x, double y, double rad) {
        this.mainTarget.getBody().setCenterX(x);
        this.mainTarget.getBody().setCenterY(y);
        this.mainTarget.getBody().setRadius(rad);
    }

    public void clearMainTarget() {
        this.mainTarget.getBody().setCenterX(0);
        this.mainTarget.getBody().setCenterY(0);
    }

    public void setStats() {
        String print = "MaxFood: " + this.maxFood + "\n" +
                "Food: " + String.format( "%.2f", this.food) + "\n" +
                "MaxEnergy: " + this.maxEnergy + "\n" +
                "Energy: " +  String.format( "%.2f", this.energy) + "\n" +
                "Strenght: " + this.strenght + "\n" +
                "FoodCarrier: " + String.format( "%.2f", this.foodCarring) + "\n";
        this.stats.setText(print);
        this.stats.setTranslateX(this.getBody().getTranslateX());
        this.stats.setTranslateY(this.getBody().getTranslateY());
    }

    public void setMaxEnergy(int maxEnergy) {
        this.maxEnergy = maxEnergy;
    }

    public void setMaxFood(int maxFood) {
        this.maxFood = maxFood;
    }

    public void setFood(double food) {
        this.food = food;
    }

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public void setID(int ID){
		String print = "ID: " + ID + "\n";

		this.ID.setText(print);
		this.ID.setTranslateX(this.getBody().getTranslateX());
		this.ID.setTranslateY(this.getBody().getTranslateY());
	}

	public void setEnergy(double energy){
		this.energy = energy;
	}
	
	public void setStrenght(int strenght){
		this.strenght = strenght;
	}
	
	public void setFoodCarring(double foodCarring){
		this.foodCarring = foodCarring;
	}
	
	public void setMinFoodCons(int minFoodCons){
		this.minFoodCons = minFoodCons;
	}
	
	public void setSmellRange(double smellRange){
		this.smellRange.setRadius(smellRange);
		this.smellRange.setCenterX(this.Body.getCenterX());
		this.smellRange.setCenterY(this.Body.getCenterY());
	}
	
	public void setAge(int age){
		this.age = age;
	}
	
	public void setForgetfulness(int forgetfulness){
		this.forgetfulness = forgetfulness;
	}
	
	public void setNestX(int nestX){
		this.nestX = nestX;
	}
	
	public void setNestY(int nestY){
		this.nestY = nestY;
	}
	
	public void setAngleRange(int angleRange){
		this.angleRange = angleRange;
	}
	
	public void setLifeExpectancy(int lifeExpectancy){
		this.lifeExpectancy = lifeExpectancy;
	}
	
	public void setSize(int size){
		this.Body.setRadius(size);
	}
	
	public void setMinSize(int minSize){
		this.minSize = minSize;
	}
	
	public void setMaxSize(int maxSize){
		this.minSize = maxSize;
	}

	public void setDx(double dx){
		this.dx = dx;
	}
	
	public void setDy(double dy){
		this.dy = dy;
	}

    public void setProvisionalTarget(Target provisionalTarget) {
        this.provisionalTarget = provisionalTarget;
    }
	
	public void collideWalls(World world){
		// check collision against walls
		if (this.Body.getTranslateX()+ this.Body.getCenterX() < this.Body.getRadius() && dx < 0)	dx = -dx;
		else if (world.getWidth() - this.Body.getTranslateX()- this.Body.getCenterX()  < this.Body.getRadius() && dx > 0) dx = -dx;
		if (this.Body.getTranslateY()+ this.Body.getCenterY()  < this.Body.getRadius()+25 && dy < 0)	dy = -dy;
		else if (world.getHeight() - 50 - this.Body.getCenterY() - this.Body.getTranslateY()  < this.Body.getRadius() && dy > 0) dy = -dy;
	
	}
	
	// get angle for going to the target
	public double getAngleTo(double targetX, double targetY){
        double thisX = Body.getCenterX() + Body.getTranslateX();
        double thisY = Body.getCenterY() + Body.getTranslateY();
        return Math.atan2(targetY - thisY, targetX - thisX);
    }


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

    public boolean isValidTarget(int tx, int ty){
        if (tx > (NewMenu.width-Body.getRadius()+smellRange.getRadius()) || ty > (NewMenu.height-Body.getRadius()+smellRange.getRadius()-50) || tx < Body.getRadius()-smellRange.getRadius() || ty < Body.getRadius()+30-smellRange.getRadius()) {
            if (this.Body.getCenterX() + this.Body.getTranslateX() > (NewMenu.width - Body.getRadius()) || this.Body.getCenterY() + this.Body.getTranslateY() > (NewMenu.height - Body.getRadius() - 50) || this.Body.getCenterX() + this.Body.getTranslateX() < Body.getRadius() || this.Body.getCenterY() + this.Body.getTranslateY() < Body.getRadius() + 30) {
                return false;
            }
        }
        return true;
    }

    public void getOut(){
        Random rnd = new Random();
        this.Body.setTranslateX(this.Body.getTranslateX() - this.dx);
        this.Body.setTranslateY(this.Body.getTranslateY() - this.dy);

        this.lastAngle += rnd.nextInt(180)+90;
        if (this.lastAngle >= 360) this.lastAngle -= 360;

    }

	public void displayAnimal(){
		String print;

		print = "Name: " + this.name + "\n" +
				this.ID.getText() +
				"Size: " + this.getBody().getRadius() + "\n" +
				"Smell Range: " + this.smellRange.getRadius() + "\n" +
				"Max Energy: " + this.maxEnergy + "\n" +
				"Max Food: " + this.maxFood + "\n" +
				"Metabolism: " + this.metabolism + "\n" +
				"Strength: " + this.strenght + "\n" +
				"Angle of movement: " + this.angleRange + "\n" +
				"Speed: " + this.speed ;

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Animal Chosen:");
		alert.setContentText(print);
        alert.setHeaderText(null);
        alert.getDialogPane().setMaxWidth(300);
		alert.showAndWait();
	}

    public void updateMainTarget(){
        if (this.houseTarget.getBody().getCenterX() != 0 && this.houseTarget.getBody().getCenterY() != 0) {
            if (this.energy <= this.maxEnergy/3){
                if(this.food <= this.maxFood/3){
                    if (this.energy <= this.food){
                        this.setMainTarget(this.houseTarget);
                    }
                }
                else this.setMainTarget(this.houseTarget.getBody().getCenterX(), this.houseTarget.getBody().getCenterY(), this.houseTarget.getBody().getRadius());
            }
        }
    }

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

		if (this.getPosX() > (NewMenu.width - Body.getRadius()) || this.getPosY() > (NewMenu.height - Body.getRadius() - 50) || this.getPosX() < Body.getRadius() || this.getPosY() < Body.getRadius() + 30) {
			this.getOut();
			getRandomLocalTarget();

		}
	}

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
            else {
                //goHome
            }
        }

    }

	public void update(){
		/*
        select maintarget
            if energy too low
             go home to sleep
            else if food too low
                if distance of food is more than distance of home
                    go home
                else go foodTarget


            ...... optional if additional time ......
            else if water too low
                if distance of water is more than distance of home
                    go home
                else go waterTarget
             ...... optional if additional time ......



        select provisionaltarget
            go in the direction of main target
                getting a random point inside the angle.

		go to provisionaltarget
		    each cycle substract 1 energy, 1 food, (1 water)

		    if collision with obstacle (inner circle)
		        ...... optional if additional time...... if obstacle's weight < strenght move obstacle
		        choose random target not taking into account the angle
            if collision with food (smell circle)
                detect position of detection
                set its position as provisional target
		*/

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

        this.setStats();
	}
}
