package AnimalWorld;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.Random;

/**
 * Zebra represents the Zebra type of animal in the world
 *
 * Created by Kikadass on 19/01/2016.
 *
 * @author Kikadass
 */
public class Zebra extends Animal{
    private static int counter;
    private String[] names = {"Alex", "Melvin"};

    /**
     * Constructs Zebra and initializes it
     * @param world world of the animation
     */
    public Zebra(World world){
        Random rnd = new Random();
        this.setName(names[rnd.nextInt(2)]);
        this.setID(counter);
        this.setEnergy(rnd.nextInt(100)+200);
        this.setMaxEnergy((int)this.getEnergy());
        this.setFood(rnd.nextInt(100)+200);
        this.setMetabolism(rnd.nextInt(5)*0.001 + 0.020);
        this.setMaxFood((int) this.getFood());
        this.setStrenght(rnd.nextInt(100)+50);
        this.setFoodCarring(0);
        this.setMinFoodCons(10);
        this.setSize(rnd.nextInt(10)+10);
        this.setSmellRange(rnd.nextInt(10)+(int)getBody().getRadius()*3);
        this.setAge(0);
        this.setAngleRange(rnd.nextInt(10)+20);
        this.setLifeExpectancy(rnd.nextInt(1500)+4000);
        this.addFoodPreferences("NonMeat");
        this.addFoodPreferences("Grass");


        int tries = 0;
        do{
            tries++;
            this.getBody().setCenterX(rnd.nextInt(world.getWidth() - (int) this.getBody().getRadius()*2) + this.getBody().getRadius());
            this.getBody().setCenterY(rnd.nextInt(world.getHeight() - (int)this.getBody().getRadius()*2 - 80) + this.getBody().getRadius() + 30);
        }while((Collisions.collideObstacle(getBody(), world) || Collisions.collideAnimals(getBody(), world.getAnimalList(), 0)) && tries <= 100);

        if (tries >= 100) {
            this.getBody().setRadius(0);
            System.out.println("No space to add another Zebra");
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

        this.getBody().setFill(Color.FLORALWHITE);
        this.getBody().setStroke(Color.BLACK);

        addCounter();
    }

    /**
     * Adds 1 to the counter
     *
     */
    public void addCounter(){
        counter++;
    }

    /**
     *Restarts the counter
     */
    public static void restartCounter(){counter = 0;}

}
