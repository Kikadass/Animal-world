package AnimalWorld;

import java.io.*;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.*;
import javafx.scene.control.TextInputDialog;


public class Configuration implements Serializable{
	private String lastFile;
	private int amountFood;
	private int amountObstacles;
	private int amountBugs;
	private int sizeMap;
	private int cycles;

	public Configuration(){
		this.lastFile = new String();
		this.lastFile = "defConfiguration.ser";
		this.amountFood = 100;
		this.amountObstacles = 100;
		this.amountBugs = 1;
		this.sizeMap = 25;
		this.cycles = 100;
	}
	
	public Configuration(String lastFile, int amountFood, int amountObstacles, int amountBugs, int sizeMap, int cycles){
		this.lastFile = lastFile;
		this.amountFood = amountFood;
		this.amountObstacles = amountObstacles;
		this.amountBugs = amountBugs;
		this.sizeMap = sizeMap;
		this.cycles = cycles;
	}
	
	public void setConfiguration(Configuration config){
		this.lastFile = config.lastFile;
		this.amountFood = config.amountFood;
		this.amountObstacles = config.amountObstacles;
		this.amountBugs = config.amountBugs;
		this.sizeMap = config.sizeMap;
		this.cycles = config.cycles;
	}
	
	public void EditConfig(){
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
            dialog.setHeaderText("What would be the size of the World?");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                check = result.get();
            }
        }while(check == null);
		this.sizeMap = Integer.parseInt(check);
		
		check = null;
        do {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Amount of cycles");
            dialog.setHeaderText("How many cycles do you want to do?");
            Optional<String> result = dialog.showAndWait();

            if (result.isPresent()) {
                check = result.get();
            }
        }while(check == null);
		this.cycles = Integer.parseInt(check);
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
		
		this.EditConfig();
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
	
	public int getSize(){
		return this.sizeMap;
	}
	
	public void setSize(int sizeMap){
		this.sizeMap = sizeMap;
	} 

	public int getCycles(){
		return this.cycles;
	}
	
	public void setCycles(int cycles){
		this.cycles = cycles;
	} 

	public void displayConfig(){
		String print;
		print = "Name of file: " + this.lastFile + "\n" + 
				"Amount of Food: " + this.amountFood + "\n" +
				"Amount of Obstacles: " + this.amountObstacles + "\n" + 
				"Amount of Bugs: " + this.amountBugs + "\n" +
				"Size of Map: " + this.sizeMap + "\n" +
				"Cycles: " + this.cycles;

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
				"Size of Map: " + this.sizeMap;

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
			i.printStackTrace();
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
