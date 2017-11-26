public class Bullet extends Sprite implements Common{
    private String path = "res//image//Defender_Missile.png";
    public Bullet(int x, int y){
        super(x,y);
        //Initial Bullet
        initBullet();
    }
    public void initBullet(){
        visible = true;
        loadImage(path);
        resizeImage(2);
        setImageDimension(2);
    }
    public void move(){
        //update the bullet position
        y_cor -= Bullet_Speed;
        if(y_cor <= 1){
            visible = false;
        }
    }
}
