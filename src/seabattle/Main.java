package seabattle;

/**
 * Created by Chuyec on 05.10.2015.
 */
public class Main {
    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        Field field = new Field(10);
        field.print();
        field.fill();
        field.print();

        System.out.println("\033[32m Warning! " + (char)27 + "[36m");
        System.out.println("chuyec");
    }
}
