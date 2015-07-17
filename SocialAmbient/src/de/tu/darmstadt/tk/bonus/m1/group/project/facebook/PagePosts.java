/**
 * 
 */
package de.tu.darmstadt.tk.bonus.m1.group.project.facebook;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import facebook4j.Comment;
import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.PagableList;
import facebook4j.Post;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

/**
 * @author dinesh
 *
 */
public class PagePosts {

	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws FacebookException, InterruptedException, IOException, URISyntaxException {
		
		//Accessing facebook using API
		Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId(PrivateTokens.appID, PrivateTokens.appSecret);
		AccessToken at = new AccessToken(PrivateTokens.BALU_LONG_TERM_TOKEN);
		facebook.setOAuthAccessToken(at);
		
		String message = "";
		//String artist = "coldplay";
		//String track = "clocks";
		while(true) {
			
			 // Set limit to 1 feeds.
		    ResponseList<Post> feeds = facebook.getFeed("694521957319631",
		            new Reading().limit(1));

		        // For 1 feeds...
		        for (int i = 0; i < feeds.size(); i++) {
		            // Get post.
		            Post post = feeds.get(i);
		            	            	            
		            // Get (string) message.
		            if(null!=post.getMessage()) {
		            	//System.out.println(post.getMessage());
		            	//System.out.println(message);
		            	if(!post.getMessage().trim().equals(message.trim())) {
		            		message = post.getMessage();
			            	System.out.println(message);
			            	
			            	MessageRefactorer refator = new MessageRefactorer();
			            	String[] publishArticles = refator.findSubscriber(message);
			            	//System.out.println();
			            	//publish here
			            	boolean spotifyTrue = message.contains(Constants.artistFinder);
			            	Publisher publisher = new Publisher();
			            	publisher.publish(publishArticles,spotifyTrue);
			            	//play on spotify
			            	//SpotifyCall.playTrackOnSpotify(artist, track);
		            	}		            	
		            }		   	           
		        } 					        
			Thread.sleep(10*1000);
		}		
	}
}
