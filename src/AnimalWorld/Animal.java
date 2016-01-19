package AnimalWorld;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.control.Alert;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class Animal{
	private Circle Body= new Circle(), smellRange = new Circle();
    private Text stats = new Text();
	private String name;
	private Text ID = new Text();
	private int energy;
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
	private double dx;
	private double dy;
	private double speed;
    private Target houseTarget;
    private Target foodTarget;
    private Target waterTarget;
    private Target mainTarget = new Target (0,0, 1);
    private Target provisionalTarget = new Target(0, 0, 3);

	
	public Animal(){
		this.name = new String();
		this.energy = 0;
	}

	public String getName(){
		return this.name;
	}
	
	public Text getID(){
		return this.ID;
	}
	
	public int getEnergy(){
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
	
	public int getangleRange(){
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

    public void setMetabolism(double metabolism) {
        this.metabolism = metabolism;
    }


    public void setMainTarget(Target mainTarget) {
        this.mainTarget = mainTarget;
    }

    public void setStats() {
        String print = "MaxFood: " + this.maxFood + "\n" +
                "Food: " + this.food + "\n" +
                "MaxEnergy: " + this.maxEnergy + "\n" +
                "Energy: " + this.energy + "\n" +
                "Strenght: " + this.strenght + "\n" +
                "FoodCarrier: " + this.foodCarring + "\n";
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

	public void setEnergy(int energy){
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
	
	public void setSmellRange(int smellRange){
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
	
	//getImage = Animal's Body
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
        setDx((Math.cos(angle) * getSpeed()));
        setDy((Math.sin(angle) * getSpeed()));
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

        this.lastAngle += rnd.nextInt(360);
        if (this.lastAngle >= 360) this.lastAngle -= 360;

    }

	public void displayAnimal(){
		String print;

		print = "Name: " + this.name + "\n" +
				"ID: " + this.ID + "\n" +
				"Size: " + this.getBody().getRadius() + "\n" +
				"Smell Range: " + this.smellRange.getRadius() + "\n" +
				"Max Energy: " + this.maxEnergy + "\n" +
				"Max Food: " + this.maxFood + "\n" +
				"Metabolism: " + this.metabolism + "\n" +
				"Strength: " + this.strenght + "\n" +
				"Angle of movement: " + this.angleRange + "\n" +
				"Speed: " + this.speed ;

		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Configuration loaded:");
		alert.setContentText(print);
		alert.showAndWait();
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
				this.setMainTarget(new Target(0, 0, 1));
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
        this.setFood(this.getFood()-1*this.metabolism);

        if (this.getFood() < this.getMaxFood()/2){
            if (this.foodCarring > 0){
                this.setFood(this.getFood() + 1*this.metabolism);
                this.setFoodCarring(this.getFoodCarring() - 1*this.metabolism);
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
        this.setEnergy(this.getEnergy()-1);

        this.setStats();
	}
}
