package seabattle;

import java.util.Random;

/**
 * Created by Chuyec on 06.10.2015.
 */
public class Ship {
    enum Orientation {
        VERTICAL,
        HORIZONTAL;

        private static final Orientation[] VALUES = values();

        public static Orientation getRandom() {
            Random random = new Random();
            return VALUES[random.nextInt(VALUES.length)];
        }
    }

    enum State {
        WHOLE,
        WOUNDED,
        KILLED,
    }

    public int size;
    public Deck[] decks;
    Orientation orientation;
    State state;

    public Ship(int size) {
        this.size = size;
        decks = new Deck[size];

        for (int i = 0; i < decks.length; i++) {
            decks[i] = new Deck(0, 0, false);
        }
        state = State.WHOLE;
    }

    public void createRandom(int border) {
        orientation = Orientation.getRandom();

        Random random = new Random();
        int x;
        int y;
        if (orientation == Orientation.VERTICAL) {
            x = random.nextInt(border);
            y = random.nextInt(border - size);
        }
        else {
            x = random.nextInt(border - size);
            y = random.nextInt(border);
        }

        for (int i = 0; i < decks.length; i++) {
            decks[i].x = x;
            decks[i].y = y;
            if (orientation == Orientation.VERTICAL) y++;
            if (orientation == Orientation.HORIZONTAL) x++;
        }
    }

    public State shot(int x, int y) {
        State state = State.WHOLE;

        for (int i = 0; i < decks.length; i++) {
            Deck deck = decks[i];
            if (deck.x == x && deck.y == y) {
                deck.destroy();
                state = State.WOUNDED;
                break;
            }
        }

        if (isAllDecksDestroyed()) {
            state = State.KILLED;
        }

        return state;
    }

    private boolean isAllDecksDestroyed() {
        for (int i = 0; i < decks.length; i++) {
            Deck deck = decks[i];
            if (!deck.isDestroyed()) {
                return false;
            }
        }
        return true;
    }
}
