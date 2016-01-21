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
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 *Configuration represents all the details needed to Load, Save or Modify a world
 *
 * @author Kikadass
 */
public class Configuration implements Serializable{
	private String lastFile;
	private int amountFood;
    private int amountGrass;
    private int amountHabitats;
	private int amountObstacles;
	private int amountLions;
	private int amountZebras;
    private int height;
    private int width;

    /**
     * Constructs andinitializes a Configuration file
     */
    public Configuration(){
		this.lastFile = "/defConfiguration.ser";
		this.amountFood = 10;
        this.amountGrass = 1;
        this.amountHabitats = 1;
        this.amountObstacles = 10;
		this.amountLions = 1;
		this.amountZebras = 1;
		this.height = 720;
		this.width = 1280;
	}

    /**
     * Sets a Configuration with the one input
     * @param config main config
     */
    public void setConfiguration(Configuration config){
		this.lastFile = config.lastFile;
		this.amountFood = config.amountFood;
        this.amountGrass = config.amountGrass;
        this.amountHabitats = config.amountHabitats;
		this.amountObstacles = config.amountObstacles;
		this.amountLions = config.amountLions;
		this.amountZebras = config.amountZebras;
		this.height = config.height;
        this.width = config.width;
	}

    /**
     *The user can Edit the configuration through a GUI based on one single menu
     */
    public void EditConfig2(){

		//Creating a GridPane container
		GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 350, 350);
        final Stage stage = new Stage();

        stage.setTitle("Edit Configuration");
        stage.setScene(scene);
        stage.sizeToScene();

        grid.setPadding(new Insets(10, 10, 10, 10));
		grid.setVgap(5);
		grid.setHgap(5);

		int configThings = 0;

		//Defining the Food text field
		final TextField food = new TextField();
		food.setPromptText("Enter amount of food.");
		food.setPrefColumnCount(10);
		food.getText();
        GridPane.setConstraints(food, 1, configThings);
		grid.getChildren().add(food);
        Label label1 = new Label("Food:");
        GridPane.setConstraints(label1, 0, configThings);
        grid.getChildren().add(label1);

		configThings++;

        //Defining the Grass text field
        final TextField grass = new TextField();
        grass.setPromptText("Enter amount of grass.");
        grass.setPrefColumnCount(10);
        grass.getText();
        GridPane.setConstraints(grass, 1, configThings);
        grid.getChildren().add(grass);
        Label label7 = new Label("Grass:");
        GridPane.setConstraints(label7, 0, configThings);
        grid.getChildren().add(label7);

        configThings++;

        //Defining the Habitats text field
        final TextField habitats = new TextField();
        habitats.setPromptText("Enter amount of habitats.");
        habitats.setPrefColumnCount(10);
        habitats.getText();
        GridPane.setConstraints(habitats, 1, configThings);
        grid.getChildren().add(habitats);
        Label label8 = new Label("Habitats:");
        GridPane.setConstraints(label8, 0, configThings);
        grid.getChildren().add(label8);

        configThings++;

		//Defining the obstacles text field
		final TextField obstacles = new TextField();
		obstacles.setPromptText("Enter amount of obstacles.");
		obstacles.setPrefColumnCount(10);
		obstacles.getText();
		GridPane.setConstraints(obstacles, 1, configThings);
		grid.getChildren().add(obstacles);
        Label label2 = new Label("Obstacles:");
        GridPane.setConstraints(label2, 0, configThings);
        grid.getChildren().add(label2);

		configThings++;

		//Defining the Lions text field
		final TextField lions = new TextField();
		lions.setPromptText("Enter amount of lions.");
		lions.setPrefColumnCount(10);
		lions.getText();
		GridPane.setConstraints(lions, 1, configThings);
		grid.getChildren().add(lions);
        Label label3 = new Label("Lions:");
        GridPane.setConstraints(label3, 0, configThings);
        grid.getChildren().add(label3);

		configThings++;

        //Defining the zebras text field
        final TextField zebras = new TextField();
        zebras.setPromptText("Enter amount of zebras.");
        zebras.setPrefColumnCount(10);
        zebras.getText();
        GridPane.setConstraints(zebras, 1, configThings);
        grid.getChildren().add(zebras);
        Label label6 = new Label("Zebras:");
        GridPane.setConstraints(label6, 0, configThings);
        grid.getChildren().add(label6);

        configThings++;

		//Defining the world text field
		final TextField height = new TextField();
		height.setPromptText("Enter height of the world.");
		GridPane.setConstraints(height, 1, configThings);
		grid.getChildren().add(height);
        Label label4 = new Label("Height:");
        GridPane.setConstraints(label4, 0, configThings);
        grid.getChildren().add(label4);

		configThings++;

        //Defining the world text field
        final TextField width = new TextField();
        width.setPromptText("Enter width of the world.");
        GridPane.setConstraints(width, 1, configThings);
        grid.getChildren().add(width);
        Label label5 = new Label("Width:");
        GridPane.setConstraints(label5, 0, configThings);
        grid.getChildren().add(label5);

		configThings++;

