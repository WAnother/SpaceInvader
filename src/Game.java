import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class Game extends JFrame implements Common{
    private Board board;
    private Menu menu;
    private JPanel screen;
    private CardLayout cardLayout;
    private boolean start;
    public Game() {
        initGame();
    }
    private void initGame(){
        //Create a new frame
        start = false;
        //board = new Board();
        //String path = "res//music//Radioactive.mp3";
        menu = new Menu();
        screen = new JPanel(new CardLayout());
        JButton jButton = new JButton("Enter");
        jButton.setOpaque(true);
        jButton.setBackground(Color.white);
        jButton.setActionCommand("Nothing");
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton jButton = (JButton) e.getSource();
                menu.check_start(jButton);
                if(jButton.getBackground() == Color.blue){
                    Board board = new Board(menu.getRow(), menu.getColumn(),menu.getMove_h(),menu.getMove_v(), menu.getFire_speed(), menu.getNumber_lives());
                    screen.add(board, "Game Screen");
                    cardLayout.show(screen,"Game Screen");
                }
            }
        };
        jButton.addActionListener(actionListener);
        menu.add(jButton);
        screen.add(menu, "Menu Screen");
       // screen.add(board, "Game Screen");
        cardLayout= (CardLayout)screen.getLayout();
        cardLayout.show(screen,"Menu Screen");
        add(screen);
        pack();
        setSize(Frame_Width, Frame_Height);
        setTitle("Space Invader");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        music();
        //setFocusable(true);
    }
    public void music(){
        try{
            URL url;
            File file = new File("res//music//bgmusic.wav");
            url = file.toURI().toURL();
            AudioClip audioClip;
            audioClip = Applet.newAudioClip(url);
            audioClip.loop();
        }catch (Exception e){}
    }
    public static void main(String[] args){
        //Game entry point
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game game = new Game();
               // game.music();
           }
        });
    }


}
