public enum PieceType {
    RABBIT('R'), 
    FOX('F'), 
    MUSHROOM('M'), 
    HOLE('O');

    private final char type; 
    PieceType(char type) {
        this.type = type;
    }

    public char getType() {
        return type;
    }
}