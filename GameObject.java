public abstract class GameObject implements View {
    protected int move;
    protected int x, y;
    protected int health;

    public GameObject(int startX, int startY, int move, int health) {
        this.x = startX;
        this.y = startY;
        this.move = move;
        this.health = health;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHealth(){
        return health;
    }

    public boolean collide(GameObject p) {
        if(this.x == p.getX() && this.y == p.getY())
            return true;
        else
            return false;
    }

}
