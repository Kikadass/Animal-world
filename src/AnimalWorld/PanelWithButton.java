package AnimalWorld;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PanelWithButton extends JPanel{
    // Field members
    private AtomicBoolean paused;
    private AtomicBoolean toggle;
    private JButton button1;
    private JButton button2;
    private Thread threadObject;
    boolean keep;
    
    public PanelWithButton(AWorld world, int cycles){
        paused = new AtomicBoolean(false);
        toggle = new AtomicBoolean(false);
        button1 = new JButton();
        button2 = new JButton();
        
        initComponents(world, cycles);

    }

    public void initComponents(AWorld world, int cycles){
    	
        // Construct components
        
        //pause button
        button1.setPreferredSize(new Dimension(100, 50));
        button1.setText("Pause");
        button1.addActionListener(new ButtonListener1());
        add(button1);
        
        //toggle button
        button2.setPreferredSize(new Dimension(100, 50));
        button2.setText("Toggle");
        button2.addActionListener(new ButtonListener2());
        add(button2);

        // Runnable that continually writes to text area
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                for(int i = 0; i < cycles; i++){
                	System.out.println("cycle nº: " + i);
                    if(paused.get()){
                        synchronized(threadObject){
                            // Pause
                            try {
                                threadObject.wait();
                            } 
                            catch (InterruptedException e) {
                            }
                        }
                    }
                    
                    for (int j = 0; j < world.BugList.length; j++){
                    	System.out.println("OK");
            			int movement = world.BugList[j].move(world.BugList[j].getDirectionOfFood());
            			if (movement > 0) world.BugList[j].AddEnergy(movement);
            			world.BugList[j].AddEnergy(-1);
            			if (world.BugList[j].getEnergy() < 0){
            				world.BugList = world.deleteBug(j, world.BugList);
            			}
            			
            			if (toggle.get()){
            				world.printWorld();	
               			}
            			// Sleep
                        try {
                            Thread.sleep(200);
                        } 
                        catch (InterruptedException e){
                        }
            		}
                }
            }
        };
        threadObject = new Thread(runnable);
        threadObject.start();
        
    }

    @Override
    public Dimension getPreferredSize(){
        return new Dimension(200, 150);
    }

    class ButtonListener1 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt) {
        	
            if(!paused.get()){
                button1.setText("Start");
                paused.set(true);
            }
            else{
                button1.setText("Pause");
                paused.set(false);

                // Resume
                synchronized(threadObject){
                    threadObject.notify();
                }
            }
        }
    }
    
    class ButtonListener2 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent evt) {
        	
            if(!toggle.get()){
                button2.setText("Untoggled");
                toggle.set(true);
            }
            else{
                button2.setText("Toggled");
                toggle.set(false);

                // Resume
                synchronized(threadObject){
                    threadObject.notify();
                }
            }
        }
    }
}