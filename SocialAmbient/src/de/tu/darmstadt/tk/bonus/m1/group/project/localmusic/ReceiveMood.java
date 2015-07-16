package de.tu.darmstadt.tk.bonus.m1.group.project.localmusic;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * 
 * @author manishaluthra
 *
 */
public class ReceiveMood implements MqttCallback {
	MqttClient client;
	MqttMessage message;
	int playCounter = 0;
	PlayMusic play=new PlayMusic();
	
	public static void main(String[] args) {

		new ReceiveMood().doDemo();
	    
	}

	public void doDemo() {
	    try {
	        client = new MqttClient("tcp://test.mosquitto.org:1883", "Sending");
	        client.connect();
	        if(client.isConnected())
	        	System.out.println("client connected");
	        client.setCallback(this);
	        client.subscribe("/home/colors");

	    } catch (MqttException e) {
	        e.printStackTrace();
	    }
	}

	public void connectionLost(Throwable arg0) {
		// TODO Auto-generated method stub
		
	}

	public void deliveryComplete(IMqttDeliveryToken arg0) {
		// TODO Auto-generated method stub
		
	}

	public void messageArrived(String arg0, MqttMessage arg1) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println(message);   
		System.out.println("some message received");
		String str = new String(arg1.toString());
		System.out.println(str);
		
		playCounter++;		
		play.playsong1(str, playCounter);				
		
	}
				
}




