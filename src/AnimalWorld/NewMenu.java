package AnimalWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import javafx.animation.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.*;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NewMenu extends Application {

    String[] animalTypes = {"Lion", "Zebra"};
    String[] foodTypes = {"Meat", "NonMeat", "Grass"};
    static int width = 1280;
    static int height = 720;
    boolean toggleSmellRange = false;
    boolean toggleTargets = false;
    boolean toggleStats = false;
	static boolean pause = true;
	static boolean stop = true;
    static boolean startOver = false;
    static boolean exit = false;
    int cycle = 0;
    int collisionCycles = -50;
    int tries = 0;

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setHeight(int height) {
        NewMenu.height = height;
    }

    public static void setWidth(int width) {
        NewMenu.width = width;
    }

    public static void setStartOver(){
        startOver = true;
        pause = true;
        stop = true;
    }

    public void addLifeForm(final World world, final Configuration config){
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 250, 100);
        final Stage stage = new Stage();

        stage.setTitle("Add Life Form");
        stage.setScene(scene);
        stage.sizeToScene();

        grid.setPadding(new Insets(10, 10, 10, 50));  // sets margins
        grid.setVgap(5);
        grid.setHgap(5);


        // Defining the Type ChoiceBox
        final ChoiceBox typeCb  = new ChoiceBox(FXCollections.observableArrayList(
                "Choose an animal type",
                new Separator()
        ));
        typeCb.getSelectionModel().selectFirst();   // In order to have "Choose an ID" already selected

        //To input all the animal Types:
        for (String animalType : animalTypes){
            typeCb.getItems().add(animalType);
        }

        GridPane.setHalignment(typeCb, HPos.CENTER);
        GridPane.setConstraints(typeCb, 0, 0, 2, 1);
        grid.getChildren().add(typeCb);


        //BUTTONS

        //Defining the ok button
        Button ok = new Button("OK");
        ok.alignmentProperty();
        GridPane.setHalignment(ok, HPos.RIGHT);
        GridPane.setConstraints(ok, 0, 2);
        grid.getChildren().add(ok);



        //Defining the cancel button
        Button cancel = new Button("Cancel");
        GridPane.setHalignment(cancel, HPos.RIGHT);
        GridPane.setConstraints(cancel, 1, 2);
        grid.getChildren().add(cancel);


        //Setting an action for the OK button
        ok.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                int value = typeCb.getSelectionModel().getSelectedIndex()-2;
                if (value >= 0) {
                    String print;

                    print = "Are you sure you want to Add this Life Form?";

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Add Life Form:");
                    alert.setContentText(print);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        // ... user chose OK
                        world.addAnimal(value);
                        if (value == 0) config.setLions(config.getLions() + 1);
                        if (value == 0) config.setZebras(config.getZebras() + 1);

                    }
                }
            }
        });


        //Setting an action for the cancel button
        cancel.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
               stage.close();
            }
        });




        stage.showAndWait();

    }

    public void chooseLifeForm(final World world, final Configuration config){
        GridPane grid = new GridPane();
        Scene scene = new Scene(grid, 300, 100);
        final Stage stage = new Stage();

        stage.setTitle("Choose Life Form");
        stage.setScene(scene);
        stage.sizeToScene();

        grid.setPadding(new Insets(10, 10, 10, 10));  // sets margins
        grid.setVgap(5);
        grid.setHgap(5);


        // Defining the Type ChoiceBox
        final ChoiceBox typeCb  = new ChoiceBox(FXCollections.observableArrayList(
                "Choose an animal type",
                new Separator()
        ));
        typeCb.getSelectionModel().selectFirst();   // In order to have "Choose an ID" already selected

        //To input all the animal Types:
        for (String animalType : animalTypes){
            typeCb.getItems().add(animalType);
        }

        GridPane.setConstraints(typeCb, 0, 0, 2, 1);
        grid.getChildren().add(typeCb);



        // Defining the ID ChoiceBox
        final ChoiceBox idCb  = new ChoiceBox(FXCollections.observableArrayList (
                "Choose an ID",
                new Separator()
        ));
        idCb.getSelectionModel().selectFirst();     // In order to have "Choose an ID" already selected
        idCb.setVisible(false);

        GridPane.setConstraints(idCb, 2, 0, 2, 1);
        grid.getChildren().add(idCb);



        // Defining label
        final Label label = new Label();
        GridPane.setConstraints(label, 0, 2);
        GridPane.setColumnSpan(label, 3);
        grid.getChildren().add(label);



        //Defining the show button
        Button show = new Button("Show");
        show.alignmentProperty();
        GridPane.setHalignment(show, HPos.CENTER);
        GridPane.setConstraints(show, 0, 3);
        grid.getChildren().add(show);



        //Defining the Modify button
        Button modify = new Button("Modify");
        modify.alignmentProperty();
        GridPane.setHalignment(modify, HPos.CENTER);
        GridPane.setConstraints(modify, 1, 3);
        grid.getChildren().add(modify);



        //Defining the remove button
        Button remove = new Button("Remove");
        remove.alignmentProperty();
        GridPane.setHalignment(remove, HPos.CENTER);
        GridPane.setConstraints(remove, 2, 3);
        grid.getChildren().add(remove);



        //Defining the done button
        Button done = new Button("Done");
        GridPane.setHalignment(done, HPos.CENTER);
        GridPane.setConstraints(done, 3, 3);
        grid.getChildren().add(done);



        // Setting the action for the Type ChoiceBox
        typeCb.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // action
                final int value = newValue.intValue() - 2;
                if (newValue != oldValue){
                    if (oldValue.intValue()-2 >= 0) {
                        world.hideSpecificID(oldValue.intValue() - 2);
                    }
                    idCb.getItems().remove(0, idCb.getItems().size());
                    if (newValue.intValue() >= 2) {
                        idCb.getItems().addAll(
                                "Choose an ID",
                                new Separator()
                        );
                        idCb.getSelectionModel().selectFirst();
                    }
                    else idCb.setVisible(false);
                }

                if (newValue.intValue() >= 2) {
                    for (Animal animal : world.animalList.get(value)) {
                        idCb.getItems().add(animal.getID().getText());
                    }
                    idCb.setVisible(true);

                    // show that specific animals ID visible
                    world.showSpecificID(value);

                }
            }
        });



        //Setting an action for the show button
        show.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                int value2 = idCb.getSelectionModel().getSelectedIndex() -2;
                int value = typeCb.getSelectionModel().getSelectedIndex()-2;
                if (value >= 0 && value2 >= 0) {
                    // show information for that animal
                    world.animalList.get(value).get(value2).displayAnimal();
                }
                else label.setText("Please choose an animal type and an ID");
            }
        });



        //Setting an action for the Modify button
        modify.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                int value2 = idCb.getSelectionModel().getSelectedIndex() -2;
                int value = typeCb.getSelectionModel().getSelectedIndex()-2;
                if (value >= 0 && value2 >= 0) {
                    world.animalList.get(value).get(value2).modifyAnimal();
                }
            }
        });



        //Setting an action for the Remove button
        remove.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                int value2 = idCb.getSelectionModel().getSelectedIndex() -2;
                int value = typeCb.getSelectionModel().getSelectedIndex()-2;
                if (value >= 0 && value2 >= 0) {

                    String print;

                    print = "Are you sure you want to remove this Life Form?";

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Remove Life Form:");
                    alert.setContentText(print);

                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK){
                        // ... user chose OK
                        world.removeAnimal(value, value2);
                        if (value == 0) config.setLions(config.getLions() - 1);
                        if (value == 0) config.setZebras(config.getZebras() - 1);

                        idCb.getItems().remove(0, idCb.getItems().size());
                        idCb.getItems().addAll(
                                "Choose an ID",
                                new Separator()
                        );
                        idCb.getSelectionModel().selectFirst();


                        for (Animal animal : world.animalList.get(value)) {
                            idCb.getItems().add(animal.getID().getText());
                        }

                    }

                }
            }
        });



        //Setting an action for the done button
        done.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                int value = typeCb.getSelectionModel().getSelectedIndex()-2;
                if (value >= 0) {
                    world.hideSpecificID(value);
                }
                stage.close();
            }
        });



        stage.showAndWait();
    }

    public void displayApplication(){
        String print  =
                        "Built since: 09/01/2016" + "\n" +
                        "Version Finished in: 21/01/2016" + "\n" +
                        "Version: 1.0  " + "\n";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Animal World application");
        alert.setTitle("About Animal World");
        alert.setContentText(print);
        alert.showAndWait();
    }

    public void displayAuthor(){
        String print  =
                    "Student number: 23021090" + "\n" +
                    "Student username: jw021090" + "\n" +
                    "Course: Bsc Computer Science with Industrial Year" +  "\n" +
                    "Tutor: Dr Richard Mitchel" + "\n" +
                    "Send any questions to: kikadass@gmail.com " + "\n";

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Enrique Piera Serra");
        alert.setTitle("About the Author");
        alert.setContentText(print);
        alert.showAndWait();
    }

	public MenuBar CreateMenuBar(final World world, final Configuration config) {

		MenuBar menuBar = new MenuBar();

		final Menu menu1 = new Menu("File");
		final Menu menu2 = new Menu("View");
		final Menu menu3 = new Menu("Edit");
		final Menu menu4 = new Menu("Help");
		menuBar.getMenus().addAll(menu1, menu2, menu3, menu4);

		//inner menus
		MenuItem menu11 = new MenuItem("New Configuration");
		MenuItem menu12 = new MenuItem("Open Configuration File");
		MenuItem menu13 = new MenuItem("Save");
		MenuItem menu14 = new MenuItem("Save As");
        MenuItem menu15 = new MenuItem("Exit");

		menu1.getItems().addAll(menu11, menu12, menu13, menu14, menu15);

		MenuItem menu21 = new MenuItem("Display Configuration");
		MenuItem menu23 = new MenuItem("Info About Life Forms");
		MenuItem menu24 = new MenuItem("Info About Map");
        final MenuItem menu25 = new MenuItem("Show Smell Range");
        final MenuItem menu26 = new MenuItem("Show Targets");
        final MenuItem menu27 = new MenuItem("Show Stats");

        menu2.getItems().addAll(menu21, menu23, menu24, menu25, menu26, menu27);

		MenuItem menu31 = new MenuItem("Modify Life Form parameters");
		MenuItem menu32 = new MenuItem("Remove Life Form");
		MenuItem menu33 = new MenuItem("Add Life Form");
		MenuItem menu34 = new MenuItem("Edit Configuration");


		menu3.getItems().addAll(menu31, menu32, menu33, menu34);
				/*
				MenuItem menu41 = new MenuItem("Run");
				MenuItem menu42 = new MenuItem("Pause/Restart");
				MenuItem menu43 = new MenuItem("Reset");
				MenuItem menu44 = new MenuItem("Display Map At Each Iteration");
				
				menu4.getItems().addAll(menu41, menu42, menu43, menu44);
				*/

		MenuItem menu41 = new MenuItem("Display info about application");
		MenuItem menu42 = new MenuItem("Display info about author");

		menu4.getItems().addAll(menu41, menu42);

		// Setting the actions of sub menus

        // Menu 1 --> File
		menu11.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				config.NewConfiguration2();
			}
		});


        //Open Config file
		menu12.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				config.OpenConfigurationFile2();
			}
		});

        //Save
		menu13.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				config.Save();
            }
		});

        //Save as
		menu14.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				config.SaveAs();
			}
		});

        //Exit
        menu15.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                exit = true;
            }
        });


		//Menu 2 --> View
        //Display config
		menu21.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				config.displayConfig();
			}
		});


        // Display Bugs
		menu23.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
                chooseLifeForm(world, config);
                world.hideID();
            }
		});


        // display map
		menu24.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
            config.displayMap();
			}
		});


        //Show and hide Smell Range
        menu25.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {

                if (toggleSmellRange) {
                    world.hideSmellRange();
                    menu25.setText("Show Smell Range");
                    toggleSmellRange = false;
                } else {
                    world.showSmellRange();
                    menu25.setText("Hide Smell Range");
                    toggleSmellRange = true;
                }
            }
        });


        //Show and hide Targets
        menu26.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                if (toggleTargets) {
                    world.hideTargets();
                    menu26.setText("Show Targets");
                    toggleTargets = false;
                } else {
                    world.showTargets();
                    menu26.setText("Hide Targets");
                    toggleTargets = true;
                }
            }
        });


        // Show and hide Stats
        menu27.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                if (toggleStats) {
                    world.hideStats();
                    menu27.setText("Show Stats");
                    toggleStats = false;
                } else {
                    world.showStats();
                    menu27.setText("Hide Stats");
                    toggleStats = true;
                }
            }
        });


        //Menu 3
        //Modify Life Form parameters
		menu31.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				chooseLifeForm(world, config);
                world.hideID();
            }
		});


        // Remove Life Form
		menu32.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
                chooseLifeForm(world, config);
                world.hideID();
            }
		});

        //Add Life Form
		menu33.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

                addLifeForm(world, config);

			}
		});


        //Edit config
		menu34.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				config.EditConfig2();
			}
		});


        //Menu4 --> Help
        //Display info about application
        menu41.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                displayApplication();
            }
        });



        //Display info about author
        menu42.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                displayAuthor();
            }
        });



		return menuBar;
	}

    public void eat(World world, ArrayList<Animal> animalTypeList, int i) {
        for (int j = 0; j < world.foodList.size(); j++) {
            if (Collisions.nonEfficientCollide(animalTypeList.get(i).getBody(), world.foodList.get(j).getBody())) {
                for (String foodPreference : animalTypeList.get(i).getFoodPreferences()) {

                    if (world.foodList.get(j).getType().equals(foodPreference)) {
                        // if hungry: eat one by one until not hungry or finished
                        // if there is still food, carry what you can depending on strenght

                        double amount = world.foodList.get(j).getEnergy() + animalTypeList.get(i).getFood();
                        if (amount >= animalTypeList.get(i).getMaxFood()) {
                            animalTypeList.get(i).setFood(animalTypeList.get(i).getMaxFood());
                            amount -= animalTypeList.get(i).getMaxFood();

                            // if after eating there is still food left take it or leave it:
                            if (amount > 0) {
                                double amount2 = amount + animalTypeList.get(i).getFoodCarring();
                                if (amount2 >= animalTypeList.get(i).getStrenght()) {
                                    animalTypeList.get(i).setFoodCarring(animalTypeList.get(i).getStrenght());
                                    amount2 -= animalTypeList.get(i).getStrenght();

                                    // if not strong enough leave extra food
                                    if (amount2 > 0) {
                                        world.foodList.get(j).setEnergy(amount2);
                                    } else {
                                        if (!world.foodList.get(j).getType().equals("Grass")) {
                                            world.deleteFood(j);
                                            j--;
                                        } else world.foodList.get(j).setEnergy(0);
                                    }
                                } else {
                                    animalTypeList.get(i).setFoodCarring(amount2);
                                    if (!world.foodList.get(j).getType().equals("Grass")) {
                                        world.deleteFood(j);
                                        j--;
                                    } else world.foodList.get(j).setEnergy(0);

                                }
                            } else {
                                if (!world.foodList.get(j).getType().equals("Grass")) {
                                    world.deleteFood(j);
                                    j--;
                                } else world.foodList.get(j).setEnergy(0);

                            }
                        } else {
                            animalTypeList.get(i).setFood(amount);
                            if (!world.foodList.get(j).getType().equals("Grass")) {
                                world.deleteFood(j);
                                j--;
                            } else world.foodList.get(j).setEnergy(0);
                        }
                    }
                }
            }
        }
    }

    public double distance(double x1, double y1, double x2, double y2) {
        double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
        return distance;
    }

    public void animalTypeUpdate(ArrayList<Animal> animalTypeList, World world){
        for (int i = 0; i < animalTypeList.size(); i++) {
            animalTypeList.get(i).update();

            // ANIMAL COLISIONS
            if (Collisions.nonEfficientCollide(animalTypeList.get(i).getBody(), animalTypeList.get(i).getProvisionalTarget().getBody())) {
                animalTypeList.get(i).getRandomLocalTarget();
            }

            if (Collisions.collideAnimals(animalTypeList.get(i).getBody(), world.animalList)) {
                // reproduce
            }

            // if it finds an obstacle try to avoid it and find a new target
            if (Collisions.collideObstacle(animalTypeList.get(i).getBody(), world)) {
                // choose new target
                animalTypeList.get(i).getOut();
                animalTypeList.get(i).getRandomLocalTarget();
                if (animalTypeList.get(i).getMainTarget().getBody().getCenterX() != 0) {
                    animalTypeList.get(i).setMainTarget(new Target(0, 0, 2));
                    collisionCycles = cycle;
                    tries++;
                }

            }

            // SMELLING COLLISIONS
            if (collisionCycles + 50 * tries <= cycle) {
                if (animalTypeList.get(i).getFoodCarring() < animalTypeList.get(i).getStrenght()) {
                    if (Collisions.collideFood(animalTypeList.get(i).getSmellRange(), world)) {
                        // main target: center of that food.
                        for (int j = 0; j < world.foodList.size(); j++) {
                            for (String foodPreference : animalTypeList.get(i).getFoodPreferences()) {
                                if (world.foodList.get(j).getType().equals(foodPreference)) {
                                    if (world.foodList.get(j).getEnergy() > animalTypeList.get(i).getMinFoodCons()) {
                                        if (Collisions.nonEfficientCollide(animalTypeList.get(i).getSmellRange(), world.foodList.get(j).getBody())) {

                                            // if food is closer than actual main target --> go to it
                                            if (Math.abs(distance(animalTypeList.get(i).getPosX(), animalTypeList.get(i).getPosY(), world.foodList.get(j).getBody().getCenterX(), world.foodList.get(j).getBody().getCenterY())) < Math.abs(distance(animalTypeList.get(i).getPosX(), animalTypeList.get(i).getPosY(), animalTypeList.get(i).getMainTarget().getBody().getCenterX(), animalTypeList.get(i).getMainTarget().getBody().getCenterY()))) {

                                                int tx;
                                                int ty;
                                                int rad;

                                                ty = (int) world.foodList.get(j).getBody().getCenterY();

                                                tx = (int) world.foodList.get(j).getBody().getCenterX();
                                                rad = (int) world.foodList.get(j).getBody().getRadius();

                                                // create the main target depending on tx and ty
                                                animalTypeList.get(i).setMainTarget(new Target(tx, ty, rad));
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                } else if (animalTypeList.get(i).getMainTarget().getBody().getCenterX() != 0) {
                    animalTypeList.get(i).setMainTarget(new Target(0, 0, 1));
                }
            }


            if (Collisions.collideFood(animalTypeList.get(i).getBody(), world)) {
                eat(world, animalTypeList, i);
                tries = 0;
            }
        }

    }

    public void mainProgram(final Stage stage1, final Configuration config){
        setHeight(config.getHeight());
        setWidth(config.getWidth());


        final Group root = new Group();
        final World world = new World(root, config, animalTypes.length, foodTypes.length);
        final Scene scene = new Scene(root, config.getWidth(), config.getHeight());


        final Rectangle rectangle = new Rectangle(0, height - 50, width, 50);
        root.getChildren().add(rectangle);

        //Menu
        MenuBar menuBar1 = new MenuBar();
        menuBar1 = CreateMenuBar(world, config);
        menuBar1.setMinWidth(width);
        final MenuBar menuBar = menuBar1;

        root.getChildren().add(menuBar);


        //creating buttons
        final Button pauseBtn = new Button();
        pauseBtn.setText("Pause");
        pauseBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                if (pause) {
                    pauseBtn.setText("Pause");
                    pause = false;
                } else {
                    pauseBtn.setText("Replay");
                    pause = true;
                }

            }

        });

        final Button stopBtn = new Button();
        pauseBtn.setVisible(false);
        stopBtn.setText("START");
        stopBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                if (stop) {
                    pauseBtn.setVisible(true);
                    stopBtn.setText("STOP");
                    stop = false;
                    pause = false;
                } else {
                    setStartOver();
                    pauseBtn.setVisible(false);
                    stopBtn.setText("START");

                }

            }

        });

        root.getChildren().add(stopBtn);
        root.getChildren().add(pauseBtn);


        KeyFrame frame = new KeyFrame(Duration.millis(15), new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent t) {

                NewMenu.setHeight((int)scene.getHeight());
                NewMenu.setWidth((int)scene.getWidth());

                pauseBtn.setLayoutX(NewMenu.getWidth() / 2 - 50);
                pauseBtn.setLayoutY(NewMenu.getHeight() - 40);

                stopBtn.setLayoutX(NewMenu.getWidth() / 2 + 10);
                stopBtn.setLayoutY(NewMenu.getHeight() - 40);

                rectangle.setY(getHeight()-50);
                rectangle.setWidth(NewMenu.getWidth());

                menuBar.setMinWidth(NewMenu.getWidth());

                world.setWidth(NewMenu.getWidth());
                world.setHeight(NewMenu.getHeight());



                if (!pause && !startOver) {
                    cycle++;

                    // 4000 cycles = 1day == 1 real minute
                    if (cycle%4000 == 0){
                        System.out.println("Day 1");
                        world.setDay(world.getDay() + 1);
                        if (world.getDay() == 365 ){
                            world.setYear(world.getYear() +1);
                            world.setDay(0);
                        }
                    }
                    for (ArrayList<Animal> animalTypeList : world.animalList){
                        animalTypeUpdate(animalTypeList, world);
                    }

                    for (int i = 0; i < world.foodList.size(); i++){
                        if (world.foodList.get(i).getType().equals("Grass")){
                            world.foodList.get(i).update();
                        }
                    }

                }
                if (startOver){
                    stage1.close();
                    startOver = false;
                    mainProgram(stage1, config);
                }
                if (exit){
                    stage1.close();
                }

            }

        });

        TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build().play();

        stage1.setTitle("Animal World");
        stage1.setScene(scene);

        stage1.show();

    }

	@Override
	public void start(Stage stage1) throws Exception {
        final Configuration config = new Configuration();
        config.StartLoad();

        mainProgram(stage1, config);
    }

	public static void main(String[] args) {
            launch(args);

	}

}
