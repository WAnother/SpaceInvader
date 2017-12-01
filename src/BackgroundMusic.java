import java.io.*;
import java.applet.*;
import java.net.*;
//class for the background music
public class BackgroundMusic implements Runnable{
    private String path;
    private URL url;
    private AudioClip audioClip;
    private int i = 0;
    public BackgroundMusic(){}
    public void play(){
        //use thread to play background music to avoid conflict
        path = new String("res//music//bgmusic.wav");
        Thread t = new Thread(this);
        t.start();
    }
    public void run() {
            while (true) {
                playSound();
        }
    }
    private void playSound() {
        //only play one time
        if(i == 0) {
            i = 1;
            //play music
            File file = new File(path);
            try {
                url = file.toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            audioClip = Applet.newAudioClip(url);
            audioClip.play();
            audioClip.loop();
        }
    }
}
