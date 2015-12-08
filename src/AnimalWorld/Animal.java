package AnimalWorld;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.shape.Circle;

public abstract class Animal extends Circle{
	private String name;
	private int ID;
	private int energy;
	private int strenght;
	private int foodCarring;
	private int minFoodCons;	// minimum of calories that the meat has to be in order to eat it
	private int smellRange;
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
	
	public int getSmellRange(){
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
		return (int) this.getRadius();
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
		this.smellRange = smellRange;
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
		this.setRadius(size);
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
	
	public boolean collideAnimals(ArrayList<Animal> circles, boolean modifySpeeds){
			// check collision against other bugs	
		for (int i = 0; i < circles.size(); i++){
			if (this.getCenterX() != circles.get(i).getCenterX() && this.getCenterY() != circles.get(i).getCenterY()){
				
				// (x2-x1)^2 + (y1-y2)^2 <= (r1+r2)^2
				int a = (int) Math.pow(((this.getCenterX() + this.getTranslateX()) - (circles.get(i).getCenterX() + circles.get(i).getTranslateX())), 2);
				int b = (int) Math.pow(((this.getCenterY() + this.getTranslateY()) - (circles.get(i).getCenterY() + circles.get(i).getTranslateY())), 2);
				int c = (int) Math.pow(this.getRadius() + circles.get(i).getRadius(), 2);
				
				if (a + b <= c){
					if (modifySpeeds) this.newSpeeds(circles, i);
					else return true;
				}
			}
		}
		return false;
	}
	
	public void collideWalls(World world){
		// check collision against walls
		if (this.getTranslateX()+ this.getCenterX() < this.getRadius() && dx < 0)	dx = -dx;
		else if (world.getWidth() - this.getTranslateX()- this.getCenterX()  < this.getRadius() && dx > 0) dx = -dx;
		if (this.getTranslateY()+ this.getCenterY()  < this.getRadius()+25 && dy < 0)	dy = -dy;
		else if (world.getHeight() - 50 - this.getCenterY() - this.getTranslateY()  < this.getRadius() && dy > 0) dy = -dy;
	
	}
	
	public boolean collideObstacle(World world, boolean modifyDirection){
		for (int i = 0; i < world.obstacleList.size(); i++){
			if (Circle.class.isInstance(world.obstacleList.get(i).object)){
					if (this.getCenterX() != world.obstacleList.get(i).getX() && this.getCenterY() != world.obstacleList.get(i).getY()){
						
						// (x2-x1)^2 + (y1-y2)^2 <= (r1+r2)^2
						int a = (int) Math.pow(((this.getCenterX() + this.getTranslateX()) - (world.obstacleList.get(i).getX() + world.obstacleList.get(i).object.getTranslateX())), 2);
						int b = (int) Math.pow(((this.getCenterY() + this.getTranslateY()) - (world.obstacleList.get(i).getY() + world.obstacleList.get(i).object.getTranslateY())), 2);
						int c = (int) Math.pow(this.getRadius() + world.obstacleList.get(i).getSize(), 2);
						
						if (a + b <= c) {
							if (modifyDirection) this.newSpeedsO(world.obstacleList, i);
							else return true;
						}
					}
				}
			else {
				if (this.intersects(world.obstacleList.get(i).object.getBoundsInParent())) return true;
			}
		}
		return false;
	}
	
	public boolean collideFood(World world){
		for (int i = 0; i < world.foodList.size(); i++){
			if (this.intersects(world.foodList.get(i).getBoundsInParent())) return true;
		}		
		return false;
	}
	
	public void newSpeeds(ArrayList<Animal> circles, int i){
		float newdx = 
				  (float) ((this.dx * (this.getRadius() - circles.get(i).getRadius()) + (2 * circles.get(i).getRadius() * circles.get(i).dx)) 
				 / (this.getRadius() + circles.get(i).getRadius()));
		float newdy = 
				  (float) ((this.dy * (this.getRadius() - circles.get(i).getRadius()) + (2 * circles.get(i).getRadius() * circles.get(i).dy)) 
				 / (this.getRadius() + circles.get(i).getRadius()));
		circles.get(i).dx = 
				  (float) ((circles.get(i).dx * (circles.get(i).getRadius() - this.getRadius()) + (2 * this.getRadius() * this.dx)) 
						 / (circles.get(i).getRadius() + this.getRadius()));
		circles.get(i).dy = 
				 (float) ((circles.get(i).dy * (circles.get(i).getRadius() - this.getRadius()) + (2 * this.getRadius() * this.dy)) 
						 / (circles.get(i).getRadius() + this.getRadius()));
		this.setTranslateX(this.getTranslateX()- dx);
		this.setTranslateY(this.getTranslateY()- dy);  
		this.dx = newdx;
		this.dy = newdy;	
	}
	
	public void newSpeedsO(ArrayList<Obstacle> circles, int i){
		float newdx = 
				  (float) ((this.dx * (this.getRadius()) 
				 / (this.getRadius() + circles.get(i).getSize())));
		float newdy = 
				  (float) ((this.dy * (this.getRadius() - circles.get(i).getSize())) 
				 / (this.getRadius() + circles.get(i).getSize()));
		this.setTranslateX(this.getTranslateX()- dx);
		this.setTranslateY(this.getTranslateY()- dy);  
		this.dx = newdx;
		this.dy = newdy;	
	}
	
	
	public void update(){
		this.setTranslateX(this.getTranslateX()+ this.dx);
		this.setTranslateY(this.getTranslateY() + this.dy);
	}
}
