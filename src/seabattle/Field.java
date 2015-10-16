package seabattle;

import java.util.ArrayList;

/**
 * Created by Chuyec on 05.10.2015.
 */
public class Field {
    private Point[][] points;
    private int size;
    private ArrayList<Ship> shipArrayList;

    public Field(int size) {
        this.size = size;
        points = new Point[size][size];

        for (int i = 0; i < points.length; i++) {
            Point[] point = points[i];
            for (int j = 0; j < point.length; j++) {
                points[i][j] = new Point(j, i, '.', Point.Color.AQUA);
            }
        }

        shipArrayList = new ArrayList<>();
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
    void fill() {
        shipArrayList.add(new Ship(4));
        for (int i = 0; i < 2; i++) {
            shipArrayList.add(new Ship(3));
        }
        for (int i = 0; i < 3; i++) {
            shipArrayList.add(new Ship(2));
        }
        for (int i = 0; i < 4; i++) {
            shipArrayList.add(new Ship(1));
        }

        for (int i = 0; i < shipArrayList.size(); i++) {
            Ship ship;
            do {
                ship = shipArrayList.get(i);
                ship.createRandom(size);
            } while(!trySetShip(ship));
        }
    }

    public Ship.State shot(int x, int y) {
        Ship.State state = Ship.State.KILLED;

        for (Ship ship : shipArrayList) {
            state = ship.shot(x, y);

            switch (state) {
                case WHOLE:
                    points[y][x].color = Point.Color.VIOLET;
                    points[y][x].symbol = '#';
                    break;
                case KILLED:
                    shipArrayList.remove(ship);
                case WOUNDED:
                    points[y][x].color = Point.Color.RED;
                    points[y][x].symbol = Deck.DESTROYED_DECK;
                    break;
            }

            if (state != Ship.State.WHOLE) {
                break;
            }
        }
        return state;
    }

    public boolean isEmpty() {
        return shipArrayList.isEmpty();
    }

    /**
     * Попробовать установить один корабль
     * @param ship Корабль
     * @return true, если удалось, иначе false
     */
    private boolean trySetShip(Ship ship) {
        for (int i = 0; i < ship.size; i++) {
            Deck deck = ship.decks[i];
            if (!checkPoint(deck)) {
                return false;
            }
        }

        for (int i = 0; i < ship.size; i++) {
            Deck deck = ship.decks[i];
            points[deck.y][deck.x].symbol = deck.symbol;
            points[deck.y][deck.x].color = deck.color;
        }
        return true;
    }

    /**
     * Проверить точку на поле на возможность поставить туда палубу корабля
     * @param deck Палуба
     * @return true, если палубу возможно поставить, иначе false
     */
    private boolean checkPoint(Deck deck) {
        if (points[deck.y][deck.x].symbol != '.') {
            return false;
        }

        for (int i = deck.y - 1; i <= deck.y + 1; i++) {
            for (int j = deck.x - 1; j <= deck.x + 1; j++) {
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
