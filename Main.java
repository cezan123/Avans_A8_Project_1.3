import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Shape;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JTabbedPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

public class Main extends JFrame
{
	private static final long serialVersionUID = 1L;
	ArrayList<Artist> artists;
	ArrayList<Stage> stages;
	ArrayList<Performance> performances;
	JFrame frame = new JFrame();

	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException{
		Main main = new Main();
		main.mainGUI();
	}
	
	public void update(){
		   ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("agenda.txt"));
			artists = (ArrayList<Artist>) ois.readObject();
			stages = (ArrayList<Stage>) ois.readObject();
			performances = (ArrayList<Performance>) ois.readObject();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}
	public void writeNewFile() throws FileNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("agenda.txt", false));
		oos.writeObject(artists);
		oos.writeObject(stages);
		oos.writeObject(performances);
		oos.close();
	}
	public void clearLists(){
		artists.clear();
		stages.clear();
		performances.clear();
	}

	public void mainGUI() throws ClassNotFoundException, IOException {  
	 Agenda agenda = new Agenda();
	 ArtistGUI artistGUI = new ArtistGUI(agenda);
	 StageGUI stageGUI = new StageGUI(agenda); 
	 PerformanceGUI performanceGUI = new PerformanceGUI(agenda);
	 
	 
	 JTable table = new JTable()
	 {
		private static final long serialVersionUID = 1L;
		public boolean isCellEditable(int row, int column){  
	          return false;  
	        } 
	 };
	 
	 
	 JPanel bottom = new JPanel();
	JPanel normal = new JPanel();
	JPanel tekenen = new JPanel();
	JPanel visueel = new GUI2D();
	
	JTabbedPane tabbedPane = new JTabbedPane();
	 
	  Object[] columns = {"Artiest", "Genre", "Podium", "Start Tijd", "Eind Tijd", "Populariteit"};
	  DefaultTableModel model = new DefaultTableModel();
	  model.setColumnIdentifiers(columns);
	           
	  table.setModel(model);   
	  table.setBackground(Color.LIGHT_GRAY);
	  table.setForeground(Color.black);
	  table.setFont(new Font("",1,22));
	  table.setRowHeight(30);
	  
	  TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
		 table.setRowSorter(sorter);
		 List<RowSorter.SortKey> sortKeys = new ArrayList<>(25);
		 sortKeys.add(new RowSorter.SortKey(3, SortOrder.ASCENDING));
		 sorter.setSortKeys(sortKeys);
	  
	  JLabel dummie = new JLabel("");
	  JButton btnAdd = new JButton("Toevoegen");
	  JButton btnDelete = new JButton("Verwijderen");
	  JButton btnArtist = new JButton("nieuwe Artiest");
	  JButton btnStage = new JButton("nieuw podium");
	  JButton btnPerformance = new JButton("nieuw Optreden");
	
	  JScrollPane pane = new JScrollPane(table);
	  pane.setBounds(0, 0, 880, 200);
	           
	  frame.setLayout(new BorderLayout());
	  normal.setLayout(new BorderLayout());
	  bottom.setLayout(new GridLayout(2,3));        
	  normal.add(pane);
	        
	  bottom.add(btnAdd);
	  bottom.add(btnDelete);
	  bottom.add(dummie);
	  bottom.add(btnArtist);
	  bottom.add(btnStage);
	  bottom.add(btnPerformance);
	 
	  normal.add(bottom, BorderLayout.SOUTH);
	  
	  tabbedPane.addTab("Tabel", normal);
	  tabbedPane.addTab("Agenda", visueel);
	   
	  frame.add(tabbedPane);
	  
	   Object[] row = new Object[6];
	   
	   btnStage.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent e){
			   stageGUI.GUI();
		   }
	   });
	   
	   btnArtist.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent e){
			   artistGUI.GUI();
		   }
	   });
	   
	   btnPerformance.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent e){
		   try {
				performanceGUI.GUI();
			} catch (ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		   }
	   });
	          
	   btnAdd.addActionListener(new ActionListener(){
		   @Override
		   public void actionPerformed(ActionEvent e) {
			   update();
			   model.setRowCount(0);
			for(int i = 0; i<performances.size(); i++){
				row[0] = performances.get(i).getArtistName();
				row[1] = performances.get(i).getArtistGenre();
				row[2] = performances.get(i).getStageName();
				row[3] = performances.get(i).getStartTime();
				row[4] = performances.get(i).getEndTime();
				row[5] = performances.get(i).getArtistPopularity();
				model.addRow(row);
			}
			clearLists();
		   }
	   });
	           
	   btnDelete.addActionListener(new ActionListener(){
		   @Override
		   public void actionPerformed(ActionEvent e) { 
			   update();
			   int x = table.getSelectedRowCount();
			   for(int i=0; i<x; i++){
				   model.removeRow(i);
				   performances.remove(i);
			   }
			   try {
				writeNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		   }
	   });         
	           
	    frame.setSize(900,500);
	    frame.setLocationRelativeTo(null);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
  
    }
	public void noDubArtist(){
		JOptionPane.showMessageDialog(frame, "Nieuwe artiest aangemaakt");
	}
	public void dubArtist(){
		JOptionPane.showMessageDialog(frame, "Deze artiest bestaat al");
	}
	public void noDubStage(){
		JOptionPane.showMessageDialog(frame, "Nieuw podium aangemaakt");
	}
	public void dubStage(){
		JOptionPane.showMessageDialog(frame, "Dit podium bestaat al");
	}
	public void noDubPerformance(){
		JOptionPane.showMessageDialog(frame, "Nieuw optreden aangemaakt");
	}
	public void dubPerformance(){
		JOptionPane.showMessageDialog(frame, "Dit optreden bestaat al");
	}
}

