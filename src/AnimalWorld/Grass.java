package AnimalWorld;

import javafx.scene.paint.Color;

import java.util.Random;

/**
 * Created by Kikadass on 19/01/2016.
 */
public class Grass extends Food {

    public Grass(World world) {
        Random rnd = new Random();
        this.setEnergy(rnd.nextInt(100)+10);

        this.setType("Grass");
        this.getBody().setFill(Color.DARKGREEN);


        this.getBody().setRadius(this.getEnergy()/10);

        this.setPos(world);
    }

    public void update(){
        this.setEnergy(this.getEnergy() + 0.1);
        this.getBody().setRadius(this.getEnergy()/10);
    }

}
