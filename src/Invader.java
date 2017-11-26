import java.util.ArrayList;

public class Invader extends Sprite implements Common{
    private String path = "res//image//Invader.png";
    private ArrayList<Bomb> bombs;
    private boolean condition;
    public Invader(int x, int y){
        //initial invader
        super(x,y);
        initInvader();
    }
    private void initInvader(){
        visible = true;
        condition = false;
        loadImage(path);
        resizeImage(1);
        setImageDimension(1);
        bombs = new ArrayList<Bomb>();
    }
    public ArrayList<Bomb> getBomb(){
        //get the bomb arraylist
        return bombs;
    }
    public void shoot(){
        //add bomb to bomb list
        bombs.add(new Bomb(x_cor+50, y_cor+80));
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
