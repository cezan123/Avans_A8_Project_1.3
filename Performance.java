import java.io.Serializable;

public class Performance implements Serializable{
Artist artist;
Stage stage;
TimeLine time;

public Performance(Artist artist, Stage stage, int sH, int sM, int eH, int eM){
		this.artist = artist;
		this.stage = stage;
		time = new TimeLine(sH, sM, eH, eM);
	}

		public String getArtistName(){
			return artist.getName();
		}
		
		public String getArtistGenre(){
			return artist.getGenre();
		}
		
		public int getArtistPopularity(){
			return artist.getPopularity();
		}
		
		public String getStageName(){
			return stage.getName();
		}
		
		public String getStartTime(){
			return time.getStartTime();
		}
		public String getEndTime(){
			return time.getEndTime();
		}
		public int getSH(){
			return Integer.parseInt(time.getStartHours());
		}
		public int getSM(){
			return Integer.parseInt(time.getStartMinutes());		
		}
		public int getEH(){
			return Integer.parseInt(time.getEndHours());
		}
		public int getEM(){
			return Integer.parseInt(time.getEndMinutes());
		}
	
	}
