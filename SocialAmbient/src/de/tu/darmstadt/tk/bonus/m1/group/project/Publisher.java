/**
 * 
 */
package de.tu.darmstadt.tk.bonus.m1.group.project;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author dinesh
 *
 */
public class Publisher {
	
	static String topic        = "/home/colors";
    //static String content      = "love";
    static int qos             = 2;
    static String broker       = "tcp://test.mosquitto.org:1883";
    static String clientId     = "JavaSample";
    static MemoryPersistence persistence = new MemoryPersistence();
    
    public static boolean publish(String content) {
    	boolean success = false;
    	try {
    		
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            System.out.println("Connecting to broker: "+broker);
            sampleClient.connect(connOpts);
            System.out.println("Connected");
            System.out.println("Publishing message: "+content);
            MqttMessage message = new MqttMessage(content.getBytes());
            message.setQos(qos);
           // sampleClient.publish(topic, message);
            sampleClient.publish(topic, message);
            success = true;
            System.out.println("Message published");
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
}
