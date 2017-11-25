import java.awt.*;
import javax.swing.*;
import java.awt.event.*;



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
        addKeyListener(new KeyAdpt());
        setFocusable(true);
        initGame();
        timer = new Timer(10, this);
        timer.start();
    }
    public void initGame(){
        //Initial the objects and start the game
        defender = new Defender();

    }
    private void drawBackground(Graphics g){
        //Draw the background
        g.drawImage(background.getImage(),0,0,null );
    }
    private void drawDefender(Graphics g){
        //Draw the defender
        Graphics2D g2d = (Graphics2D)g;
        g.drawImage(defender.getImage(), defender.getXcor(), defender.getYcor(), null);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawBackground(g);
        drawDefender(g);
    }
    public void actionPerformed(ActionEvent e){
        defender.move();
        repaint();
    }

    private class KeyAdpt extends KeyAdapter{
        public void KeyPressed(KeyEvent e){
            defender.KeyPressed(e);
        }
        public void KeyReleased(KeyEvent e){
            defender.KeyReleased(e);
        }
    }


}
