public enum PieceType {
    RABBIT('R'), 
    FOX('F'), 
    MUSHROOM('M'), 
    HOLE('H');

    private final char type; 
    PieceType(char type) {
        this.type = type;
    }

    public char getType() {
        return type;
    }
}