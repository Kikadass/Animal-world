package AnimalWorld;

import java.util.Random;

import javafx.scene.paint.Color;

public class Lion extends Predator {
	private static int counter;
	private String[] names = {"Alex", "Melvin"};
	
	public Lion(World world){
		Random rnd = new Random();
		this.setName(names[rnd.nextInt(2)]);
		this.setID(counter);
		this.setEnergy(rnd.nextInt(200)+200);
		this.setStrenght(rnd.nextInt(200)+200);
		this.setFoodCarring(0);
		this.setMinFoodCons(100);	
		this.setSmellRange(rnd.nextInt(100)+100);
		this.setAge(0);			
		this.setForgetfulness(rnd.nextInt(1000));
		this.setNestX(20);
		this.setNestY(20);
		this.setAngle(rnd.nextInt(10)+30);			
		this.setLifeExpectancy(rnd.nextInt(1500)+4000);	
		this.setSize(rnd.nextInt(50)+50);
		this.setMinSize(200);
		this.setMaxSize(400);
		
		do{
			this.setCenterX(rnd.nextInt(world.getWidth() - (int)this.getRadius()*2) + this.getRadius());
			this.setCenterY(rnd.nextInt(world.getHeight() - (int)this.getRadius()*2 - 80) + this.getRadius() + 50);
		}while(collideObstacle(world, false) || collideAnimals(world.animalList, false) || collideFood(world));
		
		this.setDx(1);
		this.setDy(1);
		
		this.setFill(Color.YELLOW);
	}
	
	public void addCounter(){
		counter++;
	}
}
