public enum PieceType {
    RABBIT('R'), 
    FOX('F'), 
    MUSHROOM('M'), 
    HOLE('O'),
    FOXHEAD('H'),
    FOXTAIl('T');

    private final char type; 
    PieceType(char type) {
        this.type = type;
    }

    /**
     * @return
     */
    public char getType() {
        return type;
    }
}