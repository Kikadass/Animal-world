package AnimalWorld;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * Target represents all the targets in the world
 * @author Kikadass
 */
public class Target{
	private Circle Body = new Circle();

    /**
     * Constructs an Orange Target in the given position and with the given radius
     * @param Tx Target's x
     * @param Ty Target's y
     * @param radius Target's radius
     */
    public Target(int Tx, int Ty, int radius){
		this.Body.setRadius(radius);
		this.Body.setCenterX(Tx);
		this.Body.setCenterY(Ty);

		this.Body.setFill(Color.ORANGE);
	}

    /**
     * Body Getter
     * @return Target's Circle (Body)
     */
    public Circle getBody(){
		return this.Body;
	}
	
	
}
