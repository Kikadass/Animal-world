package AnimalWorld;

import java.util.ArrayList;

/**
 * Predator is an abstract class that represents all predators in the world
 */
public abstract class Predator extends Animal{
	private ArrayList<String> preys = new ArrayList<String>();

    /**
     * Makes The Predator Chace its pray
     * @param world main world
     */
    public void chace(World world){
		// check collision between smell range and zebras
		for (int type = 0; type < world.getAnimalList().size(); type++){
			if (world.getAnimalList().get(type).size() > 0 && world.getAnimalList().get(type).get(0) instanceof Zebra){
				for (int i = 0; i < world.getAnimalList().get(type).size(); i++){
					if (Collisions.efficientCollide(this.getSmellRange(), world.getAnimalList().get(type).get(i).getBody())){
						if (Collisions.nonEfficientCollide(this.getSmellRange(), world.getAnimalList().get(type).get(i).getBody())){
							// Chace
							this.setMainTarget(world.getAnimalList().get(type).get(i).getPosX(), world.getAnimalList().get(type).get(i).getPosY(), world.getAnimalList().get(type).get(i).getBody().getRadius());

						}
					}
				}
			}
		}
	}

    /**
     * Predator Kill it's pray
     * @param world main world
     */
    public void kill(World world){
        // check collision between smell range and zebras
        for (int type = 0; type < world.getAnimalList().size(); type++){
            if (world.getAnimalList().get(type).size() > 0 && world.getAnimalList().get(type).get(0) instanceof Zebra){
                for (int i = 0; i < world.getAnimalList().get(type).size(); i++){
                    if (Collisions.efficientCollide(this.getBody(), world.getAnimalList().get(type).get(i).getBody())){
                        if (Collisions.nonEfficientCollide(this.getBody(), world.getAnimalList().get(type).get(i).getBody())){
                            // kill
                            this.setMainTarget(new Target(0, 0, 1));
                            world.dieAnimal(type, i);

                        }
                    }
                }
            }
        }
    }

	/**
	 * Preys Getter
	 * @return Array of Preys
	 */
	public ArrayList<String> getPreys() {
		return this.preys;
	}

}
