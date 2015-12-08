package AnimalWorld;

import java.util.Random;

import javafx.scene.shape.*;

public class Obstacle {
	private int weight;
	private int size;
	private int x;
	private int y;
	//private String[] shapes = {"Rectangle", "Circle"};
	Shape object;
	
	public Obstacle(int width, int height){
		Random rnd = new Random();
		this.weight = rnd.nextInt(200)+ 10;
		this.size = rnd.nextInt(100)+ 10;
		this.x = rnd.nextInt(width - size*2)+ size;
		this.y = rnd.nextInt(height - size*2 - 80)+ size + 50;
				
		int shape = rnd.nextInt(2);
		if (shape == 0) {
			System.out.println("Rectangle");
			this.object = new Rectangle(x, y, size, size);
		}
		else {
			System.out.println("Circle");
			this.object = new Circle(x, y, size);
		}
	}

	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public void setSize(int size){
		this.size = size;
	}
	
	public int getX(){
		return this.x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public int getSize(){
		return this.size;
	}
}
