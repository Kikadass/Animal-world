package AnimalWorld;

import java.util.Random;

import javafx.scene.shape.Circle;

public class Target{
	private Circle Body;
	
	public Target(World world){
		Random rnd = new Random();
		this.Body.setRadius(rnd.nextInt(100)+ 10);
				
			this.Body.setCenterX(rnd.nextInt(world.getWidth() - (int)this.Body.getRadius()*2) + this.Body.getRadius());
			this.Body.setCenterY(rnd.nextInt(world.getHeight() - (int)this.Body.getRadius()*2 - 80) + this.Body.getRadius() + 50);
	}
	
	
	
}
