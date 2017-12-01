import java.io.*;
import java.applet.*;
import java.net.*;
//class for action's music
public class ActionMusic {
    private String path;
    private URL url;
    private AudioClip audioClip;
    private int selection;
    public ActionMusic(int x){
        selection = x;
    }
    public void play(){
        //choose file name based on given action
        switch (selection){
            case 0: path = new String("res//music//explosion.wav");
                    break;
            case 1: path = new String("res//music//invader.wav");
                    break;
            case 2: path = new String("res//music//invaderkilled.wav");
                    break;
            case 3: path = new String("res//music//shoot.wav");
                    break;
        }
        playSound();
    }
    private void playSound(){
        //play music
        File file = new File(path);
        try {
            url = file.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        audioClip = Applet.newAudioClip(url);
        audioClip.play();
    }
}
