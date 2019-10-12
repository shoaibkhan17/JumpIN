
public abstract class Piece {
    char type;

    public Piece(char type) {
        this.type = type;
    }

    public String toString() {
        return this.type + "";
    }
}
