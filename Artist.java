import java.io.Serializable;

public class Artist implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String artistName;
	private String artistGenre;
	private int artistPopularity;

	public Artist(String name, String genre, int popularity){
		setName(name);
		setGenre(genre);
		setPopularity(popularity);
	}

	// get/set voor artistName
	public String getName(){
		return artistName;
	}
	public void setName(String name){
		artistName = name;
	}

	// get/set voor artistGenre
	public String getGenre(){
		return artistGenre;
	}
	public void setGenre(String genre){
		artistGenre = genre;
	}

	// get/set voor artistPopularity
	public int getPopularity(){
		return artistPopularity;
	}
	public void setPopularity(int popularity){
		if(popularity >=0)
		{
			artistPopularity = popularity;
			if(popularity > 100)
			{
				artistPopularity = 100;
			}
		}
	}
}