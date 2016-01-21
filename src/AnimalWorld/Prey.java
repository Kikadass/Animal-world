package AnimalWorld;

import java.util.Random;

/**
 * Created by Kikadass on 21/01/2016.
 */
public class Prey extends Animal{
    private boolean escaping = false;


    public boolean isEscaping() {
        return escaping;
    }

    public void setEscaping(boolean escaping) {
        this.escaping = escaping;
    }

    public void escape(World world){
        boolean collided = false;
        // check collision between smell range and Predators
        for (int type = 0; type < world.getAnimalList().size(); type++){
            if (world.getAnimalList().get(type).size() > 0 && world.getAnimalList().get(type).get(0) instanceof Predator){
                for (int i = 0; i < world.getAnimalList().get(type).size(); i++) {
                    if (Collisions.efficientCollide(this.getSmellRange(), world.getAnimalList().get(type).get(i).getBody())) {
                        collided = true;
                        if (Collisions.nonEfficientCollide(this.getSmellRange(), world.getAnimalList().get(type).get(i).getBody())) {
                            if (!this.escaping) {
                                this.escaping = true;

                                // set a provisional target to escape
                                int anAngle = (int) getAngleTo(world.getAnimalList().get(type).get(i).getPosX(), world.getAnimalList().get(type).get(i).getPosY());
                                anAngle += 180;
                                if (anAngle >= 360) anAngle -= 360;


                                Random rand = new Random();
                                int randomAttemptTracker = 0;
                                int tX, tY, path = (int) this.getSmellRange().getRadius();
                                do {
                                    if (randomAttemptTracker < 135 * 2) {
                                        // all range of angles desired
                                        anAngle = rand.nextInt(135 * 2) + anAngle - 135;
                                        randomAttemptTracker++;
                                    } else {
                                        // Too many attempts at picking an angle within the turn range were made
                                        anAngle = rand.nextInt(360);
                                        path = rand.nextInt((int) this.getSmellRange().getRadius());
                                    }
                                    double angle = Math.toRadians(anAngle);
                                    tX = (int) ((this.getSmellRange().getCenterX() + this.getSmellRange().getTranslateX()) + path * Math.cos(angle));
                                    tY = (int) ((this.getSmellRange().getCenterY() + this.getSmellRange().getTranslateY()) + path * Math.sin(angle));
                                } while (!isValidTarget(tX, tY));
                                this.getProvisionalTarget().getBody().setCenterX(tX);
                                this.getProvisionalTarget().getBody().setCenterY(tY);
                                this.setLastAngle(anAngle);

                            }
                        }
                    }
                }
            }
        }

        if (this.escaping && !collided){
            this.escaping = false;
        }
    }
}

