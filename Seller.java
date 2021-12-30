public class Seller extends GameObject implements View {

    public Seller(int startX, int startY, int move, int health) {
        super(startX, startY, move, health);
    }

    @Override
    public void move() {
        int n = (int)(Math.random()*5);
        switch (n) {
            case 0: //go UP
                y = y - move;
                if(y < 0) y = 0;
                break;
            case 1: //go DOWN
                y = y + move;
                if(y > 9) y = 9;
                break;
            case 2: //go LEFT
                x = x - move;
                if(x < 0) x = 0;
                break;
            case 3: //go RIGHT
                x = x + move;
                if(x > 19) x = 19;
                break;
            case 4: //STAY
                break;
        }
    }

    @Override
    public char getShape() {
        return '#';
    }
}