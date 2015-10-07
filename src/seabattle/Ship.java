package seabattle;

/**
 * Created by Chuyec on 06.10.2015.
 */
public class Ship {
    enum Orientation {
        VERTICAL,
        HORIZONTAL,
    }

    public int size;
    public Deck[] decks;

    public Ship(int size) {
        this.size = size;
        decks = new Deck[size];

        for (int i = 0; i < decks.length; i++) {
            decks[i] = new Deck(0, 0, false);
        }
    }

    void createRandom(Orientation orientation) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < decks.length; i++) {
            decks[i].x = x;
            decks[i].y = y;
            if (orientation == Orientation.VERTICAL) y++;
            if (orientation == Orientation.HORIZONTAL) x++;
        }
    }
}
