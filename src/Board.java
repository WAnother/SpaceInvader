import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.KeyListener;

public class Board extends JPanel implements Common, ActionListener{
    private Background background;
    private Defender defender;
    private Invader invader;
    private boolean in_progress;
    private Timer timer;

    public Board(){
        //initial board
        initBoard();
    }
    private void initBoard(){
        //Initial the background image
        background = new Background();
        in_progress = true;
        KeyListener k1 = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                    defender.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                    defender.keyReleased(e);
            }
        };
        addKeyListener(k1);
        setFocusable(true);
        initGame();
    }
    public void initGame(){
        //Initial the objects and start the game
        timer = new Timer(10, this);
        timer.start();
        defender = new Defender();

    }
    private void drawBackground(Graphics g){
        //Draw the background
        g.drawImage(background.getImage(),0,0,null );
    }
    private void drawDefender(Graphics g){
        //Draw the defender
        g.drawImage(defender.getImage(), defender.getXcor(), defender.getYcor(), this);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g);
        drawDefender(g);
        Toolkit.getDefaultToolkit().sync();
    }
    public void actionPerformed(ActionEvent e){
        defender.move();
        repaint();
    }


}
