package AnimalWorld;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Habitat represents all the Habitats in the world where Animals can live and go to gan energy
 *
 * @author Kikadass
 */
public class Habitat {
    private Circle area = new Circle();
	private Circle body = new Circle();
	private ArrayList<Animal> animalList = new ArrayList<Animal>();
    private int specie = -1;
    private int maxCapacity;
    private int maxFood;
    private int Food = 0;

    /**
     * Constructs and initialises Habitat
     * @param world main world
     */
    public Habitat(World world){
		Random rnd = new Random();

        // setting radius
        this.getBody().setRadius(10);
        this.getArea().setRadius(rnd.nextInt(20) + this.getBody().getRadius()*3);
        this.getArea().setFill(Color.FIREBRICK);
        this.getArea().setOpacity(0.60);

        this.maxCapacity = rnd.nextInt(10)+5;
        this.maxFood = rnd.nextInt(1000)+500;

        // Try to input habitat into world
		int tries = 0;

		do{
			tries++;
			this.getArea().setCenterX(rnd.nextInt(world.getWidth() - (int) this.getArea().getRadius()*2) + this.getArea().getRadius());
			this.getArea().setCenterY(rnd.nextInt(world.getHeight() - (int)this.getArea().getRadius()*2 - 80) + this.getArea().getRadius() + 30);
		}while((Collisions.collideObstacle(getArea(), world) || Collisions.collideAnimals(getArea(), world.getAnimalList(), 0) || Collisions.collideFood(getArea(), world, 0) || Collisions.collideHabitatsArea(getArea(), world, 0)) && tries <= 100);
		if (tries >= 100) {
			this.body.setRadius(0);
			System.out.println("No space to add another Habitat");
		}

        this.getBody().setCenterX(this.getArea().getCenterX());
        this.getBody().setCenterY(this.getArea().getCenterY());
	}


    /**
     * Body Getter
     *
     * @return Habitat Body: invisible circle in the middle of the Habbitats area
     */
    public Circle getBody() {
		return body;
	}

    /**
     * MaxCapacity Getter
     * @return maximum amount of animals that fit in the habitat at the same time
     */
    public int getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * Specie Getter
     * @return species for which the Habitat is exclusive
     */
    public int getSpecie() {
        return specie;
    }

    /**
     * Area Getter
     * @return Habbitat Area: Visible Circle that represents the Habitat, but the animal has to get to the middle to enter the habitat
     */
    public Circle getArea() {
        return area;
    }

    /**
     * AmountOfAnimals Getter
     * @return Amount of animals inside the habbitat at that instant
     */
    public int getAmountOfAnimals(){
        return animalList.size();
    }

    /**
     * Specie Getter
     * @param specie species for which the Habitat is exclusive
     */
    public void setSpecie(int specie) {
        System.out.println(specie);
        this.specie = specie;
    }

    /**
     * Adds an animal into the Habbitat
     *
     * Adds animal given into the animalList of the Habbitat
     * Feeds the animal that has arrived qith the supplies
     * Takes the Food that the animals carries and stores it
     *
     * @param animal animal that has arrived into the habitat
     */
    public void addAnimal(Animal animal){
        this.animalList.add(animal);


        //eat
        if (animal.getFood() + this.Food < animal.getMaxFood()){
            this.Food = 0;
            animal.setFood(animal.getFood() + this.Food);
        }
        else {
            this.Food = (int)(this.Food - animal.getMaxFood() - animal.getFood());
            animal.setFood(animal.getMaxFood());
        }


        //put food that he is carrying into the habitat
        if (this.Food + (int) animal.getFoodCarring() >= this.maxFood){
            animal.setFoodCarring(this.Food + (int) animal.getFoodCarring() - this.maxFood);
            this.Food = this.maxFood;
        }
        else {
            this.Food += (int) animal.getFoodCarring();
            animal.setFoodCarring(0);
        }

    }

    /**
     * Updates the habbitat adding energy to every animal inside it
     *
     * If animal is full of energy the habitat releases him into the world
     */
    public void update(){
        for (int i = 0; i < this.animalList.size(); i++){
            this.animalList.get(i).setEnergy(this.animalList.get(i).getEnergy()+this.animalList.get(i).getMetabolism()*2);
            if (this.animalList.get(i).getEnergy() >= this.animalList.get(i).getMaxEnergy()){
                this.animalList.get(i).setEnergy(this.animalList.get(i).getMaxEnergy());
                //go out into the world
                this.animalList.get(i).getBody().setVisible(true);
                this.animalList.remove(i);
            }

        }

    }

}
