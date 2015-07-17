/**
 * 
 */
package de.tu.darmstadt.tk.bonus.m1.group.project.spotify;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import de.tu.darmstadt.tk.bonus.m1.group.project.facebook.Constants;
import de.tu.darmstadt.tk.bonus.m1.group.project.localmusic.PlayMusic;
import de.tu.darmstadt.tk.bonus.m1.group.project.localmusic.SubscribeLocal;

/**
 * @author dinesh
 *
 */
public class SubscribeSpotify implements MqttCallback {
	
	MqttClient client;
	MqttMessage message;
	int playCounter = 0;
	PlayMusic musicPlayer =new PlayMusic();
	
	public static void main(String[] args) {

		new SubscribeSpotify().activateSubscriptionForSpotify();
	    
	}
	
	public void activateSubscriptionForSpotify() {
	    try {
	        client = new MqttClient("tcp://test.mosquitto.org:1883", "Sending");
	        client.connect();
	        if(client.isConnected())
	        	System.out.println("client connected");
	        client.setCallback(this);
	        client.subscribe(Constants.spotify);

	    } catch (MqttException e) {
	        e.printStackTrace();
	    }
	}
	
	@Override
	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("spotify message received");
		String artist_track = new String(arg1.toString());
		String[] spotifyIdentifiers = artist_track.split("--");
		System.out.println(spotifyIdentifiers[0]+"--"+spotifyIdentifiers[1]);
		SpotifyCall.playTrackOnSpotify(spotifyIdentifiers[0], spotifyIdentifiers[1]);
	}

}
