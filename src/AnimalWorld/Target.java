package AnimalWorld;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Target{
	private Circle Body = new Circle();
	
	public Target(int Tx, int Ty, int radius){
		this.Body.setRadius(radius);
		this.Body.setCenterX(Tx);
		this.Body.setCenterY(Ty);

		this.Body.setFill(Color.ORANGE);
	}

	public Circle getBody(){
		return this.Body;
	}
	
	
}
