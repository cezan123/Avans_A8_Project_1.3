import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
// Github setup test comment
public class Agenda{
	// 3 Arraylists met artist, stage en performance objecten.
	ArrayList<Artist> artists = new ArrayList<>();
	ArrayList<Stage> stages = new ArrayList<>();
	ArrayList<Performance> performances = new ArrayList<>();
	Main main = new Main();
	
	// methode om nieuwe Artiest te maken en toe te voegen aan bestaande File
	public void createArtist(String name, String genre, int popularity) throws FileNotFoundException, IOException, ClassNotFoundException{
		getOldFile();
		boolean artistTest = false;
		for (int i = 0; i <artists.size();i++){
			if (name.equals(artists.get(i).getName())){
				artistTest = true;
			}
		}
		if (artistTest == true){
			main.dubArtist();
			}
			else{
					artists.add(new Artist(name, genre, popularity));
					main.noDubArtist();
				}
		update();
	}
	
	// methode om nieuw Podium te maken en toe te voegen aan bestaande File
	public void createStage(String name, int maxViewers) throws FileNotFoundException, IOException, ClassNotFoundException {
		getOldFile();
		boolean stageTest = false;
		for (int i = 0; i <stages.size();i++){
			if (name.equals(stages.get(i).getName())){
				stageTest = true;
			}
		}
		if (stageTest == true){
			main.dubStage();
			}
			else{
				 stages.add(new Stage(name, maxViewers));
					main.noDubStage();
				}
		update();
	}
	
	// methode om nieuw Optreden te maken en toe te voegen aan bestaande File
	public void createPerformance(Artist artist, Stage stage, int sH, int sM, int eH, int eM) throws FileNotFoundException, ClassNotFoundException, IOException{
		getOldFile();
		boolean performanceTest = false;
		for (int i = 0; i <performances.size();i++){
			if (stage.equals(performances.get(i).getStageName()) && sH == performances.get(i).getSH()
					&& sM == performances.get(i).getSM()){
				performanceTest = true; 
			}
		}
		if (performanceTest == true){
			main.dubPerformance();
			}
			else{
				performances.add(new Performance(artist, stage, sH, sM, eH, eM));
					main.noDubPerformance();
				}
		update();
	}
	
	// methode om de aangepaste arraylisten te schrijven naar een file
	public void update() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("agenda.txt", false));
		oos.writeObject(artists);
		oos.writeObject(stages);
		oos.writeObject(performances);
		artists.clear();
		stages.clear();
		performances.clear();
		oos.close();
	}
	
	//methode om oude arraylist uit File op te halen en toe te voegen aan nieuwe
	//zodat data niet wordt overschreven
	public void getOldFile() throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("agenda.txt"));
		ArrayList<Artist> inputArtists = (ArrayList<Artist>) ois.readObject();
		ArrayList<Stage> inputStages = (ArrayList<Stage>) ois.readObject();
		ArrayList<Performance> inputPerformances = (ArrayList<Performance>) ois.readObject();
		artists.addAll(inputArtists);
		stages.addAll(inputStages);
		performances.addAll(inputPerformances);
	}
}