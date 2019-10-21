/**
 * ENUM containing the different types of pieces
 */
public enum PieceType {
    RABBIT('R'), 
    FOX('F'), 
    MUSHROOM('M'), 
    HOLE('O'),
    FOXHEAD('H'),
    FOXTAIl('T');

    private final char type;
    
    /**
     * Default constructor initializing instance variables
     */
    PieceType(char type) {
        this.type = type;
    }

    /**
     * gets the type
     * @return char
     */
    public char getType() {
        return type;
    }
}