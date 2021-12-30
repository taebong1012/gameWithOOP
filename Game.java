import java.util.Scanner;

public class Game {

    Scanner sc = new Scanner(System.in);

    private static final int MAX_Y = 10;
    private static final int MAX_X = 20;
    private char map[][] = new char[MAX_Y][MAX_X];

    private GameObject[] who = new GameObject[3];

    public Game() {
        for (int y = 0; y < MAX_Y; y++) {
            for (int x = 0; x < MAX_X; x++) {
                map[y][x] = '-';
            }
        }
        who[0] = new Warrior(0, 0, 1, 50);
        who[1] = new Monster(5, 5, 1, 80);
        who[2] = new Seller(15, 8, 1, 10000);
    }

    void run() {
        System.out.println(">>>전사의 모험<<<");
        System.out.println("> 몬스터를 잡고 가위바위보를 하여 물리치세요!");
        System.out.println("> 상인을 만나면 체력이 회복됩니다.");
        System.out.println("> &: 당신    @: 몬스터     #: 상인");
        update();
        draw();

        int recovery = 0;   //상인 방문 횟수
        while (who[0].health > 0 && who[1].health > 0) {
            clear();
            for(int i =0; i<who.length; i++)
                who[i].move();
            update();
            draw();
            if(meetMonster()) {
                rsp();
            }
            if(meetSeller()) {  //게임동안 상인을 만나 체력을 회복할 수 있는 횟수는 2번
                if (recovery < 2) {
                    recovery++;
                    who[0].health += 30;    //상인을 만나서 체력 30 회복
                    System.out.println("상인에게서 물약을 구매하였습니다. 체력이 30 회복되었습니다.\n내 체력: " + who[0].getHealth());
                    System.out.println("상인에게서 물약을 구매할 수 있는 횟수는 " + (2-recovery) + "번 남았습니다.");
                }
                else
                    System.out.println("상인에게서 더이상 물약을 살 수 없습니다.");
            }
        }
        if(who[0].getHealth() <= 0) {
            System.out.println("-------------------");
            System.out.println("|당신은 사망하였습니다.|");
            System.out.println("-------------------");
        }
        else if(who[1].getHealth() <= 0) {
            System.out.println("-------------------");
            System.out.println("|몬스터를 처치하였습니다.|");
            System.out.println("-------------------");
        }
    }

    void update() {
        for(int i = who.length-1; i >= 0; i--) {
            map[who[i].getY()][who[i].getX()] = who[i].getShape();
        }
    }

    void clear() {
        for(int i = 0; i < who.length; i++) {
            map[who[i].getY()][who[i].getX()] = '-';
        }
    }

    void draw() {
        System.out.println();
        for(int y = 0; y<MAX_Y; y++) {
            for (int x = 0; x<MAX_X; x++) {
                System.out.print(map[y][x]);
            }
            System.out.println();
        }
    }

    boolean meetMonster() {
        if(who[0].collide(who[1]))
            return true;
        else
            return false;
    }

    boolean meetSeller() {
        if(who[0].collide(who[2]))
            return true;
        else
            return false;
    }

    //몬스터랑 RockScissorPaper
    void rsp() {
        int usrPick;
        while(true) {
            System.out.println("!!! !!! !!! !!! !!!");
            System.out.print("몬스터와 마주쳤습니다. 숫자를 입력하여 고르세요 (가위: 1 / 바위: 2 / 보: 3)\n>>> ");
            usrPick = sc.nextInt() - 1;    // 가위: 0, 바위: 1, 보: 2 로 변경
            if(usrPick == 0 || usrPick == 1 || usrPick ==2)
                break;
        }

        String[] listRSP = {"가위", "바위", "보"};

        int monsterPick = (int)(Math.random()*3);
        int damage = (int)(Math.random()*20) + 11;   // 10~30의 데미지를 입거나 가할 수 있음.

        if(usrPick == monsterPick) {
            who[0].health -= 20;
            who[1].health -= 20;
            System.out.println("나의 선택: " + listRSP[usrPick] + "\t몬스터: " + listRSP[monsterPick]);
            System.out.println("무승부 입니다. 둘 다 20의 체력을 잃었습니다.\n내 체력: " + who[0].getHealth() + "\t몬스터 체력: "
                    + who[1].getHealth());
        }
        else if(usrPick == 0) {
            switch (monsterPick) {
                case 1:
                    who[0].health = who[0].health - damage;
                    System.out.println("나의 선택: " + listRSP[usrPick] + "\t몬스터: " + listRSP[monsterPick]);
                    System.out.println("[LOSE] 졌습니다. " + damage + "의 데미지를 입었습니다.\n내 체력: " + who[0].getHealth() +
                            "\t몬스터 체력: " + who[1].getHealth());
                    break;
                case 2:
                    who[1].health = who[1].health - damage;
                    System.out.println("나의 선택: " + listRSP[usrPick] + "\t몬스터: " + listRSP[monsterPick]);
                    System.out.println("[WIN!] 이겼습니다. " + damage + "의 데미지를 입혔습니다.\n내 체력: " + who[0].getHealth()
                            + "\t몬스터 체력: " + who[1].getHealth());
            }
        }
        else if(usrPick == 1) {
            switch (monsterPick) {
                case 2:
                    who[0].health = who[0].health - damage;
                    System.out.println("나의 선택: " + listRSP[usrPick] + "\t몬스터: " + listRSP[monsterPick]);
                    System.out.println("[LOSE] 졌습니다. " + damage + "의 데미지를 입었습니다.\n내 체력: " + who[0].getHealth() +
                            "\t몬스터 체력: " + who[1].getHealth());
                    break;
                case 0:
                    who[1].health = who[1].health - damage;
                    System.out.println("나의 선택: " + listRSP[usrPick] + "\t몬스터: " + listRSP[monsterPick]);
                    System.out.println("[WIN!] 이겼습니다. " + damage + "의 데미지를 입혔습니다.\n내 체력: " + who[0].getHealth()
                            + "\t몬스터 체력: " + who[1].getHealth());
            }
        }
        else if(usrPick == 2) { //보
            switch (monsterPick) {
                case 0: //가위
                    who[0].health = who[0].health - damage;
                    System.out.println("나의 선택: " + listRSP[usrPick] + "\t몬스터: " + listRSP[monsterPick]);
                    System.out.println("[LOSE] 졌습니다. " + damage + "의 데미지를 입었습니다.\n내 체력: " + who[0].getHealth() +
                            "\t몬스터 체력: " + who[1].getHealth());
                    break;
                case 1: //바위
                    who[1].health = who[1].health - damage;
                    System.out.println("나의 선택: " + listRSP[usrPick] + "\t몬스터: " + listRSP[monsterPick]);
                    System.out.println("[WIN!] 이겼습니다. " + damage + "의 데미지를 입혔습니다.\n내 체력: " + who[0].getHealth()
                            + "\t몬스터 체력: " + who[1].getHealth());
            }
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}
