package Model;


import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music 
{
	public static String mp3 = "NeverFrozenBottomFlows.mp3";
	public static Media sound = new Media(transictionalMusic(mp3).toURI().toString());
	public static MediaPlayer mediaPlayer = new MediaPlayer(sound);
	
	public static void handleMusic(String mp3)
	{	
		mediaPlayer.stop();

		sound = new Media(transictionalMusic(mp3).toURI().toString());
		
		mediaPlayer = new MediaPlayer(sound);
		
		mediaPlayer.setCycleCount(5);
		
		mediaPlayer.play();
	}
	
	public static File transictionalMusic(String mp3) 
	{	
		File musicFile = new File("data/Resource/" + mp3);
	
		return musicFile;
	}
}
