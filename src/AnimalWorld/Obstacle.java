package AnimalWorld;

import java.util.Random;

import javafx.scene.shape.*;

public class Obstacle{
	private int weight;
	private Circle Body = new Circle();
	
	public Obstacle(World world){
		Random rnd = new Random();
		this.weight = rnd.nextInt(200)+ 10;
		this.Body.setRadius(rnd.nextInt(10)+ 10);

		int tries = 0;
		do{
			tries++;
			this.Body.setCenterX(rnd.nextInt(world.getWidth() - (int)this.Body.getRadius()*2) + this.Body.getRadius());
			this.Body.setCenterY(rnd.nextInt(world.getHeight() - (int)this.Body.getRadius()*2 - 80) + this.Body.getRadius() + 30);
		}while((Collisions.collideObstacle(Body, world) || Collisions.collideAnimals(Body, world.animalList) || Collisions.collideFood(Body, world)) && tries <= 100);
        if (tries >= 100) {
            System.out.println("No space to add another obstacle");
            this.Body.setRadius(0);

        }



	}
	
	public void setX(int x){
		this.Body.setCenterX(x);
	}
	
	public void setY(int y){
		this.Body.setCenterY(y);
	}
	
	public void setSize(int size){
		this.Body.setRadius(size);
	}
	
	public void setWeight(int weight){
		this.weight = weight;
	}
	
	public int getX(){
		return (int) this.Body.getCenterX();
	}
	
	public int getY(){
		return (int) this.Body.getCenterY();
	}
	
	public int getSize(){
		return (int) this.Body.getRadius();
	}
	
	public int getWeight(){
		return this.weight;
	}
	
	public Circle getBody(){
		return this.Body;
	}
	
	
}
