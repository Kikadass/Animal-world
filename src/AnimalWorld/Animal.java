package AnimalWorld;

import java.util.ArrayList;

import javafx.scene.shape.Circle;

public abstract class Animal{
	private Circle Body= new Circle(), smellRange = new Circle();
	private String name;
	private int ID;
	private int energy;
	private int strenght;
	private int foodCarring;
	private int minFoodCons;	// minimum of calories that the meat has to be in order to eat it
	private int age;			// in days
	private int forgetfulness;
	private int nestX;
	private int nestY;
	private int angle;			// angle of normal movement
	private int lifeExpectancy;	// how old are can that specie live for
	private int minSize;
	private int maxSize;	
	private float dx;
	private float dy;
	
	public Animal(){
		this.name = new String();
		this.energy = 0;
		this.ID = 0;
	}

	public String getName(){
		return this.name;
	}
	
	public int getID(){
		return this.ID;
	}
	
	public int getEnergy(){
		return this.energy;
	}
	
	public int getStrenght(){
		return this.strenght;
	}
	
	public int getFoodCarring(){
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
	
	public int getAngle(){
		return this.angle;
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
	
	public float getDx(){
		return this.dx;
	}
	
	public float getDy(){
		return this.dy;
	}
	
	public Circle getBody(){
		return this.Body;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setID(int ID){
		this.ID = ID;
	}
	
	public void setEnergy(int energy){
		this.energy = energy;
	}
	
	public void setStrenght(int strenght){
		this.strenght = strenght;
	}
	
	public void setFoodCarring(int foodCarring){
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
	
	public void setAngle(int angle){
		this.angle = angle;
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

	public void setDx(float dx){
		this.dx = dx;
	}
	
	public void setDy(float dy){
		this.dy = dy;
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

	/*
	// Direct the bug to the target
    public void directDxDy(){
        double targetX = (getLocalTarget().getCircle().getCenterX() + getLocalTarget().getCircle().getTranslateX());
        double targetY = (getLocalTarget().getCircle().getCenterY() + getLocalTarget().getCircle().getTranslateY());
        double angle = getAngleTo(targetX, targetY);
        setDx((Math.cos(angle) * getSpeed()));
        setDy((Math.sin(angle) * getSpeed()));
    }
	*/
	
	public void update(){
		this.Body.setTranslateX(this.Body.getTranslateX()+ this.dx);
		this.Body.setTranslateY(this.Body.getTranslateY() + this.dy);
		this.smellRange.setTranslateX(this.smellRange.getTranslateX()+ this.dx);
		this.smellRange.setTranslateY(this.smellRange.getTranslateY() + this.dy);
	}
}
