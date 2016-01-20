package AnimalWorld;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Lion extends Predator {
	private static int counter;
	private String[] names = {"Alex", "Melvin"};
	
	public Lion(World world){
		Random rnd = new Random();
		this.setName(names[rnd.nextInt(2)]);
		this.setID(counter);
		this.setEnergy(rnd.nextInt(200)+100);
        this.setMaxEnergy((int)this.getEnergy());
        this.setFood(rnd.nextInt(200)+100);
		this.setMetabolism(rnd.nextInt(10)*0.01 + 0.05);
        this.setMaxFood((int) this.getFood());
		this.setStrenght(rnd.nextInt(100)+100);
		this.setFoodCarring(0);
		this.setMinFoodCons(10);
        this.setSize(rnd.nextInt(10)+10);
        this.setSmellRange(rnd.nextInt(10)+(int)getBody().getRadius()*3);
		this.setAge(0);			
		this.setForgetfulness(rnd.nextInt(1000));
		this.setNestX(20);
		this.setNestY(20);
		this.setAngleRange(rnd.nextInt(10)+20);
		this.setLifeExpectancy(rnd.nextInt(1500)+4000);	
		this.setMinSize(10);
		this.setMaxSize(20);
		this.addFoodPreferences("Meat");
		this.addFoodPreferences("ZebraMeat");

		int tries = 0;
		do{
			tries++;
			this.getBody().setCenterX(rnd.nextInt(world.getWidth() - (int) this.getBody().getRadius()*2) + this.getBody().getRadius());
			this.getBody().setCenterY(rnd.nextInt(world.getHeight() - (int)this.getBody().getRadius()*2 - 80) + this.getBody().getRadius() + 30);
		}while((Collisions.collideObstacle(getBody(), world) || Collisions.collideAnimals(getBody(), world.getAnimalList(), 0)) && tries <= 100);

        if (tries >= 100) {
            this.getBody().setRadius(0);
            System.out.println("No space to add another Lion");
        }

        this.getSmellRange().setCenterX(this.getBody().getCenterX());
        this.getSmellRange().setCenterY(this.getBody().getCenterY());
        this.getSmellRange().setOpacity(0.10);

        this.setSpeed((rnd.nextInt(10)+10)*0.1);

        this.getStats().setFont(new Font(10));
        this.getStats().setX(this.getBody().getCenterX());
        this.getStats().setY(this.getBody().getCenterY());

        this.getID().setFont(new Font(10));
        this.getID().setX(this.getBody().getCenterX());
        this.getID().setY(this.getBody().getCenterY());

        this.setStats();

		this.getBody().setFill(Color.YELLOW);

		addCounter();
	}
	
	public void addCounter(){
		counter++;
	}



	public static void restartCounter(){counter = 0;}
}
