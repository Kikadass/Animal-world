package AnimalWorld;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


/**
 * The {@code Food} class creates a new circle
 * with the specified radius and center location measured in pixels
 *
 * Example usage. The following code creates a circle with radius 50px centered
 * at (100,100)px.
 *
 <PRE>

 World world = world;
 Food food = new Food(world);

 </PRE>
 */
public class Food{
	private double energy;
	private String type;
	private Circle Body = new Circle();
    private boolean poisonous = false;

    /**
     * Constructs and initializes Food
     */
    public Food(){}

    /**
     * Constructs and initializes Food taking care of not colliding with other world objects
     * There is a 10% probability of being ZebraMeat and 90% probability of being NonMeat
     * @param world main world
     */
    public Food(World world){
		Random rnd = new Random();

        // 10% of meat compared to non meat
        if (rnd.nextInt(100)%10 == 0) createMeat("ZebraMeat");
        else createNonMeat();

		this.Body.setRadius(rnd.nextInt(5)+5);

        this.setPos(world);
	}

    /**
     * Constructs and initializes Food depending on the type input
     * @param world main world
     * @param type type of food
     * @param meat true if is meat, false if is not meat
     */
    public Food(World world, String type, boolean meat){
        Random rnd = new Random();

        if (meat) {
            createMeat(type);
        }
        else {
            createNonMeat();
        }

        this.Body.setRadius(rnd.nextInt(5)+5);

        this.setPos(world);
    }

    /**
     *
     * Constructs and initializes Food depending on the type, radius, and position input
     * @param world main world
     * @param type Food type
     * @param meat true if is meat, false if is not meat
     * @param rad radius of food
     * @param x x coordinate in pixels
     * @param y y coordinate in pixels
     */
    public Food(World world, String type, boolean meat, int rad, int x, int y){
        if (meat) {
            createMeat(type);
        }

        else {
            createNonMeat();
        }

        this.Body.setRadius(rad);

        this.Body.setCenterX(x);
        this.Body.setCenterY(y);
    }

    /**
     * Creates Meat depending on the type input with a red color
     * @param type Meat type
     */
    public void createMeat(String type){
        Random rnd = new Random();

        this.type = type;
        this.Body.setFill(Color.RED);

        this.energy = rnd.nextInt(100)+100;

    }

    /**
     * Creates NonMeat with a Green color
     */
    public void createNonMeat(){
        Random rnd = new Random();
        this.type = "NonMeat";
        this.Body.setFill(Color.GREEN);
        this.energy = rnd.nextInt(50)+10;
    }

    /**
     * Energy Getter
     * @return Food energy (calories)
     */
    public double getEnergy(){
		return this.energy;
	}

    /**
     * Body Getter
     * @return Food's Circle
     */
    public Circle getBody(){
		return this.Body;
	}

    /**
     * Type Getter
     * @return Type of Food
     */
    public String getType() {
		return type;
	}

    /**Check if is poisonous or not
     * @return poisonous (boolean)
     */
    public boolean isPoisonous() {
        return poisonous;
    }

    /**
     * Poisonous Setter
     *
     * Sets poisonous to true
     * Changes the color of the Food to show that it has been poisoned
     */
    public void setPoisonous() {
        this.poisonous = true;
        if (this.getBody().getFill() == Color.RED){
            this.getBody().setFill(Color.DARKSALMON);
        }
        else  this.getBody().setFill(Color.DARKSEAGREEN);
    }

    /**
     * Type Setter
     * @param type type of Food
     */
    public void setType(String type) {
		this.type = type;
	}

    /**
     * Energy Setter
     * @param energy energy that Foods gives if eaten
     */
    public void setEnergy(double energy) {
		this.energy = energy;
	}

    /**
     * Position Setter
     *
     * Sets the coordinates X and Y of Food (CenterX and CenterY of the Circle (Body)) in pixels
     * Making sure that it does not collide with obstacles, animals, other Food, Habitats
     *
     * @param world main world
     */
    public void setPos(World world){
        Random rnd = new Random();

        int tries = 0;
        do{
            tries++;
            this.Body.setCenterX(rnd.nextInt(world.getWidth() - (int)this.Body.getRadius()*2) + this.Body.getRadius());
            this.Body.setCenterY(rnd.nextInt(world.getHeight() - (int)this.Body.getRadius()*2 - 80) + this.Body.getRadius() + 30);
        }while((Collisions.collideObstacle(Body, world) || Collisions.collideAnimals(Body, world.getAnimalList()) || Collisions.collideFood(Body, world) || Collisions.collideHabitatsArea(Body, world)) && tries <= 100);
        if (tries >= 100) {
            this.Body.setRadius(0);
            System.out.println("No space to add another piece of food");
        }
    }

    /**
     * Updates Food with the possibility to get poisonous (1/100000)
     */
    public void update(){
        Random rnd = new Random();
        int chance = rnd.nextInt(100000);

        if (chance == 1 && !this.isPoisonous()){
            setPoisonous();
        }
    }

}
