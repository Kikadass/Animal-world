package AnimalWorld;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;


/**
 * Lion represents all Lions in the world
 * @author Kikadass
 * @since Java 8.0
 */
public class Lion extends Predator {
	private static int counter;
    private String[][] names = {
            //girls
            {"Sophia", "Emma", "Olivia", "Ava", "Isabella", "Mia", "Zoe", "Lily", "Emily", "Madelyn", "Madison", "Chloe", "Charlotte", "Aubrey",
                    "Avery", "Abigail", "Kaylee", "Layla", "Harper", "Ella", "Amelia", "Arianna", "Riley", "Aria", "Hailey", "Hannah", "Aaliyah", "Evelyn", "Addison",
                    "Mackenzie", "Adalyn", "Ellie", "Nora", "Scarlett", "Grace", "Anna", "Isabelle", "Natalie", "Kaitlyn", "Lillian", "Sarah", "Audrey" , "Nei",
                    "Elizabeth", "Leah", "Annabelle", "Kylie", "Mila", "Claire", "Victoria", "Maya", "Lila", "Elena"},

            //Boys
            {"Alex", "Melvin", "John", "Papadopolous", "Marty"}
    };


    /**Constructs and initializes Lion inside the world
     * @param world animation's world
     */
    public Lion(World world){
		Random rnd = new Random();
        this.setGender(rnd.nextBoolean());
        //girls
        if (this.getGender()) {
            this.setName(names[0][rnd.nextInt(names[0].length)]);
        }
        //boys
        else  this.setName(names[1][rnd.nextInt(names[1].length)]);

		this.setID(counter);
		this.setEnergy(rnd.nextInt(200)+100);
        this.setMaxEnergy((int)this.getEnergy());
        this.setFood(rnd.nextInt(200)+100);
		this.setMetabolism(rnd.nextInt(5)*0.001 + 0.020);
        this.setMaxFood((int) this.getFood());
		this.setStrenght(rnd.nextInt(100)+100);
		this.setFoodCarring(0);
		this.setMinFoodCons(10);
        this.setSize(rnd.nextInt(10)+10);
        this.setSmellRange(rnd.nextInt(10)+(int)getBody().getRadius()*5);
		this.setAge(0);
		this.setAngleRange(rnd.nextInt(10)+20);
		this.setLifeExpectancy(rnd.nextInt(1500)+4000);
		this.addFoodPreferences("Meat");
		this.addFoodPreferences("ZebraMeat");
        this.getPreys().add("Zebra");

		int tries = 0;
		do{
			tries++;
			this.getBody().setCenterX(rnd.nextInt(world.getWidth() - (int) this.getBody().getRadius()*2) + this.getBody().getRadius());
			this.getBody().setCenterY(rnd.nextInt(world.getHeight() - (int)this.getBody().getRadius()*2 - 80) + this.getBody().getRadius() + 30);
		}while((Collisions.collideObstacle(getBody(), world) || Collisions.collideAnimals(getBody(), world.getAnimalList())) && tries <= 100);

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

    /**
     * Adds 1 to the Lion counter
     */
    public void addCounter(){
		counter++;
	}

    /**
     * Restarts the Lion counter
     */
    public static void restartCounter(){counter = 0;}
}
