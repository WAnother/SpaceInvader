import java.util.ArrayList;

public class Invader extends Sprite implements Common{
    private String path = "res//image//Invader.png";
    private ArrayList<Bomb> bombs;
    private boolean condition;
    private int move_status;
    public Invader(int x, int y){
        //initial invader
        super(x,y);
        initInvader();
    }
    private void initInvader(){
        visible = true;
        condition = false;
        move_status = 1;
        loadImage(path);
        resizeImage(1);
        setImageDimension(1);
        bombs = new ArrayList<Bomb>();
    }
    public ArrayList<Bomb> getBomb(){
        //get the bomb arraylist
        return bombs;
    }
    public void move(){
        if(move_status == 1){
            move_x = Invader_Speed;
        }
        else{
            move_x = -Invader_Speed;
        }
        x_cor += move_x;
      //  y_cor += y_cor;
    }
    public boolean checkLeftBound(){
        if(x_cor <= 100){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean checkRightBound() {
        if (x_cor >= 1200) {
            return true;
        } else {
            return false;
        }
    }
    public void setMove_status(int x){
        move_status = x;
    }
    public void shoot(){
        //add bomb to bomb list
        if(bombs.size() == 0) {
            bombs.add(new Bomb(x_cor + 50, y_cor + 80));
        }
    }
    public boolean getCondition(){
        return condition;
    }
    public void setConditionToTrue(){
        condition = true;
    }
    public void setConditionToFalse(){
        condition = false;
    }
}