class GUI2D extends JPanel {
	
	private static final long serialVersionUID = 1L;
	ArrayList<Artist> artists;
	ArrayList<Stage> stages;
	ArrayList<Performance> performances;
	
	public GUI2D() throws ClassNotFoundException, IOException
	{
		setPreferredSize( new Dimension(640,480));
		setBackground(Color.white);
		getArrayList();
	}
	public void getArrayList(){
		   ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("agenda.txt"));
			artists = (ArrayList<Artist>) ois.readObject();
			stages = (ArrayList<Stage>) ois.readObject();
			performances = (ArrayList<Performance>) ois.readObject();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D)g;
		
		Shape background = new Rectangle2D.Double(0,0,150,10000);
		boolean drawn = false;
		g2.setColor(Color.white);
		g2.fill(background);
		g2.draw(background);
	
		int x = 0;
		for(int i = 0; i < 40; i++)	{
			Rectangle2D nummer1 = new Rectangle2D.Double(150,x,10000,100);
			
			g2.setColor(Color.WHITE);
			
			if(drawn == true)
			{
				g2.setColor(Color.lightGray);
				g2.fill(nummer1);
				drawn = false;
			}
			else
			{
				g2.setColor(Color.WHITE);
				g2.fill(nummer1);	
				drawn = true;
			}
			x = x + 100;
			
			}
			g2.setColor(Color.BLACK);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 26));
			int hour = 0; 
			int x2 = 150;
			
			for(int i = 0; i < 24; i++) {
			if(hour < 10)
			{
				g2.drawString("0" + hour +  ":00", 30, x2);

				
			}
			else {
				g2.drawString( hour +  ":00", 30, x2);
			}
			if(i == 0 || i == 3 || i == 6 || i == 9 || i == 12 || i == 15) {
				Rectangle2D lines = new Rectangle2D.Double(x2,100,1,1000);
				g2.fill(lines);
			}
			hour++; 
			x2 = x2 + 100;	
			}
			
			for(int i = 0; i < stages.size(); i++){
				g2.setFont(new Font("TimesRoman", Font.BOLD, 50));
				g2.drawString(performances.get(i).getStageName(), ((i+1)*275), 75);
				g2.setColor(Color.black);
			}	
			for(int i = 0; i < performances.size(); i++){
			}
			
			int testbeginuur = 4;
			int testeinduur = 7;
			int beginminuut = 12;
			int eindminuut = 26;
			String naam = "Jan";
			int stagenummer = 1;
			int kolomlengte = 1;
			
			for(int i = 0; i < kolomlengte; i++)
			{
				int y = 100 + testbeginuur * 100;
				int x3 = stagenummer * 150;
				int h = (testeinduur - testbeginuur) * 100  ;
				Rectangle2D tijd = new Rectangle2D.Double(x3, y, 300, h);
				g2.setColor(Color.red);
				g2.fill(tijd);
				g2.setColor(Color.BLACK);
				g2.drawString(naam, x3 + 120,y+ 150);
				g2.draw(tijd);
			
			}
	}
}