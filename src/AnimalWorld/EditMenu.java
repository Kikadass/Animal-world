package AnimalWorld;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class EditMenu {
	private String[] Options = {"Modify Life Forms", "Remove Life Forms", "Add Life Forms"};
	private String WindowName = "Edit";
	private String[] BugChanges = {"Specie", "Name", "Energy", "Max Smell Distance"};
	
	public String[] getOptions(){
		return this.Options;
	}
	
	public String getWindowName(){
		return this.WindowName;
	}
	
	public String[] getBugOptions(ABug[] BugList){
		String[] BugOptions = new String[BugList.length];
		for (int i = 0; i < BugList.length; i++){
			BugOptions[i] = BugList[i].getSymbolID();
		}
		
		return BugOptions;
	}

	public String[] getBugChanges(){
		return this.BugChanges;
	}

	public void ChangeBug(ABug Bug){
 		int choice;
 		do{
 			// string to print
 	 		String print = new String();
 	 		String attributes = Bug.toText();
 	 		// printing all attributes one by one
 	 		for (int j = 0; j < attributes.length(); j++){
 	 			if (attributes.charAt(j) == ';') print += "\n";
 	 			else  print += attributes.charAt(j);
 	 		}
 	 		
	 		choice = ChangeBugMenu(print);
	 		
	 		if (choice == 0) {
	 			String species = null;
	 			do{
	 				species = JOptionPane.showInputDialog(null, "What is the species you would like to create?");
	 			}while (species == null);
	 			Bug.setSpecies(species);
	 			
	 		}
	 		if (choice == 1) {
	 			String name = null;
	 			do{
	 				name = JOptionPane.showInputDialog(null, "What is the new name of the bug?");
	 			}while(name == null);
	 			Bug.setName(name);
	 		}
	 		if (choice == 2) {
	 			String energy1 = null;
	 			do{
	 				energy1 = JOptionPane.showInputDialog(null, "What is the new energy of the Bug?");
	 			}while(energy1 == null);
	 			int energy = Integer.parseInt(energy1);
	 			Bug.setEnergy(energy);
	 		}
	 		if (choice == 3) {
	 			String MaxSensinDist1 = null;
	 			do{
	 				JOptionPane.showInputDialog(null, "What is the new Maximum Smell Distance of the Bug?");
	 			}while(MaxSensinDist1 == null);
	 			int MaxSensinDist = Integer.parseInt(MaxSensinDist1);
	 			Bug.setMaxSensinDist(MaxSensinDist);
	 		}
 		}while(choice != -1);
	}
	
	public int ChangeBugMenu(String BugDetails){
		List<String> optionList = new ArrayList<String>();
	    for (int i = 0; i < this.BugChanges.length; i++){
	    	optionList.add(this.BugChanges[i]);
	    }
	    optionList.add("Exit");
	    
	    Object[] options = optionList.toArray();
	    int value = JOptionPane.showOptionDialog(
	                     null,
	                     BugDetails,
	                     "Changing Bug",
	                     JOptionPane.YES_NO_OPTION,
	                     JOptionPane.QUESTION_MESSAGE,
	                     null,
	                     options,
	                     optionList.get(0));
	
	    
	    String opt = optionList.get(value);

	    for (int i = 0; i < BugChanges.length; i++){
	    	if (opt == "Exit" || value == JOptionPane.CLOSED_OPTION) return -1;
	    	else if (opt == BugChanges[i]) return i;
	    }
	    
	    return -1;
	
	}
}
