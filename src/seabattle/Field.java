package seabattle;

import java.util.Random;

/**
 * Created by Chuyec on 05.10.2015.
 */
public class Field {
    private Point[][] points;
    private int size;

    public Field(int size) {
        this.size = size;
        points = new Point[size][size];

        for (int i = 0; i < points.length; i++) {
            Point[] point = points[i];
            for (int j = 0; j < point.length; j++) {
                points[i][j] = new Point(j, i, '.', Point.Color.AQUA);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void print() {
        System.out.printf("   ");
        for (int i = 1; i < size + 1; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < points.length; i++) {
            Point[] point = points[i];
            for (int j = 0; j < point.length; j++) {
                if (j == 0) {
                    System.out.printf("%2d ", i + 1);
                }
                Point cell = point[j];
                cell.print();
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    /**
     * Установить все корабли на поле рандомно
     */
    void setShipsRandom() {
        Ship ship4 = new Ship(4);
        Random random = new Random();

        ship4.createRandom(Ship.Orientation.VERTICAL);
        trySet(ship4);
    }

    /**
     * Попробовать установить один корабль
     * @param ship Корабль
     * @return true, если удалось, иначе false
     */
    private boolean trySet(Ship ship) {
        for (int i = 0; i < ship.size; i++) {
            Deck deck = ship.decks[i];
            points[deck.y][deck.x].symbol = Deck.WHOLE_DECK;
            points[deck.y][deck.x].color = Point.Color.GREEN;
        }
        return true;
    }

    /**
     * Проверить точку на поле на возможность поставить туда палубу корабля
     * @param deck Палуба
     * @return true, если палубу возможно поставить, иначе false
     */
    private boolean chekPoint(Deck deck) {
        if (points[deck.y][deck.x].symbol != '.') {
            return false;
        }

        for (int i = deck.y - 1; i < deck.y + 1; i++) {
            for (int j = deck.x - 1; j < deck.x + 1; j++) {
                if (i < 0 || j < 0) {
                    continue;
                }
                if (i > size - 1 || j > size - 1) {
                    continue;
                }
                if (points[i][j].symbol != '.') {
                    return false;
                }
            }
        }
        return true;
    }
}
