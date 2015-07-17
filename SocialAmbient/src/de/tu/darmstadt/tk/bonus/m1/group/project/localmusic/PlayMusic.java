package de.tu.darmstadt.tk.bonus.m1.group.project.localmusic;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
/**
 * 
 * @author Gopi
 *
 */
public class PlayMusic {
	Clip clip;	
	int playCount;
	int playCounter1;
	float intensity = -6.0f;
	String moodIntensity = null;
	File song;
	boolean sleep;
	public static String[] moodNames = {
		 "loved",
		 "happy",
		 "surprised",
		 "angry",
		 "envy",
		 "sad",
		 "fear",
		 "neutral",
		 "sleep"
		} ;
	
	public  void playTrack(String mood1, int playCounter)
	{						
		if(mood1.toLowerCase().contains(moodNames[0]))
		{
			sleep = false;
			song = new File ("songs/loved.mp3");
			System.out.println("loved received in playsong");			
		}
		else if(mood1.toLowerCase().contains(moodNames[1]))
		{
			sleep = false;
			song = new File ("songs/happy.mp3");
			System.out.println("happy received in playsong");			
		}
		else if(mood1.toLowerCase().contains(moodNames[2]))
		{	
			sleep = false;
			System.out.println("surprised received in playsong");					
			song = new File ("songs/surprise.mp3");			
		}
		else if(mood1.toLowerCase().contains(moodNames[3]))
		{
			sleep = false;
			System.out.println("angry received in playsong");										
			song = new File ("songs/angry.mp3");			
		}
		else if(mood1.toLowerCase().contains(moodNames[4]))
		{
			sleep = false;
			System.out.println("envy received in playsong");
			song = new File ("songs/envy.mp3");			
		}
		else if(mood1.toLowerCase().contains(moodNames[5]))
		{
			sleep = false;
			System.out.println("sad received in playsong");
			song = new File ("songs/sad.mp3");			
		}
		else if(mood1.toLowerCase().contains(moodNames[6]))
		{
			sleep = false;
			System.out.println("fear received in playsong");
			song = new File ("songs/fear.mp3");			
		}
		else if(mood1.toLowerCase().contains(moodNames[7]))
		{
			sleep = false;
			System.out.println("neutral received in playsong");
			song = new File ("songs/neutral.mp3");			
			
		}
		else if(mood1.toLowerCase().contains(moodNames[8]))
		{
			System.out.println("sleep received in playsong");
			song = new File ("songs/envy.mp3");
			sleep = true;
			intensity = -100.0f;
		}
		
		if (mood1.toLowerCase().contains("mild")){
			intensity = -16.0f;
		}
		else if (mood1.toLowerCase().contains("considerable")){
			intensity = -6.0f;
		}
		else if (mood1.toLowerCase().contains("extreme")){
			intensity = 6.0f;
		}
		System.out.println(song);
		
		initializeSong (intensity,playCounter);		
	}
		
	private synchronized void initializeSong (float intensity, int counter) {
		try
		{	
			 if(counter > 1) 
			 {
				 
				 System.out.println("counter >1 if statement"); 
				 if (clip.isOpen())
					 {			
					 	clip.flush();
					 	clip.close();					 	
					 	System.out.println("Previous Song is stopped");
					 }
			 }

			 AudioInputStream audioStream = AudioSystem.getAudioInputStream(song);
			 AudioFormat baseFormat = audioStream.getFormat();
			 AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(), 16, baseFormat.getChannels(),
			         baseFormat.getChannels() * 2, baseFormat.getSampleRate(), false);
			 AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(decodedFormat, audioStream);
			 
			 clip = AudioSystem.getClip();			 
			 clip.open(audioStream2);
			 FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			 gainControl.setValue(intensity); // Reduce volume by 10 decibels. -25.0f			
			 System.out.println(counter);  // step 1			
		}
		catch(Exception e)
		{
				e.printStackTrace();
		}
				
			new Thread() {
				public void run() {
	                try {		                	
	                		clip.start();	
	                		if (sleep==true)
	                		{
	                			clip.flush();
	    					 	clip.close();					 	
	    					 	System.out.println("Sleep Song is played");
	                		}
	                	System.out.println("New Song is being played now"); 	                	
	                } 
	                catch (Exception e) {
	                    System.out.println(e);
	                    
	                }
	           }
			}.start();
			
	}
}
	

