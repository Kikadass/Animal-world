package AnimalWorld;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.*;
import javafx.scene.*;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

public class NewMenu extends Application {

    static int width = 1280;
    static int height = 720;
    boolean toggleSmellRange = true;
    boolean toggleTargets = true;
    boolean toggleStats = true;
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

    public static int Loop(String[] Options, String WindowName, boolean Edit) {
		List<String> optionList = new ArrayList<String>();
		for (int i = 0; i < Options.length; i++) {
			optionList.add(Options[i]);
		}
		if (!Edit) optionList.add("Exit");

		Object[] options = optionList.toArray();

		int value = JOptionPane.showOptionDialog(
				null,
				"Please select:\n",
				WindowName,
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				optionList.get(0));
		String opt = optionList.get(value);

		for (int i = 0; i < Options.length; i++) {
			if (opt == "Exit" || value == JOptionPane.CLOSED_OPTION) return -1;
			else if (opt == Options[i]) return i;
		}
		return -1;
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
        final MenuItem menu25 = new MenuItem("Hide Smell Range");
        final MenuItem menu26 = new MenuItem("Hide Targets");
        final MenuItem menu27 = new MenuItem("Hide Stats");

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
				for (int i = 0; i < config.getBugs(); i++) {
					//world.BugList[i].printBug();
				}
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

		EditMenu bugsMenu = new EditMenu();

		menu31.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// choose between bugs
				//int bugChosen = Loop(bugsMenu.getBugOptions(world.BugList), "Bug List", true);
				//world.BugList[bugChosen].printBug();	// print bug chosen
				String[] Modify = {"Yes", "No"};
				int change = Loop(Modify, "Modify?", true);
				//if (change == 0) bugsMenu.ChangeBug(world.BugList[bugChosen]);	// modify bug
			}
		});

		menu32.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// choose between bugs
				//int bugChosen = Loop(bugsMenu.getBugOptions(world.BugList), "Bug List", true);
				String[] Modify = {"Yes", "No"};
				int change = Loop(Modify, "DELETE?", true);
				if (change == 0) {
					//world.BugList[bugChosen].die();	// delete bug visually
					//world.BugList = world.deleteBug(bugChosen, world.BugList); // delete bug from list
					config.setBugs(config.getBugs() - 1);
				}
			}
		});

		menu33.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				//world.AddBug();
				config.setBugs(config.getBugs() + 1);
			}
		});

		menu34.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				config.EditConfig2();
				//startOver = true;
			}
		});

		return menuBar;
	}

    public void eat(World world, int i) {
        for (int j = 0; j < world.foodList.size(); j++) {
            if (Collisions.nonEfficientCollide(world.animalList.get(i).getBody(), world.foodList.get(j).getBody())) {
                // if hungry: eat one by one until not hungry or finished
                // if there is still food, carry what you can depending on strenght

                double amount = world.foodList.get(j).getEnergy() + world.animalList.get(i).getFood();
                if (amount >= world.animalList.get(i).getMaxFood()) {
                    world.animalList.get(i).setFood(world.animalList.get(i).getMaxFood());
                    amount -= world.animalList.get(i).getMaxFood();

                    // if after eating there is still food left take it or leave it:
                    if (amount > 0) {
                        double amount2 = amount + world.animalList.get(i).getFoodCarring();
                        if (amount2 >= world.animalList.get(i).getStrenght()) {
                            world.animalList.get(i).setFoodCarring(world.animalList.get(i).getStrenght());
                            amount2 -= world.animalList.get(i).getStrenght();

                            // if not strong enough leave extra food
                            if (amount2 > 0) {
                                world.foodList.get(j).setEnergy(amount2);
                            }
                            else {
                                world.deleteFood(j);
                                j--;
                            }
                        }
                        else {
                            world.animalList.get(i).setFoodCarring(amount2);
                            world.deleteFood(j);
                            j--;
                        }
                    }
                    else {
                        world.deleteFood(j);
                        j--;
                    }
                }
                else {
                    world.animalList.get(i).setFood(amount);
                    world.deleteFood(j);
                    j--;
                }
            }
        }
    }

    public double distance(double x1, double y1, double x2, double y2) {
        double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
        return distance;
    }

    public void mainProgram(final Stage stage1, final Configuration config){
        setHeight(config.getHeight());
        setWidth(config.getWidth());


        final Group root = new Group();
        final World world = new World(root, config);
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

                    for (int i = 0; i < world.animalList.size(); i++) {
                        world.animalList.get(i).update();

                        // ANIMAL COLISIONS
                        if (Collisions.nonEfficientCollide(world.animalList.get(i).getBody(), world.animalList.get(i).getProvisionalTarget().getBody())){
                            world.animalList.get(i).getRandomLocalTarget();
                        }

                        if (Collisions.collideAnimals(world.animalList.get(i).getBody(), world.animalList)){
                            // reproduce
                        }

                        // if it finds an obstacle try to avoid it and find a new target
                        if (Collisions.collideObstacle(world.animalList.get(i).getBody(), world)){
                            // choose new target
                            world.animalList.get(i).getOut();
                            world.animalList.get(i).getRandomLocalTarget();
                            if (world.animalList.get(i).getMainTarget().getBody().getCenterX() != 0){
                                world.animalList.get(i).setMainTarget(new Target(0,0 ,2));
                                collisionCycles =  cycle;
                                tries++;
                            }

                        }

                        // SMELLING COLLISIONS
                        if (collisionCycles+50*tries <= cycle){
                            if (world.animalList.get(i).getFoodCarring() < world.animalList.get(i).getStrenght()) {
                                if (Collisions.collideFood(world.animalList.get(i).getSmellRange(), world)) {
                                    // main target: center of that food.
                                    for (int j = 0; j < world.foodList.size(); j++) {
                                        if (Collisions.nonEfficientCollide(world.animalList.get(i).getSmellRange(), world.foodList.get(j).getBody())) {
                                            // if food is closer than actual main target --> go to it
                                            if (Math.abs(distance(world.animalList.get(i).getPosX(), world.animalList.get(i).getPosY(), world.foodList.get(j).getBody().getCenterX(), world.foodList.get(j).getBody().getCenterY())) < Math.abs(distance(world.animalList.get(i).getPosX(), world.animalList.get(i).getPosY(), world.animalList.get(i).getMainTarget().getBody().getCenterX(), world.animalList.get(i).getMainTarget().getBody().getCenterY()))) {

                                                int tx;
                                                int ty;
                                                int rad;

                                                ty = (int) world.foodList.get(j).getBody().getCenterY();

                                                tx = (int) world.foodList.get(j).getBody().getCenterX();
                                                rad = (int)world.foodList.get(j).getBody().getRadius();

                                                // create the main target depending on tx and ty
                                                world.animalList.get(i).setMainTarget(new Target(tx, ty, rad));
                                            }

                                        }
                                    }
                                }
                            }
                            else if (world.animalList.get(i).getMainTarget().getBody().getCenterX() != 0){
                                world.animalList.get(i).setMainTarget(new Target(0, 0, 1));
                            }
                        }


                        if (Collisions.collideFood(world.animalList.get(i).getBody(), world)) {
                            eat(world, i);
                            tries = 0;
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
