package AnimalWorld;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.Random;

public class Habitat {
    private Circle area = new Circle();
	private Circle body = new Circle();
	private ArrayList<Animal> animalList = new ArrayList<Animal>();
    private int specie = -1;
    private int maxCapacity;
    private int maxFood;
    private int Food = 0;

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



	public Circle getBody() {
		return body;
	}

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getMaxFood() {
        return maxFood;
    }

    public int getSpecie() {
        return specie;
    }

    public int getFood() {
        return Food;
    }

    public Circle getArea() {
        return area;
    }

    public void setArea(Circle area) {
        this.area = area;
    }


    public void setFood(int food) {
        this.Food = food;
    }

    public void setSpecie(int specie) {
        this.specie = specie;
    }

    public void setMaxFood(int maxFood) {
        this.maxFood = maxFood;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

	public void setBody(Circle body) {
		this.body = body;
	}

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
