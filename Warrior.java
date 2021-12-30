import java.util.Scanner;

public class Warrior extends GameObject implements View{
    private Scanner sc = new Scanner(System.in);

    public Warrior(int startX, int startY, int move, int health) {
        super(startX, startY, move, health);
    }

    @Override
    public void move() {
        char key;
        while (true) {
            System.out.print("왼쪽(a) 위쪽(w) 아래쪽(s) 오른쪽(d) >> ");
            key = sc.next().charAt(0);
            if(key == 'a' || key == 'w' || key == 's' || key == 'd')
                break;
        }

        switch(key) {
            case 'a':
                x--;
                if(x<0) x=0;
                break;
            case 'd':
                x++;
                if(x>19) x=19;
                break;
            case 'w':
                y--;
                if(y<0) y=0;
                break;
            case 's':
                y++;
                if(y>9) y=9;
                break;
        }
    }

    @Override
    public char getShape() {
        return '&';
    }
}
