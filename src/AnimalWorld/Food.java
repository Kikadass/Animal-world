package AnimalWorld;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Food{
	private int energy;
	private String type;
	private Circle Body = new Circle();
	
	public Food(World world){
		Random rnd = new Random();
		this.energy = rnd.nextInt(200)+5;

		switch (rnd.nextInt(2)+1) {
	        case 1: 
	        	this.type = "Meat";
				this.Body.setFill(Color.RED);
	            break;
	        case 2: 
	        	this.type = "NonMeat";
	        	this.Body.setFill(Color.GREEN);
	            break;
	        default:
	        	this.type = "NonMeat";
	        	this.Body.setFill(Color.GREEN);
	            break;
	    }
		
		this.Body.setRadius(rnd.nextInt(50)+5); 
		
		do{
			this.Body.setCenterX(rnd.nextInt(world.getWidth() - (int)this.Body.getRadius()*2) + this.Body.getRadius());
			this.Body.setCenterY(rnd.nextInt(world.getHeight() - (int)this.Body.getRadius()*2 - 80) + this.Body.getRadius() + 50);
		}while(Collisions.collideObstacle(Body, world) || Collisions.collideAnimals(Body, world.animalList) || Collisions.collideFood(Body, world));
	}
	
	public int getEnergy(){
		return this.energy;
	}
	
	public Circle getBody(){
		return this.Body;
	}
}
