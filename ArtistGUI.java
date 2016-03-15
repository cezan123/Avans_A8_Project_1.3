import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ArtistGUI extends JFrame{
	Agenda agenda;
	
	public ArtistGUI(Agenda agenda){
		this.agenda = agenda;
	}
	
	public void GUI(){
		JFrame frame = new JFrame("Nieuwe artiest toevoegen");
		
		   JLabel artistName = new JLabel("     Naam:");
		   JLabel artistGenre = new JLabel("     Genre:");
		   JLabel artistPopularity = new JLabel("     populariteit:");
		   JLabel dummie = new JLabel("");
		   JLabel dummie2 = new JLabel("");
		   JLabel dummie3 = new JLabel("");

		   JLabel dummie8 = new JLabel("");
		   JLabel dummie9 = new JLabel("");
		   
		   JTextField name = new JTextField();
		   JTextField genre = new JTextField();
		   JTextField popularity = new JTextField();
		   
		   JButton btnSave = new JButton("opslaan");
		   
		   frame.setLayout(new GridLayout(9,2));
		   
		   frame.add(dummie2);
		   frame.add(dummie3);
		   frame.add(artistName);
		   frame.add(name);
		   frame.add(artistGenre);
		   frame.add(genre);
		   frame.add(artistPopularity);
		   frame.add(popularity);
		   frame.add(dummie8);
		   frame.add(dummie9);
		   frame.add(dummie);
		   frame.add(btnSave);
		   
		   btnSave.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent e){
			   int x = Integer.parseInt(popularity.getText());
			   try {
				   agenda.createArtist(name.getText(), genre.getText(),x);
			   	} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
			   	} catch (FileNotFoundException e1) {
					e1.printStackTrace();
			   	} catch (IOException e1) {
					e1.printStackTrace();
				}
			   name.setText("");
			   genre.setText("");
			   popularity.setText("");
			   frame.dispose();
			   }
		   });
		   
		    frame.setSize(400,300);
		    frame.setResizable(false);
		    frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
	}

}
