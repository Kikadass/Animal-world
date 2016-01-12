package AnimalWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

	static int width = 1000;
	static int height = 700;
	int nCircles = 10;
	boolean pause = true;
	boolean stop = true;

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

	public MenuBar CreateMenuBar(World world, final Configuration config) {

		MenuBar menuBar = new MenuBar();

		//final Menu menu1 = new Menu("File");
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

		menu1.getItems().addAll(menu11, menu12, menu13, menu14);

		MenuItem menu21 = new MenuItem("Display Configuration");
		MenuItem menu22 = new MenuItem("Edit Configuration");
		MenuItem menu23 = new MenuItem("Info About Life Forms");
		MenuItem menu24 = new MenuItem("Info About Map");

		menu2.getItems().addAll(menu21, menu22, menu23, menu24);

		MenuItem menu31 = new MenuItem("Modify Life Form parameters");
		MenuItem menu32 = new MenuItem("Remove Life Form");
		MenuItem menu33 = new MenuItem("Add Life Form");

		menu3.getItems().addAll(menu31, menu32, menu33);
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

		// setting the actions of sub menus
		// Menu 1
		menu11.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				config.NewConfiguration();
			}
		});

		menu12.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				config.OpenConfigurationFile();
			}
		});

		menu13.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				config.Save();
			}
		});

		menu14.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String lastFile = null;
				do {
					lastFile = JOptionPane.showInputDialog(null, "What is the location where you want to safe the config file?");
				} while (lastFile == null);

				config.setLastFile(lastFile);
				config.Save();
			}
		});


		//Menu 2
		menu21.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				config.displayConfig();
			}
		});

		menu22.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				config.EditConfig();
			}
		});

		menu23.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				for (int i = 0; i < config.getBugs(); i++) {
					//world.BugList[i].printBug();
				}
			}
		});

		menu24.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				config.displayMap();
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


		return menuBar;
	}

	public ArrayList<ACircle> CreateRandomCircles() {
		ArrayList<ACircle> circles = new ArrayList<ACircle>();
		for (int i = 0; i < nCircles; i++) {
			Random rnd = new Random();
			ACircle circle = new ACircle();
			do {
				int radius = rnd.nextInt(40) + 10;
				int x = rnd.nextInt(width - radius * 2) + radius;
				int y = rnd.nextInt(height - 80 - radius * 2) + radius + 30;
				float dx = rnd.nextInt(10);
				float dy = rnd.nextInt(10);

				circle = new ACircle(x, y, radius, dx, dy);

			} while (circle.colliding(circles));
			circles.add(circle);

		}
		return circles;
	}

	public ArrayList<ACircle> CreateCircles(AWorld world) {
		ArrayList<ACircle> circles = new ArrayList<ACircle>();
		for (int i = 0; i < world.BugList.length; i++) {
			Random rnd = new Random();
			ACircle circle = new ACircle();
			do {
				int radius = rnd.nextInt(40) + 10;
				int x = rnd.nextInt(width - radius * 2) + radius;
				int y = rnd.nextInt(height - 80 - radius * 2) + radius + 30;
				float dx = rnd.nextInt(10);
				float dy = rnd.nextInt(10);

				circle = new ACircle(x, y, radius, dx, dy);

			} while (circle.colliding(circles));
			circles.add(circle);

		}
		return circles;
	}

    public void eat(World world, int i) {
        for (int j = 0; j < world.foodList.size(); j++) {
            if (Collisions.nonEfficientCollide(world.animalList.get(i).getBody(), world.foodList.get(j).getBody())) {
                // if hungry: eat one by one until not hungry or finished
                // if there is still food, carry what you can depending on strenght

                int amount = world.foodList.get(j).getEnergy() + world.animalList.get(i).getFood();
                if (amount >= world.animalList.get(i).getMaxFood()) {
                    world.animalList.get(i).setFood(world.animalList.get(i).getMaxFood());
                    amount -= world.animalList.get(i).getMaxFood();

                    // if after eating there is still food left take it or leave it:
                    if (amount > 0) {
                        int amount2 = amount + world.animalList.get(i).getFoodCarring();
                        if (amount2 >= world.animalList.get(i).getStrenght()) {
                            world.animalList.get(i).setFoodCarring(world.animalList.get(i).getStrenght());
                            amount2 -= world.animalList.get(i).getStrenght();

                            // if not strong enough leave extra food
                            if (amount2 > 0) {
                                world.foodList.get(j).setEnergy(amount2);
                            }
                            else world.deleteFood(j);
                        }
                        else {
                            world.animalList.get(i).setFoodCarring(amount2);
                            world.deleteFood(j);
                        }
                    }
                    else world.deleteFood(j);
                }
                else {
                    world.animalList.get(i).setFood(amount);
                    world.deleteFood(j);
                }
            }
        }
    }

    public double distance(double x1, double y1, double x2, double y2) {
        double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
        return distance;
    }


	@Override
	public void start(Stage stage1) throws Exception {
        final Configuration config = new Configuration();
        //config.EndSave();
        config.StartLoad();


        //AWorld world = new AWorld(config);
		final Group root = new Group();
		final Scene scene = new Scene(root, width, height);
		final World world = new World(root, config, scene);


        final Rectangle rectangle = new Rectangle(0, height - 50, width, 50);
		root.getChildren().add(rectangle);

		//Menu
        MenuBar menuBar1 = new MenuBar();
		menuBar1 = CreateMenuBar(world, config);
		menuBar1.setMinWidth(width);
		final MenuBar menuBar = menuBar1;

		root.getChildren().add(menuBar);

		//ArrayList<ACircle> circles = CreateRandomCircles();

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
					for (int i = 0; i < world.animalList.size(); i++) {
						world.animalList.get(i).getBody().setTranslateX(0);
						world.animalList.get(i).getBody().setTranslateY(0);
					}
					pauseBtn.setVisible(false);
					stopBtn.setText("START");
					stop = true;
					pause = true;
				}

			}

		});

		root.getChildren().add(stopBtn);
		root.getChildren().add(pauseBtn);


		KeyFrame frame = new KeyFrame(Duration.millis(16), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
                width = (int)scene.getWidth();
                height = (int)scene.getHeight();

                pauseBtn.setLayoutX(scene.getWidth() / 2 - 50);
				pauseBtn.setLayoutY(scene.getHeight() - 40);

				stopBtn.setLayoutX(scene.getWidth() / 2 + 10);
				stopBtn.setLayoutY(scene.getHeight() - 40);

				rectangle.setLayoutY(scene.getHeight()-700);
				rectangle.setWidth(scene.getWidth());

				menuBar.setMinWidth(scene.getWidth());

				world.setWidth((int) scene.getWidth());
				world.setHeight((int) scene.getHeight());


				if (!pause) {
					for (int i = 0; i < world.animalList.size(); i++) {
						world.animalList.get(i).update();
						//world.animalList.get(i).collideWalls(world)

                        // ANIMAL COLISIONS
                        if (Collisions.nonEfficientCollide(world.animalList.get(i).getBody(), world.animalList.get(i).getProvisionalTarget().getBody())){
                            world.animalList.get(i).getRandomLocalTarget();
                        }

						if (Collisions.collideAnimals(world.animalList.get(i).getBody(), world.animalList)){
                            // reproduce
                        }
						if (Collisions.collideObstacle(world.animalList.get(i).getBody(), world)){
                            // choose new target
                            world.animalList.get(i).getOut();
                            world.animalList.get(i).getRandomLocalTarget();
                        }

						if (Collisions.collideFood(world.animalList.get(i).getBody(), world)) {
                            eat(world, i);

						}

                        // SMELLING COLLISIONS
                        if (world.animalList.get(i).getFoodCarring() < world.animalList.get(i).getStrenght()){
                            if (Collisions.collideFood(world.animalList.get(i).getSmellRange(), world)) {
                                // main target: center of that food.
                                for (int j = 0; j < world.foodList.size(); j++) {
                                    if (Collisions.efficientCollide(world.animalList.get(i).getSmellRange(), world.foodList.get(j).getBody())) {
                                        if (Math.abs(distance(world.animalList.get(i).getPosX(), world.animalList.get(i).getPosY(), world.foodList.get(j).getBody().getCenterX(), world.foodList.get(j).getBody().getCenterY())) < Math.abs(distance(world.animalList.get(i).getPosX(), world.animalList.get(i).getPosY(), world.animalList.get(i).getMainTarget().getBody().getCenterX(), world.animalList.get(i).getMainTarget().getBody().getCenterY()))) {
                                            int tx = (int) world.foodList.get(j).getBody().getCenterX();
                                            int ty = (int) world.foodList.get(j).getBody().getCenterY();
                                            world.animalList.get(i).setMainTarget(new Target(tx, ty));
                                           /* System.out.println("Setting main target");
                                            System.out.println(world.animalList.get(i).getMainTarget().getBody().getCenterX());
                                            System.out.println(world.animalList.get(i).getMainTarget().getBody().getCenterY());
                                            */
                                        }
                                    }
                                }
                            }

                        }

                        else if (world.animalList.get(i).getMainTarget().getBody().getCenterX() != 0){
                            System.out.println("GET RID OF THE FUCKING TARGET");
                            world.animalList.get(i).setMainTarget(new Target(0, 0));
                        }

                    }

				}

			}

		});

		TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build().play();


		stage1.setTitle("Deez nuts!");
		stage1.setScene(scene);

		stage1.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
