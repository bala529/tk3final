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
 * @author balu
 * @author gopi
 *
 *Gets connection from Facebook graph api via an access token. This token mentioned below is valid till Sep 17th
 */
public class FacebookCall {

	
	@SuppressWarnings("static-access")
	public static void main(String[] args) throws FacebookException, InterruptedException, IOException, URISyntaxException {
		
		//Accessing facebook using API
		Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId(PrivateTokens.appID, PrivateTokens.appSecret);
		AccessToken at = new AccessToken(PrivateTokens.BALU_LONG_TERM_TOKEN);
		facebook.setOAuthAccessToken(at);
		
		String message = "";
		while(true) {			
			 // Set limit to 1 feeds.
		    ResponseList<Post> feeds = facebook.getFeed("1732042247081344",
		            new Reading().limit(1));
		        // For 1 feeds...
		        for (int i = 0; i < feeds.size(); i++) {
		            // Get post.
		            Post post = feeds.get(i);		            	            	            
		            // Get (string) message.
		            if(null!=post.getMessage()) {
		            	if(!post.getMessage().trim().equals(message.trim())) {
		            		message = post.getMessage();
			            	System.out.printf("post read from Facebook:%s",message);
			            	
			            	MessageRefactorer refator = new MessageRefactorer();
			            	String[] publishArticles = refator.findSubscriber(message);
			            	//publish here
			            	boolean spotifyTrue = message.contains(Constants.artistFinder);
			            	Publisher publisher = new Publisher();
			            	//MQtt publish
			            	publisher.publish(publishArticles,spotifyTrue);
		            	}		            	
		            }		   	           
		        } 					        
			Thread.sleep(10*1000); // making the thread sleep for a while to repeat the code every period of time.
		}		
	}
}
