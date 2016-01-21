package AnimalWorld;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Grass represents all the grass in the world
 * Created by Kikadass on 19/01/2016.
 *
 * @author Kikadass
 */
public class Grass extends Food {

    /**
     * Constructs and initializes Grass
     * @param world main world
     */
    public Grass(World world) {
        Random rnd = new Random();
        this.setEnergy(rnd.nextInt(100)+10);

        this.setType("Grass");
        this.getBody().setFill(Color.DARKGREEN);


        this.getBody().setRadius(this.getEnergy()/10);

        this.setPos(world);
    }

    /**
     * Updates grass in order to grow and get poisonous
     */
    public void update(){
        this.setEnergy(this.getEnergy() + 0.1);
        this.getBody().setRadius(this.getEnergy()/10);

        super.update();
    }

}
