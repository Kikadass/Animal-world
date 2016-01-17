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

	public String[] getBugChanges(){
		return this.BugChanges;
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
