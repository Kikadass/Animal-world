package AnimalWorld;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Target{
	private Circle Body = new Circle();
	
	public Target(int Tx, int Ty){
		this.Body.setRadius(3);
		this.Body.setCenterX(Tx);
		this.Body.setCenterY(Ty);

		this.Body.setFill(Color.GREEN);
	}

	public Circle getBody(){
		return this.Body;
	}
	
	
}
