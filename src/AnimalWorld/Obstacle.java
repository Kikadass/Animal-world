package AnimalWorld;

import java.util.Random;

import javafx.scene.shape.*;

/**
 *Obstacle represents the obstacles in the world
 *
 * @author Kikadass
 */
public class Obstacle{
	private int weight;
	private Circle Body = new Circle();

	/**
	 * Constructs and initializes the Obstacle in the world
	 * @param world animation's world
	 */
	public Obstacle(World world){
		Random rnd = new Random();
		this.weight = rnd.nextInt(200)+ 10;
		this.Body.setRadius(rnd.nextInt(10)+ 10);

		int tries = 0;
		do{
			tries++;
			this.Body.setCenterX(rnd.nextInt(world.getWidth() - (int)this.Body.getRadius()*2) + this.Body.getRadius());
			this.Body.setCenterY(rnd.nextInt(world.getHeight() - (int)this.Body.getRadius()*2 - 80) + this.Body.getRadius() + 30);
		}while((Collisions.collideObstacle(Body, world) || Collisions.collideAnimals(Body, world.getAnimalList()) || Collisions.collideFood(Body, world) || Collisions.collideHabitatsArea(Body, world)) && tries <= 100);
        if (tries >= 100) {
            System.out.println("No space to add another obstacle");
            this.Body.setRadius(0);

        }



	}

	/**
	 * Weight Getter
	 * @return Obstacle's weight
	 */
	public int getWeight(){
		return this.weight;
	}

	/**
	 * Body Getter
	 * @return Obstacle's Body
	 */
	public Circle getBody(){
		return this.Body;
	}
	
	
}
