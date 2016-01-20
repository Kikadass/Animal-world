package AnimalWorld;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Food{
	private double energy;
	private String type;
	private Circle Body = new Circle();
    private boolean poisonous = false;

    public Food(){}

	public Food(World world){
		Random rnd = new Random();

        // 10% of meat compared to non meat
        if (rnd.nextInt(100)%10 == 0) createMeat("ZebraMeat");
        else createNonMeat();

		this.Body.setRadius(rnd.nextInt(5)+5);

        this.setPos(world);


	}

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

    public void createMeat(String type){
        Random rnd = new Random();

        this.type = type;
        this.Body.setFill(Color.RED);

        this.energy = rnd.nextInt(100)+100;

    }

    public void createNonMeat(){
        Random rnd = new Random();
        this.type = "NonMeat";
        this.Body.setFill(Color.GREEN);
        this.energy = rnd.nextInt(50)+10;
    }

	public double getEnergy(){
		return this.energy;
	}

	public Circle getBody(){
		return this.Body;
	}

	public String getType() {
		return type;
	}

    public boolean isPoisonous() {
        return poisonous;
    }

    public void setPoisonous() {
        this.poisonous = true;
        if (this.getBody().getFill() == Color.RED){
            this.getBody().setFill(Color.DARKSALMON);
        }
        else  this.getBody().setFill(Color.DARKSEAGREEN);
    }

    public void setType(String type) {
		this.type = type;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

    public void setPos(World world){
        Random rnd = new Random();

        int tries = 0;
        do{
            tries++;
            this.Body.setCenterX(rnd.nextInt(world.getWidth() - (int)this.Body.getRadius()*2) + this.Body.getRadius());
            this.Body.setCenterY(rnd.nextInt(world.getHeight() - (int)this.Body.getRadius()*2 - 80) + this.Body.getRadius() + 30);
        }while((Collisions.collideObstacle(Body, world) || Collisions.collideAnimals(Body, world.getAnimalList(), 0) || Collisions.collideFood(Body, world, 0) || Collisions.collideHabitatsArea(Body, world, 0)) && tries <= 100);
        if (tries >= 100) {
            this.Body.setRadius(0);
            System.out.println("No space to add another piece of food");
        }
    }

    public void update(){
        Random rnd = new Random();
        int chance = rnd.nextInt(100000);

        if (chance == 20 && !this.isPoisonous()){
            setPoisonous();
        }
    }

}
