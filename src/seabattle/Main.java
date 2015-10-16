package seabattle;

import java.util.Scanner;

/**
 * Created by Chuyec on 05.10.2015.
 */
public class Main {
    public static void main(String[] args) {
        playGame();
    }

    private static void playGame() {
        Field field = new Field(10);
        field.fill();
        field.print();
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int x, y;

        do {
            System.out.println("Введите координату X");
            if (scanner.hasNextInt()) {
                x = scanner.nextInt();
            } else {
                System.out.println("Некорректный ввод");
                continue;
            }

            System.out.println("Введите координату Y");
            if (scanner.hasNextInt()) {
                y = scanner.nextInt();
            } else {
                System.out.println("Некорректный ввод");
                continue;
            }

            Ship.State result = field.shot(x - 1, y - 1);
            switch (result) {
                case WHOLE:
                    System.out.println("Мимо!");
                    break;
                case WOUNDED:
                    System.out.println("Ранен!");
                    break;
                case KILLED:
                    System.out.println("Убит!");
                    break;
            }
            field.print();
            System.out.println();
        } while (!field.isEmpty());

        System.out.println("Игра окончена");

        System.out.println("\033[32m Warning! " + (char)27 + "[36m");
        System.out.println("chuyec");
    }
}
