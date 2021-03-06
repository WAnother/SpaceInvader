import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.util.*;

public class Board extends JPanel implements Common, ActionListener{
    private Background background;
    public Defender defender;
    private ArrayList<Invader> invaders;
    private ArrayList<Barrier> barriers;
    private boolean in_progress;
    private boolean in_menu;
    private Timer timer;
    private int score;
    private int lives;
    private String game_message;
    private int total_enemy;
    private int move;
    private int move_down;
    private int count;
    private int bomb_speed;
    public Board(int row, int column, int move_h, int move_v, int fire_speed, int number_lives){
        //initial board
        //Add componentlistener
        this.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {}

            @Override
            public void componentMoved(ComponentEvent e) {}

            @Override
            public void componentShown(ComponentEvent e) {
                Board.this.requestFocusInWindow();
            }

            @Override
            public void componentHidden(ComponentEvent e) {}
        });
        initBoard(row, column,move_h,move_v,fire_speed,number_lives);
    }
    private void initBoard(int row, int column, int move_h, int move_v, int fire_speed, int number_lives){
        //Initial the background image
        background = new Background();
        //Add Keylistener for keyboard input
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
        this.addKeyListener(k1);
        setFocusable(true);
        initGame(row, column,move_h,move_v,fire_speed,number_lives);
        setDoubleBuffered(true);
    }
    private void initGame(int row, int column, int move_h, int move_v, int fire_speed, int number_lives){
        //Initial the objects and start the game
        defender = new Defender();
        initInvader(row, column,move_h, move_v);
        initBarrier();
        total_enemy = invaders.size();
        count = 0;
        in_progress = true;
        score = 0;
        move = 1;
        move_down = 0;
        bomb_speed = setBombSpeed(fire_speed);
        lives = number_lives - 1;
        timer = new Timer(10, this);
        timer.start();
    }
    private void initInvader(int row, int column,int move_h, int move_v){
        //initialize the invaders
        invaders = new ArrayList<Invader>();
        int H_Space = chooseHspace(column);
        int V_Space = chooseVspace(row);
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                Invader invader = new Invader(Invader_Xcor + j * H_Space, Invader_Ycor + i * V_Space, move_h, move_v);
                invaders.add(invader);
            }
        }
    }
    private int setBombSpeed(int i){
        //set the drop bomb speed from invader
        int bomb_speed = 0;
        if(i == 1){
            bomb_speed = Bullet_Speed3;
        }
        else if(i == 2){
            bomb_speed = Bullet_Speed2;
        }
        else if(i == 3){
            bomb_speed = Bullet_Speed;
        }
        return bomb_speed;
    }
    private int chooseHspace(int i){
        //set the space between invader based on the number of enemy
        int Hspace = 0;
        if(i == 4){
            Hspace = Invader_HSpace_4;
        }
        else if(i == 5){
            Hspace = Invader_HSpace_5;
        }
        else if(i == 6){
            Hspace = Invader_HSpace_6;
        }
        return Hspace;
    }
    private int chooseVspace(int i){
        //set the space between invader based on the number of enemy
        int Vspace = 0;
        if(i == 4){
            Vspace = Invader_VSpace_4;
        }
        else if(i == 5){
            Vspace = Invader_VSpace_5;
        }
        else if(i == 6){
            Vspace = Invader_VSpace_6;
        }
        return Vspace;
    }
    private void initBarrier(){
        //initialize the barrier
        barriers = new ArrayList<Barrier>();
        for(int i = 0; i < 3; i++){
            Barrier barrier = new Barrier(Barrier_Xcor + i * 450, Barrier_Ycor);
            barriers.add(barrier);
        }
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
        Iterator iterator = invaders.iterator();
        while(iterator.hasNext()){
            Invader invader = (Invader) iterator.next();
            //If invader is visible then draw, else remove
            if(invader.checkVisible()){
                g.drawImage(invader.getImage(), invader.getXcor(), invader.getYcor(), this );
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
    private void drawBarrier(Graphics g){
        for(Barrier barrier:barriers){
            Iterator iterator = barrier.getBoxes().iterator();
            while(iterator.hasNext()){
                Box box = (Box) iterator.next();
                if(box.checkVisible()){
                    g.setColor(Color.blue);
                    g.fillRect(box.getXcor(),box.getYcor(),box.getWidth(),box.getHeight());
                }
                else{
                    iterator.remove();
                }
            }
        }
    }
    private void drawBackground(Graphics g){
        //Draw the background
        g.drawImage(background.getImage(),0,0,null );
    }
    private void drawScore(Graphics g){
        //draw the score
        g.setColor(Color.blue);
        g.fillRect(1350,0,100,50);
        g.setColor(Color.WHITE);
        Font font = new Font("TimesRoman", Font.BOLD, 16);
        g.setFont(font);
        g.drawString("Score: ", 1360, 25);
        g.drawString(String.valueOf(score), 1410, 25);
        g.drawString("Lives: ", 1360, 45);
        g.drawString(String.valueOf(lives), 1410, 45);
    }
    private void drawInGame(Graphics g){
        //repaint when game is in progress
        drawBackground(g);
        drawDefender(g);
        drawBullet(g);
        drawInvader(g);
        drawBomb(g);
        drawScore(g);
        drawBarrier(g);
    }
    private void drawMessage(Graphics g){
        //draw the message at the end of the game
        Font font = new Font("TimesRoman", Font.BOLD, 30);
        g.setFont(font);
        g.setColor(Color.YELLOW);
        g.drawString(game_message, 600, 450);
    }
    private void drawGameOver(Graphics g){
        //repaint when game is over
        drawBackground(g);
        drawMessage(g);
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
        count++;
        defender.shoot_count++;
        defenderAction();
        bulletAction();
        checkCollision();
        invaderAction();
        bombAction();
        repaint();
    }
    private void invaderAction(){
        //update the invader's movement
        //Movements
        for(Invader invader:invaders){
            if(invader.checkVisible()) {
                move_down = 0;
                if (invader.checkLeftBound()) {
                    move = 1;
                    move_down = 1;
                    break;
                } else if (invader.checkRightBound()) {
                    move = -1;
                    break;
                }
            }
        }
        for(Invader invader:invaders){
            if(invader.checkVisible()){
                invader.setMove_status(move);
                invader.setMove_down(move_down);
            }
        }
        for(Invader invader:invaders){
            invader.move();
            invader.setMove_down(0);
        }
        //Drop bombs
        if(count % bomb_speed == 0) {
            Random random = new Random();
            int position = random.nextInt(invaders.size());
            Invader invader = invaders.get(position);
            if (invader.checkVisible()) {
                if (invader.getCondition()) {
                    ActionMusic actionMusic = new ActionMusic(1);
                    actionMusic.play();
                    invader.shoot();
                } else {
                    int x_cor = invader.getXcor();
                    int y_cor = invader.getYcor();
                    for (Invader temp_invader : invaders) {
                        if (temp_invader != invader && temp_invader.checkVisible()) {
                            if ((x_cor == temp_invader.getXcor()) && (y_cor < temp_invader.getYcor())) {
                                invader.setConditionToFalse();
                                break;
                            } else {
                                invader.setConditionToTrue();
                            }
                        }
                    }
                    if (invader.getCondition() || invaders.size() == 1) {
                        ActionMusic actionMusic = new ActionMusic(1);
                        actionMusic.play();
                        invader.shoot();
                    }
                }
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
        ArrayList<Bullet> bullets = defender.getBullet();
        for(Bullet bullet:bullets){
            if(bullet.checkVisible()) {
                int bullet_x = bullet.getXcor();
                int bullet_y = bullet.getYcor();
                //Check if bullet hits barrier
                for (Barrier barrier : barriers) {
                    ArrayList<Box> boxes = barrier.getBoxes();
                    for (Box box : boxes) {
                        if (box.checkVisible()) {
                            if ((bullet_x + bullet.getWidth() >= box.getXcor()) && (bullet_x <= box.getXcor() + box.getWidth()) && (bullet_y + bullet.getHeight() >= box.getYcor()) && (bullet_y <= box.getYcor() + box.getHeight())){
                                bullet.die();
                                break;
                            }
                        }
                    }
                }
                //Check if the bullet hits invader
                if(bullet.checkVisible()) {
                    for (Invader invader : invaders) {
                        if (invader.checkVisible()) {
                            int invader_x = invader.getXcor();
                            int invader_y = invader.getYcor();
                            if ((bullet_x >= invader_x) && (bullet_x <= invader_x + invader.getWidth() - 50) && (bullet_y >= invader_y) && (bullet_y <= invader_y + invader.getHeight() - 50)) {
                                bullet.die();
                                ActionMusic actionMusic = new ActionMusic(2);
                                actionMusic.play();
                                invader.die();
                                score++;
                                if (score == total_enemy) {
                                    in_progress = false;
                                    game_message = "Game over, you win!!!";
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        //Check if bomb hits barrier
        for(Invader invader: invaders){
            ArrayList<Bomb> bombs = invader.getBomb();
            for(Bomb bomb:bombs){
                if(bomb.checkVisible()) {
                    int bomb_x = bomb.getXcor();
                    int bomb_y = bomb.getYcor();
                    //Check if bomb hits barriers
                    for (Barrier barrier : barriers) {
                        ArrayList<Box> boxes = barrier.getBoxes();
                        for (Box box : boxes) {
                            if(box.checkVisible()) {
                                if ((bomb_x + bomb.getWidth() >= box.getXcor()) && (bomb_x <= box.getXcor() + box.getWidth()) && (bomb_y + bomb.getHeight() >= box.getYcor()) && (bomb_y + bomb.getHeight() <= box.getYcor() + box.getHeight())) {
                                    bomb.die();
                                    box.die();
                                    break;
                                }
                            }
                        }
                    }
                    //Check if bomb hits player
                    if(bomb.checkVisible()) {
                        if ((bomb_x >= defender.getXcor()) && (bomb_x <= defender.getXcor() + defender.getWidth() - 50) && (bomb_y >= defender.getYcor()) && (bomb_y <= defender.getYcor() + defender.getHeight() - 70)) {
                            ActionMusic actionMusic = new ActionMusic(0);
                            actionMusic.play();
                            bomb.die();
                            /*Music music = new Music(1);
                            music.play();*/
                            defender.die();
                            lives--;
                            if(lives == -1) {
                                in_progress = false;
                                game_message = "Game over, you lose!!!";
                            }
                            else{
                                defender = new Defender();
                            }
                            break;
                        }
                    }
                }
            }
        }
        //Check if invader hits defender
        for(Invader invader: invaders){
            if(invader.checkVisible()) {
                int invader_x = invader.getXcor();
                int invader_y = invader.getYcor();
                if(invader_y >= 700){
                    in_progress = false;
                    game_message = "Game over, you lose!!!";
                    break;
                }
                if ((invader_x + invader.getWidth()  >= defender.getXcor() + 50) && (invader_x <= defender.getXcor() + defender.getWidth()) && (invader_y + invader.getHeight()  >= defender.getYcor() + 100) && (invader_y <= defender.getYcor() + defender.getHeight())) {
                    defender.die();
                    lives--;
                    if(lives == -1) {
                        in_progress = false;
                        game_message = "Game over, you lose!!!";
                    }
                    else{
                        defender = new Defender();
                    }
                    break;
                }
            }
        }
    }
}
