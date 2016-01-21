package AnimalWorld;

import java.util.ArrayList;

import javafx.scene.shape.Circle;

/**
 *Collisions is a class that stores all the functions about the phisical collisions of the world
 *
 * @author Kikadass
 */
public abstract class Collisions {
	/**
	 * Calculates in a Precise and non Efficient way the collison between 2 circles
	 *
	 * It checks the collision with the round shape
	 * @param c1 first circle input
	 * @param c2 second circle input
	 * @return true if both circles collide
	 */
	public static boolean nonEfficientCollide(Circle c1, Circle c2){
		// (x2-x1)^2 + (y1-y2)^2 <= (r1+r2)^2
		int a = (int) Math.pow(((c1.getCenterX() + c1.getTranslateX()) - (c2.getCenterX() + c2.getTranslateX())), 2);
		int b = (int) Math.pow(((c1.getCenterY() + c1.getTranslateY()) - (c2.getCenterY() + c2.getTranslateY())), 2);
		int c = (int) Math.pow(c1.getRadius() + c2.getRadius(), 2);
		
		if (a + b <= c) return true;
		return false;
	}

	/**
	 * Calculates in an Efficient and non Precise way the collision between 2 circles
	 *
	 * It checks the collision as a square around the Circle
	 * @param c1 first circle input
	 * @param c2 second circle input
	 * @return true if both circles collide
	 */
	public static boolean efficientCollide(Circle c1, Circle c2){
        int x1 = (int) (c1.getCenterX() + c1.getTranslateX()), y1 = (int) (c1.getCenterY() + c1.getTranslateY());
        int x2 = (int) (c2.getCenterX() + c2.getTranslateX()), y2 = (int) (c2.getCenterY() + c2.getTranslateY());
        int r1 = (int) c1.getRadius();
        int r2 = (int) c2.getRadius();
        
        return (x1 - (r2 + r1) < x2 && x2 < x1 + (r2 + r1) &&
                y1 - (r2 + r1) < y2 && y2 < y1 + (r2 + r1));
	}

	/**
	 * Checks the collsions between circle c1 and the obstacles in the world
	 * @param c1 circle input
	 * @param world main world
	 * @return true if the circle collides with some Obstacle
	 */
	public static boolean collideObstacle(Circle c1, World world){
		for (int i = 0; i < world.getObstacleList().size(); i++){
			if (Collisions.efficientCollide(c1, world.getObstacleList().get(i).getBody())){
				if (Collisions.nonEfficientCollide(c1, world.getObstacleList().get(i).getBody())){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Checks the collisions between circle c1 and Food in the world
	 *
	 * @param c1 circle input
	 * @param world main world
	 * @return true if circle collides with food
	 */
	public static boolean collideFood(Circle c1, World world){
		for (int i = 0; i < world.getFoodList().size(); i++){
				if (Collisions.efficientCollide(c1, world.getFoodList().get(i).getBody())){
					if (Collisions.nonEfficientCollide(c1, world.getFoodList().get(i).getBody())){
						return true;
					}
				}
			}

		return false;
	}

	/**
	 * Checks the collisions between circle c1 and all the other animals in the world
	 *
	 * @param c1 circle input
	 * @param circles All the animals
	 * @return true if circle collides with animals
	 */
	public static boolean collideAnimals(Circle c1, ArrayList<ArrayList<Animal>> circles){
		for (ArrayList<Animal> a : circles){
			for (int i = 0; i < a.size(); i++){
				if (Collisions.efficientCollide(c1, a.get(i).getBody())){
					if (Collisions.nonEfficientCollide(c1, a.get(i).getBody())){
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Checks the collisions between circle c1 and Habitats Area in the world
	 *
	 * @param c1 circle input
	 * @param world main world
	 * @return true if circle collides with habitat area
	 */
	public static boolean collideHabitatsArea(Circle c1, World world){
		for (int i = 0; i < world.getHabitatsList().size(); i++){
			if (Collisions.efficientCollide(c1, world.getHabitatsList().get(i).getArea())){
				if (Collisions.nonEfficientCollide(c1, world.getHabitatsList().get(i).getArea())){
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks the collisions between the animal and Habitats in the world
	 *
	 * The collision is only checked in the habitat that matches with th animal's houseTarget
	 * This function takes into account the pass by "reference" of java by returning the index of the Habitat c1 collided with outside the function
	 * @param animal animal to check the collections with
	 * @param world main world
	 * @return true if circle collides with habitat and it indirectly returns the index of the habitat c1 has collided with
	 */
	public static boolean collideHabitats(Animal animal, World world){
		for (int i = 0; i < world.getHabitatsList().size(); i++){
			//if the animal's house is this habitat, than see if he is colliding
			if (animal.getHouseTarget().getBody().getCenterX() == world.getHabitatsList().get(i).getBody().getCenterX() && animal.getHouseTarget().getBody().getCenterY() == world.getHabitatsList().get(i).getBody().getCenterY()) {
				if (Collisions.efficientCollide(animal.getBody(), world.getHabitatsList().get(i).getBody())) {
					if (Collisions.nonEfficientCollide(animal.getBody(), world.getHabitatsList().get(i).getBody())) {
						return true;
					}
				}
			}
		}

		return false;
	}

}
