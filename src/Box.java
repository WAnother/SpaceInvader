public class Box extends Sprite {
    public Box(int x, int y){
        super(x,y);
        initBox();
    }
    private void initBox(){
        visible = true;
        setImageDimension(3);
    }
}
