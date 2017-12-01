import java.io.*;
import java.applet.*;
import java.net.*;

public class BackgroundMusic implements Runnable{
    private String path;
    private URL url;
    private AudioClip audioClip;
    private int i = 0;
  //  private int selection;
    public BackgroundMusic(){
       // if(x == 0){
        //    selection = 0;
       // }
   /*     if(x == 1){
            selection = 1;
        }*/
    }
    public void play(){
     /*   switch (selection){
            case 0: path = new String("res//music//bgmusic.wav");
                    break;
            case 1: path = new String("res//music//explosion.wav");
                    break;
            case 2: path = new String("res//music//invader.wav");
                    break;
            case 3: path = new String("res//music//invaderkilled.wav");
                    break;
            case 4: path = new String("res//music//shoot.wav");
                    break;
        }*/
        path = new String("res//music//bgmusic.wav");
        Thread t = new Thread(this);
        t.start();
    }
    public void run() {
      //  if(selection == 0)
            while (true) {
                playSound();
        }
     /*   else{
            playOnce(path);
        }*/
    }
    private void playSound() {
        if(i == 0) {
            i = 1;
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
 /*   private void playOnce(String path){
        File file = new File(path);
        try{
            url = file.toURI().toURL();
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        audioClip = Applet.newAudioClip(url);
        audioClip.play();
      //  audioClip.stop();
    }*/
}
