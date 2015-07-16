/**
 * 
 */
package de.tu.darmstadt.tk.bonus.m1.group.project;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.google.code.jspot.Results;
import com.google.code.jspot.Spotify;
import com.google.code.jspot.Track;

/**
 * @author dinesh
 *
 */
public class SpotifyCall {

	public static void playTrackOnSpotify(String artist, String track) throws IOException, URISyntaxException {
		
		Spotify spotify = new Spotify();

        Results<Track> results = spotify.searchTrack(artist,track);
       // List tracks = results.getItems();
        if(results.getItems() != null) {
        	Track song = (Track) results.getItems().get(0);
        	if(song!=null) {
        		String rawSongID = song.getId();
        		String[] rawSongIDArray = rawSongID.split(":");
        		if(null!=rawSongIDArray && rawSongIDArray.length!=0) {
        			String spotifyID = rawSongIDArray[2];
        			if(null!=spotifyID) {
        				 if(Desktop.isDesktopSupported())
        					{
        					  Desktop.getDesktop().browse(new URI("http://open.spotify.com/track/"+spotifyID));
        					}
        			}
        		}         		        		
        	}        	                       
        }                               
	}	
}
