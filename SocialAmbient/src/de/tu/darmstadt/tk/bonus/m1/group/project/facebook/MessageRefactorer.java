package de.tu.darmstadt.tk.bonus.m1.group.project.facebook;

/**
 * @author dinesh
 * @author balu
 * @author gopi
 *
 * Performs the task of separation of song name and artist name.
 */
public class MessageRefactorer {
	
	public String[] findSubscriber(String message) {
		
		boolean spotifyTrue = message.contains(Constants.artistFinder);
		System.out.println("spotify is "+spotifyTrue);
		String artist = ""; String track = ""; String song = "";
		//String[] returnVariable = {artist, track, song};
		
		if(spotifyTrue) {			
			artist = findArtist(message);
			track = findTrack(message);
		} else {
			song = findSong(message);
		}	
		String[] returnVariable = {artist, track, song};
		return returnVariable;		
	}

	private String findTrack(String message) {
		// TODO Auto-generated method stub
		String trackName = "";
		String[] rawTrackName = message.split(Constants.trackFinder);
		String[] trackNamer = rawTrackName[1].split(" ");
		trackName = trackNamer[1].trim();
		System.out.println("trackName ::"+trackName);
		return trackName;
	}

	private String findSong(String message) {
		String songName = "";
		String[] rawSongName = message.split(Constants.localPlayer);
		String[] songNamer = rawSongName[1].split(" ");
		songName = songNamer[1].trim();
		System.out.println("songName ::"+songName);
		return songName;
		// TODO Auto-generated method stub
		
	}

	private String findArtist(String message) {
		// TODO Auto-generated method stub
		String artistName = "";
		String[] rawArtistName = message.split(Constants.artistFinder);
		String[] artistNamer = rawArtistName[1].split(" ");
		artistName = artistNamer[1].trim();
		System.out.println("artistName :: "+artistName);
		return artistName; 
	}
}
