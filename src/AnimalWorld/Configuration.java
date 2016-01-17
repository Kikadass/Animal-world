package AnimalWorld;

import java.io.*;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Configuration implements Serializable{
	private String lastFile;
	private int amountFood;
	private int amountObstacles;
	private int amountBugs;
    private int height;
    private int width;

	public Configuration(){
		this.lastFile = new String();
		this.lastFile = "defConfiguration.ser";
		this.amountFood = 10;
		this.amountObstacles = 10;
		this.amountBugs = 1;
        this.height = 720;
		this.width = 1280;
	}
	
	public Configuration(String lastFile, int amountFood, int amountObstacles, int amountBugs, int height, int width){
		this.lastFile = lastFile;
		this.amountFood = amountFood;
		this.amountObstacles = amountObstacles;
		this.amountBugs = amountBugs;
        this.height = height;
        this.width = width;
	}
	
	public void setConfiguration(Configuration config){
		this.lastFile = config.lastFile;
		this.amountFood = config.amountFood;
		this.amountObstacles = config.amountObstacles;
		this.amountBugs = config.amountBugs;
        this.height = config.height;
        this.width = config.width;
	}

	public void EditConfig2(){

		//Creating a GridPane container
		GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 350, 250);
        final Stage stage = new Stage();

        stage.setTitle("Edit Configuration");
        stage.setScene(scene);
        stage.sizeToScene();

        grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		//Defining the Name text field
		final TextField food = new TextField();
		food.setPromptText("Enter amount of food.");
		food.setPrefColumnCount(10);
		food.getText();
        GridPane.setConstraints(food, 1, 0);
		grid.getChildren().add(food);
        Label label1 = new Label("Food:");
        GridPane.setConstraints(label1, 0, 0);
        grid.getChildren().add(label1);

		//Defining the obstacles text field
		final TextField obstacles = new TextField();
		obstacles.setPromptText("Enter amount of obstacles.");
		obstacles.setPrefColumnCount(10);
		obstacles.getText();
		GridPane.setConstraints(obstacles, 1, 1);
		grid.getChildren().add(obstacles);
        Label label2 = new Label("Obstacles:");
        GridPane.setConstraints(label2, 0, 1);
        grid.getChildren().add(label2);

		//Defining the animals text field
		final TextField animals = new TextField();
		animals.setPromptText("Enter amount of animals.");
		animals.setPrefColumnCount(10);
		animals.getText();
		GridPane.setConstraints(animals, 1, 2);
		grid.getChildren().add(animals);
        Label label3 = new Label("Animals:");
        GridPane.setConstraints(label3, 0, 2);
        grid.getChildren().add(label3);


		//Defining the world text field
		final TextField height = new TextField();
		height.setPromptText("Enter height of the world.");
		GridPane.setConstraints(height, 1, 3);
		grid.getChildren().add(height);
        Label label4 = new Label("Height:");
        GridPane.setConstraints(label4, 0, 3);
        grid.getChildren().add(label4);

        //Defining the world text field
        final TextField width = new TextField();
        width.setPromptText("Enter width of the world.");
        GridPane.setConstraints(width, 1, 4);
        grid.getChildren().add(width);
        Label label5 = new Label("Width:");
        GridPane.setConstraints(label5, 0, 4);
        grid.getChildren().add(label5);

		//Defining the Apply button
		Button apply = new Button("Apply");
		GridPane.setConstraints(apply, 0, 6);
		grid.getChildren().add(apply);

		//Defining the Clear button
		Button clear = new Button("Clear");
		GridPane.setConstraints(clear, 2, 6);
		grid.getChildren().add(clear);

        //Defining the Current Values button
        Button current = new Button("Current Values");
        GridPane.setConstraints(current, 1, 6);
        grid.getChildren().add(current);

		//Adding a Label
		final Label label = new Label();
		GridPane.setConstraints(label, 0, 5);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);



		//Setting an action for the Apply button
		apply.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if ((food.getText() != null && !food.getText().isEmpty()) &&
                    (obstacles.getText() != null && !obstacles.getText().isEmpty()) &&
                    (animals.getText() != null && !animals.getText().isEmpty()) &&
                    (height.getText() != null && !height.getText().isEmpty()) &&
                    (width.getText() != null && !width.getText().isEmpty())){

                    if (Integer.parseInt(height.getText()) >= 300 && Integer.parseInt(width.getText()) >= 300){
                        setFood(Integer.parseInt(food.getText()));
                        setObstacles(Integer.parseInt(obstacles.getText()));
                        setBugs(Integer.parseInt(animals.getText()));
                        if (Integer.parseInt(height.getText()) != getHeight() || Integer.parseInt(width.getText()) != getWidth()) {
                            setHeight(Integer.parseInt(height.getText()));
                            setWidth(Integer.parseInt(width.getText()));
                            NewMenu.setStartOver();
                            stage.close();
                        }
                        NewMenu.setStartOver();
                    }
                    else label.setText("Height and width have to be greater than 300.");

				} else {
					label.setText("Please enter all details.");
				}
			}
		});

        //Setting an action for the current button
        current.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                food.setText(String.valueOf(amountFood));
                obstacles.setText(String.valueOf(amountObstacles));
                animals.setText(String.valueOf(amountBugs));
                height.setText(String.valueOf(getHeight()));
                width.setText(String.valueOf(getWidth()));
                label.setText(null);
            }
        });

		//Setting an action for the Clear button
		clear.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				food.clear();
				obstacles.clear();
                animals.clear();
                height.clear();
                width.clear();
				label.setText(null);
			}
		});

        stage.showAndWait();
	}

	public void EditConfig1(){
		String check = null;

        do {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Food");
            dialog.setHeaderText("How much Food do you want to create?");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                check = result.get();
            }
        }while(check == null);
        this.amountFood = Integer.parseInt(check);


        check = null;

        do {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Obstacles");
            dialog.setHeaderText("How many Obstacles do you want to create?");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                check = result.get();
            }
        }while(check == null);
		this.amountObstacles = Integer.parseInt(check);


		check = null;
        do {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Animals");
            dialog.setHeaderText("How many Bugs do you want to create?");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                check = result.get();
            }
        }while(check == null);
		this.amountBugs = Integer.parseInt(check);


		check = null;
        do {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("World Size");
            dialog.setHeaderText("What would be the height of the World?");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                check = result.get();
            }
        }while(check == null);
		this.height = Integer.parseInt(check);

        check = null;
        do {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("World Size");
            dialog.setHeaderText("What would be the width of the World?");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                check = result.get();
            }
        }while(check == null);
        this.width = Integer.parseInt(check);
	}
	
	public void NewConfiguration(){
		String check = null;
        do {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("New Location");
            dialog.setHeaderText("What is the location where you want to safe the config file?");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                check = result.get();
            }
        }while(check == null);
		this.lastFile = check;
		
		this.EditConfig2();
	}
	
	public void OpenConfigurationFile(){
		String location = null;
        do {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("File Location");
            dialog.setHeaderText("What is the location of the config file?");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                location = result.get();
            }
        }while(location == null);

		this.lastFile = location;
		this.Load();
	}
	
	public String getLastFile(){
		return this.lastFile;
	}
	
	public void setLastFile(String lastFile){
		this.lastFile = lastFile;
	} 
	
	public int getFood(){
		return this.amountFood;
	}
	
	public void setFood(int amountFood){
		this.amountFood = amountFood;
	} 

	public int getObstacles(){
		return this.amountObstacles;
	}
	
	public void setObstacles(int amountObstacles){
		this.amountObstacles = amountObstacles;
	} 
	
	public int getBugs(){
		return this.amountBugs;
	}
	
	public void setBugs(int amountBugs){
		this.amountBugs = amountBugs;
	} 
	
	public int getWidth(){
		return this.width;
	}
	
	public void setWidth(int width){
		this.width = width;
	}

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

	public void displayConfig(){
		String print;
		print = "Name of file: " + this.lastFile + "\n" + 
				"Amount of Food: " + this.amountFood + "\n" +
				"Amount of Obstacles: " + this.amountObstacles + "\n" + 
				"Amount of Bugs: " + this.amountBugs + "\n" +
				"Height: " + this.height + "\n" +
                "Width: " + this.width;

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Configuration loaded:");
        alert.setContentText(print);
        alert.showAndWait();

        //JOptionPane.showMessageDialog(null, print);
	}
	
	public void displayMap(){
		String print;
		print = "Amount of Food: " + this.amountFood + "\n" +
				"Amount of Obstacles: " + this.amountObstacles + "\n" + 
				"Amount of Bugs: " + this.amountBugs + "\n" +
                "Height: " + this.height + "\n" +
                "Width: " + this.width;

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Configuration loaded:");
        alert.setContentText(print);
        alert.showAndWait();
	}
	
	public void Save(){
		try{
	         FileOutputStream fileOut = new FileOutputStream(this.lastFile);
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in " + this.lastFile + "\n");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
		this.EndSave();
	}

	public void EndSave(){
		try{
	         FileOutputStream fileOut = new FileOutputStream("defConfiguration.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in " + "defConfiguration.ser" + "\n");
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public static Configuration tryLoad(String lastFile){
		Configuration e = null;
		
		try{
			FileInputStream fileIn = new FileInputStream(lastFile);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			e = (Configuration) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException i)
		{
            // if you want to know the error activate next line
			//i.printStackTrace();
			return e;
		}catch(ClassNotFoundException c)
		{
			System.out.println("Configuration class not found");
			c.printStackTrace();
			return e;
		}
		return e;
	}
	
	public void StartLoad(){
		Configuration e = tryLoad("defConfiguration.ser");
		//e.lastFile = "defConfiguration.ser";

        if (e == null){
            Configuration config = new Configuration();
            e = config;
        }

		if (e.lastFile != "defConfiguration.ser"){
			e = tryLoad(e.lastFile);
			e.Save();
		}

		System.out.println("Deserialized Configuration...");

        e.displayConfig();

		this.setConfiguration(e);
	}
	
	public void Load(){
		Configuration e = new Configuration();

		e = tryLoad(this.lastFile);
		
		System.out.println("Deserialized Configuration...");
		e.displayConfig();
		this.setConfiguration(e);
	}
}
