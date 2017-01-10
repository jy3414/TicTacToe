class Move {

    private int x;
    private int y;

    Move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    Move() {
        this.x = -1;
        this.y = -1;
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }
}
