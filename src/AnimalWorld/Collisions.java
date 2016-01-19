package AnimalWorld;

import java.util.ArrayList;

import javafx.scene.shape.Circle;

public class Collisions {
	public static boolean nonEfficientCollide(Circle c1, Circle c2){
		// (x2-x1)^2 + (y1-y2)^2 <= (r1+r2)^2
		int a = (int) Math.pow(((c1.getCenterX() + c1.getTranslateX()) - (c2.getCenterX() + c2.getTranslateX())), 2);
		int b = (int) Math.pow(((c1.getCenterY() + c1.getTranslateY()) - (c2.getCenterY() + c2.getTranslateY())), 2);
		int c = (int) Math.pow(c1.getRadius() + c2.getRadius(), 2);
		
		if (a + b <= c) return true;
		return false;
	}
	
	public static boolean efficientCollide(Circle c1, Circle c2){
        int x1 = (int) (c1.getCenterX() + c1.getTranslateX()), y1 = (int) (c1.getCenterY() + c1.getTranslateY());
        int x2 = (int) (c2.getCenterX() + c2.getTranslateX()), y2 = (int) (c2.getCenterY() + c2.getTranslateY());
        int r1 = (int) c1.getRadius();
        int r2 = (int) c2.getRadius();
        
        return (x1 - (r2 + r1) < x2 && x2 < x1 + (r2 + r1) &&
                y1 - (r2 + r1) < y2 && y2 < y1 + (r2 + r1));
	}
	
	public static boolean collideObstacle(Circle c1, World world){
		for (int i = 0; i < world.obstacleList.size(); i++){
			if (Collisions.efficientCollide(c1, world.obstacleList.get(i).getBody())){
				if (Collisions.nonEfficientCollide(c1, world.obstacleList.get(i).getBody())){
					return true;
				}
			}
		}
		return false;
	}
	
	public static boolean collideFood(Circle c1, World world){
		for (int i = 0; i < world.foodList.size(); i++){
				if (Collisions.efficientCollide(c1, world.foodList.get(i).getBody())){
					if (Collisions.nonEfficientCollide(c1, world.foodList.get(i).getBody())){
						return true;
					}
				}
			}

		return false;
	}
	
	public static boolean collideAnimals(Circle c1, ArrayList<ArrayList<Animal>> circles){
		// check collision against other bugs

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
}
