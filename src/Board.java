import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Board extends JPanel implements Common, ActionListener{
    private Background background;
    private Defender defender;
    private ArrayList<Invader> invaders;
    private boolean in_progress;
    private boolean in_menu;
    private Timer timer;
    private int score;
    private int killed_enemy;
    public Board(){
        //initial board
        initBoard();
    }
    private void initBoard(){
        //Initial the background image
        background = new Background();
        //Add Keylistener
        KeyListener k1 = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

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
        in_progress = true;
        initGame();
        setDoubleBuffered(true);
    }
    private void initGame(){
        //Initial the objects and start the game
        score = 0;
        timer = new Timer(10, this);
        timer.start();
        defender = new Defender();
        initInvader();
    }
    private void initInvader(){
        invaders = new ArrayList<Invader>();
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 6; j++){
                Invader invader = new Invader(Invader_Xcor + i * 200, Common.Invader_Ycor + j * 50);
                invaders.add(invader);
            }
        }
    }
    private void drawBackground(Graphics g){
        //Draw the background
        g.drawImage(background.getImage(),0,0,null );
    }
    private void drawDefender(Graphics g){
        //Draw the defender
        if(defender.visible) {
            g.drawImage(defender.getImage(), defender.getXcor(), defender.getYcor(), this);
        }
    }
    private void drawBullet(Graphics g){
        //Draw the bullet
        Iterator iterator = defender.getBullet().iterator();
        while(iterator.hasNext()){
            Bullet bullet = (Bullet) iterator.next();
            //If bullet is visible then draw, else remove
            if(bullet.checkVisible()) {
                g.drawImage(bullet.getImage(), bullet.getXcor(), bullet.getYcor(), this);
            }
            else{
                iterator.remove();
            }
        }
    }
    private void drawInvader(Graphics g){
        //Draw the invader
        killed_enemy = 0;
        Iterator iterator = invaders.iterator();
        while(iterator.hasNext()){
            Invader invader = (Invader) iterator.next();
            //If invader is visible then draw, else remove
            if(invader.checkVisible()){
                g.drawImage(invader.getImage(), invader.getXcor(), invader.getYcor(), this );
            }
            else{
                killed_enemy++;
            }

        }
    }
    private void drawBomb(Graphics g){
        for(Invader invader:invaders){
            Iterator iterator = invader.getBomb().iterator();
            while(iterator.hasNext()){
                Bomb bomb = (Bomb) iterator.next();
                //If bomb is visible then draw, else remove
                if(bomb.checkVisible()){
                    g.drawImage(bomb.getImage(), bomb.getXcor(), bomb.getYcor(),this);
                }
                else{
                    iterator.remove();
                }
            }
        }
    }
    private void drawScore(Graphics g){
       // g.drawRect(1330,0,200,50);
        g.setColor(Color.BLUE);
        g.fillRect(1330,0,200,50);
     /*   int font_size = 20;
        g.setFont(new Font("TimesRoman", Font.BOLD, font_size));
        g.setColor(Color.BLACK);
        g.drawString("Score " + score, 1340, 0);*/
    }
    private void drawInGame(Graphics g){
        //repaint when game is in progress
        drawBackground(g);
        drawDefender(g);
        drawBullet(g);
        drawInvader(g);
        drawBomb(g);
        drawScore(g);
    }
    private void drawGameOver(Graphics g){
        //repaint when game is over
        drawBackground(g);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(in_progress) {
            drawInGame(g);
        }
        else{
            drawGameOver(g);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }
    public void actionPerformed(ActionEvent e){
        //Update the movement
        defenderAction();
        bulletAction();
        checkCollision();
        invaderAction();
        bombAction();
        scoreAction();
        repaint();
    }
    private void scoreAction(){
        score +=  killed_enemy;
    }
    private void invaderAction(){
        //update the invader's movement
        Random random = new Random();
        int position = random.nextInt(invaders.size());
        Invader invader = invaders.get(position);
        if(invader.getCondition()){
            invader.shoot();
        }
        else {
            int x_cor = invader.getXcor();
            int y_cor = invader.getYcor();
            for (Invader temp_invader : invaders) {
                if(temp_invader != invader){
                    if((x_cor == temp_invader.getXcor()) && (y_cor < temp_invader.getYcor())){
                        invader.setConditionToFalse();
                        break;
                    }
                    else{
                        invader.setConditionToTrue();
                    }
                }
            }
            if(invader.getCondition()){
                invader.shoot();
            }
        }

    }
    private void defenderAction(){
        //update the defender's movement
        defender.move();
    }
    private void bulletAction(){
        //update the bullet's movement
        ArrayList<Bullet> bullets = defender.getBullet();
        for(int i = 0; i < bullets.size(); i++){
            Bullet b = bullets.get(i);
            b.move();
        }
    }
    private void bombAction(){
        //update the bomb's movement
        for(Invader invader:invaders){
            ArrayList<Bomb> bombs = invader.getBomb();
            for(int i = 0; i < bombs.size();i++) {
                Bomb b = bombs.get(i);
                b.move();
            }
        }

    }
    private void checkCollision(){
        //Check if bullet hits enemy
        ArrayList<Bullet> bullets = defender.getBullet();
        for(Bullet bullet:bullets){
            int bullet_x = bullet.getXcor();
            int bullet_y = bullet.getYcor();
            for(Invader invader:invaders){
                //if a invader is killed, ignore that one and move on
                if(invader.checkVisible()) {
                    int invader_x = invader.getXcor();
                    int invader_y = invader.getYcor();
                    if ((bullet_x >= invader_x) && (bullet_x <= invader_x + invader.getWidth() - 50) && (bullet_y >= invader_y) && (bullet_y <= invader_y + invader.getHeight() - 50)) {
                        bullet.die();
                        invader.die();
                    }
                }
            }
        }
        //Check if bomb hits player
        for(Invader invader: invaders){
            ArrayList<Bomb> bombs = invader.getBomb();
            for(Bomb bomb:bombs){
                int bomb_x = bomb.getXcor();
                int bomb_y = bomb.getYcor();
                if((bomb_x >= defender.getXcor()) && (bomb_x <= defender.getXcor() + defender.getWidth() - 50) && (bomb_y >= defender.getYcor()) && (bomb_y <= defender.getYcor() + defender.getHeight() - 50)){
                    bomb.die();
                    defender.die();
                    in_progress = false;
                }
            }
        }

    }

}
