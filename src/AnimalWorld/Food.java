package AnimalWorld;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Food extends Rectangle{
	private int energy;
	private String type;
	
	public Food(World world){
		Random rnd = new Random();
		this.energy = rnd.nextInt(200)+5;

		switch (rnd.nextInt(2)+1) {
	        case 1: 
	        	this.type = "Meat";
				this.setFill(Color.RED);
	            break;
	        case 2: 
	        	this.type = "NonMeat";
	        	this.setFill(Color.GREEN);
	            break;
	        default:
	        	this.type = "NonMeat";
	        	this.setFill(Color.GREEN);
	            break;
	    }
		
		this.setHeight(rnd.nextInt(50)+5);
		this.setWidth(this.getHeight()); 
		
		boolean intersecting = false;
		do{
			this.setX(rnd.nextInt(world.getWidth() - (int)this.getHeight()*2)+ this.getHeight());
			this.setY(rnd.nextInt(world.getHeight() - (int)this.getHeight()*2 - 80)+ this.getHeight() + 50);
			for(int i = 0; i < world.foodList.size(); i++){
				intersecting = this.intersects(world.foodList.get(i).getBoundsInParent());
				if (intersecting) break;
			}
			System.out.println("HELLO3");

			for(int i = 0; i < world.obstacleList.size(); i++){
				if (intersecting) break;
				intersecting = this.intersects(world.obstacleList.get(i).object.getBoundsInParent());
			}
			
		}while(intersecting);
		
	}
}
