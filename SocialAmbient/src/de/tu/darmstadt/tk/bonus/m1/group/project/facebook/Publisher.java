/**
 * 
 */
package de.tu.darmstadt.tk.bonus.m1.group.project.facebook;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author dinesh
 * @author balu
 * @author gopi
 *
 * Spliting based on local or spotify 
 */
public class Publisher {

    static MemoryPersistence persistence = new MemoryPersistence();
    
    public static boolean publish(String[] content,boolean spotifyTrue) {
    	boolean success = false;
    	try {
    		
            MqttClient sampleClient = new MqttClient(Constants.broker, Constants.clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+Constants.broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            //System.out.println("Publishing message: "+content);
            
            String artist = getStringForPublish(content[0]);
            String track = getStringForPublish(content[1]);
            String song = getStringForPublish(content[2]);
            
            String spotifyMsg = artist+"--"+track;
            System.out.printf("Message to Spotify:%s",spotifyMsg);
            
            MqttMessage spotifyMessage = new MqttMessage(spotifyMsg.getBytes());
            spotifyMessage.setQos(Constants.qos);                       
            
            MqttMessage songMessage = new MqttMessage(song.getBytes());
            songMessage.setQos(Constants.qos);
           
            if(!spotifyTrue) {
            	sampleClient.publish(Constants.local, songMessage);
            	success = true;
                System.out.println("Local Message published :: "+song);
            }            	
            else  {
            	
            	sampleClient.publish(Constants.spotify, spotifyMessage);
            	success = true;
                System.out.println("Spotify Message published :: "+spotifyMsg);
            }    
            
            
            //sampleClient.disconnect();
            //System.out.println("Disconnected");
            //System.exit(0);
        } catch(MqttException me) {
            System.out.println("reason "+me.getReasonCode());
            System.out.println("msg "+me.getMessage());
            System.out.println("loc "+me.getLocalizedMessage());
            System.out.println("cause "+me.getCause());
            System.out.println("excep "+me);
            me.printStackTrace();
        }
		return success;
    }

	private static String getStringForPublish(String string) {
		// TODO Auto-generated method stub
		System.out.println("print "+string);
		if(null!= string && !"".equals(string)){
			return string;
		} else {
			return "";
		}
	}
}