		//Adding a Label
		final Label label = new Label();
		GridPane.setConstraints(label, 0, configThings);
		GridPane.setColumnSpan(label, 2);
		grid.getChildren().add(label);

		configThings++;

		//Defining the Apply button
		Button apply = new Button("Apply");
		GridPane.setConstraints(apply, 0, configThings);
		grid.getChildren().add(apply);

		//Defining the Clear button
		Button clear = new Button("Clear");
		GridPane.setConstraints(clear, 2, configThings);
		grid.getChildren().add(clear);

        //Defining the Current Values button
        Button current = new Button("Current Values");
        GridPane.setConstraints(current, 1, configThings);
        grid.getChildren().add(current);



		//Setting an action for the Apply button
		apply.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if ((food.getText() != null && !food.getText().isEmpty()) &&
                    (grass.getText() != null && !grass.getText().isEmpty()) &&
                    (habitats.getText() != null && !habitats.getText().isEmpty()) &&
                    (obstacles.getText() != null && !obstacles.getText().isEmpty()) &&
                    (lions.getText() != null && !lions.getText().isEmpty()) &&
					(zebras.getText() != null && !zebras.getText().isEmpty()) &&
					(height.getText() != null && !height.getText().isEmpty()) &&
                    (width.getText() != null && !width.getText().isEmpty())){

                    if (Integer.parseInt(height.getText()) >= 300 && Integer.parseInt(width.getText()) >= 300){
                        setFood(Integer.parseInt(food.getText()));
                        setGrass(Integer.parseInt(grass.getText()));
                        setHabitats(Integer.parseInt(habitats.getText()));
                        setObstacles(Integer.parseInt(obstacles.getText()));
                        setLions(Integer.parseInt(lions.getText()));
						setZebras(Integer.parseInt(zebras.getText()));
						if (Integer.parseInt(height.getText()) != getHeight() || Integer.parseInt(width.getText()) != getWidth()) {
                            setHeight(Integer.parseInt(height.getText()));
                            setWidth(Integer.parseInt(width.getText()));
                            MainAnimation.setStartOver();
                        }
                        stage.close();
                        MainAnimation.setStartOver();
                    }
                    else label.setText("Height and width have to be greater than 300.");

				}
                else {
					label.setText("Please enter all details.");
				}
			}
		});

        //Setting an action for the current button
        current.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                food.setText(String.valueOf(amountFood));
                grass.setText(String.valueOf(amountGrass));
                habitats.setText(String.valueOf(amountHabitats));
                obstacles.setText(String.valueOf(amountObstacles));
                lions.setText(String.valueOf(amountLions));
				zebras.setText(String.valueOf(amountZebras));
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
                grass.clear();
                habitats.clear();
				obstacles.clear();
                lions.clear();
				zebras.clear();
                height.clear();
                width.clear();
				label.setText(null);
			}
		});

        stage.showAndWait();
	}

    /**
     *The user can Edit the configuration with continuous Pop ups
     */
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
            dialog.setTitle("Lions");
            dialog.setHeaderText("How many Lions do you want to create?");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                check = result.get();
            }
        }while(check == null);
		this.amountLions = Integer.parseInt(check);


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

    /**
     * The User can create a new Configuration and save it in a new directory through a pop up and the EditConfig2
     *
     */
    public void newConfiguration(){
        Stage stage = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("New Location");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Config Files", "*.ser"));
        File existDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(existDirectory);
        File file =  fileChooser.showSaveDialog(stage);


        if (file != null) {

            this.lastFile = file.getPath();
            this.EditConfig2();
            this.EndSave();
        }
    }

    /**
     * Opens a configuration chose by the user through a FileChooser
     */
    public void openConfigurationFile(){
		Stage stage = new Stage();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Configuration File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Config Files", "*.ser"));
		File existDirectory = new File(System.getProperty("user.dir"));
		fileChooser.setInitialDirectory(existDirectory);
		File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            this.lastFile = file.getPath();
            this.Load();
            MainAnimation.setStartOver();
            this.EndSave();
        }
	}


    /**
     * AmountFood Setter
     *
     * @param amountFood amount of food in the world
     */
    public void setFood(int amountFood){
		this.amountFood = amountFood;
	}

    /**
     * Grass Setter
     * @param amountGrass amount of Grass circles in the world
     */
    public void setGrass(int amountGrass){
        this.amountGrass = amountGrass;
    }

    /**Habitats Setter
     * @param amountHabitats amount of Habitats in the world
     */
    public void setHabitats(int amountHabitats) {
        this.amountHabitats = amountHabitats;
    }

    /**
     * Obstacles Setter
     * @param amountObstacles amount of Obstacles in the world
     */
    public void setObstacles(int amountObstacles){
		this.amountObstacles = amountObstacles;
	}

    /**
     * Lions Setter
     * @param amountLions amount of Lions in the world
     */
    public void setLions(int amountLions){
		this.amountLions = amountLions;
	}

    /**
     * Zebras Setter
     * @param amountZebras amount of Zebras in the world
     */
    public void setZebras(int amountZebras){
        this.amountZebras = amountZebras;
    }

    /**
     * Width Setter
     * @param width width of the world in pixels
     */
    public void setWidth(int width){
		this.width = width;
	}

    /**Height Setter
     * @param height Height of the world in pixels
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * AmountFood Getter
     * @return amount of food that has to be created
     */
    public int getFood(){
        return this.amountFood;
    }

    /**
     * Grass Getter
     * @return amount of Grass circles in the world
     */
    public int getGrass(){
        return this.amountGrass;
    }

    /**
     * Habitats Getter
     * @return amount of Habitats in the world
     */
    public int getHabitats() {
        return amountHabitats;
    }

    /**
     * Obstacles Setter
     * @return amount of Obstacles in the world
     */
    public int getObstacles(){
        return this.amountObstacles;
    }

    /**
     * Lions Getter
     * @return amount of Lions in the world
     */
    public int getLions(){
        return this.amountLions;
    }

    /**
     * Zebras Getter
     * @return amount of Zebras in the world
     */
    public int getZebras(){
        return this.amountZebras;
    }

    /**
     * Width Getter
     * @return width of the world in pixels
     */
    public int getWidth(){
        return this.width;
    }

    /**
     * Height Getter
     * @return height of the world in pixels
     */
    public int getHeight() {
        return height;
    }


    /**
     * Displays the All the Configuration throught an Alert.
     */
    public void displayConfig(){
		String print;

        String nameOfFile;
        int i = this.lastFile.length()-1;
        char[] temp = this.lastFile.toCharArray();

        // check when the first / or \ character appears
        while((int)temp[i] != 92 && (int)temp[i] != 47){
            i--;
        }

        i++;
        nameOfFile = this.lastFile.substring(i,this.lastFile.length());

		print = "Name of file: " + nameOfFile + "\n" +
				"Amount of Food: " + this.amountFood + "\n" +
                "Amount of Grass: " + this.amountGrass + "\n" +
                "Amount of Habitats: " + this.amountHabitats + "\n" +
                "Amount of Obstacles: " + this.amountObstacles + "\n" +
				"Amount of Lions: " + this.amountLions + "\n" +
                "Amount of Zebras: " + this.amountZebras + "\n" +
				"Height: " + this.height + "\n" +
                "Width: " + this.width;

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Configuration loaded:");
        alert.setContentText(print);
        alert.setHeaderText(null);
        alert.getDialogPane().setMaxWidth(300);
        alert.showAndWait();

	}

    /**
     * Displays The amounts of each thing in the world and its size
     *
     */
    public void displayMap(){
		String print;
		print = "Amount of Food: " + this.amountFood + "\n" +
                "Amount of Grass: " + this.amountGrass + "\n" +
                "Amount of Habitats: " + this.amountHabitats + "\n" +
                "Amount of Obstacles: " + this.amountObstacles + "\n" +
                "Amount of Lions: " + this.amountLions + "\n" +
                "Amount of Zebras: " + this.amountZebras + "\n" +
                "Height: " + this.height + "\n" +
                "Width: " + this.width;

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Configuration loaded:");
        alert.setContentText(print);
        alert.setHeaderText(null);
        alert.getDialogPane().setMaxWidth(300);
        alert.showAndWait();
	}

    /**
     * Saves a Configuration File in the "lastFile" directory
     */
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

    /**
     * Modifies the lastFile of the Configuration and executes Save()
     */
    public void SaveAs(){
        Stage stage = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("New Location");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Config Files", "*.ser"));
        File existDirectory = new File(System.getProperty("user.dir"));
        fileChooser.setInitialDirectory(existDirectory);
        File file =  fileChooser.showSaveDialog(stage);


        if (file != null) {

            this.lastFile = file.getPath();

            this.Save();
        }
    }

    /**
     * Saves in the default Configuration file "defConfiguration.ser" the last Configuration used
     */
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

    /**
     * Tries to load a Configuration file in the directory given
     *
     * @param lastFile directory given (location)
     * @return Configuration file found in the given directory or If not found it returns a null configuration file
     */
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
		}
		return e;
	}

    /**
     * Loads the starting file
     *
     * Tries to load the default configuraion file
     * if found
     *      if the lastFile is not the default configuration file it tryes to load that file
     *      else ir creates a default configuration file
     * else it creates one
     *
     * finally it displays it
     */
    public void StartLoad(){
		Configuration e = tryLoad("defConfiguration.ser");

        if (e == null){
            e = new Configuration();
        }

		if (!e.lastFile.equals("/defConfiguration.ser")){
			e = tryLoad(e.lastFile);
            if (e == null){
                e = new Configuration();
            }
			e.Save();
		}

		System.out.println("Deserialized Configuration...");

        e.displayConfig();

		this.setConfiguration(e);
	}

    /**
     *
     * Loads a configuration in lastFile directory
     * if not found it creates a default config file
     *
     * finally it dislays it
     */
    public void Load(){
		Configuration e;

		e = tryLoad(this.lastFile);

        if (e == null){
            e = new Configuration();
        }
		
		System.out.println("Deserialized Configuration...");
		e.displayConfig();
		this.setConfiguration(e);
	}
}
