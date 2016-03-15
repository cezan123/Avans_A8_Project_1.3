import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JOptionPane;

public class StageGUI extends JFrame{
	Agenda agenda;
	
	public StageGUI(Agenda agenda){
		this.agenda = agenda;
	}
	
	public void GUI(){
		JFrame frame = new JFrame("Nieuw Podium toevoegen");
		
		   JLabel stageName = new JLabel("Naam:");
		   JLabel stageMaximum = new JLabel("Maximum bezoekers:");
		   JLabel dummie = new JLabel("");
		   JLabel dummie2 = new JLabel("");
		   JLabel dummie3 = new JLabel("");
		   JLabel dummie4 = new JLabel("");
		   JLabel dummie5 = new JLabel("");
		   JLabel dummie6 = new JLabel("");
		   JLabel dummie7 = new JLabel("");
		   
		   JTextField stage = new JTextField();
		   JTextField viewers = new JTextField();
		   
		   JButton btnSave = new JButton("opslaan");
		   
		   frame.setLayout(new GridLayout(6,2));
	
		   frame.add(dummie2);
		   frame.add(dummie3);
		   frame.add(stageName);
		   frame.add(stage);
		   frame.add(stageMaximum);
		   frame.add(viewers);
		   frame.add(dummie);
		   frame.add(dummie4);
		   frame.add(dummie5);
		   frame.add(btnSave);
		   frame.add(dummie6);
		   frame.add(dummie7);
		   
		   btnSave.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e){
				   int x = Integer.parseInt(viewers.getText());
				   try {
					   agenda.createStage(stage.getText(), x);
				   	} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					}
				  stage.setText("");
				  viewers.setText("");
				  frame.dispose();
			   }
		   });
		   
		    frame.setSize(400,200);
		    frame.setResizable(false);
		    frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
	}

}